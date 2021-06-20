package me.explicit.module.values;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;

public class ValueAnimals extends Module {

    private static final String[] llIlIlIl;
    private static String[] llIlIllI;

    public ValueAnimals() {
        super(ValueAnimals.llIlIlIl[0], 0, Category.VALUES, ValueAnimals.llIlIlIl[1]);
    }

    static {
        llIIIllllI();
        llIIIlllIl();
    }

    private static void llIIIlllIl() {
        llIlIlIl = new String[2];
        ValueAnimals.llIlIlIl[0] = llIIIllIlI(ValueAnimals.llIlIllI[0], ValueAnimals.llIlIllI[1]);
        ValueAnimals.llIlIlIl[1] = llIIIllIlI(ValueAnimals.llIlIllI[2], ValueAnimals.llIlIllI[3]);
        ValueAnimals.llIlIllI = null;
    }

    private static void llIIIllllI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ValueAnimals.llIlIllI = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIIIllIlI(String s, String s1) {
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
