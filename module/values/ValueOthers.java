package me.explicit.module.values;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;

public class ValueOthers extends Module {

    private static final String[] lIlIlI;
    private static String[] lIlIll;

    public ValueOthers() {
        super(ValueOthers.lIlIlI[0], 0, Category.VALUES, ValueOthers.lIlIlI[1]);
    }

    static {
        lIIlIllI();
        lIIlIlIl();
    }

    private static void lIIlIlIl() {
        lIlIlI = new String[2];
        ValueOthers.lIlIlI[0] = lIIlIIll(ValueOthers.lIlIll[0], ValueOthers.lIlIll[1]);
        ValueOthers.lIlIlI[1] = lIIlIlII(ValueOthers.lIlIll[2], ValueOthers.lIlIll[3]);
        ValueOthers.lIlIll = null;
    }

    private static void lIIlIllI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ValueOthers.lIlIll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIlIIll(String s, String s1) {
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

    private static String lIIlIlII(String s, String s1) {
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
