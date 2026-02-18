package com.koen.betternoteblocks.blocks;

import com.koen.betternoteblocks.registration.InstrumentMapper;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

//Main class for my custom block

public class BetterNoteBlock extends Block implements EntityBlock {
    public static final IntegerProperty NOTE = BlockStateProperties.NOTE;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final EnumProperty<BetterInstruments> INSTRUMENT = EnumProperty.create("instrument", BetterInstruments.class);

    public BetterNoteBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NOTE, 0)
                .setValue(INSTRUMENT, BetterInstruments.HARP)
                .setValue(POWERED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NOTE, INSTRUMENT, POWERED);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BetterNoteBlockEntity(pos, state);
    }

   @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (level.isClientSide) return;
        boolean isRainbow = state.getValue(INSTRUMENT) == BetterInstruments.RAINBOW;

        if (fromPos.equals(pos.below()) && !isRainbow) {
            BetterInstruments newInstrument = InstrumentMapper.getInstrumentForBlock(level.getBlockState(fromPos));
            if (newInstrument != state.getValue(INSTRUMENT)) {
                state = state.setValue(INSTRUMENT, newInstrument);
                level.setBlock(pos, state, 3);
            }
        }

        // Redstone logic
        boolean isPowered = level.hasNeighborSignal(pos);
        if (isPowered != state.getValue(POWERED)) {
            level.setBlock(pos, state.setValue(POWERED, isPowered), 3);
            if (isPowered) {
                this.triggerNote(level, pos, state.setValue(POWERED, isPowered));
            }
        }
    }

    public void triggerNote(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide) return;

        BetterInstruments inst = state.getValue(INSTRUMENT);

        if (level instanceof ServerLevel server) {
            if (inst == BetterInstruments.GROAN_TUBE) { 
                Player player = level.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 32, false);
                
                if (player instanceof ServerPlayer serverPlayer) {
                    ResourceLocation advId = new ResourceLocation("betternoteblocks", "groan_tube");
                    Advancement groanAdv = server.getServer().getAdvancements().getAdvancement(advId);
                    
                    if (groanAdv != null) {
                        serverPlayer.getAdvancements().award(groanAdv, "played_groan_tube");
                    } else {
                    }
                } else {
                    return;
                }
            }
        }

        if (inst.isSustained()) {
            level.blockEvent(pos, this, 3, 1);
        } else {
            this.playNote(level, pos, state);
        }
    }

    private float getCalculatedPitch(BlockState state) {
        int note = state.getValue(NOTE);
        BetterInstruments inst = state.getValue(INSTRUMENT);

        if (inst.isSustained()) {
            int relativeNote = (note > 12) ? (note - 12) : note;
            return (float) Math.pow(2.0D, (double) relativeNote / 12.0D);
        }
        return (float) Math.pow(2.0D, (double) (note - 12) / 12.0D);
    }

    @Override
    public boolean triggerEvent(BlockState state, Level level, BlockPos pos, int id, int param) {
        if (level.isClientSide && id == 3 && param == 1) {
            BetterInstruments inst = state.getValue(INSTRUMENT);
            int note = state.getValue(NOTE);
            float pitch = getCalculatedPitch(state);

            net.minecraft.sounds.SoundEvent sound = InstrumentMapper.getSoundForInstrument(inst, level.getBlockState(pos.below()), level, pos, note);

            net.minecraftforge.fml.DistExecutor.unsafeRunWhenOn(net.minecraftforge.api.distmarker.Dist.CLIENT, () -> () -> {
                com.koen.betternoteblocks.client.ClientSoundHelper.playSynth(level, pos, sound, pitch);
            });

            return true;
        }
        return super.triggerEvent(state, level, pos, id, param);
    }

    private void playNote(Level level, BlockPos pos, BlockState state) {
        BetterInstruments inst = state.getValue(INSTRUMENT);
        int note = state.getValue(NOTE);
        float pitch = getCalculatedPitch(state);
        
        net.minecraft.sounds.SoundEvent sound = InstrumentMapper.getSoundForInstrument(inst, level.getBlockState(pos.below()), level, pos, note);
        level.playSound(null, pos, sound, SoundSource.RECORDS, 4.0F, pitch);

        if (level instanceof ServerLevel server) {
            server.sendParticles(ParticleTypes.NOTE, pos.getX() + 0.5, pos.getY() + 1.2, pos.getZ() + 0.5, 0, (double) note / 24.0, 0.0, 0.0, 1.0);
        }
    }

    @Override
    public void attack(BlockState state, Level level, BlockPos pos, Player player) {
        if (!level.isClientSide) {
            this.triggerNote(level, pos, state);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        int nextNote = (state.getValue(NOTE)) % 25;
        int finalNote = (state.getValue(NOTE) + 1) % 25;
        BlockState newState = state.setValue(NOTE, finalNote);
        level.setBlock(pos, newState, 3);

        if (!level.isClientSide) {
            this.triggerNote(level, pos, newState);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            
            if (state.getValue(INSTRUMENT) == BetterInstruments.RAINBOW) return;

            BlockPos below = pos.below();
            BlockState blockBelow = level.getBlockState(below);

            if (blockBelow.is(Blocks.BEACON)) {
                BlockEntity be = level.getBlockEntity(below);
                if (be instanceof BeaconBlockEntity beacon) {
                    int beaconLevels = beacon.saveWithFullMetadata().getInt("Levels");

                    if (beaconLevels >= 4) {
                        Player player = level.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10, true);
                        
                        if (player instanceof ServerPlayer serverPlayer) {
                            Advancement fullPalette = serverLevel.getServer().getAdvancements()
                                    .getAdvancement(new ResourceLocation("betternoteblocks", "full_palette"));
                            
                            boolean hasRequirement = fullPalette != null && serverPlayer.getAdvancements().getOrStartProgress(fullPalette).isDone();

                            if (hasRequirement) {
                                level.setBlock(pos, com.koen.betternoteblocks.registration.ModBlocks.BETTER_NOTE_BLOCK.get().defaultBlockState()
                                        .setValue(INSTRUMENT, BetterInstruments.RAINBOW), 3);

                                Advancement bugAdv = serverLevel.getServer().getAdvancements()
                                        .getAdvancement(new ResourceLocation("betternoteblocks", "found_a_bug"));
                                if (bugAdv != null) {
                                    serverPlayer.getAdvancements().award(bugAdv, "has_rainbow_block");
                                }

                                level.playSound(null, pos, SoundEvents.BEACON_ACTIVATE, SoundSource.BLOCKS, 3.0f, 3.0f);
                                serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 40, 0.5, 0.5, 0.5, 0.1);
                                return;
                            } else {
                                return;
                            }
                        }
                    }
                }
            }

            BetterInstruments newInstrument = InstrumentMapper.getInstrumentForBlock(blockBelow);
            if (newInstrument != state.getValue(INSTRUMENT)) {
                level.setBlock(pos, state.setValue(INSTRUMENT, newInstrument), 3);
            }
        }
        super.onPlace(state, level, pos, oldState, isMoving);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, net.minecraft.world.phys.HitResult target, net.minecraft.world.level.BlockGetter level, BlockPos pos, Player player) {
        ItemStack stack = super.getCloneItemStack(state, target, level, pos, player);

        boolean isClient = false;
        if (level instanceof net.minecraft.world.level.Level actualLevel) {
            isClient = actualLevel.isClientSide;
        }

        if (isClient) {
            if (net.minecraft.client.gui.screens.Screen.hasControlDown()) {
                net.minecraft.nbt.CompoundTag tag = new net.minecraft.nbt.CompoundTag();
                net.minecraft.nbt.CompoundTag blockStateTag = new net.minecraft.nbt.CompoundTag();

                blockStateTag.putInt("note", state.getValue(NOTE));

                tag.put("BlockStateTag", blockStateTag);
                stack.setTag(tag);
            }
        }

        return stack;
    }

    public boolean isBeaconBase(BlockState state, LevelReader level, BlockPos pos, BlockPos beaconPos) {
        return false;
    }

    @Override
    @net.minecraftforge.api.distmarker.OnlyIn(net.minecraftforge.api.distmarker.Dist.CLIENT)
    public float[] getBeaconColorMultiplier(BlockState state, LevelReader level, BlockPos pos, BlockPos beaconPos) {
        if (state.getValue(INSTRUMENT) == BetterInstruments.RAINBOW) {
            float time = (float)(System.currentTimeMillis() % 4000) / 4000.0f;
            float r = (float)Math.sin(time * 2 * Math.PI) * 0.5f + 0.5f;
            float g = (float)Math.sin((time + 0.33) * 2 * Math.PI) * 0.5f + 0.5f;
            float b = (float)Math.sin((time + 0.66) * 2 * Math.PI) * 0.5f + 0.5f;
            return new float[]{r, g, b};
        }
        return null;
    }
}