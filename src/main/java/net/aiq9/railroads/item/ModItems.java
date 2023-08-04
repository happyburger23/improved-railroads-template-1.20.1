package net.aiq9.railroads.item;

import net.aiq9.railroads.ImprovedRailroads;
import net.aiq9.railroads.item.custom.CouplerItem;
import net.aiq9.railroads.item.custom.MetalDetectorItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item COUPLER = registerItem("coupler", new CouplerItem(new FabricItemSettings().maxCount(1).maxDamage(32)));
    public static final Item RAW_STEEL = registerItem("raw_steel", new Item(new FabricItemSettings()));
    public static final Item STEEL_INGOT = registerItem("steel_ingot", new Item(new FabricItemSettings()));
    public static final Item METAL_DETECTOR = registerItem("metal_detector", new MetalDetectorItem(new FabricItemSettings().maxCount(1).maxDamage(64)));

    //creative tab implementation
    /*private static void addItemsToIngredientsItemGroup(FabricItemGroupEntries entries) {
        entries.add(TEST);
        entries.add(COUPLER);
    }*/

    //registers items
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ImprovedRailroads.MOD_ID, name), item);
    }

    //registers items and what creative tab they go in
    public static void registerItems() {
        ImprovedRailroads.LOGGER.info("Registering Mod Items for " + ImprovedRailroads.MOD_ID);

        //ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientsItemGroup);
    }
}
