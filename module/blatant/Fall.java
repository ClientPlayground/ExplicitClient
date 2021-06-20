package me.explicit.module.blatant;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.utils.Game;
import net.minecraft.block.BlockAir;
import net.minecraft.network.play.client.C03PacketPlayer.C06PacketPlayerPosLook;
import net.minecraft.util.BlockPos;

public class Fall extends Module {

    private static final String[] lIIllllIl;
    private static String[] lIIlllllI;

    public Fall() {
        super(Fall.lIIllllIl[0], 0, Category.BLATANT, Fall.lIIllllIl[1]);
    }

    public void onTick() {
        if (Game.Player().fallDistance > 3.0F && this.isBlockUnderneath()) {
            Fall.mc.getNetHandler().addToSendQueue(new C06PacketPlayerPosLook(Game.Player().posX, Game.Player().posY, Game.Player().posZ, Game.Player().rotationYaw, Game.Player().rotationPitch, true));
            Game.Player().fallDistance = 0.0F;
        }

    }

    private boolean isBlockUnderneath() {
        boolean flag = false;

        for (int i = 0; (double) i < Game.Player().posY + 2.0D; ++i) {
            BlockPos blockpos = new BlockPos(Game.Player().posX, (double) i, Game.Player().posZ);

            if (!(Module.mc.theWorld.getBlockState(blockpos).getBlock() instanceof BlockAir)) {
                flag = true;
            }
        }

        return flag;
    }

    public double getDistanceToGround() {
        int i = 0;

        for (int j = 0; (double) j < Game.Player().posY; ++j) {
            BlockPos blockpos = new BlockPos(Game.Player().posX, Game.Player().posY - (double) j, Game.Player().posZ);

            if (Game.World().isAirBlock(blockpos)) {
                ++i;
            }
        }

        return (double) i;
    }

    static {
        lIIIlIlIIll();
        lIIIlIlIIlI();
    }

    private static void lIIIlIlIIlI() {
        lIIllllIl = new String[2];
        Fall.lIIllllIl[0] = lIIIlIlIIIl(Fall.lIIlllllI[0], Fall.lIIlllllI[1]);
        Fall.lIIllllIl[1] = lIIIlIlIIIl(Fall.lIIlllllI[2], Fall.lIIlllllI[3]);
        Fall.lIIlllllI = null;
    }

    private static void lIIIlIlIIll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Fall.lIIlllllI = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIlIlIIIl(String s, String s1) {
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
