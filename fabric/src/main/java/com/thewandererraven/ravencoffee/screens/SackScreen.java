package com.thewandererraven.ravencoffee.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.screens.handlers.SackScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SackScreen extends HandledScreen<SackScreenHandler> {

    private static final Identifier BACKGROUND_TEXTURE = new Identifier(Constants.MOD_ID, "textures/gui/menus/sack.png");
    public static final int STATION_NAME_YPOS = 6;

    public SackScreen(SackScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }


    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(BACKGROUND_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        final int LABEL_XPOS = SackScreenHandler.PLAYER_INVENTORY_XPOS;
        final int FONT_Y_SPACING = 12;
        context.drawText(this.textRenderer, this.title, LABEL_XPOS, STATION_NAME_YPOS, 4210752, false);

        final int PLAYER_INV_LABEL_YPOS = SackScreenHandler.PLAYER_INVENTORY_YPOS - FONT_Y_SPACING;
        context.drawText(this.textRenderer, this.playerInventoryTitle, LABEL_XPOS, PLAYER_INV_LABEL_YPOS, 4210752, false);
    }

}
