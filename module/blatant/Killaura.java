package me.explicit.module.blatant;

import io.netty.util.internal.ThreadLocalRandom;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
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
import me.explicit.utils.TimerUtils;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;

public class Killaura extends Module {

    private TimerUtils switchTimer = new TimerUtils();
    private TimerUtils attackTimer = new TimerUtils();
    public EntityLivingBase target;
    private List targets = new ArrayList();
    private boolean isBlocking;
    private boolean canBlock;
    private int currentIndex;
    public static boolean isAttackTick;
    public static boolean isRotationTick;
    private String mode;
    private String rotationsmode;
    private double range;
    private double blockrange;
    private int hitchance;
    private int switchdelay;
    private double aps;
    public boolean autoblock;
    private boolean raytrace;
    private static final String[] llIlllI;
    private static String[] lllIIII;

    public Killaura() {
        super(Killaura.llIlllI[0], 0, Category.BLATANT, Killaura.llIlllI[1]);
    }

    public void setup() {
        ArrayList arraylist = new ArrayList();

        arraylist.add(Killaura.llIlllI[2]);
        arraylist.add(Killaura.llIlllI[3]);
        arraylist.add(Killaura.llIlllI[4]);
        Explicit.instance.sm.rSetting(new Setting(Killaura.llIlllI[5], this, Killaura.llIlllI[6], arraylist));
        ArrayList arraylist1 = new ArrayList();

        arraylist1.add(Killaura.llIlllI[7]);
        arraylist1.add(Killaura.llIlllI[8]);
        arraylist1.add(Killaura.llIlllI[9]);
        arraylist1.add(Killaura.llIlllI[10]);
        Explicit.instance.sm.rSetting(new Setting(Killaura.llIlllI[11], this, Killaura.llIlllI[12], arraylist1));
        Explicit.instance.sm.rSetting(new Setting(Killaura.llIlllI[13], this, 4.2D, 1.0D, 6.0D, false));
        Explicit.instance.sm.rSetting(new Setting(Killaura.llIlllI[14], this, 5.0D, 1.0D, 10.0D, false));
        Explicit.instance.sm.rSetting(new Setting(Killaura.llIlllI[15], this, 100.0D, 1.0D, 100.0D, true));
        Explicit.instance.sm.rSetting(new Setting(Killaura.llIlllI[16], this, 200.0D, 1.0D, 1000.0D, true));
        Explicit.instance.sm.rSetting(new Setting(Killaura.llIlllI[17], this, 10.0D, 2.0D, 20.0D, false));
        Explicit.instance.sm.rSetting(new Setting(Killaura.llIlllI[18], this, true));
        Explicit.instance.sm.rSetting(new Setting(Killaura.llIlllI[19], this, false));
        Explicit.instance.sm.rSetting(new Setting(Killaura.llIlllI[20], this, false));
        Explicit.instance.sm.rSetting(new Setting(Killaura.llIlllI[21], this, false));
        Explicit.instance.sm.rSetting(new Setting(Killaura.llIlllI[22], this, false));
        Explicit.instance.sm.rSetting(new Setting(Killaura.llIlllI[23], this, false));
    }

