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
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class InvMove extends Module {

    private static final String[] llIlIlI;
    private static String[] llIlIll;

    public InvMove() {
        super(InvMove.llIlIlI[0], InvMove.llIlIlI[1], Category.MOVEMENT);
    }

    public void onTick() {
        if (InvMove.mc.currentScreen instanceof GuiInventory || InvMove.mc.currentScreen instanceof GuiChest) {
            this.setKeys();
        }

    }

    public void onDisable() {
        this.setKeys();
    }

    private void setKeys() {
        if (Game.Player() != null && Game.World() != null) {
            int i = InvMove.mc.gameSettings.keyBindForward.getKeyCode();
            int j = InvMove.mc.gameSettings.keyBindBack.getKeyCode();
            int k = InvMove.mc.gameSettings.keyBindRight.getKeyCode();
            int l = InvMove.mc.gameSettings.keyBindLeft.getKeyCode();
            int i1 = InvMove.mc.gameSettings.keyBindJump.getKeyCode();
            int j1 = InvMove.mc.gameSettings.keyBindSneak.getKeyCode();
            int k1 = InvMove.mc.gameSettings.keyBindSprint.getKeyCode();

            KeyBinding.setKeyBindState(i, Keyboard.isKeyDown(i));
            KeyBinding.setKeyBindState(j, Keyboard.isKeyDown(j));
            KeyBinding.setKeyBindState(k, Keyboard.isKeyDown(k));
            KeyBinding.setKeyBindState(l, Keyboard.isKeyDown(l));
            KeyBinding.setKeyBindState(i1, Keyboard.isKeyDown(i1));
            KeyBinding.setKeyBindState(j1, Keyboard.isKeyDown(j1));
            KeyBinding.setKeyBindState(k1, Keyboard.isKeyDown(k1));
        }

    }

    static {
        lIllllIII();
        lIlllIlll();
    }

    private static void lIlllIlll() {
        llIlIlI = new String[2];
        InvMove.llIlIlI[0] = lIlllIlIl(InvMove.llIlIll[0], InvMove.llIlIll[1]);
        InvMove.llIlIlI[1] = lIlllIllI(InvMove.llIlIll[2], InvMove.llIlIll[3]);
        InvMove.llIlIll = null;
    }

    private static void lIllllIII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        InvMove.llIlIll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIlllIlIl(String s, String s1) {
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

    private static String lIlllIllI(String s, String s1) {
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
