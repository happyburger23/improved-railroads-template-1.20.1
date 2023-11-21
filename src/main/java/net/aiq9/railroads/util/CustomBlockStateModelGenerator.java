package net.aiq9.railroads.util;

import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.block.enums.RailShape;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/*
   THIS CLASS IS HERE TO GENERATE RAILBLOCKS THAT CANNOT BE GENERATED VIA THE VANILLA METHODS
 */

public class CustomBlockStateModelGenerator {
    public final Consumer<BlockStateSupplier> blockStateCollector;
    public final BiConsumer<Identifier, Supplier<JsonElement>> modelCollector;

    public CustomBlockStateModelGenerator(FabricDataOutput output) {
        this.blockStateCollector = blockStateCollector;
        this.modelCollector = modelCollector;
    }

    public final Identifier createSubModel(Block block, String suffix, Model model, Function<Identifier, TextureMap> texturesFactory) {
        return model.upload(block, suffix, texturesFactory.apply(TextureMap.getSubId(block, suffix)), this.customModelCollector);
    }

    public void registerItemModel(Item item) {
        Models.GENERATED.upload(ModelIds.getItemModelId(item), TextureMap.layer0(item), this.customModelCollector);
    }

    //for IntersectionRailBlocks
    public final void registerIntersectionRail(Block rail) {
        TextureMap textureMap = TextureMap.rail(rail);
        Identifier identifier = Models.RAIL_FLAT.upload(rail, textureMap, this.modelCollector);
        this.registerItemModel(rail.asItem());
        this.blockStateCollector.accept(VariantsBlockStateSupplier.create(rail).coordinate(BlockStateVariantMap.create(Properties.RAIL_SHAPE).register(RailShape.NORTH_SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, identifier)).register(RailShape.EAST_WEST, BlockStateVariant.create();
    }
}
