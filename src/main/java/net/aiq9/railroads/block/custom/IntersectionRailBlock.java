package net.aiq9.railroads.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RailBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.RailShape;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IntersectionRailBlock extends RailBlock {
    public static EnumProperty<RailShape> SHAPE;

    public IntersectionRailBlock(Settings settings) {
        super(settings);
        this.setDefaultState(((this.stateManager.getDefaultState()).with(SHAPE, RailShape.NORTH_SOUTH)).with(WATERLOGGED, false));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.literal("Only place on flat ground. Do not place in slopes.").formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, options);
    }

    //ripped from AbstractRailBlock
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        RailShape railShape;
        RailShape railShape2 = railShape = state.isOf(this) ? state.get(this.getShapeProperty()) : null;
        if (railShape.isAscending()) {
            return STRAIGHT_SHAPE;
        }

        return STRAIGHT_SHAPE;
    }

    @Override //do not update curves
    protected BlockState updateCurves(BlockState state, World world, BlockPos pos, boolean notify) {
        return null;
    }

    @Override //cannot create curves
    public boolean cannotMakeCurves() {
        return true;
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
                    case ASCENDING_EAST: {
                        return state.with(SHAPE, RailShape.EAST_WEST);
                    }
                    case ASCENDING_WEST: {
                        return state.with(SHAPE, RailShape.EAST_WEST);
                    }
                    case ASCENDING_NORTH: {
                        return (BlockState)state.with(SHAPE, RailShape.NORTH_SOUTH);
                    }
                    case ASCENDING_SOUTH: {
                        return (BlockState)state.with(SHAPE, RailShape.NORTH_SOUTH);
                    }
                }
            }
            case COUNTERCLOCKWISE_90: {
                switch (state.get(SHAPE)) {
                    case NORTH_SOUTH: {
                        return state.with(SHAPE, RailShape.NORTH_SOUTH);
                    }
                    case EAST_WEST: {
                        return state.with(SHAPE, RailShape.EAST_WEST);
                    }
                    case ASCENDING_EAST: {
                        return state.with(SHAPE, RailShape.EAST_WEST);
                    }
                    case ASCENDING_WEST: {
                        return state.with(SHAPE, RailShape.EAST_WEST);
                    }
                    case ASCENDING_NORTH: {
                        return state.with(SHAPE, RailShape.NORTH_SOUTH);
                    }
                    case ASCENDING_SOUTH: {
                        return state.with(SHAPE, RailShape.NORTH_SOUTH);
                    }
                }
            }
            case CLOCKWISE_90: {
                switch (state.get(SHAPE)) {
                    case NORTH_SOUTH: {
                        return state.with(SHAPE, RailShape.NORTH_SOUTH);
                    }
                    case EAST_WEST: {
                        return state.with(SHAPE, RailShape.EAST_WEST);
                    }
                    case ASCENDING_EAST: {
                        return state.with(SHAPE, RailShape.EAST_WEST);
                    }
                    case ASCENDING_WEST: {
                        return state.with(SHAPE, RailShape.EAST_WEST);
                    }
                    case ASCENDING_NORTH: {
                        return state.with(SHAPE, RailShape.NORTH_SOUTH);
                    }
                    case ASCENDING_SOUTH: {
                        return state.with(SHAPE, RailShape.NORTH_SOUTH);
                    }
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
                    case ASCENDING_NORTH: {
                        return state.with(SHAPE, RailShape.NORTH_SOUTH);
                    }
                    case ASCENDING_SOUTH: {
                        return state.with(SHAPE, RailShape.NORTH_SOUTH);
                    }
                }
                break;
            }
            case FRONT_BACK: {
                switch (railShape) {
                    case ASCENDING_EAST: {
                        return state.with(SHAPE, RailShape.EAST_WEST);
                    }
                    case ASCENDING_WEST: {
                        return state.with(SHAPE, RailShape.EAST_WEST);
                    }
                }
                break;
            }
        }
        return super.mirror(state, mirror);
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SHAPE, WATERLOGGED);
    }

    static {
        SHAPE = Properties.RAIL_SHAPE;
    }
}
