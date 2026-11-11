package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.item.TestEquipmentAssets;
import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.function.BiConsumer;

public class TestEquipmentAssetProvider extends EquipmentAssetProvider {
    public TestEquipmentAssetProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void registerModels(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output) {
        bootstrap(output);
    }

    protected void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output) {
        Identifier azurite = TestMod.createIdentifier("azurite");
        String war_turtle_body_key = "testmod_WAR_TURTLE_BODY";
        output.accept(TestEquipmentAssets.AZURITE, EquipmentClientInfo.builder()
                        .addHumanoidLayers(azurite, false)
                        .addLayers(EquipmentClientInfo.LayerType.HORSE_BODY, new EquipmentClientInfo.Layer(azurite))
                        .addLayers(EquipmentClientInfo.LayerType.valueOf(war_turtle_body_key), new EquipmentClientInfo.Layer(azurite))
                .build());
        output.accept(EquipmentAssets.IRON, EquipmentClientInfo.builder()
                        .addLayers(EquipmentClientInfo.LayerType.valueOf(war_turtle_body_key), new EquipmentClientInfo.Layer(TestMod.createIdentifier("iron")))
                .build());
        output.accept(EquipmentAssets.GOLD, EquipmentClientInfo.builder()
                .addLayers(EquipmentClientInfo.LayerType.valueOf(war_turtle_body_key), new EquipmentClientInfo.Layer(TestMod.createIdentifier("gold")))
                .build());
        output.accept(EquipmentAssets.DIAMOND, EquipmentClientInfo.builder()
                .addLayers(EquipmentClientInfo.LayerType.valueOf(war_turtle_body_key), new EquipmentClientInfo.Layer(TestMod.createIdentifier("diamond")))
                .build());
        output.accept(EquipmentAssets.NETHERITE, EquipmentClientInfo.builder()
                .addLayers(EquipmentClientInfo.LayerType.valueOf(war_turtle_body_key), new EquipmentClientInfo.Layer(TestMod.createIdentifier("netherite")))
                .build());
    }
}
