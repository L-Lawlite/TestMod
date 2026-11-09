package net.lawliet.testmod.entity.client.geckolib.babyman;

import com.geckolib.cache.animation.BakedAnimations;
import com.geckolib.cache.model.BakedGeoModel;
import com.geckolib.model.GeoModel;
import com.geckolib.renderer.base.GeoRenderState;
import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.entity.BabyManEntity;
import net.minecraft.resources.Identifier;

public class BabyManModel extends GeoModel<BabyManEntity> {

    public static final Identifier MODEL_IDENTIFIER = Identifier.fromNamespaceAndPath(TestMod.MODID, "baby_man");

    /// Returns the resource ID for the [BakedGeoModel] (model JSON file) to render based on the provided [GeoRenderState]
    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return MODEL_IDENTIFIER;
    }

    /// Returns the resource path for the texture file to render based on the provided [GeoRenderState]
    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath(TestMod.MODID, "textures/entity/baby_man/baby_man.png");
    }

    /// Returns the resource ID for the [BakedAnimations] (animation JSON file) to use for animations based on the provided animatable
    @Override
    public Identifier getAnimationResource(BabyManEntity animatable) {
        return MODEL_IDENTIFIER;
    }
}
