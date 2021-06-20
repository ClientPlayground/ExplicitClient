package me.explicit.module.render;

import java.awt.Color;
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
import me.explicit.utils.RenderUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;

public class StorageESP extends Module {

    private boolean rainbow;
    private int red;
    private int green;
    private int blue;
    private static final String[] lIlIIlI;
    private static String[] lIllIll;

    public StorageESP() {
        super(StorageESP.lIlIIlI[0], 0, Category.RENDER, StorageESP.lIlIIlI[1]);
    }

    public void setup() {
        Explicit.instance.sm.rSetting(new Setting(StorageESP.lIlIIlI[2], this, true));
        Explicit.instance.sm.rSetting(new Setting(StorageESP.lIlIIlI[3], this, 2.0D, 0.1D, 5.0D, false));
        Explicit.instance.sm.rSetting(new Setting(StorageESP.lIlIIlI[4], this, 150.0D, 0.0D, 255.0D, true));
        Explicit.instance.sm.rSetting(new Setting(StorageESP.lIlIIlI[5], this, 0.0D, 0.0D, 255.0D, true));
        Explicit.instance.sm.rSetting(new Setting(StorageESP.lIlIIlI[6], this, 0.0D, 0.0D, 255.0D, true));
        Explicit.instance.sm.rSetting(new Setting(StorageESP.lIlIIlI[7], this, false));
        Explicit.instance.sm.rSetting(new Setting(StorageESP.lIlIIlI[8], this, 20.0D, 1.0D, 512.0D, true));
        Explicit.instance.sm.rSetting(new Setting(StorageESP.lIlIIlI[9], this, true));
        Explicit.instance.sm.rSetting(new Setting(StorageESP.lIlIIlI[10], this, true));
        Explicit.instance.sm.rSetting(new Setting(StorageESP.lIlIIlI[11], this, false));
        Explicit.instance.sm.rSetting(new Setting(StorageESP.lIlIIlI[12], this, false));
        Explicit.instance.sm.rSetting(new Setting(StorageESP.lIlIIlI[13], this, false));
        Explicit.instance.sm.rSetting(new Setting(StorageESP.lIlIIlI[14], this, false));
    }

    public void onUpdateNoToggle() {
        this.rainbow = Explicit.instance.sm.getSettingByName(this, StorageESP.lIlIIlI[15]).getValBoolean();
        Explicit.instance.sm.getSettingByName(this, StorageESP.lIlIIlI[16]).setVisible(!this.rainbow);
        Explicit.instance.sm.getSettingByName(this, StorageESP.lIlIIlI[17]).setVisible(!this.rainbow);
        Explicit.instance.sm.getSettingByName(this, StorageESP.lIlIIlI[18]).setVisible(!this.rainbow);
    }

    public void onRender3D() {
        int i = (int) Explicit.instance.sm.getSettingByName(this, StorageESP.lIlIIlI[19]).getValDouble();
        boolean flag = Explicit.instance.sm.getSettingByName(this, StorageESP.lIlIIlI[20]).getValBoolean();
        Iterator iterator = StorageESP.mc.theWorld.loadedTileEntityList.iterator();

        while (iterator.hasNext()) {
            Object object = iterator.next();

            if (flag) {
                if (((TileEntity) object).getPos().getX() - StorageESP.mc.thePlayer.getPosition().getX() > -(i + 1) && ((TileEntity) object).getPos().getX() - StorageESP.mc.thePlayer.getPosition().getX() < i + 1 && ((TileEntity) object).getPos().getY() - StorageESP.mc.thePlayer.getPosition().getY() > -(i + 1) && ((TileEntity) object).getPos().getY() - StorageESP.mc.thePlayer.getPosition().getY() < i + 1 && ((TileEntity) object).getPos().getZ() - StorageESP.mc.thePlayer.getPosition().getZ() > -(i + 1) && ((TileEntity) object).getPos().getZ() - StorageESP.mc.thePlayer.getPosition().getZ() < i + 1) {
                    this.checkCorrectBlock((TileEntity) object);
                }
            } else {
                this.checkCorrectBlock((TileEntity) object);
            }
        }

    }

    private void checkCorrectBlock(TileEntity tileentity) {
        boolean flag = Explicit.instance.sm.getSettingByName(this, StorageESP.lIlIIlI[21]).getValBoolean();
        boolean flag1 = Explicit.instance.sm.getSettingByName(this, StorageESP.lIlIIlI[22]).getValBoolean();
        boolean flag2 = Explicit.instance.sm.getSettingByName(this, StorageESP.lIlIIlI[23]).getValBoolean();
        boolean flag3 = Explicit.instance.sm.getSettingByName(this, StorageESP.lIlIIlI[24]).getValBoolean();
        boolean flag4 = Explicit.instance.sm.getSettingByName(this, StorageESP.lIlIIlI[25]).getValBoolean();
        boolean flag5 = Explicit.instance.sm.getSettingByName(this, StorageESP.lIlIIlI[26]).getValBoolean();
        float f = (float) Explicit.instance.sm.getSettingByName(this, StorageESP.lIlIIlI[27]).getValDouble();

        if (tileentity instanceof TileEntityChest && flag) {
            RenderUtils.blockESPBox(((TileEntityChest) tileentity).getPos(), (float) this.getColor().getRed() / 255.0F, (float) this.getColor().getGreen() / 255.0F, (float) this.getColor().getBlue() / 255.0F, f);
        }

        if (tileentity instanceof TileEntityEnderChest && flag1) {
            RenderUtils.blockESPBox(((TileEntityEnderChest) tileentity).getPos(), (float) this.getColor().getRed() / 255.0F, (float) this.getColor().getGreen() / 255.0F, (float) this.getColor().getBlue() / 255.0F, f);
        }

        if (tileentity instanceof TileEntityDropper && flag2) {
            RenderUtils.blockESPBox(((TileEntityDropper) tileentity).getPos(), (float) this.getColor().getRed() / 255.0F, (float) this.getColor().getGreen() / 255.0F, (float) this.getColor().getBlue() / 255.0F, f);
        }

        if (tileentity instanceof TileEntityDispenser && flag3) {
            RenderUtils.blockESPBox(((TileEntityDispenser) tileentity).getPos(), (float) this.getColor().getRed() / 255.0F, (float) this.getColor().getGreen() / 255.0F, (float) this.getColor().getBlue() / 255.0F, f);
        }

        if (tileentity instanceof TileEntityFurnace && flag4) {
            RenderUtils.blockESPBox(((TileEntityFurnace) tileentity).getPos(), (float) this.getColor().getRed() / 255.0F, (float) this.getColor().getGreen() / 255.0F, (float) this.getColor().getBlue() / 255.0F, f);
        }

        if (tileentity instanceof TileEntityHopper && flag5) {
            RenderUtils.blockESPBox(((TileEntityHopper) tileentity).getPos(), (float) this.getColor().getRed() / 255.0F, (float) this.getColor().getGreen() / 255.0F, (float) this.getColor().getBlue() / 255.0F, f);
        }

    }

