package me.explicit.module.combat;

import io.netty.util.internal.ThreadLocalRandom;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.Setting;
import me.explicit.utils.CombatUtils;
import me.explicit.utils.Game;
import me.explicit.utils.RotationUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;

public class RodAimbot extends Module {

    private Entity target;
    private static final String[] lIIIlllIl;
    private static String[] lIIIlllll;

    public RodAimbot() {
        super(RodAimbot.lIIIlllIl[0], 0, Category.COMBAT, RodAimbot.lIIIlllIl[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(RodAimbot.lIIIlllIl[2], this, 30.0D, 3.0D, 250.0D, true));
        Explicit.instance.sm.rSetting(new Setting(RodAimbot.lIIIlllIl[3], this, 30.0D, 3.0D, 250.0D, true));
        Explicit.instance.sm.rSetting(new Setting(RodAimbot.lIIIlllIl[4], this, 8.0D, 1.0D, 17.5D, false));
        Explicit.instance.sm.rSetting(new Setting(RodAimbot.lIIIlllIl[5], this, 100.0D, 1.0D, 360.0D, true));
    }

    public void onUpdate() {
        if (Game.Player() != null && Game.World() != null) {
            if (this.target == null || this.target != null && !this.isValid(this.target)) {
                this.target = this.getTarget();
            }

            if (this.target != null) {
                this.Aim();
            }
        }

    }

    private void Aim() {
        if (this.target != null) {
            int i = Explicit.instance.sm.getSettingByName(this, RodAimbot.lIIIlllIl[6]).getValInt();
            int j = Explicit.instance.sm.getSettingByName(this, RodAimbot.lIIIlllIl[7]).getValInt();
            Entity entity = this.getTarget();

            if (entity != null && (this.getY(entity) > 1.0D || this.getY(entity) < -1.0D)) {
                boolean flag = this.getY(entity) > 0.0D;
                EntityPlayerSP entityplayersp = Game.Player();

                entityplayersp.rotationYaw += (float) (flag ? -(Math.abs(this.getY(entity)) / (double) i) : Math.abs(this.getY(entity)) / (double) i);
                float[] afloat = RotationUtils.getRotations(entity);

                if (this.target == null) {
                    return;
                }

                float f = afloat[1] - Game.Player().getDistanceToEntity(this.target) + Game.Player().getDistanceToEntity(this.target) / 4.0F - Game.Player().rotationPitch;

                entityplayersp.rotationPitch = (float) ((double) entityplayersp.rotationPitch + (double) f / ((double) j + ThreadLocalRandom.current().nextDouble()));
            }

        }
    }

    public Entity getTarget() {
        Entity entity = null;
        int i = (int) Explicit.instance.sm.getSettingByName(this, RodAimbot.lIIIlllIl[8]).getValDouble();

        if (Game.World().loadedEntityList != null && Game.Player().getCurrentEquippedItem() != null && Game.Player().getCurrentEquippedItem().getItem().getUnlocalizedName().toLowerCase().contains(RodAimbot.lIIIlllIl[9])) {
            List list = Game.World().loadedEntityList;

            for (int j = 0; j < list.size(); ++j) {
                Entity entity1 = (Entity) list.get(j);

                if (this.isValid(entity1) && this.can(entity1, (float) i)) {
                    entity = entity1;
                    i = (int) this.getY(entity1);
                }
            }

            return entity;
        } else {
            return null;
        }
    }

    private boolean isValid(Entity entity) {
        return RotationUtils.canEntityBeSeen(entity) && entity.isEntityAlive() && entity != Game.Player() && (double) Game.Player().getDistanceToEntity(entity) <= Explicit.instance.sm.getSettingByName(this, RodAimbot.lIIIlllIl[10]).getValDouble() && CombatUtils.canTarget(entity, true);
    }

    public boolean can(Entity entity, float f) {
        f = (float) ((double) f * 0.5D);
        double d0 = ((double) (Game.Player().rotationYaw - this.yaw(entity)) % 360.0D + 540.0D) % 360.0D - 180.0D;

        return d0 > 0.0D && d0 < (double) f || (double) (-f) < d0 && d0 < 0.0D;
    }

    public float yaw(Entity entity) {
        double d0 = entity.posX - Game.Player().posX;
        double d1 = entity.posY - Game.Player().posY;
        double d2 = entity.posZ - Game.Player().posZ;
        double d3 = Math.atan2(d0, d2) * 57.2957795D;

        d3 = -d3;
        double d4 = Math.asin(d1 / Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 57.2957795D;

        d4 = -d4;
        return (float) d3;
    }

    public double getY(Entity entity) {
        return ((double) (Game.Player().rotationYaw - this.yaw(entity)) % 360.0D + 540.0D) % 360.0D - 180.0D;
    }

    static {
        llllllIIII();
        lllllIllll();
    }

    private static void lllllIllll() {
        lIIIlllIl = new String[11];
        RodAimbot.lIIIlllIl[0] = lllllIllII(RodAimbot.lIIIlllll[0], RodAimbot.lIIIlllll[1]);
        RodAimbot.lIIIlllIl[1] = lllllIllIl(RodAimbot.lIIIlllll[2], RodAimbot.lIIIlllll[3]);
        RodAimbot.lIIIlllIl[2] = lllllIllII(RodAimbot.lIIIlllll[4], RodAimbot.lIIIlllll[5]);
        RodAimbot.lIIIlllIl[3] = lllllIlllI(RodAimbot.lIIIlllll[6], RodAimbot.lIIIlllll[7]);
        RodAimbot.lIIIlllIl[4] = lllllIllII(RodAimbot.lIIIlllll[8], RodAimbot.lIIIlllll[9]);
        RodAimbot.lIIIlllIl[5] = lllllIllII(RodAimbot.lIIIlllll[10], RodAimbot.lIIIlllll[11]);
        RodAimbot.lIIIlllIl[6] = lllllIllIl(RodAimbot.lIIIlllll[12], RodAimbot.lIIIlllll[13]);
        RodAimbot.lIIIlllIl[7] = lllllIllIl(RodAimbot.lIIIlllll[14], RodAimbot.lIIIlllll[15]);
        RodAimbot.lIIIlllIl[8] = lllllIllII(RodAimbot.lIIIlllll[16], RodAimbot.lIIIlllll[17]);
        RodAimbot.lIIIlllIl[9] = lllllIllIl(RodAimbot.lIIIlllll[18], RodAimbot.lIIIlllll[19]);
        RodAimbot.lIIIlllIl[10] = lllllIllIl(RodAimbot.lIIIlllll[20], RodAimbot.lIIIlllll[21]);
        RodAimbot.lIIIlllll = null;
    }

    private static void llllllIIII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        RodAimbot.lIIIlllll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lllllIlllI(String s, String s1) {
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

    private static String lllllIllII(String s, String s1) {
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

    private static String lllllIllIl(String s, String s1) {
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
