package com.thewandererraven.ravencoffee.containers.screen;

import com.thewandererraven.ravencoffee.containers.CoffeeGrinderContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.awt.*;

public class CoffeeGrinderContainerScreen extends AbstractContainerScreen<CoffeeGrinderContainer> {
    //public static final float STATION_NAME_XPOS = CoffeeGrinderContainer.PLAYER_INVENTORY_XPOS;
    public static final float STATION_NAME_YPOS = 10;
    private CoffeeGrinderContainer coffeeGrinderContainer;

    public CoffeeGrinderContainerScreen(CoffeeGrinderContainer coffeeGrinderContainer, Inventory playerInventory, Component title) {
        super(coffeeGrinderContainer, playerInventory, title);
        this.coffeeGrinderContainer = coffeeGrinderContainer;
        // Set the width and height of the gui.  Should match the size of the texture!
        imageWidth = 176;
        imageHeight = 166;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     * Taken directly from ContainerScreen
     */
    @Override
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        final float LABEL_XPOS = CoffeeGrinderContainer.PLAYER_INVENTORY_XPOS;
        final float FONT_Y_SPACING = 12;
        this.font.draw(poseStack, this.title,
                LABEL_XPOS, STATION_NAME_YPOS, Color.darkGray.getRGB());  //this.font.drawString;

        final float PLAYER_INV_LABEL_YPOS = CoffeeGrinderContainer.PLAYER_INVENTORY_YPOS - FONT_Y_SPACING;
        this.font.draw(poseStack, this.playerInventoryTitle,                  ///    this.font.drawString
                LABEL_XPOS, PLAYER_INV_LABEL_YPOS, Color.darkGray.getRGB());
    }

    /**
     * Draws the background layer of this container (behind the items).
     * Taken directly from ChestScreen / BeaconScreen
     */
    @Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        //this.minecraft.getTextureManager().bindForSetup(BACKGROUND_TEXTURE);   // this.minecraft.getTextureManager()

        // width and height are the size provided to the window when initialised after creation.
        // xSize, ySize are the expected size of the texture-? usually seems to be left as a default.
        // The code below is typical for vanilla containers, so I've just copied that- it appears to centre the texture within
        //  the available window
        int edgeSpacingX = (this.width - this.imageWidth) / 2;
        int edgeSpacingY = (this.height - this.imageHeight) / 2;
        this.blit(poseStack, edgeSpacingX, edgeSpacingY, 0, 0, this.imageWidth, this.imageHeight);
    }

    // This is the resource location for the background image for the GUI
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("ravencoffee", "textures/gui/containers/coffee_grinder.png");
}
