package net.aiq9.railroads.block.custom;

import net.aiq9.railroads.mixin.AbstractMinecartEntityMixin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RailBlock;
import net.minecraft.block.enums.RailShape;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class IntersectionRailBlock extends RailBlock {
    public static EnumProperty<RailShape> SHAPE;

    public IntersectionRailBlock(Settings settings) {
        super(settings);
        this.setDefaultState(((this.stateManager.getDefaultState()).with(SHAPE, RailShape.NORTH_SOUTH)).with(WATERLOGGED, false));
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
    public void updateBlockState(BlockState state, World world, BlockPos pos, Block neighbor) {
        /*
        if (world.isClient) {
            world.setBlockState(pos, state.with(RailShape.), Block.NOTIFY_ALL);
            world.updateNeighborsAlways(pos.down(), this);
        }
        */
        super.updateBlockState(state, world, pos, neighbor);
    }

    @Override
    public Property<RailShape> getShapeProperty() {
        return SHAPE;
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
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SHAPE, WATERLOGGED);
    }

    static {
        SHAPE = Properties.RAIL_SHAPE;
    }
}
