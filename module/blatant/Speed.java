package me.explicit.module.blatant;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.utils.Game;
import me.explicit.utils.MoveUtils;
import me.explicit.utils.PrivateUtils;
import me.explicit.utils.TimerUtils;

public class Speed extends Module {

    boolean collided = false;
    double stair;
    double less;
    boolean lessSlow;
    public TimerUtils timer = new TimerUtils();
    private TimerUtils lastCheck = new TimerUtils();
    public boolean shouldslow = false;
    private double movementSpeed;
    private int stage;
    private static final String[] lIIlII;
    private static String[] lIIlIl;

    public Speed() {
        super(Speed.lIIlII[0], 0, Category.BLATANT, Speed.lIIlII[1]);
    }

    public void onDisable() {
        super.onDisable();
        PrivateUtils.timer().timerSpeed = 1.0F;
    }

    public void onEnable() {
        super.onEnable();
        boolean flag = Game.Player() == null;

        this.collided = !flag && Game.Player().isCollidedHorizontally;
        this.less = 0.0D;
        this.lessSlow = false;
        if (Game.Player() != null) {
            this.movementSpeed = MoveUtils.getBaseMovementSpeed();
        }

        this.shouldslow = false;
    }

    private boolean canZoom() {
        return MoveUtils.PlayerMoving() && Game.Player().onGround;
    }

    public void onUpdate() {}

    public void onMove() {
        this.run();
    }

    public void run() {
        if (Game.Player().isCollidedHorizontally) {
            this.collided = true;
        }

        if (this.collided) {
            PrivateUtils.timer().timerSpeed = 1.0F;
            this.stage = -1;
        }

        if (this.stair > 0.0D) {
            this.stair -= 0.25D;
        }

        this.less -= this.less > 1.0D ? 0.12D : 0.11D;
        if (this.less < 0.0D) {
            this.less = 0.0D;
        }

        if (MoveUtils.isOnGround(0.01D) && MoveUtils.PlayerMoving()) {
            this.collided = Game.Player().isCollidedHorizontally;
            if (this.stage >= 0 || this.collided) {
                this.stage = 0;
                double d0 = 0.407D + (double) MoveUtils.getJumpEffect() * 0.1D;

                if (this.stair == 0.0D) {
                    Game.Player().jump();
                    Game.Player().motionY = d0;
                }

                ++this.less;
                if (this.less > 1.0D && !this.lessSlow) {
                    this.lessSlow = true;
                } else {
                    this.lessSlow = false;
                }

                if (this.less > 1.12D) {
                    this.less = 1.12D;
                }
            }
        }

        this.movementSpeed = this.getHypixelSpeed(this.stage) + 0.0331D;
        this.movementSpeed *= 0.91D;
        if (this.stair > 0.0D) {
            this.movementSpeed *= 0.7D - (double) MoveUtils.getSpeedEffect() * 0.1D;
        }

        if (this.stage < 0) {
            this.movementSpeed = MoveUtils.getBaseMovementSpeed();
        }

        if (this.lessSlow) {
            this.movementSpeed *= 0.95D;
        }

        if (Game.Player().moveForward != 0.0F || Game.Player().moveStrafing != 0.0F) {
            MoveUtils.setMoveSpeed(this.movementSpeed);
            ++this.stage;
        }

    }

    public void onTick() {}

    private double getHypixelSpeed(int i) {
        double d0 = MoveUtils.getBaseMovementSpeed() + 0.028D * (double) MoveUtils.getSpeedEffect() + (double) MoveUtils.getSpeedEffect() / 15.0D;
        double d1 = 0.4145D + (double) MoveUtils.getSpeedEffect() / 12.5D;
        double d2 = (double) i / 500.0D * 2.0D;

        if (i == 0) {
            if (this.timer.delay(300.0F)) {
                this.timer.reset();
            }

            if (!this.lastCheck.delay(500.0F)) {
                if (!this.shouldslow) {
                    this.shouldslow = true;
                }
            } else if (this.shouldslow) {
                this.shouldslow = false;
            }

            d0 = 0.64D + ((double) MoveUtils.getSpeedEffect() + 0.028D * (double) MoveUtils.getSpeedEffect()) * 0.134D;
        } else if (i == 1) {
            if (PrivateUtils.timer().timerSpeed == 1.354F) {
                ;
            }

            d0 = d1;
        } else if (i >= 2) {
            if (PrivateUtils.timer().timerSpeed == 1.254F) {
                ;
            }

            d0 = d1 - d2;
        }

        if (this.shouldslow || !this.lastCheck.delay(500.0F) || this.collided) {
            d0 = 0.2D;
            if (i == 0) {
                d0 = 0.0D;
            }
        }

        return Math.max(d0, this.shouldslow ? d0 : MoveUtils.getBaseMovementSpeed() + 0.028D * (double) MoveUtils.getSpeedEffect());
    }

    public void setSpeed(double d0) {
        Game.Player().motionX = -Math.sin((double) MoveUtils.getDirection()) * d0;
        Game.Player().motionZ = Math.cos((double) MoveUtils.getDirection()) * d0;
    }

    static {
        lllllIl();
        lllllII();
    }

    private static void lllllII() {
        lIIlII = new String[2];
        Speed.lIIlII[0] = llllIlI(Speed.lIIlIl[0], Speed.lIIlIl[1]);
        Speed.lIIlII[1] = llllIll(Speed.lIIlIl[2], Speed.lIIlIl[3]);
        Speed.lIIlIl = null;
    }

    private static void lllllIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Speed.lIIlIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llllIll(String s, String s1) {
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

    private static String llllIlI(String s, String s1) {
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
