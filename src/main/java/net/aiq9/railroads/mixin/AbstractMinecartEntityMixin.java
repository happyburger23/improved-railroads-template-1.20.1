package net.aiq9.railroads.mixin;

import net.minecraft.entity.vehicle.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractMinecartEntity.class)
public abstract class AbstractMinecartEntityMixin {

    /*
    @Inject(at = @At("HEAD"), method= "create", cancellable = true)
    private void create(World world, double x, double y, double z, AbstractMinecartEntity.Type type, CallbackInfoReturnable<AbstractMinecartEntity> cir) {

        if (type == AbstractMinecartEntity.Type.CHEST) {
            return new ChestMinecartEntity(world, x, y, z);
        }
        if (type == AbstractMinecartEntity.Type.FURNACE) {
            return new FurnaceMinecartEntity(world, x, y, z);
        }
        if (type == AbstractMinecartEntity.Type.TNT) {
            return new TntMinecartEntity(world, x, y, z);
        }
        if (type == AbstractMinecartEntity.Type.HOPPER) {
            return new HopperMinecartEntity(world, x, y, z);
        }

        return new MinecartEntity(world, x, y, z);
    }
     */
}
