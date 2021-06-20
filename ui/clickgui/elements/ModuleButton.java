package me.explicit.ui.clickgui.elements;

import java.awt.Color;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Module;
import me.explicit.module.render.ClickGUI;
import me.explicit.settings.Setting;
import me.explicit.ui.clickgui.Panel;
import me.explicit.ui.clickgui.elements.menu.ElementCheckBox;
import me.explicit.ui.clickgui.elements.menu.ElementComboBox;
import me.explicit.ui.clickgui.elements.menu.ElementSlider;
import me.explicit.ui.clickgui.util.ColorUtil;
import me.explicit.ui.clickgui.util.FontUtil;
import me.explicit.utils.ChatUtils;
import me.explicit.utils.TimerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Keyboard;

public class ModuleButton {

    public Module mod;
    public ArrayList menuelements;
    public Panel parent;
    public double x;
    public double y;
    public double width;
    public double height;
    public boolean extended = false;
    public boolean listening = false;
    private TimerUtils timer;
    private static final String[] lIllllII;
    private static String[] lIlllllI;

    public ModuleButton(Module module, Panel panel) {
        this.mod = module;
        this.height = (double) (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 6);
        this.parent = panel;
        (this.menuelements = new ArrayList()).clear();
        if (Explicit.instance.sm.getSettingsByMod(module) != null) {
            Iterator iterator = Explicit.instance.sm.getSettingsByMod(module).iterator();

            while (iterator.hasNext()) {
                Setting setting = (Setting) iterator.next();

                if (setting.isCheck()) {
                    this.menuelements.add(new ElementCheckBox(this, setting));
                } else if (setting.isSlider()) {
                    this.menuelements.add(new ElementSlider(this, setting));
                } else if (setting.isCombo()) {
                    this.menuelements.add(new ElementComboBox(this, setting));
                }
            }
        }

        this.timer = new TimerUtils();
    }

    public void drawScreen(int i, int j, float f) {
        Color color = ColorUtil.getClickGUIColor();
        int k = (new Color(color.getRed(), color.getGreen(), color.getBlue(), 150)).getRGB();
        int l = ColorUtil.getClickGUIColor().getRGB();

        if (this.mod.isToggled() || this.mod.getName().equalsIgnoreCase(ModuleButton.lIllllII[0])) {
            Gui.drawRect((int) this.x - 2, (int) this.y, (int) this.x + (int) this.width + 2, (int) this.y + (int) this.height, k);
            l = ColorUtil.getClickGUIColor().brighter().getRGB();
            if (ClickGUI.mode.equalsIgnoreCase(ModuleButton.lIllllII[1])) {
                (new Color(240, 240, 240, 200)).getRGB();
            } else {
                ColorUtil.getClickGUIColor().brighter().getRGB();
            }
        }

        if (this.isHovered(i, j)) {
            Gui.drawRect((int) this.x - 2, (int) this.y, (int) this.x + (int) this.width + 2, (int) this.y + (int) this.height + 1, 1427181841);
            if (this.timer.hasReached(1000.0D)) {
                Gui.drawRect(i - 1, j, (int) ((float) (i + 1) + FontUtil.getStringWidth(this.mod.getDescription())), (int) ((double) (j - FontUtil.getFontHeight()) - 2.5D), ColorUtil.getClickGUIColor().darker().darker().getRGB());
                FontUtil.drawString(this.mod.getDescription(), (float) (i + 1), (float) ((double) j - (double) FontUtil.getFontHeight() * 1.25D), ColorUtil.getClickGUIColor().brighter().brighter().getRGB());
            }
        } else {
            this.timer.reset();
        }

    }

    public void onUpdate() {
        int i = ClickGUI.mode.equalsIgnoreCase(ModuleButton.lIllllII[2]) ? (new Color(240, 240, 240)).getRGB() : ColorUtil.getClickGUIColor().brighter().getRGB();

        FontUtil.drawTotalCenteredStringWithShadow(this.mod.getName(), (float) (this.x + (double) (FontUtil.getStringWidth(this.mod.getName()) / 2.0F)), (float) (this.y + 1.0D + this.height / 2.0D), i);
        if (!this.menuelements.isEmpty()) {
            FontUtil.drawTotalCenteredStringWithShadow(this.extended ? ModuleButton.lIllllII[3] : ModuleButton.lIllllII[4], (float) this.x + 72.0F, (float) (this.y + 1.0D + this.height / 2.0D), i);
        }

    }

    public boolean mouseClicked(int i, int j, int k) {
        if (!this.isHovered(i, j)) {
            return false;
        } else {
            if (k == 0) {
                this.mod.toggle();
            } else if (k == 1) {
                if (this.menuelements != null && this.menuelements.size() > 0) {
                    boolean flag = !this.extended;

                    this.extended = flag;
                }
            } else if (k == 2) {
                this.listening = true;
            }

            return true;
        }
    }

    public boolean keyTyped(char c0, int i) throws IOException {
        if (this.listening) {
            if (i != 1) {
                ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(ModuleButton.lIllllII[5]).append(this.mod.getName()).append(ModuleButton.lIllllII[6]).append(Keyboard.getKeyName(i)).append(ModuleButton.lIllllII[7])));
                this.mod.setKey(i);
            } else {
                ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(ModuleButton.lIllllII[8]).append(this.mod.getName()).append(ModuleButton.lIllllII[9])));
                this.mod.setKey(0);
            }

            this.listening = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean isHovered(int i, int j) {
        return (double) i >= this.x && (double) i <= this.x + this.width && (double) j >= this.y && (double) j <= this.y + this.height;
    }

    static {
        lIlIllIlll();
        lIlIllIllI();
    }

    private static void lIlIllIllI() {
        lIllllII = new String[10];
        ModuleButton.lIllllII[0] = lIlIllIIIl(ModuleButton.lIlllllI[0], ModuleButton.lIlllllI[1]);
        ModuleButton.lIllllII[1] = lIlIllIIll(ModuleButton.lIlllllI[2], ModuleButton.lIlllllI[3]);
        ModuleButton.lIllllII[2] = lIlIllIlIl(ModuleButton.lIlllllI[4], ModuleButton.lIlllllI[5]);
        ModuleButton.lIllllII[3] = lIlIllIIll(ModuleButton.lIlllllI[6], ModuleButton.lIlllllI[7]);
        ModuleButton.lIllllII[4] = lIlIllIIll(ModuleButton.lIlllllI[8], ModuleButton.lIlllllI[9]);
        ModuleButton.lIllllII[5] = lIlIllIIll(ModuleButton.lIlllllI[10], ModuleButton.lIlllllI[11]);
        ModuleButton.lIllllII[6] = lIlIllIIIl(ModuleButton.lIlllllI[12], ModuleButton.lIlllllI[13]);
        ModuleButton.lIllllII[7] = lIlIllIIll(ModuleButton.lIlllllI[14], ModuleButton.lIlllllI[15]);
        ModuleButton.lIllllII[8] = lIlIllIIll(ModuleButton.lIlllllI[16], ModuleButton.lIlllllI[17]);
        ModuleButton.lIllllII[9] = lIlIllIIll(ModuleButton.lIlllllI[18], ModuleButton.lIlllllI[19]);
        ModuleButton.lIlllllI = null;
    }

    private static void lIlIllIlll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ModuleButton.lIlllllI = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIlIllIlIl(String s, String s1) {
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

    private static String lIlIllIIll(String s, String s1) {
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

    private static String lIlIllIIIl(String s, String s1) {
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
