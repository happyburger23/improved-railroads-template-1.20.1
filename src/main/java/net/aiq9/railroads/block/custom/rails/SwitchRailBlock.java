package net.aiq9.railroads.block.custom.rails;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.block.enums.RailShape;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SwitchRailBlock extends PoweredRailBlock {
    public static final EnumProperty<RailShape> SHAPE = EnumProperty.of("shape", RailShape.class, shape ->
            shape != RailShape.ASCENDING_NORTH && shape != RailShape.ASCENDING_EAST && shape != RailShape.ASCENDING_SOUTH && shape != RailShape.ASCENDING_WEST &&
                    shape != RailShape.NORTH_EAST && shape != RailShape.NORTH_WEST && shape != RailShape.SOUTH_EAST && shape != RailShape.SOUTH_WEST);

    public static final DirectionProperty FACING;
    public static final BooleanProperty FLIPPED;
    public static final BooleanProperty POWERED;

    public SwitchRailBlock(Settings settings) {
        super(settings);

        this.setDefaultState(((this.stateManager.getDefaultState())
                .with(FACING, Direction.NORTH)
                .with(FLIPPED, false)
                .with(POWERED, false)
                .with(SHAPE, RailShape.NORTH_SOUTH)
                .with(WATERLOGGED, false))
        );
    }

    @Override
    public boolean cannotMakeCurves() {
        return true;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        switch (rotation) {
            case COUNTERCLOCKWISE_90: {
                switch (state.get(SHAPE)) {
                    case NORTH_SOUTH -> state.with(SHAPE, RailShape.EAST_WEST);
                    case EAST_WEST -> state.with(SHAPE, RailShape.NORTH_SOUTH);
                }
            }
            case CLOCKWISE_90: {
                switch (state.get(SHAPE)) {
                    case NORTH_SOUTH -> state.with(SHAPE, RailShape.EAST_WEST);
                    case EAST_WEST -> state.with(SHAPE, RailShape.NORTH_SOUTH);
                }
            }
        }
        return state;
    }

    //plays piston_extend sound and will eventually flip the blockstate when clicked
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            //world.setBlockState(pos, state.cycle(FLIPPED));

            world.playSound(null, pos, SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }

        return ActionResult.success(world.isClient);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public Property<RailShape> getShapeProperty() {
        return SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SHAPE, FACING, FLIPPED, POWERED, WATERLOGGED);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.literal("Can switch minecarts from one track to another").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("Flip block by right-clicking").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("DO NOT PLACE IN SLOPE").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("FUTURE FEATURE").formatted(Formatting.RED));
        super.appendTooltip(stack, world, tooltip, options);
    }

    static {
        FACING = Properties.HORIZONTAL_FACING;
        FLIPPED = BooleanProperty.of("flipped");
        POWERED = Properties.POWERED;
    }
}
