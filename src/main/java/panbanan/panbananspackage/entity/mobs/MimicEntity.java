package panbanan.panbananspackage.entity.mobs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.MobSpawnerEntry;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;

public class MimicEntity extends SlimeEntity implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);
    public float targetStretch;
    public float stretch;
    public float lastStretch;
    private boolean onGroundLastTick;

    public MimicEntity(EntityType<? extends SlimeEntity> type, World worldIn){
        super(type, worldIn);
        this.ignoreCameraFrustum = true;
    }
// Animation control //
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.mimic.moving", true));
            return PlayState.CONTINUE;
        }


        if ((this.isDead() || this.dead || this.getHealth() < 0.01)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.mimic.death", false));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.mimic.idle", true));
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
//Endof Animation Control//

//MobEntity Overrides//
    @Override
    protected void initGoals() {

        super.initGoals();
        /*this.goalSelector.add(1, new MimicEntity.FaceTowardTargetGoal(this));
        this.goalSelector.add(2, new MimicEntity.MoveGoal(this));
        this.targetSelector.add(3, new FollowTargetGoal(this, PlayerEntity.class, true));*/
        //this.goalSelector.add(4, new );
    }

    @Override
    public boolean canPickupItem(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canSpawn(WorldAccess world, SpawnReason spawnReason) {
        return super.canSpawn(world, spawnReason);
    }

    @Override
    protected void updatePostDeath() {
        ++this.deathTime;
        if (this.deathTime == 20) {
            this.remove();
        }
    }

    /*@Override
    public void checkDespawn(){
        this.despawnCounter = 0;
        this.cannotDespawn();
    }*/
//Endof Overrides//

// Mimic Methods //
    protected boolean canAttack() {
        return true;
    }

    protected boolean makesJumpSound() {
       return true;
   }

    public void tick() {
        this.stretch += (this.targetStretch - this.stretch) * 0.5F;
        this.lastStretch = this.stretch;
        super.tick();
        if (this.onGround && !this.onGroundLastTick) {
            int i = 1;

            for(int j = 0; j < i * 8; ++j) {
                float f = this.random.nextFloat() * 6.2831855F;
                float g = this.random.nextFloat() * 0.5F + 0.5F;
                float h = MathHelper.sin(f) * (float)i * 0.5F * g;
                float k = MathHelper.cos(f) * (float)i * 0.5F * g;
                this.world.addParticle(this.getParticles(), this.getX() + (double)h, this.getY(), this.getZ() + (double)k, 0.0D, 0.0D, 0.0D);
            }

            this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            this.targetStretch = -0.5F;
        } else if (!this.onGround && this.onGroundLastTick) {
            this.targetStretch = 1.0F;
        }

        this.onGroundLastTick = this.onGround;
        this.updateStretch();
    }

    protected void updateStretch() {
        this.targetStretch *= 0.6F;
    }

    protected int getTicksUntilNextJump() {
        return this.random.nextInt(20) + 10;
    }


// Mimic goals //
    static class MoveGoal extends Goal {
        private final MimicEntity mimic;

        public MoveGoal(MimicEntity mimic) {
            this.mimic = mimic;
            this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
        }

        public boolean canStart() {
            return !this.mimic.hasVehicle();
        }

        /*public void tick() {
            ((MimicEntity.MimicMoveControl)this.mimic.getMoveControl()).move(1.0D);
        }*/
    }

    static class FaceTowardTargetGoal extends Goal {
        private final MimicEntity mimic;
        private int ticksLeft;

        public FaceTowardTargetGoal(MimicEntity mimic) {
            this.mimic = mimic;
            this.setControls(EnumSet.of(Goal.Control.LOOK));
        }

        public boolean canStart() {
            LivingEntity livingEntity = this.mimic.getTarget();
            if (livingEntity == null) {
                return false;
            } else if (!livingEntity.isAlive()) {
                return false;
            } else {
                return livingEntity instanceof PlayerEntity && ((PlayerEntity)livingEntity).abilities.invulnerable ? false : this.mimic.getMoveControl() instanceof MimicEntity.MimicMoveControl;
            }
        }

        public void start() {
            this.ticksLeft = 300;
            super.start();
        }

        public boolean shouldContinue() {
            LivingEntity livingEntity = this.mimic.getTarget();
            if (livingEntity == null) {
                return false;
            } else if (!livingEntity.isAlive()) {
                return false;
            } else if (livingEntity instanceof PlayerEntity && ((PlayerEntity)livingEntity).abilities.invulnerable) {
                return false;
            } else {
                return --this.ticksLeft > 0;
            }
        }

        public void tick() {
            this.mimic.lookAtEntity(this.mimic.getTarget(), 10.0F, 10.0F);
            ((MimicEntity.MimicMoveControl)this.mimic.getMoveControl()).look(this.mimic.yaw, this.mimic.canAttack());
        }
    }

// Mimic Media //
    protected SoundEvent getJumpSound() {
        return SoundEvents.ENTITY_SLIME_JUMP;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SLIME_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SLIME_DEATH;
    }

    protected SoundEvent getSquishSound() {
        return SoundEvents.ENTITY_SLIME_SQUISH;
    }

    protected ParticleEffect getParticles() {
        return ParticleTypes.ITEM_SLIME;
    }

// Mimic movement control //
   static class MimicMoveControl extends MoveControl {
       private float targetYaw;
       private int ticksUntilJump;
       private final MimicEntity mimic;
       private boolean jumpOften;

       public MimicMoveControl(MimicEntity mimic) {
           super(mimic);
           this.mimic = mimic;
           this.targetYaw = 180.0F * mimic.yaw / 3.1415927F;
       }

       public void look(float targetYaw, boolean jumpOften) {
           this.targetYaw = targetYaw;
           this.jumpOften = jumpOften;
       }

       public void move(double speed) {
           this.speed = speed;
           this.state = MoveControl.State.MOVE_TO;
       }

       public void tick() {
           this.entity.yaw = this.wrapDegrees(this.entity.yaw, this.targetYaw, 90.0F);
           this.entity.headYaw = this.entity.yaw;
           this.entity.bodyYaw = this.entity.yaw;
           if (this.state != MoveControl.State.MOVE_TO) {
               this.entity.setForwardSpeed(0.0F);
           } else {
               this.state = MoveControl.State.WAIT;
               if (this.entity.isOnGround()) {
                   this.entity.setMovementSpeed((float)(this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
                   if (this.ticksUntilJump-- <= 0) {
                       this.ticksUntilJump = this.mimic.getTicksUntilNextJump();
                       if (this.jumpOften) {
                           this.ticksUntilJump /= 3;
                       }

                       this.mimic.getJumpControl().setActive();
                       if (this.mimic.makesJumpSound()) {
                           this.mimic.playSound(this.mimic.getJumpSound(), 1, 1);
                       }
                   } else {
                       this.mimic.sidewaysSpeed = 0.0F;
                       this.mimic.forwardSpeed = 0.0F;
                       this.entity.setMovementSpeed(0.0F);
                   }
               } else {
                   this.entity.setMovementSpeed((float)(this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
               }

           }
       }
   }

}
