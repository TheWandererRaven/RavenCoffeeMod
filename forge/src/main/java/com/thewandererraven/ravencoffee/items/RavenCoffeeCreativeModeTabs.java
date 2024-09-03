package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravencoffee.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RavenCoffeeCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

    public static ResourceLocation general_tab_id = new ResourceLocation(Constants.MOD_ID, "general_tab");
    public static final RegistryObject<CreativeModeTab> GENERAL_TAB = CREATIVE_MODE_TABS.register(
            "general_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED.get()))
                    .title(Component.translatable(general_tab_id.toLanguageKey()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(RavenCoffeeItems.COFFEE_CHERRIES.get());
                        pOutput.accept(RavenCoffeeItems.COFFEE_BEANS.get());
                        pOutput.accept(RavenCoffeeItems.COFFEE_BEANS_ROASTED.get());
                        pOutput.accept(RavenCoffeeItems.COFFEE_BEANS_MAGMA.get());
                        pOutput.accept(RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND.get());
                        pOutput.accept(RavenCoffeeItems.COFFEE_BEANS_MAGMA_GROUND.get());
                        pOutput.accept(RavenCoffeeItems.POPCHORUS.get());
                        pOutput.accept(RavenCoffeeItems.MUFFIN.get());
                        pOutput.accept(RavenCoffeeItems.MELON_PAN.get());
                        pOutput.accept(RavenCoffeeItems.COFFEE_ECLAIR.get());
                        pOutput.accept(RavenCoffeeItems.BROWNIE.get());
                        pOutput.accept(RavenCoffeeItems.TIRAMISU_SLICE.get());
                        pOutput.accept(RavenCoffeeItems.SANDWICH_HAM.get());
                        pOutput.accept(RavenCoffeeItems.SANDWICH_BEEF.get());
                        pOutput.accept(RavenCoffeeItems.SANDWICH_CHICKEN.get());
                        pOutput.accept(RavenCoffeeItems.CROISSANT.get());
                        pOutput.accept(RavenCoffeeItems.CROISSANT_HAM.get());
                        pOutput.accept(RavenCoffeeItems.CROISSANT_BEEF.get());
                        pOutput.accept(RavenCoffeeItems.CROISSANT_CHICKEN.get());
                        pOutput.accept(RavenCoffeeItems.BAGEL.get());
                        pOutput.accept(RavenCoffeeItems.BAGEL_HAM.get());
                        pOutput.accept(RavenCoffeeItems.BAGEL_BEEF.get());
                        pOutput.accept(RavenCoffeeItems.BAGEL_CHICKEN.get());
                        pOutput.accept(RavenCoffeeItems.COFFEE_PLATES.get());
                        pOutput.accept(RavenCoffeeItems.COFFEE_INGOT.get());
                        pOutput.accept(RavenCoffeeItems.COFFEE_PICKAXE.get());
                        pOutput.accept(RavenCoffeeItems.COFFEE_AXE.get());
                        pOutput.accept(RavenCoffeeItems.COFFEE_HOE.get());
                        pOutput.accept(RavenCoffeeItems.COFFEE_SHOVEL.get());
                        pOutput.accept(RavenCoffeeItems.COFFEE_SWORD.get());
                        pOutput.accept(RavenCoffeeItems.BROWNIE_BLOCK_ITEM.get());
                        pOutput.accept(RavenCoffeeItems.ROSCA_DE_REYES_BLOCK_ITEM.get());
                        pOutput.accept(RavenCoffeeItems.TIRAMISU_BLOCK_ITEM.get());
                        pOutput.accept(RavenCoffeeItems.COFFEE_BEANS_ROASTED_BLOCK_ITEM.get());
                        pOutput.accept(RavenCoffeeItems.COFFEE_BEANS_MAGMA_BLOCK_ITEM.get());
                        pOutput.accept(RavenCoffeeItems.COFFEE_GRINDER_ITEM.get());
                        pOutput.accept(RavenCoffeeItems.COFFEE_MACHINE_ITEM.get());
                        pOutput.accept(RavenCoffeeItems.SACK_BLOCK_ITEM.get());
                    })
                    .build());

    public static ResourceLocation mug_tab_id = new ResourceLocation(Constants.MOD_ID, "mug_tab");
    public static final RegistryObject<CreativeModeTab> COFFEE_MUG_TAB = CREATIVE_MODE_TABS.register(
            "mug_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_BASIC.get()))
                    .title(Component.translatable(mug_tab_id.toLanguageKey()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(RavenCoffeeItems.COFFEE_MUG.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_BASIC.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_SUGAR.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_APPLE.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_BERRY.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_HONEY.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_CHOCOLATE.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_MILK.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_MOCHA.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_AWKWARD.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_CARROT.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_CARROT_GOLDEN.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_COOKIESANDCREAM.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_END.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_MELON.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_MELON_GOLDEN.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_NETHER.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_PHANTASM.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_PUMPKINSPICELATTE.get());
                        pOutput.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN.get());
                    })
                    .build()
    );
    
    public static ResourceLocation small_tab_id = new ResourceLocation(Constants.MOD_ID, "small_tab");
    public static final RegistryObject<CreativeModeTab> CUP_SMALL_TAB = CREATIVE_MODE_TABS.register(
            "small_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(RavenCoffeeBrewItems.CUP_SMALL_BREW_BASIC.get()))
                    .title(Component.translatable(small_tab_id.toLanguageKey()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(RavenCoffeeItems.CUP_SMALL_UNFIRED.get());
                        pOutput.accept(RavenCoffeeItems.CUP_SMALL.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_SMALL_BREW_BASIC.get());
                    })
                    .build()
    );
    
    public static ResourceLocation medium_tab_id = new ResourceLocation(Constants.MOD_ID, "medium_tab");
    public static final RegistryObject<CreativeModeTab> CUP_MEDIUM_TAB = CREATIVE_MODE_TABS.register(
            "medium_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_BASIC.get()))
                    .title(Component.translatable(medium_tab_id.toLanguageKey()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(RavenCoffeeItems.CUP_MEDIUM_UNFIRED.get());
                        pOutput.accept(RavenCoffeeItems.CUP_MEDIUM.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_BASIC.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_SUGAR.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_APPLE.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_BERRY.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_HONEY.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_CHOCOLATE.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_MILK.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_MOCHA.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_AWKWARD.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_CARROT.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_CARROT_GOLDEN.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_COOKIESANDCREAM.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_END.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_MELON.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_MELON_GOLDEN.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_NETHER.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_PHANTASM.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_PUMPKINSPICELATTE.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN.get());
                    })
                    .build()
    );

    public static ResourceLocation large_tab_id = new ResourceLocation(Constants.MOD_ID, "large_tab");
    public static final RegistryObject<CreativeModeTab> CUP_LARGE_TAB = CREATIVE_MODE_TABS.register(
            "large_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_BASIC.get()))
                    .title(Component.translatable(large_tab_id.toLanguageKey()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(RavenCoffeeItems.CUP_LARGE_UNFIRED.get());
                        pOutput.accept(RavenCoffeeItems.CUP_LARGE.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_BASIC.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_SUGAR.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_APPLE.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_BERRY.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_HONEY.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_CHOCOLATE.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_MILK.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_MOCHA.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_AWKWARD.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_CARROT.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_CARROT_GOLDEN.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_COOKIESANDCREAM.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_END.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_MELON.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_MELON_GOLDEN.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_NETHER.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_PHANTASM.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_PUMPKINSPICELATTE.get());
                        pOutput.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN.get());
                    })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
