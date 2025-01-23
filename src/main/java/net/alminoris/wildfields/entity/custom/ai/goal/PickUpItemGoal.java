package net.alminoris.wildfields.entity.custom.ai.goal;

import net.alminoris.wildfields.entity.custom.MarmotEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class PickUpItemGoal extends Goal
{
    private final PathAwareEntity pathAwareEntity;
    private final double speed;
    private ItemEntity targetItem;
    private final List<Item> prioritizedItems;

    public PickUpItemGoal(PathAwareEntity pathAwareEntity, double speed, Item... pickupabbleItems)
    {
        this.pathAwareEntity = pathAwareEntity;
        this.speed = speed;
        prioritizedItems = List.of(pickupabbleItems);
    }

    @Override
    public boolean canStart()
    {
        double searchRadius = 6.0D;
        List<ItemEntity> nearbyItems = this.pathAwareEntity.getWorld().getEntitiesByClass(
                ItemEntity.class,
                this.pathAwareEntity.getBoundingBox().expand(searchRadius),
                itemEntity -> prioritizedItems.contains(itemEntity.getStack().getItem())
        );

        if (!nearbyItems.isEmpty())
        {
            this.targetItem = nearbyItems.stream().findFirst().get();
            return true;
        }

        return false;
    }

    @Override
    public void start()
    {
        if (this.targetItem != null)
        {
            this.pathAwareEntity.getNavigation().startMovingTo(this.targetItem, this.speed);
        }
    }

    @Override
    public void tick()
    {
        if (this.targetItem != null && this.pathAwareEntity.squaredDistanceTo(this.targetItem) < 1.0D)
        {
            ItemStack itemStack = this.targetItem.getStack();
            if (pathAwareEntity instanceof MarmotEntity)
            {
                ((MarmotEntity) pathAwareEntity).triggerPickUpAnimation();
            }
            this.pathAwareEntity.equipStack(EquipmentSlot.MAINHAND, itemStack.split(1));

            if (itemStack.isEmpty())
            {
                this.targetItem.discard();
            }

            this.targetItem = null;
        }
    }

    @Override
    public boolean shouldContinue()
    {
        return this.targetItem != null && !this.targetItem.isRemoved();
    }
}

