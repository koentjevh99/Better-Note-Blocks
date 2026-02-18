package com.koen.betternoteblocks.registration;

import com.koen.betternoteblocks.blocks.BetterNoteBlock;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

// Registers custom note blocks

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "betternoteblocks");

    public static final Map<DyeColor, RegistryObject<Block>> COLORED_NOTE_BLOCKS = new HashMap<>();

    public static final RegistryObject<Block> BETTER_NOTE_BLOCK = BLOCKS.register("better_note_block",
        () -> new BetterNoteBlock(BlockBehaviour.Properties.copy(Blocks.NOTE_BLOCK)));

    static {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.BROWN) continue;
            COLORED_NOTE_BLOCKS.put(color, BLOCKS.register(color.getSerializedName() + "_note_block",
                () -> new BetterNoteBlock(BlockBehaviour.Properties.copy(Blocks.NOTE_BLOCK))));
        }
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
} 