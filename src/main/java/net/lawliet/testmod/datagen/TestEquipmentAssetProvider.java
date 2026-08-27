package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.item.TestEquipmentAssets;
import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

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
        output.accept(TestEquipmentAssets.AZURITE, EquipmentClientInfo.builder()
                        .addHumanoidLayers(TestMod.createIdentifier("azurite"), false)
                        .addLayers(EquipmentClientInfo.LayerType.HORSE_BODY, new EquipmentClientInfo.Layer(TestMod.createIdentifier("azurite")))
                .build());
    }
}
