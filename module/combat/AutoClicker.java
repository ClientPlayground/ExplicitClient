package me.explicit.module.combat;

import io.netty.util.internal.ThreadLocalRandom;
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
import me.explicit.utils.Game;
import me.explicit.utils.TimerUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSword;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import org.lwjgl.input.Mouse;

public class AutoClicker extends Module {

    public double minLeft;
    public double maxLeft;
    public double minBlock;
    public double maxBlock;
    public double jitterLeft;
    public double jitterRight;
    public double breakDelay;
    public boolean sword;
    public boolean axe;
    public boolean blocks;
    public boolean rightClick;
    public boolean blockHit;
    public boolean noShift;
    private long time1Left;
    private long timeLeft;
    private long time2Left;
    private long time3Left;
    private double time4Left;
    private boolean shouldLeft;
    private long time1Right;
    private long timeRight;
    private long time2Right;
    private long time3Right;
    private double time4Right;
    private boolean shouldRight;
    private static Field buttonstate;
    private static Field button;
    private static Field buttons;
    private Random rando = new Random();
    private TimerUtils breaktimer = new TimerUtils();
    private TimerUtils unbreaktimer = new TimerUtils();
    private boolean isBreaking = false;
    private boolean wasBreaking = false;
    private static final String[] lllllIlI;
    private static String[] lIIIIIIlI;

