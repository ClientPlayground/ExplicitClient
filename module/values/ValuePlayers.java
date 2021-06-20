package me.explicit.module.values;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;

public class ValuePlayers extends Module {

    private static final String[] lIlllI;
    private static String[] lIllll;

    public ValuePlayers() {
        super(ValuePlayers.lIlllI[0], 0, Category.VALUES, ValuePlayers.lIlllI[1]);
        this.setToggled(true);
    }

    static {
        lIIlllIl();
        lIIlllII();
    }

    private static void lIIlllII() {
        lIlllI = new String[2];
        ValuePlayers.lIlllI[0] = lIIllIll(ValuePlayers.lIllll[0], ValuePlayers.lIllll[1]);
        ValuePlayers.lIlllI[1] = lIIllIll(ValuePlayers.lIllll[2], ValuePlayers.lIllll[3]);
        ValuePlayers.lIllll = null;
    }

    private static void lIIlllIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ValuePlayers.lIllll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIllIll(String s, String s1) {
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
