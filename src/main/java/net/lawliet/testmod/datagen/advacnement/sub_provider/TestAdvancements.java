package net.lawliet.testmod.datagen.advacnement.sub_provider;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.registries.TestBlocks;
import net.lawliet.testmod.registries.TestItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.LocationPredicate;
import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.advancements.triggers.ItemUsedOnLocationTrigger;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.predicates.AllOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;

import java.util.Optional;

public class TestAdvancements extends AdvancementSubProvider {

    public TestAdvancements(BootstrapContext<Advancement> output) {
        super(output);
    }

    @Override
    public void generate() {
        var items = output.lookup(Registries.ITEM);
        var blocks = output.lookup(Registries.BLOCK);

        AdvancementHolder root = Advancement.Builder.advancement()
                .rootDisplay(
                        TestItems.AZURITE.get(),
                        Component.translatable("advancement.testmod.root.title"),
                        Component.translatable("advancement.testmod.root.description"),
                        Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("has_azurite", InventoryChangeTrigger.TriggerInstance.hasItems(TestItems.AZURITE))
                .save(output, getSavePath("root"));
        AdvancementHolder plantSeed = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        TestItems.RICE.get(),
                        Component.translatable("advancement.testmod.plant_custom.title"),
                        Component.translatable("advancement.testmod.plant_custom.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("berries", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(blocks, TestBlocks.GOJI_BERRY_BUSH.get()))
                .addCriterion("rice", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(blocks, TestBlocks.RICE.get()))
                .addCriterion("onion", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(blocks, TestBlocks.ONION.get()))
                .save(output, getSavePath("plant_custom"));
        AdvancementHolder metalDetector = Advancement.Builder.advancement()
                .parent(plantSeed)
                .display(
                        TestItems.METAL_DETECTOR.get(),
                        Component.translatable("advancement.testmod.metal_detector.title"),
                        Component.translatable("advancement.testmod.metal_detector.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("metal_detector",
                        CriteriaTriggers.ITEM_USED_ON_BLOCK.createCriterion(new ItemUsedOnLocationTrigger.TriggerInstance(Optional.empty(), Optional.of(
                                Holder.direct(
                                        AllOfCondition.allOf(
                                        LocationCheck.checkLocation(LocationPredicate.Builder.location().setCanSeeSky(true), new BlockPos(0, 1, 0)),
                                        MatchTool.toolMatches(ItemPredicate.Builder.item().of(items, TestItems.METAL_DETECTOR))
                                ).build())))))
                .save(output, getSavePath("metal_detector"));
    }

    private Identifier getSavePath(String group, String name) {
        return Identifier.fromNamespaceAndPath(TestMod.MODID, "%s/%s".formatted(group, name));
    }

    private Identifier getSavePath(String name) {
        return getSavePath(TestMod.MODID, name);
    }

}

