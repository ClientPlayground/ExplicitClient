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
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Safewalk extends Module {

    private static final String[] llII;
    private static String[] llIl;

    public Safewalk() {
        super(Safewalk.llII[0], 0, Category.PLAYER, Safewalk.llII[1]);
    }

    public void onTick() {
        if (Safewalk.mc.thePlayer != null && Game.World() != null && Game.Player().onGround) {
            double d0 = (double) ((int) Game.Player().posX) - Game.Player().posX;
            double d1 = Game.Player().posZ - (double) ((int) Game.Player().posZ);

            System.out.println(String.valueOf((new StringBuilder()).append(d0).append(Safewalk.llII[2]).append(d1)));
            if (Game.World().getBlockState((new BlockPos(Game.Player())).add(0, -1, 0)).getBlock() == Blocks.air) {
                Safewalk.mc.thePlayer.motionX = 0.0D;
                Safewalk.mc.thePlayer.motionY = 0.0D;
                Safewalk.mc.thePlayer.motionZ = 0.0D;
                Safewalk.mc.thePlayer.jumpMovementFactor = 0.0F;
                Safewalk.mc.thePlayer.noClip = true;
                Safewalk.mc.thePlayer.onGround = false;
            }

        }
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingUpdateEvent livingupdateevent) {}

    public void onDisable() {}

    static {
        lIllIl();
        lIllII();
    }

    private static void lIllII() {
        llII = new String[3];
        Safewalk.llII[0] = lIlIlI(Safewalk.llIl[0], Safewalk.llIl[1]);
        Safewalk.llII[1] = lIlIlI(Safewalk.llIl[2], Safewalk.llIl[3]);
        Safewalk.llII[2] = lIlIll(Safewalk.llIl[4], Safewalk.llIl[5]);
        Safewalk.llIl = null;
    }

    private static void lIllIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Safewalk.llIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIlIlI(String s, String s1) {
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

    private static String lIlIll(String s, String s1) {
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
