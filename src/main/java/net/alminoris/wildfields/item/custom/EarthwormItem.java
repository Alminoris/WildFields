package net.alminoris.wildfields.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.Map;

public class EarthwormItem extends Item
{
    private static final Map<Block, IntProperty> ALLOWED_PLANTS = Map.of(
            Blocks.WHEAT, Properties.AGE_7,
            Blocks.BEETROOTS, Properties.AGE_3,
            Blocks.CARROTS, Properties.AGE_7,
            Blocks.POTATOES, Properties.AGE_7
    );

    public EarthwormItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getStack();

        if (!ALLOWED_PLANTS.containsKey(block))
        {
            return ActionResult.PASS;
        }

        IntProperty ageProperty = ALLOWED_PLANTS.get(block);

        if (!state.contains(ageProperty))
        {
            return ActionResult.PASS;
        }

        int age = state.get(ageProperty);
        int maxAge = Collections.max(ageProperty.getValues());

        if (age < maxAge && world.random.nextFloat() < 0.5f)
        {
            world.setBlockState(pos, state.with(ageProperty, age + 1), 2);

            assert player != null;
            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
            }

            world.playSound(null, pos, SoundEvents.BLOCK_ROOTED_DIRT_PLACE, SoundCategory.BLOCKS, 0.5F, 1.2F);
            world.addParticle(ParticleTypes.HAPPY_VILLAGER,
                    pos.getX() + 0.5, pos.getY() + 0.7, pos.getZ() + 0.5,
                    0.0, 0.1, 0.0);

            return ActionResult.SUCCESS;
        }

        return ActionResult.CONSUME;
    }
}