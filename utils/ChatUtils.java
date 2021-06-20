package me.explicit.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ChatUtils {

    private static final String[] llllllI;
    private static String[] lllllll;

    public static void sendMessage(String s) {
        if (s != null) {
            EntityPlayerSP entityplayersp = Game.Player();
            String s1 = ChatUtils.llllllI[0];
            Object[] aobject = new Object[2];
            boolean flag = false;
            StringBuilder stringbuilder = (new StringBuilder()).append(EnumChatFormatting.DARK_AQUA);

            aobject[0] = String.valueOf(stringbuilder.append(ChatUtils.llllllI[1]).append(EnumChatFormatting.GRAY).append(ChatUtils.llllllI[2]));
            aobject[1] = s;
            entityplayersp.addChatMessage(new ChatComponentText(String.valueOf((new StringBuilder()).append(EnumChatFormatting.DARK_GREEN).append(ChatUtils.llllllI[3]).append(EnumChatFormatting.AQUA).append(ChatUtils.llllllI[4]).append(EnumChatFormatting.DARK_GREEN).append(ChatUtils.llllllI[5]).append(EnumChatFormatting.GRAY).append(s))));
        }
    }

    static {
        llIlIIIll();
        llIlIIIlI();
    }

    private static void llIlIIIlI() {
        llllllI = new String[6];
        ChatUtils.llllllI[0] = llIlIIIII(ChatUtils.lllllll[0], ChatUtils.lllllll[1]);
        ChatUtils.llllllI[1] = llIlIIIII(ChatUtils.lllllll[2], ChatUtils.lllllll[3]);
        ChatUtils.llllllI[2] = llIlIIIII(ChatUtils.lllllll[4], ChatUtils.lllllll[5]);
        ChatUtils.llllllI[3] = llIlIIIIl(ChatUtils.lllllll[6], ChatUtils.lllllll[7]);
        ChatUtils.llllllI[4] = llIlIIIIl(ChatUtils.lllllll[8], ChatUtils.lllllll[9]);
        ChatUtils.llllllI[5] = llIlIIIIl(ChatUtils.lllllll[10], ChatUtils.lllllll[11]);
        ChatUtils.lllllll = null;
    }

    private static void llIlIIIll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ChatUtils.lllllll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIlIIIII(String s, String s1) {
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

    private static String llIlIIIIl(String s, String s1) {
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
