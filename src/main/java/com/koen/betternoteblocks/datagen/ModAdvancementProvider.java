package com.koen.betternoteblocks.datagen;

import com.koen.betternoteblocks.registration.ModBlocks;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

//Achievements Generator

public class ModAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        //Colorful Notes!
        Advancement colorfulNotes = Advancement.Builder.advancement()
                .display(new DisplayInfo(
                        new ItemStack(ModBlocks.COLORED_NOTE_BLOCKS.get(net.minecraft.world.item.DyeColor.RED).get()),
                        Component.translatable("advancement.betternoteblocks.colorful_notes"), // Use Translatable for Lang files!
                        Component.translatable("advancement.betternoteblocks.colorful_notes.desc"),
                        new ResourceLocation("minecraft", "textures/block/stone_bricks.png"),
                        FrameType.TASK, true, true, false))
                .addCriterion("has_colored_noteblock", InventoryChangeTrigger.TriggerInstance.hasItems(
                        net.minecraft.advancements.critereon.ItemPredicate.Builder.item()
                                .of(ModItemTagProvider.COLORED_NOTE_BLOCKS_TAG).build()))
                .save(saver, new ResourceLocation("betternoteblocks", "colorful_notes"), existingFileHelper);

        //The Full Palette
        Advancement fullPalette = Advancement.Builder.advancement()
                .parent(colorfulNotes)
                .display(new DisplayInfo(
                        new ItemStack(net.minecraft.world.item.Items.PAINTING),
                        Component.translatable("advancement.betternoteblocks.full_palette"),
                        Component.translatable("advancement.betternoteblocks.full_palette.desc"),
                        null, FrameType.CHALLENGE, true, true, false))
                .addCriterion("has_all_colors", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ModBlocks.COLORED_NOTE_BLOCKS.values().stream().map(b -> b.get().asItem()).toArray(net.minecraft.world.item.Item[]::new)))
                .save(saver, new ResourceLocation("betternoteblocks", "full_palette"), existingFileHelper);

        //Wait, Go Back!
        Advancement waitGoBack = Advancement.Builder.advancement()
                .parent(colorfulNotes)
                .display(new DisplayInfo(
                        new ItemStack(net.minecraft.world.item.Items.RECOVERY_COMPASS),
                        Component.translatable("advancement.betternoteblocks.wait_go_back"),
                        Component.translatable("advancement.betternoteblocks.wait_go_back.desc"),
                        null, FrameType.TASK, true, true, false))
                .addCriterion("shifted_note", new net.minecraft.advancements.critereon.ImpossibleTrigger.TriggerInstance())
                .save(saver, new ResourceLocation("betternoteblocks", "wait_go_back"), existingFileHelper);

        //I FOUND A BUG!
        Advancement foundABug = Advancement.Builder.advancement()
                .parent(fullPalette) 
                .display(new DisplayInfo(
                        new ItemStack(net.minecraft.world.level.block.Blocks.BEACON),
                        Component.translatable("advancement.betternoteblocks.found_a_bug"),
                        Component.translatable("advancement.betternoteblocks.found_a_bug.desc"),
                        null, 
                        FrameType.CHALLENGE, 
                        true,  // showToast
                        true,  // announceToChat
                        true   // hidden
                ))
                .addCriterion("has_rainbow_block", new net.minecraft.advancements.critereon.ImpossibleTrigger.TriggerInstance())
                .save(saver, new ResourceLocation("betternoteblocks", "found_a_bug"), existingFileHelper);

        //Yuck!    
        Advancement groanTube = Advancement.Builder.advancement()
            .parent(colorfulNotes) // <--- Set this to your Root advancement (e.g., colorfulNotes)
            .display(new DisplayInfo(
                new ItemStack(net.minecraft.world.item.Items.SLIME_BALL),
                Component.translatable("advancement.betternoteblocks.groan_tube"),
                Component.translatable("advancement.betternoteblocks.groan_tube.desc"),
                null, // Keep this null!
                FrameType.TASK, 
                true, 
                true, 
                true // Keeps it hidden until found
            ))
            .addCriterion("played_groan_tube", new net.minecraft.advancements.critereon.ImpossibleTrigger.TriggerInstance())
            .save(saver, new ResourceLocation("betternoteblocks", "groan_tube"), existingFileHelper);
    }
}