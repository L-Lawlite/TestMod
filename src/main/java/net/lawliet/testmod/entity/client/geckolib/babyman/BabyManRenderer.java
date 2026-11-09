package net.lawliet.testmod.entity.client.geckolib.babyman;

import com.geckolib.renderer.GeoEntityRenderer;
import net.lawliet.testmod.entity.BabyManEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class BabyManRenderer extends GeoEntityRenderer<BabyManEntity, LivingEntityRenderState> {
    public BabyManRenderer(EntityRendererProvider.Context context) {
        super(context, new BabyManModel());
    }
}
