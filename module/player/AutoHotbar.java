package me.explicit.module.player;

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
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AutoHotbar extends Module {

    private TimerUtils timer = new TimerUtils();
    private double speed;
    private static final String[] lIIlIlIl;
    private static String[] lIIllIII;

    public AutoHotbar() {
        super(AutoHotbar.lIIlIlIl[0], 0, Category.PLAYER, AutoHotbar.lIIlIlIl[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(AutoHotbar.lIIlIlIl[2], this, false));
        Explicit.instance.sm.rSetting(new Setting(AutoHotbar.lIIlIlIl[3], this, true));
        Explicit.instance.sm.rSetting(new Setting(AutoHotbar.lIIlIlIl[4], this, false));
        Explicit.instance.sm.rSetting(new Setting(AutoHotbar.lIIlIlIl[5], this, true));
        Explicit.instance.sm.rSetting(new Setting(AutoHotbar.lIIlIlIl[6], this, 0.2D, 0.0D, 1.0D, false));
        Explicit.instance.sm.rSetting(new Setting(AutoHotbar.lIIlIlIl[7], this, 0.4D, 0.0D, 1.001D, false));
    }

    public void onEnable() {
        this.updateSpeed();
    }

    private void updateSpeed() {
        double d0 = Explicit.instance.sm.getSettingByName(this, AutoHotbar.lIIlIlIl[8]).getValDouble();
        double d1 = Explicit.instance.sm.getSettingByName(this, AutoHotbar.lIIlIlIl[9]).getValDouble();

        if (d0 == d1) {
            d1 = d0 + 1.0D;
        }

        this.speed = ThreadLocalRandom.current().nextDouble(Math.min(d0, d1), Math.max(d0, d1));
    }

    public void onUpdate() {
        if (AutoHotbar.mc.currentScreen instanceof GuiInventory && this.timer.hasReached(this.speed * 1000.0D)) {
            if (!this.isHotbarFull() && this.getItem() != -1) {
                this.shiftClick(this.getItem());
                this.updateSpeed();
            }

            this.timer.reset();
            this.updateSpeed();
        }

    }

    private int getItem() {
        for (int i = 9; i < 36; ++i) {
            if (this.isValidItem(Game.Player().inventory.mainInventory[i])) {
                return i;
            }
        }

        return -1;
    }

    private boolean isHotbarFull() {
        for (int i = 0; i < 9; ++i) {
            if (Game.Player().inventory.mainInventory[i] == null) {
                return false;
            }
        }

        return true;
    }

    private void shiftClick(int i) {
        Module.mc.playerController.windowClick(AutoHotbar.mc.thePlayer.inventoryContainer.windowId, i, 0, 1, Module.mc.thePlayer);
    }

    private boolean isValidItem(ItemStack itemstack) {
        if (itemstack == null) {
            return false;
        } else {
            boolean flag = Explicit.instance.sm.getSettingByName(this, AutoHotbar.lIIlIlIl[10]).getValBoolean();
            boolean flag1 = Explicit.instance.sm.getSettingByName(this, AutoHotbar.lIIlIlIl[11]).getValBoolean();
            boolean flag2 = Explicit.instance.sm.getSettingByName(this, AutoHotbar.lIIlIlIl[12]).getValBoolean();
            boolean flag3 = Explicit.instance.sm.getSettingByName(this, AutoHotbar.lIIlIlIl[13]).getValBoolean();

            return Item.getIdFromItem(itemstack.getItem()) == 373 && flag && itemstack.getItem().getItemStackDisplayName(itemstack).toLowerCase().contains(AutoHotbar.lIIlIlIl[14]) ? true : (Item.getIdFromItem(itemstack.getItem()) == 373 && flag1 && !itemstack.getItem().getItemStackDisplayName(itemstack).toLowerCase().contains(AutoHotbar.lIIlIlIl[15]) ? true : ((Item.getIdFromItem(itemstack.getItem()) == 332 || Item.getIdFromItem(itemstack.getItem()) == 344) && flag2 ? true : Item.getIdFromItem(itemstack.getItem()) == 436 && flag3));
        }
    }

    static {
        lIIIIlIlII();
        lIIIIlIIll();
    }

    private static void lIIIIlIIll() {
        lIIlIlIl = new String[16];
        AutoHotbar.lIIlIlIl[0] = lIIIIIlllI(AutoHotbar.lIIllIII[0], AutoHotbar.lIIllIII[1]);
        AutoHotbar.lIIlIlIl[1] = lIIIIIllll(AutoHotbar.lIIllIII[2], AutoHotbar.lIIllIII[3]);
        AutoHotbar.lIIlIlIl[2] = lIIIIlIIII(AutoHotbar.lIIllIII[4], AutoHotbar.lIIllIII[5]);
        AutoHotbar.lIIlIlIl[3] = lIIIIIlllI(AutoHotbar.lIIllIII[6], AutoHotbar.lIIllIII[7]);
        AutoHotbar.lIIlIlIl[4] = lIIIIIllll(AutoHotbar.lIIllIII[8], AutoHotbar.lIIllIII[9]);
        AutoHotbar.lIIlIlIl[5] = lIIIIIllll(AutoHotbar.lIIllIII[10], AutoHotbar.lIIllIII[11]);
        AutoHotbar.lIIlIlIl[6] = lIIIIlIIII(AutoHotbar.lIIllIII[12], AutoHotbar.lIIllIII[13]);
        AutoHotbar.lIIlIlIl[7] = lIIIIIlllI(AutoHotbar.lIIllIII[14], AutoHotbar.lIIllIII[15]);
        AutoHotbar.lIIlIlIl[8] = lIIIIIlllI(AutoHotbar.lIIllIII[16], AutoHotbar.lIIllIII[17]);
        AutoHotbar.lIIlIlIl[9] = lIIIIIlllI(AutoHotbar.lIIllIII[18], AutoHotbar.lIIllIII[19]);
        AutoHotbar.lIIlIlIl[10] = lIIIIIllll(AutoHotbar.lIIllIII[20], AutoHotbar.lIIllIII[21]);
        AutoHotbar.lIIlIlIl[11] = lIIIIlIIII(AutoHotbar.lIIllIII[22], AutoHotbar.lIIllIII[23]);
        AutoHotbar.lIIlIlIl[12] = lIIIIIllll(AutoHotbar.lIIllIII[24], AutoHotbar.lIIllIII[25]);
        AutoHotbar.lIIlIlIl[13] = lIIIIlIIII(AutoHotbar.lIIllIII[26], AutoHotbar.lIIllIII[27]);
        AutoHotbar.lIIlIlIl[14] = lIIIIlIIII(AutoHotbar.lIIllIII[28], AutoHotbar.lIIllIII[29]);
        AutoHotbar.lIIlIlIl[15] = lIIIIlIIII(AutoHotbar.lIIllIII[30], AutoHotbar.lIIllIII[31]);
        AutoHotbar.lIIllIII = null;
    }

    private static void lIIIIlIlII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        AutoHotbar.lIIllIII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIIIllll(String s, String s1) {
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

    private static String lIIIIIlllI(String s, String s1) {
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

    private static String lIIIIlIIII(String s, String s1) {
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
