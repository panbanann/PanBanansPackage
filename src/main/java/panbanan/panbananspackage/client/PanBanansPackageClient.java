package panbanan.panbananspackage.client;

import net.fabricmc.api.ClientModInitializer;
import panbanan.panbananspackage.entity.EntityRegister;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class PanBanansPackageClient implements ClientModInitializer {

    @Override
    public void onInitializeClient(){

        RenderRegister.onInitialize();
    }
}
