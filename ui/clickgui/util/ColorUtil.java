package me.explicit.ui.clickgui.util;

import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.render.ClickGUI;

public class ColorUtil {

    private static final String[] lIlIIlll;
    private static String[] lIlIlIIl;

    public static Color getClickGUIColor() {
        return ClickGUI.mode.equalsIgnoreCase(ColorUtil.lIlIIlll[0]) ? new Color(Explicit.instance.cm.cc.getColor(0).getRed(), Explicit.instance.cm.cc.getColor(0).getGreen(), Explicit.instance.cm.cc.getColor(0).getBlue(), 255) : (ClickGUI.mode.equalsIgnoreCase(ColorUtil.lIlIIlll[1]) ? Color.getHSBColor(0.62F, 1.0F, 1.0F) : (ClickGUI.mode.equalsIgnoreCase(ColorUtil.lIlIIlll[2]) ? new Color(0, 255, 0) : (ClickGUI.mode.equalsIgnoreCase(ColorUtil.lIlIIlll[3]) ? new Color(255, 0, 0) : (ClickGUI.mode.equalsIgnoreCase(ColorUtil.lIlIIlll[4]) ? new Color(128, 0, 128) : (ClickGUI.mode.equalsIgnoreCase(ColorUtil.lIlIIlll[5]) ? new Color(20, 20, 20, 255) : new Color(1, 1, 1, 255))))));
    }

    static {
        lIIlIlIlIl();
        lIIlIlIlII();
    }

    private static void lIIlIlIlII() {
        lIlIIlll = new String[6];
        ColorUtil.lIlIIlll[0] = lIIlIIlllI(ColorUtil.lIlIlIIl[0], ColorUtil.lIlIlIIl[1]);
        ColorUtil.lIlIIlll[1] = lIIlIlIIII(ColorUtil.lIlIlIIl[2], ColorUtil.lIlIlIIl[3]);
        ColorUtil.lIlIIlll[2] = lIIlIIlllI(ColorUtil.lIlIlIIl[4], ColorUtil.lIlIlIIl[5]);
        ColorUtil.lIlIIlll[3] = lIIlIIlllI(ColorUtil.lIlIlIIl[6], ColorUtil.lIlIlIIl[7]);
        ColorUtil.lIlIIlll[4] = lIIlIlIIII(ColorUtil.lIlIlIIl[8], ColorUtil.lIlIlIIl[9]);
        ColorUtil.lIlIIlll[5] = lIIlIlIIll(ColorUtil.lIlIlIIl[10], ColorUtil.lIlIlIIl[11]);
        ColorUtil.lIlIlIIl = null;
    }

    private static void lIIlIlIlIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ColorUtil.lIlIlIIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIlIlIIII(String s, String s1) {
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

    private static String lIIlIlIIll(String s, String s1) {
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

    private static String lIIlIIlllI(String s, String s1) {
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
}
