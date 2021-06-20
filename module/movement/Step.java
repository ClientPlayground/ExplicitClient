package me.explicit.module.movement;

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
import me.explicit.utils.Game;

public class Step extends Module {

    private float oldStep;
    private static final String[] llllll;
    private static String[] lIIIIII;

    public Step() {
        super(Step.llllll[0], 0, Category.MOVEMENT, Step.llllll[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(Step.llllll[2], this, 3.0D, 1.0D, 10.0D, false));
    }

    public void onEnable() {
        super.onEnable();
        this.oldStep = Game.Player().stepHeight;
    }

    public void onDisable() {
        super.onDisable();
        Game.Player().stepHeight = this.oldStep;
    }

    public void onTick() {
        Game.Player().stepHeight = (float) Explicit.instance.sm.getSettingByName(this, Step.llllll[3]).getValInt();
    }

    static {
        llIlllll();
        llIllllI();
    }

    private static void llIllllI() {
        llllll = new String[4];
        Step.llllll[0] = llIlllII(Step.lIIIIII[0], Step.lIIIIII[1]);
        Step.llllll[1] = llIlllIl(Step.lIIIIII[2], Step.lIIIIII[3]);
        Step.llllll[2] = llIlllIl(Step.lIIIIII[4], Step.lIIIIII[5]);
        Step.llllll[3] = llIlllIl(Step.lIIIIII[6], Step.lIIIIII[7]);
        Step.lIIIIII = null;
    }

    private static void llIlllll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Step.lIIIIII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIlllIl(String s, String s1) {
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

    private static String llIlllII(String s, String s1) {
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
