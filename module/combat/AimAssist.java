package me.explicit.module.combat;

import io.netty.util.internal.ThreadLocalRandom;
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
import me.explicit.settings.Setting;
import me.explicit.utils.CombatUtils;
import me.explicit.utils.Game;
import me.explicit.utils.RotationUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemSword;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class AimAssist extends Module {

    private float a;
    private float b;
    private float c;
    private float h;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean i;
    private boolean j;
    private boolean k;
    private static final String[] lIlIIIIII;
    private static String[] lIlIIIIIl;

    public AimAssist() {
        super(AimAssist.lIlIIIIII[0], 0, Category.COMBAT, AimAssist.lIlIIIIII[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(AimAssist.lIlIIIIII[2], this, 50.0D, 10.0D, 250.0D, true));
        Explicit.instance.sm.rSetting(new Setting(AimAssist.lIlIIIIII[3], this, 50.0D, 10.0D, 250.0D, true));
        Explicit.instance.sm.rSetting(new Setting(AimAssist.lIlIIIIII[4], this, 90.0D, 15.0D, 360.0D, true));
        Explicit.instance.sm.rSetting(new Setting(AimAssist.lIlIIIIII[5], this, 4.2D, 1.0D, 10.0D, false));
        Explicit.instance.sm.rSetting(new Setting(AimAssist.lIlIIIIII[6], this, true));
        Explicit.instance.sm.rSetting(new Setting(AimAssist.lIlIIIIII[7], this, true));
        Explicit.instance.sm.rSetting(new Setting(AimAssist.lIlIIIIII[8], this, true));
        Explicit.instance.sm.rSetting(new Setting(AimAssist.lIlIIIIII[9], this, false));
        Explicit.instance.sm.rSetting(new Setting(AimAssist.lIlIIIIII[10], this, false));
        Explicit.instance.sm.rSetting(new Setting(AimAssist.lIlIIIIII[11], this, false));
    }

    public void onUpdateNoToggle() {
        this.a = (float) Explicit.instance.sm.getSettingByName(this, AimAssist.lIlIIIIII[12]).getValDouble();
        this.h = (float) Explicit.instance.sm.getSettingByName(this, AimAssist.lIlIIIIII[13]).getValDouble();
        this.b = (float) Explicit.instance.sm.getSettingByName(this, AimAssist.lIlIIIIII[14]).getValDouble();
        this.c = (float) Explicit.instance.sm.getSettingByName(this, AimAssist.lIlIIIIII[15]).getValDouble();
        this.d = Explicit.instance.sm.getSettingByName(this, AimAssist.lIlIIIIII[16]).getValBoolean();
        this.e = Explicit.instance.sm.getSettingByName(this, AimAssist.lIlIIIIII[17]).getValBoolean();
        this.g = Explicit.instance.sm.getSettingByName(this, AimAssist.lIlIIIIII[18]).getValBoolean();
        this.i = Explicit.instance.sm.getSettingByName(this, AimAssist.lIlIIIIII[19]).getValBoolean();
        this.j = Explicit.instance.sm.getSettingByName(this, AimAssist.lIlIIIIII[20]).getValBoolean();
        this.k = Explicit.instance.sm.getSettingByName(this, AimAssist.lIlIIIIII[21]).getValBoolean();
        Explicit.instance.sm.getSettingByName(this, AimAssist.lIlIIIIII[22]).setVisible(this.i && !this.g);
        Explicit.instance.sm.getSettingByName(this, AimAssist.lIlIIIIII[23]).setVisible(!this.g);
        Explicit.instance.sm.getSettingByName(this, AimAssist.lIlIIIIII[24]).setVisible(!this.g);
        Explicit.instance.sm.getSettingByName(this, AimAssist.lIlIIIIII[25]).setVisible(!this.g);
    }

    private boolean isKeyDown(int i) {
        return i < 0 ? Mouse.isButtonDown(i + 100) : Keyboard.isKeyDown(i);
    }

    public void onUpdate() {
        if (AimAssist.mc.currentScreen == null && (!this.j || !this.isKeyDown(AimAssist.mc.gameSettings.keyBindAttack.getKeyCode()) || AimAssist.mc.objectMouseOver == null || AimAssist.mc.objectMouseOver.getBlockPos() == null || AimAssist.mc.theWorld.isAirBlock(AimAssist.mc.objectMouseOver.getBlockPos()))) {
            if (Game.Player() != null && (!this.k || Game.Player().isSprinting())) {
                if (this.e) {
                    if (Game.Player().getCurrentEquippedItem() == null) {
                        return;
                    }

                    if (!(Game.Player().getCurrentEquippedItem().getItem() instanceof ItemSword) && !(Game.Player().getCurrentEquippedItem().getItem() instanceof ItemAxe)) {
                        return;
                    }
                }

                if (!this.d || Mouse.isButtonDown(0)) {
                    Entity entity = this.ent();

                    if (!this.g) {
                        if (entity != null && (getY(entity) > 1.0D || getY(entity) < -1.0D)) {
                            boolean flag = getY(entity) > 0.0D;
                            EntityPlayerSP entityplayersp = Game.Player();

                            entityplayersp.rotationYaw += (float) (flag ? -(Math.abs(getY(entity)) / (double) this.a) : Math.abs(getY(entity)) / (double) this.a);
                            float[] afloat = RotationUtils.getRotations(entity);

                            if (afloat[1] < -2.0F || afloat[1] > 2.0F && this.i) {
                                float f = afloat[1] - entityplayersp.rotationPitch;

                                entityplayersp.rotationPitch = (float) ((double) entityplayersp.rotationPitch + (double) f / ((double) this.h + ThreadLocalRandom.current().nextDouble()));
                            }
                        }
                    } else {
                        blatant(entity);
                    }

                }
            }
        }
    }

    public Entity ent() {
        Entity entity = null;
        int i = (int) this.b;
        Iterator iterator = Game.World().loadedEntityList.iterator();

        while (iterator.hasNext()) {
            Object object = iterator.next();
            Entity entity1 = (Entity) object;

            if (entity1.isEntityAlive() && entity1 != Game.Player() && Game.Player().getDistanceToEntity(entity1) <= this.c && CombatUtils.canTarget(entity1, true)) {
                if (!this.g) {
                    if (can(entity1, (float) i)) {
                        entity = entity1;
                        i = (int) getY(entity1);
                    }
                } else {
                    entity = entity1;
                    i = (int) getY(entity1);
                }
            }
        }

        return entity;
    }

    public static float yaw(Entity entity) {
        double d0 = entity.posX - Game.Player().posX;
        double d1 = entity.posY - Game.Player().posY;
        double d2 = entity.posZ - Game.Player().posZ;
        double d3 = Math.atan2(d0, d2) * 57.2957795D;

        d3 = -d3;
        double d4 = Math.asin(d1 / Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 57.2957795D;

        d4 = -d4;
        return (float) d3;
    }

    public static double getY(Entity entity) {
        return ((double) (Game.Player().rotationYaw - yaw(entity)) % 360.0D + 540.0D) % 360.0D - 180.0D;
    }

    public static boolean can(Entity entity, float f) {
        f = (float) ((double) f * 0.5D);
        double d0 = ((double) (Game.Player().rotationYaw - yaw(entity)) % 360.0D + 540.0D) % 360.0D - 180.0D;

        return d0 > 0.0D && d0 < (double) f || (double) (-f) < d0 && d0 < 0.0D;
    }

    public static float[] rots(Entity entity) {
        if (entity == null) {
            return null;
        } else {
            double d0 = entity.posX - Game.Player().posX;
            double d1;

            if (entity instanceof EntityLivingBase) {
                EntityLivingBase entitylivingbase = (EntityLivingBase) entity;

                d1 = entitylivingbase.posY + (double) entitylivingbase.getEyeHeight() * 0.9D - (Game.Player().posY + (double) Game.Player().getEyeHeight());
            } else {
                d1 = (entity.getEntityBoundingBox().minY + entity.getEntityBoundingBox().minY) / 2.0D - (Game.Player().posY + (double) Game.Player().getEyeHeight());
            }

            double d2 = entity.posZ - Game.Player().posZ;
            double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
            float f = (float) (Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
            float f1 = (float) (-(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D));

            return new float[] { Game.Player().rotationYaw + MathHelper.wrapAngleTo180_float(f - Game.Player().rotationYaw), Game.Player().rotationPitch + MathHelper.wrapAngleTo180_float(f1 - Game.Player().rotationPitch)};
        }
    }

    public static void blatant(Entity entity) {
        float[] afloat = rots(entity);

        if (afloat != null) {
            Game.Player().rotationYaw = afloat[0];
            Game.Player().rotationPitch = afloat[1] + 4.0F;
        }

    }

    static {
        lIIIllIIIII();
        lIIIlIlllll();
    }

    private static void lIIIlIlllll() {
        lIlIIIIII = new String[26];
        AimAssist.lIlIIIIII[0] = lIIIlIlllII(AimAssist.lIlIIIIIl[0], AimAssist.lIlIIIIIl[1]);
        AimAssist.lIlIIIIII[1] = lIIIlIlllII(AimAssist.lIlIIIIIl[2], AimAssist.lIlIIIIIl[3]);
        AimAssist.lIlIIIIII[2] = lIIIlIlllIl(AimAssist.lIlIIIIIl[4], AimAssist.lIlIIIIIl[5]);
        AimAssist.lIlIIIIII[3] = lIIIlIlllII(AimAssist.lIlIIIIIl[6], AimAssist.lIlIIIIIl[7]);
        AimAssist.lIlIIIIII[4] = lIIIlIlllIl(AimAssist.lIlIIIIIl[8], AimAssist.lIlIIIIIl[9]);
        AimAssist.lIlIIIIII[5] = lIIIlIlllIl(AimAssist.lIlIIIIIl[10], AimAssist.lIlIIIIIl[11]);
        AimAssist.lIlIIIIII[6] = lIIIlIllllI(AimAssist.lIlIIIIIl[12], AimAssist.lIlIIIIIl[13]);
        AimAssist.lIlIIIIII[7] = lIIIlIllllI(AimAssist.lIlIIIIIl[14], AimAssist.lIlIIIIIl[15]);
        AimAssist.lIlIIIIII[8] = lIIIlIlllII(AimAssist.lIlIIIIIl[16], AimAssist.lIlIIIIIl[17]);
        AimAssist.lIlIIIIII[9] = lIIIlIllllI(AimAssist.lIlIIIIIl[18], AimAssist.lIlIIIIIl[19]);
        AimAssist.lIlIIIIII[10] = lIIIlIlllII(AimAssist.lIlIIIIIl[20], AimAssist.lIlIIIIIl[21]);
        AimAssist.lIlIIIIII[11] = lIIIlIllllI(AimAssist.lIlIIIIIl[22], AimAssist.lIlIIIIIl[23]);
        AimAssist.lIlIIIIII[12] = lIIIlIlllIl(AimAssist.lIlIIIIIl[24], AimAssist.lIlIIIIIl[25]);
        AimAssist.lIlIIIIII[13] = lIIIlIlllII(AimAssist.lIlIIIIIl[26], AimAssist.lIlIIIIIl[27]);
        AimAssist.lIlIIIIII[14] = lIIIlIllllI(AimAssist.lIlIIIIIl[28], AimAssist.lIlIIIIIl[29]);
        AimAssist.lIlIIIIII[15] = lIIIlIlllIl(AimAssist.lIlIIIIIl[30], AimAssist.lIlIIIIIl[31]);
        AimAssist.lIlIIIIII[16] = lIIIlIlllIl(AimAssist.lIlIIIIIl[32], AimAssist.lIlIIIIIl[33]);
        AimAssist.lIlIIIIII[17] = lIIIlIlllII(AimAssist.lIlIIIIIl[34], AimAssist.lIlIIIIIl[35]);
        AimAssist.lIlIIIIII[18] = lIIIlIllllI(AimAssist.lIlIIIIIl[36], AimAssist.lIlIIIIIl[37]);
        AimAssist.lIlIIIIII[19] = lIIIlIllllI(AimAssist.lIlIIIIIl[38], AimAssist.lIlIIIIIl[39]);
        AimAssist.lIlIIIIII[20] = lIIIlIlllII(AimAssist.lIlIIIIIl[40], AimAssist.lIlIIIIIl[41]);
        AimAssist.lIlIIIIII[21] = lIIIlIllllI("GBMwKio/LCwvPQ==", "KcBCD");
        AimAssist.lIlIIIIII[22] = lIIIlIllllI("GjQpKT8o", "LgYLZ");
        AimAssist.lIlIIIIII[23] = lIIIlIlllII("XhjAnpGkac8=", "hHcnw");
        AimAssist.lIlIIIIII[24] = lIIIlIllllI("KSsV", "odCsH");
        AimAssist.lIlIIIIII[25] = lIIIlIlllII("WoY8A1eGVrCoa9715TqsDA==", "UvvtG");
        AimAssist.lIlIIIIIl = null;
    }

    private static void lIIIllIIIII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        AimAssist.lIlIIIIIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIlIlllIl(String s, String s1) {
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

    private static String lIIIlIllllI(String s, String s1) {
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

    private static String lIIIlIlllII(String s, String s1) {
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
