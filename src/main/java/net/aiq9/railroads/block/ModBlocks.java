package net.aiq9.railroads.block;

import net.aiq9.railroads.ImprovedRailroads;
import net.aiq9.railroads.block.custom.IntersectionRailBlock;
import net.aiq9.railroads.block.custom.SoundBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.RailBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block BALLAST_BLOCK = registerBlock("ballast",
            new Block(FabricBlockSettings.copyOf(Blocks.GRAVEL).sounds(BlockSoundGroup.ROOTED_DIRT))); //use .create() for custom settings
    public static final Block IRON_FRAMEWORK = registerBlock("iron_framework",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));
    public static final Block STEEL_ORE = registerBlock("steel_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE), UniformIntProvider.create(2, 5)));
    public static final Block DEEPSLATE_STEEL_ORE = registerBlock("deepslate_steel_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE), UniformIntProvider.create(2, 5)));
    public static final Block STEEL_BLOCK = registerBlock("steel_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block SOUND_BLOCK = registerBlock("sound_block",
            new SoundBlock(FabricBlockSettings.copyOf(Blocks.NOTE_BLOCK)));
    public static final RailBlock INTERSECTION_RAIL = registerBlock("rail_intersection",
            new IntersectionRailBlock(FabricBlockSettings.copyOf(Blocks.RAIL)));

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
