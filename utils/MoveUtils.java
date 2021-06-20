package me.explicit.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import net.minecraft.potion.Potion;

public class MoveUtils {

    private static final String[] lIllIII;
    private static String[] lIllIIl;

    public static boolean PlayerMoving() {
        return Game.Player().movementInput.moveForward != 0.0F || Game.Player().movementInput.moveStrafe != 0.0F;
    }

    public static void setMoveSpeed(double d0) {
        double d1 = (double) Game.Player().movementInput.moveForward;
        double d2 = (double) Game.Player().movementInput.moveStrafe;
        double d3 = (double) Game.Player().rotationYaw;

        if (d1 != 0.0D || d2 != 0.0D) {
            if (d1 != 0.0D) {
                if (d2 > 0.0D) {
                    d3 += d1 > 0.0D ? -45.0D : 45.0D;
                } else if (d2 < 0.0D) {
                    d3 += d1 > 0.0D ? 45.0D : -45.0D;
                }

                d2 = 0.0D;
                if (d1 > 0.0D) {
                    d1 = 1.0D;
                } else if (d1 < 0.0D) {
                    d1 = -1.0D;
                }
            }

            double d4 = d1 * d0 * Math.cos(Math.toRadians(d3 + 88.0D)) + d2 * d0 * Math.sin(Math.toRadians(d3 + 87.9000015258789D));
            double d5 = d1 * d0 * Math.sin(Math.toRadians(d3 + 88.0D)) - d2 * d0 * Math.cos(Math.toRadians(d3 + 87.9000015258789D));

            if (Explicit.instance.mm.getModuleByName(MoveUtils.lIllIII[0]).isToggled()) {
                Game.Player().motionX = d4 / 1.5D;
                Game.Player().motionZ = d5 / 1.5D;
            } else {
                Game.Player().motionX = d4 / 1.25D;
                Game.Player().motionZ = d5 / 1.25D;
            }
        }

    }

    public static double getBaseMovementSpeed() {
        double d0 = 0.2873D;

        if (Game.Player().isPotionActive(Potion.moveSpeed)) {
            int i = Game.Player().getActivePotionEffect(Potion.moveSpeed).getAmplifier();

            d0 *= 1.0D + 0.2D * (double) (i + 1);
        }

        return d0;
    }

    public static float getSpeed() {
        float f = (float) Math.sqrt(Game.Player().motionX * Game.Player().motionX + Game.Player().motionZ * Game.Player().motionZ);

        return f;
    }

    public static boolean isOnGround(double d0) {
        return !Game.World().getCollidingBoundingBoxes(Game.Player(), Game.Player().getEntityBoundingBox().offset(0.0D, -d0, 0.0D)).isEmpty();
    }

    public static int getJumpEffect() {
        return Game.Player().isPotionActive(Potion.jump) ? Game.Player().getActivePotionEffect(Potion.jump).getAmplifier() + 1 : 0;
    }

    public static int getSpeedEffect() {
        return Game.Player().isPotionActive(Potion.moveSpeed) ? Game.Player().getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1 : 0;
    }

    public static float getDirection() {
        float f = Game.Player().rotationYaw;

        if (Game.Player().moveForward < 0.0F) {
            f += 180.0F;
        }

        float f1 = 1.0F;

        if (Game.Player().moveForward < 0.0F) {
            f1 = -0.5F;
        } else if (Game.Player().moveForward > 0.0F) {
            f1 = 0.5F;
        } else {
            f1 = 1.0F;
        }

        if (Game.Player().moveStrafing > 0.0F) {
            f -= 90.0F * f1;
        }

        if (Game.Player().moveStrafing < 0.0F) {
            f += 90.0F * f1;
        }

        f *= 0.017453292F;
        return f;
    }

    static {
        lIIlIlllI();
        lIIlIllIl();
    }

    private static void lIIlIllIl() {
        lIllIII = new String[1];
        MoveUtils.lIllIII[0] = lIIlIllII(MoveUtils.lIllIIl[0], MoveUtils.lIllIIl[1]);
        MoveUtils.lIllIIl = null;
    }

    private static void lIIlIlllI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        MoveUtils.lIllIIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIlIllII(String s, String s1) {
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
