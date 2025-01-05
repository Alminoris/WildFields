package net.alminoris.wildfields.item.custom;

import net.alminoris.wildfields.entity.custom.projectile.SteppeArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SteppeArrowItem extends ArrowItem
{
    public SteppeArrowItem(Item.Settings settings)
    {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom)
    {
        return new SteppeArrowEntity(world, shooter, stack.copyWithCount(1), shotFrom);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction)
    {
        SteppeArrowEntity steppeArrowEntity = new SteppeArrowEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack.copyWithCount(1), null);
        steppeArrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
        return steppeArrowEntity;
    }
}
