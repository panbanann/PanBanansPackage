package panbanan.panbananspackage.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.SummonCommand;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import panbanan.panbananspackage.entity.mobs.MimicEntity;

public class MimicChestBlock extends Block {

    public MimicChestBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        //EntityType<MimicEntity> mobEntity = new MimicEntity();
        //MimicEntity mimicEntity = new MimicEntity();
        if (!world.isClient) {
            player.sendMessage(new LiteralText("Hello, world!"), false);
            player.playSound(SoundEvents.BLOCK_CHEST_OPEN, 1.0F, 0.5F);
            //world.addParticle();
        }


        return ActionResult.SUCCESS;
    }

}
