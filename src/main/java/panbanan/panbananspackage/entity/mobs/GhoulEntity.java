package panbanan.panbananspackage.entity.mobs;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.TameableEntity;
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

public class GhoulEntity extends HostileEntity implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);

    public GhoulEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.ignoreCameraFrustum = true;
        this.stepHeight = 1.0F;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        /*if (event.isMoving() || this.getMovementSpeed() > 0.0F){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }
        if ((this.dead || this.getHealth() < 0.01)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("death", false));
            return PlayState.CONTINUE;
        }

         */
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
        return 0.625F * dimensions.height;
    }
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new GhoulEntity.AttackGoal());
        super.initGoals();
    }

    class AttackGoal extends MeleeAttackGoal {
        public AttackGoal() {
            super(GhoulEntity.this, 1.0D, true);
        }

        protected double getSquaredMaxAttackDistance(LivingEntity entity) {
            float f = GhoulEntity.this.getWidth() - 0.5F;
            return (double)(f * 2.0F * f * 2.0F + entity.getWidth());
        }
    }

    //TODO Goal bite and run, seek meat, dash, dodge, apply effect
    //TODO Bite and Run - Attack player and run when get hit. Up to 8 blocks away. 16 range for sense player.
    //TODO When effect applied, player drop meat - entity or rotten flesh - random place in 8 block distance from player.
    //TODO Seek meat - collect dropped meat in range of 16 blocks. When meat collected heal 50% hp, next attack deals additional heart of dmg (or 15% more)
    //TODO Dash - give velocity - if collide with player apply effect, then bite and run.
    //TODO Dodge (?)
    //TODO Effect - weakening wound - player get weakness effect for 5 second while hitting any entity they lose 1 heart hp, and after effect ends player dropping meat.
    /*TODO SpawnRules:
       Spawn on swamp and dark forest during day,
       Plains 15% - chance to spawn close to river during night
       5% to spawn pack of 3
       Max 3 ghouls can be spawned
       If graveyards mod in - spawn in there.
     */

}
