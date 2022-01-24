package panbanan.panbananspackage.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import panbanan.panbananspackage.entity.EntityRegister;
import panbanan.panbananspackage.entity.mobs.MimicEntity;

import java.util.Random;
//TODO try to extend chest and make the block render as regular chest instead of model

public class MimicChestBlock extends Block {

    //public static final BooleanProperty DOFACE = BooleanProperty.of("spawnable");
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public MimicChestBlock(FabricBlockSettings settings) {
        super(settings);
        //setDefaultState(getStateManager().getDefaultState().with(SPAWNABLE, false));
        setDefaultState(getStateManager().getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
        //this.setDefaultState((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH);
    }
   @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
    }

    //TODO Add world generation similar to YungsCaves.

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        AreaEffectCloudEntity poofCloud = new AreaEffectCloudEntity(world, pos.getX(), pos.getY(), pos.getZ());
        player.playSound(SoundEvents.BLOCK_CHEST_OPEN, 1.0F, 0.5F);
        poofCloud.setRadius(2.0F);
        poofCloud.setWaitTime(0);
        poofCloud.setParticleType(ParticleTypes.EXPLOSION);
        poofCloud.setDuration(0);
        world.spawnEntity(poofCloud);
        //world.addParticle(ParticleTypes.POOF, true, pos.getX() + getRandOffset(), pos.getY() + getRandOffset(), pos.getZ() + getRandOffset(), 0.0, 0.0, 0.0);

        if (!world.isClient) {
            MimicEntity mimicEntity = EntityRegister.MIMIC.create(world);
            mimicEntity.refreshPositionAndAngles(pos, 0, 180);
            mimicEntity.setTarget(player);
            world.spawnEntity(mimicEntity);
            world.removeBlock(pos, false);
        }

        return ActionResult.SUCCESS;
    }

    private double getRandOffset() {
        return new Random().nextDouble() * 5 / 2;
    }



    /*if (!world.isClient) {
            player.sendMessage(new LiteralText("Hello, world!"), false);
            MimicEntity mimicEntity = EntityRegister.MIMIC.create(world);
            mimicEntity.setPos(pos.getX(), pos.getY()+1, pos.getZ());
            world.spawnEntity(mimicEntity);
            mimicEntity.checkDespawn();
        }*/
}
