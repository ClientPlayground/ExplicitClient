package me.explicit.module.blatant;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.Setting;
import me.explicit.utils.Game;
import me.explicit.utils.MoveUtils;

public class Fly extends Module {

    private static final String[] llIllllI;
    private static String[] llIlllll;

    public Fly() {
        super(Fly.llIllllI[0], 0, Category.BLATANT, Fly.llIllllI[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(Fly.llIllllI[2], this, 1.0D, 0.05D, 5.0D, false));
    }

    public void onTick() {
        boolean flag = Fly.mc.gameSettings.keyBindJump.isKeyDown();
        boolean flag1 = Fly.mc.gameSettings.keyBindSneak.isKeyDown();
        double d0 = Explicit.instance.sm.getSettingByName(this, Fly.llIllllI[3]).getValDouble();

        if (!flag && !flag1) {
            Game.Player().motionY = 0.0D;
        } else if (flag) {
            Game.Player().motionY = d0;
        } else if (flag1) {
            Game.Player().motionY = -d0;
        }

        MoveUtils.setMoveSpeed(d0);
    }

    public void onDisable() {
        super.onDisable();
    }

    static {
        llIIlllIlI();
        llIIlllIIl();
    }

    private static void llIIlllIIl() {
        llIllllI = new String[4];
        Fly.llIllllI[0] = llIIllIlll(Fly.llIlllll[0], Fly.llIlllll[1]);
        Fly.llIllllI[1] = llIIlllIII(Fly.llIlllll[2], Fly.llIlllll[3]);
        Fly.llIllllI[2] = llIIlllIII(Fly.llIlllll[4], Fly.llIlllll[5]);
        Fly.llIllllI[3] = llIIllIlll(Fly.llIlllll[6], Fly.llIlllll[7]);
        Fly.llIlllll = null;
    }

    private static void llIIlllIlI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Fly.llIlllll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIIlllIII(String s, String s1) {
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

    private static String llIIllIlll(String s, String s1) {
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
