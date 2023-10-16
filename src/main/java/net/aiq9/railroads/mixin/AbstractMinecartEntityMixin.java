package net.aiq9.railroads.mixin;

import net.minecraft.entity.vehicle.*;
import org.spongepowered.asm.mixin.Mixin;

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
        if (type == AbstractMinecartEntity.Type.SPAWNER) {
            return new SpawnerMinecartEntity(world, x, y, z);
        }
        if (type == AbstractMinecartEntity.Type.HOPPER) {
            return new HopperMinecartEntity(world, x, y, z);
        }
        if (type == AbstractMinecartEntity.Type.COMMAND_BLOCK) {
            return new CommandBlockMinecartEntity(world, x, y, z);
        }
        return new MinecartEntity(world, x, y, z);
    }

    @Inject(at = @At("HEAD"), method= "moveOnRail", cancellable = true)
        protected void moveOnRail(BlockPos pos, BlockState state) { }


    public static enum Type {
        RIDEABLE,
        CHEST,
        FURNACE,
        TNT,
        SPAWNER,
        HOPPER,
        COMMAND_BLOCK;

    }
    */
}
