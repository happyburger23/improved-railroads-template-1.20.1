package net.aiq9.railroads.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.ItemStack;

public class MinecartConnector implements MinecartStuff {
    @Override
    public boolean canLink(PlayerEntity player, ItemStack coupler, MinecartEntity minecartEntity) {
        return true;
    }

    @Override
    public boolean onLink(PlayerEntity player, ItemStack coupler, MinecartEntity minecartEntity) {
        return false;
    }
}
