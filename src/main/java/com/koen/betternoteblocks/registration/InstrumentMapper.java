package com.koen.betternoteblocks.registration;

import com.koen.betternoteblocks.blocks.BetterInstruments;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraftforge.registries.ForgeRegistries;

//Mapper to set instruments 

public class InstrumentMapper {


    public static BetterInstruments getInstrumentForBlock(BlockState stateBelow) {
        if (stateBelow.is(Blocks.AMETHYST_BLOCK)) return BetterInstruments.BETTER_MUSIC_BOX;
        if (stateBelow.is(Blocks.REDSTONE_ORE)) return BetterInstruments.ELEC_GUITAR;
        if (stateBelow.is(Blocks.BLACK_STAINED_GLASS)) return BetterInstruments.HI_HAT;
        if (stateBelow.is(Blocks.OBSIDIAN)) return BetterInstruments.SYNTH_STAB;
        if (stateBelow.is(Blocks.DEEPSLATE)) return BetterInstruments.KICK_STOMP;
        if (stateBelow.is(Blocks.GILDED_BLACKSTONE)) return BetterInstruments.ORCHESTRAL_HIT;
        if (stateBelow.is(Blocks.NETHERITE_BLOCK)) return BetterInstruments.METAL_PIPE;
        if (stateBelow.is(Blocks.SANDSTONE)) return BetterInstruments.BRAWL_KICK;
        if (stateBelow.is(Blocks.RAW_COPPER_BLOCK)) return BetterInstruments.CRASH;
        if (stateBelow.is(Blocks.BEEHIVE)) return BetterInstruments.ACOUSTIC_TOM;
        if (stateBelow.is(Blocks.CAULDRON)) return BetterInstruments.HOLY_CHOIR;
        if (stateBelow.is(Blocks.COMPOSTER)) return BetterInstruments.WOOD_BLOCK;
        if (stateBelow.is(Blocks.POLISHED_BASALT)) return BetterInstruments.HARDSTYLE_KICK_BUZZED;
        if (stateBelow.is(Blocks.DIAMOND_BLOCK)) return BetterInstruments.TRIANGLE;
        if (stateBelow.is(Blocks.REINFORCED_DEEPSLATE)) return BetterInstruments.BETTER_SYNTH_BASS;
        if (stateBelow.is(Blocks.LODESTONE)) return BetterInstruments.BETTER_TIMPANI;
        if (stateBelow.is(Blocks.CHISELED_STONE_BRICKS)) return BetterInstruments.PSY_DRUM_HIT_AZTEK;
        if (stateBelow.is(Blocks.WET_SPONGE)) return BetterInstruments.BLOOP_NOISE;
        if (stateBelow.is(Blocks.RED_SAND)) return BetterInstruments.DRY_SNARE;
        if (stateBelow.is(Blocks.SEA_LANTERN)) return BetterInstruments.KALIMBA;
        if (stateBelow.is(Blocks.LOOM)) return BetterInstruments.AFRO_HARP;
        if (stateBelow.is(Blocks.WHITE_GLAZED_TERRACOTTA)) return BetterInstruments.PHONK_COWBELL_CRYSTAL;
        if (stateBelow.is(Blocks.CALCITE)) return BetterInstruments.DRY_FINGER_SNAP;
        if (stateBelow.is(Blocks.OCHRE_FROGLIGHT)) return BetterInstruments.BONGO;
        if (stateBelow.is(Blocks.GRAVEL)) return BetterInstruments.CLASSIC_CLAP;
        if (stateBelow.is(Blocks.BREWING_STAND)) return BetterInstruments.TAMBOURINE;
        if (stateBelow.is(Blocks.SLIME_BLOCK)) return BetterInstruments.GROAN_TUBE;
        if (stateBelow.is(Blocks.RAW_GOLD_BLOCK)) return BetterInstruments.GONG;

        // Sustained Sounds (synth)
        if (stateBelow.is(Blocks.RED_CONCRETE)) return BetterInstruments.FLANGED_SAWTOOTH;
        if (stateBelow.is(Blocks.ORANGE_CONCRETE)) return BetterInstruments.YM_ELECTONE_TROMBONE;
        if (stateBelow.is(Blocks.YELLOW_CONCRETE)) return BetterInstruments.GB_SQR;
        if (stateBelow.is(Blocks.LIME_CONCRETE)) return BetterInstruments.GB_SAW;


        //modded blocks
        ResourceLocation registryName = ForgeRegistries.BLOCKS.getKey(stateBelow.getBlock());
        if (registryName != null && registryName.toString().equals("ba:baba")) {
            return BetterInstruments.BA;
        }

        NoteBlockInstrument vanillaEnum = stateBelow.instrument();
        
        for (BetterInstruments better : BetterInstruments.values()) {
            if (better.getSerializedName().equalsIgnoreCase(vanillaEnum.getSerializedName())) {
                return better;
            }
        }

        return BetterInstruments.HARP; 
    }


