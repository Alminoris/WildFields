package net.alminoris.wildfields.block.custom;

import net.alminoris.wildfields.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class PricklyPearCactusBlock extends SweetBerryBushBlock
{
    public PricklyPearCactusBlock()
    {
        super(Settings.copy(Blocks.SWEET_BERRY_BUSH));
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, Integer.valueOf(0)));
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state)
    {
        return new ItemStack(ModItems.PRICKLY_PEAR);
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos)
    {
        return floor.isOf(Blocks.SAND);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        int i = (Integer)state.get(AGE);
        boolean bl = i == 3;
        if (i > 1)
        {
            int j = 1 + world.random.nextInt(2);
            dropStack(world, pos, new ItemStack(ModItems.PRICKLY_PEAR, j + (bl ? 1 : 0)));
            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES,
                    SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            BlockState blockState = state.with(AGE, Integer.valueOf(1));
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
            return ActionResult.success(world.isClient);
        }
        else
        {
            return super.onUse(state, world, pos, player, hit);
        }
    }
}