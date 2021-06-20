package me.explicit.settings;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.config.ConfigManager;
import me.explicit.module.Module;

public class Setting {

    private String name;
    private Module parent;
    private String mode;
    private String sval;
    private List options;
    private boolean bval;
    private double dval;
    private double min;
    private double max;
    private boolean onlyint = false;
    public boolean percentage = false;
    boolean visible = false;
    private static final String[] lIlIIll;
    private static String[] lIlIlII;

    public Setting(String s, Module module, String s1, ArrayList arraylist) {
        this.name = s;
        this.parent = module;
        this.sval = s1;
        this.options = arraylist;
        this.mode = Setting.lIlIIll[0];
        this.visible = false;
    }

    public Setting(String s, Module module, boolean flag) {
        this.name = s;
        this.parent = module;
        this.bval = flag;
        this.mode = Setting.lIlIIll[1];
        this.visible = false;
    }

    public Setting(String s, Module module, double d0, double d1, double d2, boolean flag) {
        this.name = s;
        this.parent = module;
        this.dval = d0;
        this.min = d1;
        this.max = d2;
        this.onlyint = flag;
        this.mode = Setting.lIlIIll[2];
        this.visible = false;
    }

    public Setting(String s, Module module, double d0, double d1, double d2, boolean flag, boolean flag1) {
        this.name = s;
        this.parent = module;
        this.dval = d0;
        this.min = d1;
        this.max = d2;
        this.onlyint = flag;
        this.percentage = flag1;
        this.mode = Setting.lIlIIll[3];
        this.visible = false;
    }

    public void setVisible(boolean flag) {
        this.visible = !flag;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public String getName() {
        return this.name;
    }

    public Module getParentMod() {
        return this.parent;
    }

    public String getValString() {
        return this.sval;
    }

    public void setValString(String s) {
        if (this.sval != s) {
            this.sval = s;
            ConfigManager.SaveConfigFile(Setting.lIlIIll[4]);
        }

    }

    public void setValStringNoSave(String s) {
        this.sval = s;
    }

    public List getOptions() {
        return this.options;
    }

    public boolean getValBoolean() {
        return this.bval;
    }

    public void setValBoolean(boolean flag) {
        if (this.bval != flag) {
            this.bval = flag;
            ConfigManager.SaveConfigFile(Setting.lIlIIll[5]);
        }

    }

    public void setValBooleanNoSave(boolean flag) {
        this.bval = flag;
    }

    public double getValDouble() {
        if (this.onlyint) {
            this.dval = (double) ((int) this.dval);
        }

        return this.dval;
    }

    public int getValInt() {
        if (this.onlyint) {
            this.dval = (double) ((int) this.dval);
        }

        return (int) this.dval;
    }

    public void setValDouble(double d0) {
        if (this.dval != d0) {
            this.dval = d0;
            ConfigManager.SaveConfigFile(Setting.lIlIIll[6]);
        }

    }

    public void setValDoubleNoSave(double d0) {
        this.dval = d0;
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }

    public boolean isCombo() {
        return this.mode.equalsIgnoreCase(Setting.lIlIIll[7]);
    }

    public boolean isCheck() {
        return this.mode.equalsIgnoreCase(Setting.lIlIIll[8]);
    }

    public boolean isSlider() {
        return this.mode.equalsIgnoreCase(Setting.lIlIIll[9]);
    }

    public boolean onlyInt() {
        return this.onlyint;
    }

    static {
        lIIIlllII();
        lIIIllIll();
    }

    private static void lIIIllIll() {
        lIlIIll = new String[10];
        Setting.lIlIIll[0] = lIIIlIlll(Setting.lIlIlII[0], Setting.lIlIlII[1]);
        Setting.lIlIIll[1] = lIIIllIII(Setting.lIlIlII[2], Setting.lIlIlII[3]);
        Setting.lIlIIll[2] = lIIIlIlll(Setting.lIlIlII[4], Setting.lIlIlII[5]);
        Setting.lIlIIll[3] = lIIIlIlll(Setting.lIlIlII[6], Setting.lIlIlII[7]);
        Setting.lIlIIll[4] = lIIIllIII(Setting.lIlIlII[8], Setting.lIlIlII[9]);
        Setting.lIlIIll[5] = lIIIlIlll(Setting.lIlIlII[10], Setting.lIlIlII[11]);
        Setting.lIlIIll[6] = lIIIlIlll(Setting.lIlIlII[12], Setting.lIlIlII[13]);
        Setting.lIlIIll[7] = lIIIllIlI(Setting.lIlIlII[14], Setting.lIlIlII[15]);
        Setting.lIlIIll[8] = lIIIllIII(Setting.lIlIlII[16], Setting.lIlIlII[17]);
        Setting.lIlIIll[9] = lIIIllIII(Setting.lIlIlII[18], Setting.lIlIlII[19]);
        Setting.lIlIlII = null;
    }

    private static void lIIIlllII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Setting.lIlIlII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIllIlI(String s, String s1) {
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

    private static String lIIIlIlll(String s, String s1) {
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

    private static String lIIIllIII(String s, String s1) {
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
