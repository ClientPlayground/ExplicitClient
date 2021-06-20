package me.explicit.module.combat;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.Setting;
import me.explicit.utils.CombatUtils;
import me.explicit.utils.Game;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Reach extends Module {

    float ab;
    float bb;
    boolean cb;
    boolean d;
    int timeout;
    int hits;
    boolean misplace;
    public Random e = new Random();
    private List Players;
    private List oldPos;
    private List currentPos;
    private static final String[] I;
    private static String[] llI;

    public Reach() {
        super(Reach.I[0], 0, Category.COMBAT, Reach.I[1]);
    }

    public void onEnable() {
        super.onEnable();
        if (this.Players == null) {
            (this.Players = new ArrayList()).clear();
        } else {
            this.Players.clear();
        }

        if (this.oldPos == null) {
            (this.oldPos = new ArrayList()).clear();
        } else {
            this.oldPos.clear();
        }

        if (this.currentPos == null) {
            (this.currentPos = new ArrayList()).clear();
        } else {
            this.currentPos.clear();
        }

    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(Reach.I[2], this, 3.0D, 3.0D, 6.0D, false));
        Explicit.instance.sm.rSetting(new Setting(Reach.I[3], this, 3.8D, 3.0D, 6.0D, false));
        Explicit.instance.sm.rSetting(new Setting(Reach.I[4], this, 0.0D, 0.0D, 20.0D, true));
        Explicit.instance.sm.rSetting(new Setting(Reach.I[5], this, false));
        Explicit.instance.sm.rSetting(new Setting(Reach.I[6], this, false));
        Explicit.instance.sm.rSetting(new Setting(Reach.I[7], this, true));
        Explicit.instance.sm.rSetting(new Setting(Reach.I[8], this, false));
    }

    public void onUpdateNoToggle() {
        this.misplace = Explicit.instance.sm.getSettingByName(this, Reach.I[9]).getValBoolean();
        if (this.misplace) {
            Explicit.instance.sm.getSettingByName(this, Reach.I[10]).setVisible(false);
            Explicit.instance.sm.getSettingByName(this, Reach.I[11]).setVisible(false);
            Explicit.instance.sm.getSettingByName(this, Reach.I[12]).setVisible(false);
            Explicit.instance.sm.getSettingByName(this, Reach.I[13]).setVisible(false);
        } else {
            Explicit.instance.sm.getSettingByName(this, Reach.I[14]).setVisible(true);
            Explicit.instance.sm.getSettingByName(this, Reach.I[15]).setVisible(true);
            Explicit.instance.sm.getSettingByName(this, Reach.I[16]).setVisible(true);
            Explicit.instance.sm.getSettingByName(this, Reach.I[17]).setVisible(true);
        }

    }

    @SubscribeEvent
    public void mous(MouseEvent mouseevent) {
        if (!Explicit.destructed) {
            this.ab = (float) Explicit.instance.sm.getSettingByName(this, Reach.I[18]).getValDouble();
            this.bb = (float) Explicit.instance.sm.getSettingByName(this, Reach.I[19]).getValDouble();
            this.cb = Explicit.instance.sm.getSettingByName(this, Reach.I[20]).getValBoolean();
            this.timeout = (int) Explicit.instance.sm.getSettingByName(this, Reach.I[21]).getValDouble();
            this.misplace = Explicit.instance.sm.getSettingByName(this, Reach.I[22]).getValBoolean();
            this.d = false;
            if (this.canReach()) {
                if (Reach.mc.objectMouseOver != null) {
                    BlockPos blockpos = Reach.mc.objectMouseOver.getBlockPos();

                    if (blockpos != null && Game.World().getBlockState(blockpos).getBlock() != Blocks.air) {
                        return;
                    }
                }

                double d0 = (double) this.ab + this.e.nextDouble() * (double) (this.bb - this.ab);
                Object[] aobject = add(d0, 0.0D, 0.0F);

                if (aobject != null) {
                    Reach.mc.objectMouseOver = new MovingObjectPosition((Entity) aobject[0], (Vec3) aobject[1]);
                    Reach.mc.pointedEntity = (Entity) aobject[0];
                }
            }
        }
    }

    public boolean canReach() {
        boolean flag = Explicit.instance.sm.getSettingByName(this, Reach.I[23]).getValBoolean();
        boolean flag1 = Explicit.instance.sm.getSettingByName(this, Reach.I[24]).getValBoolean();

        if (flag1 && !Game.Player().onGround) {
            return false;
        } else if (flag && !Game.Player().isSprinting()) {
            return false;
        } else if (this.misplace) {
            return false;
        } else {
            if (this.cb) {
                if (Game.Player().getCurrentEquippedItem() == null) {
                    return false;
                }

                if (!(Game.Player().getCurrentEquippedItem().getItem() instanceof ItemSword) && !(Game.Player().getCurrentEquippedItem().getItem() instanceof ItemAxe)) {
                    return false;
                }
            }

            return this.hits >= this.timeout;
        }
    }

    @SubscribeEvent
    public void onAttack(LivingAttackEvent livingattackevent) {
        if (livingattackevent.entityLiving != null && livingattackevent.source.getEntity() != null && livingattackevent.source.getEntity() == Reach.mc.thePlayer && livingattackevent.ammount > 0.0F && CombatUtils.canTarget(livingattackevent.entityLiving, true)) {
            if (Reach.mc.thePlayer.getDistanceToEntity(livingattackevent.entityLiving) >= Math.min(this.ab, this.bb)) {
                if (this.hits < this.timeout) {
                    ++this.hits;
                } else if (this.hits >= this.timeout) {
                    this.hits = 0;
                }
            } else {
                ++this.hits;
            }
        }

    }

    public static Object[] add(double d0, double d1, float f) {
        Entity entity = Reach.mc.getRenderViewEntity();
        Entity entity1 = null;

        if (entity != null && Game.World() != null) {
            Reach.mc.mcProfiler.startSection(Reach.I[25]);
            Vec3 vec3 = entity.getPositionEyes(0.0F);
            Vec3 vec31 = entity.getLook(0.0F);
            Vec3 vec32 = vec3.addVector(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0);
            Vec3 vec33 = null;
            float f1 = 1.0F;
            List list = Game.World().getEntitiesWithinAABBExcludingEntity(entity, entity.getEntityBoundingBox().addCoord(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0).expand(1.0D, 1.0D, 1.0D));
            double d2 = d0;

            for (int i = 0; i < list.size(); ++i) {
                Entity entity2 = (Entity) list.get(i);

                if (entity2.canBeCollidedWith()) {
                    float f2 = entity2.getCollisionBorderSize();
                    AxisAlignedBB axisalignedbb = entity2.getEntityBoundingBox().expand((double) f2, (double) f2, (double) f2);

                    axisalignedbb = axisalignedbb.expand(d1, d1, d1);
                    MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);

                    if (axisalignedbb.isVecInside(vec3)) {
                        if (0.0D < d2 || d2 == 0.0D) {
                            entity1 = entity2;
                            vec33 = movingobjectposition == null ? vec3 : movingobjectposition.hitVec;
                            d2 = 0.0D;
                        }
                    } else if (movingobjectposition != null) {
                        double d3 = vec3.distanceTo(movingobjectposition.hitVec);

                        if (d3 < d2 || d2 == 0.0D) {
                            boolean flag = false;

                            if (entity2 == entity.ridingEntity) {
                                if (d2 == 0.0D) {
                                    entity1 = entity2;
                                    vec33 = movingobjectposition.hitVec;
                                }
                            } else {
                                entity1 = entity2;
                                vec33 = movingobjectposition.hitVec;
                                d2 = d3;
                            }
                        }
                    }
                }
            }

            if (d2 < d0 && !CombatUtils.canTarget(entity1, true)) {
                entity1 = null;
            }

            Reach.mc.mcProfiler.endSection();
            if (entity1 != null && vec33 != null) {
                return new Object[] { entity1, vec33};
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    static {
        llIl();
        llII();
    }

    private static void llII() {
        I = new String[26];
        Reach.I[0] = I(Reach.llI[0], Reach.llI[1]);
        Reach.I[1] = llI(Reach.llI[2], Reach.llI[3]);
        Reach.I[2] = llI(Reach.llI[4], Reach.llI[5]);
        Reach.I[3] = llI(Reach.llI[6], Reach.llI[7]);
        Reach.I[4] = lIIl(Reach.llI[8], Reach.llI[9]);
        Reach.I[5] = llI(Reach.llI[10], Reach.llI[11]);
        Reach.I[6] = I(Reach.llI[12], Reach.llI[13]);
        Reach.I[7] = lIIl(Reach.llI[14], Reach.llI[15]);
        Reach.I[8] = I(Reach.llI[16], Reach.llI[17]);
        Reach.I[9] = lIIl(Reach.llI[18], Reach.llI[19]);
        Reach.I[10] = llI(Reach.llI[20], Reach.llI[21]);
        Reach.I[11] = I(Reach.llI[22], Reach.llI[23]);
        Reach.I[12] = lIIl(Reach.llI[24], Reach.llI[25]);
        Reach.I[13] = lIIl(Reach.llI[26], Reach.llI[27]);
        Reach.I[14] = lIIl(Reach.llI[28], Reach.llI[29]);
        Reach.I[15] = llI(Reach.llI[30], Reach.llI[31]);
        Reach.I[16] = lIIl("7CKcMYlNmS2KtQI/rEz6sg==", "Uquao");
        Reach.I[17] = lIIl("GX767rndmuidhTU84C/S8Q==", "Tzmdv");
        Reach.I[18] = lIIl("k3tjs2kCTMrdIZowHtF0WA==", "jyITs");
        Reach.I[19] = lIIl("m2MXLwEdzkpUjN6e4CjE1g==", "ykzKL");
        Reach.I[20] = I("EAo0ICUpIDs8Mw==", "GoUPJ");
        Reach.I[21] = lIIl("Rb+99LXJzHf2iq+8K47RUA==", "XwHQR");
        Reach.I[22] = lIIl("0N+dOi4p2EEuNZ2iZhbwdA==", "ehVVV");
        Reach.I[23] = I("HiUwGwM5GiweFA==", "MUBrm");
        Reach.I[24] = I("ITc2BCgCCjcdPw==", "fEYqF");
        Reach.I[25] = I("FxMCDw==", "gzadP");
        Reach.llI = null;
    }

    private static void llIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Reach.llI = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String I(String s, String s1) {
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

    private static String llI(String s, String s1) {
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

    private static String lIIl(String s, String s1) {
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
