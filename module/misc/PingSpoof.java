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

public class PingSpoof extends Module {

    public static boolean toggled;
    public static int ping;
    private static final String[] lllIlIIl;
    private static String[] lllIlIll;

    public PingSpoof() {
        super(PingSpoof.lllIlIIl[0], PingSpoof.lllIlIIl[1], Category.MISC);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(PingSpoof.lllIlIIl[2], this, 150.0D, 1.0D, 6000.0D, true));
    }

    public void onUpdateNoToggle() {
        PingSpoof.toggled = this.isToggled();
    }

    public void onUpdate() {
        PingSpoof.ping = Explicit.instance.sm.getSettingByName(this, PingSpoof.lllIlIIl[3]).getValInt();
    }

    static {
        llIllIIlII();
        llIllIIIll();
        PingSpoof.toggled = false;
        PingSpoof.ping = 0;
    }

    private static void llIllIIIll() {
        lllIlIIl = new String[4];
        PingSpoof.lllIlIIl[0] = llIlIllIlI(PingSpoof.lllIlIll[0], PingSpoof.lllIlIll[1]);
        PingSpoof.lllIlIIl[1] = llIlIllIll(PingSpoof.lllIlIll[2], PingSpoof.lllIlIll[3]);
        PingSpoof.lllIlIIl[2] = llIlIllIlI(PingSpoof.lllIlIll[4], PingSpoof.lllIlIll[5]);
        PingSpoof.lllIlIIl[3] = llIlIllIll(PingSpoof.lllIlIll[6], PingSpoof.lllIlIll[7]);
        PingSpoof.lllIlIll = null;
    }

    private static void llIllIIlII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        PingSpoof.lllIlIll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIlIllIlI(String s, String s1) {
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

    private static String llIlIllIll(String s, String s1) {
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
