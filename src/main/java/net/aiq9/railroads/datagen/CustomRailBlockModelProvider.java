package net.aiq9.railroads.datagen;

import net.aiq9.railroads.block.ModBlocks;
import net.aiq9.railroads.util.CustomBlockStateModelGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

/*
   THIS CLASS IS HERE TO LOAD THE CUSTOMBLOCKSTATEMODELGENERATOR METHODS
 */

public class CustomRailBlockModelProvider extends CustomBlockStateModelGenerator {

    public CustomRailBlockModelProvider(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(CustomBlockStateModelGenerator customBlockStateModelGenerator) {
        customBlockStateModelGenerator.registerIntersectionRail(ModBlocks.INTERSECTION_RAIL);
    }
}
