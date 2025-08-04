package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.MarmotEntity;
import net.alminoris.wildfields.entity.custom.MoleEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MoleRenderer extends GeoEntityRenderer<MoleEntity>
{
    public MoleRenderer(EntityRendererFactory.Context renderManager)
    {
        super(renderManager, new MoleModel());
    }

    @Override
    public Identifier getTextureLocation(MoleEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/mole.png");
    }

    @Override
    public void postRender(MatrixStack poseStack, MoleEntity entity, BakedGeoModel model, VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour)
    {
        super.postRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }

    @Override
    public void preRender(MatrixStack poseStack, MoleEntity entity, BakedGeoModel model, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour)
    {
        if (entity.isBaby())
            poseStack.scale(0.35f, 0.35f, 0.35f);
        else { poseStack.scale(0.75F, 0.75F, 0.75F); }
        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }
}
