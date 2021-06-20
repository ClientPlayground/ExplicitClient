package me.explicit.module.combat;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.Setting;
import me.explicit.utils.CombatUtils;
import me.explicit.utils.Game;
import me.explicit.utils.RotationUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

public class HitBoxes extends Module {

    private Entity pointedEntity;
    private MovingObjectPosition moving;
    public static float hitBoxMultiplier;
    private static final String[] llIIllI;
    private static String[] llIIlll;

    public HitBoxes() {
        super(HitBoxes.llIIllI[0], 0, Category.COMBAT, HitBoxes.llIIllI[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(HitBoxes.llIIllI[2], this, 30.0D, 1.0D, 180.0D, true));
    }

    @SubscribeEvent
    public void mouse(MouseEvent mouseevent) {
        if (this.moving != null && mouseevent.button == 0 && (HitBoxes.mc.objectMouseOver.getBlockPos() == null || Game.World().isAirBlock(HitBoxes.mc.objectMouseOver.getBlockPos()))) {
            Module.mc.objectMouseOver = this.moving;
        }

    }

    public void onClick() {}

    @SubscribeEvent
    public void tick(RenderTickEvent rendertickevent) {
        if (Game.World() != null) {
            HitBoxes.hitBoxMultiplier = (float) Explicit.instance.sm.getSettingByName(this, HitBoxes.llIIllI[3]).getValDouble();
        }

        this.getMouseOver(1.0F);
    }

    public void onUpdate() {
        if (Game.World() != null) {
            HitBoxes.hitBoxMultiplier = (float) Explicit.instance.sm.getSettingByName(this, HitBoxes.llIIllI[4]).getValDouble();
        }

        this.getMouseOver(1.0F);
    }

    private void getMouseOver(float f) {
        if (HitBoxes.mc.getRenderViewEntity() != null && Game.World() != null) {
            HitBoxes.mc.pointedEntity = null;
            double d0 = 3.0D;
            double d1;

            if (Explicit.instance.mm.getModuleByName(HitBoxes.llIIllI[5]).isToggled()) {
                d1 = Explicit.instance.sm.getSettingByName(Explicit.instance.mm.getModuleByName(HitBoxes.llIIllI[6]), HitBoxes.llIIllI[7]).getValDouble();
                double d2 = Explicit.instance.sm.getSettingByName(Explicit.instance.mm.getModuleByName(HitBoxes.llIIllI[8]), HitBoxes.llIIllI[9]).getValDouble();

                if (d1 <= d2 && d1 != d2) {
                    d0 = ThreadLocalRandom.current().nextDouble(d1, d2);
                } else {
                    d0 = d1;
                }
            }

            this.moving = HitBoxes.mc.getRenderViewEntity().rayTrace(d0, f);
            d1 = d0;
            Vec3 vec3 = HitBoxes.mc.getRenderViewEntity().getPositionEyes(f);

            if (this.moving != null) {
                d1 = this.moving.hitVec.distanceTo(vec3);
            }

            Vec3 vec31 = HitBoxes.mc.getRenderViewEntity().getLook(f);
            Vec3 vec32 = vec3.addVector(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0);

            this.pointedEntity = null;
            Vec3 vec33 = null;
            float f1 = 1.0F;
            List list = Game.World().getEntitiesWithinAABBExcludingEntity(HitBoxes.mc.getRenderViewEntity(), HitBoxes.mc.getRenderViewEntity().getEntityBoundingBox().addCoord(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0).expand((double) f1, (double) f1, (double) f1));
            double d3 = d1;

            for (int i = 0; i < list.size(); ++i) {
                Entity entity = (Entity) list.get(i);

                if (entity.canBeCollidedWith() && CombatUtils.canTarget(entity, true) && RotationUtils.canEntityBeSeen(entity)) {
                    float f2 = 0.13F * HitBoxes.hitBoxMultiplier;
                    AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand((double) f2, (double) f2, (double) f2);
                    MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);

                    if (axisalignedbb.isVecInside(vec3)) {
                        if (0.0D < d3 || d3 == 0.0D) {
                            this.pointedEntity = entity;
                            vec33 = movingobjectposition == null ? vec3 : movingobjectposition.hitVec;
                            d3 = 0.0D;
                        }
                    } else if (movingobjectposition != null) {
                        double d4 = vec3.distanceTo(movingobjectposition.hitVec);

                        if (d4 < d3 || d3 == 0.0D) {
                            if (entity == HitBoxes.mc.getRenderViewEntity().ridingEntity && !entity.canRiderInteract()) {
                                if (d3 == 0.0D) {
                                    this.pointedEntity = entity;
                                    vec33 = movingobjectposition.hitVec;
                                }
                            } else {
                                this.pointedEntity = entity;
                                vec33 = movingobjectposition.hitVec;
                                d3 = d4;
                            }
                        }
                    }
                }
            }

            if (this.pointedEntity != null && (d3 < d1 || this.moving == null)) {
                this.moving = new MovingObjectPosition(this.pointedEntity, vec33);
                if (CombatUtils.canTarget(this.pointedEntity, true) || this.pointedEntity instanceof EntityItemFrame) {
                    HitBoxes.mc.pointedEntity = this.pointedEntity;
                }
            }
        }

    }

    static {
        lIllIIlll();
        lIllIIllI();
        HitBoxes.hitBoxMultiplier = 1.0F;
    }

    private static void lIllIIllI() {
        llIIllI = new String[10];
        HitBoxes.llIIllI[0] = lIllIIIII(HitBoxes.llIIlll[0], HitBoxes.llIIlll[1]);
        HitBoxes.llIIllI[1] = lIllIIlII(HitBoxes.llIIlll[2], HitBoxes.llIIlll[3]);
        HitBoxes.llIIllI[2] = lIllIIlII(HitBoxes.llIIlll[4], HitBoxes.llIIlll[5]);
        HitBoxes.llIIllI[3] = lIllIIlIl(HitBoxes.llIIlll[6], HitBoxes.llIIlll[7]);
        HitBoxes.llIIllI[4] = lIllIIlII(HitBoxes.llIIlll[8], HitBoxes.llIIlll[9]);
        HitBoxes.llIIllI[5] = lIllIIlII(HitBoxes.llIIlll[10], HitBoxes.llIIlll[11]);
        HitBoxes.llIIllI[6] = lIllIIlII(HitBoxes.llIIlll[12], HitBoxes.llIIlll[13]);
        HitBoxes.llIIllI[7] = lIllIIlII(HitBoxes.llIIlll[14], HitBoxes.llIIlll[15]);
        HitBoxes.llIIllI[8] = lIllIIlIl(HitBoxes.llIIlll[16], HitBoxes.llIIlll[17]);
        HitBoxes.llIIllI[9] = lIllIIlIl(HitBoxes.llIIlll[18], HitBoxes.llIIlll[19]);
        HitBoxes.llIIlll = null;
    }

    private static void lIllIIlll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        HitBoxes.llIIlll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIllIIlIl(String s, String s1) {
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

    private static String lIllIIIII(String s, String s1) {
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

    private static String lIllIIlII(String s, String s1) {
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
