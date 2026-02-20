package com.koen.betternoteblocks.registration;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

//Creative tab logic

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "betternoteblocks");

    public static final RegistryObject<CreativeModeTab> NOTE_BLOCK_TAB = TABS.register("note_block_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.betternoteblocks"))
            .icon(() -> new ItemStack(Blocks.NOTE_BLOCK))
            .displayItems((params, output) -> {
                DyeColor[] vanillaSearchOrder = { DyeColor.WHITE, DyeColor.LIGHT_GRAY, DyeColor.GRAY, DyeColor.BLACK };
                DyeColor[] remainingColors = {
                    DyeColor.RED, DyeColor.ORANGE, DyeColor.YELLOW, DyeColor.LIME, 
                    DyeColor.GREEN, DyeColor.CYAN, DyeColor.LIGHT_BLUE, DyeColor.BLUE, 
                    DyeColor.PURPLE, DyeColor.MAGENTA, DyeColor.PINK, DyeColor.BROWN
                };

                for (DyeColor color : vanillaSearchOrder) {
                    if (ModBlocks.COLORED_NOTE_BLOCKS.containsKey(color)) {
                        output.accept(ModBlocks.COLORED_NOTE_BLOCKS.get(color).get());
                    }
                }

                output.accept(ModBlocks.BETTER_NOTE_BLOCK.get());

                for (DyeColor color : remainingColors) {
                    if (ModBlocks.COLORED_NOTE_BLOCKS.containsKey(color)) {
                        output.accept(ModBlocks.COLORED_NOTE_BLOCKS.get(color).get());
                    }
                }

                output.accept(Blocks.AMETHYST_BLOCK);      // Music Box
                output.accept(Blocks.REDSTONE_ORE);       // Elec Guitar
                output.accept(Blocks.BLACK_STAINED_GLASS);// Hi-Hat
                output.accept(Blocks.OBSIDIAN);           // Synth Stab
                output.accept(Blocks.DEEPSLATE);          // Kick Stomp
                output.accept(Blocks.GILDED_BLACKSTONE);   // Orchestral Hit
                output.accept(Blocks.NETHERITE_BLOCK);     // Metal Pipe
                output.accept(Blocks.SANDSTONE);          // Brawl Kick
                output.accept(Blocks.RAW_COPPER_BLOCK);    // Crash
                output.accept(Blocks.BEEHIVE);            // Acoustic Tom
                output.accept(Blocks.CAULDRON);           // Holy Choir
                output.accept(Blocks.COMPOSTER);          // Wood Block
                output.accept(Blocks.POLISHED_BASALT);    // Hardstyle Kick
                output.accept(Blocks.DIAMOND_BLOCK);       // Triangle
                output.accept(Blocks.REINFORCED_DEEPSLATE);// Synth Bass
                output.accept(Blocks.LODESTONE);          // Timpani
                output.accept(Blocks.CHISELED_STONE_BRICKS);// Psy Drum
                output.accept(Blocks.WET_SPONGE);         // Bloop Noise
                output.accept(Blocks.RED_SAND);           // Snare
                output.accept(Blocks.SEA_LANTERN);            // Kalimba
                output.accept(Blocks.CALCITE);            // Finger Snap
                output.accept(Blocks.GRAVEL);            // Classic Clap
                output.accept(Blocks.OCHRE_FROGLIGHT);            // Bongo
                output.accept(Blocks.LOOM);            // Afro Harp
                output.accept(Blocks.WHITE_GLAZED_TERRACOTTA);            // Phonk Cowbell Crystal
                output.accept(Blocks.BREWING_STAND);            // Tambourine
                output.accept(Blocks.RAW_GOLD_BLOCK);            // Gong
                output.accept(Blocks.POLISHED_DEEPSLATE);            // Hard Punchy Kick
                output.accept(Blocks.BEACON);            // EE
                output.accept(Blocks.RED_CONCRETE);            // Sawtooth synth
                output.accept(Blocks.ORANGE_CONCRETE);            // ym electone trombone
                output.accept(Blocks.YELLOW_CONCRETE);            // GB_SQR synth
                output.accept(Blocks.LIME_CONCRETE);            // GB-SAW synth
                output.accept(Blocks.GREEN_CONCRETE);            // Saxophone Synth

                Block babaBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("ba", "baba"));
                if (babaBlock != null && babaBlock != Blocks.AIR) {
                    output.accept(babaBlock);
                }
            })
            .build());

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}