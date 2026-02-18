package com.koen.betternoteblocks.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;


//Generates jsons for item models

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, "betternoteblocks", existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.BROWN) continue;
            
            String colorName = color.getName();
            String itemName = colorName + "_note_block";
            
            getBuilder(itemName)
                .parent(getExistingFile(new ResourceLocation("betternoteblocks", "block/" + colorName + "_note_0")))
                .override()
                    .predicate(new ResourceLocation("betternoteblocks", "instrument"), 1.0f)
                    .model(getExistingFile(new ResourceLocation("betternoteblocks", "block/rainbow_note_block")))
                .end();
        }

        getBuilder("better_note_block")
            .parent(getExistingFile(new ResourceLocation("betternoteblocks", "block/better_note_block_0")))
            .override()
                .predicate(new ResourceLocation("betternoteblocks", "instrument"), 1.0f)
                .model(getExistingFile(new ResourceLocation("betternoteblocks", "block/rainbow_note_block")))
            .end();
    }
}