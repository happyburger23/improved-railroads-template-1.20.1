package net.aiq9.railroads.block.custom.rails;

import net.aiq9.railroads.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.enums.RailShape;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IntersectionRailBlock extends AbstractRailBlock {
    public static final EnumProperty<RailShape> SHAPE = EnumProperty.of("shape", RailShape.class, shape ->
            shape != RailShape.ASCENDING_NORTH && shape != RailShape.ASCENDING_EAST && shape != RailShape.ASCENDING_SOUTH && shape != RailShape.ASCENDING_WEST &&
            shape != RailShape.NORTH_EAST && shape != RailShape.NORTH_WEST && shape != RailShape.SOUTH_EAST && shape != RailShape.SOUTH_WEST);

    public IntersectionRailBlock(Settings settings) {
        super(true, settings);
        this.setDefaultState ((this.stateManager.getDefaultState()).with(SHAPE, RailShape.NORTH_SOUTH).with(WATERLOGGED, false));
    }

    //change shape on minecart collision
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient() && entity instanceof AbstractMinecartEntity) {
            AbstractMinecartEntity abstractMinecartEntity = (AbstractMinecartEntity) entity;

            //get minecart movement direction
            Direction direction = abstractMinecartEntity.getMovementDirection();

            //determine direction of minecart
            boolean isMovingNorthOrSouth = Math.abs(abstractMinecartEntity.getVelocity().x) < Math.abs(abstractMinecartEntity.getVelocity().z);

            //replace block with appropriate shape?
            if (isMovingNorthOrSouth) {
                world.setBlockState(pos, ModBlocks.INTERSECTION_RAIL.getDefaultState());
            } else {
                world.setBlockState(pos, ModBlocks.INTERSECTION_RAIL.getDefaultState());
            }

            /*
            //THIS METHOD CRASHES THE GAME - FIX AT SOME POINT?
            //get current shape
            RailShape currentShape = state.get(RailBlock.SHAPE);

            //calculate new shape based on minecart momentum
            RailShape newShape;
            if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                newShape = RailShape.NORTH_SOUTH;
            } else {
                newShape = RailShape.EAST_WEST;
            }

            //update state if shape needs to change
            if (currentShape != newShape) {
                BlockState newState = state.with(RailBlock.SHAPE, newShape);
                world.setBlockState(pos, newState);
            }*/
        }
    }

    //tooltip
    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.literal("DO NOT PLACE IN A SLOPE.").formatted(Formatting.GRAY));
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
