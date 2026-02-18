package com.koen.betternoteblocks.datagen;

import com.koen.betternoteblocks.registration.ModBlocks;
import com.koen.betternoteblocks.blocks.BetterNoteBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyBlockState;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

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

            this.add(ModBlocks.BETTER_NOTE_BLOCK.get(), createMamaSupremeTable());
        }

        private net.minecraft.world.level.storage.loot.LootTable.Builder createMamaSupremeTable() {
            return LootTable.lootTable().withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(LootItem.lootTableItem(ModBlocks.BETTER_NOTE_BLOCK.get())
                        .when(HAS_SILK_TOUCH)
                        .apply(CopyBlockState.copyState(ModBlocks.BETTER_NOTE_BLOCK.get())
                            .copy(BetterNoteBlock.INSTRUMENT))
                        .otherwise(LootItem.lootTableItem(Blocks.NOTE_BLOCK))
                    )
            );
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream()
                .map(net.minecraftforge.registries.RegistryObject::get)::iterator;
        }
    }
}