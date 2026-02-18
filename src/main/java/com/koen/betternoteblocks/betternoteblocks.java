package com.koen.betternoteblocks;

import com.koen.betternoteblocks.command.OverlayToggleCommand;
import com.koen.betternoteblocks.registration.*;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.nio.file.Path;

//Main mod class

@Mod(betternoteblocks.MODID)
public class betternoteblocks {
    public static final String MODID = "betternoteblocks";

    public betternoteblocks() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModSounds.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);

        modEventBus.addListener(this::onAddPackFinders);
        modEventBus.addListener(this::onClientSetup);

        MinecraftForge.EVENT_BUS.addListener(this::onCommandsRegister);
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            net.minecraft.client.renderer.item.ItemPropertyFunction rainbowGetter = (stack, level, entity, seed) -> {
                CompoundTag tag = stack.getTagElement("BlockStateTag");
                if (tag != null && tag.contains("instrument")) {
                    return "rainbow".equals(tag.getString("instrument")) ? 1.0f : 0.0f;
                }
                return 0.0f;
            };

            ItemProperties.register(ModItems.BETTER_NOTE_BLOCK_ITEM.get(), 
                new ResourceLocation(MODID, "instrument"), rainbowGetter);

            ModBlocks.COLORED_NOTE_BLOCKS.values().forEach(registryObject -> {
                ItemProperties.register(registryObject.get().asItem(), 
                    new ResourceLocation(MODID, "instrument"), rainbowGetter);
            });
        });
    }

    private void onCommandsRegister(RegisterCommandsEvent event) {
        OverlayToggleCommand.register(event.getDispatcher());
    }

    private void onAddPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            Path resourcePath = ModList.get().getModFileById(MODID).getFile().findResource("resourcepacks/note_block_overlay");
            
            event.addRepositorySource((consumer) -> {
                Pack pack = Pack.readMetaAndCreate(
                    "betternoteblocks:note_block_overlay",
                    Component.literal("Better Note Blocks Overlay"),
                    false,
                    (id) -> new PathPackResources(id, resourcePath, false),
                    PackType.CLIENT_RESOURCES,
                    Pack.Position.TOP,
                    PackSource.BUILT_IN
                );
                if (pack != null) consumer.accept(pack);
            });
        }
    }
}