    public void onTick() {
        Killaura.isRotationTick = false;
        this.updateSettings();
        this.setTargets();
        if (Game.Player() != null && Game.World() != null) {
            if (!Explicit.instance.sm.getSettingByName(this, Killaura.llIlllI[24]).getValBoolean() || Killaura.mc.gameSettings.keyBindAttack.isKeyDown()) {
                this.isBlocking = Game.Player().isUsingItem() && Killaura.mc.thePlayer.getCurrentEquippedItem() != null && Killaura.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword;
                if (this.target != null) {
                    this.rotate();
                    double d0 = (double) Killaura.mc.thePlayer.getDistanceToEntity(this.target);

                    if (d0 <= this.blockrange && d0 <= this.range && this.autoblock && Killaura.mc.thePlayer.getCurrentEquippedItem() != null && Killaura.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword) {
                        this.canBlock = true;
                    } else {
                        this.canBlock = false;
                    }

                    if (this.canBlock && !this.isBlocking) {
                        this.block();
                    }

                    if (d0 <= this.range && this.attackTimer.hasReached(1000.0D / this.aps)) {
                        Killaura.mc.thePlayer.swingItem();
                        if (ThreadLocalRandom.current().nextInt(0, 100) <= this.hitchance && (Killaura.mc.objectMouseOver.entityHit != null && (Killaura.mc.objectMouseOver.entityHit == this.target || this.mode.equalsIgnoreCase(Killaura.llIlllI[25])) || !this.raytrace) && this.canHit()) {
                            if (this.isBlocking) {
                                this.unBlock();
                            }

                            Killaura.isAttackTick = true;
                            this.attack();
                            if (this.canBlock) {
                                this.block();
                            }
                        }

                        this.attackTimer.reset();
                    } else {
                        Killaura.isAttackTick = false;
                    }
                } else if (this.isBlocking) {
                    this.unBlock();
                }

            }
        }
    }

    private boolean canHit() {
        boolean flag = Explicit.instance.sm.getSettingByName(this, Killaura.llIlllI[26]).getValBoolean();
        boolean flag1 = Explicit.instance.sm.getSettingByName(this, Killaura.llIlllI[27]).getValBoolean();

        if (flag || flag1) {
            if (Game.Player().getCurrentEquippedItem() == null) {
                return false;
            }

            if (flag && !flag1 && !(Game.Player().getCurrentEquippedItem().getItem() instanceof ItemSword)) {
                return false;
            }

            if (!flag && flag1 && !(Game.Player().getCurrentEquippedItem().getItem() instanceof ItemAxe)) {
                return false;
            }

            if (flag && flag1 && !(Game.Player().getCurrentEquippedItem().getItem() instanceof ItemAxe) && !(Game.Player().getCurrentEquippedItem().getItem() instanceof ItemSword)) {
                return false;
            }
        }

        return true;
    }

    private void rotate() {
        if (this.canHit()) {
            float[] afloat = RotationUtils.getRotations(this.target);

            if (this.rotationsmode.equalsIgnoreCase(Killaura.llIlllI[28])) {
                Game.Player().rotationYaw = afloat[0];
                Game.Player().rotationPitch = afloat[1];
                Killaura.isRotationTick = true;
            } else {
                double d0;
                float f;
                float f1;
                double d1;

                if (this.rotationsmode.equalsIgnoreCase(Killaura.llIlllI[29])) {
                    d0 = ThreadLocalRandom.current().nextDouble(7.5D, 10.0D);
                    f = RotationUtils.getYawChange(Game.Player().prevRotationYaw, this.target.posX + ThreadLocalRandom.current().nextDouble(-1.0D, 1.0D) * 0.05D, this.target.posZ + ThreadLocalRandom.current().nextDouble(-1.0D, 1.0D) * 0.05D);
                    f1 = (float) ((double) f / d0);
                    Game.Player().rotationYaw = Game.Player().prevRotationYaw + f1;
                    d1 = (double) (afloat[1] - Game.Player().rotationPitch);
                    Game.Player().rotationPitch = (float) ((double) Game.Player().rotationPitch + d1 / d0);
                    Killaura.isRotationTick = true;
                } else if (this.rotationsmode.equalsIgnoreCase(Killaura.llIlllI[30])) {
                    d0 = ThreadLocalRandom.current().nextDouble(15.0D, 20.0D);
                    f = RotationUtils.getYawChange(Game.Player().prevRotationYaw, this.target.posX + ThreadLocalRandom.current().nextDouble(-1.0D, 1.0D) * 0.05D, this.target.posZ + ThreadLocalRandom.current().nextDouble(-1.0D, 1.0D) * 0.05D);
                    f1 = (float) ((double) f / d0);
                    Game.Player().rotationYaw = Game.Player().prevRotationYaw + f1;
                    d1 = (double) (afloat[1] - Game.Player().rotationPitch);
                    Game.Player().rotationPitch = (float) ((double) Game.Player().rotationPitch + d1 / d0);
                    Killaura.isRotationTick = true;
                }
            }
        }

        if (Killaura.mc.thePlayer.rotationPitch > 90.0F) {
            Killaura.mc.thePlayer.rotationPitch = 90.0F;
        } else if (Killaura.mc.thePlayer.rotationPitch < -90.0F) {
            Killaura.mc.thePlayer.rotationPitch = -90.0F;
        }

    }

