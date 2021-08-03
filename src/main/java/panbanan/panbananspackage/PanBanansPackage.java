package panbanan.panbananspackage;

import net.fabricmc.api.ModInitializer;
import panbanan.panbananspackage.entity.EntityRegister;
import panbanan.panbananspackage.items.ItemRegistry;
import software.bernie.geckolib3.GeckoLib;

public class PanBanansPackage implements ModInitializer {

    public  static final String MOD_ID = "panbananspackage";
    @Override
    public void onInitialize() {
        ItemRegistry.registerItems();
        GeckoLib.initialize();
        EntityRegister.onInitialize();
    }
}
