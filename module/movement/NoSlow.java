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
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.MovementInput;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoSlow extends Module {

    private static final String[] lIIllIlI;
    private static String[] lIIllIll;

    public NoSlow() {
        super(NoSlow.lIIllIlI[0], 0, Category.MOVEMENT, NoSlow.lIIllIlI[1]);
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingUpdateEvent livingupdateevent) {
        if (Game.Player() != null && Game.Player().isUsingItem() && !Game.Player().isRiding()) {
            EntityPlayerSP entityplayersp = Game.Player();

            entityplayersp.moveForward *= 5.0F;
            entityplayersp = Game.Player();
            entityplayersp.moveStrafing *= 5.0F;
            MovementInput movementinput = Game.Player().movementInput;

            movementinput.moveForward *= 5.0F;
            movementinput = Game.Player().movementInput;
            movementinput.moveStrafe *= 5.0F;
            entityplayersp = Game.Player();
            entityplayersp.motionX *= 1.1D;
            entityplayersp = Game.Player();
            entityplayersp.motionY *= 1.1D;
        }

    }

    static {
        lIIIlIIlII();
        lIIIlIIIll();
    }

    private static void lIIIlIIIll() {
        lIIllIlI = new String[2];
        NoSlow.lIIllIlI[0] = lIIIlIIIlI(NoSlow.lIIllIll[0], NoSlow.lIIllIll[1]);
        NoSlow.lIIllIlI[1] = lIIIlIIIlI(NoSlow.lIIllIll[2], NoSlow.lIIllIll[3]);
        NoSlow.lIIllIll = null;
    }

    private static void lIIIlIIlII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        NoSlow.lIIllIll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIlIIIlI(String s, String s1) {
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