    public void onEnable() {
        this.targets.clear();
        super.onEnable();
    }

    public void onDisable() {
        if (this.isBlocking) {
            this.unBlock();
        }

        Killaura.isAttackTick = false;
        Killaura.isRotationTick = false;
        super.onDisable();
    }

    private void setTargets() {
        this.targets = this.getTargets();
        if (!this.targets.isEmpty() && (this.target == null || this.targets.contains(this.target))) {
            if (!this.mode.equalsIgnoreCase(Killaura.llIlllI[31]) && this.switchTimer.hasReached((double) this.switchdelay) && (!this.mode.equalsIgnoreCase(Killaura.llIlllI[32]) || this.targets.size() != 1)) {
                if (this.mode.equalsIgnoreCase(Killaura.llIlllI[33])) {
                    if (this.target != null && this.isValid(this.target) && this.targets.size() == 1) {
                        return;
                    }

                    if (this.target == null) {
                        this.target = (EntityLivingBase) this.targets.get(0);
                    } else if (this.targets.size() > 1) {
                        int i = this.targets.size() - 1;

                        if (this.currentIndex >= i) {
                            this.currentIndex = 0;
                        } else {
                            ++this.currentIndex;
                        }

                        if (this.targets.get(this.currentIndex) != null && this.targets.get(this.currentIndex) != this.target) {
                            this.target = (EntityLivingBase) this.targets.get(this.currentIndex);
                            this.switchTimer.reset();
                        }
                    } else {
                        this.target = null;
                    }
                } else if (this.mode.equalsIgnoreCase(Killaura.llIlllI[34])) {
                    if (this.targets.isEmpty()) {
                        this.target = null;
                    } else if (this.target != null && !this.targets.contains(this.target) || this.target == null) {
                        this.target = (EntityLivingBase) this.targets.get(ThreadLocalRandom.current().nextInt(this.targets.size() - 1));
                    }
                }
            } else {
                if (this.target != null && this.isValid(this.target)) {
                    return;
                }

                this.target = (EntityLivingBase) this.targets.get(0);
            }

        } else {
            this.target = null;
        }
    }

    private int getTargetInt() {
        for (int i = 0; i < this.targets.size(); ++i) {
            if (this.targets.get(i) == this.target) {
                return i;
            }
        }

        return -1;
    }

    private List getTargets() {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = Killaura.mc.theWorld.loadedEntityList.iterator();

        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();

            if (this.isValid(entity)) {
                arraylist.add((EntityLivingBase) entity);
            }
        }

