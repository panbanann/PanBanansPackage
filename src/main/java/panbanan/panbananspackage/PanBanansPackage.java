package panbanan.panbananspackage;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import panbanan.panbananspackage.blocks.BlockRegistry;
import panbanan.panbananspackage.config.EntityConfig;
import panbanan.panbananspackage.config.ItemsConfig;
import panbanan.panbananspackage.effect.EffectRegistry;
import panbanan.panbananspackage.entity.EntityRegister;
import panbanan.panbananspackage.items.ItemRegistry;
import software.bernie.geckolib3.GeckoLib;
import java.io.IOException;

public class PanBanansPackage implements ModInitializer {

    public static final String MOD_ID = "panbananspackage";
    private static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static Identifier ID(String path) {
        return new Identifier(MOD_ID, path);
    }
    @Override
    public void onInitialize() {
        try {
            ItemsConfig.configInit();
            EntityConfig.configInit();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EffectRegistry.init();
        ItemRegistry.registerItems();
        GeckoLib.initialize();
        BlockRegistry.blockInit();
        EntityRegister.onInitialize();
    }
    public static void log(Level logLevel, String message) {
        LOGGER.log(logLevel, "[PanBanansPackage]" + message);
    }
}
