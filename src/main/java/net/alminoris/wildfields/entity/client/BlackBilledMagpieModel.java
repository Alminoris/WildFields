package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.BlackBilledMagpieEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BlackBilledMagpieModel extends GeoModel<BlackBilledMagpieEntity>
{
    @Override
    public Identifier getModelResource(BlackBilledMagpieEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "geo/black_billed_magpie.geo.json");
    }

    @Override
    public Identifier getTextureResource(BlackBilledMagpieEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/black_billed_magpie.png");
    }

    @Override
    public Identifier getAnimationResource(BlackBilledMagpieEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "animations/black_billed_magpie.animation.json");
    }

    @Override
    public void setCustomAnimations(BlackBilledMagpieEntity animatable, long instanceId, AnimationState<BlackBilledMagpieEntity> animationState)
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
