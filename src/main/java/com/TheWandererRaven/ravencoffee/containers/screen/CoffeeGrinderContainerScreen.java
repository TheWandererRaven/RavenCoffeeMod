package com.TheWandererRaven.ravencoffee.containers.screen;

import com.TheWandererRaven.ravencoffee.containers.CoffeeGrinderContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.awt.*;

public class CoffeeGrinderContainerScreen extends ContainerScreen<CoffeeGrinderContainer> {
    //public static final float STATION_NAME_XPOS = CoffeeGrinderContainer.PLAYER_INVENTORY_XPOS;
    public static final float STATION_NAME_YPOS = 10;
    private CoffeeGrinderContainer coffeeGrinderContainer;

    public CoffeeGrinderContainerScreen(CoffeeGrinderContainer coffeeGrinderContainer, PlayerInventory playerInventory, ITextComponent title) {
        super(coffeeGrinderContainer, playerInventory, title);
        this.coffeeGrinderContainer = coffeeGrinderContainer;
        // Set the width and height of the gui.  Should match the size of the texture!
        xSize = 176;
        ySize = 166;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     * Taken directly from ContainerScreen
     */
    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
        final float LABEL_XPOS = CoffeeGrinderContainer.PLAYER_INVENTORY_XPOS;
        final float FONT_Y_SPACING = 12;
        this.font.func_243248_b(matrixStack, this.title,
                LABEL_XPOS, STATION_NAME_YPOS, Color.darkGray.getRGB());  //this.font.drawString;

        final float PLAYER_INV_LABEL_YPOS = CoffeeGrinderContainer.PLAYER_INVENTORY_YPOS - FONT_Y_SPACING;
        this.font.func_243248_b(matrixStack, this.playerInventory.getDisplayName(),                  ///    this.font.drawString
                LABEL_XPOS, PLAYER_INV_LABEL_YPOS, Color.darkGray.getRGB());
    }

    /**
     * Draws the background layer of this container (behind the items).
     * Taken directly from ChestScreen / BeaconScreen
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);   // this.minecraft.getTextureManager()

        // width and height are the size provided to the window when initialised after creation.
        // xSize, ySize are the expected size of the texture-? usually seems to be left as a default.
        // The code below is typical for vanilla containers, so I've just copied that- it appears to centre the texture within
        //  the available window
        int edgeSpacingX = (this.width - this.xSize) / 2;
        int edgeSpacingY = (this.height - this.ySize) / 2;
        this.blit(matrixStack, edgeSpacingX, edgeSpacingY, 0, 0, this.xSize, this.ySize);
    }

    // This is the resource location for the background image for the GUI
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("ravencoffee", "textures/gui/containers/coffee_grinder.png");
}
