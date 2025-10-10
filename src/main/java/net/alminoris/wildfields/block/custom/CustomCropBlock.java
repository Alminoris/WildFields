package net.alminoris.wildfields.block.custom;

import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;

import static net.alminoris.wildfields.util.helper.ModBlockSetsHelper.CROP_SEEDS;

public class CustomCropBlock extends CropBlock
{
    private final String name;

    public CustomCropBlock(String name)
    {
        super(Settings.copy(Blocks.WHEAT));
        this.name = name;
    }

    @Override
    protected ItemConvertible getSeedsItem()
    {
        return CROP_SEEDS.get(name);
    }
}
