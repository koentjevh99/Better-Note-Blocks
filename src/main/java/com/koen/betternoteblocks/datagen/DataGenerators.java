package com.koen.betternoteblocks.datagen;

import com.koen.betternoteblocks.betternoteblocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.concurrent.CompletableFuture;

//Master file to generate data

@Mod.EventBusSubscriber(modid = betternoteblocks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        if (event.includeServer()) {
            ModBlockTagProvider blockTags = generator.addProvider(true,
                    new ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper));
            
            generator.addProvider(true,
                    new ModItemTagProvider(packOutput, lookupProvider, blockTags.contentsGetter(), existingFileHelper));

            generator.addProvider(true, new ModRecipeProvider(packOutput));

            generator.addProvider(true, ModLootTableProvider.create(packOutput));

            generator.addProvider(true, new ForgeAdvancementProvider(packOutput, lookupProvider, existingFileHelper,
                    List.of(new ModAdvancementProvider())));
        }

        if (event.includeClient()) {
            generator.addProvider(true, new ModBlockStateProvider(packOutput, existingFileHelper));
            generator.addProvider(true, new ModItemModelProvider(packOutput, existingFileHelper));
            generator.addProvider(true, new ModLanguageProvider(packOutput));
        }
    }
}