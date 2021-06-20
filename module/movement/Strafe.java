package me.explicit.module.movement;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.Setting;
import me.explicit.utils.Game;
import me.explicit.utils.MoveUtils;
import me.explicit.utils.PrivateUtils;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Strafe extends Module {

    private static final String[] lIIIIllIl;
    private static String[] lIIIIlllI;

    public Strafe() {
        super(Strafe.lIIIIllIl[0], 0, Category.MOVEMENT, Strafe.lIIIIllIl[1]);
    }

    public void setup() {
        ArrayList arraylist;

        (arraylist = new ArrayList()).add(Strafe.lIIIIllIl[2]);
        arraylist.add(Strafe.lIIIIllIl[3]);
        Explicit.instance.sm.rSetting(new Setting(Strafe.lIIIIllIl[4], this, Strafe.lIIIIllIl[5], arraylist));
        Explicit.instance.sm.rSetting(new Setting(Strafe.lIIIIllIl[6], this, 1.08D, 0.1D, 3.0D, false));
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingUpdateEvent livingupdateevent) {
        double d0 = Explicit.instance.sm.getSettingByName(this, Strafe.lIIIIllIl[7]).getValDouble();
        String s = Explicit.instance.sm.getSettingByName(this, Strafe.lIIIIllIl[8]).getValString();

        if (livingupdateevent.entityLiving != null && livingupdateevent.entityLiving == Game.Player()) {
            if (s.equalsIgnoreCase(Strafe.lIIIIllIl[9])) {
                if (Game.Player().moveStrafing != 0.0F && Game.Player().onGround && Game.Player().ticksExisted % 2 == 0) {
                    MoveUtils.setMoveSpeed(d0 / 3.0D);
                }
            } else if (s.equalsIgnoreCase(Strafe.lIIIIllIl[10])) {
                if (Game.Player().moveStrafing != 0.0F && Game.Player().onGround) {
                    PrivateUtils.timer().timerSpeed = (float) d0;
                } else if (!Explicit.instance.mm.getModuleByName(Strafe.lIIIIllIl[11]).isToggled()) {
                    PrivateUtils.timer().timerSpeed = 1.0F;
                }
            }
        }

    }

    static {
        lllIllllll();
        lllIllllIl();
    }

    private static void lllIllllIl() {
        lIIIIllIl = new String[12];
        Strafe.lIIIIllIl[0] = lllIlllIIl(Strafe.lIIIIlllI[0], Strafe.lIIIIlllI[1]);
        Strafe.lIIIIllIl[1] = lllIlllIIl(Strafe.lIIIIlllI[2], Strafe.lIIIIlllI[3]);
        Strafe.lIIIIllIl[2] = lllIlllIlI(Strafe.lIIIIlllI[4], Strafe.lIIIIlllI[5]);
        Strafe.lIIIIllIl[3] = lllIlllIlI(Strafe.lIIIIlllI[6], Strafe.lIIIIlllI[7]);
        Strafe.lIIIIllIl[4] = lllIlllIlI(Strafe.lIIIIlllI[8], Strafe.lIIIIlllI[9]);
        Strafe.lIIIIllIl[5] = lllIlllIIl(Strafe.lIIIIlllI[10], Strafe.lIIIIlllI[11]);
        Strafe.lIIIIllIl[6] = lllIlllIIl(Strafe.lIIIIlllI[12], Strafe.lIIIIlllI[13]);
        Strafe.lIIIIllIl[7] = lllIlllIIl(Strafe.lIIIIlllI[14], Strafe.lIIIIlllI[15]);
        Strafe.lIIIIllIl[8] = lllIllllII(Strafe.lIIIIlllI[16], Strafe.lIIIIlllI[17]);
        Strafe.lIIIIllIl[9] = lllIllllII(Strafe.lIIIIlllI[18], Strafe.lIIIIlllI[19]);
        Strafe.lIIIIllIl[10] = lllIlllIIl(Strafe.lIIIIlllI[20], Strafe.lIIIIlllI[21]);
        Strafe.lIIIIllIl[11] = lllIlllIlI(Strafe.lIIIIlllI[22], Strafe.lIIIIlllI[23]);
        Strafe.lIIIIlllI = null;
    }

    private static void lllIllllll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Strafe.lIIIIlllI = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lllIlllIlI(String s, String s1) {
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

    private static String lllIllllII(String s, String s1) {
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

    private static String lllIlllIIl(String s, String s1) {
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
