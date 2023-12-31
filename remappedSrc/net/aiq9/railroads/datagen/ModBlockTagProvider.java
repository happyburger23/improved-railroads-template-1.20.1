package net.aiq9.railroads.datagen;

import net.aiq9.railroads.block.ModBlocks;
import net.aiq9.railroads.util.CustomTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    //CREATES BLOCK TAG FILES

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(CustomTags.Blocks.METAL_DETECTOR_DETECTABLE_BLOCKS)
                .forceAddTag(BlockTags.GOLD_ORES)
                .forceAddTag(BlockTags.EMERALD_ORES)
                .forceAddTag(BlockTags.REDSTONE_ORES)
                .forceAddTag(BlockTags.LAPIS_ORES)
                .forceAddTag(BlockTags.DIAMOND_ORES)
                .forceAddTag(BlockTags.IRON_ORES)
                .forceAddTag(BlockTags.COPPER_ORES)
                .forceAddTag(BlockTags.COAL_ORES);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.IRON_FRAMEWORK)
                .add(ModBlocks.PETROLEUM_ORE)
                .add(ModBlocks.RAIL_CRAFTING_TABLE)
                .add(ModBlocks.CART_CRAFTING_TABLE)
                .add(ModBlocks.COPPER_RAIL);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.WOODEN_RAIL);

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.OIL_SANDS)
                .add(ModBlocks.BALLAST_BLOCK);

        getOrCreateTagBuilder(BlockTags.RAILS)
                .add(ModBlocks.WOODEN_RAIL)
                .add(ModBlocks.COPPER_RAIL)
                .add(ModBlocks.NOTE_BLOCK_RAIL);
    }
}
