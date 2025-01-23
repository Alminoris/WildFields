package net.alminoris.wildfields.item;


import net.minecraft.item.FoodComponent;

public class ModFoodComponents
{
    public static final FoodComponent OLIVES = new FoodComponent.Builder().hunger(2).saturationModifier(0.25f).build();

    public static final FoodComponent SAIGA = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).build();

    public static final FoodComponent COOKED_SAIGA = new FoodComponent.Builder().hunger(8).saturationModifier(0.8f).build();

    public static FoodComponent registerFood(int hunger, float saturation)
    {
        return new FoodComponent.Builder().hunger(hunger).saturationModifier(saturation).build();
    }
}
