package net.alminoris.wildfields.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Items;

public class ModFoodComponents
{
    public static final FoodComponent OLIVES = new FoodComponent.Builder().nutrition(2).saturationModifier(0.25f).build();

    public static final FoodComponent SAIGA = new FoodComponent.Builder().nutrition(3).saturationModifier(0.3f).build();

    public static final FoodComponent COOKED_SAIGA = new FoodComponent.Builder().nutrition(8).saturationModifier(0.8f).build();

    public static final FoodComponent BISON = new FoodComponent.Builder().nutrition(4).saturationModifier(0.4f).build();

    public static final FoodComponent BARLEY_BREAD = new FoodComponent.Builder().nutrition(6).saturationModifier(0.5f).build();

    public static final FoodComponent OAT_COOKIE = new FoodComponent.Builder().nutrition(2).saturationModifier(0.45f).build();

    public static final FoodComponent OATMEAL = createStew(8).build();

    public static final FoodComponent BARLEY_STEW = createStew(7).build();

    public static final FoodComponent COOKED_BISON = new FoodComponent.Builder().nutrition(9).saturationModifier(0.9f).build();

    public static final FoodComponent JACKRABBIT = new FoodComponent.Builder().nutrition(3).saturationModifier(0.45f).build();

    public static final FoodComponent COOKED_JACKRABBIT = new FoodComponent.Builder().nutrition(6).saturationModifier(0.75f).build();

    public static final FoodComponent PALLID_WINGED_GRASSHOPPER_LEG = new FoodComponent.Builder().nutrition(1).saturationModifier(0.25f).build();

    public static final FoodComponent COOKED_PALLID_WINGED_GRASSHOPPER_LEG = new FoodComponent.Builder().nutrition(4).saturationModifier(0.55f).build();

    public static FoodComponent registerFood(int nutrition, float saturation)
    {
        return new FoodComponent.Builder().nutrition(nutrition).saturationModifier(saturation).build();
    }

    private static FoodComponent.Builder createStew(int hunger) {
        return new FoodComponent.Builder().nutrition(hunger).saturationModifier(0.6F).usingConvertsTo(Items.BOWL);
    }
}