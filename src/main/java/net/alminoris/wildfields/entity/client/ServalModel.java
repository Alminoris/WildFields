package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.ServalEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ServalModel extends GeoModel<ServalEntity>
{
    @Override
    public Identifier getModelResource(ServalEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "geo/serval.geo.json");
    }

    @Override
    public Identifier getTextureResource(ServalEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/serval.png");
    }

    @Override
    public Identifier getAnimationResource(ServalEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "animations/serval.animation.json");
    }

    @Override
    public void setCustomAnimations(ServalEntity entity, long instanceId, AnimationState<ServalEntity> animationState)
    {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null && !entity.isSleeping())
        {
            EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityModelData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityModelData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
