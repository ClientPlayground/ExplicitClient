package me.explicit.module.player;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.utils.Game;
import me.explicit.utils.TimerUtils;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import org.lwjgl.input.Mouse;

public class AutoMine extends Module {

    private TimerUtils timer = new TimerUtils();
    private static final String[] lIlIII;
    private static String[] lIlIIl;

    public AutoMine() {
        super(AutoMine.lIlIII[0], 0, Category.PLAYER, AutoMine.lIlIII[1]);
    }

    public void onEnable() {
        super.onEnable();
        this.timer.reset();
        System.out.println();
    }

    @SubscribeEvent
    public void rty(RenderTickEvent rendertickevent) {
        int i;

        if (AutoMine.mc.objectMouseOver != null && AutoMine.mc.objectMouseOver.getBlockPos() != null && !Game.World().isAirBlock(AutoMine.mc.objectMouseOver.getBlockPos()) && !Mouse.isButtonDown(0)) {
            i = AutoMine.mc.gameSettings.keyBindAttack.getKeyCode();
            KeyBinding.setKeyBindState(i, true);
            KeyBinding.onTick(i);
            this.timer.reset();
        } else if (this.timer.hasReached(30.0D)) {
            i = AutoMine.mc.gameSettings.keyBindAttack.getKeyCode();
            KeyBinding.setKeyBindState(i, false);
        }

    }

    static {
        lIIlIIlI();
        lIIlIIIl();
    }

    private static void lIIlIIIl() {
        lIlIII = new String[2];
        AutoMine.lIlIII[0] = lIIIllll(AutoMine.lIlIIl[0], AutoMine.lIlIIl[1]);
        AutoMine.lIlIII[1] = lIIlIIII(AutoMine.lIlIIl[2], AutoMine.lIlIIl[3]);
        AutoMine.lIlIIl = null;
    }

    private static void lIIlIIlI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        AutoMine.lIlIIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIllll(String s, String s1) {
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

    private static String lIIlIIII(String s, String s1) {
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
