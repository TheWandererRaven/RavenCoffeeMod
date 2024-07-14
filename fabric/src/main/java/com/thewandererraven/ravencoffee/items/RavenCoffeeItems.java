package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RavenCoffeeItems {


    // ############################################### BASIC COFFEE STUFF ##############################################
    public static final Item COFFEE_CHERRIES = registerItem(
            "coffee_cherries",
            new CoffeeCherriesItem(
                    RavenCoffeeBlocks.COFFEE_TREE_TRUNK_BLOCK,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.GENERAL_TAB)
            )
    );

    public static final Item COFFEE_BEANS = registerItem(
            "coffee_beans",
            new Item(new FabricItemSettings().group(RavenCoffeeItemGroups.GENERAL_TAB))
    );

    public static final Item COFFEE_BEANS_ROASTED = registerItem(
            "coffee_beans_roasted",
            new Item(new FabricItemSettings().group(RavenCoffeeItemGroups.GENERAL_TAB))
    );

    public static final Item COFFEE_BEANS_MAGMA = registerItem(
            "coffee_beans_magma",
            new Item(new FabricItemSettings().group(RavenCoffeeItemGroups.GENERAL_TAB))
    );

    // ############################################### COFFEE INGREDIENTS ##############################################
    public static final Item COFFEE_BEANS_ROASTED_GROUND = registerItem(
            "coffee_beans_roasted_ground",
            new Item(new FabricItemSettings().group(RavenCoffeeItemGroups.GENERAL_TAB))
    );

    public static final Item COFFEE_BEANS_MAGMA_GROUND = registerItem(
            "coffee_beans_magma_ground",
            new Item(new FabricItemSettings().group(RavenCoffeeItemGroups.GENERAL_TAB))
    );

    // ############################################### FOODSTUFF ##############################################
    public static final Item POPCHORUS = registerItem(
            "popchorus",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB)
                    .food(new FoodComponent.Builder()
                            .hunger(12)
                            .saturationModifier(0.15f)
                            .build()
                    )
            )
    );
    public static final Item MUFFIN = registerItem(
            "muffin",
            new MuffinItem(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB)
                    .food(new FoodComponent.Builder()
                            .hunger(2)
                            .saturationModifier(0.5f/2f)
                            .build()
                    )
            )
    );
    public static final Item MELON_PAN = registerItem(
            "melon_pan",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB)
                    .food(new FoodComponent.Builder()
                            .hunger(7)
                            .saturationModifier(0.8f/2f)
                            .build()
                    )
            )
    );

    public static final Item COFFEE_ECLAIR = registerItem(
            "coffee_eclair",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB)
                    .food(new FoodComponent.Builder()
                            .hunger(2)
                            .saturationModifier(0.1f/2f)
                            .build()
                    )
            )
    );

    public static final Item BROWNIE = registerItem(
            "brownie",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB)
                    .food(new FoodComponent.Builder()
                            .hunger(2)
                            .saturationModifier(0.4f/2f)
                            .build()
                    )
            )
    );

    public static final Item TIRAMISU_SLICE = registerItem(
            "tiramisu_slice",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB)
                    .food(new FoodComponent.Builder()
                            .hunger(2)
                            .saturationModifier(0.5f/2f)
                            .build()
                    )
            )
    );

    public static final Item SANDWICH_HAM = registerItem(
            "sandwich_ham",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB)
                    .food(new FoodComponent.Builder()
                            .hunger(8)
                            .saturationModifier(0.9f/2f)
                            .build()
                    )
            )
    );

    public static final Item SANDWICH_BEEF = registerItem(
            "sandwich_beef",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB)
                    .food(new FoodComponent.Builder()
                            .hunger(11)
                            .saturationModifier(1.4f/2f)
                            .build()
                    )
            )
    );

    public static final Item SANDWICH_CHICKEN = registerItem(
            "sandwich_chicken",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB)
                    .food(new FoodComponent.Builder()
                            .hunger(9)
                            .saturationModifier(1.2f/2f)
                            .build()
                    )
            )
    );

    public static final Item CROISSANT = registerItem(
            "croissant",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB)
                    .food(new FoodComponent.Builder()
                            .hunger(5)
                            .saturationModifier(1.2f/2f)
                            .build()
                    )
            )
    );

    public static final Item CROISSANT_HAM = registerItem(
            "croissant_ham",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB)
                    .food(new FoodComponent.Builder()
                            .hunger(8)
                            .saturationModifier(0.9f/2f)
                            .build()
                    )
            )
    );

    public static final Item CROISSANT_BEEF = registerItem(
            "croissant_beef",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB)
                    .food(new FoodComponent.Builder()
                            .hunger(11)
                            .saturationModifier(1.4f/2f)
                            .build()
                    )
            )
    );

    public static final Item CROISSANT_CHICKEN = registerItem(
            "croissant_chicken",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB)
                    .food(new FoodComponent.Builder()
                            .hunger(9)
                            .saturationModifier(1.2f/2f)
                            .build()
                    )
            )
    );

    public static final Item BAGEL = registerItem(
            "bagel",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB)
                    .food(new FoodComponent.Builder()
                            .hunger(7)
                            .saturationModifier(1.2f/2f)
                            .build()
                    )
            )
    );

    public static final Item BAGEL_HAM = registerItem(
            "bagel_ham",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB)
                    .food(new FoodComponent.Builder()
                            .hunger(10)
                            .saturationModifier(0.9f/2f)
                            .build()
                    )
            )
    );

    public static final Item BAGEL_BEEF = registerItem(
            "bagel_beef",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB)
                    .food(new FoodComponent.Builder()
                            .hunger(13)
                            .saturationModifier(1.4f/2f)
                            .build()
                    )
            )
    );

    public static final Item BAGEL_CHICKEN = registerItem(
            "bagel_chicken",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB)
                    .food(new FoodComponent.Builder()
                            .hunger(11)
                            .saturationModifier(1.2f/2f)
                            .build()
                    )
            )
    );

    // ###################################################### CUPS #####################################################
    public static final Item CUP_SMALL_UNFIRED = registerItem(
            "cup_small_unfired",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.CUP_SMALL_TAB)
            )
    );

    public static final Item CUP_SMALL = registerItem(
            "cup_small",
            new StackingCupsBlockItem(
                    RavenCoffeeBlocks.STACKING_CUPS_BLOCK,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_SMALL_TAB)
            )
    );

    public static final Item CUP_MEDIUM_UNFIRED = registerItem(
            "cup_medium_unfired",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
            )
    );

    public static final Item CUP_MEDIUM = registerItem(
            "cup_medium",
            new StackingCupsBlockItem(
                    RavenCoffeeBlocks.STACKING_CUPS_BLOCK,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
            )
    );

    public static final Item CUP_LARGE_UNFIRED = registerItem(
            "cup_large_unfired",
            new Item(new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
            )
    );

    public static final Item CUP_LARGE = registerItem(
            "cup_large",
            new StackingCupsBlockItem(
                    RavenCoffeeBlocks.STACKING_CUPS_BLOCK,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
            )
    );

    public static final Item COFFEE_MUG = registerItem(
            "coffee_mug",
            new StackingCupsBlockItem(
                    RavenCoffeeBlocks.STACKING_CUPS_BLOCK,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
            )
    );

    // ############################################### COFFEE MATERIALS ################################################
    public static final Item COFFEE_PLATES = registerItem(
            "coffee_plates",
            new Item(new FabricItemSettings().group(ItemGroup.MISC))
    );

    public static final Item COFFEE_INGOT = registerItem(
            "coffee_ingot",
            new Item(new FabricItemSettings().group(ItemGroup.MISC))
    );

    // ################################################# COFFEE TOOLS ##################################################
    //                               Material Tier || Extra Attack Points || Attack Speed = 4 + n || Creative GENERAL_TAB
    public static final Item COFFEE_PICKAXE = registerItem(
            "coffee_pickaxe",
            new PickaxeItem(RavenCoffeeToolMaterial.COFFEE, 3, -2.8f, new FabricItemSettings().group(RavenCoffeeItemGroups.GENERAL_TAB))
    );
    public static final Item COFFEE_AXE =  registerItem(
            "coffee_axe",
            new AxeItem(RavenCoffeeToolMaterial.COFFEE, 8, -2.7f, new FabricItemSettings().group(RavenCoffeeItemGroups.GENERAL_TAB))
    );
    public static final Item COFFEE_HOE =  registerItem(
            "coffee_hoe",
            new HoeItem(RavenCoffeeToolMaterial.COFFEE, 0, 1.0f, new FabricItemSettings().group(RavenCoffeeItemGroups.GENERAL_TAB)) {

            }
    );
    public static final Item COFFEE_SHOVEL =  registerItem(
            "coffee_shovel",
            new ShovelItem(RavenCoffeeToolMaterial.COFFEE, 4, -3.0f, new FabricItemSettings().group(RavenCoffeeItemGroups.GENERAL_TAB))
    );
    public static final Item COFFEE_SWORD =  registerItem(
            "coffee_sword",
            new SwordItem(RavenCoffeeToolMaterial.COFFEE, 5, -2.0f, new FabricItemSettings().group(RavenCoffeeItemGroups.GENERAL_TAB))
    );

    // ############################################### COFFEE BLOCK ITEMS ################################################
    public static final Item BROWNIE_BLOCK_ITEM = registerItem(
            "brownie_block",
            new BlockItem(RavenCoffeeBlocks.BROWNIE_BLOCK, new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB)
                    .food(new FoodComponent.Builder()
                            .hunger(20)
                            .saturationModifier(1.0f)
                            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 6000, 5), 1.0f)
                            .build()
                    )) {
                @Override
                public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                    String descriptionKey = this.getTranslationKey().concat(".tooltip");
                    String completeDescription = (Text.translatable(descriptionKey)).getString();
                    tooltip.add(Text.of(String.format(
                            "\u00A77%s\u00A77",
                            completeDescription
                    )));
                }
            }
    );

    public static final Item ROSCA_DE_REYES_BLOCK_ITEM = registerItem(
            "rosca_de_reyes_block",
            new BlockItem(RavenCoffeeBlocks.ROSCA_DE_REYES_BLOCK, new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB))
    );

    public static final Item TIRAMISU_BLOCK_ITEM = registerItem(
            "tiramisu_block",
            new BlockItem(RavenCoffeeBlocks.TIRAMISU_BLOCK, new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB))
    );

    public static final Item COFFEE_BEANS_ROASTED_BLOCK_ITEM = registerItem(
            "coffee_beans_roasted_block",
            new BlockItem(RavenCoffeeBlocks.COFFEE_BEANS_ROASTED_BLOCK, new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB))
    );

    public static final Item COFFEE_BEANS_MAGMA_BLOCK_ITEM = registerItem(
            "coffee_beans_magma_block",
            new BlockItem(RavenCoffeeBlocks.COFFEE_BEANS_MAGMA_BLOCK, new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB))
    );

    public static final Item COFFEE_GRINDER_ITEM = registerItem(
            "coffee_grinder_block",
            new BlockItem(RavenCoffeeBlocks.COFFEE_GRINDER, new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB))
    );

    public static final Item COFFEE_MACHINE_ITEM = registerItem(
            "coffee_machine_block",
            new BlockItem(RavenCoffeeBlocks.COFFEE_MACHINE_BLOCK, new FabricItemSettings()
                    .group(RavenCoffeeItemGroups.GENERAL_TAB))
    );

    public static final Item SACK_BLOCK_ITEM = registerItem(
            "sack_block",
            new BlockItem(RavenCoffeeBlocks.SACK_BLOCK, new Item.Settings().group(RavenCoffeeItemGroups.GENERAL_TAB))
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Constants.MOD_ID, name), item);
    }
    
    public static void register() {
        Constants.LOGGER.info(Constants.MOD_NAME + " has registered its items!");
    }
}
