package me.explicit.module.blatant;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.utils.Game;
import me.explicit.utils.MoveUtils;

public class LongJump extends Module {

    public long enabled;
    private static final String[] lIIIIIllI;
    private static String[] lIIIIlIII;

    public LongJump() {
        super(LongJump.lIIIIIllI[0], 0, Category.BLATANT, LongJump.lIIIIIllI[1]);
    }

    public void onMove() {
        if (Game.Player().onGround && System.currentTimeMillis() - this.enabled > 500L) {
            this.setToggled(false);
        } else {
            if (!Game.Player().onGround) {
                MoveUtils.setMoveSpeed(2.0D);
            }

        }
    }

    public void onEnable() {
        super.onEnable();
        if (Game.Player().onGround) {
            LongJump.mc.thePlayer.jump();
        } else {
            this.setToggled(false);
        }

        this.enabled = System.currentTimeMillis();
    }

    static {
        lllIlIlIII();
        lllIlIIlll();
    }

    private static void lllIlIIlll() {
        lIIIIIllI = new String[2];
        LongJump.lIIIIIllI[0] = lllIIlIlIl(LongJump.lIIIIlIII[0], LongJump.lIIIIlIII[1]);
        LongJump.lIIIIIllI[1] = lllIIlIlIl(LongJump.lIIIIlIII[2], LongJump.lIIIIlIII[3]);
        LongJump.lIIIIlIII = null;
    }

    private static void lllIlIlIII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        LongJump.lIIIIlIII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lllIIlIlIl(String s, String s1) {
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
