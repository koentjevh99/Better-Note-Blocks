package com.koen.betternoteblocks.datagen;

import com.koen.betternoteblocks.registration.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

//Generates Blocktag data

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, "betternoteblocks", existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        var axeTag = this.tag(BlockTags.MINEABLE_WITH_AXE);
        
        axeTag.add(ModBlocks.BETTER_NOTE_BLOCK.get());
        
        ModBlocks.COLORED_NOTE_BLOCKS.values().forEach(block -> axeTag.add(block.get()));
    }
}