package net.lawliet.testmod.item;

import net.lawliet.testmod.TestMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

public class TestEquipmentAssets {
    private static final ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID = ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("equipment_asset"));

    public static final ResourceKey<EquipmentAsset> AZURITE = createId("azurite");


    private static ResourceKey<EquipmentAsset> createId(String name)  {
        return ResourceKey.create(ROOT_ID, Identifier.fromNamespaceAndPath(TestMod.MODID, name));
    }
}
