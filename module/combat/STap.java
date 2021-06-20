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

public class STap extends Module {

    boolean shouldTap = false;
    private TimerUtils tDelay = new TimerUtils();
    private TimerUtils tHold = new TimerUtils();
    private TimerUtils updateData = new TimerUtils();
    private double delay;
    private double hold;
    boolean hasReached = false;
    private static final String[] lIIlIIlI;
    private static String[] lIIlIIll;

    public STap() {
        super(STap.lIIlIIlI[0], 0, Category.MOVEMENT, STap.lIIlIIlI[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(STap.lIIlIIlI[2], this, 100.0D, 1.0D, 500.0D, true));
        Explicit.instance.sm.rSetting(new Setting(STap.lIIlIIlI[3], this, 150.0D, 1.0D, 500.0D, true));
        Explicit.instance.sm.rSetting(new Setting(STap.lIIlIIlI[4], this, 10.0D, 1.0D, 250.0D, true));
        Explicit.instance.sm.rSetting(new Setting(STap.lIIlIIlI[5], this, 11.0D, 1.0D, 250.0D, true));
    }

    public void onEnable() {
        this.shouldTap = false;
        this.hasReached = false;
        super.onEnable();
    }

    public void setData() {
        double d0 = Explicit.instance.sm.getSettingByName(this, STap.lIIlIIlI[6]).getValDouble();
        double d1 = Explicit.instance.sm.getSettingByName(this, STap.lIIlIIlI[7]).getValDouble();
        double d2 = Explicit.instance.sm.getSettingByName(this, STap.lIIlIIlI[8]).getValDouble();
        double d3 = Explicit.instance.sm.getSettingByName(this, STap.lIIlIIlI[9]).getValDouble();

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
    public void t34ff(RenderTickEvent rendertickevent) {
        double d0 = Explicit.instance.sm.getSettingByName(this, STap.lIIlIIlI[10]).getValDouble();
        double d1 = Explicit.instance.sm.getSettingByName(this, STap.lIIlIIlI[11]).getValDouble();

        if (this.updateData.hasReached(Math.max(d0, d1) * (ThreadLocalRandom.current().nextDouble() + ThreadLocalRandom.current().nextDouble()) * 2.0D)) {
            this.setData();
            this.hasReached = true;
            this.updateData.reset();
        }

        if (this.hasReached) {
            if (this.tHold.hasReached(this.hold) && this.shouldTap && Keyboard.isKeyDown(STap.mc.gameSettings.keyBindBack.getKeyCode())) {
                KeyBinding.setKeyBindState(STap.mc.gameSettings.keyBindBack.getKeyCode(), true);
                this.shouldTap = false;
            } else if (!Keyboard.isKeyDown(STap.mc.gameSettings.keyBindBack.getKeyCode())) {
                KeyBinding.setKeyBindState(STap.mc.gameSettings.keyBindBack.getKeyCode(), false);
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
            KeyBinding.setKeyBindState(STap.mc.gameSettings.keyBindBack.getKeyCode(), false);
        }

    }

    static {
        lIIIIIIlII();
        lIIIIIIIll();
    }

    private static void lIIIIIIIll() {
        lIIlIIlI = new String[12];
        STap.lIIlIIlI[0] = lIIIIIIIII(STap.lIIlIIll[0], STap.lIIlIIll[1]);
        STap.lIIlIIlI[1] = lIIIIIIIIl(STap.lIIlIIll[2], STap.lIIlIIll[3]);
        STap.lIIlIIlI[2] = lIIIIIIIII(STap.lIIlIIll[4], STap.lIIlIIll[5]);
        STap.lIIlIIlI[3] = lIIIIIIIlI(STap.lIIlIIll[6], STap.lIIlIIll[7]);
        STap.lIIlIIlI[4] = lIIIIIIIlI(STap.lIIlIIll[8], STap.lIIlIIll[9]);
        STap.lIIlIIlI[5] = lIIIIIIIII(STap.lIIlIIll[10], STap.lIIlIIll[11]);
        STap.lIIlIIlI[6] = lIIIIIIIlI(STap.lIIlIIll[12], STap.lIIlIIll[13]);
        STap.lIIlIIlI[7] = lIIIIIIIII(STap.lIIlIIll[14], STap.lIIlIIll[15]);
        STap.lIIlIIlI[8] = lIIIIIIIIl(STap.lIIlIIll[16], STap.lIIlIIll[17]);
        STap.lIIlIIlI[9] = lIIIIIIIlI(STap.lIIlIIll[18], STap.lIIlIIll[19]);
        STap.lIIlIIlI[10] = lIIIIIIIlI(STap.lIIlIIll[20], STap.lIIlIIll[21]);
        STap.lIIlIIlI[11] = lIIIIIIIII(STap.lIIlIIll[22], STap.lIIlIIll[23]);
        STap.lIIlIIll = null;
    }

    private static void lIIIIIIlII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        STap.lIIlIIll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIIIIIII(String s, String s1) {
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

    private static String lIIIIIIIlI(String s, String s1) {
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

    private static String lIIIIIIIIl(String s, String s1) {
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