    public AutoClicker() {
        super(AutoClicker.lllllIlI[0], 0, Category.COMBAT, AutoClicker.lllllIlI[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(AutoClicker.lllllIlI[2], this, 8.0D, 1.0D, 20.0D, false));
        Explicit.instance.sm.rSetting(new Setting(AutoClicker.lllllIlI[3], this, 12.0D, 1.0D, 20.0D, false));
        Explicit.instance.sm.rSetting(new Setting(AutoClicker.lllllIlI[4], this, 3.0D, 1.0D, 10.0D, false));
        Explicit.instance.sm.rSetting(new Setting(AutoClicker.lllllIlI[5], this, 5.0D, 1.0D, 10.0D, false));
        Explicit.instance.sm.rSetting(new Setting(AutoClicker.lllllIlI[6], this, 0.0D, 0.0D, 4.0D, false));
        Explicit.instance.sm.rSetting(new Setting(AutoClicker.lllllIlI[7], this, 0.0D, 0.0D, 4.0D, false));
        Explicit.instance.sm.rSetting(new Setting(AutoClicker.lllllIlI[8], this, 25.0D, 0.0D, 500.0D, true));
        Explicit.instance.sm.rSetting(new Setting(AutoClicker.lllllIlI[9], this, false));
        Explicit.instance.sm.rSetting(new Setting(AutoClicker.lllllIlI[10], this, false));
        Explicit.instance.sm.rSetting(new Setting(AutoClicker.lllllIlI[11], this, true));
        Explicit.instance.sm.rSetting(new Setting(AutoClicker.lllllIlI[12], this, false));
        Explicit.instance.sm.rSetting(new Setting(AutoClicker.lllllIlI[13], this, false));
        Explicit.instance.sm.rSetting(new Setting(AutoClicker.lllllIlI[14], this, false));
    }

    public void onEnable() {
        super.onEnable();
    }

    public void onTick() {}

    public void onUpdateNoToggle() {
        this.minLeft = Explicit.instance.sm.getSettingByName(this, AutoClicker.lllllIlI[15]).getValDouble();
        this.maxLeft = Explicit.instance.sm.getSettingByName(this, AutoClicker.lllllIlI[16]).getValDouble();
        this.jitterLeft = Explicit.instance.sm.getSettingByName(this, AutoClicker.lllllIlI[17]).getValDouble();
        this.jitterRight = Explicit.instance.sm.getSettingByName(this, AutoClicker.lllllIlI[18]).getValDouble();
        this.sword = Explicit.instance.sm.getSettingByName(this, AutoClicker.lllllIlI[19]).getValBoolean();
        this.axe = Explicit.instance.sm.getSettingByName(this, AutoClicker.lllllIlI[20]).getValBoolean();
        this.blocks = Explicit.instance.sm.getSettingByName(this, AutoClicker.lllllIlI[21]).getValBoolean();
        this.rightClick = Explicit.instance.sm.getSettingByName(this, AutoClicker.lllllIlI[22]).getValBoolean();
        this.blockHit = Explicit.instance.sm.getSettingByName(this, AutoClicker.lllllIlI[23]).getValBoolean();
        this.noShift = Explicit.instance.sm.getSettingByName(this, AutoClicker.lllllIlI[24]).getValBoolean();
        this.minBlock = Explicit.instance.sm.getSettingByName(this, AutoClicker.lllllIlI[25]).getValDouble();
        this.maxBlock = Explicit.instance.sm.getSettingByName(this, AutoClicker.lllllIlI[26]).getValDouble();
        this.breakDelay = (double) ((int) Explicit.instance.sm.getSettingByName(this, AutoClicker.lllllIlI[27]).getValDouble());
        Explicit.instance.sm.getSettingByName(this, AutoClicker.lllllIlI[28]).setVisible(this.blocks);
        Explicit.instance.sm.getSettingByName(this, AutoClicker.lllllIlI[29]).setVisible(this.rightClick);
        Explicit.instance.sm.getSettingByName(this, AutoClicker.lllllIlI[30]).setVisible(this.rightClick);
        Explicit.instance.sm.getSettingByName(this, AutoClicker.lllllIlI[31]).setVisible(this.blockHit && this.rightClick);
        Explicit.instance.sm.getSettingByName(this, AutoClicker.lllllIlI[32]).setVisible(this.blockHit && this.rightClick);
    }

    @SubscribeEvent
    public void tick(RenderTickEvent rendertickevent) {
        boolean flag = false;

        if (Game.World() != null && Game.Player() != null) {
            if (Game.Player().getCurrentEquippedItem() != null && (Game.Player().getCurrentEquippedItem().getItem() instanceof ItemBow || Game.Player().getCurrentEquippedItem().getItem() instanceof ItemFood || Game.Player().getCurrentEquippedItem().getItem() instanceof ItemPotion)) {
                flag = true;
            }

            if (AutoClicker.mc.currentScreen == null && (!AutoClicker.mc.gameSettings.keyBindSneak.isKeyDown() || !this.noShift)) {
                Mouse.poll();
                if (Mouse.isButtonDown(0)) {
                    this.clickLeft();
                } else {
                    this.time1Left = 0L;
                    this.timeLeft = 0L;
                }

                Mouse.poll();
                if (Mouse.isButtonDown(1) && this.rightClick && !flag && (this.blockHit || !Mouse.isButtonDown(0))) {
                    this.clickRight();
                } else {
                    this.time1Right = 0L;
                    this.timeRight = 0L;
                }

                this.wasBreaking = this.isBreaking;
            }
        }
    }

    public void clickLeft() {
        if (AutoClicker.mc.inGameHasFocus) {
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

            this.isBreaking = false;
            if (this.blocks && AutoClicker.mc.objectMouseOver != null) {
                BlockPos blockpos = AutoClicker.mc.objectMouseOver.getBlockPos();

                if (blockpos != null && Game.World().getBlockState(blockpos).getBlock() != Blocks.air) {
                    if (this.breaktimer.hasReached(this.breakDelay + ThreadLocalRandom.current().nextDouble(-5.0D, 5.0D))) {
                        int i = AutoClicker.mc.gameSettings.keyBindAttack.getKeyCode();

                        KeyBinding.setKeyBindState(i, true);
                        KeyBinding.onTick(i);
                    }

                    this.isBreaking = true;
                    this.wasBreaking = true;
                    return;
                }
            }

            this.breaktimer.reset();
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
                int j;

                if (System.currentTimeMillis() > this.timeLeft) {
                    j = AutoClicker.mc.gameSettings.keyBindAttack.getKeyCode();
                    KeyBinding.setKeyBindState(j, true);
                    KeyBinding.onTick(j);
                    pushEvent(0, true);
                    this.getELeft();
                } else if (System.currentTimeMillis() > this.time1Left) {
                    j = AutoClicker.mc.gameSettings.keyBindAttack.getKeyCode();
                    KeyBinding.setKeyBindState(j, false);
                    pushEvent(0, false);
                }
            } else {
                this.getELeft();
            }

        }
    }

