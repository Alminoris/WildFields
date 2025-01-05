package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.SaigaEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SaigaModel extends GeoModel<SaigaEntity>
{
    @Override
    public Identifier getModelResource(SaigaEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "geo/saiga.geo.json");
    }

    @Override
    public Identifier getTextureResource(SaigaEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/saiga.png");
    }

    @Override
    public Identifier getAnimationResource(SaigaEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "animations/saiga.animation.json");
    }

    @Override
    public void setCustomAnimations(SaigaEntity entity, long instanceId, AnimationState<SaigaEntity> animationState)
    {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null && !entity.isEating())
        {
            EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityModelData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityModelData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
