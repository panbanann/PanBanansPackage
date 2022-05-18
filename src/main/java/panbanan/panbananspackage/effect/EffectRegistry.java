package panbanan.panbananspackage.effect;

import jdk.jshell.Snippet;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.DamageModifierStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.commons.lang3.builder.StandardToStringStyle;
import panbanan.panbananspackage.PanBanansPackage;

public class EffectRegistry {
    public static StatusEffect ARMOR_BREAK;
    public static StatusEffect CRIPLING_VOID;

    public static void init() {
        ARMOR_BREAK = Registry.register(Registry.STATUS_EFFECT, new Identifier(PanBanansPackage.MOD_ID, "armor_break"), new ModifyStatusEffect(StatusEffectCategory.HARMFUL, 0x484D48, -20.0).addAttributeModifier(EntityAttributes.GENERIC_ARMOR, "666668D-DA3E-4C1C-8813-96EA60972ABC", 0.0, EntityAttributeModifier.Operation.ADDITION));
        CRIPLING_VOID = Registry.register(Registry.STATUS_EFFECT, new Identifier(PanBanansPackage.MOD_ID, "cripling_void"), new CriplingVoidEffect());



    }


}
