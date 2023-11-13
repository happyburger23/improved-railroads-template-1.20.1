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
    public static final Item METAL_DETECTOR = registerItem("metal_detector", new MetalDetectorItem(new FabricItemSettings().maxCount(1).maxDamage(64)));

    public static final Item WOODEN_MINECART = registerItem("wooden_minecart", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item WOODEN_MINECART_CHEST = registerItem("wooden_chest_minecart", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item WOODEN_MINECART_FURNACE = registerItem("wooden_furnace_minecart", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item WOODEN_MINECART_HOPPER = registerItem("wooden_hopper_minecart", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item WOODEN_MINECART_TNT = registerItem("wooden_tnt_minecart", new Item(new FabricItemSettings().maxCount(1)));

    //registers items
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ImprovedRailroads.MOD_ID, name), item);
    }

    //registers items and what creative tab they go in
    public static void registerItems() {
        ImprovedRailroads.LOGGER.info("Registering Mod Items for " + ImprovedRailroads.MOD_ID);
    }
}
