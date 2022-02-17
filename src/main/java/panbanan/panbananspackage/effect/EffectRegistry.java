package panbanan.panbananspackage.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.commons.lang3.builder.StandardToStringStyle;
import panbanan.panbananspackage.PanBanansPackage;

public class EffectRegistry {
    public static StatusEffect TEST_STATUS;

    public static void init(){
        TEST_STATUS = Registry.register(Registry.STATUS_EFFECT, new Identifier(PanBanansPackage.MOD_ID, "test_status"), new TestStatusEffect());
    }


}
