package me.explicit.ui.clickgui.elements.menu;

import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.settings.Setting;
import me.explicit.ui.clickgui.elements.Element;
import me.explicit.ui.clickgui.elements.ModuleButton;
import me.explicit.ui.clickgui.util.ColorUtil;
import me.explicit.ui.clickgui.util.FontUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.MathHelper;

public class ElementSlider extends Element {

    public boolean dragging;
    private static final String[] lllIlI;
    private static String[] lllIll;

    public ElementSlider(ModuleButton modulebutton, Setting setting) {
        this.parent = modulebutton;
        this.set = setting;
        this.dragging = false;
        super.setup();
    }

    public void drawScreen(int i, int j, float f) {
        String s = String.valueOf((new StringBuilder()).append(ElementSlider.lllIlI[0]).append((double) Math.round(this.set.getValDouble() * 100.0D) / 100.0D));
        boolean flag = this.isSliderHovered(i, j) || this.dragging;
        Color color = ColorUtil.getClickGUIColor();
        int k = (new Color(color.getRed(), color.getGreen(), color.getBlue(), flag ? 250 : 200)).getRGB();
        int l = (new Color(color.getRed(), color.getGreen(), color.getBlue(), flag ? 255 : 230)).getRGB();
        int i1 = (new Color(color.getRed(), color.getGreen(), color.getBlue(), 200)).darker().darker().darker().darker().getRGB();
        int j1 = (new Color(ColorUtil.getClickGUIColor().getRed(), ColorUtil.getClickGUIColor().getGreen(), ColorUtil.getClickGUIColor().getBlue(), 100)).darker().getRGB();
        double d0 = (this.set.getValDouble() - this.set.getMin()) / (this.set.getMax() - this.set.getMin());

        Gui.drawRect((int) this.x, (int) this.y, (int) this.x + (int) this.width, (int) this.y + (int) this.height, j1);
        Gui.drawRect((int) this.x, (int) this.y + 12, (int) this.x + (int) this.width, (int) this.y + 2, -15724528);
        Gui.drawRect((int) this.x, (int) this.y + 12, (int) this.x + (int) (d0 * this.width), (int) this.y + 2, k);
        FontUtil.drawString(this.setstrg, (float) (this.x + 1.0D), (float) (this.y + 3.0D), -1);
        String s1 = ElementSlider.lllIlI[1];

        if (s.endsWith(ElementSlider.lllIlI[2])) {
            for (int k1 = 0; (long) k1 < s.chars().count() - 2L; ++k1) {
                s1 = String.valueOf((new StringBuilder()).append(s1).append(s.charAt(k1)));
            }
        } else {
            s1 = s;
        }

        if (this.set.percentage) {
            s1 = String.valueOf((new StringBuilder()).append(s1).append(ElementSlider.lllIlI[3]));
        }

        FontUtil.drawString(s1, (float) (this.x + this.width - (double) FontUtil.getStringWidth(s1)), (float) (this.y + 3.0D), -1);
        if (this.dragging) {
            double d1 = this.set.getMax() - this.set.getMin();
            double d2 = this.set.getMin() + MathHelper.clamp_double(((double) i - this.x) / this.width, 0.0D, 1.0D) * d1;

            this.set.setValDouble(d2);
        }

    }

    public boolean mouseClicked(int i, int j, int k) {
        if (k == 0 && this.isSliderHovered(i, j)) {
            this.dragging = true;
            return true;
        } else {
            return super.mouseClicked(i, j, k);
        }
    }

    public void mouseReleased(int i, int j, int k) {
        this.dragging = false;
    }

    public boolean isSliderHovered(int i, int j) {
        return (double) i >= this.x && (double) i <= this.x + this.width && (double) j >= this.y + 2.0D && (double) j <= this.y + 14.0D;
    }

    static {
        llIlIIll();
        llIlIIlI();
    }

    private static void llIlIIlI() {
        lllIlI = new String[4];
        ElementSlider.lllIlI[0] = llIIllll(ElementSlider.lllIll[0], ElementSlider.lllIll[1]);
        ElementSlider.lllIlI[1] = llIlIIII(ElementSlider.lllIll[2], ElementSlider.lllIll[3]);
        ElementSlider.lllIlI[2] = llIlIIIl(ElementSlider.lllIll[4], ElementSlider.lllIll[5]);
        ElementSlider.lllIlI[3] = llIIllll(ElementSlider.lllIll[6], ElementSlider.lllIll[7]);
        ElementSlider.lllIll = null;
    }

    private static void llIlIIll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ElementSlider.lllIll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIlIIII(String s, String s1) {
        try {
            SecretKeySpec secretkeyspec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(s1.getBytes(StandardCharsets.UTF_8)), 8), "DES");
            Cipher cipher = Cipher.getInstance("DES");

            cipher.init(2, secretkeyspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(s.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private static String llIlIIIl(String s, String s1) {
        try {
            SecretKeySpec secretkeyspec = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(s1.getBytes(StandardCharsets.UTF_8)), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");

            cipher.init(2, secretkeyspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(s.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private static String llIIllll(String s, String s1) {
        s = new String(Base64.getDecoder().decode(s.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        StringBuilder stringbuilder = new StringBuilder();
        char[] achar = s1.toCharArray();
        int i = 0;
        char[] achar1 = s.toCharArray();
        int j = achar1.length;

        for (int k = 0; k < j; ++k) {
            char c0 = achar1[k];

            stringbuilder.append((char) (c0 ^ achar[i % achar.length]));
            ++i;
        }

        return String.valueOf(stringbuilder);
    }
}
