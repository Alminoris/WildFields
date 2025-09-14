package net.alminoris.wildfields.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TripleTallPlantBlock extends Block {
    public enum PlantPart implements StringIdentifiable {
        LOWER, MIDDLE, UPPER;

        @Override
        public String asString() {
            return name().toLowerCase();
        }
    }

    public static final EnumProperty<PlantPart> PART = EnumProperty.of("part", PlantPart.class);

    public TripleTallPlantBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(PART, PlantPart.LOWER));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PART);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (world.isClient) return;

        if (state.get(PART) == PlantPart.LOWER) {
            BlockPos middlePos = pos.up();
            BlockPos upperPos = pos.up(2);

            // Check space
            boolean middleFree = world.getBlockState(middlePos).isReplaceable();
            boolean upperFree = world.getBlockState(upperPos).isReplaceable();

            if (middleFree && upperFree) {
                world.setBlockState(middlePos, getDefaultState().with(PART, PlantPart.MIDDLE), Block.NOTIFY_ALL);
                world.setBlockState(upperPos, getDefaultState().with(PART, PlantPart.UPPER), Block.NOTIFY_ALL);
            } else {
                // Not enough space → remove the lower
                world.breakBlock(pos, false);
            }
        }

        super.onBlockAdded(state, world, pos, oldState, notify);
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        PlantPart part = state.get(PART);
        BlockPos basePos = switch (part) {
            case LOWER -> pos;
            case MIDDLE -> pos.down();
            case UPPER -> pos.down(2);
        };

        for (int i = 0; i < 3; i++) {
            BlockPos target = basePos.up(i);
            BlockState targetState = world.getBlockState(target);
            if (targetState.getBlock() == this) {
                world.breakBlock(target, !player.isCreative());
            }
        }

        return super.onBreak(world, pos, state, player);
    }
}
