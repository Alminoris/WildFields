package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.WhiteTailedJackrabbitEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class WhiteTailedJackrabbitModel extends GeoModel<WhiteTailedJackrabbitEntity>
{
    @Override
    public Identifier getModelResource(WhiteTailedJackrabbitEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "geo/white_tailed_jackrabbit.geo.json");
    }

    @Override
    public Identifier getTextureResource(WhiteTailedJackrabbitEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/white_tailed_jackrabbit.png");
    }

    @Override
    public Identifier getAnimationResource(WhiteTailedJackrabbitEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "animations/white_tailed_jackrabbit.animation.json");
    }

    @Override
    public void setCustomAnimations(WhiteTailedJackrabbitEntity animatable, long instanceId, AnimationState<WhiteTailedJackrabbitEntity> animationState)
    {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null)
        {
            EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityModelData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityModelData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}