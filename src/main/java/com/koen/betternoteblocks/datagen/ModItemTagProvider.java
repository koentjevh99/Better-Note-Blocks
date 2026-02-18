package com.koen.betternoteblocks.datagen;

import com.koen.betternoteblocks.betternoteblocks;
import com.koen.betternoteblocks.registration.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

//Generator for item tags jsons

public class ModItemTagProvider extends ItemTagsProvider {
    public static final TagKey<Item> COLORED_NOTE_BLOCKS_TAG = ItemTags.create(new ResourceLocation("betternoteblocks", "colored_note_blocks"));

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<net.minecraft.world.level.block.Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, "betternoteblocks", existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        var tag = this.tag(COLORED_NOTE_BLOCKS_TAG);
        ModBlocks.COLORED_NOTE_BLOCKS.values().forEach(block -> tag.add(block.get().asItem()));
    }
}