package com.koen.betternoteblocks.datagen;

import com.koen.betternoteblocks.betternoteblocks;
import com.koen.betternoteblocks.blocks.BetterNoteBlock;
import com.koen.betternoteblocks.registration.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

//Blockstate data generator

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, betternoteblocks.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.BROWN) continue; 
            
            Block block = ModBlocks.COLORED_NOTE_BLOCKS.get(color).get();
            String colorName = color.getSerializedName();
            
            customNoteBlock(block, colorName);
        }
        customVanillaNoteBlock(Blocks.NOTE_BLOCK);

        customBetterNoteBlock(ModBlocks.BETTER_NOTE_BLOCK.get());
    }

    private void customBetterNoteBlock(Block block) {
        getVariantBuilder(block).forAllStates(state -> {
            int note = state.getValue(BetterNoteBlock.NOTE);
            var inst = state.getValue(BetterNoteBlock.INSTRUMENT);
            
            if (inst == com.koen.betternoteblocks.blocks.BetterInstruments.RAINBOW) {
                return ConfiguredModel.builder()
                    .modelFile(models().getExistingFile(modLoc("block/rainbow_note_block")))
                    .build();
            }

            return ConfiguredModel.builder()
                .modelFile(models().withExistingParent("better_note_block_" + note, 
                    modLoc("block/layered_note_block"))
                    .texture("base", modLoc("block/better_note_block")) 
                    .texture("note", modLoc("block/note/light/note_" + note)))
                .build();
        });
    }


    private void customVanillaNoteBlock(Block block) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            int note = state.getValue(NoteBlock.NOTE);
            
            return ConfiguredModel.builder()
                .modelFile(models().withExistingParent("note_block_" + note, 
                    modLoc("block/layered_note_block"))
                    .texture("base", "minecraft:block/note_block") 
                    .texture("note", modLoc("block/note/light/note_" + note)))
                .build();
        }, NoteBlock.INSTRUMENT, NoteBlock.POWERED);
    }


    private void customNoteBlock(Block block, String colorName) {
        getVariantBuilder(block).forAllStates(state -> {
            int note = state.getValue(BetterNoteBlock.NOTE);
            var inst = state.getValue(BetterNoteBlock.INSTRUMENT);

            if (inst == com.koen.betternoteblocks.blocks.BetterInstruments.RAINBOW) {
                return ConfiguredModel.builder()
                    .modelFile(models().getExistingFile(modLoc("block/rainbow_note_block")))
                    .build();
            }

            String folder = (colorName.equals("light_gray") || colorName.equals("white")) ? "dark" : "light";
            if (colorName.equals("light_gray")) folder = "white"; 

            return ConfiguredModel.builder()
                .modelFile(models().withExistingParent(colorName + "_note_" + note, 
                    modLoc("block/layered_note_block"))
                    .texture("base", modLoc("block/" + colorName + "_note_block"))
                    .texture("note", modLoc("block/note/" + folder + "/note_" + note)))
                .build();
        });
    }
}