package me.explicit.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public class ItemUtils {

    private static final Minecraft mc;
    private static final String[] lIllIIlI;
    private static String[] lIllIIll;

    public static ItemStack bestSword() {
        ItemStack itemstack = null;
        float f = 0.0F;

        for (int i = 9; i < 45; ++i) {
            if (ItemUtils.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                ItemStack itemstack1 = ItemUtils.mc.thePlayer.inventoryContainer.getSlot(i).getStack();

                if (itemstack1.getItem() instanceof ItemSword) {
                    float f1 = getItemDamage(itemstack1);

                    if (f1 > f) {
                        f = f1;
                        itemstack = itemstack1;
                    }
                }
            }
        }

        return itemstack;
    }

    public static boolean isThrowable(ItemStack itemstack) {
        Item item = itemstack.getItem();

        return item instanceof ItemBow || item instanceof ItemSnowball || item instanceof ItemEgg || item instanceof ItemEnderPearl || item instanceof ItemPotion && ItemPotion.isSplash(itemstack.getItemDamage());
    }

    public static ItemStack compareDamage(ItemStack itemstack, ItemStack itemstack1) {
        try {
            return itemstack != null && itemstack1 != null ? (!(itemstack.getItem() instanceof ItemSword) && itemstack1.getItem() instanceof ItemSword ? null : (getItemDamage(itemstack) > getItemDamage(itemstack1) ? itemstack : (getItemDamage(itemstack1) > getItemDamage(itemstack) ? itemstack1 : itemstack))) : null;
        } catch (NullPointerException nullpointerexception) {
            nullpointerexception.printStackTrace();
            return itemstack;
        }
    }

    public static boolean isBad(ItemStack itemstack) {
        return itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[0]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[1]) || itemstack.getItem().getUnlocalizedName().equalsIgnoreCase(ItemUtils.lIllIIlI[2]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[3]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[4]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[5]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[6]) || itemstack.getItem().getUnlocalizedName().equalsIgnoreCase(ItemUtils.lIllIIlI[7]) && !itemstack.getDisplayName().toLowerCase().contains(ItemUtils.lIllIIlI[8]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[9]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[10]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[11]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[12]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[13]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[14]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[15]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[16]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[17]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[18]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[19]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[20]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[21]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[22]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[23]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[24]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[25]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[26]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[27]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[28]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[29]) || itemstack.getItem() instanceof ItemGlassBottle || itemstack.getItem() instanceof ItemArmor && !getBest().contains(itemstack) || itemstack.getItem() instanceof ItemSword && itemstack != bestSword() || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[30]) || itemstack.getItem().getUnlocalizedName().contains(ItemUtils.lIllIIlI[31]) && isBadPotion(itemstack);
    }

    public static List getBest() {
        ArrayList arraylist = new ArrayList();

        for (int i = 0; i < 4; ++i) {
            ItemStack itemstack = null;
            ItemStack[] aitemstack = ItemUtils.mc.thePlayer.inventory.armorInventory;
            int j = ItemUtils.mc.thePlayer.inventory.armorInventory.length;

            ItemStack itemstack1;

            for (int k = 0; k < j; ++k) {
                itemstack1 = aitemstack[k];
                if (itemstack1 != null && itemstack1.getItem() instanceof ItemArmor) {
                    ItemArmor itemarmor = (ItemArmor) itemstack1.getItem();

                    if (itemarmor.armorType == i) {
                        itemstack = itemstack1;
                    }
                }
            }

            double d0 = itemstack == null ? -1.0D : getArmorStrength(itemstack);

            itemstack1 = findBestArmor(i);
            if (itemstack1 != null && getArmorStrength(itemstack1) <= d0) {
                itemstack1 = itemstack;
            }

            if (itemstack1 != null) {
                arraylist.add(itemstack1);
            }
        }

        return arraylist;
    }

    public static ItemStack findBestArmor(int i) {
        ItemStack itemstack = null;
        double d0 = 0.0D;

        for (int j = 0; j < 36; ++j) {
            ItemStack itemstack1 = ItemUtils.mc.thePlayer.inventory.mainInventory[j];

            if (itemstack1 != null) {
                double d1 = getArmorStrength(itemstack1);

                if (d1 != -1.0D) {
                    ItemArmor itemarmor = (ItemArmor) itemstack1.getItem();

                    if (itemarmor.armorType == i && d1 >= d0) {
                        d0 = d1;
                        itemstack = itemstack1;
                    }
                }
            }
        }

        return itemstack;
    }

    public static double getArmorStrength(ItemStack itemstack) {
        if (!(itemstack.getItem() instanceof ItemArmor)) {
            return -1.0D;
        } else {
            float f = (float) ((ItemArmor) itemstack.getItem()).damageReduceAmount;
            Map map = EnchantmentHelper.getEnchantments(itemstack);

            if (map.containsKey(Integer.valueOf(Enchantment.protection.effectId))) {
                int i = ((Integer) map.get(Integer.valueOf(Enchantment.protection.effectId))).intValue();

                f += (float) Enchantment.protection.calcModifierDamage(i, DamageSource.generic);
            }

            return (double) f;
        }
    }

    public static boolean isBadPotion(ItemStack itemstack) {
        if (itemstack != null && itemstack.getItem() instanceof ItemPotion) {
            ItemPotion itempotion = (ItemPotion) itemstack.getItem();

            if (ItemPotion.isSplash(itemstack.getItemDamage())) {
                Iterator iterator = itempotion.getEffects(itemstack).iterator();

                while (iterator.hasNext()) {
                    Object object = iterator.next();
                    PotionEffect potioneffect = (PotionEffect) object;

                    if (potioneffect.getPotionID() == Potion.poison.getId() || potioneffect.getPotionID() == Potion.harm.getId() || potioneffect.getPotionID() == Potion.moveSlowdown.getId() || potioneffect.getPotionID() == Potion.weakness.getId()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static float getItemDamage(ItemStack itemstack) {
        if (itemstack == null) {
            return 0.0F;
        } else if (!(itemstack.getItem() instanceof ItemSword)) {
            return 0.0F;
        } else {
            float f = ((ItemSword) itemstack.getItem()).getDamageVsEntity();

            f += (float) EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, itemstack) * 1.25F;
            f += (float) EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, itemstack) * 0.01F;
            return f;
        }
    }

    static {
        lIlIIIIIll();
        lIlIIIIIlI();
        mc = Minecraft.getMinecraft();
    }

    private static void lIlIIIIIlI() {
        lIllIIlI = new String[32];
        ItemUtils.lIllIIlI[0] = lIIlllllll(ItemUtils.lIllIIll[0], ItemUtils.lIllIIll[1]);
        ItemUtils.lIllIIlI[1] = lIlIIIIIII(ItemUtils.lIllIIll[2], ItemUtils.lIllIIll[3]);
        ItemUtils.lIllIIlI[2] = lIlIIIIIII(ItemUtils.lIllIIll[4], ItemUtils.lIllIIll[5]);
        ItemUtils.lIllIIlI[3] = lIlIIIIIIl(ItemUtils.lIllIIll[6], ItemUtils.lIllIIll[7]);
        ItemUtils.lIllIIlI[4] = lIIlllllll(ItemUtils.lIllIIll[8], ItemUtils.lIllIIll[9]);
        ItemUtils.lIllIIlI[5] = lIlIIIIIII(ItemUtils.lIllIIll[10], ItemUtils.lIllIIll[11]);
        ItemUtils.lIllIIlI[6] = lIIlllllll(ItemUtils.lIllIIll[12], ItemUtils.lIllIIll[13]);
        ItemUtils.lIllIIlI[7] = lIlIIIIIIl(ItemUtils.lIllIIll[14], ItemUtils.lIllIIll[15]);
        ItemUtils.lIllIIlI[8] = lIIlllllll(ItemUtils.lIllIIll[16], ItemUtils.lIllIIll[17]);
        ItemUtils.lIllIIlI[9] = lIlIIIIIII(ItemUtils.lIllIIll[18], ItemUtils.lIllIIll[19]);
        ItemUtils.lIllIIlI[10] = lIlIIIIIIl(ItemUtils.lIllIIll[20], ItemUtils.lIllIIll[21]);
        ItemUtils.lIllIIlI[11] = lIlIIIIIIl(ItemUtils.lIllIIll[22], ItemUtils.lIllIIll[23]);
        ItemUtils.lIllIIlI[12] = lIlIIIIIIl(ItemUtils.lIllIIll[24], ItemUtils.lIllIIll[25]);
        ItemUtils.lIllIIlI[13] = lIlIIIIIII(ItemUtils.lIllIIll[26], ItemUtils.lIllIIll[27]);
        ItemUtils.lIllIIlI[14] = lIlIIIIIIl(ItemUtils.lIllIIll[28], ItemUtils.lIllIIll[29]);
        ItemUtils.lIllIIlI[15] = lIIlllllll(ItemUtils.lIllIIll[30], ItemUtils.lIllIIll[31]);
        ItemUtils.lIllIIlI[16] = lIlIIIIIII(ItemUtils.lIllIIll[32], ItemUtils.lIllIIll[33]);
        ItemUtils.lIllIIlI[17] = lIlIIIIIII(ItemUtils.lIllIIll[34], ItemUtils.lIllIIll[35]);
        ItemUtils.lIllIIlI[18] = lIlIIIIIII(ItemUtils.lIllIIll[36], ItemUtils.lIllIIll[37]);
        ItemUtils.lIllIIlI[19] = lIIlllllll(ItemUtils.lIllIIll[38], ItemUtils.lIllIIll[39]);
        ItemUtils.lIllIIlI[20] = lIlIIIIIIl(ItemUtils.lIllIIll[40], ItemUtils.lIllIIll[41]);
        ItemUtils.lIllIIlI[21] = lIlIIIIIII(ItemUtils.lIllIIll[42], ItemUtils.lIllIIll[43]);
        ItemUtils.lIllIIlI[22] = lIlIIIIIII(ItemUtils.lIllIIll[44], ItemUtils.lIllIIll[45]);
        ItemUtils.lIllIIlI[23] = lIlIIIIIIl(ItemUtils.lIllIIll[46], ItemUtils.lIllIIll[47]);
        ItemUtils.lIllIIlI[24] = lIIlllllll(ItemUtils.lIllIIll[48], ItemUtils.lIllIIll[49]);
        ItemUtils.lIllIIlI[25] = lIlIIIIIII(ItemUtils.lIllIIll[50], ItemUtils.lIllIIll[51]);
        ItemUtils.lIllIIlI[26] = lIIlllllll(ItemUtils.lIllIIll[52], ItemUtils.lIllIIll[53]);
        ItemUtils.lIllIIlI[27] = lIIlllllll(ItemUtils.lIllIIll[54], ItemUtils.lIllIIll[55]);
        ItemUtils.lIllIIlI[28] = lIIlllllll("CjYCNz0=", "yZkZX");
        ItemUtils.lIllIIlI[29] = lIIlllllll("ByYm", "pCDlm");
        ItemUtils.lIllIIlI[30] = lIlIIIIIIl("EP15kV8anRc=", "XDvZl");
        ItemUtils.lIllIIlI[31] = lIlIIIIIII("rCFTWL8Ygjo=", "TbXso");
        ItemUtils.lIllIIll = null;
    }

    private static void lIlIIIIIll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ItemUtils.lIllIIll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIlllllll(String s, String s1) {
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

    private static String lIlIIIIIIl(String s, String s1) {
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

    private static String lIlIIIIIII(String s, String s1) {
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
