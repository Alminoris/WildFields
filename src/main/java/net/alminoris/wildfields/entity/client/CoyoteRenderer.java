package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.CoyoteEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CoyoteRenderer extends GeoEntityRenderer<CoyoteEntity>
{
    public CoyoteRenderer(EntityRendererFactory.Context renderManager)
    {
        super(renderManager, new CoyoteModel());
    }

    @Override
    public Identifier getTextureLocation(CoyoteEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/coyote.png");
    }

    @Override
    public void preRender(MatrixStack poseStack, CoyoteEntity entity, BakedGeoModel model, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour)
    {
        if (entity.isBaby())
            poseStack.scale(0.6f, 0.6f, 0.6f);
        else { poseStack.scale(1.0F, 1.0F, 1.0F); }
        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }
}
