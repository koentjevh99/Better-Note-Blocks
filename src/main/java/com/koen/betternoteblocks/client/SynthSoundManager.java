package com.koen.betternoteblocks.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;

//Manages Synth sounds

public class SynthSoundManager {
    // Plays a sustained sound
    public static void playSustained(BlockPos pos, SoundEvent sound, float pitch) {
        if (sound == null) return;
        
        BetterSynthInstance instance = new BetterSynthInstance(sound, pitch, pos);
        Minecraft.getInstance().getSoundManager().play(instance);
    }
}