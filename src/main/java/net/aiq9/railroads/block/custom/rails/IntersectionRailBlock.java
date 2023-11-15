package net.aiq9.railroads.block.custom.rails;

import net.minecraft.block.*;
import net.minecraft.block.enums.RailShape;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockRotation;

public class IntersectionRailBlock extends AbstractRailBlock {
    public static final EnumProperty<RailShape> SHAPE = EnumProperty.of("shape", RailShape.class, shape -> shape
            != RailShape.ASCENDING_NORTH && shape != RailShape.ASCENDING_EAST && shape != RailShape.ASCENDING_SOUTH && shape != RailShape.ASCENDING_WEST &&
            shape != RailShape.NORTH_EAST && shape != RailShape.NORTH_WEST && shape != RailShape.SOUTH_EAST && shape != RailShape.SOUTH_WEST);

    public IntersectionRailBlock(Settings settings) {
        super(true, settings);

        this.setDefaultState((this.stateManager.getDefaultState())
                .with(SHAPE, RailShape.NORTH_SOUTH)
                .with(WATERLOGGED, false)
        );
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        switch (rotation) {
            case COUNTERCLOCKWISE_90:
                switch (state.get(SHAPE)) {
                    case NORTH_SOUTH:
                        return state.with(SHAPE, RailShape.EAST_WEST);
                    case EAST_WEST:
                        return state.with(SHAPE, RailShape.NORTH_SOUTH);
                }
            case CLOCKWISE_90:
                switch (state.get(SHAPE)) {
                    case NORTH_SOUTH:
                        return state.with(SHAPE, RailShape.EAST_WEST);
                    case EAST_WEST:
                        return state.with(SHAPE, RailShape.NORTH_SOUTH);
                }
            default:
                return state;
        }
    }

    /*
    public static void railIntersectionShape(AbstractMinecartEntity minecart, World world) {
        BlockState state = world.getBlockState(new BlockPos());
        BlockPos pos = minecart.getBlockPos();
        Direction direction = Direction.getFacing(minecart.getVelocity().getX(), 0, minecart.getVelocity().getZ());

        if (state.isIn(BlockTags.RAILS) && state.getBlock() instanceof AbstractRailBlock railBlock) {
            RailShape railShape = state.get(railBlock.getShapeProperty());

            if (railShape instanceof IntersectionRailBlock && minecart.getVelocity().horizontalLength() > 0) {
                if (railShape == RailShape.NORTH_SOUTH && (direction == Direction.EAST || direction == Direction.WEST)) {
                    world.setBlockState(pos, state.with(railBlock.getShapeProperty(), RailShape.EAST_WEST));
                }

                if (railShape == RailShape.EAST_WEST && (direction == Direction.NORTH || direction == Direction.SOUTH)) {
                    world.setBlockState(pos, state.with(railBlock.getShapeProperty(), RailShape.NORTH_SOUTH));
                }
            }
        }
    }
    */

    @Override
    public Property<RailShape> getShapeProperty() {
        return SHAPE;
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SHAPE, WATERLOGGED);
    }
}
