package com.thewandererraven.ravencoffee.containers.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.thewandererraven.ravencoffee.containers.SackMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.awt.*;

public class SackScreen extends AbstractContainerScreen<SackMenu> {

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("ravencoffee", "textures/gui/menus/sack.png");
    public static final int STATION_NAME_YPOS = 6;
    private SackMenu sackMenu;

    public SackScreen(SackMenu sackMenu, Inventory playerInventory, Component title) {
        super(sackMenu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(BACKGROUND_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        final int LABEL_XPOS = SackMenu.PLAYER_INVENTORY_XPOS;
        final int FONT_Y_SPACING = 12;
        guiGraphics.drawString(this.font, this.title, LABEL_XPOS, STATION_NAME_YPOS, Color.darkGray.getRGB());

        final int PLAYER_INV_LABEL_YPOS = SackMenu.PLAYER_INVENTORY_YPOS - FONT_Y_SPACING;
        guiGraphics.drawString(this.font, this.playerInventoryTitle, LABEL_XPOS, PLAYER_INV_LABEL_YPOS, Color.darkGray.getRGB());
    }
}
