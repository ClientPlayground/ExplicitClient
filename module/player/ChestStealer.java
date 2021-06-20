package me.explicit.module.player;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.Setting;
import me.explicit.utils.Game;
import me.explicit.utils.ItemUtils;
import me.explicit.utils.TimerUtils;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;

public class ChestStealer extends Module {

    TimerUtils timer = new TimerUtils();
    double delay;
    private boolean checkName;
    private static final String[] llIlIIl;
    private static String[] llIllIl;

    public ChestStealer() {
        super(ChestStealer.llIlIIl[0], 0, Category.PLAYER, ChestStealer.llIlIIl[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(ChestStealer.llIlIIl[2], this, 100.0D, 0.0D, 500.0D, true));
        Explicit.instance.sm.rSetting(new Setting(ChestStealer.llIlIIl[3], this, 150.0D, 0.0D, 500.0D, true));
        Explicit.instance.sm.rSetting(new Setting(ChestStealer.llIlIIl[4], this, true));
        Explicit.instance.sm.rSetting(new Setting(ChestStealer.llIlIIl[5], this, false));
    }

    public void onEnable() {
        this.setDelay();
        super.onEnable();
    }

    public void onUpdate() {
        this.checkName = Explicit.instance.sm.getSettingByName(this, ChestStealer.llIlIIl[6]).getValBoolean();
        int i = Explicit.instance.sm.getSettingByName(this, ChestStealer.llIlIIl[7]).getValInt();
        int j = Explicit.instance.sm.getSettingByName(this, ChestStealer.llIlIIl[8]).getValInt();

        if (Module.mc.currentScreen instanceof GuiChest) {
            GuiChest guichest = (GuiChest) Module.mc.currentScreen;
            ContainerChest containerchest = (ContainerChest) Game.Player().openContainer;

            if ((containerchest.getLowerChestInventory().getName().toLowerCase().contains(ChestStealer.llIlIIl[9]) || containerchest.getLowerChestInventory().getName().toLowerCase().contains(ChestStealer.llIlIIl[10]) || containerchest.getLowerChestInventory().getName().toLowerCase().contains(ChestStealer.llIlIIl[11]) || containerchest.getLowerChestInventory().getName().toLowerCase().contains(ChestStealer.llIlIIl[12])) && this.checkName) {
                return;
            }

            if (this.isChestEmpty(guichest) || this.isInventoryFull()) {
                Module.mc.thePlayer.closeScreen();
                return;
            }

            for (int k = 0; k < containerchest.getLowerChestInventory().getSizeInventory(); ++k) {
                ItemStack itemstack = containerchest.getLowerChestInventory().getStackInSlot(k);

                if (itemstack != null && this.timer.hasReached(this.delay) && (this.isValidItem(itemstack) || !Explicit.instance.sm.getSettingByName(this, ChestStealer.llIlIIl[13]).getValBoolean())) {
                    Module.mc.playerController.windowClick(guichest.inventorySlots.windowId, k, 0, 1, Module.mc.thePlayer);
                    this.setDelay();
                    this.timer.reset();
                    break;
                }
            }
        }

    }

    private boolean isValidItem(ItemStack itemstack) {
        return itemstack != null && (ItemUtils.compareDamage(itemstack, ItemUtils.bestSword()) != null && ItemUtils.compareDamage(itemstack, ItemUtils.bestSword()) == itemstack || itemstack.getItem() instanceof ItemBlock || itemstack.getItem() instanceof ItemPotion && !ItemUtils.isBadPotion(itemstack) || itemstack.getItem() instanceof ItemArmor || itemstack.getItem() instanceof ItemAppleGold || itemstack.getItem() instanceof ItemFood);
    }

    public void setDelay() {
        double d0 = Explicit.instance.sm.getSettingByName(this, ChestStealer.llIlIIl[14]).getValDouble();
        double d1 = Explicit.instance.sm.getSettingByName(this, ChestStealer.llIlIIl[15]).getValDouble();

        if (d0 == d1) {
            d1 = d0 * 1.1D;
        }

        this.delay = ThreadLocalRandom.current().nextDouble(Math.min(d0, d1), Math.max(d0, d1));
    }

    private boolean isChestEmpty(GuiChest guichest) {
        ContainerChest containerchest = (ContainerChest) Game.Player().openContainer;

        for (int i = 0; i < containerchest.getLowerChestInventory().getSizeInventory(); ++i) {
            ItemStack itemstack = containerchest.getLowerChestInventory().getStackInSlot(i);

            if (itemstack != null && (this.isValidItem(itemstack) || !Explicit.instance.sm.getSettingByName(this, ChestStealer.llIlIIl[16]).getValBoolean())) {
                return false;
            }
        }

        return true;
    }

    private boolean isInventoryFull() {
        for (int i = 9; i <= 44; ++i) {
            ItemStack itemstack = Module.mc.thePlayer.inventoryContainer.getSlot(i).getStack();

            if (itemstack == null) {
                return false;
            }
        }

        return true;
    }

    static {
        llIIIIIII();
        lIlllllll();
    }

    private static void lIlllllll() {
        llIlIIl = new String[17];
        ChestStealer.llIlIIl[0] = lIlllIIll(ChestStealer.llIllIl[0], ChestStealer.llIllIl[1]);
        ChestStealer.llIlIIl[1] = lIlllIlII(ChestStealer.llIllIl[2], ChestStealer.llIllIl[3]);
        ChestStealer.llIlIIl[2] = lIlllIIll(ChestStealer.llIllIl[4], ChestStealer.llIllIl[5]);
        ChestStealer.llIlIIl[3] = lIlllIlII(ChestStealer.llIllIl[6], ChestStealer.llIllIl[7]);
        ChestStealer.llIlIIl[4] = lIlllIIll(ChestStealer.llIllIl[8], ChestStealer.llIllIl[9]);
        ChestStealer.llIlIIl[5] = lIlllIIll(ChestStealer.llIllIl[10], ChestStealer.llIllIl[11]);
        ChestStealer.llIlIIl[6] = lIllllIIl(ChestStealer.llIllIl[12], ChestStealer.llIllIl[13]);
        ChestStealer.llIlIIl[7] = lIlllIlII(ChestStealer.llIllIl[14], ChestStealer.llIllIl[15]);
        ChestStealer.llIlIIl[8] = lIllllIIl(ChestStealer.llIllIl[16], ChestStealer.llIllIl[17]);
        ChestStealer.llIlIIl[9] = lIlllIlII(ChestStealer.llIllIl[18], ChestStealer.llIllIl[19]);
        ChestStealer.llIlIIl[10] = lIlllIlII(ChestStealer.llIllIl[20], ChestStealer.llIllIl[21]);
        ChestStealer.llIlIIl[11] = lIllllIIl(ChestStealer.llIllIl[22], ChestStealer.llIllIl[23]);
        ChestStealer.llIlIIl[12] = lIllllIIl(ChestStealer.llIllIl[24], ChestStealer.llIllIl[25]);
        ChestStealer.llIlIIl[13] = lIlllIIll(ChestStealer.llIllIl[26], ChestStealer.llIllIl[27]);
        ChestStealer.llIlIIl[14] = lIllllIIl(ChestStealer.llIllIl[28], ChestStealer.llIllIl[29]);
        ChestStealer.llIlIIl[15] = lIlllIlII(ChestStealer.llIllIl[30], ChestStealer.llIllIl[31]);
        ChestStealer.llIlIIl[16] = lIlllIIll(ChestStealer.llIllIl[32], ChestStealer.llIllIl[33]);
        ChestStealer.llIllIl = null;
    }

    private static void llIIIIIII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ChestStealer.llIllIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIlllIIll(String s, String s1) {
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

    private static String lIlllIlII(String s, String s1) {
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

    private static String lIllllIIl(String s, String s1) {
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
