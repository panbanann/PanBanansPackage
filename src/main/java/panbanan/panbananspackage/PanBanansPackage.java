package panbanan.panbananspackage;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import panbanan.panbananspackage.config.ItemsConfig;
import panbanan.panbananspackage.entity.EntityRegister;
import panbanan.panbananspackage.items.ItemRegistry;
import software.bernie.geckolib3.GeckoLib;

import java.io.IOException;
import java.util.IdentityHashMap;

public class PanBanansPackage implements ModInitializer {

    public  static final String MOD_ID = "panbananspackage";

    public static Identifier ID(String path) {
        return new Identifier(MOD_ID, path);
    }
    @Override
    public void onInitialize() {
        try {
            ItemsConfig.configInit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ItemRegistry.registerItems();
        GeckoLib.initialize();
        EntityRegister.onInitialize();
    }
}
