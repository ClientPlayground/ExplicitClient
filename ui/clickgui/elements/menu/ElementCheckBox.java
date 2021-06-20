package me.explicit.ui.clickgui.elements.menu;

import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.settings.Setting;
import me.explicit.ui.clickgui.elements.Element;
import me.explicit.ui.clickgui.elements.ModuleButton;
import me.explicit.ui.clickgui.util.ColorUtil;
import me.explicit.ui.clickgui.util.FontUtil;
import net.minecraft.client.gui.Gui;

public class ElementCheckBox extends Element {

    private long prevTime = System.currentTimeMillis();
    private long prevTimeHover = System.currentTimeMillis();
    private boolean wasChecked = false;
    private static final String[] llllIlII;
    private static String[] llllIlIl;

    public ElementCheckBox(ModuleButton modulebutton, Setting setting) {
        this.parent = modulebutton;
        this.set = setting;
        super.setup();
    }

    public void drawScreen(int i, int j, float f) {
        Color color = ColorUtil.getClickGUIColor();
        int k = (new Color(color.getRed(), color.getGreen(), color.getBlue(), 200)).getRGB();
        int l = (new Color(color.getRed(), color.getGreen(), color.getBlue(), 100)).darker().getRGB();
        int i1 = (new Color(color.getRed(), color.getGreen(), color.getBlue(), 200)).darker().darker().darker().darker().getRGB();
        int j1 = (new Color(color.getRed(), color.getGreen(), color.getBlue(), 200)).darker().darker().darker().darker().darker().darker().darker().darker().getRGB();
        int k1 = (new Color(ColorUtil.getClickGUIColor().getRed(), ColorUtil.getClickGUIColor().getGreen(), ColorUtil.getClickGUIColor().getBlue(), 100)).darker().getRGB();

        Gui.drawRect((int) this.x, (int) this.y, (int) (this.x + this.width), (int) (this.y + this.height), k1);
        FontUtil.drawString(this.setstrg, (float) (this.x + this.width - (double) FontUtil.getStringWidth(this.setstrg)), (float) (this.y + (double) (FontUtil.getFontHeight() / 2) - 0.5D), -1);
        Gui.drawRect((int) this.x + 1, (int) this.y + 2, (int) this.x + 12, (int) this.y + 13, j1);
        long l1 = System.currentTimeMillis() - this.prevTime;

        if (this.set.getValBoolean()) {
            if (l1 > 250L) {
                Gui.drawRect((int) this.x + 2, (int) this.y + 3, (int) this.x + 11, (int) this.y + 12, k);
            } else {
                Gui.drawRect((int) this.x + 2, (int) this.y + 3, (int) this.x + 11, (int) (this.y + 3.0D + (double) (l1 / 25L)), k);
            }
        } else if (l1 <= 250L) {
            if (l1 > 250L) {
                Gui.drawRect((int) this.x + 2, (int) this.y + 3, (int) this.x + 11, (int) this.y + 12, k);
            } else {
                Gui.drawRect((int) this.x + 2, (int) this.y + 3, (int) this.x + 11, (int) (this.y + 12.0D - (double) (l1 / 25L)), k);
            }
        }

        if (this.isCheckHovered(i, j)) {
            if (!this.wasChecked) {
                this.prevTimeHover = System.currentTimeMillis();
            }

            long i2 = System.currentTimeMillis() - this.prevTimeHover;

            if (i2 <= 100L && !this.set.getValBoolean()) {
                Gui.drawRect((int) this.x + 2, (int) this.y + 3, (int) this.x + 11, (int) (this.y + 3.0D + (double) i2 / 10.7777778D), l);
            } else if (l1 > 250L) {
                Gui.drawRect((int) this.x + 2, (int) this.y + 3, (int) this.x + 11, (int) this.y + 12, l);
            }

            this.wasChecked = true;
        } else {
            this.wasChecked = false;
        }

        if (this.set.getValBoolean()) {
            this.prevTimeHover = System.currentTimeMillis();
        }

    }

    public boolean mouseClicked(int i, int j, int k) {
        if (k == 0 && this.isCheckHovered(i, j)) {
            long l = System.currentTimeMillis() - this.prevTime;
            long i1 = System.currentTimeMillis() - this.prevTimeHover;

            if (l > 250L) {
                this.set.setValBoolean(!this.set.getValBoolean());
                this.prevTime = System.currentTimeMillis();
                return super.mouseClicked(i, j, k);
            }
        }

        return super.mouseClicked(i, j, k);
    }

    public boolean isCheckHovered(int i, int j) {
        boolean flag = (double) i >= this.x + 1.0D && (double) i <= this.x + 12.0D && (double) j >= this.y + 2.0D && (double) j <= this.y + 13.0D;

        if (this.set.getName().equalsIgnoreCase(ElementCheckBox.llllIlII[0])) {
            ;
        }

        return flag;
    }

    static {
        llIlllIIll();
        llIlllIIlI();
    }

    private static void llIlllIIlI() {
        llllIlII = new String[1];
        ElementCheckBox.llllIlII[0] = llIlllIIIl(ElementCheckBox.llllIlIl[0], ElementCheckBox.llllIlIl[1]);
        ElementCheckBox.llllIlIl = null;
    }

    private static void llIlllIIll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ElementCheckBox.llllIlIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIlllIIIl(String s, String s1) {
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
}
