package me.explicit.module.misc;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;

public class SelfDestruct extends Module {

    private static final String[] llllIll;
    private static String[] lllllII;

    public SelfDestruct() {
        super(SelfDestruct.llllIll[0], 0, Category.MISC, SelfDestruct.llllIll[1]);
    }

    public void onEnable() {
        super.onEnable();
        Explicit.instance.onSelfDestruct();
        this.setToggled(false);
    }

    public void onTick() {
        Explicit.instance.onSelfDestruct();
        this.setToggled(false);
    }

    static {
        llIIlllIl();
        llIIlllII();
    }

    private static void llIIlllII() {
        llllIll = new String[2];
        SelfDestruct.llllIll[0] = llIIllIlI(SelfDestruct.lllllII[0], SelfDestruct.lllllII[1]);
        SelfDestruct.llllIll[1] = llIIllIll(SelfDestruct.lllllII[2], SelfDestruct.lllllII[3]);
        SelfDestruct.lllllII = null;
    }

    private static void llIIlllIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        SelfDestruct.lllllII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIIllIll(String s, String s1) {
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

    private static String llIIllIlI(String s, String s1) {
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
