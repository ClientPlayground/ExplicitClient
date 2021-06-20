package me.explicit.module.render;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.Setting;

public class TimeChanger extends Module {

    private static final String[] lllIlIlI;
    private static String[] llllIIll;

    public TimeChanger() {
        super(TimeChanger.lllIlIlI[0], 0, Category.RENDER, TimeChanger.lllIlIlI[1]);
    }

    public void setup() {
        ArrayList arraylist;

        (arraylist = new ArrayList()).add(TimeChanger.lllIlIlI[2]);
        arraylist.add(TimeChanger.lllIlIlI[3]);
        arraylist.add(TimeChanger.lllIlIlI[4]);
        Explicit.instance.sm.rSetting(new Setting(TimeChanger.lllIlIlI[5], this, TimeChanger.lllIlIlI[6], arraylist));
    }

    static {
        llIlllIIII();
        llIllIllll();
    }

    private static void llIllIllll() {
        lllIlIlI = new String[7];
        TimeChanger.lllIlIlI[0] = llIlIlllIl(TimeChanger.llllIIll[0], TimeChanger.llllIIll[1]);
        TimeChanger.lllIlIlI[1] = llIllIIIIl(TimeChanger.llllIIll[2], TimeChanger.llllIIll[3]);
        TimeChanger.lllIlIlI[2] = llIlIlllIl(TimeChanger.llllIIll[4], TimeChanger.llllIIll[5]);
        TimeChanger.lllIlIlI[3] = llIllIIIlI(TimeChanger.llllIIll[6], TimeChanger.llllIIll[7]);
        TimeChanger.lllIlIlI[4] = llIlIlllIl(TimeChanger.llllIIll[8], TimeChanger.llllIIll[9]);
        TimeChanger.lllIlIlI[5] = llIlIlllIl(TimeChanger.llllIIll[10], TimeChanger.llllIIll[11]);
        TimeChanger.lllIlIlI[6] = llIllIIIIl(TimeChanger.llllIIll[12], TimeChanger.llllIIll[13]);
        TimeChanger.llllIIll = null;
    }

    private static void llIlllIIII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        TimeChanger.llllIIll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIlIlllIl(String s, String s1) {
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

    private static String llIllIIIIl(String s, String s1) {
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

    private static String llIllIIIlI(String s, String s1) {
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
