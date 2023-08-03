package net.aiq9.railroads.item;

import net.aiq9.railroads.ImprovedRailroads;
import net.aiq9.railroads.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
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
                    .icon(() -> new ItemStack(ModItems.TEST)).entries((displayContext, entries) -> {
                        entries.add(ModItems.TEST);
                        entries.add(ModItems.COUPLER);
                        entries.add(ModItems.RAW_STEEL);
                        entries.add(ModItems.STEEL_INGOT);
                        entries.add(ModBlocks.BALLAST_BLOCK);
                        entries.add(ModBlocks.IRON_FRAMEWORK);
                        entries.add(ModBlocks.STEEL_ORE);
                        entries.add(ModBlocks.DEEPSLATE_STEEL_ORE);
                        entries.add(ModBlocks.STEEL_BLOCK);

                    }).build());

    public static void registerItemGroups() {
        ImprovedRailroads.LOGGER.info("Registering Mod ItemGroups for " + ImprovedRailroads.MOD_ID);
    }
}
