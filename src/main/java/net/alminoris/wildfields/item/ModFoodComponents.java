package net.alminoris.wildfields.item;


import net.minecraft.item.FoodComponent;

public class ModFoodComponents
{
    public static final FoodComponent OLIVES = new FoodComponent.Builder().hunger(2).saturationModifier(0.25f).build();

    public static final FoodComponent SAIGA = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).build();

    public static final FoodComponent COOKED_SAIGA = new FoodComponent.Builder().hunger(8).saturationModifier(0.8f).build();

    public static final FoodComponent BISON = new FoodComponent.Builder().hunger(4).saturationModifier(0.4f).build();

    public static final FoodComponent BARLEY_BREAD = new FoodComponent.Builder().hunger(6).saturationModifier(0.5f).build();

    public static final FoodComponent OAT_COOKIE = new FoodComponent.Builder().hunger(2).saturationModifier(0.45f).build();

    public static final FoodComponent OATMEAL = createStew(8).build();

    public static final FoodComponent BARLEY_STEW = createStew(7).build();

    public static final FoodComponent COOKED_BISON = new FoodComponent.Builder().hunger(9).saturationModifier(0.9f).build();

    public static final FoodComponent JACKRABBIT = new FoodComponent.Builder().hunger(3).saturationModifier(0.45f).build();

    public static final FoodComponent COOKED_JACKRABBIT = new FoodComponent.Builder().hunger(6).saturationModifier(0.75f).build();

    public static final FoodComponent PALLID_WINGED_GRASSHOPPER_LEG = new FoodComponent.Builder().hunger(1).saturationModifier(0.25f).build();

    public static final FoodComponent COOKED_PALLID_WINGED_GRASSHOPPER_LEG = new FoodComponent.Builder().hunger(4).saturationModifier(0.55f).build();

    public static FoodComponent registerFood(int hunger, float saturation)
    {
        return new FoodComponent.Builder().hunger(hunger).saturationModifier(saturation).build();
    }

    private static FoodComponent.Builder createStew(int hunger)
    {
        return (new FoodComponent.Builder()).hunger(hunger).saturationModifier(0.6F);
    }
}
