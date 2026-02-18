package com.koen.betternoteblocks.client;

import com.koen.betternoteblocks.blocks.BetterNoteBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

// Synth logic

public class BetterSynthInstance extends AbstractTickableSoundInstance {
    private final BlockPos pos;
    private boolean wasPowered = false;
    private int lifeTime = 0;
    private int particleTick = 0; 

    public BetterSynthInstance(SoundEvent sound, float pitch, BlockPos pos) {
        super(sound, SoundSource.RECORDS, RandomSource.create());
        this.pos = pos;
        this.pitch = pitch;
        this.x = pos.getX() + 0.5D;
        this.y = pos.getY() + 0.5D;
        this.z = pos.getZ() + 0.5D;
        this.looping = true;
        this.delay = 0;
        this.volume = 4.0F;
        this.particleTick = 10; 
    }

    @Override
    public void tick() {
        Level level = Minecraft.getInstance().level;
        if (level == null) { this.stop(); return; }

        BlockState state = level.getBlockState(pos);
        if (!(state.getBlock() instanceof BetterNoteBlock)) { this.stop(); return; }

        boolean isPowered = state.getValue(BetterNoteBlock.POWERED);

        if (isPowered) {
            this.wasPowered = true;
            this.lifeTime = 0;
        } else {
            if (this.wasPowered) {
                this.stop();
                return;
            } else {
                this.lifeTime++;
                if (this.lifeTime > 60) {
                    this.stop();
                    return;
                }
            }
        }

        // Particle Logic
        if (!this.isStopped()) {
            if (this.particleTick % 10 == 0) {
                double color = (double) state.getValue(BetterNoteBlock.NOTE) / 24.0;
                level.addParticle(ParticleTypes.NOTE, 
                    this.x, this.y + 0.7D, this.z, 
                    color, 0.0D, 0.0D);
            }
            this.particleTick++;
        }
    }

    public void stopPlaying() {
        this.stop(); 
    }
}