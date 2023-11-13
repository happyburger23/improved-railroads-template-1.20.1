package net.aiq9.railroads.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecartEntity.class)
public abstract class MinecartEntityMixin extends AbstractMinecartEntity {
    protected MinecartEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    protected MinecartEntityMixin(EntityType<?> type, World world, double x, double y, double z) {
        super(type, world, x, y, z);
    }

    @Inject(at = @At("HEAD"), method = "interact")
    private void interact(PlayerEntity player, Hand hand, CallbackInfoReturnable<Boolean> info) {

        //check if player has interacted w/ entity
        if (player != null) {
            //apply resistance effect
            StatusEffectInstance existingEffect = player.getStatusEffect(StatusEffects.RESISTANCE);

            if (existingEffect == null && !player.isSneaking()) {
                StatusEffectInstance effectInstance =
                        new StatusEffectInstance(StatusEffects.RESISTANCE, 500, 2); //600 ticks is 30 seconds

                player.addStatusEffect(effectInstance);
            }
        }
    }
}
