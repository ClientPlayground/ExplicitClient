package me.explicit.module.movement;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.utils.Game;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;

public class Parkour extends Module {

    private static final String[] llIlII;
    private static String[] llIllI;

    public Parkour() {
        super(Parkour.llIlII[0], Parkour.llIlII[1], Category.MOVEMENT);
    }

    public void onTick() {
        if (Game.Player() != null && Game.World() != null && Parkour.mc.gameSettings.keyBindForward.isKeyDown()) {
            if (Game.World().getBlockState((new BlockPos(Game.Player())).add(0, -1, 0)).getBlock() == Blocks.air && Parkour.mc.thePlayer.onGround) {
                KeyBinding.setKeyBindState(Parkour.mc.gameSettings.keyBindJump.getKeyCode(), true);
            } else {
                KeyBinding.setKeyBindState(Parkour.mc.gameSettings.keyBindJump.getKeyCode(), Keyboard.isKeyDown(Parkour.mc.gameSettings.keyBindJump.getKeyCode()));
            }

        } else {
            KeyBinding.setKeyBindState(Parkour.mc.gameSettings.keyBindJump.getKeyCode(), Keyboard.isKeyDown(Parkour.mc.gameSettings.keyBindJump.getKeyCode()));
        }
    }

    static {
        lIlllIIl();
        lIlIlIIl();
    }

    private static void lIlIlIIl() {
        llIlII = new String[2];
        Parkour.llIlII[0] = lIlIIllI(Parkour.llIllI[0], Parkour.llIllI[1]);
        Parkour.llIlII[1] = lIlIIlll(Parkour.llIllI[2], Parkour.llIllI[3]);
        Parkour.llIllI = null;
    }

    private static void lIlllIIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Parkour.llIllI = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIlIIllI(String s, String s1) {
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

    private static String lIlIIlll(String s, String s1) {
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
