package com.koen.betternoteblocks.datagen;

import com.koen.betternoteblocks.registration.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

//Generates the loot table jsons

public class ModLootTableProvider {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(BlocksLoot::new, LootContextParamSets.BLOCK)
        ));
    }

    public static class BlocksLoot extends BlockLootSubProvider {
        protected BlocksLoot() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            ModBlocks.COLORED_NOTE_BLOCKS.values().forEach(block -> this.dropSelf(block.get()));

            this.dropSelf(ModBlocks.BETTER_NOTE_BLOCK.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream()
                .map(net.minecraftforge.registries.RegistryObject::get)::iterator;
        }
    }
}