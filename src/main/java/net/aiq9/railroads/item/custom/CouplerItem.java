package net.aiq9.railroads.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CouplerItem extends Item {
    public CouplerItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Right click to link two minecarts").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("FUTURE FEATURE").formatted(Formatting.RED));
        tooltip.add(Text.literal("32 Uses").formatted(Formatting.DARK_GREEN));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
