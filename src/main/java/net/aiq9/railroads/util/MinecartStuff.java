package net.aiq9.railroads.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.ItemStack;

public interface MinecartStuff {

    //controls wether or not player can link a minecart
    public boolean canLink(PlayerEntity player, ItemStack coupler, MinecartEntity minecartEntity);

    //controls what happens when a minecart is linked
    public boolean onLink(PlayerEntity player, ItemStack coupler, MinecartEntity minecartEntity);
}
