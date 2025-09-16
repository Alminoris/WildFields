package net.alminoris.wildfields.world.gen.feature.custom;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.BitSetVoxelSet;
import net.minecraft.util.shape.VoxelSet;
import net.minecraft.world.ModifiableWorld;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.root.RootPlacer;
import net.minecraft.world.gen.treedecorator.TreeDecorator;

import java.util.Iterator;
import java.util.List;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.BiConsumer;

public class TreeGroupFeature extends Feature<TreeFeatureConfig> {
    public TreeGroupFeature(Codec<TreeFeatureConfig> codec) {
        super(codec);
    }

    private static boolean isVine(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, state -> state.isOf(Blocks.VINE));
    }

    public static boolean isAirOrLeaves(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, state -> state.isAir() || state.isIn(BlockTags.LEAVES));
    }

    private static void setBlockStateWithoutUpdatingNeighbors(ModifiableWorld world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state, Block.NOTIFY_ALL | Block.FORCE_STATE);
    }

    public static boolean canReplace(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, state -> state.isAir() || state.isIn(BlockTags.REPLACEABLE_BY_TREES));
    }

    private boolean generateSingle(
            StructureWorldAccess world,
            Random random,
            BlockPos pos,
            BiConsumer<BlockPos, BlockState> rootPlacerReplacer,
            BiConsumer<BlockPos, BlockState> trunkPlacerReplacer,
            FoliagePlacer.BlockPlacer blockPlacer,
            TreeFeatureConfig config
    ) {
        int i = config.trunkPlacer.getHeight(random);
        int j = config.foliagePlacer.getRandomHeight(random, i, config);
        int k = i - j;
        int l = config.foliagePlacer.getRandomRadius(random, k);
        BlockPos blockPos = config.rootPlacer.map(rootPlacer -> rootPlacer.trunkOffset(pos, random)).orElse(pos);
        int m = Math.min(pos.getY(), blockPos.getY());
        int n = Math.max(pos.getY(), blockPos.getY()) + i + 1;

        if (m >= world.getBottomY() + 1 && n <= world.getTopY()) {
            OptionalInt optionalInt = config.minimumSize.getMinClippedHeight();
            int o = this.getTopPosition(world, i, blockPos, config);
            if (o >= i || (!optionalInt.isEmpty() && o >= optionalInt.getAsInt())) {
                if (config.rootPlacer.isPresent() &&
                        !config.rootPlacer.get().generate(world, rootPlacerReplacer, random, pos, blockPos, config)) {
                    return false;
                } else {
                    List<FoliagePlacer.TreeNode> list = config.trunkPlacer.generate(world, trunkPlacerReplacer, random, o, blockPos, config);
                    list.forEach(node -> config.foliagePlacer.generate(world, blockPlacer, random, config, o, node, j, l));
                    return true;
                }
            }
        }
        return false;
    }

    private int getTopPosition(TestableWorld world, int height, BlockPos pos, TreeFeatureConfig config) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (int i = 0; i <= height + 1; i++) {
            int j = config.minimumSize.getRadius(height, i);
            for (int k = -j; k <= j; k++) {
                for (int l = -j; l <= j; l++) {
                    mutable.set(pos, k, i, l);
                    if (!config.trunkPlacer.canReplaceOrIsLog(world, mutable) || (!config.ignoreVines && isVine(world, mutable))) {
                        return i - 2;
                    }
                }
            }
        }
        return height;
    }

    @Override
    protected void setBlockState(ModifiableWorld world, BlockPos pos, BlockState state) {
        setBlockStateWithoutUpdatingNeighbors(world, pos, state);
    }

    @Override
    public boolean generate(FeatureContext<TreeFeatureConfig> context)
    {
        final StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();
        BlockPos origin = context.getOrigin();
        TreeFeatureConfig config = context.getConfig();

        boolean success = false;

        success |= generateTree(world, random, origin, config);

        int count = 5 + random.nextInt(3);
        int radius = 8 + random.nextInt(5);

        for (int i = 0; i < count; i++)
        {
            double angle = 2 * Math.PI * i / count + random.nextDouble();
            int dx = (int) (Math.cos(angle) * radius);
            int dz = (int) (Math.sin(angle) * radius);
            BlockPos offsetPos = origin.add(dx, 0, dz);

            BlockPos ground = findGround(world, offsetPos);
            if (ground != null) {
                success |= generateTree(world, random, ground, config);
            }
        }

        return success;
    }

    private boolean generateTree(StructureWorldAccess world, Random random, BlockPos pos, TreeFeatureConfig config) {
        Set<BlockPos> set = Sets.newHashSet();
        Set<BlockPos> set2 = Sets.newHashSet();
        final Set<BlockPos> set3 = Sets.newHashSet();
        Set<BlockPos> set4 = Sets.newHashSet();

        BiConsumer<BlockPos, BlockState> biConsumer = (p, s) -> {
            set.add(p.toImmutable());
            world.setBlockState(p, s, Block.NOTIFY_ALL | Block.FORCE_STATE);
        };

        BiConsumer<BlockPos, BlockState> biConsumer2 = (p, s) -> {
            set2.add(p.toImmutable());
            world.setBlockState(p, s, Block.NOTIFY_ALL | Block.FORCE_STATE);
        };

        FoliagePlacer.BlockPlacer blockPlacer = new FoliagePlacer.BlockPlacer() {
            @Override
            public void placeBlock(BlockPos p, BlockState s) {
                set3.add(p.toImmutable());
                world.setBlockState(p, s, Block.NOTIFY_ALL | Block.FORCE_STATE);
            }

            @Override
            public boolean hasPlacedBlock(BlockPos p) {
                return set3.contains(p);
            }
        };

        BiConsumer<BlockPos, BlockState> biConsumer3 = (p, s) -> {
            set4.add(p.toImmutable());
            world.setBlockState(p, s, Block.NOTIFY_ALL | Block.FORCE_STATE);
        };

        boolean bl = this.generateSingle(world, random, pos, biConsumer, biConsumer2, blockPlacer, config);

        if (bl && (!set2.isEmpty() || !set3.isEmpty())) {
            if (!config.decorators.isEmpty()) {
                TreeDecorator.Generator generator = new TreeDecorator.Generator(world, biConsumer3, random, set2, set3, set);
                config.decorators.forEach(decorator -> decorator.generate(generator));
            }
            return BlockBox.encompassPositions(Iterables.concat(set, set2, set3, set4)).map(box -> {
                VoxelSet voxelSet = placeLogsAndLeaves(world, box, set2, set4, set);
                StructureTemplate.updateCorner(world, 3, voxelSet, box.getMinX(), box.getMinY(), box.getMinZ());
                return true;
            }).orElse(false);
        }
        return false;
    }

    private static BlockPos findGround(StructureWorldAccess world, BlockPos pos) {
        BlockPos.Mutable mutable = pos.mutableCopy();
        while (mutable.getY() > world.getBottomY()) {
            if (!world.isAir(mutable)) {
                return mutable.up();
            }
            mutable.move(Direction.DOWN);
        }
        return null;
    }

    private static VoxelSet placeLogsAndLeaves(
            WorldAccess world,
            BlockBox box,
            Set<BlockPos> trunkPositions,
            Set<BlockPos> decorationPositions,
            Set<BlockPos> rootPositions
    ) {
        VoxelSet voxelSet = new BitSetVoxelSet(box.getBlockCountX(), box.getBlockCountY(), box.getBlockCountZ());
        int i = 7;
        List<Set<BlockPos>> list = Lists.newArrayList();
        for (int j = 0; j < 7; j++) {
            list.add(Sets.newHashSet());
        }
        for (BlockPos blockPos : Lists.newArrayList(Sets.union(decorationPositions, rootPositions))) {
            if (box.contains(blockPos)) {
                voxelSet.set(blockPos.getX() - box.getMinX(), blockPos.getY() - box.getMinY(), blockPos.getZ() - box.getMinZ());
            }
        }
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int k = 0;
        list.get(0).addAll(trunkPositions);
        while (true) {
            while (k >= 7 || !list.get(k).isEmpty()) {
                if (k >= 7) {
                    return voxelSet;
                }

                Iterator<BlockPos> iterator = list.get(k).iterator();
                BlockPos blockPos2 = iterator.next();
                iterator.remove();
                if (box.contains(blockPos2)) {
                    if (k != 0) {
                        BlockState blockState = world.getBlockState(blockPos2);
                        setBlockStateWithoutUpdatingNeighbors(world, blockPos2, blockState.with(Properties.DISTANCE_1_7, k));
                    }
                    voxelSet.set(blockPos2.getX() - box.getMinX(), blockPos2.getY() - box.getMinY(), blockPos2.getZ() - box.getMinZ());
                    for (Direction direction : Direction.values()) {
                        mutable.set(blockPos2, direction);
                        if (box.contains(mutable)) {
                            int l = mutable.getX() - box.getMinX();
                            int m = mutable.getY() - box.getMinY();
                            int n = mutable.getZ() - box.getMinZ();
                            if (!voxelSet.contains(l, m, n)) {
                                BlockState blockState2 = world.getBlockState(mutable);
                                OptionalInt optionalInt = LeavesBlock.getOptionalDistanceFromLog(blockState2);
                                if (!optionalInt.isEmpty()) {
                                    int o = Math.min(optionalInt.getAsInt(), k + 1);
                                    if (o < 7) {
                                        list.get(o).add(mutable.toImmutable());
                                        k = Math.min(k, o);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            k++;
        }
    }
}