package me.explicit.module.render;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderPlayerEvent.Post;
import net.minecraftforge.client.event.RenderPlayerEvent.Pre;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class Chams extends Module {

    private static final String[] llIIIIIl;
    private static String[] llIIIIlI;

    public Chams() {
        super(Chams.llIIIIIl[0], Chams.llIIIIIl[1], Category.RENDER);
    }

    @SubscribeEvent
    public void onRenderLiving(Pre pre) {
        GL11.glEnable('耷');
        GL11.glPolygonOffset(1.0F, -1100000.0F);
    }

    @SubscribeEvent
    public void onRenderLiving(Post post) {
        GL11.glDisable('耷');
        GL11.glPolygonOffset(1.0F, 1100000.0F);
        Minecraft.getMinecraft().getRenderManager().setRenderOutlines(false);
    }

    static {
        lIllIIlIll();
        lIllIIlIlI();
    }

    private static void lIllIIlIlI() {
        llIIIIIl = new String[2];
        Chams.llIIIIIl[0] = lIllIIlIII(Chams.llIIIIlI[0], Chams.llIIIIlI[1]);
        Chams.llIIIIIl[1] = lIllIIlIIl(Chams.llIIIIlI[2], Chams.llIIIIlI[3]);
        Chams.llIIIIlI = null;
    }

    private static void lIllIIlIll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Chams.llIIIIlI = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIllIIlIII(String s, String s1) {
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

    private static String lIllIIlIIl(String s, String s1) {
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
