package com.koen.betternoteblocks.datagen;

import com.koen.betternoteblocks.betternoteblocks;
import com.koen.betternoteblocks.registration.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Locale;

//Generates a language file

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, betternoteblocks.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.betternoteblocks", "Better Note Blocks");    //Creative tab name

        add(ModBlocks.BETTER_NOTE_BLOCK.get(), "Better Note Block");

        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.BROWN) continue;
            
            String name = color.getName().replace("_", " ");
            String capitalizedName = java.util.Arrays.stream(name.split(" "))
                    .map(word -> word.substring(0, 1).toUpperCase(Locale.ROOT) + word.substring(1))
                    .collect(java.util.stream.Collectors.joining(" ")); 
            
            add(ModBlocks.COLORED_NOTE_BLOCKS.get(color).get(), capitalizedName + " Note Block");
        }

        //Advancement Translations
        add("advancement.betternoteblocks.colorful_notes", "Colorful Notes!");
        add("advancement.betternoteblocks.colorful_notes.desc", "Obtain your first colored note block");

        add("advancement.betternoteblocks.full_palette", "The Full Palette");
        add("advancement.betternoteblocks.full_palette.desc", "Obtain every single Note Block color!");

        add("advancement.betternoteblocks.wait_go_back", "Wait, Go Back!");
        add("advancement.betternoteblocks.wait_go_back.desc", "Lower a note's pitch for the first time.");

        add("advancement.betternoteblocks.found_a_bug", "Hidden in Plain Sight");
        add("advancement.betternoteblocks.found_a_bug.desc", "Witness the note block transformation on a beacon.");

        add("advancement.betternoteblocks.groan_tube", "Yuck!");
        add("advancement.betternoteblocks.groan_tube.desc", "Play a groan tube sound using a slime block.");
    }
}