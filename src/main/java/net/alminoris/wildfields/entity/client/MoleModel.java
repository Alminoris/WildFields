package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.MarmotEntity;
import net.alminoris.wildfields.entity.custom.MoleEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class MoleModel extends GeoModel<MoleEntity>
{
    @Override
    public Identifier getModelResource(MoleEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "geo/mole.geo.json");
    }

    @Override
    public Identifier getTextureResource(MoleEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/mole.png");
    }

    @Override
    public Identifier getAnimationResource(MoleEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "animations/mole.animation.json");
    }

    @Override
    public void setCustomAnimations(MoleEntity animatable, long instanceId, AnimationState<MoleEntity> animationState)
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