    private Color getColor() {
        return new Color(this.red, this.green, this.blue, 255);
    }

    static {
        lIIllIIll();
        lIIllIIlI();
    }

    private static void lIIllIIlI() {
        lIlIIlI = new String[28];
        StorageESP.lIlIIlI[0] = lIIIlIllI(StorageESP.lIllIll[0], StorageESP.lIllIll[1]);
        StorageESP.lIlIIlI[1] = lIIIllIIl(StorageESP.lIllIll[2], StorageESP.lIllIll[3]);
        StorageESP.lIlIIlI[2] = lIIIllIIl(StorageESP.lIllIll[4], StorageESP.lIllIll[5]);
        StorageESP.lIlIIlI[3] = lIIIllIIl(StorageESP.lIllIll[6], StorageESP.lIllIll[7]);
        StorageESP.lIlIIlI[4] = lIIIlIllI(StorageESP.lIllIll[8], StorageESP.lIllIll[9]);
        StorageESP.lIlIIlI[5] = lIIIllIIl(StorageESP.lIllIll[10], StorageESP.lIllIll[11]);
        StorageESP.lIlIIlI[6] = lIIIllIIl(StorageESP.lIllIll[12], StorageESP.lIllIll[13]);
        StorageESP.lIlIIlI[7] = lIIIllIIl(StorageESP.lIllIll[14], StorageESP.lIllIll[15]);
        StorageESP.lIlIIlI[8] = lIIIlllIl(StorageESP.lIllIll[16], StorageESP.lIllIll[17]);
        StorageESP.lIlIIlI[9] = lIIIlIllI(StorageESP.lIllIll[18], StorageESP.lIllIll[19]);
        StorageESP.lIlIIlI[10] = lIIIlllIl(StorageESP.lIllIll[20], StorageESP.lIllIll[21]);
        StorageESP.lIlIIlI[11] = lIIIllIIl(StorageESP.lIllIll[22], StorageESP.lIllIll[23]);
        StorageESP.lIlIIlI[12] = lIIIlllIl(StorageESP.lIllIll[24], StorageESP.lIllIll[25]);
        StorageESP.lIlIIlI[13] = lIIIlIllI(StorageESP.lIllIll[26], StorageESP.lIllIll[27]);
        StorageESP.lIlIIlI[14] = lIIIllIIl(StorageESP.lIllIll[28], StorageESP.lIllIll[29]);
        StorageESP.lIlIIlI[15] = lIIIllIIl(StorageESP.lIllIll[30], StorageESP.lIllIll[31]);
        StorageESP.lIlIIlI[16] = lIIIlllIl(StorageESP.lIllIll[32], StorageESP.lIllIll[33]);
        StorageESP.lIlIIlI[17] = lIIIlllIl(StorageESP.lIllIll[34], StorageESP.lIllIll[35]);
        StorageESP.lIlIIlI[18] = lIIIllIIl(StorageESP.lIllIll[36], StorageESP.lIllIll[37]);
        StorageESP.lIlIIlI[19] = lIIIllIIl(StorageESP.lIllIll[38], StorageESP.lIllIll[39]);
        StorageESP.lIlIIlI[20] = lIIIlIllI(StorageESP.lIllIll[40], StorageESP.lIllIll[41]);
        StorageESP.lIlIIlI[21] = lIIIllIIl(StorageESP.lIllIll[42], StorageESP.lIllIll[43]);
        StorageESP.lIlIIlI[22] = lIIIllIIl("iXw57Tkipi/uXUcN4CdisQ==", "sPfVt");
        StorageESP.lIlIIlI[23] = lIIIlllIl("CRwlBjgoHA==", "MnJvH");
        StorageESP.lIlIIlI[24] = lIIIllIIl("EvoID8/FE+V3IryAuLVL7Q==", "unufH");
        StorageESP.lIlIIlI[25] = lIIIllIIl("maktT7XAm4I=", "sHAHn");
        StorageESP.lIlIIlI[26] = lIIIllIIl("eVAL6AlFbMw=", "iWMlu");
        StorageESP.lIlIIlI[27] = lIIIlllIl("Pg8ALh4bHAs=", "rfnKM");
        StorageESP.lIllIll = null;
    }

    private static void lIIllIIll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        StorageESP.lIllIll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIllIIl(String s, String s1) {
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

    private static String lIIIlllIl(String s, String s1) {
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

    private static String lIIIlIllI(String s, String s1) {
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
