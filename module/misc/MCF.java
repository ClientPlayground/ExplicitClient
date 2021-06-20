package me.explicit.module.misc;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.utils.ChatUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Mouse;

public class MCF extends Module {

    boolean waitingForRelease = false;
    private static final String[] lIIl;
    private static String[] lIlI;

    public MCF() {
        super(MCF.lIIl[0], 0, Category.MISC, MCF.lIIl[1]);
    }

    public void onTick() {
        if (Mouse.isButtonDown(2) && !this.waitingForRelease) {
            if (MCF.mc.objectMouseOver != null && MCF.mc.objectMouseOver.entityHit != null && MCF.mc.objectMouseOver.entityHit instanceof EntityPlayer) {
                boolean flag = false;
                Iterator iterator = Explicit.instance.friendManager.getFriends().iterator();

                while (iterator.hasNext()) {
                    String s = (String) iterator.next();

                    if (s.equalsIgnoreCase(MCF.mc.objectMouseOver.entityHit.getName())) {
                        flag = true;
                    }
                }

                if (!flag) {
                    Explicit.instance.friendManager.addFriend(MCF.mc.objectMouseOver.entityHit.getName());
                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(MCF.lIIl[2]).append(getTeamColor((EntityPlayer) MCF.mc.objectMouseOver.entityHit)).append(MCF.mc.objectMouseOver.entityHit.getName()).append(EnumChatFormatting.GOLD).append(MCF.lIIl[3])));
                } else {
                    Explicit.instance.friendManager.getFriends().remove(MCF.mc.objectMouseOver.entityHit.getName());
                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(MCF.lIIl[4]).append(getTeamColor((EntityPlayer) MCF.mc.objectMouseOver.entityHit)).append(MCF.mc.objectMouseOver.entityHit.getName()).append(EnumChatFormatting.GOLD).append(MCF.lIIl[5])));
                }
            }

            this.waitingForRelease = true;
        } else if (!Mouse.isButtonDown(2)) {
            this.waitingForRelease = false;
        }

    }

    public static String getTeamColor(EntityPlayer entityplayer) {
        Character character = Character.valueOf(entityplayer.getDisplayName().getFormattedText().charAt(1));

        return String.valueOf((new StringBuilder()).append(MCF.lIIl[6]).append(character));
    }

    static {
        lIIlII();
        lIIIll();
    }

    private static void lIIIll() {
        lIIl = new String[7];
        MCF.lIIl[0] = lIIIII(MCF.lIlI[0], MCF.lIlI[1]);
        MCF.lIIl[1] = lIIIII(MCF.lIlI[2], MCF.lIlI[3]);
        MCF.lIIl[2] = lIIIIl(MCF.lIlI[4], MCF.lIlI[5]);
        MCF.lIIl[3] = lIIIIl(MCF.lIlI[6], MCF.lIlI[7]);
        MCF.lIIl[4] = lIIIlI(MCF.lIlI[8], MCF.lIlI[9]);
        MCF.lIIl[5] = lIIIII(MCF.lIlI[10], MCF.lIlI[11]);
        MCF.lIIl[6] = lIIIII(MCF.lIlI[12], MCF.lIlI[13]);
        MCF.lIlI = null;
    }

    private static void lIIlII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        MCF.lIlI = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIlI(String s, String s1) {
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

    private static String lIIIII(String s, String s1) {
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

    private static String lIIIIl(String s, String s1) {
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
