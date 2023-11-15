package net.aiq9.railroads.util;

import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
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
    public final BiConsumer<Identifier, Supplier<JsonElement>> customModelCollector = null;
    public final Consumer<BlockStateSupplier> customBlockStateCollector = null;

    public CustomBlockStateModelGenerator(FabricDataOutput output) {
    }

    public final Identifier createSubModel(Block block, String suffix, Model model, Function<Identifier, TextureMap> texturesFactory) {
        return model.upload(block, suffix, texturesFactory.apply(TextureMap.getSubId(block, suffix)), this.customModelCollector);
    }

    public void registerItemModel(Item item) {
        Models.GENERATED.upload(ModelIds.getItemModelId(item), TextureMap.layer0(item), this.customModelCollector);
    }

    //for things that cannot have corner blockstates, yet can be powered
    public final void registerIntersectionRail(Block rail) {
        Identifier identifier = this.createSubModel(rail, "", Models.RAIL_FLAT, TextureMap::rail);
        Identifier identifier2 = this.createSubModel(rail, "_on", Models.RAIL_FLAT, TextureMap::rail);
        BlockStateVariantMap blockStateVariantMap = BlockStateVariantMap.create(Properties.POWERED, Properties.STRAIGHT_RAIL_SHAPE).register((on, shape) -> {
            switch (shape) {
                case NORTH_SOUTH:
                    return BlockStateVariant.create().put(VariantSettings.MODEL, on ? identifier2 : identifier);
                case EAST_WEST:
                    return BlockStateVariant.create().put(VariantSettings.MODEL, on ? identifier2 : identifier).put(VariantSettings.Y, VariantSettings.Rotation.R90);
                default:
                    throw new UnsupportedOperationException("Fix your generator!");
            }
        });
        this.registerItemModel(rail.asItem());
        this.customBlockStateCollector.accept(VariantsBlockStateSupplier.create(rail).coordinate(blockStateVariantMap));
    }
}
