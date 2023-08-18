package net.aiq9.railroads.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.FurnaceMinecartEntity;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FurnaceMinecartEntity.class)
public abstract class FurnaceMinecartEntityMixin extends AbstractMinecartEntity {
    @Shadow private int fuel;
    @Shadow @Final @Mutable private static Ingredient ACCEPTABLE_FUEL;
    @Shadow public abstract ActionResult interact(PlayerEntity player, Hand hand);

    protected FurnaceMinecartEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    static {
        ACCEPTABLE_FUEL = Ingredient.ofItems(new ItemConvertible[]{
                Items.COAL, Items.CHARCOAL, Items.COAL_BLOCK, Items.COAL_BLOCK,

                //special items
                Items.BLAZE_ROD, Items.BLAZE_POWDER, Items.LAVA_BUCKET
        });
    }

}
