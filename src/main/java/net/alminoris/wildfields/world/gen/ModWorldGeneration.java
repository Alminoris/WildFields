package net.alminoris.wildfields.world.gen;

public class ModWorldGeneration
{
    public static void generateModWorldGen()
    {
        ModOreGeneration.generateOres();
        ModBiomeModifications.addTreesAndHugeMushrooms();
        ModEntityGeneration.addSpawns();
    }
}
