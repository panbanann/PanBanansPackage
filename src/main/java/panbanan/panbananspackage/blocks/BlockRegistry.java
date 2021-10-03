package panbanan.panbananspackage.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import panbanan.panbananspackage.entity.mobs.MimicEntity;

public class BlockRegistry {
    public static final Block M_CHEST_BLOCK = new MimicChestBlock(FabricBlockSettings.of(Material.WOOD).strength(5.0f));

    public static void blockInit(){
        Registry.register(Registry.BLOCK, new Identifier("panbananspackage", "m_chest_block"), M_CHEST_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("panbananspackage", "m_chest_block"), new BlockItem(M_CHEST_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));

    }
}
