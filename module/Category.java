package me.explicit.module;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public enum Category {

    COMBAT, BLATANT, MOVEMENT, RENDER, PLAYER, MISC, VALUES, CONFIGS;

    private static final Category[] $VALUES;
    private static final String[] lIIIIllI;
    private static String[] lIIIIlll;

    static {
        lllIlIIlI();
        lllIlIIIl();
        COMBAT = new Category(Category.lIIIIllI[0], 0);
        BLATANT = new Category(Category.lIIIIllI[1], 1);
        MOVEMENT = new Category(Category.lIIIIllI[2], 2);
        RENDER = new Category(Category.lIIIIllI[3], 3);
        PLAYER = new Category(Category.lIIIIllI[4], 4);
        MISC = new Category(Category.lIIIIllI[5], 5);
        VALUES = new Category(Category.lIIIIllI[6], 6);
        CONFIGS = new Category(Category.lIIIIllI[7], 7);
        $VALUES = new Category[] { Category.COMBAT, Category.BLATANT, Category.MOVEMENT, Category.RENDER, Category.PLAYER, Category.MISC, Category.VALUES, Category.CONFIGS};
    }

    private static void lllIlIIIl() {
        lIIIIllI = new String[8];
        Category.lIIIIllI[0] = lllIIlllI(Category.lIIIIlll[0], Category.lIIIIlll[1]);
        Category.lIIIIllI[1] = lllIIllll(Category.lIIIIlll[2], Category.lIIIIlll[3]);
        Category.lIIIIllI[2] = lllIIlllI(Category.lIIIIlll[4], Category.lIIIIlll[5]);
        Category.lIIIIllI[3] = lllIlIIII(Category.lIIIIlll[6], Category.lIIIIlll[7]);
        Category.lIIIIllI[4] = lllIIllll(Category.lIIIIlll[8], Category.lIIIIlll[9]);
        Category.lIIIIllI[5] = lllIIlllI(Category.lIIIIlll[10], Category.lIIIIlll[11]);
        Category.lIIIIllI[6] = lllIIllll(Category.lIIIIlll[12], Category.lIIIIlll[13]);
        Category.lIIIIllI[7] = lllIIlllI(Category.lIIIIlll[14], Category.lIIIIlll[15]);
        Category.lIIIIlll = null;
    }

    private static void lllIlIIlI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Category.lIIIIlll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lllIIlllI(String s, String s1) {
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

    private static String lllIlIIII(String s, String s1) {
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

    private static String lllIIllll(String s, String s1) {
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
