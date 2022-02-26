package panbanan.panbananspackage.mixin;

import de.dafuqs.lootcrates.LootCrates;
import de.dafuqs.lootcrates.blocks.LootCrateBlock;
import de.dafuqs.lootcrates.blocks.LootCrateBlockEntity;
import de.dafuqs.lootcrates.blocks.chest.ChestLootCrateBlock;
import de.dafuqs.lootcrates.blocks.chest.ChestLootCrateBlockEntity;
import de.dafuqs.lootcrates.enums.LootCrateRarity;
import de.dafuqs.lootcrates.worldgen.LootCrateReplacementEntry;
import de.dafuqs.lootcrates.worldgen.WeightedLootCrateEntryList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import panbanan.panbananspackage.entity.EntityRegister;
import panbanan.panbananspackage.entity.mobs.MimicEntity;

@Mixin(LootCrateBlock.class)
public abstract class LootCrateBlockMixin {

    @Inject(method = "onUse", at = @At("RETURN"), cancellable = true)
    public void onUse (BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir){

        BlockEntity blockEntity = world.getBlockEntity(pos);
        LootCrateBlockEntity lootCrateBlockEntity = (LootCrateBlockEntity)blockEntity;
        if(!lootCrateBlockEntity.isLocked() && lootCrateBlockEntity instanceof ChestLootCrateBlockEntity){
            LootableContainerBlockEntityAccessor tableAccess = ((LootableContainerBlockEntityAccessor) blockEntity);
            Identifier lootTableId = tableAccess.getLootTableId();

            if (lootTableId != null && lootTableId != LootTables.EMPTY) {
                world.playSound(null, pos, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 1.0F, 0.5F);
                AreaEffectCloudEntity poofCloud = new AreaEffectCloudEntity(world, pos.getX(), pos.getY(), pos.getZ());
                poofCloud.setRadius(10.0F);
                poofCloud.setWaitTime(0);
                poofCloud.setParticleType(ParticleTypes.EXPLOSION);
                poofCloud.setDuration(0);
                world.spawnEntity(poofCloud);
                MimicEntity mimicEntity = EntityRegister.MIMIC.create(world);
                mimicEntity.setChestLootTableID(lootTableId);
                // always directly face player when spawning
                mimicEntity.refreshPositionAndAngles(pos, (player.getYaw() + 180) % 360, 180);
                mimicEntity.setTarget(player);
                world.spawnEntity(mimicEntity);
                tableAccess.setLootTableId(LootTables.EMPTY);

                //cir.setReturnValue(ActionResult.CONSUME);
            }

        }
    }
}
