package me.explicit.module.blatant;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.utils.CombatUtils;
import me.explicit.utils.Game;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Criticals extends Module {

    private static final String[] llIlIIII;
    private static String[] llIlIIIl;

    public Criticals() {
        super(Criticals.llIlIIII[0], Criticals.llIlIIII[1], Category.BLATANT);
    }

    @SubscribeEvent
    public void mous(MouseEvent mouseevent) {
        if (Game.Player() != null && Game.World() != null && ThreadLocalRandom.current().nextInt(100) < 85 && Criticals.mc.thePlayer.onGround && Criticals.mc.objectMouseOver != null && Criticals.mc.objectMouseOver.entityHit != null && CombatUtils.canTarget(Criticals.mc.objectMouseOver.entityHit, true) && mouseevent.button == 0) {
            for (int i = 0; i < 2; ++i) {
                double d0 = ThreadLocalRandom.current().nextDouble(4.0E-6D, 6.0E-6D);

                Criticals.mc.thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(Criticals.mc.thePlayer.posX, Criticals.mc.thePlayer.posY + d0, Criticals.mc.thePlayer.posZ, false));
                Criticals.mc.thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(Criticals.mc.thePlayer.posX, Criticals.mc.thePlayer.posY, Criticals.mc.thePlayer.posZ, false));
            }
        }

    }

    static {
        llIIIlIIII();
        llIIIIllll();
    }

    private static void llIIIIllll() {
        llIlIIII = new String[2];
        Criticals.llIlIIII[0] = llIIIIllIl(Criticals.llIlIIIl[0], Criticals.llIlIIIl[1]);
        Criticals.llIlIIII[1] = llIIIIlllI(Criticals.llIlIIIl[2], Criticals.llIlIIIl[3]);
        Criticals.llIlIIIl = null;
    }

    private static void llIIIlIIII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Criticals.llIlIIIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIIIIllIl(String s, String s1) {
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

    private static String llIIIIlllI(String s, String s1) {
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
