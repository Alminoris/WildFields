package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.MarmotEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class MarmotModel extends GeoModel<MarmotEntity>
{
    @Override
    public Identifier getModelResource(MarmotEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "geo/marmot.geo.json");
    }

    @Override
    public Identifier getTextureResource(MarmotEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/marmot.png");
    }

    @Override
    public Identifier getAnimationResource(MarmotEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "animations/marmot.animation.json");
    }

    @Override
    public void setCustomAnimations(MarmotEntity animatable, long instanceId, AnimationState<MarmotEntity> animationState)
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
