package me.explicit.module.values;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;

public class ValueNaked extends Module {

    private static final String[] lIlII;
    private static String[] lIlIl;

    public ValueNaked() {
        super(ValueNaked.lIlII[0], 0, Category.VALUES, ValueNaked.lIlII[1]);
    }

    static {
        lIIIIll();
        lIIIIlI();
    }

    private static void lIIIIlI() {
        lIlII = new String[2];
        ValueNaked.lIlII[0] = lIIIIII(ValueNaked.lIlIl[0], ValueNaked.lIlIl[1]);
        ValueNaked.lIlII[1] = lIIIIIl(ValueNaked.lIlIl[2], ValueNaked.lIlIl[3]);
        ValueNaked.lIlIl = null;
    }

    private static void lIIIIll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ValueNaked.lIlIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIIIl(String s, String s1) {
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

    private static String lIIIIII(String s, String s1) {
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
