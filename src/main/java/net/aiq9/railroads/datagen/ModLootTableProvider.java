package net.aiq9.railroads.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.aiq9.railroads.block.ModBlocks;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    //CREATES LOOT TABLE FILES

    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.BALLAST_BLOCK);
        addDrop(ModBlocks.IRON_FRAMEWORK);
        addDrop(ModBlocks.OIL_SANDS);
        addDrop(ModBlocks.PETROLEUM_ORE);

        addDrop(ModBlocks.WOODEN_RAIL);
        addDrop(ModBlocks.COPPER_RAIL);
        addDrop(ModBlocks.NOTE_BLOCK_RAIL);

        addDrop(ModBlocks.MINECART_CRAFTING_TABLE);
        addDrop(ModBlocks.RAIL_CRAFTING_TABLE);
    }
}