    public void clickRight() {
        if (AutoClicker.mc.inGameHasFocus) {
            if (this.jitterRight > 0.0D) {
                double d0 = this.jitterRight * 0.5D;
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

            if (this.timeRight > 0L && this.time1Right > 0L) {
                int i;

                if (System.currentTimeMillis() > this.timeRight && !AutoClicker.mc.gameSettings.keyBindAttack.isKeyDown()) {
                    i = AutoClicker.mc.gameSettings.keyBindUseItem.getKeyCode();
                    KeyBinding.setKeyBindState(i, true);
                    KeyBinding.onTick(i);
                    pushEvent(1, true);
                    this.getERight();
                } else if (System.currentTimeMillis() > this.time1Right || AutoClicker.mc.gameSettings.keyBindAttack.isKeyDown()) {
                    i = AutoClicker.mc.gameSettings.keyBindUseItem.getKeyCode();
                    KeyBinding.setKeyBindState(i, false);
                    pushEvent(1, false);
                }
            } else {
                this.getERight();
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

    public void getERight() {
        double d0 = this.blockHit ? this.minBlock : this.minLeft;
        double d1 = this.blockHit ? this.maxBlock : this.maxLeft;
        double d2 = d0 + this.rando.nextDouble() * (d1 - d0);
        long i = (long) ((int) Math.round(1000.0D / d2) - (int) (Math.round(1000.0D / d2) / 1000L));

        if (System.currentTimeMillis() > this.time2Right) {
            if (!this.shouldRight && this.rando.nextInt(100) >= 85) {
                this.shouldRight = true;
                this.time4Right = 1.1D + this.rando.nextDouble() * 0.15D;
            } else {
                this.shouldRight = false;
            }

            this.time2Right = System.currentTimeMillis() + 400L + (long) this.rando.nextInt(1500);
        }

        if (this.shouldRight) {
            i *= (long) this.time4Right;
        }

        if (System.currentTimeMillis() > this.time3Right) {
            if (this.rando.nextInt(100) >= 80) {
                i += 50L + (long) this.rando.nextInt(100);
            }

            this.time3Right = System.currentTimeMillis() + 450L + (long) this.rando.nextInt(100);
        }

        this.timeRight = System.currentTimeMillis() + i;
        this.time1Right = System.currentTimeMillis() + i / 2L - (long) this.rando.nextInt(8);
    }

    public static void pushEvent(int i, boolean flag) {
        MouseEvent mouseevent = new MouseEvent();

        AutoClicker.button.setAccessible(true);

        try {
            AutoClicker.button.set(mouseevent, Integer.valueOf(i));
        } catch (IllegalAccessException illegalaccessexception) {
            illegalaccessexception.printStackTrace();
        }

        AutoClicker.button.setAccessible(false);
        AutoClicker.buttonstate.setAccessible(true);

        try {
            AutoClicker.buttonstate.set(mouseevent, Boolean.valueOf(flag));
        } catch (IllegalAccessException illegalaccessexception1) {
            illegalaccessexception1.printStackTrace();
        }

        AutoClicker.buttonstate.setAccessible(false);
        MinecraftForge.EVENT_BUS.post(mouseevent);

        try {
            AutoClicker.buttons.setAccessible(true);
            ByteBuffer bytebuffer = (ByteBuffer) AutoClicker.buttons.get((Object) null);

            AutoClicker.buttons.setAccessible(false);
            bytebuffer.put(i, (byte) (flag ? 1 : 0));
        } catch (IllegalAccessException illegalaccessexception2) {
            illegalaccessexception2.printStackTrace();
        }

    }

    static {
        lllIIlIIlI();
        lllIIlIIIl();

        try {
            AutoClicker.button = MouseEvent.class.getDeclaredField(AutoClicker.lllllIlI[33]);
        } catch (NoSuchFieldException nosuchfieldexception) {
            nosuchfieldexception.printStackTrace();
        }

        try {
            AutoClicker.buttonstate = MouseEvent.class.getDeclaredField(AutoClicker.lllllIlI[34]);
        } catch (NoSuchFieldException nosuchfieldexception1) {
            nosuchfieldexception1.printStackTrace();
        }

        try {
            AutoClicker.buttons = Mouse.class.getDeclaredField(AutoClicker.lllllIlI[35]);
        } catch (NoSuchFieldException nosuchfieldexception2) {
            nosuchfieldexception2.printStackTrace();
        }

    }

    private static void lllIIlIIIl() {
        lllllIlI = new String[36];
        AutoClicker.lllllIlI[0] = llIlllllIl(AutoClicker.lIIIIIIlI[0], AutoClicker.lIIIIIIlI[1]);
        AutoClicker.lllllIlI[1] = llIlllllll(AutoClicker.lIIIIIIlI[2], AutoClicker.lIIIIIIlI[3]);
        AutoClicker.lllllIlI[2] = llIlllllIl(AutoClicker.lIIIIIIlI[4], AutoClicker.lIIIIIIlI[5]);
        AutoClicker.lllllIlI[3] = lllIIIIIII(AutoClicker.lIIIIIIlI[6], AutoClicker.lIIIIIIlI[7]);
        AutoClicker.lllllIlI[4] = lllIIIIIII(AutoClicker.lIIIIIIlI[8], AutoClicker.lIIIIIIlI[9]);
        AutoClicker.lllllIlI[5] = lllIIIIIII(AutoClicker.lIIIIIIlI[10], AutoClicker.lIIIIIIlI[11]);
        AutoClicker.lllllIlI[6] = llIlllllIl(AutoClicker.lIIIIIIlI[12], AutoClicker.lIIIIIIlI[13]);
        AutoClicker.lllllIlI[7] = llIlllllIl(AutoClicker.lIIIIIIlI[14], AutoClicker.lIIIIIIlI[15]);
        AutoClicker.lllllIlI[8] = llIlllllIl(AutoClicker.lIIIIIIlI[16], AutoClicker.lIIIIIIlI[17]);
        AutoClicker.lllllIlI[9] = lllIIIIIII(AutoClicker.lIIIIIIlI[18], AutoClicker.lIIIIIIlI[19]);
        AutoClicker.lllllIlI[10] = lllIIIIIII(AutoClicker.lIIIIIIlI[20], AutoClicker.lIIIIIIlI[21]);
        AutoClicker.lllllIlI[11] = lllIIIIIII(AutoClicker.lIIIIIIlI[22], AutoClicker.lIIIIIIlI[23]);
        AutoClicker.lllllIlI[12] = llIlllllIl(AutoClicker.lIIIIIIlI[24], AutoClicker.lIIIIIIlI[25]);
        AutoClicker.lllllIlI[13] = lllIIIIIII(AutoClicker.lIIIIIIlI[26], AutoClicker.lIIIIIIlI[27]);
        AutoClicker.lllllIlI[14] = lllIIIIIII(AutoClicker.lIIIIIIlI[28], AutoClicker.lIIIIIIlI[29]);
        AutoClicker.lllllIlI[15] = llIlllllll(AutoClicker.lIIIIIIlI[30], AutoClicker.lIIIIIIlI[31]);
        AutoClicker.lllllIlI[16] = llIlllllIl(AutoClicker.lIIIIIIlI[32], AutoClicker.lIIIIIIlI[33]);
        AutoClicker.lllllIlI[17] = llIlllllll(AutoClicker.lIIIIIIlI[34], AutoClicker.lIIIIIIlI[35]);
        AutoClicker.lllllIlI[18] = lllIIIIIII(AutoClicker.lIIIIIIlI[36], AutoClicker.lIIIIIIlI[37]);
        AutoClicker.lllllIlI[19] = lllIIIIIII("hQlRQHe2RE5/6DYHBUJWYQ==", "GddRK");
        AutoClicker.lllllIlI[20] = llIlllllll("EhAqAjo/EQ==", "ShOMT");
        AutoClicker.lllllIlI[21] = llIlllllll("CTQDLD0JKgkuPTg=", "KFfMV");
        AutoClicker.lllllIlI[22] = llIlllllll("EBAXAzsBFRkIJA==", "BypkO");
        AutoClicker.lllllIlI[23] = llIlllllll("DB4BKy0GGxo=", "NrnHF");
        AutoClicker.lllllIlI[24] = llIlllllIl("0Ff7aulEK3w=", "HFzcE");
        AutoClicker.lllllIlI[25] = llIlllllIl("wimwy/rQQzc=", "vzsst");
        AutoClicker.lllllIlI[26] = lllIIIIIII("n+IYivp8jbw=", "AVGUi");
        AutoClicker.lllllIlI[27] = llIlllllll("DiMMIjEINAUiIw==", "LQiCZ");
        AutoClicker.lllllIlI[28] = llIlllllIl("7ik5Bytyb9tcGIOVc9OlBA==", "gqGwY");
        AutoClicker.lllllIlI[29] = llIlllllIl("Oqf+0aU7mWWG69hcI5YIdQ==", "GcpvA");
        AutoClicker.lllllIlI[30] = llIlllllll("PzsFIBAnOxY8AR8=", "mRbHd");
        AutoClicker.lllllIlI[31] = llIlllllll("DwUKKwIR", "BldiR");
        AutoClicker.lllllIlI[32] = llIlllllll("PigXFBwg", "sIoVL");
        AutoClicker.lllllIlI[33] = llIlllllIl("Jx1i7Yd1BNc=", "KPkkV");
        AutoClicker.lllllIlI[34] = lllIIIIIII("mvFNUxLO+GKAHAWnXP/eLg==", "vRqWs");
        AutoClicker.lllllIlI[35] = llIlllllll("FBY/HQgYEA==", "vcKig");
        AutoClicker.lIIIIIIlI = null;
    }

    private static void lllIIlIIlI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        AutoClicker.lIIIIIIlI = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lllIIIIIII(String s, String s1) {
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

    private static String llIlllllll(String s, String s1) {
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

    private static String llIlllllIl(String s, String s1) {
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
}
