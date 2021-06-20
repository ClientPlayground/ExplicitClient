package me.explicit.utils;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class VersionCheck extends Thread {

    private static final String[] llllIl;
    private static String[] lllllI;

    public void run() {
        try {
            URL url = new URL(VersionCheck.llllIl[0]);
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(url.openStream()));
            boolean flag = false;

            String s;

            while ((s = bufferedreader.readLine()) != null) {
                String[] astring = s.split(VersionCheck.llllIl[1]);
                String[] astring1 = astring;
                int i = astring.length;

                for (int j = 0; j < i; ++j) {
                    String s1 = astring1[j];

                    if (s1.equalsIgnoreCase(VersionCheck.llllIl[2])) {
                        flag = true;
                    }
                }
            }

            if (!flag) {
                JOptionPane.showMessageDialog((Component) null, VersionCheck.llllIl[3], VersionCheck.llllIl[4], 2);
            }

            bufferedreader.close();
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }

    }

    static {
        llIllIll();
        llIllIlI();
    }

    private static void llIllIlI() {
        llllIl = new String[5];
        VersionCheck.llllIl[0] = llIlIlll(VersionCheck.lllllI[0], VersionCheck.lllllI[1]);
        VersionCheck.llllIl[1] = llIllIII(VersionCheck.lllllI[2], VersionCheck.lllllI[3]);
        VersionCheck.llllIl[2] = llIllIIl(VersionCheck.lllllI[4], VersionCheck.lllllI[5]);
        VersionCheck.llllIl[3] = llIlIlll(VersionCheck.lllllI[6], VersionCheck.lllllI[7]);
        VersionCheck.llllIl[4] = llIllIIl(VersionCheck.lllllI[8], VersionCheck.lllllI[9]);
        VersionCheck.lllllI = null;
    }

    private static void llIllIll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        VersionCheck.lllllI = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIllIIl(String s, String s1) {
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

    private static String llIlIlll(String s, String s1) {
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

    private static String llIllIII(String s, String s1) {
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
