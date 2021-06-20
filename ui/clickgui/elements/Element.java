package me.explicit.ui.clickgui.elements;

import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.settings.Setting;
import me.explicit.ui.clickgui.ClickGUI;
import me.explicit.ui.clickgui.util.FontUtil;
import net.minecraft.client.gui.Gui;

public class Element {

    public ClickGUI clickgui;
    public ModuleButton parent;
    public Setting set;
    public double offset;
    public double x;
    public double y;
    public double width;
    public double height;
    private boolean isEnd;
    public String setstrg;
    public boolean comboextended;
    private static final String[] llIllIIl;
    private static String[] llIllIll;

    public void setup() {
        this.clickgui = this.parent.parent.clickgui;
    }

    public void update() {
        this.x = this.parent.x - 2.0D;
        this.y = this.parent.y + this.offset + this.parent.height + 1.0D;
        this.width = this.parent.width + 4.0D;
        this.height = 15.0D;
        String s = this.set.getName();

        if (this.set.isCheck()) {
            this.setstrg = String.valueOf((new StringBuilder()).append(s.substring(0, 1).toUpperCase()).append(s.substring(1, s.length())));
            double d0 = this.x + this.width - (double) FontUtil.getStringWidth(this.setstrg);

            if (d0 < this.x + 13.0D) {
                this.width += this.x + 13.0D - d0 + 1.0D;
            }
        } else if (this.set.isCombo()) {
            this.height = this.comboextended ? (double) (this.set.getOptions().size() * (FontUtil.getFontHeight() + 2) + 15) : 15.0D;
            this.setstrg = String.valueOf((new StringBuilder()).append(s.substring(0, 1).toUpperCase()).append(s.substring(1, s.length())));
            float f = FontUtil.getStringWidth(this.setstrg);
            Iterator iterator = this.set.getOptions().iterator();

            while (iterator.hasNext()) {
                String s1 = (String) iterator.next();
                float f1 = FontUtil.getStringWidth(s1);

                if (f1 > f) {
                    f = f1;
                }
            }

            double d1 = this.x + this.width - (double) f;

            if (d1 < this.x) {
                this.width += this.x - d1 + 1.0D;
            }
        } else if (this.set.isSlider()) {
            this.setstrg = String.valueOf((new StringBuilder()).append(s.substring(0, 1).toUpperCase()).append(s.substring(1, s.length())));
            String s2 = String.valueOf((new StringBuilder()).append(Element.llIllIIl[0]).append((double) Math.round(this.set.getValDouble() * 100.0D) / 100.0D));
            String s3 = String.valueOf((new StringBuilder()).append(Element.llIllIIl[1]).append((double) Math.round(this.set.getMax() * 100.0D) / 100.0D));
            double d2 = this.x + this.width - (double) FontUtil.getStringWidth(this.setstrg) - (double) FontUtil.getStringWidth(s3) - 4.0D;

            if (d2 < this.x - 13.0D) {
                this.width += this.x - d2 + 13.0D;
            }
        }

        if (this.isEnd) {
            Gui.drawRect((int) this.x, (int) this.y + 14, (int) this.x + (int) this.width, (int) this.y + 16, (new Color(0, 0, 0, 175)).getRGB());
        }

    }

    public void setEndEl(boolean flag) {
        this.isEnd = flag;
    }

    public void drawScreen(int i, int j, float f) {}

    public boolean mouseClicked(int i, int j, int k) {
        return this.isHovered(i, j);
    }

    public void mouseReleased(int i, int j, int k) {}

    public boolean isHovered(int i, int j) {
        return (double) i >= this.x && (double) i <= this.x + this.width && (double) j >= this.y && (double) j <= this.y + this.height;
    }

    static {
        llIIlIlIII();
        llIIlIIlll();
    }

    private static void llIIlIIlll() {
        llIllIIl = new String[2];
        Element.llIllIIl[0] = llIIlIIlII(Element.llIllIll[0], Element.llIllIll[1]);
        Element.llIllIIl[1] = llIIlIIllI(Element.llIllIll[2], Element.llIllIll[3]);
        Element.llIllIll = null;
    }

    private static void llIIlIlIII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Element.llIllIll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIIlIIlII(String s, String s1) {
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

    private static String llIIlIIllI(String s, String s1) {
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
