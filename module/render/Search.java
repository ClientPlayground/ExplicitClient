package me.explicit.module.render;

import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.Setting;
import me.explicit.utils.Game;
import me.explicit.utils.RenderUtils;
import me.explicit.utils.TimerUtils;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Search extends Module {

    public int r;
    public boolean iron;
    public boolean gold;
    public boolean diamond;
    public boolean emerald;
    public boolean lapis;
    public boolean redstone;
    public boolean coal;
    public boolean spawner;
    public List ores;
    public List blocksFound;
    private TimerUtils updateTimer = new TimerUtils();
    private static final String[] lIIlIllIl;
    private static String[] lIIlIllll;

    public Search() {
        super(Search.lIIlIllIl[0], 0, Category.RENDER, Search.lIIlIllIl[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(Search.lIIlIllIl[2], this, 50.0D, 2.0D, 256.0D, true));
        Explicit.instance.sm.rSetting(new Setting(Search.lIIlIllIl[3], this, 4000.0D, 1000.0D, 9999.0D, true));
        Explicit.instance.sm.rSetting(new Setting(Search.lIIlIllIl[4], this, true));
        Explicit.instance.sm.rSetting(new Setting(Search.lIIlIllIl[5], this, false));
        Explicit.instance.sm.rSetting(new Setting(Search.lIIlIllIl[6], this, false));
        Explicit.instance.sm.rSetting(new Setting(Search.lIIlIllIl[7], this, true));
        Explicit.instance.sm.rSetting(new Setting(Search.lIIlIllIl[8], this, false));
        Explicit.instance.sm.rSetting(new Setting(Search.lIIlIllIl[9], this, false));
        Explicit.instance.sm.rSetting(new Setting(Search.lIIlIllIl[10], this, false));
        Explicit.instance.sm.rSetting(new Setting(Search.lIIlIllIl[11], this, false));
        (this.blocksFound = new ArrayList()).clear();
        (this.ores = new ArrayList()).add(Blocks.iron_ore);
        this.ores.add(Blocks.gold_ore);
        this.ores.add(Blocks.diamond_ore);
        this.ores.add(Blocks.emerald_ore);
        this.ores.add(Blocks.lapis_ore);
        this.ores.add(Blocks.redstone_ore);
        this.ores.add(Blocks.coal_ore);
        this.ores.add(Blocks.mob_spawner);
    }

    public void onUpdateNoToggle() {
        this.r = Explicit.instance.sm.getSettingByName(this, Search.lIIlIllIl[12]).getValInt();
        this.iron = Explicit.instance.sm.getSettingByName(this, Search.lIIlIllIl[13]).getValBoolean();
        this.gold = Explicit.instance.sm.getSettingByName(this, Search.lIIlIllIl[14]).getValBoolean();
        this.diamond = Explicit.instance.sm.getSettingByName(this, Search.lIIlIllIl[15]).getValBoolean();
        this.redstone = Explicit.instance.sm.getSettingByName(this, Search.lIIlIllIl[16]).getValBoolean();
        this.coal = Explicit.instance.sm.getSettingByName(this, Search.lIIlIllIl[17]).getValBoolean();
        this.emerald = Explicit.instance.sm.getSettingByName(this, Search.lIIlIllIl[18]).getValBoolean();
        this.lapis = Explicit.instance.sm.getSettingByName(this, Search.lIIlIllIl[19]).getValBoolean();
        this.spawner = Explicit.instance.sm.getSettingByName(this, Search.lIIlIllIl[20]).getValBoolean();
    }

    @SubscribeEvent
    public void orl(RenderWorldLastEvent renderworldlastevent) {
        if (this.updateTimer.hasReached(Explicit.instance.sm.getSettingByName(this, Search.lIIlIllIl[21]).getValDouble())) {
            this.updateBlocks();
            this.updateTimer.reset();
        }

        if (this.iron || this.gold || this.coal || this.diamond || this.redstone || this.emerald || this.lapis || this.spawner) {
            for (int i = 0; i < this.blocksFound.size(); ++i) {
                this.draw((BlockFound) this.blocksFound.get(i));
            }

        }
    }

    private void updateBlocks() {
        this.blocksFound.clear();
        BlockPos blockpos = Game.Player().getPosition();
        int i = this.r;

        for (int j = blockpos.getX() - i; j <= blockpos.getX() + i; ++j) {
            for (int k = blockpos.getZ() - i; k < blockpos.getZ() + i; ++k) {
                for (int l = blockpos.getY() - i; l < blockpos.getY() + i; ++l) {
                    Block block = Game.World().getBlockState(new BlockPos(j, l, k)).getBlock();

                    if (this.ores.contains(block) && (this.iron || !block.equals(Blocks.iron_ore)) && (this.gold || !block.equals(Blocks.gold_ore)) && (this.diamond || !block.equals(Blocks.diamond_ore)) && (this.emerald || !block.equals(Blocks.emerald_ore)) && (this.lapis || !block.equals(Blocks.lapis_ore)) && (this.redstone || !block.equals(Blocks.redstone_ore)) && (this.coal || !block.equals(Blocks.coal_ore)) && (this.spawner || !block.equals(Blocks.mob_spawner))) {
                        this.blocksFound.add(new BlockFound(new BlockPos(j, l, k), block, this.color(block)));
                    }
                }
            }
        }

    }

    private void draw(BlockFound blockfound) {
        RenderUtils.blockESPBox(blockfound.pos, (float) blockfound.color.getRed(), (float) blockfound.color.getGreen(), (float) blockfound.color.getBlue(), 1.0F);
    }

    private Color color(Block block) {
        short short0 = 0;
        short short1 = 0;
        short short2 = 0;

        if (block.equals(Blocks.iron_ore)) {
            short0 = 255;
            short1 = 255;
            short2 = 255;
        } else if (block.equals(Blocks.gold_ore)) {
            short0 = 255;
            short1 = 255;
        } else if (block.equals(Blocks.diamond_ore)) {
            short1 = 220;
            short2 = 255;
        } else if (block.equals(Blocks.emerald_ore)) {
            short0 = 35;
            short1 = 255;
        } else if (block.equals(Blocks.lapis_ore)) {
            short1 = 50;
            short2 = 255;
        } else if (block.equals(Blocks.redstone_ore)) {
            short0 = 255;
        } else if (block.equals(Blocks.mob_spawner)) {
            short0 = 30;
            short2 = 135;
        }

        return new Color(short0, short1, short2);
    }

    static {
        lIIIIlIlIII();
        lIIIIlIIlll();
    }

    private static void lIIIIlIIlll() {
        lIIlIllIl = new String[22];
        Search.lIIlIllIl[0] = lIIIIlIIIlI(Search.lIIlIllll[0], Search.lIIlIllll[1]);
        Search.lIIlIllIl[1] = lIIIIlIIIlI(Search.lIIlIllll[2], Search.lIIlIllll[3]);
        Search.lIIlIllIl[2] = lIIIIlIIIll(Search.lIIlIllll[4], Search.lIIlIllll[5]);
        Search.lIIlIllIl[3] = lIIIIlIIIlI(Search.lIIlIllll[6], Search.lIIlIllll[7]);
        Search.lIIlIllIl[4] = lIIIIlIIIll(Search.lIIlIllll[8], Search.lIIlIllll[9]);
        Search.lIIlIllIl[5] = lIIIIlIIIlI(Search.lIIlIllll[10], Search.lIIlIllll[11]);
        Search.lIIlIllIl[6] = lIIIIlIIllI(Search.lIIlIllll[12], Search.lIIlIllll[13]);
        Search.lIIlIllIl[7] = lIIIIlIIIlI(Search.lIIlIllll[14], Search.lIIlIllll[15]);
        Search.lIIlIllIl[8] = lIIIIlIIIll(Search.lIIlIllll[16], Search.lIIlIllll[17]);
        Search.lIIlIllIl[9] = lIIIIlIIIll(Search.lIIlIllll[18], Search.lIIlIllll[19]);
        Search.lIIlIllIl[10] = lIIIIlIIIlI(Search.lIIlIllll[20], Search.lIIlIllll[21]);
        Search.lIIlIllIl[11] = lIIIIlIIllI(Search.lIIlIllll[22], Search.lIIlIllll[23]);
        Search.lIIlIllIl[12] = lIIIIlIIIll(Search.lIIlIllll[24], Search.lIIlIllll[25]);
        Search.lIIlIllIl[13] = lIIIIlIIIlI(Search.lIIlIllll[26], Search.lIIlIllll[27]);
        Search.lIIlIllIl[14] = lIIIIlIIIll(Search.lIIlIllll[28], Search.lIIlIllll[29]);
        Search.lIIlIllIl[15] = lIIIIlIIllI(Search.lIIlIllll[30], Search.lIIlIllll[31]);
        Search.lIIlIllIl[16] = lIIIIlIIllI(Search.lIIlIllll[32], Search.lIIlIllll[33]);
        Search.lIIlIllIl[17] = lIIIIlIIIlI(Search.lIIlIllll[34], Search.lIIlIllll[35]);
        Search.lIIlIllIl[18] = lIIIIlIIIlI(Search.lIIlIllll[36], Search.lIIlIllll[37]);
        Search.lIIlIllIl[19] = lIIIIlIIIlI(Search.lIIlIllll[38], Search.lIIlIllll[39]);
        Search.lIIlIllIl[20] = lIIIIlIIllI(Search.lIIlIllll[40], Search.lIIlIllll[41]);
        Search.lIIlIllIl[21] = lIIIIlIIllI(Search.lIIlIllll[42], Search.lIIlIllll[43]);
        Search.lIIlIllll = null;
    }

    private static void lIIIIlIlIII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Search.lIIlIllll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIIlIIIll(String s, String s1) {
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

    private static String lIIIIlIIIlI(String s, String s1) {
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

    private static String lIIIIlIIllI(String s, String s1) {
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
