package me.explicit.module.render;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.Setting;

public class ClickGUI extends Module {

    public static String mode;
    private static final String[] lIIIlllI;
    private static String[] lIIIllll;

    public ClickGUI() {
        super(ClickGUI.lIIIlllI[0], 54, Category.RENDER, ClickGUI.lIIIlllI[1]);
    }

    public void setup() {
        ArrayList arraylist = new ArrayList();

        arraylist.add(ClickGUI.lIIIlllI[2]);
        arraylist.add(ClickGUI.lIIIlllI[3]);
        arraylist.add(ClickGUI.lIIIlllI[4]);
        arraylist.add(ClickGUI.lIIIlllI[5]);
        arraylist.add(ClickGUI.lIIIlllI[6]);
        arraylist.add(ClickGUI.lIIIlllI[7]);
        Explicit.instance.sm.rSetting(new Setting(ClickGUI.lIIIlllI[8], this, ClickGUI.lIIIlllI[9], arraylist));
    }

    public void onUpdateNoToggle() {
        ClickGUI.mode = Explicit.instance.sm.getSettingByName(this, ClickGUI.lIIIlllI[10]).getValString();
    }

    public void onEnable() {
        super.onEnable();
        ClickGUI.mc.displayGuiScreen(Explicit.instance.clickGui);
        this.toggle();
    }

    static {
        llllllIlI();
        llllllIIl();
        ClickGUI.mode = ClickGUI.lIIIlllI[11];
    }

    private static void llllllIIl() {
        lIIIlllI = new String[12];
        ClickGUI.lIIIlllI[0] = lllllIlII(ClickGUI.lIIIllll[0], ClickGUI.lIIIllll[1]);
        ClickGUI.lIIIlllI[1] = lllllIllI(ClickGUI.lIIIllll[2], ClickGUI.lIIIllll[3]);
        ClickGUI.lIIIlllI[2] = lllllIllI(ClickGUI.lIIIllll[4], ClickGUI.lIIIllll[5]);
        ClickGUI.lIIIlllI[3] = llllllIII(ClickGUI.lIIIllll[6], ClickGUI.lIIIllll[7]);
        ClickGUI.lIIIlllI[4] = lllllIllI(ClickGUI.lIIIllll[8], ClickGUI.lIIIllll[9]);
        ClickGUI.lIIIlllI[5] = lllllIlII(ClickGUI.lIIIllll[10], ClickGUI.lIIIllll[11]);
        ClickGUI.lIIIlllI[6] = llllllIII(ClickGUI.lIIIllll[12], ClickGUI.lIIIllll[13]);
        ClickGUI.lIIIlllI[7] = lllllIlII(ClickGUI.lIIIllll[14], ClickGUI.lIIIllll[15]);
        ClickGUI.lIIIlllI[8] = lllllIlII(ClickGUI.lIIIllll[16], ClickGUI.lIIIllll[17]);
        ClickGUI.lIIIlllI[9] = llllllIII(ClickGUI.lIIIllll[18], ClickGUI.lIIIllll[19]);
        ClickGUI.lIIIlllI[10] = lllllIllI(ClickGUI.lIIIllll[20], ClickGUI.lIIIllll[21]);
        ClickGUI.lIIIlllI[11] = lllllIlII(ClickGUI.lIIIllll[22], ClickGUI.lIIIllll[23]);
        ClickGUI.lIIIllll = null;
    }

    private static void llllllIlI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ClickGUI.lIIIllll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lllllIllI(String s, String s1) {
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

    private static String lllllIlII(String s, String s1) {
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

    private static String llllllIII(String s, String s1) {
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
