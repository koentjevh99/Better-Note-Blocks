package com.koen.betternoteblocks.registration;

import com.koen.betternoteblocks.betternoteblocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;
import java.util.Map;

// Maps block objects to items

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(ForgeRegistries.ITEMS, betternoteblocks.MODID);

    public static final RegistryObject<Item> BETTER_NOTE_BLOCK_ITEM = ITEMS.register("better_note_block",
            () -> new BlockItem(ModBlocks.BETTER_NOTE_BLOCK.get(), new Item.Properties()));

    public static final Map<DyeColor, RegistryObject<Item>> COLORED_NOTE_BLOCK_ITEMS = new EnumMap<>(DyeColor.class);

    public static void register(IEventBus eventBus) {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.BROWN) continue; 

            String name = color.getSerializedName() + "_note_block";
            
            COLORED_NOTE_BLOCK_ITEMS.put(color, ITEMS.register(name, 
                () -> new BlockItem(ModBlocks.COLORED_NOTE_BLOCKS.get(color).get(), new Item.Properties())));
        }

        ITEMS.register(eventBus);
    }
}