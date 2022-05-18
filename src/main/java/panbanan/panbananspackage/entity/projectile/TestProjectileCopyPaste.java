package panbanan.panbananspackage.entity.projectile;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import panbanan.panbananspackage.entity.EntityRegister;
//add your own listener for packets
import software.bernie.example.ClientListener;
import software.bernie.example.entity.RocketProjectile;
import software.bernie.example.registry.EntityRegistry;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Arrays;
import java.util.List;

public class TestProjectileCopyPaste extends PersistentProjectileEntity implements IAnimatable {
    protected int timeInAir;
    protected boolean inAir;
    private int ticksInAir;
    private LivingEntity shooter;
    private double damage = 2.0;
    private SoundEvent sound = this.getHitSound();

    private AnimationFactory factory = new AnimationFactory(this);

    public TestProjectileCopyPaste(EntityType<? extends TestProjectileCopyPaste> entityType, World world) {
        super(entityType, world);
        this.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
    }

    public TestProjectileCopyPaste(World world, LivingEntity owner) {
        super(EntityRegister.TEST_PROJECTILE, owner, world);
        this.shooter = owner;
    }

    protected TestProjectileCopyPaste(EntityType<? extends TestProjectileCopyPaste> type, double x, double y, double z, World world) {
        this(type, world);
    }

    protected TestProjectileCopyPaste(EntityType<? extends TestProjectileCopyPaste> type, LivingEntity owner, World world) {
        this(type, owner.getX(), owner.getEyeY() - 0.10000000149011612D, owner.getZ(), world);
        this.setOwner(owner);
        if (owner instanceof PlayerEntity) {
            this.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
        }

    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.ball_projectile.spin", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<TestProjectileCopyPaste>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return ClientListener.EntityPacket.createPacket(this);
    }

    @Override
    protected void age() {
        ++this.ticksInAir;
        if (this.ticksInAir >= 40) {
            this.remove(Entity.RemovalReason.DISCARDED);
        }
    }

    @Override
    protected void onHit(LivingEntity living) {
        super.onHit(living);
        if (!(living instanceof PlayerEntity)) {
            living.setVelocity(0, 0, 0);
            living.timeUntilRegen = 0;
        }
    }

