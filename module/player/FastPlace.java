package me.explicit.module.player;

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
import me.explicit.utils.PrivateUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemFireball;
import net.minecraft.item.ItemSnowball;

public class FastPlace extends Module {

    public static int placeDelay;
    private static final String[] lIIIlII;
    private static String[] lIIIlIl;

    public FastPlace() {
        super(FastPlace.lIIIlII[0], 0, Category.PLAYER, FastPlace.lIIIlII[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(FastPlace.lIIIlII[2], this, true));
        Explicit.instance.sm.rSetting(new Setting(FastPlace.lIIIlII[3], this, false));
        Explicit.instance.sm.rSetting(new Setting(FastPlace.lIIIlII[4], this, false));
    }

    public void onTick() {
        boolean flag = Explicit.instance.sm.getSettingByName(this, FastPlace.lIIIlII[5]).getValBoolean();
        boolean flag1 = Explicit.instance.sm.getSettingByName(this, FastPlace.lIIIlII[6]).getValBoolean();
        boolean flag2 = Explicit.instance.sm.getSettingByName(this, FastPlace.lIIIlII[7]).getValBoolean();
        byte b0 = 0;

        if (Game.Player().inventory.getCurrentItem() != null) {
            Item item = Game.Player().inventory.getCurrentItem().getItem();

            if ((!(item instanceof ItemBlock) || !flag) && (!(item instanceof ItemSnowball) && !(item instanceof ItemEgg) && !(item instanceof ItemFireball) || !flag1)) {
                if (flag2 && (!(item instanceof ItemBlock) || !flag) && (item instanceof ItemSnowball || item instanceof ItemEgg || item instanceof ItemFireball) && flag1) {
                    this.setFastPlace(b0);
                } else if ((!(item instanceof ItemBlock) || !flag) && (item instanceof ItemSnowball || item instanceof ItemEgg || item instanceof ItemFireball) && flag1) {
                    this.setFastPlace(4);
                }
            } else {
                this.setFastPlace(b0);
            }
        } else if (Game.Player().inventory.getCurrentItem() == null) {
            this.setFastPlace(4);
        }

    }

    public void onDisable() {
        this.setFastPlace(4);
        FastPlace.placeDelay = 4;
        super.onDisable();
    }

    public void setFastPlace(int i) {
        PrivateUtils.setRightClickDelayTimer(i);
    }

    static {
        llllIIlI();
        llllIIIl();
        FastPlace.placeDelay = 4;
    }

    private static void llllIIIl() {
        lIIIlII = new String[8];
        FastPlace.lIIIlII[0] = lllIllll(FastPlace.lIIIlIl[0], FastPlace.lIIIlIl[1]);
        FastPlace.lIIIlII[1] = llllIIII(FastPlace.lIIIlIl[2], FastPlace.lIIIlIl[3]);
        FastPlace.lIIIlII[2] = llllIIII(FastPlace.lIIIlIl[4], FastPlace.lIIIlIl[5]);
        FastPlace.lIIIlII[3] = llllIIII(FastPlace.lIIIlIl[6], FastPlace.lIIIlIl[7]);
        FastPlace.lIIIlII[4] = lllIllll(FastPlace.lIIIlIl[8], FastPlace.lIIIlIl[9]);
        FastPlace.lIIIlII[5] = lllIllll(FastPlace.lIIIlIl[10], FastPlace.lIIIlIl[11]);
        FastPlace.lIIIlII[6] = llllIIII(FastPlace.lIIIlIl[12], FastPlace.lIIIlIl[13]);
        FastPlace.lIIIlII[7] = llllIIII(FastPlace.lIIIlIl[14], FastPlace.lIIIlIl[15]);
        FastPlace.lIIIlIl = null;
    }

    private static void llllIIlI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        FastPlace.lIIIlIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llllIIII(String s, String s1) {
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

    private static String lllIllll(String s, String s1) {
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
