package me.explicit.module.values;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;

public class ValueFriends extends Module {

    private static final String[] lIIllIl;
    private static String[] lIIllll;

    public ValueFriends() {
        super(ValueFriends.lIIllIl[0], 0, Category.VALUES, ValueFriends.lIIllIl[1]);
        this.setToggled(true);
    }

    static {
        lIIIIlllI();
        lIIIIllIl();
    }

    private static void lIIIIllIl() {
        lIIllIl = new String[2];
        ValueFriends.lIIllIl[0] = lIIIIlIll(ValueFriends.lIIllll[0], ValueFriends.lIIllll[1]);
        ValueFriends.lIIllIl[1] = lIIIIlIll(ValueFriends.lIIllll[2], ValueFriends.lIIllll[3]);
        ValueFriends.lIIllll = null;
    }

    private static void lIIIIlllI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ValueFriends.lIIllll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIIlIll(String s, String s1) {
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
