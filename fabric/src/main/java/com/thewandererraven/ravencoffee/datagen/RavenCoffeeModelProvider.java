package com.thewandererraven.ravencoffee.datagen;

import com.thewandererraven.ravencoffee.blocks.*;
import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RavenCoffeeModelProvider extends FabricModelProvider {
    public RavenCoffeeModelProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(RavenCoffeeBlocks.BROWNIE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(RavenCoffeeBlocks.COFFEE_BEANS_ROASTED_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(RavenCoffeeBlocks.COFFEE_BEANS_MAGMA_BLOCK);

        registerCoffeeTree(blockStateModelGenerator, RavenCoffeeBlocks.COFFEE_TREE_LEAVES_BLOCK, CoffeeTreeLeavesBlock.AGE, 0, 1, 2, 3);
        registerCoffeeTree(blockStateModelGenerator, RavenCoffeeBlocks.COFFEE_TREE_TRUNK_BLOCK, CoffeeTreeLeavesBlock.AGE, 0, 1, 2, 3);

        //TODO: UPDATE PROPERTY VALUES FOR NEW VERSIONS OF MINECRAFT (1.21+)
        //registerBlockWithIntProperty(blockStateModelGenerator, RavenCoffeeBlocks.TIRAMISU_BLOCK, "_", TiramisuBlock.SLICES, true);
        //registerCakelikeBlock(blockStateModelGenerator, RavenCoffeeBlocks.TIRAMISU_BLOCK, 16);
        //registerCakelikeBlock(blockStateModelGenerator, RavenCoffeeBlocks.ROSCA_DE_REYES_BLOCK, 12);

        registerBlockWithIntProperty(blockStateModelGenerator, RavenCoffeeBlocks.SACK_BLOCK, "_", SackBlock.FULLNESS, true);

        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(RavenCoffeeBlocks.COFFEE_GRINDER);
        //blockStateModelGenerator.registerNorthDefaultHorizontalRotation(RavenCoffeeBlocks.COFFEE_MACHINE_BLOCK);
        //blockStateModelGenerator.registerNorthDefaultHorizontalRotation(RavenCoffeeBlocks.STACKING_CUPS_BLOCK);

    }


    private void registerCakelikeBlock(BlockStateModelGenerator blockStateModelGenerator, Block cake, int sliceCount) {
        BlockStateVariantMap.SingleProperty variantsSupplier = BlockStateVariantMap.create(((ICakeLikeBlock)cake).getBitesProperty());
        for(int i = 1; i <= sliceCount; i++) {
            variantsSupplier = variantsSupplier.register(i, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(cake, "_".concat(String.valueOf(i)))));
        }
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(cake).coordinate(variantsSupplier)
        );
    }

    public final void registerCoffeeTree(BlockStateModelGenerator blockStateModelGenerator, Block treePart, Property<Integer> ageProperty, int... ageTextureIndices) {
        if (ageProperty.getValues().size() != ageTextureIndices.length) {
            throw new IllegalArgumentException();
        } else {
            Int2ObjectMap<Identifier> int2ObjectMap = new Int2ObjectOpenHashMap();
            BlockStateVariantMap blockStateVariantMap = BlockStateVariantMap.create(ageProperty).register((integer) -> {
                int i = ageTextureIndices[integer];
                Identifier identifier = (Identifier)int2ObjectMap.computeIfAbsent(i, (j) -> {
                    return blockStateModelGenerator.createSubModel(treePart, "_stage" + i, Models.CROSS, TextureMap::cross);
                });
                return BlockStateVariant.create().put(VariantSettings.MODEL, identifier);
            });
            blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(treePart).coordinate(blockStateVariantMap));
        }
    }

    public final void registerBlockWithIntProperty(BlockStateModelGenerator blockStateModelGenerator, Block block, String subModelString, Property<Integer> intProperty, boolean zeroIsDefault) {
        BlockStateVariantMap.SingleProperty variantsSupplier = BlockStateVariantMap.create(intProperty)
                .register(0, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(block, zeroIsDefault ? "" : subModelString.concat("0"))));
        for(int i = 1; i < intProperty.stream().count(); i++) {
            variantsSupplier = variantsSupplier.register(i, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(block, subModelString.concat(String.valueOf(i)))));
        }
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(block).coordinate(variantsSupplier)
        );
    }
    public final void registerBlockWithIntProperty(BlockStateModelGenerator blockStateModelGenerator, Block block, String subModelString, Property<Integer> intProperty) {
        registerBlockWithIntProperty(blockStateModelGenerator, block, subModelString, intProperty, false);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        registerItem(itemModelGenerator, RavenCoffeeItems.COFFEE_CHERRIES);
        registerItem(itemModelGenerator, RavenCoffeeItems.COFFEE_BEANS);
        registerItem(itemModelGenerator, RavenCoffeeItems.COFFEE_BEANS_ROASTED);
        registerItem(itemModelGenerator, RavenCoffeeItems.COFFEE_BEANS_MAGMA);
        registerItem(itemModelGenerator, RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND);
        registerItem(itemModelGenerator, RavenCoffeeItems.COFFEE_BEANS_MAGMA_GROUND);

        registerItem(itemModelGenerator, "food", RavenCoffeeItems.POPCHORUS);
        registerItem(itemModelGenerator, "food", RavenCoffeeItems.MUFFIN);
        registerItem(itemModelGenerator, "food", RavenCoffeeItems.MELON_PAN);
        registerItem(itemModelGenerator, "food", RavenCoffeeItems.COFFEE_ECLAIR);
        registerItem(itemModelGenerator, "food", RavenCoffeeItems.BROWNIE);
        registerItem(itemModelGenerator, "food", RavenCoffeeItems.TIRAMISU_SLICE);

        registerItem(itemModelGenerator, "food", RavenCoffeeItems.SANDWICH_HAM);
        registerItem(itemModelGenerator, "food", RavenCoffeeItems.SANDWICH_BEEF);
        registerItem(itemModelGenerator, "food", RavenCoffeeItems.SANDWICH_CHICKEN);
        registerItem(itemModelGenerator, "food", RavenCoffeeItems.CROISSANT);
        registerItem(itemModelGenerator, "food", RavenCoffeeItems.CROISSANT_HAM);
        registerItem(itemModelGenerator, "food", RavenCoffeeItems.CROISSANT_BEEF);
        registerItem(itemModelGenerator, "food", RavenCoffeeItems.CROISSANT_CHICKEN);
        registerItem(itemModelGenerator, "food", RavenCoffeeItems.BAGEL);
        registerItem(itemModelGenerator, "food", RavenCoffeeItems.BAGEL_HAM);
        registerItem(itemModelGenerator, "food", RavenCoffeeItems.BAGEL_BEEF);
        registerItem(itemModelGenerator, "food", RavenCoffeeItems.BAGEL_CHICKEN);

        registerItem(itemModelGenerator, RavenCoffeeItems.CUP_SMALL_UNFIRED, "ravencoffee:item/cup_small/cup");
        registerItem(itemModelGenerator, RavenCoffeeItems.CUP_SMALL, "ravencoffee:item/cup_small/cup");
        registerItem(itemModelGenerator, RavenCoffeeItems.CUP_MEDIUM_UNFIRED, "ravencoffee:item/cup_medium/cup");
        registerItem(itemModelGenerator, RavenCoffeeItems.CUP_MEDIUM, "ravencoffee:item/cup_medium/cup");
        registerItem(itemModelGenerator, RavenCoffeeItems.CUP_LARGE_UNFIRED, "ravencoffee:item/cup_large/cup");
        registerItem(itemModelGenerator, RavenCoffeeItems.CUP_LARGE, "ravencoffee:item/cup_large/cup");
        registerItem(itemModelGenerator, RavenCoffeeItems.COFFEE_MUG, "ravencoffee:item/coffee_mug/coffee_mug");

        registerItem(itemModelGenerator, RavenCoffeeItems.COFFEE_PLATES);
        registerItem(itemModelGenerator, RavenCoffeeItems.COFFEE_INGOT);
        registerItem(itemModelGenerator, RavenCoffeeItems.COFFEE_PICKAXE);
        registerItem(itemModelGenerator, RavenCoffeeItems.COFFEE_AXE);
        registerItem(itemModelGenerator, RavenCoffeeItems.COFFEE_HOE);
        registerItem(itemModelGenerator, RavenCoffeeItems.COFFEE_SHOVEL);
        registerItem(itemModelGenerator, RavenCoffeeItems.COFFEE_SWORD);
/*
        registerItem(itemModelGenerator, RavenCoffeeItems.BROWNIE_BLOCK_ITEM);
        registerItem(itemModelGenerator, RavenCoffeeItems.ROSCA_DE_REYES_BLOCK_ITEM);
        registerItem(itemModelGenerator, RavenCoffeeItems.TIRAMISU_BLOCK_ITEM);
        registerItem(itemModelGenerator, RavenCoffeeItems.COFFEE_BEANS_ROASTED_BLOCK_ITEM);
        registerItem(itemModelGenerator, RavenCoffeeItems.COFFEE_BEANS_MAGMA_BLOCK_ITEM);
        registerItem(itemModelGenerator, RavenCoffeeItems.COFFEE_GRINDER_ITEM);
        registerItem(itemModelGenerator, RavenCoffeeItems.COFFEE_MACHINE_ITEM);
        registerItem(itemModelGenerator, RavenCoffeeItems.SACK_BLOCK_ITEM);
 */
        // TODO: ADD BREWS
    }


    // TODO: REMOVE THESE BY UPDATING ITEMS IDS TO GROUP THEM
    public void registerItem(ItemModelGenerator itemModelGenerator, Item item, String textureLocation) {
        registerItemWithTexture(itemModelGenerator, textureLocation, item, Models.GENERATED);
    }
    public void registerItem(ItemModelGenerator itemModelGenerator, Item item) {
        itemModelGenerator.register(item, Models.GENERATED);
    }
    public void registerItem(ItemModelGenerator itemModelGenerator, Item item, Model model) {
        itemModelGenerator.register(item, model);
    }

    public void registerItem(ItemModelGenerator itemModelGenerator, String subFolder, Item item) {
        registerItem(itemModelGenerator, subFolder, item, Models.GENERATED);
    }

    public void registerItem(ItemModelGenerator itemModelGenerator, String subFolder, Item item, Model model) {
        model.upload(ModelIds.getItemModelId(item), TextureMap.layer0(getTextureIdOfSubFolder(subFolder, item)), itemModelGenerator.writer);
    }

    public void registerItemWithTexture(ItemModelGenerator itemModelGenerator, String textureLocation, Item item, Model model) {
        model.upload(ModelIds.getItemModelId(item), TextureMap.layer0(new Identifier(textureLocation)), itemModelGenerator.writer);
    }

    public static Identifier getTextureIdOfSubFolder(String subFolder, Item item) {
        return getTextureId("item/" + subFolder + "/", item);
    }

    public static Identifier getTextureId(String folder, Item item) {
        Identifier identifier = Registry.ITEM.getId(item);
        String namespace = identifier.getNamespace();
        String path = identifier.getPath();
        return new Identifier(namespace,  folder + path);
    }

}
