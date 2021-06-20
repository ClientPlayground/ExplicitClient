package me.explicit.ui.clickgui.util;

import me.explicit.utils.Game;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StringUtils;

public class FontUtil {

    public static void setupFontUtils() {}

    public static float getStringWidth(String s) {
        return (float) Game.Minecraft().fontRendererObj.getStringWidth(StringUtils.stripControlCodes(s));
    }

    public static int getFontHeight() {
        return Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
    }

    public static void drawString(String s, float f, float f1, int i) {
        Game.Minecraft().fontRendererObj.drawStringWithShadow(s, f, f1, i);
    }

    public static void drawStringWithShadow(String s, float f, float f1, int i) {
        Game.Minecraft().fontRendererObj.drawStringWithShadow(s, f, f1, i);
    }

    public static void drawCenteredString(String s, float f, float f1, int i) {
        Game.Minecraft().fontRendererObj.drawStringWithShadow(s, f - getStringWidth(s) / 2.0F, f1, i);
    }

    public static void drawCenteredStringWithShadow(String s, float f, float f1, int i) {
        drawStringWithShadow(s, f - getStringWidth(s) / 2.0F, f1, i);
    }

    public static void drawTotalCenteredString(String s, float f, float f1, int i) {
        Game.Minecraft().fontRendererObj.drawString(s, (int) (f - getStringWidth(s) / 2.0F), (int) (f1 - (float) (getFontHeight() / 2)), i);
    }

    public static void drawTotalCenteredStringWithShadow(String s, float f, float f1, int i) {
        drawStringWithShadow(s, f - getStringWidth(s) / 2.0F, f1 - (float) getFontHeight() / 2.0F, i);
    }

    public static void drawBigTotalCenteredStringWithShadow(String s, float f, float f1, int i) {
        Game.Minecraft().fontRendererObj.drawStringWithShadow(s, f - getStringWidth(s) / 2.0F, f1 - (float) Game.Minecraft().fontRendererObj.FONT_HEIGHT / 2.0F, i);
    }
}
