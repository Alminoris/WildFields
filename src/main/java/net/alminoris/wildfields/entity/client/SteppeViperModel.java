package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.SteppeViperEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SteppeViperModel extends GeoModel<SteppeViperEntity>
{
    @Override
    public Identifier getModelResource(SteppeViperEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "geo/steppe_viper.geo.json");
    }

    @Override
    public Identifier getTextureResource(SteppeViperEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/steppe_viper.png");
    }

    @Override
    public Identifier getAnimationResource(SteppeViperEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "animations/steppe_viper.animation.json");
    }

    @Override
    public void setCustomAnimations(SteppeViperEntity animatable, long instanceId, AnimationState<SteppeViperEntity> animationState)
    {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null)
        {
            EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotY(entityModelData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
