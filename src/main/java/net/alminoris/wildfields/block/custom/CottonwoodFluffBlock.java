package net.alminoris.wildfields.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class CottonwoodFluffBlock extends PlantBlock implements Fertilizable, Waterloggable {
    public static final MapCodec<CottonwoodFluffBlock> CODEC = createCodec(CottonwoodFluffBlock::new);

    public static final IntProperty AGE = IntProperty.of("age", 0, 4);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private static final VoxelShape[] SHAPES = new VoxelShape[]
    {
            Block.createCuboidShape(7.0, 15.0, 7.0, 8.0, 16.0, 8.0),
            Block.createCuboidShape(7.0, 14.0, 7.0, 9.0, 16.0, 9.0),
            Block.createCuboidShape(6.0, 13.0, 6.0, 10.0, 16.0, 10.0),
            Block.createCuboidShape(5.0, 10.0, 5.0, 10.0, 16.0, 10.0),
            Block.createCuboidShape(3.0, 10.0, 3.0, 13.0, 16.0, 13.0)
    };

    public CottonwoodFluffBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(AGE, 0)
                .with(WATERLOGGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE, WATERLOGGED);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(8) == 0) {
            int age = state.get(AGE);
            if (age < 4) {
                world.setBlockState(pos, state.with(AGE, age + 1), Block.NOTIFY_LISTENERS);
            }
        }
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return state.get(AGE) < 4 && canPlaceAt(state, world, pos);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return state.get(AGE) < 4;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int age = state.get(AGE);
        if (age < 4) {
            world.setBlockState(pos, state.with(AGE, age + 1), Block.NOTIFY_LISTENERS);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int age = state.get(AGE);
        if (age < 0 || age >= SHAPES.length) age = 0;
        return SHAPES[age];
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState above = world.getBlockState(pos.up());
        Block blockAbove = above.getBlock();

        return blockAbove instanceof LeavesBlock || blockAbove instanceof PillarBlock;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        BlockState aboveState = ctx.getWorld().getBlockState(pos.up());
        Block aboveBlock = aboveState.getBlock();

        if (!(aboveBlock instanceof LeavesBlock || aboveBlock instanceof PillarBlock)) {
            return null;
        }

        FluidState fluidState = ctx.getWorld().getFluidState(pos);
        return this.getDefaultState()
                .with(AGE, 0)
                .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
                                                WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && !this.canPlaceAt(state, world, pos)) {
            return Blocks.AIR.getDefaultState();
        } else {
            if (state.get(WATERLOGGED)) {
                world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            }
            return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return CODEC;
    }
}