package me.explicit.module.blatant;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.Setting;
import me.explicit.utils.CombatUtils;
import me.explicit.utils.Game;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSword;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import org.lwjgl.input.Mouse;

public class TriggerBot extends Module {

    public double minLeft;
    public double maxLeft;
    public double jitterLeft;
    public boolean sword;
    public boolean axe;
    private long time1Left;
    private long timeLeft;
    private long time2Left;
    private long time3Left;
    private double time4Left;
    private boolean shouldLeft;
    private static Field buttonstate;
    private static Field button;
    private static Field buttons;
    private Random rando = new Random();
    private static final String[] lIlIllIl;
    private static String[] lIlIllll;

    public TriggerBot() {
        super(TriggerBot.lIlIllIl[0], TriggerBot.lIlIllIl[1], Category.BLATANT);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(TriggerBot.lIlIllIl[2], this, 8.0D, 1.0D, 20.0D, false));
        Explicit.instance.sm.rSetting(new Setting(TriggerBot.lIlIllIl[3], this, 12.0D, 1.0D, 20.0D, false));
        Explicit.instance.sm.rSetting(new Setting(TriggerBot.lIlIllIl[4], this, 0.0D, 0.0D, 4.0D, false));
        Explicit.instance.sm.rSetting(new Setting(TriggerBot.lIlIllIl[5], this, false));
        Explicit.instance.sm.rSetting(new Setting(TriggerBot.lIlIllIl[6], this, false));
    }

    public void onUpdateNoToggle() {
        this.minLeft = Explicit.instance.sm.getSettingByName(this, TriggerBot.lIlIllIl[7]).getValDouble();
        this.maxLeft = Explicit.instance.sm.getSettingByName(this, TriggerBot.lIlIllIl[8]).getValDouble();
        this.jitterLeft = Explicit.instance.sm.getSettingByName(this, TriggerBot.lIlIllIl[9]).getValDouble();
        this.sword = Explicit.instance.sm.getSettingByName(this, TriggerBot.lIlIllIl[10]).getValBoolean();
        this.axe = Explicit.instance.sm.getSettingByName(this, TriggerBot.lIlIllIl[11]).getValBoolean();
    }

    @SubscribeEvent
    public void tick(RenderTickEvent rendertickevent) {
        if (Game.World() != null && Game.Player() != null) {
            boolean flag = false;

            if (Game.Player().getCurrentEquippedItem() != null && (Game.Player().getCurrentEquippedItem().getItem() instanceof ItemBow || Game.Player().getCurrentEquippedItem().getItem() instanceof ItemFood || Game.Player().getCurrentEquippedItem().getItem() instanceof ItemPotion)) {
                flag = true;
            }

            if (!flag && TriggerBot.mc.currentScreen == null && TriggerBot.mc.objectMouseOver != null && TriggerBot.mc.objectMouseOver.entityHit != null && CombatUtils.canTarget(TriggerBot.mc.objectMouseOver.entityHit, true)) {
                Mouse.poll();
                this.clickLeft();
            } else {
                this.time1Left = 0L;
                this.timeLeft = 0L;
                KeyBinding.setKeyBindState(TriggerBot.mc.gameSettings.keyBindAttack.getKeyCode(), Mouse.isButtonDown(0));
            }
        } else {
            this.time1Left = 0L;
            this.timeLeft = 0L;
        }
    }

    public void clickLeft() {
        if (TriggerBot.mc.inGameHasFocus) {
            if (this.sword || this.axe) {
                if (Game.Player().getCurrentEquippedItem() == null) {
                    return;
                }

                if (this.sword && !this.axe && !(Game.Player().getCurrentEquippedItem().getItem() instanceof ItemSword)) {
                    return;
                }

                if (!this.sword && this.axe && !(Game.Player().getCurrentEquippedItem().getItem() instanceof ItemAxe)) {
                    return;
                }

                if (this.sword && this.axe && !(Game.Player().getCurrentEquippedItem().getItem() instanceof ItemAxe) && !(Game.Player().getCurrentEquippedItem().getItem() instanceof ItemSword)) {
                    return;
                }
            }

            if (this.jitterLeft > 0.0D) {
                double d0 = this.jitterLeft * 0.5D;
                EntityPlayerSP entityplayersp;

                if (this.rando.nextBoolean()) {
                    entityplayersp = Game.Player();
                    entityplayersp.rotationYaw += (float) ((double) this.rando.nextFloat() * d0);
                } else {
                    entityplayersp = Game.Player();
                    entityplayersp.rotationYaw -= (float) ((double) this.rando.nextFloat() * d0);
                }

                if (this.rando.nextBoolean()) {
                    entityplayersp = Game.Player();
                    entityplayersp.rotationPitch += (float) ((double) this.rando.nextFloat() * d0 * 0.45D);
                } else {
                    entityplayersp = Game.Player();
                    entityplayersp.rotationPitch -= (float) ((double) this.rando.nextFloat() * d0 * 0.45D);
                }
            }

            if (this.timeLeft > 0L && this.time1Left > 0L) {
                int i;

                if (System.currentTimeMillis() > this.timeLeft) {
                    i = TriggerBot.mc.gameSettings.keyBindAttack.getKeyCode();
                    KeyBinding.setKeyBindState(i, true);
                    KeyBinding.onTick(i);
                    pushEvent(0, true);
                    this.getELeft();
                } else if (System.currentTimeMillis() > this.time1Left) {
                    i = TriggerBot.mc.gameSettings.keyBindAttack.getKeyCode();
                    KeyBinding.setKeyBindState(i, false);
                    pushEvent(0, false);
                }
            } else {
                this.getELeft();
            }

        }
    }

    public void getELeft() {
        double d0 = this.minLeft + this.rando.nextDouble() * (this.maxLeft - this.minLeft);
        long i = (long) ((int) Math.round(1000.0D / d0) - (int) (Math.round(1000.0D / d0) / 1000L));

        if (System.currentTimeMillis() > this.time2Left) {
            if (!this.shouldLeft && this.rando.nextInt(100) >= 85) {
                this.shouldLeft = true;
                this.time4Left = 1.1D + this.rando.nextDouble() * 0.15D;
            } else {
                this.shouldLeft = false;
            }

            this.time2Left = System.currentTimeMillis() + 400L + (long) this.rando.nextInt(1500);
        }

        if (this.shouldLeft) {
            i *= (long) this.time4Left;
        }

        if (System.currentTimeMillis() > this.time3Left) {
            if (this.rando.nextInt(100) >= 80) {
                i += 50L + (long) this.rando.nextInt(100);
            }

            this.time3Left = System.currentTimeMillis() + 450L + (long) this.rando.nextInt(100);
        }

        this.timeLeft = System.currentTimeMillis() + i;
        this.time1Left = System.currentTimeMillis() + i / 2L - (long) this.rando.nextInt(8);
    }

    public static void pushEvent(int i, boolean flag) {
        MouseEvent mouseevent = new MouseEvent();

        TriggerBot.button.setAccessible(true);

        try {
            TriggerBot.button.set(mouseevent, Integer.valueOf(i));
        } catch (IllegalAccessException illegalaccessexception) {
            illegalaccessexception.printStackTrace();
        }

        TriggerBot.button.setAccessible(false);
        TriggerBot.buttonstate.setAccessible(true);

        try {
            TriggerBot.buttonstate.set(mouseevent, Boolean.valueOf(flag));
        } catch (IllegalAccessException illegalaccessexception1) {
            illegalaccessexception1.printStackTrace();
        }

        TriggerBot.buttonstate.setAccessible(false);
        MinecraftForge.EVENT_BUS.post(mouseevent);

        try {
            TriggerBot.buttons.setAccessible(true);
            ByteBuffer bytebuffer = (ByteBuffer) TriggerBot.buttons.get((Object) null);

            TriggerBot.buttons.setAccessible(false);
            bytebuffer.put(i, (byte) (flag ? 1 : 0));
        } catch (IllegalAccessException illegalaccessexception2) {
            illegalaccessexception2.printStackTrace();
        }

    }

    static {
        lIIlllIIIl();
        lIIlllIIII();

        try {
            TriggerBot.button = MouseEvent.class.getDeclaredField(TriggerBot.lIlIllIl[12]);
        } catch (NoSuchFieldException nosuchfieldexception) {
            nosuchfieldexception.printStackTrace();
        }

        try {
            TriggerBot.buttonstate = MouseEvent.class.getDeclaredField(TriggerBot.lIlIllIl[13]);
        } catch (NoSuchFieldException nosuchfieldexception1) {
            nosuchfieldexception1.printStackTrace();
        }

        try {
            TriggerBot.buttons = Mouse.class.getDeclaredField(TriggerBot.lIlIllIl[14]);
        } catch (NoSuchFieldException nosuchfieldexception2) {
            nosuchfieldexception2.printStackTrace();
        }

    }

    private static void lIIlllIIII() {
        lIlIllIl = new String[15];
        TriggerBot.lIlIllIl[0] = lIIllIllII(TriggerBot.lIlIllll[0], TriggerBot.lIlIllll[1]);
        TriggerBot.lIlIllIl[1] = lIIllIllIl(TriggerBot.lIlIllll[2], TriggerBot.lIlIllll[3]);
        TriggerBot.lIlIllIl[2] = lIIllIllII(TriggerBot.lIlIllll[4], TriggerBot.lIlIllll[5]);
        TriggerBot.lIlIllIl[3] = lIIllIllII(TriggerBot.lIlIllll[6], TriggerBot.lIlIllll[7]);
        TriggerBot.lIlIllIl[4] = lIIllIllIl(TriggerBot.lIlIllll[8], TriggerBot.lIlIllll[9]);
        TriggerBot.lIlIllIl[5] = lIIllIllII(TriggerBot.lIlIllll[10], TriggerBot.lIlIllll[11]);
        TriggerBot.lIlIllIl[6] = lIIllIllIl(TriggerBot.lIlIllll[12], TriggerBot.lIlIllll[13]);
        TriggerBot.lIlIllIl[7] = lIIllIlllI(TriggerBot.lIlIllll[14], TriggerBot.lIlIllll[15]);
        TriggerBot.lIlIllIl[8] = lIIllIlllI(TriggerBot.lIlIllll[16], TriggerBot.lIlIllll[17]);
        TriggerBot.lIlIllIl[9] = lIIllIllII(TriggerBot.lIlIllll[18], TriggerBot.lIlIllll[19]);
        TriggerBot.lIlIllIl[10] = lIIllIllII(TriggerBot.lIlIllll[20], TriggerBot.lIlIllll[21]);
        TriggerBot.lIlIllIl[11] = lIIllIllII(TriggerBot.lIlIllll[22], TriggerBot.lIlIllll[23]);
        TriggerBot.lIlIllIl[12] = lIIllIlllI(TriggerBot.lIlIllll[24], TriggerBot.lIlIllll[25]);
        TriggerBot.lIlIllIl[13] = lIIllIllIl(TriggerBot.lIlIllll[26], TriggerBot.lIlIllll[27]);
        TriggerBot.lIlIllIl[14] = lIIllIllIl(TriggerBot.lIlIllll[28], TriggerBot.lIlIllll[29]);
        TriggerBot.lIlIllll = null;
    }

    private static void lIIlllIIIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        TriggerBot.lIlIllll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIllIlllI(String s, String s1) {
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

    private static String lIIllIllII(String s, String s1) {
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

    private static String lIIllIllIl(String s, String s1) {
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
