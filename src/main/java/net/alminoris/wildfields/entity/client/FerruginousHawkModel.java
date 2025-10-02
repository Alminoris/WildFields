package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.FerruginousHawkEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class FerruginousHawkModel extends GeoModel<FerruginousHawkEntity>
{
    @Override
    public Identifier getModelResource(FerruginousHawkEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "geo/ferruginous_hawk.geo.json");
    }

    @Override
    public Identifier getTextureResource(FerruginousHawkEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/ferruginous_hawk.png");
    }

    @Override
    public Identifier getAnimationResource(FerruginousHawkEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "animations/ferruginous_hawk.animation.json");
    }

    @Override
    public void setCustomAnimations(FerruginousHawkEntity animatable, long instanceId, AnimationState<FerruginousHawkEntity> animationState)
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