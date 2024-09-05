package com.thewandererraven.ravencoffee.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.screens.handlers.CoffeeGrinderScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CoffeeGrinderScreen extends HandledScreen<CoffeeGrinderScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(Constants.MOD_ID, "textures/gui/containers/coffee_grinder.png");

    public CoffeeGrinderScreen(CoffeeGrinderScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        // Station Name Y pos
        titleY = 10;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        RenderSystem.disableBlend();
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
