package me.explicit.module.misc;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;

public class NameProtect extends Module {

    private static final String[] lIIIlIllI;
    private static String[] lIIIlIlll;

    public NameProtect() {
        super(NameProtect.lIIIlIllI[0], 0, Category.MISC, NameProtect.lIIIlIllI[1]);
    }

    static {
        lllllIIIII();
        llllIlllll();
    }

    private static void llllIlllll() {
        lIIIlIllI = new String[2];
        NameProtect.lIIIlIllI[0] = llllIlllII(NameProtect.lIIIlIlll[0], NameProtect.lIIIlIlll[1]);
        NameProtect.lIIIlIllI[1] = llllIllllI(NameProtect.lIIIlIlll[2], NameProtect.lIIIlIlll[3]);
        NameProtect.lIIIlIlll = null;
    }

    private static void lllllIIIII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        NameProtect.lIIIlIlll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llllIllllI(String s, String s1) {
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

    private static String llllIlllII(String s, String s1) {
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
