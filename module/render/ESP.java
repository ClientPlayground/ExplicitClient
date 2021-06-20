package me.explicit.module.render;

import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.module.combat.AntiBot;
import me.explicit.settings.Setting;
import me.explicit.utils.CombatUtils;
import me.explicit.utils.Game;
import me.explicit.utils.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;

public class ESP extends Module {

    private String color;
    private int red;
    private int green;
    private int blue;
    private static final String[] lIIIIll;
    private static String[] lIIlIll;

    public ESP() {
        super(ESP.lIIIIll[0], 0, Category.RENDER, ESP.lIIIIll[1]);
    }

    public void setup() {
        ArrayList arraylist;
        ArrayList arraylist1;

        (arraylist = new ArrayList()).add(ESP.lIIIIll[2]);
        arraylist.add(ESP.lIIIIll[3]);
        Explicit.instance.sm.rSetting(new Setting(ESP.lIIIIll[4], this, ESP.lIIIIll[5], arraylist));
        (arraylist1 = new ArrayList()).add(ESP.lIIIIll[6]);
        arraylist1.add(ESP.lIIIIll[7]);
        Explicit.instance.sm.rSetting(new Setting(ESP.lIIIIll[8], this, ESP.lIIIIll[9], arraylist1));
        Explicit.instance.sm.rSetting(new Setting(ESP.lIIIIll[10], this, true));
        Explicit.instance.sm.rSetting(new Setting(ESP.lIIIIll[11], this, false));
        Explicit.instance.sm.rSetting(new Setting(ESP.lIIIIll[12], this, false));
        Explicit.instance.sm.rSetting(new Setting(ESP.lIIIIll[13], this, false));
        Explicit.instance.sm.rSetting(new Setting(ESP.lIIIIll[14], this, false));
        Explicit.instance.sm.rSetting(new Setting(ESP.lIIIIll[15], this, 150.0D, 0.0D, 255.0D, true));
        Explicit.instance.sm.rSetting(new Setting(ESP.lIIIIll[16], this, 0.0D, 0.0D, 255.0D, true));
        Explicit.instance.sm.rSetting(new Setting(ESP.lIIIIll[17], this, 0.0D, 0.0D, 255.0D, true));
    }