    public static SoundEvent getSoundForInstrument(BetterInstruments instrument, BlockState stateBelow, Level level, BlockPos pos, int note) {

        if (instrument.isSustained()) {
            return getSustainedSound(instrument, level, pos, note);
        }

        return switch (instrument) {
            case BETTER_MUSIC_BOX -> ModSounds.BETTER_MUSIC_BOX.get();
            case ELEC_GUITAR -> ModSounds.ELEC_GUITAR.get();
            case HI_HAT -> ModSounds.HI_HAT.get();
            case SYNTH_STAB -> ModSounds.SYNTH_STAB.get();
            case KICK_STOMP -> ModSounds.KICK_STOMP.get();
            case ORCHESTRAL_HIT -> ModSounds.ORCHESTRAL_HIT.get();
            case METAL_PIPE -> ModSounds.METAL_PIPE.get();
            case BRAWL_KICK -> ModSounds.BRAWL_KICK.get();
            case CRASH -> ModSounds.CRASH.get();
            case ACOUSTIC_TOM -> ModSounds.ACOUSTIC_TOM.get();
            case HOLY_CHOIR -> ModSounds.HOLY_CHOIR.get();
            case WOOD_BLOCK -> ModSounds.WOOD_BLOCK.get();
            case HARDSTYLE_KICK_BUZZED -> ModSounds.HARDSTYLE_KICK_BUZZED.get();
            case TRIANGLE -> ModSounds.TRIANGLE.get();
            case BETTER_SYNTH_BASS -> ModSounds.BETTER_SYNTH_BASS.get();
            case BETTER_TIMPANI -> ModSounds.BETTER_TIMPANI.get();
            case PSY_DRUM_HIT_AZTEK -> ModSounds.PSY_DRUM_HIT_AZTEK.get();
            case BLOOP_NOISE -> ModSounds.BLOOP_NOISE.get();
            case DRY_SNARE -> ModSounds.DRY_SNARE.get();
            case KALIMBA -> ModSounds.KALIMBA.get();
            case AFRO_HARP -> ModSounds.AFRO_HARP.get();
            case PHONK_COWBELL_CRYSTAL -> ModSounds.PHONK_COWBELL_CRYSTAL.get();
            case DRY_FINGER_SNAP -> ModSounds.DRY_FINGER_SNAP.get();
            case BONGO -> ModSounds.BONGO.get();
            case CLASSIC_CLAP -> ModSounds.CLASSIC_CLAP.get();
            case TAMBOURINE -> ModSounds.TAMBOURINE.get();
            case GROAN_TUBE -> ModSounds.GROAN_TUBE.get();
            case GONG -> ModSounds.GONG.get();

            // Special Easter Egg Sound
            case RAINBOW -> net.minecraft.sounds.SoundEvents.BEACON_ACTIVATE;
            
            case BA -> ModSounds.BA.get();
            default -> stateBelow.instrument().getSoundEvent().get();
        };
    }


    // selecting the specific _bass_lo, _mid_hi -> y-2 block
    private static SoundEvent getSustainedSound(BetterInstruments inst, Level level, BlockPos pos, int note) {
        BlockState octaveBlock = level.getBlockState(pos.below(2));
        
        String layer = "mid"; // Default 
        if (octaveBlock.is(Blocks.RED_WOOL)) {
            layer = "subbass";
        } else if (octaveBlock.is(Blocks.ORANGE_WOOL)) {
            layer = "bass";
        }else if (octaveBlock.is(Blocks.GREEN_WOOL)) {
            layer = "lead";
        }else if (octaveBlock.is(Blocks.LIME_WOOL)) {
            layer = "air";
        }

        String split = (note <= 12) ? "lo" : "hi";

        String soundPath = inst.getSerializedName() + "_" + layer + "_" + split;
        
        ResourceLocation loc = new ResourceLocation("betternoteblocks", soundPath);
        SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(loc);
        
        return sound != null ? sound : net.minecraft.sounds.SoundEvents.NOTE_BLOCK_HARP.get();
    }


    //This checks for vanilla ( AND ROSEN :) ) instruments so it doesnt swap the noteblock.
    public static boolean isCustomInstrumentBlock(BlockState state) {
        BetterInstruments inst = getInstrumentForBlock(state);
        return switch (inst) {
            //vanilla
            case HARP, BASS, BASEDRUM, SNARE, HAT, BELL, FLUTE, CHIME, GUITAR, 
                 XYLOPHONE, IRON_XYLOPHONE, COW_BELL, DIDGERIDOO, BIT, BANJO, PLING -> false;
            //rosen
            case VIOLIN, SYNTH_BASS, TIMPANI, MUSIC_BOX -> false;
            default -> true;
        };
    }
}