package net.aiq9.railroads.datagen;

import net.aiq9.railroads.block.ModBlocks;
import net.aiq9.railroads.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    //generates blockstates and block models
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BALLAST_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.IRON_FRAMEWORK);

        //blockStateModelGenerator.registerCubeWithCustomTextures(ModBlocks.RAIL_CRAFTING_TABLE, Blocks.BLAST_FURNACE, TextureMap::frontSideWithCustomBottom);
        //blockStateModelGenerator.registerCubeWithCustomTextures(ModBlocks.CART_CRAFTING_TABLE, Blocks.DISPENSER, TextureMap::frontSideWithCustomBottom);

        blockStateModelGenerator.registerSingleton(ModBlocks.RAIL_CRAFTING_TABLE, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSingleton(ModBlocks.CART_CRAFTING_TABLE, TexturedModel.CUBE_BOTTOM_TOP);

        /*
          registerTurnableRail is used for standard, non-powered RailBlocks.
          registerStraightRail is for RailBlocks that can be powered (powered rails, activator rails, etc.)

          Interesting tip to self:
          registerStraightRail can be tricked into working by extending PoweredRailBlock and setting POWERED to false.
         */

        blockStateModelGenerator.registerStraightRail(ModBlocks.INTERSECTION_RAIL);
        blockStateModelGenerator.registerStraightRail(ModBlocks.NOTE_BLOCK_RAIL);

        blockStateModelGenerator.registerTurnableRail(ModBlocks.WOODEN_RAIL);
        blockStateModelGenerator.registerTurnableRail(ModBlocks.COPPER_RAIL);
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