    @Override
    public void setVelocity(double x, double y, double z, float speed, float divergence) {
        super.setVelocity(x, y, z, speed, divergence);
        this.ticksInAir = 0;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound tag) {
        super.writeCustomDataToNbt(tag);
        tag.putShort("life", (short) this.ticksInAir);
        tag.putDouble("damage", this.damage);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound tag) {
        super.readCustomDataFromNbt(tag);
        this.ticksInAir = tag.getShort("life");
        if (tag.contains("damage", 99)) {
            this.damage = tag.getDouble("damage");
        }
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDamage() {
        return this.damage;
    }

    @Override
    public void tick() {
        super.tick();
        boolean bl = this.isNoClip();
        Vec3d vec3d = this.getVelocity();
        if (this.prevPitch == 0.0F && this.prevYaw == 0.0F) {
            double f = vec3d.horizontalLength();
            this.setYaw((float) (MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875D));
            this.setPitch((float) (MathHelper.atan2(vec3d.y, (double) f) * 57.2957763671875D));
            this.prevYaw = this.getYaw();
            this.prevPitch = this.getPitch();
        }
        if (this.age >= 100) {
            this.remove(Entity.RemovalReason.DISCARDED);
        }
        if (this.inAir && !bl) {
            this.age();
            ++this.timeInAir;
        } else {
            this.timeInAir = 0;
            Vec3d vec3d3 = this.getPos();
            Vec3d vector3d3 = vec3d3.add(vec3d);
            HitResult hitResult = this.world.raycast(new RaycastContext(vec3d3, vector3d3,
                    RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, this));
            if (((HitResult) hitResult).getType() != HitResult.Type.MISS) {
                vector3d3 = ((HitResult) hitResult).getPos();
            }
            while (!this.isRemoved()) {
                EntityHitResult entityHitResult = this.getEntityCollision(vec3d3, vector3d3);
                if (entityHitResult != null) {
                    hitResult = entityHitResult;
                }
                if (hitResult != null && ((HitResult) hitResult).getType() == HitResult.Type.ENTITY) {
                    Entity entity = ((EntityHitResult) hitResult).getEntity();
                    Entity entity2 = this.getOwner();
                    if (entity instanceof PlayerEntity && entity2 instanceof PlayerEntity
                            && !((PlayerEntity) entity2).shouldDamagePlayer((PlayerEntity) entity)) {
                        hitResult = null;
                        entityHitResult = null;
                    }
                }
                if (hitResult != null && !bl) {
                    this.onCollision((HitResult) hitResult);
                    this.velocityDirty = true;
                }
                if (entityHitResult == null || this.getPierceLevel() <= 0) {
                    break;
                }
                hitResult = null;
            }
            vec3d = this.getVelocity();
            double d = vec3d.x;
            double e = vec3d.y;
            double g = vec3d.z;
            double h = this.getX() + d;
            double j = this.getY() + e;
            double k = this.getZ() + g;
            double l = vec3d.horizontalLength();
            if (bl) {
                this.setYaw((float) (MathHelper.atan2(-d, -g) * 57.2957763671875D));
            } else {
                this.setYaw((float) (MathHelper.atan2(d, g) * 57.2957763671875D));
            }
            this.setPitch((float) (MathHelper.atan2(e, (double) l) * 57.2957763671875D));
            this.setPitch(updateRotation(this.prevPitch, this.getPitch()));
            this.setPitch(updateRotation(this.prevYaw, this.getYaw()));
            float m = 0.99F;

            this.setVelocity(vec3d.multiply((double) m));
            if (!this.hasNoGravity() && !bl) {
                Vec3d vec3d5 = this.getVelocity();
                this.setVelocity(vec3d5.x, vec3d5.y - 0.05000000074505806D, vec3d5.z);
            }
            this.updatePosition(h, j, k);
            this.checkBlockCollision();
        }
    }

    public void initFromStack(ItemStack stack) {
        if (stack.getItem() == Items.TNT) {
        }
    }

    @Override
    public boolean hasNoGravity() {
        if (this.isSubmergedInWater()) {
            return false;
        } else {
            return true;
        }
    }

    public SoundEvent hitSound = this.getHitSound();

    @Override
    public void setSound(SoundEvent soundIn) {
        this.hitSound = soundIn;
    }

    @Override
    protected SoundEvent getHitSound() {
        return SoundEvents.ENTITY_ARROW_HIT;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (!this.world.isClient) {
            //this.doDamage();
            this.remove(Entity.RemovalReason.DISCARDED);
        }
        this.setSound(SoundEvents.ENTITY_GENERIC_EXPLODE);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {

        DamageSource l;
        Entity entity2;
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        float f = (float)this.getVelocity().length();
        int i = MathHelper.ceil(MathHelper.clamp((double)f * this.damage, 0.0, 2.147483647E9));

        if (this.isCritical()) {
            long l2 = this.random.nextInt(i / 2 + 2);
            i = (int)Math.min(l2 + (long)i, Integer.MAX_VALUE);
        }
        if ((entity2 = this.getOwner()) == null) {
            l = DamageSource.arrow(this, this);
        } else {
            l = DamageSource.arrow(this, entity2);
            if (entity2 instanceof LivingEntity) {
                ((LivingEntity)entity2).onAttacking(entity);
            }
        }
        boolean bl = entity.getType() == EntityType.ENDERMAN;
        int j = entity.getFireTicks();
        if (this.isOnFire() && !bl) {
            entity.setOnFireFor(5);
        }
        if (entity.damage(l, i)) {
            if (bl) {
                return;
            }
            this.playSound(this.sound, 1.0f, 1.2f / (this.random.nextFloat() * 0.2f + 0.9f));
        } else {
            entity.setFireTicks(j);
            this.setVelocity(this.getVelocity().multiply(-0.1));
            this.setYaw(this.getYaw() + 180.0f);
            this.prevYaw += 180.0f;
            if (!this.world.isClient && this.getVelocity().lengthSquared() < 1.0E-7) {
                if (this.pickupType == PickupPermission.ALLOWED) {
                    this.dropStack(this.asItemStack(), 0.1f);
                }
                this.discard();
            }
        }

        /*Entity entity = entityHitResult.getEntity();
        if (!this.world.isClient) {
            entity.damage(DamageSource.arrow(this, this.getOwner()), 10);
            this.remove(Entity.RemovalReason.DISCARDED);
        }*/
    }

    @Override
    public ItemStack asItemStack() {
        return new ItemStack(Items.TNT);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean shouldRender(double distance) {
        return true;
    }

    /*public void doDamage() {
        float q = 4.0F;
        int k = MathHelper.floor(this.getX() - (double) q - 1.0D);
        int l = MathHelper.floor(this.getX() + (double) q + 1.0D);
        int t = MathHelper.floor(this.getY() - (double) q - 1.0D);
        int u = MathHelper.floor(this.getY() + (double) q + 1.0D);
        int v = MathHelper.floor(this.getZ() - (double) q - 1.0D);
        int w = MathHelper.floor(this.getZ() + (double) q + 1.0D);
        List<Entity> list = this.world.getOtherEntities(this,
                new Box((double) k, (double) t, (double) v, (double) l, (double) u, (double) w));
        Vec3d vec3d = new Vec3d(this.getX(), this.getY(), this.getZ());
        for (int x = 0; x < list.size(); ++x) {
            Entity entity = (Entity) list.get(x);
            double y = (double) (MathHelper.sqrt((float) entity.squaredDistanceTo(vec3d)) / q);
            if (y <= 1.0D) {
                if (entity instanceof LivingEntity) {
                    //entity.damage(DamageSource.player((PlayerEntity) this.shooter), 10);
                    entity.damage(DamageSource.arrow(this, this.getOwner()), 10);
                    //entity.damage(DamageSource.GENERIC.setProjectile(), 5);
                }
            }
        }
    }*/

}