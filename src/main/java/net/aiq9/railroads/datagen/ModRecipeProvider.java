package net.aiq9.railroads.datagen;

import net.aiq9.railroads.block.ModBlocks;
import net.aiq9.railroads.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> STEEL_SMELTABLES = List.of(ModItems.RAW_STEEL,
            ModBlocks.STEEL_ORE, ModBlocks.DEEPSLATE_STEEL_ORE);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, STEEL_SMELTABLES, RecipeCategory.MISC, ModItems.STEEL_INGOT,
                0.7f, 200, "steel_ingot");
        offerBlasting(exporter, STEEL_SMELTABLES, RecipeCategory.MISC, ModItems.STEEL_INGOT,
                0.7f, 100, "steel_ingot");
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.STEEL_INGOT,
                RecipeCategory.DECORATIONS, ModBlocks.STEEL_BLOCK);

        //raw steel
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.RAW_STEEL_BLOCK, 1)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.RAW_STEEL)
                .criterion(hasItem(ModItems.RAW_STEEL), conditionsFromItem(ModItems.RAW_STEEL))
                //pass 2nd .criterion() for multiple-item shaped recipes
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.RAW_STEEL_BLOCK)));

        //iron framework block
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.IRON_FRAMEWORK, 1)
                .pattern("i i")
                .pattern(" i ")
                .pattern("i i")
                .input('i', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                //pass 2nd .criterion() for multiple-item shaped recipes
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.IRON_FRAMEWORK)));

        //rail intersection block
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.INTERSECTION_RAIL, 8)
                .pattern(" r ")
                .pattern("rrr")
                .pattern(" r ")
                .input('r', Items.RAIL)
                .criterion(hasItem(Items.RAIL), conditionsFromItem(Items.RAIL))
                //pass 2nd .criterion() for multiple-item shaped recipes
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.INTERSECTION_RAIL)));
    }
}
