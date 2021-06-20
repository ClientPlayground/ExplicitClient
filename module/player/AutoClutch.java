package me.explicit.module.player;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.utils.PrivateUtils;
import me.explicit.utils.RotationUtils;
import me.explicit.utils.TimerUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class AutoClutch extends Module {

    public static List badBlocks;
    private int distanceToTheGround = 5;
    private TimerUtils timer = new TimerUtils();
    private BlockData blockBelowData;
    private BlockPos lastBlockPos;
    private double fallStartY = 0.0D;
    private static final String[] lIlllIII;
    private static String[] lIlllIIl;

    public AutoClutch() {
        super(AutoClutch.lIlllIII[0], 0, Category.PLAYER, AutoClutch.lIlllIII[1]);
    }

    public void onTick() {
        if (!Module.mc.thePlayer.onGround && Module.mc.thePlayer.motionY > 0.0D && Module.mc.gameSettings.keyBindJump.isKeyDown() && this.lastBlockPos != null && (double) this.lastBlockPos.getX() == Math.floor(Module.mc.thePlayer.posX) && (double) this.lastBlockPos.getZ() == Math.floor(Module.mc.thePlayer.posZ)) {
            BlockPos blockpos = new BlockPos(Module.mc.thePlayer.posX, Module.mc.thePlayer.posY - 1.0D, Module.mc.thePlayer.posZ);
            IBlockState iblockstate = Module.mc.theWorld.getBlockState(blockpos);

            if (iblockstate.getBlock() == Blocks.air && this.timer.delay(100.0F)) {
                this.blockBelowData = this.getBlockData(blockpos);
                if (this.blockBelowData != null) {
                    float[] afloat = RotationUtils.getRotationsBlock(this.blockBelowData.position, this.blockBelowData.face);

                    AutoClutch.mc.thePlayer.rotationYaw = afloat[0];
                    AutoClutch.mc.thePlayer.rotationPitch = afloat[1];
                }
            }
        } else if (!Module.mc.thePlayer.onGround && Module.mc.thePlayer.motionY < 0.0D) {
            if (this.fallStartY < Module.mc.thePlayer.posY) {
                this.fallStartY = Module.mc.thePlayer.posY;
            }

            if (this.fallStartY - Module.mc.thePlayer.posY > 2.0D) {
                double d0 = Module.mc.thePlayer.posX;
                double d1 = Module.mc.thePlayer.posY - 1.5D;
                double d2 = Module.mc.thePlayer.posZ;
                BlockPos blockpos1 = new BlockPos(d0, d1, d2);
                IBlockState iblockstate1 = Module.mc.theWorld.getBlockState(blockpos1);
                IBlockState iblockstate2 = Module.mc.theWorld.getBlockState(new BlockPos(d0, d1 - 1.0D, d2));

                if (!iblockstate2.getBlock().isFullCube() && !Module.mc.thePlayer.isSneaking() && (iblockstate1.getBlock() == Blocks.air || iblockstate1.getBlock() == Blocks.snow_layer || iblockstate1.getBlock() == Blocks.tallgrass) && this.timer.delay(100.0F)) {
                    this.timer.reset();
                    this.lastBlockPos = blockpos1;
                    this.blockBelowData = this.getBlockData(blockpos1);
                    if (this.blockBelowData != null) {
                        float[] afloat1 = RotationUtils.getRotationsBlock(this.blockBelowData.position, this.blockBelowData.face);

                        AutoClutch.mc.thePlayer.rotationYaw = afloat1[0];
                        AutoClutch.mc.thePlayer.rotationPitch = afloat1[1];
                    }
                }
            } else {
                this.blockBelowData = null;
            }
        } else {
            this.fallStartY = 0.0D;
        }

        this.blockBelowData = null;
        if (this.blockBelowData != null) {
            for (int i = 36; i < 45; ++i) {
                if (Module.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                    ItemStack itemstack = Module.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                    Item item = itemstack.getItem();

                    if (item instanceof ItemBlock && !AutoClutch.badBlocks.contains(((ItemBlock) item).getBlock()) && !((ItemBlock) item).getBlock().getLocalizedName().toLowerCase().contains(AutoClutch.lIlllIII[2])) {
                        PrivateUtils.setRightClickDelayTimer(0);
                        int j = Module.mc.thePlayer.inventory.currentItem;

                        Module.mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(i - 36));
                        Module.mc.thePlayer.inventory.currentItem = i - 36;
                        Module.mc.playerController.updateController();

                        try {
                            if (Module.mc.playerController.onPlayerRightClick(Module.mc.thePlayer, Module.mc.theWorld, Module.mc.thePlayer.inventory.getCurrentItem(), this.blockBelowData.position, this.blockBelowData.face, new Vec3((double) this.blockBelowData.position.getX(), (double) this.blockBelowData.position.getY(), (double) this.blockBelowData.position.getZ()))) {
                                Module.mc.thePlayer.swingItem();
                            }
                        } catch (Exception exception) {
                            ;
                        }

                        this.blockBelowData = null;
                        Module.mc.thePlayer.inventory.currentItem = j;
                        Module.mc.playerController.updateController();
                        return;
                    }
                }
            }
        }

    }

    private BlockData getBlockData(BlockPos blockpos) {
        return !AutoClutch.badBlocks.contains(Module.mc.theWorld.getBlockState(blockpos.add(-1, 0, 0)).getBlock()) ? new BlockData(blockpos.add(-1, 0, 0), EnumFacing.EAST) : (!AutoClutch.badBlocks.contains(Module.mc.theWorld.getBlockState(blockpos.add(1, 0, 0)).getBlock()) ? new BlockData(blockpos.add(1, 0, 0), EnumFacing.WEST) : (!AutoClutch.badBlocks.contains(Module.mc.theWorld.getBlockState(blockpos.add(0, 0, -1)).getBlock()) ? new BlockData(blockpos.add(0, 0, -1), EnumFacing.SOUTH) : (!AutoClutch.badBlocks.contains(Module.mc.theWorld.getBlockState(blockpos.add(0, 0, 1)).getBlock()) ? new BlockData(blockpos.add(0, 0, 1), EnumFacing.NORTH) : null)));
    }

    static {
        lIlIlIIIlI();
        lIlIlIIIIl();
        AutoClutch.badBlocks = Arrays.asList(new Block[] { Blocks.air, Blocks.water, Blocks.flowing_water, Blocks.lava, Blocks.flowing_lava, Blocks.enchanting_table, Blocks.carpet, Blocks.glass_pane, Blocks.stained_glass_pane, Blocks.iron_bars, Blocks.snow_layer, Blocks.ice, Blocks.packed_ice, Blocks.coal_ore, Blocks.diamond_ore, Blocks.emerald_ore, Blocks.chest, Blocks.torch, Blocks.anvil, Blocks.trapped_chest, Blocks.noteblock, Blocks.jukebox, Blocks.tnt, Blocks.gold_ore, Blocks.iron_ore, Blocks.lapis_ore, Blocks.lit_redstone_ore, Blocks.quartz_ore, Blocks.redstone_ore, Blocks.wooden_pressure_plate, Blocks.stone_pressure_plate, Blocks.light_weighted_pressure_plate, Blocks.heavy_weighted_pressure_plate, Blocks.stone_button, Blocks.wooden_button, Blocks.lever});
    }

    private static void lIlIlIIIIl() {
        lIlllIII = new String[3];
        AutoClutch.lIlllIII[0] = lIlIIlllll(AutoClutch.lIlllIIl[0], AutoClutch.lIlllIIl[1]);
        AutoClutch.lIlllIII[1] = lIlIIlllll(AutoClutch.lIlllIIl[2], AutoClutch.lIlllIIl[3]);
        AutoClutch.lIlllIII[2] = lIlIlIIIII(AutoClutch.lIlllIIl[4], AutoClutch.lIlllIIl[5]);
        AutoClutch.lIlllIIl = null;
    }

    private static void lIlIlIIIlI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        AutoClutch.lIlllIIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIlIlIIIII(String s, String s1) {
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

    private static String lIlIIlllll(String s, String s1) {
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
