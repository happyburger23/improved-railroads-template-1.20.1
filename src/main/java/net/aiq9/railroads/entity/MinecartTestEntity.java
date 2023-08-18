package net.aiq9.railroads.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class MinecartTestEntity extends MinecartEntity {
    public MinecartTestEntity(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    public MinecartTestEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        return super.interact(player, hand);
    }

    @Override
    protected Item getItem() {
        return super.getItem();
    }

    @Override
    public void onActivatorRail(int x, int y, int z, boolean powered) {
        super.onActivatorRail(x, y, z, powered);
    }

    @Override
    public Type getMinecartType() {
        return super.getMinecartType();
    }
}
