package net.alminoris.wildfields.entity.client.projectile;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.projectile.SteppeArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class SteppeArrowRenderer extends ProjectileEntityRenderer<SteppeArrowEntity>
{
    public static final Identifier TEXTURE = Identifier.of(WildFields.MOD_ID, "textures/entity/projectiles/steppe_arrow.png");

    public SteppeArrowRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(SteppeArrowEntity entity)
    {
        return TEXTURE;
    }
}
