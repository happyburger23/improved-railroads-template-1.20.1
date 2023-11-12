package net.aiq9.railroads.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CouplerItem extends Item {
    public CouplerItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        HitResult hitResult = player.raycast(5.0D, 0.0F, false);
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            Entity entity = ((EntityHitResult) hitResult).getEntity();
            if (entity instanceof MinecartEntity) {
                MinecartEntity minecart = (MinecartEntity) entity;

                // Calculate the direction the player is facing
                double pushX = -Math.sin(Math.toRadians(player.getYaw()));
                double pushZ = Math.cos(Math.toRadians(player.getYaw()));

                // Apply a force to the minecart
                minecart.addVelocity(pushX * 1.5, 0.0, pushZ * 1.5);

                // Consume the item
                if (!player.isCreative()) {
                    stack.decrement(1);
                }

                return TypedActionResult.success(stack);
            }
        }

        return TypedActionResult.pass(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Right click to link two minecarts").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("FUTURE FEATURE").formatted(Formatting.RED));
        tooltip.add(Text.literal("32 Uses").formatted(Formatting.DARK_GREEN));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
