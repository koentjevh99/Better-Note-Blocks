package com.koen.betternoteblocks.blocks;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.api.distmarker.Dist;
import com.koen.betternoteblocks.registration.InstrumentMapper;
import com.koen.betternoteblocks.registration.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

//Entity class of my custom block

public class BetterNoteBlockEntity extends BlockEntity {

    private Object activeSound;

    public BetterNoteBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BETTER_NOTE_BLOCK.get(), pos, state);
    }

    public void setActiveSound(Object sound) {
        if (this.level != null && this.level.isClientSide) {
            net.minecraftforge.fml.DistExecutor.unsafeRunWhenOn(net.minecraftforge.api.distmarker.Dist.CLIENT, () -> () -> {
                com.koen.betternoteblocks.client.ClientSoundHelper.stopSynth(this.activeSound);
            });
        }
        this.activeSound = sound;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        if (this.level != null && this.level.isClientSide) {
            BlockState state = this.level.getBlockState(this.worldPosition);
            BetterInstruments inst = state.getValue(BetterNoteBlock.INSTRUMENT);
            int note = state.getValue(BetterNoteBlock.NOTE);

            if (state.getValue(BetterNoteBlock.POWERED) && inst.isSustained()) {
                int relativeNote = (note > 12) ? (note - 12) : note;
                float pitch = (float) Math.pow(2.0D, (double) relativeNote / 12.0D);

                net.minecraft.sounds.SoundEvent sound = InstrumentMapper.getSoundForInstrument(
                        inst,
                        this.level.getBlockState(this.worldPosition.below()),
                        this.level,
                        this.worldPosition,
                        note
                );

                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                    com.koen.betternoteblocks.client.ClientSoundHelper.playSynth(this.level, this.worldPosition, sound, pitch);
                });
            }
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public net.minecraft.nbt.CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }
}