package panbanan.panbananspackage.entity.mobs;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class NiddhogEntity extends HostileEntity implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);

    public NiddhogEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.ignoreCameraFrustum = true;
        this.stepHeight = 1.0F;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving() || this.getMovementSpeed() > 0.0F){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }
        if ((this.dead || this.getHealth() < 0.01)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("death", false));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    protected SoundEvent getAmbientSound(){
        return SoundEvents.ENTITY_ENDER_DRAGON_AMBIENT;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn){
        return SoundEvents.ENTITY_ENDER_DRAGON_HURT;
    }
    @Override
    protected SoundEvent getDeathSound(){
        return SoundEvents.ENTITY_ENDER_DRAGON_HURT;
    }
    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 2.55F;
    }

    @Override
    protected void initGoals() {
        this.targetSelector.add(1, new FollowTargetGoal(this, PlayerEntity.class, true));
        this.goalSelector.add(2, new NiddhogEntity.AttackGoal());
        this.targetSelector.add(4, new FollowTargetGoal(this, MerchantEntity.class, true));
        this.targetSelector.add(4, new FollowTargetGoal(this, IronGolemEntity.class, true));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.4D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
        super.initGoals();
    }

    class AttackGoal extends MeleeAttackGoal {
        public AttackGoal() {
            super(NiddhogEntity.this, 1.0D, true);
        }

        protected double getSquaredMaxAttackDistance(LivingEntity entity) {
            float f = NiddhogEntity.this.getWidth() - 0.1F;
            return (double)(f * 2.0F * f * 2.0F + entity.getWidth());
        }
    }

}
