package me.explicit.module.misc;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.Setting;
import me.explicit.utils.PrivateUtils;

public class Timer extends Module {

    private static final String[] lllllIII;
    private static String[] lllllIIl;

    public Timer() {
        super(Timer.lllllIII[0], 0, Category.MISC, Timer.lllllIII[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(Timer.lllllIII[2], this, 1.25D, 0.1D, 10.0D, false));
    }

    public void onUpdate() {
        PrivateUtils.timer().timerSpeed = (float) Explicit.instance.sm.getSettingByName(this, Timer.lllllIII[3]).getValDouble();
    }

    public void onDisable() {
        super.onDisable();
        PrivateUtils.timer().timerSpeed = 1.0F;
    }

    static {
        llIllllIll();
        llIllllIlI();
    }

    private static void llIllllIlI() {
        lllllIII = new String[4];
        Timer.lllllIII[0] = llIllllIII(Timer.lllllIIl[0], Timer.lllllIIl[1]);
        Timer.lllllIII[1] = llIllllIII(Timer.lllllIIl[2], Timer.lllllIIl[3]);
        Timer.lllllIII[2] = llIllllIII(Timer.lllllIIl[4], Timer.lllllIIl[5]);
        Timer.lllllIII[3] = llIllllIIl(Timer.lllllIIl[6], Timer.lllllIIl[7]);
        Timer.lllllIIl = null;
    }

    private static void llIllllIll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Timer.lllllIIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIllllIII(String s, String s1) {
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

    private static String llIllllIIl(String s, String s1) {
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