    public void onRender3D() {
        this.color = Explicit.instance.sm.getSettingByName(this, ESP.lIIIIll[18]).getValString();
        this.red = (int) Explicit.instance.sm.getSettingByName(this, ESP.lIIIIll[19]).getValDouble();
        this.green = (int) Explicit.instance.sm.getSettingByName(this, ESP.lIIIIll[20]).getValDouble();
        this.blue = (int) Explicit.instance.sm.getSettingByName(this, ESP.lIIIIll[21]).getValDouble();
        if (this.color.equalsIgnoreCase(ESP.lIIIIll[22])) {
            Explicit.instance.sm.getSettingByName(this, ESP.lIIIIll[23]).setVisible(false);
            Explicit.instance.sm.getSettingByName(this, ESP.lIIIIll[24]).setVisible(false);
            Explicit.instance.sm.getSettingByName(this, ESP.lIIIIll[25]).setVisible(false);
        } else {
            Explicit.instance.sm.getSettingByName(this, ESP.lIIIIll[26]).setVisible(true);
            Explicit.instance.sm.getSettingByName(this, ESP.lIIIIll[27]).setVisible(true);
            Explicit.instance.sm.getSettingByName(this, ESP.lIIIIll[28]).setVisible(true);
        }

        Iterator iterator = Game.World().loadedEntityList.iterator();

        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();

            if (!AntiBot.getBots().contains(entity)) {
                boolean flag = true;
                boolean flag1 = Explicit.instance.sm.getSettingByName(this, ESP.lIIIIll[29]).getValBoolean();
                boolean flag2 = Explicit.instance.sm.getSettingByName(this, ESP.lIIIIll[30]).getValBoolean();
                boolean flag3 = Explicit.instance.sm.getSettingByName(this, ESP.lIIIIll[31]).getValBoolean();

                if (!CombatUtils.isTeam(Game.Player(), entity) && flag1 && flag2 && entity instanceof EntityPlayer) {
                    flag = false;
                } else if (entity.isInvisible() && !flag3 && flag2 && entity instanceof EntityPlayer) {
                    flag = false;
                } else if (!flag2 || !(entity instanceof EntityPlayer)) {
                    flag = false;
                }

                if (entity != Game.Player() && !AntiBot.getBots().contains(entity) && (flag || entity instanceof EntityMob && Explicit.instance.sm.getSettingByName(this, ESP.lIIIIll[32]).getValBoolean() || entity instanceof EntityAnimal && Explicit.instance.sm.getSettingByName(this, ESP.lIIIIll[33]).getValBoolean())) {
                    if (Explicit.instance.sm.getSettingByName(this, ESP.lIIIIll[34]).getValString().equalsIgnoreCase(ESP.lIIIIll[35])) {
                        RenderUtils.renderEntity(entity, this.getColor().getRGB(), 2);
                    } else if (Explicit.instance.sm.getSettingByName(this, ESP.lIIIIll[36]).getValString().equalsIgnoreCase(ESP.lIIIIll[37])) {
                        RenderUtils.renderEntity(entity, this.getColor().getRGB(), 3);
                    }
                }
            }
        }

    }

    private Color getColor() {
        return this.color.equalsIgnoreCase(ESP.lIIIIll[38]) ? Explicit.instance.cm.cc.getColor(0) : new Color(this.red, this.green, this.blue, 255);
    }

    static {
        lIIIIlIlI();
        lIIIIIllI();
    }

    private static void lIIIIIllI() {
        lIIIIll = new String[39];
        ESP.lIIIIll[0] = lllIIllI(ESP.lIIlIll[0], ESP.lIIlIll[1]);
        ESP.lIIIIll[1] = lllIllIl(ESP.lIIlIll[2], ESP.lIIlIll[3]);
        ESP.lIIIIll[2] = lllIlllI(ESP.lIIlIll[4], ESP.lIIlIll[5]);
        ESP.lIIIIll[3] = lllIIllI(ESP.lIIlIll[6], ESP.lIIlIll[7]);
        ESP.lIIIIll[4] = lllIIllI(ESP.lIIlIll[8], ESP.lIIlIll[9]);
        ESP.lIIIIll[5] = lllIlllI(ESP.lIIlIll[10], ESP.lIIlIll[11]);
        ESP.lIIIIll[6] = lllIIllI(ESP.lIIlIll[12], ESP.lIIlIll[13]);
        ESP.lIIIIll[7] = lllIIllI(ESP.lIIlIll[14], ESP.lIIlIll[15]);
        ESP.lIIIIll[8] = lllIlllI(ESP.lIIlIll[16], ESP.lIIlIll[17]);
        ESP.lIIIIll[9] = lllIllIl(ESP.lIIlIll[18], ESP.lIIlIll[19]);
        ESP.lIIIIll[10] = lllIlllI(ESP.lIIlIll[20], ESP.lIIlIll[21]);
        ESP.lIIIIll[11] = lllIllIl(ESP.lIIlIll[22], ESP.lIIlIll[23]);
        ESP.lIIIIll[12] = lllIllIl(ESP.lIIlIll[24], ESP.lIIlIll[25]);
        ESP.lIIIIll[13] = lllIlllI(ESP.lIIlIll[26], ESP.lIIlIll[27]);
        ESP.lIIIIll[14] = lllIIllI(ESP.lIIlIll[28], ESP.lIIlIll[29]);
        ESP.lIIIIll[15] = lllIllIl(ESP.lIIlIll[30], ESP.lIIlIll[31]);
        ESP.lIIIIll[16] = lllIlllI(ESP.lIIlIll[32], ESP.lIIlIll[33]);
        ESP.lIIIIll[17] = lllIlllI(ESP.lIIlIll[34], ESP.lIIlIll[35]);
        ESP.lIIIIll[18] = lllIllIl(ESP.lIIlIll[36], ESP.lIIlIll[37]);
        ESP.lIIIIll[19] = lllIlllI(ESP.lIIlIll[38], ESP.lIIlIll[39]);
        ESP.lIIIIll[20] = lllIIllI(ESP.lIIlIll[40], ESP.lIIlIll[41]);
        ESP.lIIIIll[21] = lllIIllI(ESP.lIIlIll[42], ESP.lIIlIll[43]);
        ESP.lIIIIll[22] = lllIllIl(ESP.lIIlIll[44], ESP.lIIlIll[45]);
        ESP.lIIIIll[23] = lllIlllI(ESP.lIIlIll[46], ESP.lIIlIll[47]);
        ESP.lIIIIll[24] = lllIllIl(ESP.lIIlIll[48], ESP.lIIlIll[49]);
        ESP.lIIIIll[25] = lllIlllI("X5H8JFbaBMs=", "ZdSPQ");
        ESP.lIIIIll[26] = lllIIllI("HXEDOAdq7+Y=", "JpZjP");
        ESP.lIIIIll[27] = lllIllIl("AxgyACk=", "DjWeG");
        ESP.lIIIIll[28] = lllIlllI("rJx/dY09R9s=", "DcAXd");
        ESP.lIIIIll[29] = lllIlllI("7O7kZmwihl4=", "IejaQ");
        ESP.lIIIIll[30] = lllIllIl("PDosHioeJQ==", "lVMgO");
        ESP.lIIIIll[31] = lllIlllI("7tyKNnLr903Kt3W1w31SJQ==", "wDNgs");
        ESP.lIIIIll[32] = lllIIllI("4zZsl0gdym0=", "krfHL");
        ESP.lIIIIll[33] = lllIIllI("QI3+l1NB6HA=", "AliAk");
        ESP.lIIIIll[34] = lllIllIl("ITwBBw==", "lSebm");
        ESP.lIIIIll[35] = lllIllIl("LgUy", "ljJhi");
        ESP.lIIIIll[36] = lllIlllI("VvFStmxJkLs=", "xiLGX");
        ESP.lIIIIll[37] = lllIIllI("u4EdPlPRmKI=", "dmcoP");
        ESP.lIIIIll[38] = lllIIllI("6uXD08aqnUI=", "dsALw");
        ESP.lIIlIll = null;
    }

    private static void lIIIIlIlI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ESP.lIIlIll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lllIlllI(String s, String s1) {
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

    private static String lllIllIl(String s, String s1) {
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

    private static String lllIIllI(String s, String s1) {
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
