package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.CoyoteEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class CoyoteModel extends GeoModel<CoyoteEntity>
{
    @Override
    public Identifier getModelResource(CoyoteEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "geo/coyote.geo.json");
    }

    @Override
    public Identifier getTextureResource(CoyoteEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/coyote.png");
    }

    @Override
    public Identifier getAnimationResource(CoyoteEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "animations/coyote.animation.json");
    }

    @Override
    public void setCustomAnimations(CoyoteEntity entity, long instanceId, AnimationState<CoyoteEntity> animationState)
    {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null && !entity.isSleeping())
        {
            EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityModelData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityModelData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
