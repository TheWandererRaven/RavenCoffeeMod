package com.thewandererraven.ravencoffee.containers.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.containers.CoffeeMachineMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CoffeeMachineScreen extends AbstractContainerScreen<CoffeeMachineMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(
            Constants.MOD_ID, "textures/gui/menus/coffee_machine.png");

    private static final int PROGRESS_INDICATOR_X = 102;
    private static final int PROGRESS_INDICATOR_Y = 16;

    private static final int ProgressIndicatorWidth = 9;
    private static final int ProgressIndicatorHeight = 28;

    private static final int EmptyCupSlotWidth = 16;
    private static final int EmptyCupSlotHeight = 16;

    public CoffeeMachineScreen(CoffeeMachineMenu coffeeMachineMenu, Inventory inventory, Component component) {
        super(coffeeMachineMenu, inventory, component);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);
        float progress = this.getMenu().getBrewingProgress(); // From 0 to 1
        if(progress > 0f)
            this.blit(poseStack, x + PROGRESS_INDICATOR_X, y + PROGRESS_INDICATOR_Y,
                    imageWidth, 0,
                    ProgressIndicatorWidth, Math.round(ProgressIndicatorHeight * progress));
        if(this.getMenu().isCupsSlotEmpty())
            this.blit(poseStack, x + CoffeeMachineMenu.CUPS_SLOT_POS_X, y + CoffeeMachineMenu.CUPS_SLOT_POS_Y,
                    imageWidth + ProgressIndicatorWidth, 0,
                    EmptyCupSlotWidth, EmptyCupSlotHeight);
        if(this.getMenu().isOutputSlotEmpty())
            this.blit(poseStack, x + CoffeeMachineMenu.CUP_OUTPUT_SLOT_POS_X, y + CoffeeMachineMenu.CUP_OUTPUT_SLOT_POS_Y,
                    imageWidth + ProgressIndicatorWidth + EmptyCupSlotWidth, 0,
                    EmptyCupSlotWidth, EmptyCupSlotHeight);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        renderTooltip(poseStack, mouseX, mouseY);
    }
}
