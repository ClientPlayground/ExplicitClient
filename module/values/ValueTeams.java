package me.explicit.module.values;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;

public class ValueTeams extends Module {

    private static final String[] llllIIII;
    private static String[] llllIIIl;

    public ValueTeams() {
        super(ValueTeams.llllIIII[0], 0, Category.VALUES, ValueTeams.llllIIII[1]);
    }

    static {
        llIllIllII();
        llIllIlIll();
    }

    private static void llIllIlIll() {
        llllIIII = new String[2];
        ValueTeams.llllIIII[0] = llIllIlIIl(ValueTeams.llllIIIl[0], ValueTeams.llllIIIl[1]);
        ValueTeams.llllIIII[1] = llIllIlIlI(ValueTeams.llllIIIl[2], ValueTeams.llllIIIl[3]);
        ValueTeams.llllIIIl = null;
    }

    private static void llIllIllII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ValueTeams.llllIIIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIllIlIIl(String s, String s1) {
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

    private static String llIllIlIlI(String s, String s1) {
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
}
