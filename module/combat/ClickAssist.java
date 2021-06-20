package me.explicit.module.combat;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.Setting;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import org.lwjgl.input.Mouse;

public class ClickAssist extends Module {

    public double minLeft;
    public double maxLeft;
    private long lastClick;
    private long hold;
    private double speed;
    private double holdLength;
    private int averageCPS = 0;
    private ArrayList cps = new ArrayList();
    private boolean wasClick = false;
    private static final String[] lllIIIII;
    private static String[] lllIIIll;

    public ClickAssist() {
        super(ClickAssist.lllIIIII[0], ClickAssist.lllIIIII[1], Category.COMBAT);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(ClickAssist.lllIIIII[2], this, 8.0D, 4.0D, 20.0D, false));
        Explicit.instance.sm.rSetting(new Setting(ClickAssist.lllIIIII[3], this, 12.0D, 5.0D, 20.0D, false));
    }

    @SubscribeEvent
    public void tick(RenderTickEvent rendertickevent) {
        if (ClickAssist.mc.thePlayer != null) {
            Mouse.poll();
            this.updateVals();
            if (this.cps.size() > Math.max(4, this.averageCPS) && ThreadLocalRandom.current().nextDouble(this.minLeft - 0.2D, this.maxLeft) > (double) this.cps.size()) {
                if ((double) (System.currentTimeMillis() - this.lastClick) > this.speed * 1000.0D) {
                    this.lastClick = System.currentTimeMillis();
                    if (this.hold < this.lastClick) {
                        this.hold = this.lastClick;
                    }

                    int i = ClickAssist.mc.gameSettings.keyBindAttack.getKeyCode();

                    KeyBinding.setKeyBindState(i, true);
                    KeyBinding.onTick(i);
                    this.updateVals();
                } else if ((double) (System.currentTimeMillis() - this.hold) > this.holdLength * 1000.0D) {
                    KeyBinding.setKeyBindState(ClickAssist.mc.gameSettings.keyBindAttack.getKeyCode(), false);
                    this.updateVals();
                }
            }

        }
    }

    public void onEnable() {
        super.onEnable();
        this.wasClick = false;
        this.averageCPS = 0;
    }

    private void updateVals() {
        this.minLeft = Explicit.instance.sm.getSettingByName(this, ClickAssist.lllIIIII[4]).getValDouble();
        this.maxLeft = Explicit.instance.sm.getSettingByName(this, ClickAssist.lllIIIII[5]).getValDouble();

        for (int i = 0; i < this.cps.size(); ++i) {
            if (System.currentTimeMillis() - ((Long) this.cps.get(i)).longValue() > 1000L) {
                this.cps.remove(i);
            }
        }

        if (!this.wasClick && Mouse.isButtonDown(0)) {
            this.cps.add(Long.valueOf(System.currentTimeMillis()));
            this.averageCPS = (int) ((double) this.cps.size() / 1.3D);
            this.wasClick = true;
        } else if (!Mouse.isButtonDown(0)) {
            this.wasClick = false;
        }

        if (this.minLeft >= this.maxLeft) {
            this.maxLeft = this.minLeft + 1.0D;
        }

        this.speed = 1.0D / ThreadLocalRandom.current().nextDouble(this.minLeft - 0.2D, this.maxLeft);
        this.holdLength = this.speed / ThreadLocalRandom.current().nextDouble(this.minLeft, this.maxLeft);
    }

    static {
        llIlIIIlIl();
        llIlIIIIlI();
    }

    private static void llIlIIIIlI() {
        lllIIIII = new String[6];
        ClickAssist.lllIIIII[0] = llIIlllIll(ClickAssist.lllIIIll[0], ClickAssist.lllIIIll[1]);
        ClickAssist.lllIIIII[1] = llIIllllII(ClickAssist.lllIIIll[2], ClickAssist.lllIIIll[3]);
        ClickAssist.lllIIIII[2] = llIIlllIll(ClickAssist.lllIIIll[4], ClickAssist.lllIIIll[5]);
        ClickAssist.lllIIIII[3] = llIIllllIl(ClickAssist.lllIIIll[6], ClickAssist.lllIIIll[7]);
        ClickAssist.lllIIIII[4] = llIIllllIl(ClickAssist.lllIIIll[8], ClickAssist.lllIIIll[9]);
        ClickAssist.lllIIIII[5] = llIIllllII(ClickAssist.lllIIIll[10], ClickAssist.lllIIIll[11]);
        ClickAssist.lllIIIll = null;
    }

    private static void llIlIIIlIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ClickAssist.lllIIIll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIIllllIl(String s, String s1) {
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

    private static String llIIllllII(String s, String s1) {
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

    private static String llIIlllIll(String s, String s1) {
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
