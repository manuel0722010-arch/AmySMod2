package com.amys.amysmod;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public class HudRenderer implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.options.hudHidden) return;

        TextRenderer textRenderer = client.textRenderer;
        int fps = client.getCurrentFps();
        
        int ping = 0;
        if (client.player != null && client.getNetworkHandler() != null && 
            client.getNetworkHandler().getPlayerListEntry(client.player.getUuid()) != null) {
            ping = client.getNetworkHandler().getPlayerListEntry(client.player.getUuid()).getLatency();
        }

        int x = AmySMod.posX;
        int y = AmySMod.posY;

        context.drawText(textRenderer, "Axion Client", x, y, 0x55FFFF, true);
        context.drawText(textRenderer, "FPS: " + fps, x, y + 10, 0x55FFFF, true);
        context.drawText(textRenderer, "Ping: " + ping + "ms", x, y + 20, 0x55FFFF, true);
    }
}
