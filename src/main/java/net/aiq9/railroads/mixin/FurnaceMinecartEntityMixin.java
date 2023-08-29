package net.aiq9.railroads.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.FurnaceMinecartEntity;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FurnaceMinecartEntity.class)
public abstract class FurnaceMinecartEntityMixin extends AbstractMinecartEntity {
    private static Ingredient ACCEPTABLE_FUEL;



    protected FurnaceMinecartEntityMixin(EntityType<?> type, World world, double x, double y, double z) {
        super(type, world, x, y, z);
    }

    protected FurnaceMinecartEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    static {
        ACCEPTABLE_FUEL = Ingredient.ofItems(new ItemConvertible[]{
                Items.COAL, Items.CHARCOAL, Items.COAL_BLOCK, Items.COAL_BLOCK,
        });
    }

}
