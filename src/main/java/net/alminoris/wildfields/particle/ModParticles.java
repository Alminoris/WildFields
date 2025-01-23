package net.alminoris.wildfields.particle;

import net.alminoris.wildfields.WildFields;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles
{
    public static final SimpleParticleType OLIVE_LEAVES = FabricParticleTypes.simple();

    public static void registerParticles()
    {
        Registry.register(
                Registries.PARTICLE_TYPE,
                Identifier.of(WildFields.MOD_ID, "olive_leaves"),
                OLIVE_LEAVES
        );
    }
}
