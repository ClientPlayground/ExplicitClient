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
import me.explicit.utils.TimerUtils;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;

public class AutoArmor extends Module {

    private TimerUtils timer = new TimerUtils();
    private TimerUtils dropTimer = new TimerUtils();
    private boolean dropping;
    private double delay;
    private static final String[] lIIlI;
    private static String[] lIIll;

    public AutoArmor() {
        super(AutoArmor.lIIlI[0], 0, Category.PLAYER, AutoArmor.lIIlI[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(AutoArmor.lIIlI[2], this, 100.0D, 0.0D, 1000.0D, true));
        Explicit.instance.sm.rSetting(new Setting(AutoArmor.lIIlI[3], this, 150.0D, 0.0D, 1000.0D, true));
        Explicit.instance.sm.rSetting(new Setting(AutoArmor.lIIlI[4], this, true));
        Explicit.instance.sm.rSetting(new Setting(AutoArmor.lIIlI[5], this, false));
    }

    public void onTick() {
        if (this.isToggled()) {
            String s;

            if (Explicit.instance.sm.getSettingByName(this, AutoArmor.lIIlI[6]).getValBoolean()) {
                s = AutoArmor.lIIlI[7];
            } else {
                s = AutoArmor.lIIlI[8];
            }

            double d0 = Explicit.instance.sm.getSettingByName(this, AutoArmor.lIIlI[9]).getValDouble();
            double d1 = Explicit.instance.sm.getSettingByName(this, AutoArmor.lIIlI[10]).getValDouble();

            if (d0 == d1) {
                d1 = d0 * 1.1D;
            }

            this.delay = ThreadLocalRandom.current().nextDouble(Math.min(d0, d1), Math.max(d0, d1));
            if (!s.equalsIgnoreCase(AutoArmor.lIIlI[11]) || AutoArmor.mc.currentScreen instanceof GuiInventory) {
                if ((AutoArmor.mc.currentScreen == null || AutoArmor.mc.currentScreen instanceof GuiInventory || AutoArmor.mc.currentScreen instanceof GuiChat) && this.timer.hasReached(this.delay)) {
                    this.getBestArmor();
                }

                if (!this.dropping) {
                    if ((AutoArmor.mc.currentScreen == null || AutoArmor.mc.currentScreen instanceof GuiInventory || AutoArmor.mc.currentScreen instanceof GuiChat) && this.timer.hasReached(this.delay)) {
                        this.getBestArmor();
                    }
                } else if (this.dropTimer.hasReached(this.delay)) {
                    Module.mc.playerController.windowClick(Module.mc.thePlayer.inventoryContainer.windowId, -999, 0, 0, Module.mc.thePlayer);
                    this.dropTimer.reset();
                    this.dropping = false;
                }

            }
        }
    }

    public void getBestArmor() {
        int i;
        ItemStack itemstack;

        for (i = 1; i < 5; ++i) {
            if (AutoArmor.mc.thePlayer.inventoryContainer.getSlot(4 + i).getHasStack()) {
                itemstack = AutoArmor.mc.thePlayer.inventoryContainer.getSlot(4 + i).getStack();
                if (isBestArmor(itemstack, i)) {
                    continue;
                }

                if (Explicit.instance.sm.getSettingByName(this, AutoArmor.lIIlI[12]).getValBoolean()) {
                    this.drop(4 + i);
                } else {
                    this.shiftClick(4 + i);
                }
            }

            for (int j = 9; j < 45; ++j) {
                if (AutoArmor.mc.thePlayer.inventoryContainer.getSlot(j).getHasStack()) {
                    ItemStack itemstack1 = AutoArmor.mc.thePlayer.inventoryContainer.getSlot(j).getStack();

                    if (isBestArmor(itemstack1, i) && getProtection(itemstack1) > 0.0F) {
                        this.shiftClick(j);
                        this.timer.reset();
                        if (this.delay > 0.0D) {
                            return;
                        }
                    }
                }
            }
        }

        if (Explicit.instance.sm.getSettingByName(this, AutoArmor.lIIlI[13]).getValBoolean()) {
            for (i = 9; i < 45; ++i) {
                if (AutoArmor.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                    itemstack = AutoArmor.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                    if ((getProtection(itemstack) > 0.0F || this.isDuplicate(itemstack, i)) && !this.dropping && itemstack.getItem() instanceof ItemArmor) {
                        this.drop(i);
                    }
                }
            }
        }

    }

    public boolean isDuplicate(ItemStack itemstack, int i) {
        for (int j = 0; j < 45; ++j) {
            if (Module.mc.thePlayer.inventoryContainer.getSlot(j).getHasStack()) {
                ItemStack itemstack1 = Module.mc.thePlayer.inventoryContainer.getSlot(j).getStack();

                if (itemstack1 != itemstack && i != j && getProtection(itemstack) == getProtection(itemstack1) && itemstack1.getItem() instanceof ItemArmor && !(itemstack1.getItem() instanceof ItemPotion) && !(itemstack1.getItem() instanceof ItemBlock)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isBestArmor(ItemStack itemstack, int i) {
        float f = getProtection(itemstack);
        String s = AutoArmor.lIIlI[14];

        if (i == 1) {
            s = AutoArmor.lIIlI[15];
        } else if (i == 2) {
            s = AutoArmor.lIIlI[16];
        } else if (i == 3) {
            s = AutoArmor.lIIlI[17];
        } else if (i == 4) {
            s = AutoArmor.lIIlI[18];
        }

        if (!itemstack.getUnlocalizedName().contains(s)) {
            return false;
        } else {
            for (int j = 5; j < 45; ++j) {
                if (AutoArmor.mc.thePlayer.inventoryContainer.getSlot(j).getHasStack()) {
                    ItemStack itemstack1 = AutoArmor.mc.thePlayer.inventoryContainer.getSlot(j).getStack();

                    if (getProtection(itemstack1) > f && itemstack1.getUnlocalizedName().contains(s)) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    public void shiftClick(int i) {
        AutoArmor.mc.playerController.windowClick(AutoArmor.mc.thePlayer.inventoryContainer.windowId, i, 0, 1, AutoArmor.mc.thePlayer);
    }

    public void drop(int i) {
        if (Explicit.instance.sm.getSettingByName(this, AutoArmor.lIIlI[19]).getValBoolean() && this.dropTimer.hasReached(this.delay) && !this.dropping) {
            Module.mc.playerController.windowClick(Module.mc.thePlayer.inventoryContainer.windowId, i, 0, 0, Module.mc.thePlayer);
            this.dropping = true;
            this.dropTimer.reset();
        }

    }

    public static float getProtection(ItemStack itemstack) {
        float f = 0.0F;

        if (itemstack.getItem() instanceof ItemArmor) {
            ItemArmor itemarmor = (ItemArmor) itemstack.getItem();

            f = (float) ((double) f + (double) itemarmor.damageReduceAmount + (double) ((100 - itemarmor.damageReduceAmount) * EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, itemstack)) * 0.0075D);
            f = (float) ((double) f + (double) EnchantmentHelper.getEnchantmentLevel(Enchantment.blastProtection.effectId, itemstack) / 100.0D);
            f = (float) ((double) f + (double) EnchantmentHelper.getEnchantmentLevel(Enchantment.fireProtection.effectId, itemstack) / 100.0D);
            f = (float) ((double) f + (double) EnchantmentHelper.getEnchantmentLevel(Enchantment.thorns.effectId, itemstack) / 100.0D);
            f = (float) ((double) f + (double) EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, itemstack) / 50.0D);
            f = (float) ((double) f + (double) EnchantmentHelper.getEnchantmentLevel(Enchantment.projectileProtection.effectId, itemstack) / 100.0D);
        }

        return f;
    }

    static {
        llllll();
        lllIIl();
    }

    private static void lllIIl() {
        lIIlI = new String[20];
        AutoArmor.lIIlI[0] = llIllI(AutoArmor.lIIll[0], AutoArmor.lIIll[1]);
        AutoArmor.lIIlI[1] = llIlll(AutoArmor.lIIll[2], AutoArmor.lIIll[3]);
        AutoArmor.lIIlI[2] = lllIII(AutoArmor.lIIll[4], AutoArmor.lIIll[5]);
        AutoArmor.lIIlI[3] = llIllI(AutoArmor.lIIll[6], AutoArmor.lIIll[7]);
        AutoArmor.lIIlI[4] = lllIII(AutoArmor.lIIll[8], AutoArmor.lIIll[9]);
        AutoArmor.lIIlI[5] = llIllI(AutoArmor.lIIll[10], AutoArmor.lIIll[11]);
        AutoArmor.lIIlI[6] = lllIII(AutoArmor.lIIll[12], AutoArmor.lIIll[13]);
        AutoArmor.lIIlI[7] = llIllI(AutoArmor.lIIll[14], AutoArmor.lIIll[15]);
        AutoArmor.lIIlI[8] = llIlll(AutoArmor.lIIll[16], AutoArmor.lIIll[17]);
        AutoArmor.lIIlI[9] = lllIII(AutoArmor.lIIll[18], AutoArmor.lIIll[19]);
        AutoArmor.lIIlI[10] = llIllI(AutoArmor.lIIll[20], AutoArmor.lIIll[21]);
        AutoArmor.lIIlI[11] = llIlll(AutoArmor.lIIll[22], AutoArmor.lIIll[23]);
        AutoArmor.lIIlI[12] = llIllI(AutoArmor.lIIll[24], AutoArmor.lIIll[25]);
        AutoArmor.lIIlI[13] = lllIII(AutoArmor.lIIll[26], AutoArmor.lIIll[27]);
        AutoArmor.lIIlI[14] = llIlll(AutoArmor.lIIll[28], AutoArmor.lIIll[29]);
        AutoArmor.lIIlI[15] = llIlll(AutoArmor.lIIll[30], AutoArmor.lIIll[31]);
        AutoArmor.lIIlI[16] = llIlll(AutoArmor.lIIll[32], AutoArmor.lIIll[33]);
        AutoArmor.lIIlI[17] = llIlll(AutoArmor.lIIll[34], AutoArmor.lIIll[35]);
        AutoArmor.lIIlI[18] = llIllI(AutoArmor.lIIll[36], AutoArmor.lIIll[37]);
        AutoArmor.lIIlI[19] = llIllI(AutoArmor.lIIll[38], AutoArmor.lIIll[39]);
        AutoArmor.lIIll = null;
    }

    private static void llllll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        AutoArmor.lIIll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIllI(String s, String s1) {
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

    private static String llIlll(String s, String s1) {
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

    private static String lllIII(String s, String s1) {
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
