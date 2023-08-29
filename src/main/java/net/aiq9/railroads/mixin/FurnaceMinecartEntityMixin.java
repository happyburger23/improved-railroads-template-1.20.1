package net.aiq9.railroads.mixin;

import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.FurnaceMinecartEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FurnaceMinecartEntity.class)
public abstract class FurnaceMinecartEntityMixin extends AbstractMinecartEntity {
    @Shadow private int fuel;

    @Inject(at = @At("HEAD"), method= "interact(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/ActionResult;")
    public void interact(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> ci) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (FurnaceBlockEntity.canUseAsFuel(itemStack)) {
            int value = FurnaceBlockEntity.createFuelTimeMap().get(itemStack.getItem()) * 2;

            if (this.fuel + value <= 32000) {
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }

                this.fuel += value;
            }
        }
    }

    protected FurnaceMinecartEntityMixin(EntityType<?> type, World world, double x, double y, double z) {
        super(type, world, x, y, z);
    }

    protected FurnaceMinecartEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }
}
