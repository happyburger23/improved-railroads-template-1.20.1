package net.aiq9.railroads.item;

import net.aiq9.railroads.ImprovedRailroads;
import net.aiq9.railroads.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    //creative tab creation
    public static final ItemGroup RAILROADS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(ImprovedRailroads.MOD_ID, "railroads_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.railroads_group"))
                    .icon(() -> new ItemStack(ModItems.COUPLER)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.BALLAST_BLOCK);
                        entries.add(ModBlocks.IRON_FRAMEWORK);
                        entries.add(ModBlocks.STEEL_BLOCK);
                        entries.add(ModBlocks.SOUND_BLOCK);
                        entries.add(ModBlocks.RAIL_STOP);
                        entries.add(ModBlocks.RAIL_CRAFTING_TABLE);
                        entries.add(ModBlocks.CART_CRAFTING_TABLE);

                        entries.add(ModBlocks.INTERSECTION_RAIL);
                        entries.add(ModBlocks.WOODEN_RAIL);
                        entries.add(ModBlocks.COPPER_RAIL);
                        entries.add(ModBlocks.NOTE_BLOCK_RAIL);

                        entries.add(ModItems.METAL_DETECTOR);
                        entries.add(ModItems.COUPLER);

                        entries.add(ModItems.WOODEN_MINECART);
                        entries.add(ModItems.WOODEN_MINECART_HOPPER);
                        entries.add(ModItems.WOODEN_MINECART_CHEST);
                        entries.add(ModItems.WOODEN_MINECART_FURNACE);
                        entries.add(ModItems.WOODEN_MINECART_TNT);

                        entries.add(ModItems.STEEL_INGOT);

                    }).build());

    public static void registerItemGroups() {
        ImprovedRailroads.LOGGER.info("Registering Mod ItemGroups for " + ImprovedRailroads.MOD_ID);
    }
}
