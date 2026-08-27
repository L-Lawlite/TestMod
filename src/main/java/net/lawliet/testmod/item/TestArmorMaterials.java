package net.lawliet.testmod.item;

import com.google.common.collect.Maps;
import net.lawliet.testmod.tags.TestTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.Map;

public class TestArmorMaterials {
    public static final ArmorMaterial AZURITE = new ArmorMaterial(1200,
            makeDefense(5, 7, 9, 5, 11), 16, SoundEvents.ARMOR_EQUIP_CHAIN, 2f, 0.1f, TestTags.Items.AZURITE_REPAIRABLE, TestEquipmentAssets.AZURITE
    );

    private static Map<ArmorType, Integer> makeDefense(int boots, int legs, int chest, int helm, int body) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body));
    }
}
