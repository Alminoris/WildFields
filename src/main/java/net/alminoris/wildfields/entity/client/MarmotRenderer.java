package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.MarmotEntity;
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

public class MarmotRenderer extends GeoEntityRenderer<MarmotEntity>
{
    private final ItemRenderer itemRenderer;

    public MarmotRenderer(EntityRendererFactory.Context renderManager)
    {
        super(renderManager, new MarmotModel());

        this.itemRenderer = renderManager.getItemRenderer();
    }

    @Override
    public Identifier getTextureLocation(MarmotEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/marmot.png");
    }

    @Override
    public void postRender(MatrixStack poseStack, MarmotEntity entity, BakedGeoModel model, VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour)
    {
        this.renderHeldItem(entity, poseStack, bufferSource, packedLight);
        super.postRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }

    @Override
    public void preRender(MatrixStack poseStack, MarmotEntity entity, BakedGeoModel model, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour)
    {
        if (entity.isBaby())
            poseStack.scale(0.6f, 0.6f, 0.6f);
        else { poseStack.scale(1.2F, 1.2F, 1.2F); }
        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }

    private void renderHeldItem(MarmotEntity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light)
    {
        ItemStack itemStack = entity.getStackInHand(Hand.MAIN_HAND);

        if (!itemStack.isEmpty())
        {
            BakedModel model = this.itemRenderer.getModel(itemStack, entity.getWorld(), entity, 0);

            matrices.push();
            matrices.translate(0.0D, 0.5D, 0.0D);
            matrices.scale(0.5F, 0.5F, 0.5F);

            this.itemRenderer.renderItem(itemStack,
                    ModelTransformationMode.THIRD_PERSON_RIGHT_HAND,
                    false,
                    matrices,
                    vertexConsumers,
                    light,
                    0,
                    model);

            matrices.pop();
        }
    }
}
