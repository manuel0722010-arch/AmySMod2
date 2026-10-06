package com.amys.amysmod;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class HudConfigScreen extends Screen {
    private boolean dragging = false;
    private int dragOffsetX = 0;
    private int dragOffsetY = 0;

    public HudConfigScreen() {
        super(Text.literal("AmyS Mod Config"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fillGradient(0, 0, this.width, this.height, 0xC0101010, 0xD0101010);
        context.drawCenteredTextWithShadow(this.textRenderer, "§b[ AmyS Mod ] - Arrastra el HUD. ESC para guardar.", this.width / 2, 20, 0xFFFFFF);

        int x = AmySMod.posX;
        int y = AmySMod.posY;

        context.fill(x - 2, y - 2, x + 85, y + 32, dragging ? 0x6f55ffff : 0x3355ffff);
        context.drawText(this.textRenderer, "Axion Client", x, y, 0x55FFFF, true);
        context.drawText(this.textRenderer, "FPS: " + (this.client != null ? this.client.getCurrentFps() : 0), x, y + 10, 0x55FFFF, true);
        context.drawText(this.textRenderer, "Ping: 0ms", x, y + 20, 0x55FFFF, true);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            int x = AmySMod.posX;
            int y = AmySMod.posY;
            if (mouseX >= x && mouseX <= x + 85 && mouseY >= y && mouseY <= y + 32) {
                dragging = true;
                dragOffsetX = (int) (mouseX - x);
                dragOffsetY = (int) (mouseY - y);
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (dragging) {
            AmySMod.posX = (int) (mouseX - dragOffsetX);
            AmySMod.posY = (int) (mouseY - dragOffsetY);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) { dragging = false; }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPauseWhenBlurred() { return false; }
}
