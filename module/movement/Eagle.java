package me.explicit.module.movement;

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
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;

public class Eagle extends Module {

    private TimerUtils sneakTimer = new TimerUtils();
    private static final String[] llIIllII;
    private static String[] llIIllIl;

    public Eagle() {
        super(Eagle.llIIllII[0], 0, Category.MOVEMENT, Eagle.llIIllII[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(Eagle.llIIllII[2], this, true));
        Explicit.instance.sm.rSetting(new Setting(Eagle.llIIllII[3], this, false));
        Explicit.instance.sm.rSetting(new Setting(Eagle.llIIllII[4], this, 500.0D, 50.0D, 1500.0D, true));
    }

    public void onTick() {
        if (Game.Player() != null && Game.World() != null) {
            if (!Explicit.instance.sm.getSettingByName(this, Eagle.llIIllII[5]).getValBoolean() && Eagle.mc.currentScreen != null) {
                KeyBinding.setKeyBindState(Eagle.mc.gameSettings.keyBindSneak.getKeyCode(), false);
            } else if (Explicit.instance.sm.getSettingByName(this, Eagle.llIIllII[6]).getValBoolean() && !Keyboard.isKeyDown(Eagle.mc.gameSettings.keyBindSneak.getKeyCode())) {
                KeyBinding.setKeyBindState(Eagle.mc.gameSettings.keyBindSneak.getKeyCode(), false);
            } else {
                if (Game.World().getBlockState((new BlockPos(Game.Player())).add(0, -1, 0)).getBlock() == Blocks.air && Eagle.mc.thePlayer.onGround) {
                    KeyBinding.setKeyBindState(Eagle.mc.gameSettings.keyBindSneak.getKeyCode(), true);
                    this.sneakTimer.reset();
                } else if (this.sneakTimer.hasReached((double) Explicit.instance.sm.getSettingByName(this, Eagle.llIIllII[7]).getValInt())) {
                    KeyBinding.setKeyBindState(Eagle.mc.gameSettings.keyBindSneak.getKeyCode(), Keyboard.isKeyDown(Eagle.mc.gameSettings.keyBindSneak.getKeyCode()));
                } else {
                    KeyBinding.setKeyBindState(Eagle.mc.gameSettings.keyBindSneak.getKeyCode(), false);
                }

            }
        } else {
            KeyBinding.setKeyBindState(Eagle.mc.gameSettings.keyBindSneak.getKeyCode(), false);
        }
    }

    public void onDisable() {
        KeyBinding.setKeyBindState(Eagle.mc.gameSettings.keyBindSneak.getKeyCode(), false);
    }

    static {
        llIIIIIIlI();
        llIIIIIIIl();
    }

    private static void llIIIIIIIl() {
        llIIllII = new String[8];
        Eagle.llIIllII[0] = lIllllllIl(Eagle.llIIllIl[0], Eagle.llIIllIl[1]);
        Eagle.llIIllII[1] = lIllllllIl(Eagle.llIIllIl[2], Eagle.llIIllIl[3]);
        Eagle.llIIllII[2] = lIllllllIl(Eagle.llIIllIl[4], Eagle.llIIllIl[5]);
        Eagle.llIIllII[3] = lIllllllll(Eagle.llIIllIl[6], Eagle.llIIllIl[7]);
        Eagle.llIIllII[4] = lIllllllll(Eagle.llIIllIl[8], Eagle.llIIllIl[9]);
        Eagle.llIIllII[5] = lIllllllll(Eagle.llIIllIl[10], Eagle.llIIllIl[11]);
        Eagle.llIIllII[6] = llIIIIIIII(Eagle.llIIllIl[12], Eagle.llIIllIl[13]);
        Eagle.llIIllII[7] = llIIIIIIII(Eagle.llIIllIl[14], Eagle.llIIllIl[15]);
        Eagle.llIIllIl = null;
    }

    private static void llIIIIIIlI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Eagle.llIIllIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIIIIIIII(String s, String s1) {
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

    private static String lIllllllIl(String s, String s1) {
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

    private static String lIllllllll(String s, String s1) {
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
