package net.lawliet.testmod.entity.client.warturtle;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.registries.entity.client.TestModelLayers;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.Equippable;

import java.util.stream.Stream;

public class WarTurtleArmorRenderLayer extends RenderLayer<WarTurtleRenderState, WarTurtleModel> {
    private final WarTurtleModel model;
    private final WarTurtleModel dyeModel;
    private final EquipmentLayerRenderer equipmentRenderer;

    private static final Identifier[] DYE_LOCATION = Stream.of(DyeColor.values()).map(WarTurtleArmorRenderLayer::getDyeLocation).toArray(Identifier[]::new);

    public WarTurtleArmorRenderLayer(RenderLayerParent<WarTurtleRenderState, WarTurtleModel> renderer, EntityModelSet modelSet, EquipmentLayerRenderer equipmentRenderer) {
        super(renderer);
        this.model = new WarTurtleModel(modelSet.bakeLayer(TestModelLayers.WAR_TURTLE_ARMOR));
        this.dyeModel = new WarTurtleModel(modelSet.bakeLayer(TestModelLayers.WAR_TURTLE_ARMOR_DYE));
        this.equipmentRenderer = equipmentRenderer;
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, WarTurtleRenderState state, float yRot, float xRot) {
        ItemStack bodyItem = state.bodyItem;
        Equippable equippable = bodyItem.get(DataComponents.EQUIPPABLE);
        if (equippable != null && equippable.assetId().isPresent() && !state.isBaby) {
            this.renderEquipment(poseStack, submitNodeCollector, state, bodyItem, equippable.assetId().get(), lightCoords);
        }

        if (state.dyeColor != null) {
            DyeColor dyeColor = state.dyeColor;
            Identifier identifier = DYE_LOCATION[dyeColor.getId()];
            submitNodeCollector.order(2).submitModel(this.dyeModel, state, poseStack, RenderTypes.entityCutout(identifier), lightCoords, OverlayTexture.NO_OVERLAY, 0);
        }
    }

    private void renderEquipment(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, WarTurtleRenderState state, ItemStack itemStack, ResourceKey<EquipmentAsset> equipmentAssetId, int lightCoords) {
        this.equipmentRenderer.renderLayers(
                //extended EquipmentClientInfo.LayerType
                EquipmentClientInfo.LayerType.valueOf("testmod_WAR_TURTLE_BODY"),
                equipmentAssetId,
                model,
                state,
                itemStack,
                poseStack,
                submitNodeCollector,
                lightCoords,
                state.outlineColor
        );
    }

    private static Identifier getDyeLocation(DyeColor dyeColor) {
        return Identifier.fromNamespaceAndPath(TestMod.MODID, "textures/entity/war_turtle/armor/blankies/%s.png".formatted(dyeColor.getName()));
    }
}
