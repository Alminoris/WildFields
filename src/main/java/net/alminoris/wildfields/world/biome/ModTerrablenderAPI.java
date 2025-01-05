package net.alminoris.wildfields.world.biome;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.world.biome.surface.ModMaterialRules;
import net.minecraft.util.Identifier;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi
{
    @Override
    public void onTerraBlenderInitialized()
    {
        //Regions.register(new PrairiesRegion(Identifier.of(WildFields.MOD_ID, "prairies_region"), 4));
        //Regions.register(new PampasasRegion(Identifier.of(WildFields.MOD_ID, "pampasas_region"), 4));
        Regions.register(new SteppesRegion(Identifier.of(WildFields.MOD_ID, "steppes_region"), 10));

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, WildFields.MOD_ID, ModMaterialRules.makeRules());
    }
}
