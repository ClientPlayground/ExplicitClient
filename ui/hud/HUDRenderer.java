package me.explicit.ui.hud;

import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.SettingsManager;
import me.explicit.ui.clickgui.ClickGUI;
import me.explicit.utils.Game;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class HUDRenderer extends Gui {

    protected Minecraft mc = Game.Minecraft();
    private static final String[] lIllIlIl;
    private static String[] lIllIllI;

    public void draw() {
        if (Explicit.instance.mm.getModuleByName(HUDRenderer.lIllIlIl[0]).isToggled()) {
            this.renderModules();
        }
    }

    public void renderModules() {
        if (!(this.mc.currentScreen instanceof ClickGUI) && Game.World() != null && Game.Player() != null) {
            ScaledResolution scaledresolution = new ScaledResolution(this.mc);
            List list = this.getOrder();
            int i = 1;

            for (int j = 0; j < this.getOrder().size(); ++j) {
                String s = (String) this.getOrder().get(j);

                if (!s.equalsIgnoreCase(HUDRenderer.lIllIlIl[1]) && !((Module) this.getOrderModule().get(j)).getCategory().equals(Category.VALUES)) {
                    int k = 10 * i - this.mc.fontRendererObj.FONT_HEIGHT + 2;

                    this.renderText(s, scaledresolution.getScaledWidth() - this.mc.fontRendererObj.getStringWidth(s) - 2, k, 0);
                    ++i;
                }
            }

            String s1 = String.valueOf((new StringBuilder()).append(HUDRenderer.lIllIlIl[2]).append(HUDRenderer.lIllIlIl[3].toLowerCase()));

            this.renderText(String.valueOf((new StringBuilder()).append(HUDRenderer.lIllIlIl[4]).append(HUDRenderer.lIllIlIl[5].toLowerCase())), 5, 5, 0);
        }
    }

    private void renderText(String s, int i, int j, int k) {
        String s1 = HUDRenderer.lIllIlIl[6];

        for (int l = 0; l < s.toCharArray().length; ++l) {
            char c0 = s.toCharArray()[l];
            float f = (float) (i + this.mc.fontRendererObj.getStringWidth(s1));

            this.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(c0).append(HUDRenderer.lIllIlIl[7])), f, (float) j, this.getColor((int) (-f) + j + k).getRGB());
            s1 = String.valueOf((new StringBuilder()).append(s1).append(c0));
        }

    }

    public Color getColor(int i) {
        Module module = Explicit.instance.mm.getModuleByName(HUDRenderer.lIllIlIl[8]);
        SettingsManager settingsmanager = Explicit.instance.sm;

        if (settingsmanager.getSettingByName(module, HUDRenderer.lIllIlIl[9]).getValBoolean()) {
            return Explicit.instance.cm.cc.getColor(i);
        } else {
            int j = (int) settingsmanager.getSettingByName(module, HUDRenderer.lIllIlIl[10]).getValDouble();
            int k = (int) settingsmanager.getSettingByName(module, HUDRenderer.lIllIlIl[11]).getValDouble();
            int l = (int) settingsmanager.getSettingByName(module, HUDRenderer.lIllIlIl[12]).getValDouble();

            return new Color(j, k, l, 255);
        }
    }

    private List getOrder() {
        List list = Explicit.instance.mm.getEnabledModules();
        ArrayList arraylist = null;
        Comparator comparator = new Comparator() {
            final HUDRenderer this$0 = HUDRenderer.this;

            public int compare(String s, String s1) {
                return Float.compare((float) this.this$0.mc.fontRendererObj.getStringWidth(s1), (float) this.this$0.mc.fontRendererObj.getStringWidth(s));
            }

            public int compare(Object object, Object object1) {
                return this.compare((String) object, (String) object1);
            }
        };

        if (arraylist == null) {
            (arraylist = new ArrayList()).clear();
        }

        for (int i = 0; i < list.size(); ++i) {
            arraylist.add(((Module) list.get(i)).getDisplayName());
        }

        Collections.sort(arraylist, comparator);
        return arraylist;
    }

    private List getOrderModule() {
        ArrayList arraylist;

        (arraylist = new ArrayList()).clear();
        Iterator iterator = this.getOrder().iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();

            if (Explicit.instance.mm.getModuleByName(s) != null) {
                arraylist.add(Explicit.instance.mm.getModuleByName(s));
            }
        }

        return arraylist;
    }

    static {
        lIlIIllIII();
        lIlIIlIlll();
    }

    private static void lIlIIlIlll() {
        lIllIlIl = new String[13];
        HUDRenderer.lIllIlIl[0] = lIlIIlIlII(HUDRenderer.lIllIllI[0], HUDRenderer.lIllIllI[1]);
        HUDRenderer.lIllIlIl[1] = lIlIIlIlII(HUDRenderer.lIllIllI[2], HUDRenderer.lIllIllI[3]);
        HUDRenderer.lIllIlIl[2] = lIlIIlIlII(HUDRenderer.lIllIllI[4], HUDRenderer.lIllIllI[5]);
        HUDRenderer.lIllIlIl[3] = lIlIIlIlIl(HUDRenderer.lIllIllI[6], HUDRenderer.lIllIllI[7]);
        HUDRenderer.lIllIlIl[4] = lIlIIlIlIl(HUDRenderer.lIllIllI[8], HUDRenderer.lIllIllI[9]);
        HUDRenderer.lIllIlIl[5] = lIlIIlIlII(HUDRenderer.lIllIllI[10], HUDRenderer.lIllIllI[11]);
        HUDRenderer.lIllIlIl[6] = lIlIIlIlII(HUDRenderer.lIllIllI[12], HUDRenderer.lIllIllI[13]);
        HUDRenderer.lIllIlIl[7] = lIlIIlIllI(HUDRenderer.lIllIllI[14], HUDRenderer.lIllIllI[15]);
        HUDRenderer.lIllIlIl[8] = lIlIIlIlII(HUDRenderer.lIllIllI[16], HUDRenderer.lIllIllI[17]);
        HUDRenderer.lIllIlIl[9] = lIlIIlIllI(HUDRenderer.lIllIllI[18], HUDRenderer.lIllIllI[19]);
        HUDRenderer.lIllIlIl[10] = lIlIIlIlIl(HUDRenderer.lIllIllI[20], HUDRenderer.lIllIllI[21]);
        HUDRenderer.lIllIlIl[11] = lIlIIlIlIl(HUDRenderer.lIllIllI[22], HUDRenderer.lIllIllI[23]);
        HUDRenderer.lIllIlIl[12] = lIlIIlIlIl(HUDRenderer.lIllIllI[24], HUDRenderer.lIllIllI[25]);
        HUDRenderer.lIllIllI = null;
    }

    private static void lIlIIllIII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        HUDRenderer.lIllIllI = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIlIIlIlII(String s, String s1) {
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

    private static String lIlIIlIlIl(String s, String s1) {
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

    private static String lIlIIlIllI(String s, String s1) {
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
