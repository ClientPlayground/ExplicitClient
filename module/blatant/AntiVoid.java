package me.explicit.module.blatant;

import io.netty.util.internal.ThreadLocalRandom;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.utils.Game;
import net.minecraft.block.material.Material;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.util.BlockPos;

public class AntiVoid extends Module {

    private static final String[] lIIIllI;
    private static String[] lIIlIII;

    public AntiVoid() {
        super(AntiVoid.lIIIllI[0], AntiVoid.lIIIllI[1], Category.BLATANT);
    }

    public void onUpdate() {
        if (Game.Player() != null && Game.Player().fallDistance > 4.0F && !this.isBlockUnderneath()) {
            Game.Player().sendQueue.addToSendQueue(new C04PacketPlayerPosition(Game.Player().posX, Game.Player().posY + ThreadLocalRandom.current().nextDouble(8.0D, 12.0D), Game.Player().posZ, false));
        }

    }

    public boolean isBlockUnderneath() {
        for (int i = 0; (double) i < AntiVoid.mc.thePlayer.posY + 1.0D; ++i) {
            if (AntiVoid.mc.theWorld.getBlockState(new BlockPos(AntiVoid.mc.thePlayer.posX, (double) i, AntiVoid.mc.thePlayer.posZ)).getBlock().getMaterial() != Material.air) {
                return true;
            }
        }

        return false;
    }

    static {
        llllIlll();
        llllIlIl();
    }

    private static void llllIlIl() {
        lIIIllI = new String[2];
        AntiVoid.lIIIllI[0] = llllIIll(AntiVoid.lIIlIII[0], AntiVoid.lIIlIII[1]);
        AntiVoid.lIIIllI[1] = llllIlII(AntiVoid.lIIlIII[2], AntiVoid.lIIlIII[3]);
        AntiVoid.lIIlIII = null;
    }

    private static void llllIlll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        AntiVoid.lIIlIII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llllIlII(String s, String s1) {
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

    private static String llllIIll(String s, String s1) {
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
