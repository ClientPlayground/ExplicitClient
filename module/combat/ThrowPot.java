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
import me.explicit.utils.TimerUtils;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;

public class ThrowPot extends Module {

    private int stage = 0;
    private TimerUtils timer = new TimerUtils();
    private int oldSlot;
    private int potSlot;
    private static final String[] lIlIlllI;
    private static String[] lIllIIII;

    public ThrowPot() {
        super(ThrowPot.lIlIlllI[0], ThrowPot.lIlIlllI[1], Category.COMBAT);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(ThrowPot.lIlIlllI[2], this, 100.0D, 10.0D, 1000.0D, true));
        Explicit.instance.sm.rSetting(new Setting(ThrowPot.lIlIlllI[3], this, 150.0D, 10.0D, 1000.0D, true));
        Explicit.instance.sm.rSetting(new Setting(ThrowPot.lIlIlllI[4], this, 8.0D, 1.0D, 20.0D, true));
    }

    public void onEnable() {
        super.onEnable();
        this.stage = 0;
        this.timer.reset();
        if (Game.Player() == null) {
            this.setToggled(false);
        } else {
            this.oldSlot = ThrowPot.mc.thePlayer.inventory.currentItem;
            this.potSlot = this.getPotionSlot();
            if (this.potSlot == -1) {
                this.setToggled(false);
            }
        }
    }

    public void onTick() {
        if (this.stage == 0) {
            this.stage = 1;
        } else if (this.stage == 1) {
            float f = (float) Explicit.instance.sm.getSettingByName(this, ThrowPot.lIlIlllI[5]).getValDouble();

            if (ThrowPot.mc.thePlayer.getHealth() <= ThrowPot.mc.thePlayer.getMaxHealth() - f) {
                ThrowPot.mc.thePlayer.inventory.currentItem = this.potSlot;
                this.stage = 2;
                this.timer.reset();
            } else {
                ThrowPot.mc.thePlayer.inventory.currentItem = this.oldSlot;
                this.setToggled(false);
            }
        } else if (this.stage == 2) {
            if (this.timer.hasReached(this.getDelay() / 3.0D)) {
                KeyBinding.setKeyBindState(ThrowPot.mc.gameSettings.keyBindUseItem.getKeyCode(), true);
                this.timer.reset();
                this.stage = 3;
            }
        } else if (this.stage == 3) {
            if (this.timer.hasReached(this.getDelay() / 3.0D)) {
                KeyBinding.setKeyBindState(ThrowPot.mc.gameSettings.keyBindUseItem.getKeyCode(), false);
                this.stage = 4;
            }
        } else if (this.stage == 4) {
            ThrowPot.mc.thePlayer.inventory.currentItem = this.oldSlot;
            this.setToggled(false);
            this.stage = 0;
        }

    }

    public int getPotionSlot() {
        for (int i = 0; i < 8; ++i) {
            ItemStack itemstack = ThrowPot.mc.thePlayer.inventory.getStackInSlot(i);

            if (itemstack != null && !itemstack.isStackable()) {
                Item item = itemstack.getItem();

                if (item instanceof ItemPotion) {
                    ItemPotion itempotion = (ItemPotion) item;

                    if (ItemPotion.isSplash(itemstack.getMetadata())) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

    public double getDelay() {
        int i = Explicit.instance.sm.getSettingByName(this, ThrowPot.lIlIlllI[6]).getValInt() * 4;
        int j = Explicit.instance.sm.getSettingByName(this, ThrowPot.lIlIlllI[7]).getValInt() * 4;

        if (i == j) {
            j = i + 1;
        }

        return ThreadLocalRandom.current().nextDouble((double) Math.min(j, i), (double) Math.max(j, i));
    }

    static {
        lIIlllIlIl();
        lIIlllIlII();
    }

    private static void lIIlllIlII() {
        lIlIlllI = new String[8];
        ThrowPot.lIlIlllI[0] = lIIllIllll(ThrowPot.lIllIIII[0], ThrowPot.lIllIIII[1]);
        ThrowPot.lIlIlllI[1] = lIIllIllll(ThrowPot.lIllIIII[2], ThrowPot.lIllIIII[3]);
        ThrowPot.lIlIlllI[2] = lIIlllIIlI(ThrowPot.lIllIIII[4], ThrowPot.lIllIIII[5]);
        ThrowPot.lIlIlllI[3] = lIIllIllll(ThrowPot.lIllIIII[6], ThrowPot.lIllIIII[7]);
        ThrowPot.lIlIlllI[4] = lIIlllIIll(ThrowPot.lIllIIII[8], ThrowPot.lIllIIII[9]);
        ThrowPot.lIlIlllI[5] = lIIlllIIll(ThrowPot.lIllIIII[10], ThrowPot.lIllIIII[11]);
        ThrowPot.lIlIlllI[6] = lIIlllIIlI(ThrowPot.lIllIIII[12], ThrowPot.lIllIIII[13]);
        ThrowPot.lIlIlllI[7] = lIIllIllll(ThrowPot.lIllIIII[14], ThrowPot.lIllIIII[15]);
        ThrowPot.lIllIIII = null;
    }

    private static void lIIlllIlIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ThrowPot.lIllIIII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIlllIIlI(String s, String s1) {
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

    private static String lIIllIllll(String s, String s1) {
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

    private static String lIIlllIIll(String s, String s1) {
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
