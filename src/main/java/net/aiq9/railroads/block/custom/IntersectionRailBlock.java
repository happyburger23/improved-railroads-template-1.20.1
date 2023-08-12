package net.aiq9.railroads.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.enums.RailShape;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IntersectionRailBlock extends RailBlock {
    public static EnumProperty<RailShape> SHAPE = EnumProperty.of("shape", RailShape.class);
    public static BooleanProperty ASCENDING = BooleanProperty.of("ascending");

    public IntersectionRailBlock(Settings settings) {
        super(settings);
        this.setDefaultState(((this.stateManager.getDefaultState()).with(SHAPE, RailShape.NORTH_SOUTH).with(ASCENDING, false).with(WATERLOGGED, false))); //
    }

    //ChatGPT-created method. Prevents railblock from forming slopes. Tweaked 8/10/23
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(ASCENDING)) {
            return state.with(ASCENDING, false);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    //ChatGPT-created method. if-statements are reused from prior attempt. Made more efficient on 8/10/23
    private RailShape determineRailShape(WorldAccess world, BlockState state, Direction motion, AbstractMinecartEntity abstractMinecartEntity) {
        if (abstractMinecartEntity != null) {
            Direction movementDirection = abstractMinecartEntity.getMovementDirection();
            if (Math.abs(motion.getOffsetX()) > Math.abs(motion.getOffsetZ())) {
                return (movementDirection == Direction.EAST || movementDirection == Direction.WEST)
                        ? RailShape.EAST_WEST
                        : RailShape.NORTH_SOUTH;
            } else {
                return (movementDirection == Direction.NORTH || movementDirection == Direction.SOUTH)
                        ? RailShape.NORTH_SOUTH
                        : RailShape.EAST_WEST;
            }
        }

        return state.get(SHAPE); //return current shape if no minecart
    }

    //uses determineRailShape. //ChatGPT-created
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        WorldAccess world = ctx.getWorld();
        BlockState currentState = world.getBlockState(blockPos);

        Direction motionDirection = ctx.getPlayerLookDirection().getOpposite(); //may need to adjust?
        RailShape newShape = determineRailShape(world, currentState, motionDirection, null); //passing null for abstractMinecartEntity

        return currentState.with(SHAPE, newShape);
    }

    //ripped from AbstractRailBlock
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        RailShape railShape;
        RailShape railShape2 = railShape = state.isOf(this) ? state.get(this.getShapeProperty()) : null;

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
        return RailBlock.SHAPE;
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SHAPE, WATERLOGGED, ASCENDING);
    }

    //tooltip
    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.literal("Allows two at-grade rail lines to cross one another.").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("FUTURE FEATURE").formatted(Formatting.RED));
        super.appendTooltip(stack, world, tooltip, options);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        switch (rotation) {
            case COUNTERCLOCKWISE_90: {
                switch (state.get(SHAPE)) {
                    case NORTH_SOUTH: {
                        return state.with(SHAPE, RailShape.NORTH_SOUTH);
                    }
                    case EAST_WEST: {
                        return state.with(SHAPE, RailShape.EAST_WEST);
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
                }
            }
        }
        return state;
    }

    static {
        SHAPE = Properties.RAIL_SHAPE;
    }
}
