package net.aiq9.railroads.block.custom.rails;

import net.minecraft.block.*;
import net.minecraft.block.enums.RailShape;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Formatting;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IntersectionRailBlock extends AbstractRailBlock {
    public static final EnumProperty<RailShape> SHAPE = EnumProperty.of("shape", RailShape.class, shape ->
            shape != RailShape.ASCENDING_NORTH && shape != RailShape.ASCENDING_EAST && shape != RailShape.ASCENDING_SOUTH && shape != RailShape.ASCENDING_WEST &&
            shape != RailShape.NORTH_EAST && shape != RailShape.NORTH_WEST && shape != RailShape.SOUTH_EAST && shape != RailShape.SOUTH_WEST);

    public IntersectionRailBlock(Settings settings) {
        super(true, settings);
        setDefaultState ((this.stateManager.getDefaultState()).with(SHAPE, RailShape.NORTH_SOUTH).with(WATERLOGGED, false));
    }

    /*
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected void updateBlockState(BlockState state, World world, BlockPos pos, Block neighbor) {
        if (neighbor.getDefaultState().equals(RailShape.ASCENDING_NORTH || RailShape.ASCENDING_SOUTH || RailShape.ASCENDING_EAST || RailShape.ASCENDING_WEST) && new RailPlacementHelper(world, pos, state).getNeighbors()) {
            this.updateBlockState(world, pos, state, false);
        }
    }
    */

    //tooltip
    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.literal("Allows two at-grade rail lines to cross one another.").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("DO NOT PLACE AS A SLOPE.").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("FUTURE FEATURE").formatted(Formatting.RED));
        super.appendTooltip(stack, world, tooltip, options);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        switch (rotation) {
            case COUNTERCLOCKWISE_90: {
                switch (state.get(SHAPE)) {
                    case NORTH_SOUTH: {
                        return state.with(SHAPE, RailShape.EAST_WEST);
                    }
                    case EAST_WEST: {
                        return state.with(SHAPE, RailShape.NORTH_SOUTH);
                    }
                }
            }
            case CLOCKWISE_90: {
                switch (state.get(SHAPE)) {
                    case NORTH_SOUTH: {
                        return state.with(SHAPE, RailShape.EAST_WEST);
                    }
                    case EAST_WEST: {
                        return state.with(SHAPE, RailShape.NORTH_SOUTH);
                    }
                }
            }
        }
        return state;
    }

    @Override
    public Property<RailShape> getShapeProperty() {
        return SHAPE;
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SHAPE, WATERLOGGED);
    }
}
