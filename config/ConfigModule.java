package me.explicit.config;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;

public class ConfigModule extends Module {

    private static final String[] lIIlIIIlI;
    private static String[] lIIlIIlII;

    public ConfigModule() {
        super(ConfigModule.lIIlIIIlI[0], ConfigModule.lIIlIIIlI[1], Category.CONFIGS);
    }

    public void onEnable() {
        super.onEnable();
        ConfigModule.mc.displayGuiScreen(new ConfigGUI(ConfigModule.mc.currentScreen));
        this.setToggled(false);
    }

    static {
        llllllIlII();
        llllllIIll();
    }

    private static void llllllIIll() {
        lIIlIIIlI = new String[2];
        ConfigModule.lIIlIIIlI[0] = llllllIIIl(ConfigModule.lIIlIIlII[0], ConfigModule.lIIlIIlII[1]);
        ConfigModule.lIIlIIIlI[1] = llllllIIlI(ConfigModule.lIIlIIlII[2], ConfigModule.lIIlIIlII[3]);
        ConfigModule.lIIlIIlII = null;
    }

    private static void llllllIlII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ConfigModule.lIIlIIlII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llllllIIIl(String s, String s1) {
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

    private static String llllllIIlI(String s, String s1) {
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
