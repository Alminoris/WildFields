package net.alminoris.wildfields.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles
{
    public static final SimpleParticleType BAUHINIA_LEAVES = FabricParticleTypes.simple();

    public static void registerParticles()
    {
        Registry.register(
                Registries.PARTICLE_TYPE,
                Identifier.of("arborealnature", "bauhinia_leaves"),
                BAUHINIA_LEAVES
        );
    }
}
