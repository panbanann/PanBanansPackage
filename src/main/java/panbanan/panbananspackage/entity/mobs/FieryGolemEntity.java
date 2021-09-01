package panbanan.panbananspackage.entity.mobs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.GolemEntity;
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

public class FieryGolemEntity extends GolemEntity implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);

    public FieryGolemEntity(EntityType<? extends GolemEntity> type, World worldIn)
    {
        super(type, worldIn);
        this.ignoreCameraFrustum = true;
        this.stepHeight = 1.0F;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving()){
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
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }


    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 10.0F));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.add(4, new WanderNearTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.add(5, new LookAroundGoal(this));
        //this.targetSelector.add(2, new RevengeGoal(this, new Class[0]));
        this.targetSelector.add(3, new FollowTargetGoal(this, PlayerEntity.class, true));
        //this.targetSelector.add(3, new FollowTargetGoal(this, MobEntity.class, 5, false, false, (livingEntity) -> {
        //    return livingEntity instanceof Monster && !(livingEntity instanceof CreeperEntity);
        this.goalSelector.add(2, new IronGolemWanderAroundGoal(this, 0.9D));
        //}));
        super.initGoals();
    }

    @Override
    protected SoundEvent getAmbientSound(){
        return SoundEvents.ENTITY_IRON_GOLEM_STEP;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn){
        return SoundEvents.ENTITY_IRON_GOLEM_HURT;
    }
    @Override
    protected SoundEvent getDeathSound(){
        return SoundEvents.ENTITY_IRON_GOLEM_DEATH;
    }
}
