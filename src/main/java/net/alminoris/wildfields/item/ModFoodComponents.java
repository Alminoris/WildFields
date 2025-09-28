package net.alminoris.wildfields.item;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents
{
    public static final FoodComponent OLIVES = new FoodComponent.Builder().nutrition(2).saturationModifier(0.25f).build();

    public static final FoodComponent SAIGA = new FoodComponent.Builder().nutrition(3).saturationModifier(0.3f).build();

    public static final FoodComponent COOKED_SAIGA = new FoodComponent.Builder().nutrition(8).saturationModifier(0.8f).build();

    public static final FoodComponent BISON = new FoodComponent.Builder().nutrition(4).saturationModifier(0.4f).build();

    public static final FoodComponent COOKED_BISON = new FoodComponent.Builder().nutrition(9).saturationModifier(0.9f).build();

    public static final FoodComponent JACKRABBIT = new FoodComponent.Builder().nutrition(3).saturationModifier(0.45f).build();

    public static final FoodComponent COOKED_JACKRABBIT = new FoodComponent.Builder().nutrition(6).saturationModifier(0.75f).build();

    public static FoodComponent registerFood(int nutrition, float saturation)
    {
        return new FoodComponent.Builder().nutrition(nutrition).saturationModifier(saturation).build();
    }
}