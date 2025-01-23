package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.SteppeViperEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SteppeViperRenderer extends GeoEntityRenderer<SteppeViperEntity>
{
    public SteppeViperRenderer(EntityRendererFactory.Context renderManager)
    {
        super(renderManager, new SteppeViperModel());
    }

    @Override
    public Identifier getTextureLocation(SteppeViperEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/steppe_viper.png");
    }

    @Override
    public void preRender(MatrixStack poseStack, SteppeViperEntity entity, BakedGeoModel model, VertexConsumerProvider bufferSource,
                          VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay,
                          float red, float green, float blue, float alpha)
    {
        if (entity.isBaby())
        {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
