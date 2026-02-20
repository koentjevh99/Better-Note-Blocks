package com.koen.betternoteblocks.registration;

import com.koen.betternoteblocks.betternoteblocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

//Registering sounds

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = 
        DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, betternoteblocks.MODID);

    public static final RegistryObject<SoundEvent> BETTER_MUSIC_BOX = SOUND_EVENTS.register("music_box",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "music_box")));

    public static final RegistryObject<SoundEvent> ELEC_GUITAR = SOUND_EVENTS.register("elec_guitar",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "elec_guitar")));

    public static final RegistryObject<SoundEvent> HI_HAT = SOUND_EVENTS.register("hi_hat",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "hi_hat")));

    public static final RegistryObject<SoundEvent> SYNTH_STAB = SOUND_EVENTS.register("synth_stab",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "synth_stab")));

    public static final RegistryObject<SoundEvent> KICK_STOMP = SOUND_EVENTS.register("kick_stomp",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "kick_stomp")));

    public static final RegistryObject<SoundEvent> ORCHESTRAL_HIT = SOUND_EVENTS.register("orchestral_hit",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "orchestral_hit")));

    public static final RegistryObject<SoundEvent> METAL_PIPE = SOUND_EVENTS.register("metal_pipe",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "metal_pipe")));

    public static final RegistryObject<SoundEvent> BRAWL_KICK = SOUND_EVENTS.register("brawl_kick",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "brawl_kick")));

    public static final RegistryObject<SoundEvent> CRASH = SOUND_EVENTS.register("crash",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "crash")));

    public static final RegistryObject<SoundEvent> ACOUSTIC_TOM = SOUND_EVENTS.register("acoustic_tom",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "acoustic_tom")));

    public static final RegistryObject<SoundEvent> HOLY_CHOIR = SOUND_EVENTS.register("holy_choir",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "holy_choir")));

    public static final RegistryObject<SoundEvent> WOOD_BLOCK = SOUND_EVENTS.register("wood_block",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "wood_block")));

    public static final RegistryObject<SoundEvent> HARDSTYLE_KICK_BUZZED = SOUND_EVENTS.register("hardstyle_kick_buzzed",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "hardstyle_kick_buzzed")));

    public static final RegistryObject<SoundEvent> TRIANGLE = SOUND_EVENTS.register("triangle",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "triangle")));

    public static final RegistryObject<SoundEvent> BETTER_SYNTH_BASS = SOUND_EVENTS.register("better_synth_bass",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "synth_bass")));

    public static final RegistryObject<SoundEvent> BETTER_TIMPANI = SOUND_EVENTS.register("better_timpani",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "timpani")));

    public static final RegistryObject<SoundEvent> PSY_DRUM_HIT_AZTEK = SOUND_EVENTS.register("psy_drum_hit_aztek",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "psy_drum_hit_aztek")));

    public static final RegistryObject<SoundEvent> BLOOP_NOISE = SOUND_EVENTS.register("bloop_noise",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "bloop_noise")));

    public static final RegistryObject<SoundEvent> DRY_SNARE = SOUND_EVENTS.register("dry_snare",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "snare")));

    public static final RegistryObject<SoundEvent> KALIMBA = SOUND_EVENTS.register("kalimba",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "kalimba")));

    public static final RegistryObject<SoundEvent> AFRO_HARP = SOUND_EVENTS.register("afro_harp",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "afro_harp")));

    public static final RegistryObject<SoundEvent> PHONK_COWBELL_CRYSTAL = SOUND_EVENTS.register("phonk_cowbell_crystal",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "phonk_cowbell_crystal")));

    public static final RegistryObject<SoundEvent> DRY_FINGER_SNAP = SOUND_EVENTS.register("dry_finger_snap",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "dry_finger_snap")));

    public static final RegistryObject<SoundEvent> BONGO = SOUND_EVENTS.register("bongo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "bongo")));

    public static final RegistryObject<SoundEvent> CLASSIC_CLAP = SOUND_EVENTS.register("classic_clap",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "classic_clap")));

    public static final RegistryObject<SoundEvent> TAMBOURINE = SOUND_EVENTS.register("tambourine",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "tambourine")));

    public static final RegistryObject<SoundEvent> GROAN_TUBE = SOUND_EVENTS.register("groan_tube",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "groan_tube")));

    public static final RegistryObject<SoundEvent> GONG = SOUND_EVENTS.register("gong",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gong")));

    public static final RegistryObject<SoundEvent> HARD_PUNCHY_KICK = SOUND_EVENTS.register("hard_punchy_kick",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "hard_punchy_kick")));


    // Sustained sounds

    public static final RegistryObject<SoundEvent> FLANGED_SAWTOOTH_SB_LO = SOUND_EVENTS.register("flanged_sawtooth_subbass_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "flanged_sawtooth_subbass_lo")));
    public static final RegistryObject<SoundEvent> FLANGED_SAWTOOTH_SB_HI = SOUND_EVENTS.register("flanged_sawtooth_subbass_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "flanged_sawtooth_subbass_hi")));
    public static final RegistryObject<SoundEvent> FLANGED_SAWTOOTH_B_LO = SOUND_EVENTS.register("flanged_sawtooth_bass_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "flanged_sawtooth_bass_lo")));
    public static final RegistryObject<SoundEvent> FLANGED_SAWTOOTH_B_HI = SOUND_EVENTS.register("flanged_sawtooth_bass_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "flanged_sawtooth_bass_hi")));
    public static final RegistryObject<SoundEvent> FLANGED_SAWTOOTH_M_LO = SOUND_EVENTS.register("flanged_sawtooth_mid_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "flanged_sawtooth_mid_lo")));
    public static final RegistryObject<SoundEvent> FLANGED_SAWTOOTH_M_HI = SOUND_EVENTS.register("flanged_sawtooth_mid_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "flanged_sawtooth_mid_hi")));
    public static final RegistryObject<SoundEvent> FLANGED_SAWTOOTH_L_LO = SOUND_EVENTS.register("flanged_sawtooth_lead_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "flanged_sawtooth_lead_lo")));
    public static final RegistryObject<SoundEvent> FLANGED_SAWTOOTH_L_HI = SOUND_EVENTS.register("flanged_sawtooth_lead_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "flanged_sawtooth_lead_hi")));
    public static final RegistryObject<SoundEvent> FLANGED_SAWTOOTH_A_LO = SOUND_EVENTS.register("flanged_sawtooth_air_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "flanged_sawtooth_air_lo")));
    public static final RegistryObject<SoundEvent> FLANGED_SAWTOOTH_A_HI = SOUND_EVENTS.register("flanged_sawtooth_air_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "flanged_sawtooth_air_hi")));

    
    public static final RegistryObject<SoundEvent> YM_TROMBONE_SB_LO = SOUND_EVENTS.register("ym_electone_trombone_subbass_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "ym_electone_trombone_subbass_lo")));
    public static final RegistryObject<SoundEvent> YM_TROMBONE_SB_HI = SOUND_EVENTS.register("ym_electone_trombone_subbass_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "ym_electone_trombone_subbass_hi")));
    public static final RegistryObject<SoundEvent> YM_TROMBONE_B_LO = SOUND_EVENTS.register("ym_electone_trombone_bass_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "ym_electone_trombone_bass_lo")));
    public static final RegistryObject<SoundEvent> YM_TROMBONE_B_HI = SOUND_EVENTS.register("ym_electone_trombone_bass_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "ym_electone_trombone_bass_hi")));
    public static final RegistryObject<SoundEvent> YM_TROMBONE_M_LO = SOUND_EVENTS.register("ym_electone_trombone_mid_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "ym_electone_trombone_mid_lo")));
    public static final RegistryObject<SoundEvent> YM_TROMBONE_M_HI = SOUND_EVENTS.register("ym_electone_trombone_mid_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "ym_electone_trombone_mid_hi")));
    public static final RegistryObject<SoundEvent> YM_TROMBONE_L_LO = SOUND_EVENTS.register("ym_electone_trombone_lead_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "ym_electone_trombone_lead_lo")));
    public static final RegistryObject<SoundEvent> YM_TROMBONE_L_HI = SOUND_EVENTS.register("ym_electone_trombone_lead_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "ym_electone_trombone_lead_hi")));
    public static final RegistryObject<SoundEvent> YM_TROMBONE_A_LO = SOUND_EVENTS.register("ym_electone_trombone_air_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "ym_electone_trombone_air_lo")));
    public static final RegistryObject<SoundEvent> YM_TROMBONE_A_HI = SOUND_EVENTS.register("ym_electone_trombone_air_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "ym_electone_trombone_air_hi")));


    public static final RegistryObject<SoundEvent> GB_SQR_SB_LO = SOUND_EVENTS.register("gb_sqr_subbass_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_sqr_subbass_lo")));
    public static final RegistryObject<SoundEvent> GB_SQR_SB_HI = SOUND_EVENTS.register("gb_sqr_subbass_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_sqr_subbass_hi")));
    public static final RegistryObject<SoundEvent> GB_SQR_B_LO = SOUND_EVENTS.register("gb_sqr_bass_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_sqr_bass_lo")));
    public static final RegistryObject<SoundEvent> GB_SQR_B_HI = SOUND_EVENTS.register("gb_sqr_bass_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_sqr_bass_hi")));
    public static final RegistryObject<SoundEvent> GB_SQR_M_LO = SOUND_EVENTS.register("gb_sqr_mid_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_sqr_mid_lo")));
    public static final RegistryObject<SoundEvent> GB_SQR_M_HI = SOUND_EVENTS.register("gb_sqr_mid_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_sqr_mid_hi")));
    public static final RegistryObject<SoundEvent> GB_SQR_L_LO = SOUND_EVENTS.register("gb_sqr_lead_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_sqr_lead_lo")));
    public static final RegistryObject<SoundEvent> GB_SQR_L_HI = SOUND_EVENTS.register("gb_sqr_lead_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_sqr_lead_hi")));
    public static final RegistryObject<SoundEvent> GB_SQR_A_LO = SOUND_EVENTS.register("gb_sqr_air_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_sqr_air_lo")));
    public static final RegistryObject<SoundEvent> GB_SQR_A_HI = SOUND_EVENTS.register("gb_sqr_air_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_sqr_air_hi")));


    public static final RegistryObject<SoundEvent> GB_SAW_SB_LO = SOUND_EVENTS.register("gb_saw_subbass_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_saw_subbass_lo")));
    public static final RegistryObject<SoundEvent> GB_SAW_SB_HI = SOUND_EVENTS.register("gb_saw_subbass_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_saw_subbass_hi")));
    public static final RegistryObject<SoundEvent> GB_SAW_B_LO = SOUND_EVENTS.register("gb_saw_bass_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_saw_bass_lo")));
    public static final RegistryObject<SoundEvent> GB_SAW_B_HI = SOUND_EVENTS.register("gb_saw_bass_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_saw_bass_hi")));
    public static final RegistryObject<SoundEvent> GB_SAW_M_LO = SOUND_EVENTS.register("gb_saw_mid_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_saw_mid_lo")));
    public static final RegistryObject<SoundEvent> GB_SAW_M_HI = SOUND_EVENTS.register("gb_saw_mid_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_saw_mid_hi")));
    public static final RegistryObject<SoundEvent> GB_SAW_L_LO = SOUND_EVENTS.register("gb_saw_lead_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_saw_lead_lo")));
    public static final RegistryObject<SoundEvent> GB_SAW_L_HI = SOUND_EVENTS.register("gb_saw_lead_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_saw_lead_hi")));
    public static final RegistryObject<SoundEvent> GB_SAW_A_LO = SOUND_EVENTS.register("gb_saw_air_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_saw_air_lo")));
    public static final RegistryObject<SoundEvent> GB_SAW_A_HI = SOUND_EVENTS.register("gb_saw_air_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "gb_saw_air_hi")));


    public static final RegistryObject<SoundEvent> SAXOPHONE_SYNTH_SB_LO = SOUND_EVENTS.register("saxophone_synth_subbass_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "saxophone_synth_subbass_lo")));
    public static final RegistryObject<SoundEvent> SAXOPHONE_SYNTH_SB_HI = SOUND_EVENTS.register("saxophone_synth_subbass_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "saxophone_synth_subbass_hi")));
    public static final RegistryObject<SoundEvent> SAXOPHONE_SYNTH_B_LO = SOUND_EVENTS.register("saxophone_synth_bass_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "saxophone_synth_bass_lo")));
    public static final RegistryObject<SoundEvent> SAXOPHONE_SYNTH_B_HI = SOUND_EVENTS.register("saxophone_synth_bass_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "saxophone_synth_bass_hi")));
    public static final RegistryObject<SoundEvent> SAXOPHONE_SYNTH_M_LO = SOUND_EVENTS.register("saxophone_synth_mid_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "saxophone_synth_mid_lo")));
    public static final RegistryObject<SoundEvent> SAXOPHONE_SYNTH_M_HI = SOUND_EVENTS.register("saxophone_synth_mid_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "saxophone_synth_mid_hi")));
    public static final RegistryObject<SoundEvent> SAXOPHONE_SYNTH_L_LO = SOUND_EVENTS.register("saxophone_synth_lead_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "saxophone_synth_lead_lo")));
    public static final RegistryObject<SoundEvent> SAXOPHONE_SYNTH_L_HI = SOUND_EVENTS.register("saxophone_synth_lead_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "saxophone_synth_lead_hi")));
    public static final RegistryObject<SoundEvent> SAXOPHONE_SYNTH_A_LO = SOUND_EVENTS.register("saxophone_synth_air_lo",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "saxophone_synth_air_lo")));
    public static final RegistryObject<SoundEvent> SAXOPHONE_SYNTH_A_HI = SOUND_EVENTS.register("saxophone_synth_air_hi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "saxophone_synth_air_hi")));


    // Custom blocks
    public static final RegistryObject<SoundEvent> BA = SOUND_EVENTS.register("ba",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(betternoteblocks.MODID, "ba")));

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}