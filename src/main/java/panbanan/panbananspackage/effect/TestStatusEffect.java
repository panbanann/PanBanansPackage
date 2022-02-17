package panbanan.panbananspackage.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.awt.*;

public class TestStatusEffect extends StatusEffect {

    public TestStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0x98D982);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // In our case, we just make it return true so that it applies the status effect every tick.
        return true;
    }

    // This method is called when it applies the status effect. We implement custom functionality here.
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        float cspeed = entity.speed;
        float hspeed = entity.horizontalSpeed;
        float mspeed = entity.getMovementSpeed();
        float fspeed = entity.forwardSpeed;
        //System.out.println("cspeed = " + cspeed);
        //System.out.println("hspeed = " + hspeed);
        //System.out.println("mspeed = " + mspeed);
        //System.out.println("fspeed = " + fspeed);
        /*if (cspeed > 0.05f) {
            entity.damage(DamageSource.GENERIC, 1 << amplifier);
        }*/
    }
}

