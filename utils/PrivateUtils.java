package me.explicit.utils;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.player.FastPlace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.Timer;

public class PrivateUtils {

    private static final String[] llIllIII;
    private static String[] llIllIlI;

    public static Timer timer() {
        try {
            Class oclass = Minecraft.class;
            Field field = oclass.getDeclaredField(new String(new char[] { 't', 'i', 'm', 'e', 'r'}));

            field.setAccessible(true);
            return (Timer) field.get(Game.Minecraft());
        } catch (Exception exception) {
            try {
                Class oclass1 = Minecraft.class;
                Field field1 = oclass1.getDeclaredField(new String(new char[] { 'f', 'i', 'e', 'l', 'd', '_', '7', '1', '4', '2', '8', '_', 'T'}));

                field1.setAccessible(true);
                return (Timer) field1.get(Game.Minecraft());
            } catch (Exception exception1) {
                return null;
            }
        }
    }

    public static void setRightClickDelayTimer(int i) {
        FastPlace.placeDelay = i;

        try {
            Field field = Minecraft.class.getDeclaredField(PrivateUtils.llIllIII[0]);

            field.setAccessible(true);
            field.set(Game.Minecraft(), Integer.valueOf(i));
        } catch (Exception exception) {
            try {
                Field field1 = Minecraft.class.getDeclaredField(PrivateUtils.llIllIII[1]);

                field1.setAccessible(true);
                field1.set(Game.Minecraft(), Integer.valueOf(i));
            } catch (Exception exception1) {
                ;
            }
        }

    }

    public static void setBlockDamage(float f) {
        try {
            Field field = PlayerControllerMP.class.getDeclaredField(PrivateUtils.llIllIII[2]);

            field.setAccessible(true);
            field.set(Game.Minecraft().playerController, Float.valueOf(f));
        } catch (Exception exception) {
            try {
                Field field1 = PlayerControllerMP.class.getDeclaredField(PrivateUtils.llIllIII[3]);

                field1.setAccessible(true);
                field1.set(Game.Minecraft().playerController, Float.valueOf(f));
            } catch (Exception exception1) {
                ;
            }
        }

    }

    public static void setBlockHitDelay(int i) {
        try {
            Field field = PlayerControllerMP.class.getDeclaredField(PrivateUtils.llIllIII[4]);

            field.setAccessible(true);
            field.set(Game.Minecraft().playerController, Integer.valueOf(i));
        } catch (Exception exception) {
            try {
                Field field1 = PlayerControllerMP.class.getDeclaredField(PrivateUtils.llIllIII[5]);

                field1.setAccessible(true);
                field1.set(Game.Minecraft().playerController, Integer.valueOf(i));
            } catch (Exception exception1) {
                ;
            }
        }

    }

    public static float getBlockDamage() {
        try {
            Class oclass = PlayerControllerMP.class;
            Field field = oclass.getDeclaredField(PrivateUtils.llIllIII[6]);

            field.setAccessible(true);
            return ((Float) field.get(Game.Minecraft().playerController)).floatValue();
        } catch (Exception exception) {
            try {
                Class oclass1 = PlayerControllerMP.class;
                Field field1 = oclass1.getDeclaredField(PrivateUtils.llIllIII[7]);

                field1.setAccessible(true);
                return ((Float) field1.get(Game.Minecraft().playerController)).floatValue();
            } catch (Exception exception1) {
                return -1.0F;
            }
        }
    }

    static {
        llIIlIIlIl();
        llIIlIIIll();
    }

    private static void llIIlIIIll() {
        llIllIII = new String[8];
        PrivateUtils.llIllIII[0] = llIIlIIIII(PrivateUtils.llIllIlI[0], PrivateUtils.llIllIlI[1]);
        PrivateUtils.llIllIII[1] = llIIlIIIIl(PrivateUtils.llIllIlI[2], PrivateUtils.llIllIlI[3]);
        PrivateUtils.llIllIII[2] = llIIlIIIIl(PrivateUtils.llIllIlI[4], PrivateUtils.llIllIlI[5]);
        PrivateUtils.llIllIII[3] = llIIlIIIII(PrivateUtils.llIllIlI[6], PrivateUtils.llIllIlI[7]);
        PrivateUtils.llIllIII[4] = llIIlIIIII(PrivateUtils.llIllIlI[8], PrivateUtils.llIllIlI[9]);
        PrivateUtils.llIllIII[5] = llIIlIIIII(PrivateUtils.llIllIlI[10], PrivateUtils.llIllIlI[11]);
        PrivateUtils.llIllIII[6] = llIIlIIIIl(PrivateUtils.llIllIlI[12], PrivateUtils.llIllIlI[13]);
        PrivateUtils.llIllIII[7] = llIIlIIIlI(PrivateUtils.llIllIlI[14], PrivateUtils.llIllIlI[15]);
        PrivateUtils.llIllIlI = null;
    }

    private static void llIIlIIlIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        PrivateUtils.llIllIlI = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIIlIIIlI(String s, String s1) {
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

    private static String llIIlIIIIl(String s, String s1) {
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

    private static String llIIlIIIII(String s, String s1) {
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
