package com.koen.betternoteblocks.datagen;

import com.koen.betternoteblocks.registration.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

//Generates Recipes

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.BROWN) continue;

            if (ModBlocks.COLORED_NOTE_BLOCKS.containsKey(color)) {
                var currentBlock = ModBlocks.COLORED_NOTE_BLOCKS.get(color).get();
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, currentBlock)
                        .requires(Blocks.NOTE_BLOCK)
                        .requires(DyeItem.byColor(color))
                        .unlockedBy("has_noteblock", has(Blocks.NOTE_BLOCK))
                        .save(writer);

                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, currentBlock)
                        .requires(ModItemTagProvider.COLORED_NOTE_BLOCKS_TAG) 
                        .requires(DyeItem.byColor(color))
                        .unlockedBy("has_colored_noteblock", has(ModItemTagProvider.COLORED_NOTE_BLOCKS_TAG))
                        .save(writer, ResourceLocation.fromNamespaceAndPath("betternoteblocks", color.getName() + "_from_recoloring"));

                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.NOTE_BLOCK)
                        .requires(currentBlock)
                        .unlockedBy("has_" + color.getName() + "_noteblock", has(currentBlock))
                        .save(writer, ResourceLocation.fromNamespaceAndPath("betternoteblocks", "vanilla_noteblock_from_" + color.getName()));
            }
        }
    }
}