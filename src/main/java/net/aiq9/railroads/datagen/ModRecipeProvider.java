package net.aiq9.railroads.datagen;

import net.aiq9.railroads.block.ModBlocks;
import net.aiq9.railroads.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class ModRecipeProvider extends FabricRecipeProvider {

    //CREATES RECIPE FILES

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        //iron framework block
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_FRAMEWORK, 1)
                .pattern("i i")
                .pattern(" i ")
                .pattern("i i")
                .input('i', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.IRON_FRAMEWORK)));

        //rail crafting table
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAIL_CRAFTING_TABLE, 1)
                .pattern("rr ")
                .pattern("ss ")
                .pattern("ss ")
                .input('r', Items.RAIL)
                .input('s', Items.SMOOTH_STONE)
                .criterion(hasItem(Items.SMOOTH_STONE), conditionsFromItem(Items.SMOOTH_STONE))
                .criterion(hasItem(Items.RAIL), conditionsFromItem(Items.RAIL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.RAIL_CRAFTING_TABLE)));

        //cart crafting table
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MINECART_CRAFTING_TABLE, 1)
                .pattern("mm ")
                .pattern("ss ")
                .pattern("ss ")
                .input('m', Items.MINECART)
                .input('s', Items.SMOOTH_STONE)
                .criterion(hasItem(Items.MINECART), conditionsFromItem(Items.MINECART))
                .criterion(hasItem(Items.SMOOTH_STONE), conditionsFromItem(Items.SMOOTH_STONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.MINECART_CRAFTING_TABLE)));

        //wooden rail
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.WOODEN_RAIL, 16)
                .pattern("p p")
                .pattern("psp")
                .pattern("p p")
                .input('p', ItemTags.PLANKS)
                .input('s', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WOODEN_RAIL)));

        //copper rail
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.COPPER_RAIL, 16)
                .pattern("c c")
                .pattern("csc")
                .pattern("c c")
                .input('c', Items.COPPER_INGOT)
                .input('s', Items.STICK)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.COPPER_RAIL)));

        //intersection rail block
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.INTERSECTION_RAIL, 8)
                .pattern("isi")
                .pattern("sss")
                .pattern("isi")
                .input('i', Items.IRON_INGOT)
                .input('s', Items.STICK)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.INTERSECTION_RAIL)));

        //note block rail
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.NOTE_BLOCK_RAIL, 8)
                .pattern("ini")
                .pattern("isi")
                .pattern("i i")
                .input('n', Items.NOTE_BLOCK)
                .input('s', Items.STICK)
                .input('i', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.NOTE_BLOCK), conditionsFromItem(Items.NOTE_BLOCK))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.NOTE_BLOCK_RAIL)));

        //ballast block
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BALLAST_BLOCK, 4)
                .input(Blocks.GRAVEL)
                .input(Blocks.GRAVEL)
                .criterion(FabricRecipeProvider.hasItem(Blocks.GRAVEL), conditionsFromItem(Blocks.GRAVEL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.BALLAST_BLOCK)));

        //Wooden Minecarts
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WOODEN_MINECART, 1)
                .pattern("   ")
                .pattern("s s")
                .pattern("sss")
                .input('s', Blocks.OAK_SLAB)
                .criterion(hasItem(Blocks.OAK_SLAB), conditionsFromItem(Blocks.OAK_SLAB))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.WOODEN_MINECART)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WOODEN_MINECART_CHEST, 1)
                .input(Blocks.CHEST)
                .input(ModItems.WOODEN_MINECART)
                .criterion(hasItem(Blocks.CHEST), conditionsFromItem(Blocks.CHEST))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.WOODEN_MINECART_CHEST)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WOODEN_MINECART_FURNACE, 1)
                .input(Blocks.FURNACE)
                .input(ModItems.WOODEN_MINECART)
                .criterion(hasItem(ModItems.WOODEN_MINECART), conditionsFromItem(ModItems.WOODEN_MINECART))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.WOODEN_MINECART_FURNACE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WOODEN_MINECART_HOPPER, 1)
                .input(Blocks.HOPPER)
                .input(ModItems.WOODEN_MINECART)
                .criterion(FabricRecipeProvider.hasItem(ModItems.WOODEN_MINECART), conditionsFromItem(ModItems.WOODEN_MINECART))
                .criterion(FabricRecipeProvider.hasItem(Items.HOPPER), conditionsFromItem(Items.HOPPER))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.WOODEN_MINECART_HOPPER)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WOODEN_MINECART_TNT, 1)
                .input(Blocks.TNT)
                .input(ModItems.WOODEN_MINECART)
                .criterion(FabricRecipeProvider.hasItem(ModItems.WOODEN_MINECART), conditionsFromItem(ModItems.WOODEN_MINECART_TNT))
                .criterion(FabricRecipeProvider.hasItem(Items.TNT), conditionsFromItem(Items.TNT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.WOODEN_MINECART_TNT)));
    }
}
