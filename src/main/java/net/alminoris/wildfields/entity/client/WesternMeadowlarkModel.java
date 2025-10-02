package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.WesternMeadowlarkEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class WesternMeadowlarkModel extends GeoModel<WesternMeadowlarkEntity>
{
    @Override
    public Identifier getModelResource(WesternMeadowlarkEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "geo/western_meadowlark.geo.json");
    }

    @Override
    public Identifier getTextureResource(WesternMeadowlarkEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/western_meadowlark.png");
    }

    @Override
    public Identifier getAnimationResource(WesternMeadowlarkEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "animations/western_meadowlark.animation.json");
    }

    @Override
    public void setCustomAnimations(WesternMeadowlarkEntity animatable, long instanceId, AnimationState<WesternMeadowlarkEntity> animationState)
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
