package net.alminoris.wildfields.item.custom;

import net.alminoris.wildfields.entity.custom.projectile.SteppeArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SteppeArrowItem extends ArrowItem
{
    public SteppeArrowItem(Item.Settings settings)
    {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        return new SteppeArrowEntity(world, shooter);
    }
}