        return arraylist;
    }

    private boolean isValid(Entity entity) {
        return entity instanceof EntityLivingBase && entity != Killaura.mc.thePlayer && entity.isEntityAlive() && !(entity instanceof EntityArmorStand) && CombatUtils.canTarget(entity, true) ? (double) Killaura.mc.thePlayer.getDistanceToEntity(entity) <= Math.max(this.range, this.blockrange) : false;
    }

    private boolean attack() {
        if (this.target == null) {
            return false;
        } else {
            if (this.mode.equalsIgnoreCase(Killaura.llIlllI[35])) {
                Iterator iterator = this.targets.iterator();

                while (iterator.hasNext()) {
                    EntityLivingBase entitylivingbase = (EntityLivingBase) iterator.next();

                    Killaura.mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(entitylivingbase, Action.ATTACK));
                }
            } else {
                Killaura.mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(this.target, Action.ATTACK));
            }

            return true;
        }
    }

    private void unBlock() {
        KeyBinding.setKeyBindState(Killaura.mc.gameSettings.keyBindUseItem.getKeyCode(), false);
        Module.mc.playerController.onStoppedUsingItem(Module.mc.thePlayer);
    }

    private void block() {
        KeyBinding.setKeyBindState(Killaura.mc.gameSettings.keyBindUseItem.getKeyCode(), true);
        if (Module.mc.playerController.sendUseItem(Module.mc.thePlayer, Module.mc.theWorld, Module.mc.thePlayer.inventory.getCurrentItem())) {
            Module.mc.getItemRenderer().resetEquippedProgress2();
        }

    }

    private void updateSettings() {
        this.mode = Explicit.instance.sm.getSettingByName(this, Killaura.llIlllI[36]).getValString();
        this.rotationsmode = Explicit.instance.sm.getSettingByName(this, Killaura.llIlllI[37]).getValString();
        this.range = Explicit.instance.sm.getSettingByName(this, Killaura.llIlllI[38]).getValDouble();
        this.blockrange = Explicit.instance.sm.getSettingByName(this, Killaura.llIlllI[39]).getValDouble();
        this.hitchance = Explicit.instance.sm.getSettingByName(this, Killaura.llIlllI[40]).getValInt();
        this.switchdelay = Explicit.instance.sm.getSettingByName(this, Killaura.llIlllI[41]).getValInt();
        boolean flag = Explicit.instance.sm.getSettingByName(this, Killaura.llIlllI[42]).getValBoolean();

        this.aps = Explicit.instance.sm.getSettingByName(this, Killaura.llIlllI[43]).getValDouble() + (flag ? ThreadLocalRandom.current().nextDouble(-1.0D, 1.0D) : 0.0D);
        this.autoblock = Explicit.instance.sm.getSettingByName(this, Killaura.llIlllI[44]).getValBoolean();
        this.raytrace = Explicit.instance.sm.getSettingByName(this, Killaura.llIlllI[45]).getValBoolean();
    }

    static {
        llIIIlllI();
        llIIIllIl();
        Killaura.isAttackTick = false;
        Killaura.isRotationTick = false;
    }

    private static void llIIIllIl() {
        llIlllI = new String[46];
        Killaura.llIlllI[0] = llIIIlIlI(Killaura.lllIIII[0], Killaura.lllIIII[1]);
        Killaura.llIlllI[1] = llIIIlIlI(Killaura.lllIIII[2], Killaura.lllIIII[3]);
        Killaura.llIlllI[2] = llIIIlIlI(Killaura.lllIIII[4], Killaura.lllIIII[5]);
        Killaura.llIlllI[3] = llIIIlIll(Killaura.lllIIII[6], Killaura.lllIIII[7]);
        Killaura.llIlllI[4] = llIIIlIlI(Killaura.lllIIII[8], Killaura.lllIIII[9]);
        Killaura.llIlllI[5] = llIIIlIll(Killaura.lllIIII[10], Killaura.lllIIII[11]);
        Killaura.llIlllI[6] = llIIIlIlI(Killaura.lllIIII[12], Killaura.lllIIII[13]);
        Killaura.llIlllI[7] = llIIIlIll(Killaura.lllIIII[14], Killaura.lllIIII[15]);
        Killaura.llIlllI[8] = llIIIlIll(Killaura.lllIIII[16], Killaura.lllIIII[17]);
        Killaura.llIlllI[9] = llIIIllII(Killaura.lllIIII[18], Killaura.lllIIII[19]);
        Killaura.llIlllI[10] = llIIIlIll(Killaura.lllIIII[20], Killaura.lllIIII[21]);
        Killaura.llIlllI[11] = llIIIlIll(Killaura.lllIIII[22], Killaura.lllIIII[23]);
        Killaura.llIlllI[12] = llIIIllII(Killaura.lllIIII[24], Killaura.lllIIII[25]);
        Killaura.llIlllI[13] = llIIIlIll(Killaura.lllIIII[26], Killaura.lllIIII[27]);
        Killaura.llIlllI[14] = llIIIlIlI(Killaura.lllIIII[28], Killaura.lllIIII[29]);
        Killaura.llIlllI[15] = llIIIlIll(Killaura.lllIIII[30], Killaura.lllIIII[31]);
        Killaura.llIlllI[16] = llIIIlIlI(Killaura.lllIIII[32], Killaura.lllIIII[33]);
        Killaura.llIlllI[17] = llIIIlIlI(Killaura.lllIIII[34], Killaura.lllIIII[35]);
        Killaura.llIlllI[18] = llIIIlIlI(Killaura.lllIIII[36], Killaura.lllIIII[37]);
        Killaura.llIlllI[19] = llIIIlIll(Killaura.lllIIII[38], Killaura.lllIIII[39]);
        Killaura.llIlllI[20] = llIIIlIll(Killaura.lllIIII[40], Killaura.lllIIII[41]);
        Killaura.llIlllI[21] = llIIIlIll(Killaura.lllIIII[42], Killaura.lllIIII[43]);
        Killaura.llIlllI[22] = llIIIlIlI("Oj0+PT4mJD02", "iJQOZ");
        Killaura.llIlllI[23] = llIIIlIlI("GRExJzc0EA==", "XiThY");
        Killaura.llIlllI[24] = llIIIlIlI("JAgCOCUIDQ==", "kfATL");
        Killaura.llIlllI[25] = llIIIllII("o0yTud7hBes=", "prWuJ");
        Killaura.llIlllI[26] = llIIIlIll("69CO0mwqQgUoWcSDtO8Q0Q==", "ypioN");
        Killaura.llIlllI[27] = llIIIlIll("Zf2YTt+REdc=", "wdCJJ");
        Killaura.llIlllI[28] = llIIIllII("6ZrrHCPMLHM=", "ZRTuG");
        Killaura.llIlllI[29] = llIIIlIlI("Gzo7PTEg", "HWTRE");
        Killaura.llIlllI[30] = llIIIlIll("CBOreD9l5Uk=", "dhExr");
        Killaura.llIlllI[31] = llIIIlIlI("NgssEjQg", "EbBuX");
        Killaura.llIlllI[32] = llIIIlIlI("LhA8Ji0=", "CePRD");
        Killaura.llIlllI[33] = llIIIllII("Ca/3t1tejHc=", "wPyjv");
        Killaura.llIlllI[34] = llIIIlIll("SSNtNNRACy4=", "UlMCU");
        Killaura.llIlllI[35] = llIIIlIll("6qncfI4F6Hg=", "ztFYC");
        Killaura.llIlllI[36] = llIIIlIll("sMjFbq6Rcpo=", "VfBfq");
        Killaura.llIlllI[37] = llIIIllII("WazPsQAZKOiX5Dge0LuYSg==", "NHYKw");
        Killaura.llIlllI[38] = llIIIllII("uZV+QGAB8EE=", "KtaWl");
        Killaura.llIlllI[39] = llIIIlIlI("MCgMLgggJQ0qBg==", "rDcMc");
        Killaura.llIlllI[40] = llIIIlIlI("BzkzGzguPiQ9", "OPGXP");
        Killaura.llIlllI[41] = llIIIlIll("vRPvvPv8CgCmT9jLSddO5Q==", "FZcNn");
        Killaura.llIlllI[42] = llIIIlIlI("FBspCSsrEz0IBRYp", "FzGmD");
        Killaura.llIlllI[43] = llIIIlIll("qbZX1j+xnxk=", "CieMk");
        Killaura.llIlllI[44] = llIIIlIll("j492mJHCP+moi0F1BKqR0g==", "jjopE");
        Killaura.llIlllI[45] = llIIIlIlI("ODkaFTkLOwY=", "jXcaK");
        Killaura.lllIIII = null;
    }

    private static void llIIIlllI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Killaura.lllIIII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIIIlIll(String s, String s1) {
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

    private static String llIIIllII(String s, String s1) {
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

    private static String llIIIlIlI(String s, String s1) {
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
