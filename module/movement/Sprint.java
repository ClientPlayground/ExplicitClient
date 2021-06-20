package me.explicit.module.movement;

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
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;

public class Sprint extends Module {

    private static final String[] llll;
    private static String[] lIIII;

    public Sprint() {
        super(Sprint.llll[0], 0, Category.MOVEMENT, Sprint.llll[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(Sprint.llll[2], this, false));
    }

    public void onTick() {
        if (Sprint.mc.inGameHasFocus && !Game.Player().isSneaking()) {
            EntityPlayerSP entityplayersp = Game.Player();

            if (Explicit.instance.sm.getSettingByName(this, Sprint.llll[3]).getValBoolean()) {
                if (MoveUtils.PlayerMoving() && entityplayersp.getFoodStats().getFoodLevel() > 6) {
                    entityplayersp.setSprinting(true);
                }
            } else {
                KeyBinding.setKeyBindState(Sprint.mc.gameSettings.keyBindSprint.getKeyCode(), true);
            }
        }

    }

    static {
        llIlIl();
        llIlII();
    }

    private static void llIlII() {
        llll = new String[4];
        Sprint.llll[0] = llIIlI(Sprint.lIIII[0], Sprint.lIIII[1]);
        Sprint.llll[1] = llIIll(Sprint.lIIII[2], Sprint.lIIII[3]);
        Sprint.llll[2] = llIIll(Sprint.lIIII[4], Sprint.lIIII[5]);
        Sprint.llll[3] = llIIll(Sprint.lIIII[6], Sprint.lIIII[7]);
        Sprint.lIIII = null;
    }

    private static void llIlIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Sprint.lIIII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIIll(String s, String s1) {
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

    private static String llIIlI(String s, String s1) {
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
