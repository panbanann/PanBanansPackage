package panbanan.panbananspackage.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import org.spongepowered.asm.mixin.Mixin;

import java.util.UUID;

//TODO remove this if not doing anything with it.
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    /*@Shadow
    @Final
    private static TrackedData<Boolean> POTION_SWIRLS_AMBIENT;

    @Inject(method = "tickStatusEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"), cancellable = true)
    protected void tickStatusEffects(CallbackInfo ci) {
        LivingEntity living = (LivingEntity) (Object) this;
        DataTracker dataTracker = living.getDataTracker();

        if(living.hasStatusEffect(EffectRegistry.TEST_STATUS)) {
            living.world.addParticle(dataTracker.get(POTION_SWIRLS_AMBIENT) ? ParticleTypes.DRIPPING_LAVA : ParticleTypes.DRIPPING_LAVA, living.getParticleX(0.5D), living.getRandomBodyY(), living.getParticleZ(0.5D), 0, 0, 0);
            ci.cancel();
        }
    }*/
}
