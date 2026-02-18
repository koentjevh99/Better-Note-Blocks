package com.koen.betternoteblocks.blocks;

import net.minecraft.util.StringRepresentable;

//Just a holder for all the insturments including instruments from the mod "rosen"

public enum BetterInstruments implements StringRepresentable {
    // Vanilla names
    HARP("harp", false), BASS("bass", false), BASEDRUM("basedrum", false), SNARE("snare", false), HAT("hat", false), 
    BELL("bell", false), FLUTE("flute", false), CHIME("chime", false), GUITAR("guitar", false), XYLOPHONE("xylophone", false), 
    IRON_XYLOPHONE("iron_xylophone", false), COW_BELL("cow_bell", false), DIDGERIDOO("didgeridoo", false), 
    BIT("bit", false), BANJO("banjo", false), PLING("pling", false),


    //ROSEN SOUNDS :)
    ACCORDION("accordion", false), BEACH("beach", false), CARILLON("carillon", false), MUSIC_BOX("music_box", false), 
    CHOIR("choir", false), DHOLAK("dholak", false), KICK("kick", false), LOG_DRUM("log_drum", false), 
    ORGAN("organ", false), PIANO("piano", false), POWER_GUITAR("power_guitar", false), SAXOPHONE("saxophone", false), 
    SITAR("sitar", false), SYNTH("synth", false), SYNTH_BASS("synth_bass", false), TIMPANI("timpani", false), VIBRAPHONE("vibraphone", false), 
    VIOLIN("violin", false), 


    //sustained
    FLANGED_SAWTOOTH("flanged_sawtooth", true), YM_ELECTONE_TROMBONE("ym_electone_trombone", true),
    GB_SQR("gb_sqr", true), GB_SAW("gb_saw", true),


    // My Custom ones
    BETTER_MUSIC_BOX("better_music_box", false), ELEC_GUITAR("elec_guitar", false), HI_HAT("hi_hat", false), 
    SYNTH_STAB("synth_stab", false), KICK_STOMP("kick_stomp", false), ORCHESTRAL_HIT("orchestral_hit", false), 
    METAL_PIPE("metal_pipe", false), BRAWL_KICK("brawl_kick", false), CRASH("crash", false), 
    ACOUSTIC_TOM("acoustic_tom", false), HOLY_CHOIR("holy_choir", false), WOOD_BLOCK("wood_block", false), 
    HARDSTYLE_KICK_BUZZED("hardstyle_kick_buzzed", false), TRIANGLE("triangle", false), 
    BETTER_SYNTH_BASS("better_synth_bass", false), BETTER_TIMPANI("better_timpani", false), PSY_DRUM_HIT_AZTEK("psy_drum_hit_aztek", false), 
    BLOOP_NOISE("bloop_noise", false), BA("ba", false), DRY_SNARE("dry_snare", false), KALIMBA("kalimba", false), AFRO_HARP("afro_harp", false), 
    PHONK_COWBELL_CRYSTAL("phonk_cowbell_crystal", false), DRY_FINGER_SNAP("dry_finger_snap", false), 
    BONGO("bongo", false), CLASSIC_CLAP("classic_clap", false), TAMBOURINE("tambourine", false), GROAN_TUBE("groan_tube", false),
    GONG("gong", false),


    //EE
    RAINBOW("rainbow", false);
    

    private final String name;
    private final boolean sustained;

    BetterInstruments(String name, boolean sustained) {
        this.name = name;
        this.sustained = sustained;
    }

    public boolean isSustained() {
        return this.sustained;
    }

    @Override 
    public String getSerializedName() { 
        return this.name; 
    }
}