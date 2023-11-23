package net.aiq9.railroads.block;

import net.aiq9.railroads.ImprovedRailroads;
import net.aiq9.railroads.block.custom.*;
import net.aiq9.railroads.block.custom.rails.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block BALLAST_BLOCK = registerBlock("ballast",
            new Block(FabricBlockSettings.copyOf(Blocks.GRAVEL).sounds(BlockSoundGroup.ROOTED_DIRT))); //use .create() for custom settings

    public static final Block IRON_FRAMEWORK = registerBlock("iron_framework",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));

    public static final Block RAIL_CRAFTING_TABLE = registerBlock("rail_crafting_table",
            new RailCraftingTableBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));

    public static final Block MINECART_CRAFTING_TABLE = registerBlock("minecart_crafting_table",
            new CartCraftingTableBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));

    public static final Block OIL_SANDS = registerBlock("oil_sands",
            new Block(FabricBlockSettings.copyOf(Blocks.SAND)));
    public static final Block PETROLEUM_ORE = registerBlock("petroleum_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.COAL_ORE)));

    /*
        - DEV NOTES -

    Do not use RailBlock to register future rails.
    I don't remember that not being possible in 1.16 to 1.18?

    !!! RailBlocks that don't use/ignore blockstates should extend AbstractRailBlock !!!
     (IntersectionRailBlock is the exception, though)
    */

    public static final Block WOODEN_RAIL = registerBlock("wooden_rail",
            new WoodenRailBlock(FabricBlockSettings.copyOf(Blocks.RAIL).sounds(BlockSoundGroup.LADDER)));

    public static final Block COPPER_RAIL = registerBlock("copper_rail",
            new CopperRailBlock(FabricBlockSettings.copyOf(Blocks.RAIL).sounds(BlockSoundGroup.COPPER)));

    public static final Block NOTE_BLOCK_RAIL = registerBlock("note_block_rail",
            new NoteBlockRailBlock(FabricBlockSettings.copyOf(Blocks.RAIL)));

    public static final Block INTERSECTION_RAIL = registerBlock("intersection_rail",
            new IntersectionRailBlock(FabricBlockSettings.copyOf(Blocks.RAIL)));

    public static final Block TIELESS_RAIL = registerBlock("tieless_rail", //iron variant
            new RailBlock(FabricBlockSettings.copyOf(Blocks.RAIL)));
    public static final Block WOODEN_TIELESS_RAIL = registerBlock("wooden_tieless_rail",
            new RailBlock(FabricBlockSettings.copyOf(Blocks.RAIL)));
    public static final Block COPPER_TIELESS_RAIL = registerBlock("copper_tieless_rail",
            new RailBlock(FabricBlockSettings.copyOf(Blocks.RAIL)));

    //DO NOT PUT IN ITEMGROUP
    public static final Block RAIL_CRAFTING_TABLE_BOTTOM = registerBlock("rail_crafting_table_bottom",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block MINECART_CRAFTING_TABLE_BOTTOM = registerBlock("minecart_crafting_table_bottom",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));

    //registers blocks
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(ImprovedRailroads.MOD_ID, name), block);
    }

    //registers block items
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(ImprovedRailroads.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    //registers blocks
    public static void registerBlocks() {
        ImprovedRailroads.LOGGER.info("Registering Mod Blocks for " + ImprovedRailroads.MOD_ID);
    }
}
