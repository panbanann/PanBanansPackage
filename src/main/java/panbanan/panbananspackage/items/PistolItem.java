package panbanan.panbananspackage.items;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import panbanan.panbananspackage.entity.projectile.TestProjectileCopyPaste;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;

public class PistolItem extends Item implements IAnimatable, ISyncable {

    public AnimationFactory factory = new AnimationFactory(this);
    public String controllerName = "controller";
    public static final int ANIM_OPEN = 0;

    public PistolItem() {
        super(new Item.Settings().group(ItemGroup.COMBAT).maxCount(1).maxDamage(501));
        GeckoLibNetwork.registerSyncable(this);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int remainingUseTicks) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity) entityLiving;
            if (stack.getDamage() < (stack.getMaxDamage() - 1)) {
                playerentity.getItemCooldownManager().set(this, 30);
                if (!worldIn.isClient) {

                    TestProjectileCopyPaste abstractarrowentity = createArrow(worldIn, stack, playerentity);
                    abstractarrowentity.setVelocity(playerentity, playerentity.getPitch(), playerentity.getYaw(), 0.0F,
                            0.5F * 1.5F, 0.5F);

                    abstractarrowentity.setDamage(0.05);
                    abstractarrowentity.age = 35;
                    abstractarrowentity.hasNoGravity();

                    //taking durability dmg
                    stack.damage(1, entityLiving, p -> p.sendToolBreakStatus(entityLiving.getActiveHand()));
                    worldIn.spawnEntity(abstractarrowentity);
                }
                if (!worldIn.isClient) {
                    final int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerWorld) worldIn);
                    GeckoLibNetwork.syncAnimation(playerentity, this, id, ANIM_OPEN);
                    for (PlayerEntity otherPlayer : PlayerLookup.tracking(playerentity)) {
                        GeckoLibNetwork.syncAnimation(otherPlayer, this, id, ANIM_OPEN);
                    }
                }
            }
        }
    }

    public TestProjectileCopyPaste createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        TestProjectileCopyPaste arrowentity = new TestProjectileCopyPaste(worldIn, shooter);
        return arrowentity;
    }

    public static float getArrowVelocity(int charge) {
        float f = (float) charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    public TestProjectileCopyPaste customeArrow(TestProjectileCopyPaste arrow) {
        return arrow;
    }

    public <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, controllerName, 1, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void onAnimationSync(int id, int state) {
        if (state == ANIM_OPEN) {
            final AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, controllerName);
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("firing", false));
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return false;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    /*@Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText(
                "Ammo: " + (stack.getMaxDamage() - stack.getDamage() - 1) + " / " + (stack.getMaxDamage() - 1))
                .formatted(Formatting.ITALIC));
    }*/
}