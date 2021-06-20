package me.explicit.ui.clickgui.elements.menu;

import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Iterator;
import me.explicit.settings.Setting;
import me.explicit.ui.clickgui.elements.Element;
import me.explicit.ui.clickgui.elements.ModuleButton;
import me.explicit.ui.clickgui.util.ColorUtil;
import me.explicit.ui.clickgui.util.FontUtil;
import net.minecraft.client.gui.Gui;

public class ElementComboBox extends Element {

    private static final String[] lIIlIIllI;
    private static String[] lIIlIIlll;

    public ElementComboBox(ModuleButton modulebutton, Setting setting) {
        this.parent = modulebutton;
        this.set = setting;
        super.setup();
    }

    public void drawScreen(int i, int j, float f) {
        Color color = ColorUtil.getClickGUIColor();
        int k = (new Color(color.getRed(), color.getGreen(), color.getBlue(), 200)).getRGB();
        int l = (new Color(color.getRed(), color.getGreen(), color.getBlue(), 200)).darker().getRGB();
        int i1 = (new Color(color.getRed(), color.getGreen(), color.getBlue(), 200)).darker().darker().darker().darker().getRGB();
        int j1 = (new Color(ColorUtil.getClickGUIColor().getRed(), ColorUtil.getClickGUIColor().getGreen(), ColorUtil.getClickGUIColor().getBlue(), 100)).darker().getRGB();

        Gui.drawRect((int) this.x, (int) this.y, (int) this.x + (int) this.width, (int) this.y + (int) this.height, j1);
        FontUtil.drawTotalCenteredString(this.setstrg, (float) (this.x + this.width / 2.0D), (float) (this.y + 7.0D), -1);
        int k1 = color.getRGB();
        int l1 = l;
        int i2 = (new Color(color.getRed(), color.getGreen(), color.getBlue(), 50)).getRGB();

        Gui.drawRect((int) this.x, (int) this.y + 14, (int) this.x + (int) this.width, (int) this.y + 15, 1996488704);
        if (this.comboextended) {
            Gui.drawRect((int) this.x, (int) this.y + 15, (int) this.x + (int) this.width, (int) this.y + (int) this.height, -1441656302);
            double d0 = this.y + 15.0D;

            for (Iterator iterator = this.set.getOptions().iterator(); iterator.hasNext(); d0 += (double) (FontUtil.getFontHeight() + 2)) {
                String s = (String) iterator.next();
                String s1 = String.valueOf((new StringBuilder()).append(s.substring(0, 1).toUpperCase()).append(s.substring(1, s.length())));

                if (s.equalsIgnoreCase(this.set.getValString())) {
                    Gui.drawRect((int) this.x + (int) this.width, (int) d0, (int) this.x, (int) (d0 + (double) FontUtil.getFontHeight() + 2.0D), l1);
                }

                if ((double) i >= this.x && (double) i <= this.x + this.width && (double) j >= d0 && (double) j < d0 + (double) FontUtil.getFontHeight() + 2.0D) {
                    Gui.drawRect((int) this.x, (int) d0, (int) this.x + (int) this.width, (int) (d0 + (double) FontUtil.getFontHeight() + 2.0D), i2);
                }

                FontUtil.drawCenteredString(s1, (float) (this.x + this.width / 2.0D), (float) (d0 + 1.0D), -1);
            }
        }

        FontUtil.drawTotalCenteredStringWithShadow(this.comboextended ? ElementComboBox.lIIlIIllI[0] : ElementComboBox.lIIlIIllI[1], (float) this.x + 74.0F, (float) this.y + 8.0F, k);
    }

    public boolean mouseClicked(int i, int j, int k) {
        if (k == 0 || k == 1) {
            if (this.isButtonHovered(i, j)) {
                this.comboextended = !this.comboextended;
                return true;
            }

            if (!this.comboextended) {
                return false;
            }

            double d0 = this.y + 15.0D;

            for (Iterator iterator = this.set.getOptions().iterator(); iterator.hasNext(); d0 += (double) (FontUtil.getFontHeight() + 2)) {
                String s = (String) iterator.next();

                if ((double) i >= this.x && (double) i <= this.x + this.width && (double) j >= d0 && (double) j <= d0 + (double) FontUtil.getFontHeight() + 2.0D) {
                    if (this.clickgui != null && this.clickgui.setmgr != null) {
                        this.clickgui.setmgr.getSettingByName(this.parent.mod, this.set.getName()).setValString(s.toLowerCase());
                    }

                    return true;
                }
            }
        }

        return super.mouseClicked(i, j, k);
    }

    public boolean isButtonHovered(int i, int j) {
        return (double) i >= this.x && (double) i <= this.x + this.width && (double) j >= this.y && (double) j <= this.y + 15.0D;
    }

    static {
        lIIIIIIlIIl();
        lIIIIIIlIII();
    }

    private static void lIIIIIIlIII() {
        lIIlIIllI = new String[2];
        ElementComboBox.lIIlIIllI[0] = lIIIIIIIlll(ElementComboBox.lIIlIIlll[0], ElementComboBox.lIIlIIlll[1]);
        ElementComboBox.lIIlIIllI[1] = lIIIIIIIlll(ElementComboBox.lIIlIIlll[2], ElementComboBox.lIIlIIlll[3]);
        ElementComboBox.lIIlIIlll = null;
    }

    private static void lIIIIIIlIIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ElementComboBox.lIIlIIlll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIIIIIlll(String s, String s1) {
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
