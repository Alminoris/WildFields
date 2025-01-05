package net.alminoris.wildfields.entity.custom.ai.goal;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;

import java.util.List;

public class EatHeldItemGoal extends Goal
{
    private final AnimalEntity animal;
    private final List<Item> items;

    public EatHeldItemGoal(AnimalEntity animal, Item... items)
    {
        this.animal = animal;
        this.items = List.of(items);
    }

    @Override
    public boolean canStart()
    {
        ItemStack heldItem = animal.getEquippedStack(EquipmentSlot.MAINHAND);
        return items.contains(heldItem.getItem());
    }

    @Override
    public void start()
    {
       eatItem();
    }

    private void eatItem()
    {
        ItemStack heldItem = animal.getEquippedStack(EquipmentSlot.MAINHAND);

        animal.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1.0F, 1.0F);

        heldItem.decrement(1);

        if (heldItem.isEmpty())
        {
            animal.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        }
    }

    @Override
    public boolean shouldContinue()
    {
        return false;
    }
}
