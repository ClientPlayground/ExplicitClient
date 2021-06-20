package me.explicit.module.blatant;

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
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;

public class BowAimbot extends Module {

    private Entity target;
    private static final String[] lIIIlIlI;
    private static String[] lIIIllII;

    public BowAimbot() {
        super(BowAimbot.lIIIlIlI[0], 0, Category.BLATANT, BowAimbot.lIIIlIlI[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(BowAimbot.lIIIlIlI[2], this, 10.0D, 0.1D, 20.0D, false));
    }

    public void onTick() {
        if (!BowAimbot.mc.gameSettings.keyBindUseItem.isKeyDown()) {
            this.target = null;
        } else {
            ItemStack itemstack = Game.Player().inventory.getCurrentItem();

            if (itemstack != null && itemstack.getItem() instanceof ItemBow) {
                if (this.target == null || !CombatUtils.canTarget(this.target, true) || !RotationUtils.canEntityBeSeen(this.target) || !this.target.isEntityAlive()) {
                    this.target = this.getBestEntity();
                }

                if (this.target != null) {
                    try {
                        float[] afloat = faceBow(this.target, false, true, (float) Explicit.instance.sm.getSettingByName(this, BowAimbot.lIIIlIlI[3]).getValDouble());

                        if (afloat.length < 2 || afloat[0] == Float.POSITIVE_INFINITY || afloat[0] == Float.NEGATIVE_INFINITY || afloat[1] == Float.POSITIVE_INFINITY || afloat[1] == Float.NEGATIVE_INFINITY) {
                            return;
                        }

                        float f;

                        for (f = afloat[0]; f > 360.0F; f -= 360.0F) {
                            ;
                        }

                        BowAimbot.mc.thePlayer.rotationYaw = f;
                        float f1 = afloat[1];

                        if (!Double.isNaN((double) f1)) {
                            BowAimbot.mc.thePlayer.rotationPitch = afloat[1];
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        BowAimbot.mc.thePlayer.rotationYaw = 0.0F;
                        BowAimbot.mc.thePlayer.rotationPitch = 0.0F;
                    }

                }
            } else {
                this.target = null;
            }
        }
    }

    public Entity getBestEntity() {
        Entity entity = null;
        float f = Float.POSITIVE_INFINITY;
        Iterator iterator = Game.World().loadedEntityList.iterator();

        while (iterator.hasNext()) {
            Entity entity1 = (Entity) iterator.next();

            if (entity1 != null && (!(Game.Player().getCurrentEquippedItem().getItem() instanceof ItemFishingRod) || Game.Player().getDistanceToEntity(entity1) <= 30.0F) && CombatUtils.canTarget(entity1, true) && BowAimbot.mc.thePlayer.canEntityBeSeen(entity1) && BowAimbot.mc.thePlayer.canEntityBeSeen(entity1) && Game.Player().getDistanceToEntity(entity1) <= 75.0F) {
                new Vec3(0.5D, 1.0D, 0.5D);
                float[] afloat = RotationUtils.getRotations(entity1);
                float f1 = BowAimbot.mc.thePlayer.rotationYaw - afloat[0];
                float f2 = BowAimbot.mc.thePlayer.rotationPitch - afloat[1];
                float f3 = (float) Math.sqrt((double) (f1 * f1 + f2 * f2));

                if (f3 < f) {
                    entity = entity1;
                    f = f3;
                }
            }
        }

        return entity;
    }

    public static float[] faceBow(Entity entity, boolean flag, boolean flag1, float f) {
        EntityPlayerSP entityplayersp = BowAimbot.mc.thePlayer;
        double d0 = entity.posX + (flag1 ? (entity.posX - entity.prevPosX) * (double) f : 0.0D) - (entityplayersp.posX + (flag1 ? entityplayersp.posX - entityplayersp.prevPosX : 0.0D));
        double d1 = entity.getEntityBoundingBox().minY + (flag1 ? (entity.getEntityBoundingBox().minY - entity.prevPosY) * (double) f : 0.0D) + (double) entity.getEyeHeight() - 0.15D - (entityplayersp.getEntityBoundingBox().minY + (flag1 ? entityplayersp.posY - entityplayersp.prevPosY : 0.0D)) - (double) entityplayersp.getEyeHeight();
        double d2 = entity.posZ + (flag1 ? (entity.posZ - entity.prevPosZ) * (double) f : 0.0D) - (entityplayersp.posZ + (flag1 ? entityplayersp.posZ - entityplayersp.prevPosZ : 0.0D));
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        float f1 = (float) entityplayersp.getItemInUseDuration() / 20.0F;

        f1 = (f1 * f1 + f1 * 2.0F) / 3.0F;
        if (f1 > 1.0F) {
            f1 = 1.0F;
        }

        float f2 = (float) (Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
        float f3 = (float) (-Math.toDegrees(Math.atan(((double) (f1 * f1) - Math.sqrt((double) (f1 * f1 * f1 * f1) - 0.006000000052154064D * (0.006000000052154064D * d3 * d3 + 2.0D * d1 * (double) (f1 * f1)))) / (0.006000000052154064D * d3))));

        return new float[] { f2, f3};
    }

    static {
        llllIIlll();
        llllIIllI();
    }

    private static void llllIIllI() {
        lIIIlIlI = new String[4];
        BowAimbot.lIIIlIlI[0] = lllIlllll(BowAimbot.lIIIllII[0], BowAimbot.lIIIllII[1]);
        BowAimbot.lIIIlIlI[1] = llllIIIII(BowAimbot.lIIIllII[2], BowAimbot.lIIIllII[3]);
        BowAimbot.lIIIlIlI[2] = llllIIIIl(BowAimbot.lIIIllII[4], BowAimbot.lIIIllII[5]);
        BowAimbot.lIIIlIlI[3] = lllIlllll(BowAimbot.lIIIllII[6], BowAimbot.lIIIllII[7]);
        BowAimbot.lIIIllII = null;
    }

    private static void llllIIlll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        BowAimbot.lIIIllII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llllIIIII(String s, String s1) {
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

    private static String lllIlllll(String s, String s1) {
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

    private static String llllIIIIl(String s, String s1) {
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
