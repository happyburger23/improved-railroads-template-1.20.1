package net.aiq9.railroads.datagen;

import net.aiq9.railroads.block.ModBlocks;
import net.aiq9.railroads.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    //generates blockstates and block models
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BALLAST_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.IRON_FRAMEWORK);

        blockStateModelGenerator.registerCubeWithCustomTextures(ModBlocks.RAIL_CRAFTING_TABLE, Blocks.CRAFTING_TABLE, TextureMap::frontSideWithCustomBottom);
        blockStateModelGenerator.registerCubeWithCustomTextures(ModBlocks.CART_CRAFTING_TABLE, Blocks.CRAFTING_TABLE, TextureMap::frontSideWithCustomBottom);

        //blockStateModelGenerator.registerStraightRail(ModBlocks.INTERSECTION_RAIL);
        //blockStateModelGenerator.registerStraightRail(ModBlocks.NOTE_BLOCK_RAIL);

        //blockStateModelGenerator.registerStraightRail(ModBlocks.WOODEN_RAIL);
        //blockStateModelGenerator.registerTurnableRail(ModBlocks.WOODEN_RAIL);

        //blockStateModelGenerator.registerStraightRail(ModBlocks.COPPER_RAIL);
        //blockStateModelGenerator.registerTurnableRail(ModBlocks.COPPER_RAIL);
    }

    //generates item models
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.COUPLER, Models.GENERATED);
        itemModelGenerator.register(ModItems.METAL_DETECTOR, Models.GENERATED);

        itemModelGenerator.register(ModItems.WOODEN_MINECART, Models.GENERATED);
        itemModelGenerator.register(ModItems.WOODEN_MINECART_CHEST, Models.GENERATED);
        itemModelGenerator.register(ModItems.WOODEN_MINECART_FURNACE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WOODEN_MINECART_HOPPER, Models.GENERATED);
        itemModelGenerator.register(ModItems.WOODEN_MINECART_TNT, Models.GENERATED);
    }
}
