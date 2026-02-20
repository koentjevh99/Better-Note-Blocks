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
import net.minecraft.world.item.crafting.Ingredient;
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
                var baseNoteBlocks = Ingredient.of(Blocks.NOTE_BLOCK, ModBlocks.BETTER_NOTE_BLOCK.get());

                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, currentBlock)
                        .requires(baseNoteBlocks)
                        .requires(DyeItem.byColor(color))
                        .unlockedBy("has_noteblock", has(Blocks.NOTE_BLOCK))
                        .unlockedBy("has_better_noteblock", has(ModBlocks.BETTER_NOTE_BLOCK.get()))
                        .save(writer, new ResourceLocation("betternoteblocks", color.getName() + "_from_base"));

                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, currentBlock)
                        .requires(ModItemTagProvider.COLORED_NOTE_BLOCKS_TAG)
                        .requires(DyeItem.byColor(color))
                        .unlockedBy("has_colored_noteblock", has(ModItemTagProvider.COLORED_NOTE_BLOCKS_TAG))
                        .save(writer, ResourceLocation.fromNamespaceAndPath("betternoteblocks", color.getName() + "_from_recoloring"));

                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.BETTER_NOTE_BLOCK.get())
                        .requires(currentBlock)
                        .unlockedBy("has_" + color.getName() + "_noteblock", has(currentBlock))
                        .save(writer, new ResourceLocation("betternoteblocks", "better_note_block_from_" + color.getName()));
            }
        }
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.BETTER_NOTE_BLOCK.get())
                .requires(Blocks.NOTE_BLOCK)
                .unlockedBy("has_noteblock", has(Blocks.NOTE_BLOCK))
                .save(writer, new ResourceLocation("betternoteblocks", "note_block_to_better_note_block"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.NOTE_BLOCK)
                .requires(ModBlocks.BETTER_NOTE_BLOCK.get())
                .unlockedBy("has_better_note_block", has(ModBlocks.BETTER_NOTE_BLOCK.get()))
                .save(writer, new ResourceLocation("betternoteblocks", "better_note_block_to_note_block"));
    }
}