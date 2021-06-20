package me.explicit.module.player;

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

public class FastBreak extends Module {

    private static final String[] lIlIIllI;
    private static String[] lIlIlIII;

    public FastBreak() {
        super(FastBreak.lIlIIllI[0], FastBreak.lIlIIllI[1], Category.PLAYER);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(FastBreak.lIlIIllI[2], this, 0.2D, 0.0D, 1.0D, false));
    }

    public void onTick() {
        if (FastBreak.mc.currentScreen == null && FastBreak.mc.thePlayer != null && FastBreak.mc.objectMouseOver != null && FastBreak.mc.objectMouseOver.getBlockPos() != null && FastBreak.mc.gameSettings.keyBindAttack.isKeyDown()) {
            PrivateUtils.setBlockHitDelay(0);
            float f = PrivateUtils.getBlockDamage();

            if (f == -1.0F) {
                return;
            }

            if ((double) f > Explicit.instance.sm.getSettingByName(this, FastBreak.lIlIIllI[3]).getValDouble()) {
                PrivateUtils.setBlockDamage(1.0F);
            }
        }

    }

    static {
        lIIlIlIIlI();
        lIIlIlIIIl();
    }

    private static void lIIlIlIIIl() {
        lIlIIllI = new String[4];
        FastBreak.lIlIIllI[0] = lIIlIIllIl(FastBreak.lIlIlIII[0], FastBreak.lIlIlIII[1]);
        FastBreak.lIlIIllI[1] = lIIlIIllll(FastBreak.lIlIlIII[2], FastBreak.lIlIlIII[3]);
        FastBreak.lIlIIllI[2] = lIIlIIllIl(FastBreak.lIlIlIII[4], FastBreak.lIlIlIII[5]);
        FastBreak.lIlIIllI[3] = lIIlIIllIl(FastBreak.lIlIlIII[6], FastBreak.lIlIlIII[7]);
        FastBreak.lIlIlIII = null;
    }

    private static void lIIlIlIIlI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        FastBreak.lIlIlIII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIlIIllll(String s, String s1) {
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

    private static String lIIlIIllIl(String s, String s1) {
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
