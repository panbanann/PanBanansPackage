package panbanan.panbananspackage.effect;

import com.google.common.base.Supplier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.awt.*;

public class TestStatusEffect extends StatusEffect {

    public TestStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0x98D982);
    }
   /* @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // In our case, we just make it return true so that it applies the status effect every tick.
        return true;
    }

    // This method is called when it applies the status effect. We implement custom functionality here.
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        Vec3d entityVec = entity.getVelocity();
        double nspeed = entityVec.horizontalLength()*1000;

//        double damageAmount = 2 + speed;
        if (nspeed > 0.0){
            entity.damage(DamageSource.GENERIC, 1 << amplifier);
            System.out.println("speed = " + nspeed);
        }
    }*/
        /*float distanceT = entity.distanceTraveled;
        System.out.println("DistanceT = " + distanceT);
        Vec3d entVel =  entity.getVelocity();
        entVel.
        System.out.println("entVel = " + entVel);*/
        //float cspeed = entity.speed;
        //float hspeed = entity.horizontalSpeed;
        //float mspeed = entity.getMovementSpeed();
        //float fspeed = entity.forwardSpeed;
        //float lspeed = entity.lastLimbDistance;
        //float ch = entity.speed - entity.horizontalSpeed;
        //double e;
        //double d = entity.getX() - entity.prevX;
        //double isMovingparam = d * d + (e = entity.getZ() - entity.prevZ) * e;
        //boolean isMoving = isMovingparam > 2.500000277905201E-7;
        //System.out.println("ismovingparam = " +isMovingparam);
        //System.out.println("cspeed = " + cspeed);
        //System.out.println("hspeed = " + hspeed);
        //System.out.println("mspeed = " + mspeed);
        //System.out.println("lspeed = " + lspeed);
        //if (!(lspeed > -0.10F && lspeed < 0.10F)) {
        //if ((d * d + e * e) > 2.500000277905201E-7){

            //entity.damage(DamageSource.GENERIC, 1 << amplifier);
            //System.out.println("isMoving = " +isMoving);

            //System.out.println("Getx-prevX = " + (entity.getX()-entity.prevX));
            //System.out.println("Entity.getX = "+ entity.prevZ);
            //System.out.println("If statement worked");
        //}


}

