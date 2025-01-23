package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.DarklingBeetleEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class DarklingBeetleModel extends GeoModel<DarklingBeetleEntity>
{
    @Override
    public Identifier getModelResource(DarklingBeetleEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "geo/darkling_beetle.geo.json");
    }

    @Override
    public Identifier getTextureResource(DarklingBeetleEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/darkling_beetle.png");
    }

    @Override
    public Identifier getAnimationResource(DarklingBeetleEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "animations/darkling_beetle.animation.json");
    }

    @Override
    public void setCustomAnimations(DarklingBeetleEntity animatable, long instanceId, AnimationState<DarklingBeetleEntity> animationState)
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
