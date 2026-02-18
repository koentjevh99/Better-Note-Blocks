package com.koen.betternoteblocks.registration;

import com.koen.betternoteblocks.betternoteblocks;
import com.koen.betternoteblocks.blocks.BetterNoteBlockEntity;
import com.koen.betternoteblocks.registration.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// Registers custom block entities

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = 
        DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, betternoteblocks.MODID);

    public static final RegistryObject<BlockEntityType<BetterNoteBlockEntity>> BETTER_NOTE_BLOCK = 
        BLOCK_ENTITIES.register("better_note_block_entity", () -> 
            BlockEntityType.Builder.of(BetterNoteBlockEntity::new, 
                ModBlocks.BETTER_NOTE_BLOCK.get()).build(null));
}