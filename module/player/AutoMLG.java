package me.explicit.module.player;

import io.netty.util.internal.ThreadLocalRandom;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.Setting;
import me.explicit.utils.Game;
import me.explicit.utils.PrivateUtils;
import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemBucket;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class AutoMLG extends Module {

    private int oldSlot;
    private boolean lastFell;
    private static final String[] lIIIIIlI;
    private static String[] lIIIIlII;

    public AutoMLG() {
        super(AutoMLG.lIIIIIlI[0], 0, Category.PLAYER, AutoMLG.lIIIIIlI[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(AutoMLG.lIIIIIlI[2], this, 4.0D, 3.0D, 10.0D, false));
        Explicit.instance.sm.rSetting(new Setting(AutoMLG.lIIIIIlI[3], this, false));
        Explicit.instance.sm.rSetting(new Setting(AutoMLG.lIIIIIlI[4], this, true));
        Explicit.instance.sm.rSetting(new Setting(AutoMLG.lIIIIIlI[5], this, false));
    }

    public void onUpdate() {
        final boolean flag = Explicit.instance.sm.getSettingByName(this, AutoMLG.lIIIIIlI[6]).getValBoolean();
        boolean flag1 = Explicit.instance.sm.getSettingByName(this, AutoMLG.lIIIIIlI[7]).getValBoolean();
        boolean flag2 = Explicit.instance.sm.getSettingByName(this, AutoMLG.lIIIIIlI[8]).getValBoolean();
        double d0 = Explicit.instance.sm.getSettingByName(this, AutoMLG.lIIIIIlI[9]).getValDouble();

        if ((double) Game.Player().fallDistance > d0 && this.isBlockUnderneath() && !AutoMLG.mc.thePlayer.onGround && this.getWaterBucketSlot() != -1) {
            if (flag1) {
                AutoMLG.mc.thePlayer.inventory.currentItem = this.getWaterBucketSlot();
            }

            if (this.isHoldingWaterBucket()) {
                if (flag) {
                    Game.Player().rotationPitch = 90.0F;
                }

                PrivateUtils.setRightClickDelayTimer(0);
                KeyBinding.setKeyBindState(AutoMLG.mc.gameSettings.keyBindUseItem.getKeyCode(), true);
                this.lastFell = true;
                if (flag2) {
                    Thread thread = new Thread(new Runnable() {
                        final boolean val$autoaim = flag;
                        final AutoMLG this$0 = AutoMLG.this;

                        public void run() {
                            try {
                                Thread.sleep(100L);
                            } catch (InterruptedException interruptedexception) {
                                interruptedexception.printStackTrace();
                            }

                            if (this.this$0.isHoldingEmptyBucket()) {
                                if (this.val$autoaim) {
                                    Game.Player().rotationPitch = 90.0F;
                                }

                                if (!AutoMLG.mc.gameSettings.keyBindUseItem.isKeyDown()) {
                                    KeyBinding.setKeyBindState(AutoMLG.mc.gameSettings.keyBindUseItem.getKeyCode(), true);
                                }

                                try {
                                    Thread.sleep(ThreadLocalRandom.current().nextLong(200L, 500L));
                                } catch (InterruptedException interruptedexception1) {
                                    interruptedexception1.printStackTrace();
                                }

                                KeyBinding.setKeyBindState(AutoMLG.mc.gameSettings.keyBindUseItem.getKeyCode(), false);
                            }

                        }
                    });

                    thread.start();
                }
            } else {
                this.StopMLG();
            }
        } else {
            this.StopMLG();
        }

    }

    private void StopMLG() {
        if (!this.lastFell) {
            this.oldSlot = AutoMLG.mc.thePlayer.inventory.currentItem;
        } else {
            AutoMLG.mc.thePlayer.inventory.currentItem = this.oldSlot;
            KeyBinding.setKeyBindState(AutoMLG.mc.gameSettings.keyBindUseItem.getKeyCode(), this.isKeyDown(AutoMLG.mc.gameSettings.keyBindUseItem.getKeyCode()));
            PrivateUtils.setRightClickDelayTimer(4);
        }

        this.lastFell = false;
    }

    private boolean isBlockUnderneath() {
        boolean flag = false;

        for (int i = 0; (double) i < Module.mc.thePlayer.posY + 2.0D; ++i) {
            BlockPos blockpos = new BlockPos(Module.mc.thePlayer.posX, (double) i, Module.mc.thePlayer.posZ);

            if (!(Module.mc.theWorld.getBlockState(blockpos).getBlock() instanceof BlockAir)) {
                flag = true;
            }
        }

        return flag;
    }

    private boolean isHoldingWaterBucket() {
        return Game.Player().getCurrentEquippedItem() != null && Game.Player().getCurrentEquippedItem().getItem().getUnlocalizedName().toLowerCase().contains(AutoMLG.lIIIIIlI[10]);
    }

    private boolean isHoldingEmptyBucket() {
        return Game.Player().getCurrentEquippedItem() != null && Game.Player().getCurrentEquippedItem().getItem() instanceof ItemBucket && !Game.Player().getCurrentEquippedItem().getItem().getUnlocalizedName().toLowerCase().contains(AutoMLG.lIIIIIlI[11]) && !Game.Player().getCurrentEquippedItem().getItem().getUnlocalizedName().toLowerCase().contains(AutoMLG.lIIIIIlI[12]) && !Game.Player().getCurrentEquippedItem().getItem().getUnlocalizedName().toLowerCase().contains(AutoMLG.lIIIIIlI[13]);
    }

    private int getWaterBucketSlot() {
        for (int i = 0; i < 9; ++i) {
            if (Game.Player().inventory.getStackInSlot(i) != null && (Game.Player().inventory.getStackInSlot(i).getItem() instanceof ItemBucket && !Game.Player().inventory.getStackInSlot(i).getItem().getUnlocalizedName().toLowerCase().contains(AutoMLG.lIIIIIlI[14]) && Game.Player().inventory.getStackInSlot(i).getItem().getUnlocalizedName().toLowerCase().contains(AutoMLG.lIIIIIlI[15]) || Game.Player().inventory.getStackInSlot(i).getItem().getUnlocalizedName().toLowerCase().contains(AutoMLG.lIIIIIlI[16]))) {
                return i;
            }
        }

        return -1;
    }

    private int getEmptyBucketSlot() {
        for (int i = 0; i < 9; ++i) {
            if (Game.Player().inventory.getStackInSlot(i) != null && Game.Player().inventory.getStackInSlot(i).getItem() instanceof ItemBucket && !Game.Player().inventory.getStackInSlot(i).getItem().getUnlocalizedName().toLowerCase().contains(AutoMLG.lIIIIIlI[17]) && !Game.Player().inventory.getStackInSlot(i).getItem().getUnlocalizedName().toLowerCase().contains(AutoMLG.lIIIIIlI[18]) && !Game.Player().inventory.getStackInSlot(i).getItem().getUnlocalizedName().toLowerCase().contains(AutoMLG.lIIIIIlI[19])) {
                return i;
            }
        }

        return -1;
    }

    private boolean isKeyDown(int i) {
        return i < 0 ? Mouse.isButtonDown(i + 100) : Keyboard.isKeyDown(i);
    }

    static boolean access$000(AutoMLG automlg) {
        return automlg.isHoldingEmptyBucket();
    }

    static Minecraft access$100() {
        return AutoMLG.mc;
    }

    static Minecraft access$200() {
        return AutoMLG.mc;
    }

    static Minecraft access$300() {
        return AutoMLG.mc;
    }

    static {
        lllIIIIll();
        lllIIIIlI();
    }

    private static void lllIIIIlI() {
        lIIIIIlI = new String[20];
        AutoMLG.lIIIIIlI[0] = llIllllIl(AutoMLG.lIIIIlII[0], AutoMLG.lIIIIlII[1]);
        AutoMLG.lIIIIIlI[1] = llIlllllI(AutoMLG.lIIIIlII[2], AutoMLG.lIIIIlII[3]);
        AutoMLG.lIIIIIlI[2] = llIlllllI(AutoMLG.lIIIIlII[4], AutoMLG.lIIIIlII[5]);
        AutoMLG.lIIIIIlI[3] = llIllllIl(AutoMLG.lIIIIlII[6], AutoMLG.lIIIIlII[7]);
        AutoMLG.lIIIIIlI[4] = llIllllll(AutoMLG.lIIIIlII[8], AutoMLG.lIIIIlII[9]);
        AutoMLG.lIIIIIlI[5] = llIllllIl(AutoMLG.lIIIIlII[10], AutoMLG.lIIIIlII[11]);
        AutoMLG.lIIIIIlI[6] = llIlllllI(AutoMLG.lIIIIlII[12], AutoMLG.lIIIIlII[13]);
        AutoMLG.lIIIIIlI[7] = llIlllllI(AutoMLG.lIIIIlII[14], AutoMLG.lIIIIlII[15]);
        AutoMLG.lIIIIIlI[8] = llIllllll(AutoMLG.lIIIIlII[16], AutoMLG.lIIIIlII[17]);
        AutoMLG.lIIIIIlI[9] = llIlllllI(AutoMLG.lIIIIlII[18], AutoMLG.lIIIIlII[19]);
        AutoMLG.lIIIIIlI[10] = llIllllll(AutoMLG.lIIIIlII[20], AutoMLG.lIIIIlII[21]);
        AutoMLG.lIIIIIlI[11] = llIlllllI(AutoMLG.lIIIIlII[22], AutoMLG.lIIIIlII[23]);
        AutoMLG.lIIIIIlI[12] = llIllllIl(AutoMLG.lIIIIlII[24], AutoMLG.lIIIIlII[25]);
        AutoMLG.lIIIIIlI[13] = llIllllll(AutoMLG.lIIIIlII[26], AutoMLG.lIIIIlII[27]);
        AutoMLG.lIIIIIlI[14] = llIllllIl(AutoMLG.lIIIIlII[28], AutoMLG.lIIIIlII[29]);
        AutoMLG.lIIIIIlI[15] = llIllllIl(AutoMLG.lIIIIlII[30], AutoMLG.lIIIIlII[31]);
        AutoMLG.lIIIIIlI[16] = llIllllll(AutoMLG.lIIIIlII[32], AutoMLG.lIIIIlII[33]);
        AutoMLG.lIIIIIlI[17] = llIllllll(AutoMLG.lIIIIlII[34], AutoMLG.lIIIIlII[35]);
        AutoMLG.lIIIIIlI[18] = llIllllIl(AutoMLG.lIIIIlII[36], AutoMLG.lIIIIlII[37]);
        AutoMLG.lIIIIIlI[19] = llIllllIl(AutoMLG.lIIIIlII[38], AutoMLG.lIIIIlII[39]);
        AutoMLG.lIIIIlII = null;
    }

    private static void lllIIIIll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        AutoMLG.lIIIIlII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIllllIl(String s, String s1) {
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

    private static String llIlllllI(String s, String s1) {
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

    private static String llIllllll(String s, String s1) {
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
