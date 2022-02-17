package panbanan.panbananspackage.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.apache.commons.compress.archivers.ar.ArArchiveEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import panbanan.panbananspackage.entity.EntityRegister;
import panbanan.panbananspackage.entity.mobs.MimicEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mixin(ChestBlock.class)
public abstract class ChestBlockMixin {
    
    // value from 0.0 - 1.0
    private double chanceForChestToTurnIntoMimic = 1.0;
    
    // a list of all block identifiers that should not be able to turn into mimics when used
    // this list should be added to the config, so modpack makers are able to expand it
    // maybe even make it a whitelist instead
    private List<Identifier> mimicSafeBlocks = new ArrayList<>() {{
        add(Identifier.tryParse("lootcrates:common_chest_loot_crate"));
        add(Identifier.tryParse("lootcrates:common_shulker_loot_crate"));
        add(Identifier.tryParse("lootcrates:common_loot_barrel"));
        add(Identifier.tryParse("lootcrates:uncommon_chest_loot_crate"));
        add(Identifier.tryParse("lootcrates:uncommon_shulker_loot_crate"));
        add(Identifier.tryParse("lootcrates:uncommon_loot_barrel"));
        add(Identifier.tryParse("lootcrates:rare_chest_loot_crate"));
        add(Identifier.tryParse("lootcrates:rare_shulker_loot_crate"));
        add(Identifier.tryParse("lootcrates:rare_loot_barrel"));
        add(Identifier.tryParse("lootcrates:epic_chest_loot_crate"));
        add(Identifier.tryParse("lootcrates:epic_shulker_loot_crate"));
        add(Identifier.tryParse("lootcrates:epic_loot_barrel"));
        add(Identifier.tryParse("lootcrates:ghost_chest_loot_crate"));
        add(Identifier.tryParse("lootcrates:ghost_shulker_loot_crate"));
        add(Identifier.tryParse("lootcrates:ghost_loot_barrel"));
        add(Identifier.tryParse("lootcrates:blaze_chest_loot_crate"));
        add(Identifier.tryParse("lootcrates:blaze_shulker_loot_crate"));
        add(Identifier.tryParse("lootcrates:blaze_loot_barrel"));
    }};
    
    //TODO prevent destroying chests when the lootcrates is installed.
    //TODO save the loot table information and give loot on kill plus extra
    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (!world.isClient) {
            // test if the block used is not contained in the blacklist
            // if yes roll a random number and check if it is lower than the value set in chanceForChestToTurnIntoMimic
            Identifier blockIdentifier = Registry.BLOCK.getId(state.getBlock());
            if (!mimicSafeBlocks.contains(blockIdentifier) && world.getRandom().nextDouble() < chanceForChestToTurnIntoMimic) {
                // since we cannot be 100 % sure that each chest block uses a LootableContainerBlockEntity
                // better check if it is true here (modded ones). This also acts as a failsafe if the chests
                // BlockEntity vanished for some reason
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity instanceof LootableContainerBlockEntity lootableContainerBlockEntity) {
                    LootableContainerBlockEntityAccessor tableAccess = ((LootableContainerBlockEntityAccessor) lootableContainerBlockEntity);
                    Identifier lootTableId = tableAccess.getLootTableId();
                    // do nothing if the loot table is empty
                    if (lootTableId != null && lootTableId != LootTables.EMPTY) {
                    
                        // using world.playSound makes it so everyone can hear it, not just the player that opened the chest itself
                        world.playSound(null, pos, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 1.0F, 0.5F);
                
                        AreaEffectCloudEntity poofCloud = new AreaEffectCloudEntity(world, pos.getX(), pos.getY(), pos.getZ());
                        poofCloud.setRadius(10.0F);
                        poofCloud.setWaitTime(0);
                        poofCloud.setParticleType(ParticleTypes.EXPLOSION);
                        poofCloud.setDuration(0);
                        world.spawnEntity(poofCloud);
                
                        MimicEntity mimicEntity = EntityRegister.MIMIC.create(world);
                        mimicEntity.lookAtEntity(player, 0, 0);
                        mimicEntity.setChestLootTableID(lootTableId);
                
                        // always directly face player when spawning
                        mimicEntity.refreshPositionAndAngles(pos, (player.getYaw() + 180) % 360, 180);
                        mimicEntity.setTarget(player);
                        world.spawnEntity(mimicEntity);
                        
                        // since the mimic has the loot table now remove it from the chest
                        // else it would drop that loot when broken by the next line
                        tableAccess.setLootTableId(LootTables.EMPTY);
                        // when the chest is removed normally it drops its loot
                        // that can be skipped by passing SKIP_DROPS as Flag
                        // this skips the drop of the Chest Item
                        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3 | Block.SKIP_DROPS);
                        
                        // cancel all the code that would run after turning it into the mimic
                        cir.setReturnValue(ActionResult.CONSUME);
                    }
                }
            }
        }
    }
}
