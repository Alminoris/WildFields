package net.alminoris.wildfields.world.gen.feature.custom;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class TripleTallPlantConfig implements FeatureConfig {
    public static final Codec<TripleTallPlantConfig> CODEC =
            BlockStateProvider.TYPE_CODEC.fieldOf("block_provider")
                    .xmap(TripleTallPlantConfig::new, cfg -> cfg.blockProvider)
                    .codec();

    public final BlockStateProvider blockProvider;

    public TripleTallPlantConfig(BlockStateProvider blockProvider)
    {
        this.blockProvider = blockProvider;
    }
}