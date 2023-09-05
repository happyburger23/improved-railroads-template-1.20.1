package net.aiq9.railroads.item.custom;

import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.enums.RailShape;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.text.Normalizer;
import java.util.List;

public class WoodenMinecartItem extends Item {
    public WoodenMinecartItem(Settings settings) {
        super(settings);
        DispenserBlock.registerBehavior(this, new ItemDispenserBehavior());
    }

    //spawns minecart entity when placed on any RailBlock
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getPlayer() instanceof ServerPlayerEntity player) {
            World world = context.getWorld();
            BlockPos blockPos = context.getBlockPos();
            BlockState blockState = world.getBlockState(blockPos);

            if (!blockState.isIn(BlockTags.RAILS)) {
                return ActionResult.FAIL;
            } else {
                ItemStack itemStack = context.getStack();
                RailShape railShape = blockState.getBlock() instanceof AbstractRailBlock ? blockState.get(((AbstractRailBlock) blockState.getBlock()).getShapeProperty()) : RailShape.NORTH_SOUTH;
                double d = 0.0D;
                if (railShape.isAscending()) {
                    d = 0.5D;
                }

                var minecart = (MinecartEntity) AbstractMinecartEntity.create(world, (double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.0625D + d, (double) blockPos.getZ() + 0.5D, AbstractMinecartEntity.Type.RIDEABLE);
                if (itemStack.hasCustomName()) {
                    minecart.setCustomName(itemStack.getName());
                }
                itemStack.decrement(1);

                world.spawnEntity(minecart);
                world.emitGameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockPos);


                return ActionResult.success(true);
            }
        }

        return super.useOnBlock(context);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Spawns regular minecart until new types are added").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("FUTURE FEATURE").formatted(Formatting.RED));

        super.appendTooltip(stack, world, tooltip, context);
    }
}
