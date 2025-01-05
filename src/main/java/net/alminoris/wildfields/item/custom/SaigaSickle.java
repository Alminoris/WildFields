package net.alminoris.wildfields.item.custom;

import net.alminoris.wildfields.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SaigaSickle extends ToolItem
{
    public SaigaSickle(ToolMaterial material, Settings settings)
    {
        super(material, settings);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner)
    {
        if (state.isIn(ModTags.Blocks.STEPPE_VIPER_PLANTS))
        {
            if (!world.isClient)
            {
                for (int dx = -1; dx <= 1; dx++)
                {
                    for (int dz = -1; dz <= 1; dz++) {
                        BlockPos targetPos = pos.add(dx, 0, dz);
                        BlockState targetState = world.getBlockState(targetPos);

                        if (targetState.isIn(ModTags.Blocks.STEPPE_VIPER_PLANTS))
                        {
                            world.breakBlock(targetPos, true, miner);
                        }
                    }
                }
            }
        }
        return super.postMine(stack, world, state, pos, miner);
    }
}
