package panbanan.panbananspackage.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.SummonCommand;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.MobSpawnerLogic;
import net.minecraft.world.World;
import net.minecraft.world.gen.Spawner;
import panbanan.panbananspackage.PanBanansPackage;
import panbanan.panbananspackage.entity.EntityIDs;
import panbanan.panbananspackage.entity.EntityRegister;
import panbanan.panbananspackage.entity.mobs.MimicEntity;

public class MimicChestBlock extends Block {

    //private Object MobEntity;


    public MimicChestBlock(FabricBlockSettings settings) {
        super(settings);
    }
    //public BlockPos positionX;
    //public BlockPos positionY;
    //public BlockPos positionZ;

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        /*NbtCompound nbtCompound = nbt.copy();
        nbtCompound.putString("id", entity.toString());
        ServerWorld serverWorld = source.getWorld();
        Entity entity2 = EntityType.loadEntityWithPassengers(nbtCompound, serverWorld, (entityx) -> {
            entityx.refreshPositionAndAngles(pos.x, pos.y, pos.z, entityx.yaw, entityx.pitch);
            return entityx;
        });*/
        //mimicEntity = new MimicEntity();
        //MimicEntity mimicEntity = MimicEntity.create(world);

        if (!world.isClient) {
            player.sendMessage(new LiteralText("Hello, world!"), false);
            player.playSound(SoundEvents.BLOCK_CHEST_OPEN, 1.0F, 0.5F);
            MimicEntity mimicEntity = EntityRegister.MIMIC.create(world);
            //positionX = pos.getX();
            world.spawnEntity(mimicEntity);
            //;
        }
        return ActionResult.SUCCESS;
    }

}
