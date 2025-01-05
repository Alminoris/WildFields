package net.alminoris.wildfields.world.gen.decorator;

import net.alminoris.wildfields.world.gen.decorator.custom.CustomAlterGroundTreeDecorator;
import net.alminoris.wildfields.world.gen.decorator.custom.CustomVineDecorator;
import net.alminoris.wildfields.world.gen.decorator.custom.CustomVineLogDecorator;
import net.alminoris.wildfields.world.gen.decorator.custom.LeafCarpetDecorator;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class ModTreeDecorators
{
    public static final TreeDecoratorType<LeafCarpetDecorator> LEAF_CARPET_DECORATOR = new TreeDecoratorType<>(LeafCarpetDecorator.CODEC);

    public static final TreeDecoratorType<CustomVineDecorator> CUSTOM_VINE_DECORATOR = new TreeDecoratorType<>(CustomVineDecorator.CODEC);

    public static final TreeDecoratorType<CustomVineLogDecorator> CUSTOM_VINE_LOG_DECORATOR = new TreeDecoratorType<>(CustomVineLogDecorator.CODEC);

    public static final TreeDecoratorType<CustomAlterGroundTreeDecorator> CUSTOM_ALTER_GROUND_DECORATOR = new TreeDecoratorType<>(CustomAlterGroundTreeDecorator.CODEC);

    public static void register()
    {
        Registry.register(Registries.TREE_DECORATOR_TYPE, "leaf_carpet_decorator", LEAF_CARPET_DECORATOR);
        Registry.register(Registries.TREE_DECORATOR_TYPE, "custom_vine_decorator", CUSTOM_VINE_DECORATOR);
        Registry.register(Registries.TREE_DECORATOR_TYPE, "custom_vine_log_decorator", CUSTOM_VINE_LOG_DECORATOR);
        Registry.register(Registries.TREE_DECORATOR_TYPE, "custom_alter_ground_decorator", CUSTOM_ALTER_GROUND_DECORATOR);
    }
}
