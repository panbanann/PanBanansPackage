package panbanan.panbananspackage.entity.mobs;

import net.minecraft.block.ChestBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.PounceAtTargetGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import panbanan.panbananspackage.mixin.ChestBlockMixin;
import panbanan.panbananspackage.mixin.LootableContainerBlockEntityAccessor;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;
//TODO change the entity type or remove randomness of health and size

public class MimicEntity extends MobEntity implements Monster, IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);


    public MimicEntity(EntityType<? extends MimicEntity> entityType, World worldIn){
        super((EntityType<? extends MobEntity>)entityType, worldIn);
        this.moveControl = new MimicEntity.MimicMoveControl(this);
        this.ignoreCameraFrustum = true;
    }
// Animation control //
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.mimic.moving", true));
            return PlayState.CONTINUE;
        }

        //TODO dashing animation and ability

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

    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new MimicEntity.FaceTowardTargetGoal(this));
        this.goalSelector.add(3, new MimicEntity.MoveGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<PlayerEntity>(this, PlayerEntity.class, 10, true, false, livingEntity -> Math.abs(livingEntity.getY() - this.getY()) <= 4.0));
        this.targetSelector.add(3, new ActiveTargetGoal<IronGolemEntity>((MobEntity)this, IronGolemEntity.class, true));
        this.goalSelector.add(3, new PounceAtTargetGoal(this, 0.4f));
        super.initGoals();
    }

    //TODO adding spawn rule to always face player on spawn.

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
            this.remove(RemovalReason.KILLED);
        }
    }

    protected boolean canAttack() {
        return true;
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        this.damage(player);
    }
    protected float getDamageAmount() {
        return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }

    private void damage(LivingEntity target) {
        if (this.isAlive()) {
            if (target.damage(DamageSource.mob(this), this.getDamageAmount())) {
                this.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0f, 0.2f);
                this.applyDamageEffects(this, target);
            }
        }
    }

    protected boolean makesJumpSound() {
       return true;
   }

    protected int getTicksUntilNextJump() {
        return this.random.nextInt(20) + 10;
    }

    /*public Identifier getChestLoot(Identifier id) {
        return id;
    }
    @Override
    protected Identifier getLootTableId(){
        Identifier id2 = ((id2) new getChestLoot());

        return getChestLoot(id2);
    }*/

    // Mimic goals //
    static class MoveGoal extends Goal {
        private final MimicEntity mimic;

        public MoveGoal(MimicEntity mimic) {
            this.mimic = mimic;
            this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
        }
        @Override
        public boolean canStart() {
            return !this.mimic.hasVehicle();
        }

        @Override
        public void tick() {
            ((MimicEntity.MimicMoveControl)this.mimic.getMoveControl()).move(1.0D);
        }
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
                return (!(livingEntity instanceof PlayerEntity) || !((PlayerEntity) livingEntity).getAbilities().invulnerable) && this.mimic.getMoveControl() instanceof MimicMoveControl;
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
            } else if (livingEntity instanceof PlayerEntity && ((PlayerEntity)livingEntity).getAbilities().invulnerable) {
                return false;
            } else {
                return --this.ticksLeft > 0;
            }
        }

        public void tick() {
            this.mimic.lookAtEntity(this.mimic.getTarget(), 10.0F, 10.0F);
            ((MimicEntity.MimicMoveControl)this.mimic.getMoveControl()).look(this.mimic.getYaw(), this.mimic.canAttack());
        }
    }

// Mimic Media //
    //TODO change the sound effect for more woodlike instead of slime.
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



// Mimic movement control //
    //TODO learn how to make the movement correctly without using slimeEntity as extend.
   static class MimicMoveControl extends MoveControl {
       private float targetYaw;
       private int ticksUntilJump;
       private final MimicEntity mimic;
       private boolean jumpOften;
       protected ParticleEffect getParticles() {return ParticleTypes.ITEM_SLIME;}
       private World world = this.entity.getWorld();



       public MimicMoveControl(MimicEntity mimic) {
           super(mimic);
           this.mimic = mimic;
           this.targetYaw = 180.0F * mimic.getYaw() / 3.1415927F;
       }

       public void look(float targetYaw, boolean jumpOften) {
           this.targetYaw = targetYaw;
           this.jumpOften = jumpOften;
       }

       public void move(double speed) {
           this.speed = speed;
           this.state = MoveControl.State.MOVE_TO;
       }

       @Override
       public void tick() {
           this.entity.setYaw(this.wrapDegrees(this.entity.getYaw(), this.targetYaw, 90.0F));
           this.entity.headYaw = this.entity.getYaw();
           this.entity.bodyYaw = this.entity.getYaw();

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
                           //This doesn't work
                           world.addParticle(this.getParticles(), mimic.getX(), mimic.getY(), mimic.getZ(), 0.0, 0.0, 0.0);
                       }
                   } else {
                       this.mimic.sidewaysSpeed = 0.0F;
                       this.mimic.forwardSpeed = 0.0F;
                       this.entity.setMovementSpeed(0.0F);
                   }
               }
               else {
                   this.entity.setMovementSpeed((float)(this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
               }

           }
       }
   }

}
