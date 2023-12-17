package net.aiq9.railroads.block.custom.rails;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.block.enums.RailShape;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NoteBlockRailBlock extends PoweredRailBlock {
    private long lastSoundPlayTime = 0L;
    private static final long COOLDOWN_TICKS = 20L; // Adjust this value to set the cooldown duration (20 ticks = 1 second)

    public NoteBlockRailBlock(Settings settings) {
        super(settings);

        this.setDefaultState(((this.stateManager.getDefaultState())
                .with(SHAPE, RailShape.NORTH_SOUTH))
                .with(WATERLOGGED, false)
                .with(POWERED, false)
        );
    }

    @Override
    public boolean cannotMakeCurves() {
        return true;
    }

    //plays sound
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient && entity instanceof AbstractMinecartEntity) {
            long currentTime = world.getTime();

            if (currentTime - lastSoundPlayTime >= COOLDOWN_TICKS) {
                world.playSound(null, pos, SoundEvents.BLOCK_NOTE_BLOCK_BELL.value(), SoundCategory.PLAYERS, 1.0f, 1.0f);
                lastSoundPlayTime = currentTime;
            }
        }

        super.onEntityCollision(state, world, pos, entity);
    }

    @Override
    public Property<RailShape> getShapeProperty() {
        return SHAPE;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        switch (rotation) {
            case CLOCKWISE_180: {
                switch (state.get(SHAPE)) {
                    case ASCENDING_EAST -> state.with(SHAPE, RailShape.ASCENDING_WEST);
                    case ASCENDING_WEST -> state.with(SHAPE, RailShape.ASCENDING_EAST);
                    case ASCENDING_NORTH -> state.with(SHAPE, RailShape.ASCENDING_SOUTH);
                    case ASCENDING_SOUTH -> state.with(SHAPE, RailShape.ASCENDING_NORTH);
                }
            }
            case COUNTERCLOCKWISE_90: {
                switch (state.get(SHAPE)) {
                    case NORTH_SOUTH -> state.with(SHAPE, RailShape.EAST_WEST);
                    case EAST_WEST -> state.with(SHAPE, RailShape.NORTH_SOUTH);
                    case ASCENDING_EAST -> state.with(SHAPE, RailShape.ASCENDING_NORTH);
                    case ASCENDING_WEST -> state.with(SHAPE, RailShape.ASCENDING_SOUTH);
                    case ASCENDING_NORTH -> state.with(SHAPE, RailShape.ASCENDING_WEST);
                    case ASCENDING_SOUTH -> state.with(SHAPE, RailShape.ASCENDING_EAST);
                }
            }
            case CLOCKWISE_90: {
                switch (state.get(SHAPE)) {
                    case NORTH_SOUTH -> state.with(SHAPE, RailShape.EAST_WEST);
                    case EAST_WEST -> state.with(SHAPE, RailShape.NORTH_SOUTH);
                    case ASCENDING_EAST -> state.with(SHAPE, RailShape.ASCENDING_SOUTH);
                    case ASCENDING_WEST -> state.with(SHAPE, RailShape.ASCENDING_NORTH);
                    case ASCENDING_NORTH -> state.with(SHAPE, RailShape.ASCENDING_EAST);
                    case ASCENDING_SOUTH -> state.with(SHAPE, RailShape.ASCENDING_WEST);
                }
            }
        }
        return state;
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        RailShape railShape = state.get(SHAPE);
        switch (mirror) {
            case LEFT_RIGHT: {
                switch (railShape) {
                    case ASCENDING_NORTH -> state.with(SHAPE, RailShape.ASCENDING_SOUTH);
                    case ASCENDING_SOUTH -> state.with(SHAPE, RailShape.ASCENDING_NORTH);
                }
                break;
            }
            case FRONT_BACK: {
                switch (railShape) {
                    case ASCENDING_EAST -> state.with(SHAPE, RailShape.ASCENDING_WEST);
                    case ASCENDING_WEST -> state.with(SHAPE, RailShape.ASCENDING_EAST);
                }
                break;
            }
        }
        return super.mirror(state, mirror);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.literal("Plays a nice sound when a minecart rolls over top").formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, options);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SHAPE, WATERLOGGED, POWERED);
    }
}
