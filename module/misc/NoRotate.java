package me.explicit.module.misc;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;

public class NoRotate extends Module {

    private static final String[] lIIIIIlIl;
    private static String[] lIIIIIlll;

    public NoRotate() {
        super(NoRotate.lIIIIIlIl[0], 0, Category.MISC, NoRotate.lIIIIIlIl[1]);
    }

    static {
        lllIIllIIl();
        lllIIlIlll();
    }

    private static void lllIIlIlll() {
        lIIIIIlIl = new String[2];
        NoRotate.lIIIIIlIl[0] = lllIIlIlII(NoRotate.lIIIIIlll[0], NoRotate.lIIIIIlll[1]);
        NoRotate.lIIIIIlIl[1] = lllIIlIlII(NoRotate.lIIIIIlll[2], NoRotate.lIIIIIlll[3]);
        NoRotate.lIIIIIlll = null;
    }

    private static void lllIIllIIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        NoRotate.lIIIIIlll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lllIIlIlII(String s, String s1) {
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
