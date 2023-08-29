package net.aiq9.railroads.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MinecartEntity.class)
public abstract class MinecartEntityMixin extends AbstractMinecartEntity {
    protected MinecartEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    protected MinecartEntityMixin(EntityType<?> type, World world, double x, double y, double z) {
        super(type, world, x, y, z);
    }

    /*
    @Inject(at = @At("HEAD"), method = "interact", cancellable = true)
    public void interact(PlayerEntity player, Hand hand, CallbackInfo info) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (itemStack.isOf(ModItems.COUPLER)) {
            player.playSound(SoundEvents.BLOCK_ANVIL_USE, 1.0f, 1.0f);
        }
    }
    */
}
