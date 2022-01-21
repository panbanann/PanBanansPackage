package panbanan.panbananspackage.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import panbanan.panbananspackage.entity.EntityRegister;
import panbanan.panbananspackage.entity.mobs.MimicEntity;

import java.util.Random;

@Mixin(ChestBlock.class)
public abstract class ChestBlockMixin {
    public Random rand = new Random();

    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        int int_random = rand.nextInt(100);
        //System.out.println("The block was used.");
        BlockEntity chestBlockEntity = world.getBlockEntity(pos);
        LootableContainerBlockEntityAccessor tableAccess = ((LootableContainerBlockEntityAccessor) chestBlockEntity);
        @Nullable
        Identifier lootTableId = tableAccess.getLootTableId();
        if (lootTableId != null && int_random<=9) {
            //System.out.println("ChestLootId wasn't null.");
            AreaEffectCloudEntity poofCloud = new AreaEffectCloudEntity(world, pos.getX(), pos.getY(), pos.getZ());
            player.playSound(SoundEvents.BLOCK_CHEST_OPEN, 1.0F, 0.5F);
            poofCloud.setRadius(10.0F);
            poofCloud.setWaitTime(0);
            poofCloud.setParticleType(ParticleTypes.EXPLOSION);
            poofCloud.setDuration(0);
            world.spawnEntity(poofCloud);
            if (!world.isClient) {
                MimicEntity mimicEntity = EntityRegister.MIMIC.create(world);
                mimicEntity.refreshPositionAndAngles(pos, 0, 180);
                mimicEntity.setTarget(player);
                world.spawnEntity(mimicEntity);
                world.removeBlock(pos, false);
            }

        }

    }
}
