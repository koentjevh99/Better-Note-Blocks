package com.koen.betternoteblocks.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import com.koen.betternoteblocks.blocks.BetterNoteBlockEntity;

//client side sound helper

public class ClientSoundHelper {
    public static void playSynth(Level level, BlockPos pos, SoundEvent sound, float pitch) {
        BetterSynthInstance newSound = new BetterSynthInstance(sound, pitch, pos);

        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof BetterNoteBlockEntity noteBe) {
            noteBe.setActiveSound(newSound);
        }

        Minecraft.getInstance().getSoundManager().play(newSound);
    }
    public static void stopSynth(Object sound) {
        if (sound instanceof BetterSynthInstance synth) {
            synth.stopPlaying();
        }
    }
}