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
import me.explicit.utils.CombatUtils;
import me.explicit.utils.Game;
import me.explicit.utils.TimerUtils;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import org.lwjgl.input.Keyboard;

public class WTap extends Module {

    boolean shouldTap = false;
    private TimerUtils tDelay = new TimerUtils();
    private TimerUtils tHold = new TimerUtils();
    private TimerUtils updateData = new TimerUtils();
    private double delay;
    private double hold;
    boolean hasReached = false;
    private static final String[] lIIIlIIIl;
    private static String[] lIIIlIIlI;

    public WTap() {
        super(WTap.lIIIlIIIl[0], 0, Category.MOVEMENT, WTap.lIIIlIIIl[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(WTap.lIIIlIIIl[2], this, 100.0D, 1.0D, 500.0D, true));
        Explicit.instance.sm.rSetting(new Setting(WTap.lIIIlIIIl[3], this, 150.0D, 1.0D, 500.0D, true));
        Explicit.instance.sm.rSetting(new Setting(WTap.lIIIlIIIl[4], this, 10.0D, 1.0D, 250.0D, true));
        Explicit.instance.sm.rSetting(new Setting(WTap.lIIIlIIIl[5], this, 11.0D, 1.0D, 250.0D, true));
    }

    public void onEnable() {
        this.shouldTap = false;
        this.hasReached = false;
        super.onEnable();
    }

    public void setData() {
        double d0 = Explicit.instance.sm.getSettingByName(this, WTap.lIIIlIIIl[6]).getValDouble();
        double d1 = Explicit.instance.sm.getSettingByName(this, WTap.lIIIlIIIl[7]).getValDouble();
        double d2 = Explicit.instance.sm.getSettingByName(this, WTap.lIIIlIIIl[8]).getValDouble();
        double d3 = Explicit.instance.sm.getSettingByName(this, WTap.lIIIlIIIl[9]).getValDouble();

        if (d0 == d1 || d0 > d1) {
            d1 = d0 * 1.1D;
        }

        if (d2 == d3 || d2 > d3) {
            d3 = d2 * 1.1D;
        }

        this.delay = ThreadLocalRandom.current().nextDouble(Math.min(d0, d1), Math.max(d0, d1));
        this.hold = ThreadLocalRandom.current().nextDouble(Math.min(d2, d3), Math.max(d2, d3));
    }

    @SubscribeEvent
    public void t3s4f(RenderTickEvent rendertickevent) {
        if (this.updateData.hasReached(1000.0D)) {
            this.setData();
            this.hasReached = true;
            this.updateData.reset();
        }

        if (this.hasReached) {
            if (this.tHold.hasReached(this.hold) && this.shouldTap && Keyboard.isKeyDown(WTap.mc.gameSettings.keyBindForward.getKeyCode())) {
                KeyBinding.setKeyBindState(WTap.mc.gameSettings.keyBindForward.getKeyCode(), true);
                this.shouldTap = false;
            } else if (!Keyboard.isKeyDown(WTap.mc.gameSettings.keyBindForward.getKeyCode())) {
                KeyBinding.setKeyBindState(WTap.mc.gameSettings.keyBindForward.getKeyCode(), false);
            }

        }
    }

    @SubscribeEvent(
        priority = EventPriority.LOWEST
    )
    public void asdfgbnv(AttackEntityEvent attackentityevent) {
        if (this.tDelay.hasReached(this.delay) && !this.shouldTap && Module.mc.objectMouseOver != null && Module.mc.objectMouseOver.typeOfHit == MovingObjectType.ENTITY && Module.mc.objectMouseOver.entityHit.getEntityId() == attackentityevent.target.getEntityId() && CombatUtils.canTarget(Game.World().getEntityByID(attackentityevent.target.getEntityId()), true)) {
            this.shouldTap = true;
            this.tHold.reset();
            this.tDelay.reset();
            KeyBinding.setKeyBindState(WTap.mc.gameSettings.keyBindForward.getKeyCode(), false);
        }

    }

    static {
        llllIIlIll();
        llllIIlIlI();
    }

    private static void llllIIlIlI() {
        lIIIlIIIl = new String[10];
        WTap.lIIIlIIIl[0] = llllIIIlll(WTap.lIIIlIIlI[0], WTap.lIIIlIIlI[1]);
        WTap.lIIIlIIIl[1] = llllIIlIII(WTap.lIIIlIIlI[2], WTap.lIIIlIIlI[3]);
        WTap.lIIIlIIIl[2] = llllIIIlll(WTap.lIIIlIIlI[4], WTap.lIIIlIIlI[5]);
        WTap.lIIIlIIIl[3] = llllIIIlll(WTap.lIIIlIIlI[6], WTap.lIIIlIIlI[7]);
        WTap.lIIIlIIIl[4] = llllIIlIII(WTap.lIIIlIIlI[8], WTap.lIIIlIIlI[9]);
        WTap.lIIIlIIIl[5] = llllIIlIII(WTap.lIIIlIIlI[10], WTap.lIIIlIIlI[11]);
        WTap.lIIIlIIIl[6] = llllIIlIIl(WTap.lIIIlIIlI[12], WTap.lIIIlIIlI[13]);
        WTap.lIIIlIIIl[7] = llllIIlIIl(WTap.lIIIlIIlI[14], WTap.lIIIlIIlI[15]);
        WTap.lIIIlIIIl[8] = llllIIlIII(WTap.lIIIlIIlI[16], WTap.lIIIlIIlI[17]);
        WTap.lIIIlIIIl[9] = llllIIIlll(WTap.lIIIlIIlI[18], WTap.lIIIlIIlI[19]);
        WTap.lIIIlIIlI = null;
    }

    private static void llllIIlIll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        WTap.lIIIlIIlI = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llllIIlIIl(String s, String s1) {
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

    private static String llllIIIlll(String s, String s1) {
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

    private static String llllIIlIII(String s, String s1) {
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
