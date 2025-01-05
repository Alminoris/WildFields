package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.SteppeEagleEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SteppeEagleModel extends GeoModel<SteppeEagleEntity>
{
    @Override
    public Identifier getModelResource(SteppeEagleEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "geo/steppe_eagle.geo.json");
    }

    @Override
    public Identifier getTextureResource(SteppeEagleEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/steppe_eagle.png");
    }

    @Override
    public Identifier getAnimationResource(SteppeEagleEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "animations/steppe_eagle.animation.json");
    }

    @Override
    public void setCustomAnimations(SteppeEagleEntity animatable, long instanceId, AnimationState<SteppeEagleEntity> animationState)
    {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null)
        {
            EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityModelData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityModelData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
