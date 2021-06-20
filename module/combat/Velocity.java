package me.explicit.module.combat;

import io.netty.util.internal.ThreadLocalRandom;
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
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemSword;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Velocity extends Module {

    private static final String[] llIlIl;
    private static String[] llIlll;

    public Velocity() {
        super(Velocity.llIlIl[0], 0, Category.COMBAT, Velocity.llIlIl[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(Velocity.llIlIl[2], this, 90.0D, 0.0D, 100.0D, true, true));
        Explicit.instance.sm.rSetting(new Setting(Velocity.llIlIl[3], this, 100.0D, 0.0D, 100.0D, true, true));
        Explicit.instance.sm.rSetting(new Setting(Velocity.llIlIl[4], this, 60.0D, 0.0D, 100.0D, true, true));
        Explicit.instance.sm.rSetting(new Setting(Velocity.llIlIl[5], this, true));
        Explicit.instance.sm.rSetting(new Setting(Velocity.llIlIl[6], this, true));
        Explicit.instance.sm.rSetting(new Setting(Velocity.llIlIl[7], this, false));
        Explicit.instance.sm.rSetting(new Setting(Velocity.llIlIl[8], this, false));
    }

    @SubscribeEvent
    public void onEv(LivingUpdateEvent livingupdateevent) {
        float f = (float) Explicit.instance.sm.getSettingByName(this, Velocity.llIlIl[9]).getValDouble();
        float f1 = (float) Explicit.instance.sm.getSettingByName(this, Velocity.llIlIl[10]).getValDouble();

        if (this.canVelocity() && Game.Player().hurtTime == Game.Player().maxHurtTime && Game.Player().maxHurtTime > 0) {
            EntityPlayerSP entityplayersp = Game.Player();

            entityplayersp.motionX *= (double) f / 100.0D;
            EntityPlayerSP entityplayersp1 = Game.Player();

            entityplayersp1.motionY *= (double) f1 / 100.0D;
            EntityPlayerSP entityplayersp2 = Game.Player();

            entityplayersp2.motionZ *= (double) f / 100.0D;
        }

    }

    public boolean canVelocity() {
        int i = 100 - (int) Explicit.instance.sm.getSettingByName(this, Velocity.llIlIl[11]).getValDouble();
        boolean flag = Explicit.instance.sm.getSettingByName(this, Velocity.llIlIl[12]).getValBoolean();
        boolean flag1 = Explicit.instance.sm.getSettingByName(this, Velocity.llIlIl[13]).getValBoolean();
        boolean flag2 = Explicit.instance.sm.getSettingByName(this, Velocity.llIlIl[14]).getValBoolean();
        boolean flag3 = Explicit.instance.sm.getSettingByName(this, Velocity.llIlIl[15]).getValBoolean();

        if (Game.World() != null && Game.Player() != null && (!flag || Game.Player().isSprinting()) && i < ThreadLocalRandom.current().nextInt(0, 101)) {
            if (flag1 && Velocity.mc.objectMouseOver != null && Velocity.mc.objectMouseOver.entityHit == null) {
                return false;
            } else {
                if (flag2) {
                    if (Game.Player().getCurrentEquippedItem() == null) {
                        return false;
                    }

                    if (!(Game.Player().getCurrentEquippedItem().getItem() instanceof ItemSword) && !(Game.Player().getCurrentEquippedItem().getItem() instanceof ItemAxe)) {
                        return false;
                    }
                }

                return !Game.Player().isInWater() && !Game.Player().isInLava() || !flag3;
            }
        } else {
            return false;
        }
    }

    static {
        llIIIIII();
        lIllllll();
    }

    private static void lIllllll() {
        llIlIl = new String[16];
        Velocity.llIlIl[0] = lIlIlIII(Velocity.llIlll[0], Velocity.llIlll[1]);
        Velocity.llIlIl[1] = lIlIlIII(Velocity.llIlll[2], Velocity.llIlll[3]);
        Velocity.llIlIl[2] = lIllIlIl(Velocity.llIlll[4], Velocity.llIlll[5]);
        Velocity.llIlIl[3] = lIlIlIII(Velocity.llIlll[6], Velocity.llIlll[7]);
        Velocity.llIlIl[4] = lIllIlll(Velocity.llIlll[8], Velocity.llIlll[9]);
        Velocity.llIlIl[5] = lIlIlIII(Velocity.llIlll[10], Velocity.llIlll[11]);
        Velocity.llIlIl[6] = lIllIlll(Velocity.llIlll[12], Velocity.llIlll[13]);
        Velocity.llIlIl[7] = lIlIlIII(Velocity.llIlll[14], Velocity.llIlll[15]);
        Velocity.llIlIl[8] = lIllIlIl(Velocity.llIlll[16], Velocity.llIlll[17]);
        Velocity.llIlIl[9] = lIlIlIII(Velocity.llIlll[18], Velocity.llIlll[19]);
        Velocity.llIlIl[10] = lIllIlll(Velocity.llIlll[20], Velocity.llIlll[21]);
        Velocity.llIlIl[11] = lIllIlll(Velocity.llIlll[22], Velocity.llIlll[23]);
        Velocity.llIlIl[12] = lIllIlIl(Velocity.llIlll[24], Velocity.llIlll[25]);
        Velocity.llIlIl[13] = lIllIlll(Velocity.llIlll[26], Velocity.llIlll[27]);
        Velocity.llIlIl[14] = lIllIlIl(Velocity.llIlll[28], Velocity.llIlll[29]);
        Velocity.llIlIl[15] = lIllIlIl(Velocity.llIlll[30], Velocity.llIlll[31]);
        Velocity.llIlll = null;
    }

    private static void llIIIIII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Velocity.llIlll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIllIlll(String s, String s1) {
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

    private static String lIllIlIl(String s, String s1) {
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

    private static String lIlIlIII(String s, String s1) {
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
