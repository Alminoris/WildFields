package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.SaigaEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SaigaRenderer extends GeoEntityRenderer<SaigaEntity>
{
    public SaigaRenderer(EntityRendererFactory.Context renderManager)
    {
        super(renderManager, new SaigaModel());
    }

    @Override
    public Identifier getTextureLocation(SaigaEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/saiga.png");
    }

    @Override
    public void preRender(MatrixStack poseStack, SaigaEntity entity, BakedGeoModel model, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour)
    {
        if (entity.isBaby())
        {
            poseStack.scale(0.8f, 0.8f, 0.8f);
            model.getBone("horn1").get().setHidden(true);
            model.getBone("horn2").get().setHidden(true);
        }
        else
        {
            poseStack.scale(1.2F, 1.2F, 1.2F);
            model.getBone("horn1").get().setHidden(false);
            model.getBone("horn2").get().setHidden(false);
        }

        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }
}