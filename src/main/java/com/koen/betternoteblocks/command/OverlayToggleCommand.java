package com.koen.betternoteblocks.command;

import com.koen.betternoteblocks.registration.ModCreativeTabs;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = "betternoteblocks", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OverlayToggleCommand {

    public static CreativeModeTab SELECTED_TAB;
    private static final Map<Block, InstrumentInfo> INSTRUMENT_CACHE = new HashMap<>();

    static {
        // Basic
        INSTRUMENT_CACHE.put(Blocks.AMETHYST_BLOCK, new InstrumentInfo("Music Box", "F#4 - F#6"));
        INSTRUMENT_CACHE.put(Blocks.REDSTONE_ORE, new InstrumentInfo("Electric Guitar", "F#3 - F#5"));
        INSTRUMENT_CACHE.put(Blocks.BLACK_STAINED_GLASS, new InstrumentInfo("Hi-Hat", "F#3 - F#5"));
        INSTRUMENT_CACHE.put(Blocks.OBSIDIAN, new InstrumentInfo("Synth Stab", "F#3 - F#5"));
        INSTRUMENT_CACHE.put(Blocks.DEEPSLATE, new InstrumentInfo("Kick Stomp", "F#2 - F#4"));
        INSTRUMENT_CACHE.put(Blocks.GILDED_BLACKSTONE, new InstrumentInfo("Orchestral Hit", "F#4 - F#6"));
        INSTRUMENT_CACHE.put(Blocks.NETHERITE_BLOCK, new InstrumentInfo("Metal Pipe", "F#4 - F#6"));
        INSTRUMENT_CACHE.put(Blocks.SANDSTONE, new InstrumentInfo("Brawl Kick", "F#2 - F#4"));
        INSTRUMENT_CACHE.put(Blocks.RAW_COPPER_BLOCK, new InstrumentInfo("Crash", "F#1 - F#3"));
        INSTRUMENT_CACHE.put(Blocks.BEEHIVE, new InstrumentInfo("Acoustic Tom", "F#1 - F#3"));
        INSTRUMENT_CACHE.put(Blocks.CAULDRON, new InstrumentInfo("Holy Choir", "F#4 - F#6"));
        INSTRUMENT_CACHE.put(Blocks.COMPOSTER, new InstrumentInfo("Wood Block", "F#2 - F#4"));
        INSTRUMENT_CACHE.put(Blocks.POLISHED_BASALT, new InstrumentInfo("Hardstyle Kick", "F#1 - F#3"));
        INSTRUMENT_CACHE.put(Blocks.DIAMOND_BLOCK, new InstrumentInfo("Triangle", "F#6 - F#8"));
        INSTRUMENT_CACHE.put(Blocks.REINFORCED_DEEPSLATE, new InstrumentInfo("Synth Bass", "F#0 - F#2"));
        INSTRUMENT_CACHE.put(Blocks.LODESTONE, new InstrumentInfo("Timpani", "F#1 - F#3"));
        INSTRUMENT_CACHE.put(Blocks.CHISELED_STONE_BRICKS, new InstrumentInfo("Aztek Psy Drum", "F#3 - F#5"));
        INSTRUMENT_CACHE.put(Blocks.WET_SPONGE, new InstrumentInfo("Bloop Noise", "F#3 - F#5"));
        INSTRUMENT_CACHE.put(Blocks.RED_SAND, new InstrumentInfo("Snare", "F#3 - F#5"));
        INSTRUMENT_CACHE.put(Blocks.SEA_LANTERN, new InstrumentInfo("Kalimba", "F#2 - F#4"));
        INSTRUMENT_CACHE.put(Blocks.CALCITE, new InstrumentInfo("Finger Snap", "F#5 - F#7"));
        INSTRUMENT_CACHE.put(Blocks.GRAVEL, new InstrumentInfo("Classic Clap", "F#3 - F#5"));
        INSTRUMENT_CACHE.put(Blocks.OCHRE_FROGLIGHT, new InstrumentInfo("Bongo", "F#2 - F#4"));
        INSTRUMENT_CACHE.put(Blocks.LOOM, new InstrumentInfo("Afro Harp", "F#2 - F#4"));
        INSTRUMENT_CACHE.put(Blocks.WHITE_GLAZED_TERRACOTTA, new InstrumentInfo("Phonk Cowbell Crystal", "F#4 - F#6"));
        INSTRUMENT_CACHE.put(Blocks.BREWING_STAND, new InstrumentInfo("Tambourine", "F#6 - F#8"));
        INSTRUMENT_CACHE.put(Blocks.RAW_GOLD_BLOCK, new InstrumentInfo("Gong", "F#2 - F#4"));

        // Sustained Sounds
        INSTRUMENT_CACHE.put(Blocks.RED_CONCRETE, new InstrumentInfo("Sawtooth Synth", "F#-1 - F#9"));
        INSTRUMENT_CACHE.put(Blocks.ORANGE_CONCRETE, new InstrumentInfo("Electone Trombone", "F#-1 - F#9"));
        INSTRUMENT_CACHE.put(Blocks.YELLOW_CONCRETE, new InstrumentInfo("GB_SQR Synth", "F#-1 - F#9"));
        INSTRUMENT_CACHE.put(Blocks.LIME_CONCRETE, new InstrumentInfo("GB_SAW Synth", "F#-1 - F#9"));
    }

    @SubscribeEvent
    public static void onRegisterClientCommands(RegisterClientCommandsEvent event) {
        register(event.getDispatcher());
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("togglenoteblockoverlay")
                .executes(context -> {
                    Minecraft mc = Minecraft.getInstance();

                    if (mc.level != null) {
                        PackRepository repo = mc.getResourcePackRepository();
                        List<String> selectedPacks = new ArrayList<>(repo.getSelectedIds());
                        String packId = "betternoteblocks:note_block_overlay";

                        boolean enabled;
                        if (selectedPacks.contains(packId)) {
                            selectedPacks.remove(packId);
                            enabled = false;
                        } else {
                            selectedPacks.add(packId);
                            enabled = true;
                        }

                        repo.setSelected(selectedPacks);
                        mc.reloadResourcePacks();

                        mc.gui.getChat().addMessage(Component.literal("Note Block Overlay: " + (enabled ? "Enabled" : "Disabled")));
                    }

                    return 1;
                })
        );
    }

    private static java.lang.reflect.Field CACHED_TAB_FIELD = null;

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (!(mc.screen instanceof CreativeModeInventoryScreen gui)) return;

        try {
            if (CACHED_TAB_FIELD == null) {
                for (java.lang.reflect.Field field : CreativeModeInventoryScreen.class.getDeclaredFields()) {
                    if (field.getType() == CreativeModeTab.class) {
                        field.setAccessible(true);
                        CACHED_TAB_FIELD = field;
                        break;
                    }
                }
            }

            if (CACHED_TAB_FIELD != null) {
                CreativeModeTab selectedTab = (CreativeModeTab) CACHED_TAB_FIELD.get(gui);

                if (selectedTab == ModCreativeTabs.NOTE_BLOCK_TAB.get()) {
                    Block block = Block.byItem(event.getItemStack().getItem());
                    InstrumentInfo info = getInstrumentForBlock(block);

                    if (info != null) {
                        event.getToolTip().add(Component.empty());
                        event.getToolTip().add(Component.literal("Instrument: ").withStyle(ChatFormatting.GRAY)
                                .append(Component.literal(info.name()).withStyle(ChatFormatting.YELLOW)));
                        event.getToolTip().add(Component.literal("Range: ").withStyle(ChatFormatting.GRAY)
                                .append(Component.literal(info.range()).withStyle(ChatFormatting.AQUA)));
                    }
                }
            }
        } catch (Exception e) {
            // Crash prevention :P
        }
    }

    private static InstrumentInfo getInstrumentForBlock(Block block) {
        // Optimized Lookup
        InstrumentInfo cached = INSTRUMENT_CACHE.get(block);
        if (cached != null) return cached;

        // Modded Check (Fallthrough for non-vanilla)
        ResourceLocation registryName = ForgeRegistries.BLOCKS.getKey(block);
        if (registryName != null && registryName.toString().equals("ba:baba")) {
            return new InstrumentInfo("Ba", "BA#2 - BA#4");
        }

        return null;
    }

    public record InstrumentInfo(String name, String range) {
    }
}