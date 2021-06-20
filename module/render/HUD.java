package me.explicit.module.render;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.Setting;

public class HUD extends Module {

    private static final String[] ll;
    private static String[] lIl;

    public HUD() {
        super(HUD.ll[0], 0, Category.RENDER, HUD.ll[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(HUD.ll[2], this, true));
        Explicit.instance.sm.rSetting(new Setting(HUD.ll[3], this, 170.0D, 0.0D, 255.0D, true));
        Explicit.instance.sm.rSetting(new Setting(HUD.ll[4], this, 0.0D, 0.0D, 255.0D, true));
        Explicit.instance.sm.rSetting(new Setting(HUD.ll[5], this, 0.0D, 0.0D, 255.0D, true));
    }

    static {
        lllI();
        lIll();
    }

    private static void lIll() {
        ll = new String[6];
        HUD.ll[0] = lI(HUD.lIl[0], HUD.lIl[1]);
        HUD.ll[1] = lI(HUD.lIl[2], HUD.lIl[3]);
        HUD.ll[2] = lIlI(HUD.lIl[4], HUD.lIl[5]);
        HUD.ll[3] = lI(HUD.lIl[6], HUD.lIl[7]);
        HUD.ll[4] = lI(HUD.lIl[8], HUD.lIl[9]);
        HUD.ll[5] = lIlI(HUD.lIl[10], HUD.lIl[11]);
        HUD.lIl = null;
    }

    private static void lllI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        HUD.lIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIlI(String s, String s1) {
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

    private static String lI(String s, String s1) {
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
