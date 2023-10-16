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
        addDrop(ModBlocks.WOODEN_RAIL);
        addDrop(ModBlocks.INTERSECTION_RAIL);
        addDrop(ModBlocks.BALLAST_BLOCK);
        addDrop(ModBlocks.IRON_FRAMEWORK);
        addDrop(ModBlocks.COPPER_RAIL);
        addDrop(ModBlocks.NOTE_BLOCK_RAIL);
        addDrop(ModBlocks.CART_CRAFTING_TABLE);
        addDrop(ModBlocks.RAIL_CRAFTING_TABLE);
    }

    /*
    public LootTable.Builder copperLikeOreDrops(Block drop, Item item) {
        return BlockLootTableGenerator.dropsWithSilkTouch(drop, (LootPoolEntry.Builder)this.applyExplosionDecay(drop,
                ((LeafEntry.Builder)ItemEntry.builder(item)
                        .apply(SetCountLootFunction
                                .builder(UniformLootNumberProvider
                                        .create(2.0f, 5.0f))))
                        .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
     */
}
