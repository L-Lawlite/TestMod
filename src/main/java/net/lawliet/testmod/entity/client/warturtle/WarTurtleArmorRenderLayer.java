package net.lawliet.testmod.entity.client.warturtle;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lawliet.testmod.registries.entity.client.TestModelLayers;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.Equippable;

public class WarTurtleArmorRenderLayer extends RenderLayer<WarTurtleRenderState, WarTurtleModel> {
    private final WarTurtleModel model;
    private final EquipmentLayerRenderer equipmentRenderer;

//    private static final Map<Item, Identifier> ARMOR_MAP = Map.of(
//            TestItems.IRON_WAR_TURTLE_ARMOR.get(), TestMod.createIdentifier("textures/entity/war_turtle/armor/iron_war_turtle.png"),
//            TestItems.GOLD_WAR_TURTLE_ARMOR.get(), TestMod.createIdentifier("textures/entity/war_turtle/armor/gold_war_turtle.png"),
//            TestItems.DIAMOND_WAR_TURTLE_ARMOR.get(), TestMod.createIdentifier("textures/entity/war_turtle/armor/diamond_war_turtle.png"),
//            TestItems.NETHERITE_WAR_TURTLE_ARMOR.get(), TestMod.createIdentifier("textures/entity/war_turtle/armor/netherite_war_turtle.png"),
//            TestItems.AZURITE_WAR_TURTLE_ARMOR.get(), TestMod.createIdentifier("textures/entity/war_turtle/armor/azurite_war_turtle.png")
//    );
    public WarTurtleArmorRenderLayer(RenderLayerParent<WarTurtleRenderState, WarTurtleModel> renderer, EntityModelSet modelSet, EquipmentLayerRenderer equipmentRenderer) {
        super(renderer);
        this.model = new WarTurtleModel(modelSet.bakeLayer(TestModelLayers.WAR_TURTLE_ARMOR));
        this.equipmentRenderer = equipmentRenderer;
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, WarTurtleRenderState state, float yRot, float xRot) {
        ItemStack bodyItem = state.bodyItem;
        Equippable equippable = bodyItem.get(DataComponents.EQUIPPABLE);
        if (equippable != null && equippable.assetId().isPresent() && !state.isBaby) {
            this.renderEquipment(poseStack, submitNodeCollector, state, bodyItem, equippable.assetId().get(), lightCoords);
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
}
