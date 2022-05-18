package panbanan.panbananspackage.effect;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.effect.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import panbanan.panbananspackage.mixin.LivingEntityAccessor;

import java.util.UUID;

public class CriplingVoidEffect extends StatusEffect {

    public CriplingVoidEffect() {
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
        Camera camera = new Camera();
        //boolean wasJumping = false;
        LivingEntityAccessor isJumping = ((LivingEntityAccessor) entity);
        //camera.getProjection();
        //ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity)entity;
        double prevVertPos = entity.prevY;
        double verticalPos = entity.getBlockY();
        float fogStart = 0.0f;
        if (isJumping.getJumping()){
            System.out.println("The entity: " + entity + " is jumping.");
            amplifier +=1;
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 30, amplifier, false, true));
        } else {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 30, amplifier, false, true));
        }
        if (entity instanceof ClientPlayerEntity){

            //Entity entity1 = camera.getFocusedEntity();
            //ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity)entity;

            /*int biome = entity.getStatusEffect(this).getDuration();
            float g = MathHelper.lerp(Math.min(1.0f, (float)biome / 20.0f), 8, 5.0f);*/


            float f = 0.0f;
            float h = 0.8f;
            f = 0.0f;
            h = 1.0f;
            BackgroundRenderer.setFogBlack();
            //RenderSystem.setShaderFogColor(0.0f, 0.0f, 0.0f, 0.0f);
            BackgroundRenderer.applyFog(camera, BackgroundRenderer.FogType.FOG_SKY, 1.0f, true);
            //RenderSystem.setShaderFogStart(f);
            //RenderSystem.setShaderFogEnd(h);
            //System.out.println("Fog start on: " + RenderSystem.getShaderFogStart());
        }
        //entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 30, amplifier, false, true));

    }
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier){

    }

    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier){
        //RenderSystem.clearColor();
    }
    /*
    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier){
        double baseArmor = entity.getArmor();
        System.out.println("the armor for " + entity + " is: " + baseArmor);
        EntityAttributeInstance attributeInstance = entity.getAttributeInstance(EntityAttributes.GENERIC_ARMOR);

        if (attributeInstance.getModifier(ARMOR_BREAK_ID) != null) {
            attributeInstance.removeModifier(ARMOR_BREAK);
        }
        attributeInstance.addTemporaryModifier(ARMOR_BREAK);
        //EntityAttributeModifier armorbreak = new EntityAttributeModifier("armor_break", 0.3, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        System.out.println("Armor after apply is: " + entity.getArmor());
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier){
        super.onRemoved(entity, attributes, amplifier);
        EntityAttributeInstance attributeInstance = entity.getAttributeInstance(EntityAttributes.GENERIC_ARMOR);
        attributeInstance.removeModifier(ARMOR_BREAK);
    }
public void setSprinting(boolean sprinting) {
        super.setSprinting(sprinting);
        EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (entityAttributeInstance.getModifier(SPRINTING_SPEED_BOOST_ID) != null) {
            entityAttributeInstance.removeModifier(SPRINTING_SPEED_BOOST);
        }
        if (sprinting) {
            entityAttributeInstance.addTemporaryModifier(SPRINTING_SPEED_BOOST);
        }
    }

    public static void armorBreak(LivingEntity target){
        int armorValue = target.getArmor();

    }
float distanceT = entity.distanceTraveled;
        System.out.println("DistanceT = " + distanceT);
        Vec3d entVel =  entity.getVelocity();
        entVel.
        System.out.println("entVel = " + entVel);

        float cspeed = entity.speed;
        float hspeed = entity.horizontalSpeed;
        float mspeed = entity.getMovementSpeed();
        float fspeed = entity.forwardSpeed;
        float lspeed = entity.lastLimbDistance;
        float ch = entity.speed - entity.horizontalSpeed;
        double e;
        double d = entity.getX() - entity.prevX;
        double isMovingparam = d * d + (e = entity.getZ() - entity.prevZ) * e;
        boolean isMoving = isMovingparam > 2.500000277905201E-7;
        System.out.println("ismovingparam = " +isMovingparam);
        System.out.println("cspeed = " + cspeed);
        System.out.println("hspeed = " + hspeed);
        System.out.println("mspeed = " + mspeed);
        System.out.println("lspeed = " + lspeed);
        if (!(lspeed > -0.10F && lspeed < 0.10F)) {
        if ((d * d + e * e) > 2.500000277905201E-7){

          entity.damage(DamageSource.GENERIC, 1 << amplifier);
           System.out.println("isMoving = " +isMoving);

            System.out.println("Getx-prevX = " + (entity.getX()-entity.prevX));
            System.out.println("Entity.getX = "+ entity.prevZ);
            System.out.println("If statement worked");
        }
*/


}

