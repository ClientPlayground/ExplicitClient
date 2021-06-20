package me.explicit.module.values;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;

public class ValueInvisibles extends Module {

    private static final String[] lIIIIIIII;
    private static String[] lIIIIIIIl;

    public ValueInvisibles() {
        super(ValueInvisibles.lIIIIIIII[0], 0, Category.VALUES, ValueInvisibles.lIIIIIIII[1]);
    }

    static {
        lllIIlIIII();
        lllIIIllll();
    }

    private static void lllIIIllll() {
        lIIIIIIII = new String[2];
        ValueInvisibles.lIIIIIIII[0] = lllIIIllIl(ValueInvisibles.lIIIIIIIl[0], ValueInvisibles.lIIIIIIIl[1]);
        ValueInvisibles.lIIIIIIII[1] = lllIIIlllI(ValueInvisibles.lIIIIIIIl[2], ValueInvisibles.lIIIIIIIl[3]);
        ValueInvisibles.lIIIIIIIl = null;
    }

    private static void lllIIlIIII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ValueInvisibles.lIIIIIIIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lllIIIlllI(String s, String s1) {
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

    private static String lllIIIllIl(String s, String s1) {
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
