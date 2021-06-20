package me.explicit.module.values;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;

public class ValueMobs extends Module {

    private static final String[] lllIlIl;
    private static String[] lllIlll;

    public ValueMobs() {
        super(ValueMobs.lllIlIl[0], 0, Category.VALUES, ValueMobs.lllIlIl[1]);
    }

    static {
        llIIlIlII();
        llIIlIIll();
    }

    private static void llIIlIIll() {
        lllIlIl = new String[2];
        ValueMobs.lllIlIl[0] = llIIlIIIl(ValueMobs.lllIlll[0], ValueMobs.lllIlll[1]);
        ValueMobs.lllIlIl[1] = llIIlIIlI(ValueMobs.lllIlll[2], ValueMobs.lllIlll[3]);
        ValueMobs.lllIlll = null;
    }

    private static void llIIlIlII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ValueMobs.lllIlll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIIlIIIl(String s, String s1) {
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

    private static String llIIlIIlI(String s, String s1) {
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
