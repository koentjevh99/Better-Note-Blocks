package com.koen.betternoteblocks.events;

import com.koen.betternoteblocks.betternoteblocks;
import com.koen.betternoteblocks.blocks.BetterNoteBlock;
import com.koen.betternoteblocks.registration.InstrumentMapper;
import com.koen.betternoteblocks.registration.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.resources.ResourceLocation;

//The main handler for (mostly) vanilla noteblocks

@Mod.EventBusSubscriber(modid = betternoteblocks.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NoteBlockHandler {

    //BlockConversion
    @SubscribeEvent
    public static void onVanillaNoteBlockPlaced(BlockEvent.EntityPlaceEvent event) {
        if (!(event.getLevel() instanceof Level level) || level.isClientSide()) return;

        BlockState placedState = event.getPlacedBlock();
        BlockPos pos = event.getPos();

        if (placedState.is(Blocks.NOTE_BLOCK)) {
            BlockState stateBelow = level.getBlockState(pos.below());

            if (InstrumentMapper.isCustomInstrumentBlock(stateBelow)) {
                upgradeToBetter(level, pos, placedState, stateBelow);
            }
        }
    }

    @SubscribeEvent
    public static void onNeighborNotify(BlockEvent.NeighborNotifyEvent event) {
        if (event.getLevel().isClientSide()) return;
        Level level = (Level) event.getLevel();

        for (Direction direction : event.getNotifiedSides()) {
            if (direction == Direction.UP) {
                BlockPos notePos = event.getPos().above();
                BlockState noteState = level.getBlockState(notePos);

                if (noteState.is(Blocks.NOTE_BLOCK)) {
                    BlockState stateBelow = level.getBlockState(event.getPos());

                    if (InstrumentMapper.isCustomInstrumentBlock(stateBelow)) {
                        upgradeToBetter(level, notePos, noteState, stateBelow);
                    }
                }
                break; 
            }
        }
    }

    //Upgrade vanilla blocks
    private static void upgradeToBetter(Level level, BlockPos pos, BlockState vanillaState, BlockState stateBelow) {
        BlockState betterState = ModBlocks.BETTER_NOTE_BLOCK.get().defaultBlockState()
                .setValue(BetterNoteBlock.NOTE, vanillaState.getValue(NoteBlock.NOTE))
                .setValue(BetterNoteBlock.POWERED, vanillaState.getValue(NoteBlock.POWERED))
                .setValue(BetterNoteBlock.INSTRUMENT, InstrumentMapper.getInstrumentForBlock(stateBelow));

        level.setBlock(pos, betterState, 3);
        
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER, 
                pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 
                5, 0.2D, 0.2D, 0.2D, 0.05D);
        }
    }

    //shift+right-click
    @SubscribeEvent
    public static void onShiftClickNoteBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState currentState = level.getBlockState(pos);
        
        boolean isVanilla = currentState.getBlock() instanceof NoteBlock;
        boolean isBetter = currentState.getBlock() instanceof BetterNoteBlock;
        
        if (!isVanilla && !isBetter) return;

        boolean mainHandEmpty = event.getEntity().getMainHandItem().isEmpty();
        boolean offHandEmpty = event.getEntity().getOffhandItem().isEmpty();

        if (event.getEntity().isShiftKeyDown() && mainHandEmpty && offHandEmpty) {
            
            if (level.isClientSide) {
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
                return;
            }

            int currentNote = currentState.getValue(BetterNoteBlock.NOTE); 
            int prevNote = (currentNote == 0) ? 24 : currentNote - 1;
            BlockState newState = currentState.setValue(BetterNoteBlock.NOTE, prevNote);

            level.setBlock(pos, newState, 3);
            
            if (isBetter) {
                ((BetterNoteBlock)newState.getBlock()).triggerNote(level, pos, newState);
            } else {
                ((NoteBlock)newState.getBlock()).triggerEvent(newState, level, pos, 0, 0);
                spawnNoteParticle(level, pos, prevNote);
            }

            awardShiftAdvancement(event.getEntity());

            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        }
    }

    public static void awardShiftAdvancement(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            var advManager = serverPlayer.server.getAdvancements();
            var advancement = advManager.getAdvancement(ResourceLocation.fromNamespaceAndPath("betternoteblocks", "wait_go_back"));
            if (advancement != null) {
                serverPlayer.getAdvancements().award(advancement, "shifted_note");
            }
        }
    }

    private static void spawnNoteParticle(LevelAccessor level, BlockPos pos, int note) {
        double noteColor = (double) note / 24.0D;
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.NOTE, 
                pos.getX() + 0.5D, pos.getY() + 1.2D, pos.getZ() + 0.5D, 
                0, noteColor, 0.0D, 0.0D, 1.0D);
        }
    }
}