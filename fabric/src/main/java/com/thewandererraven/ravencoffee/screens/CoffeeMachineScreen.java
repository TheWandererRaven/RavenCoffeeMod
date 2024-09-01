package com.thewandererraven.ravencoffee.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.screens.handlers.CoffeeMachineScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CoffeeMachineScreen extends HandledScreen<CoffeeMachineScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(Constants.MOD_ID, "textures/gui/menus/coffee_machine.png");

    private static final int PROGRESS_INDICATOR_X = 102;
    private static final int PROGRESS_INDICATOR_Y = 16;

    private static final int ProgressIndicatorWidth = 9;
    private static final int ProgressIndicatorHeight = 28;

    private static final int EmptyCupSlotWidth = 16;
    private static final int EmptyCupSlotHeight = 16;


    public CoffeeMachineScreen(CoffeeMachineScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        // Station Name Y pos
        titleY = 6;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);

        float progress = this.getScreenHandler().getScaledProgress(); // From 0 to 1
        if(progress > 0f)
            context.drawTexture(TEXTURE, x + PROGRESS_INDICATOR_X, y + PROGRESS_INDICATOR_Y,
                    this.backgroundWidth, 0,
                    ProgressIndicatorWidth, Math.round(ProgressIndicatorHeight * progress));
        if(this.getScreenHandler().isCupsSlotEmpty())
            context.drawTexture(TEXTURE, x + CoffeeMachineScreenHandler.CUPS_SLOT_POS_X, y + CoffeeMachineScreenHandler.CUPS_SLOT_POS_Y,
                    this.backgroundWidth + ProgressIndicatorWidth, 0,
                    EmptyCupSlotWidth, EmptyCupSlotHeight);
        if(this.getScreenHandler().isOutputSlotEmpty())
            context.drawTexture(TEXTURE, x + CoffeeMachineScreenHandler.CUP_OUTPUT_SLOT_POS_X, y + CoffeeMachineScreenHandler.CUP_OUTPUT_SLOT_POS_Y,
                    this.backgroundWidth + ProgressIndicatorWidth + EmptyCupSlotWidth, 0,
                    EmptyCupSlotWidth, EmptyCupSlotHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        RenderSystem.disableBlend();
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
