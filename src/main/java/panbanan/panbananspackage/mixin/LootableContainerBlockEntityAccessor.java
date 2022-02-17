package panbanan.panbananspackage.mixin;

import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.loot.LootTable;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LootableContainerBlockEntity.class)
public interface LootableContainerBlockEntityAccessor {

    @Accessor
    Identifier getLootTableId();
	
	@Accessor
	void setLootTableId(Identifier identifier);
	
	// TODO: Use those, too
	@Accessor
	long getLootTableSeed();
	
	@Accessor
	void setLootTableSeed(long seed);
	
	
}
