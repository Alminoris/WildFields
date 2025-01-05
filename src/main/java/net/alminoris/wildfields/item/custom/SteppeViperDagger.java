package net.alminoris.wildfields.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class SteppeViperDagger extends SwordItem
{
    public SteppeViperDagger(ToolMaterial material, Settings settings)
    {
        super(material, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 2));
        return super.postHit(stack, target, attacker);
    }
}
