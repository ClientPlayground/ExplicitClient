package me.explicit.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
import me.explicit.module.Module;
import me.explicit.settings.Setting;
import me.explicit.ui.clickgui.ClickGUI;
import me.explicit.ui.clickgui.Panel;
import net.minecraft.client.Minecraft;

public class ConfigManager {

    public static File dir;
    private static final File DEFAULT;
    private static final File FRIENDS;
    public static final File KILLSULTS;
    public static final File CLICKGUI;
    private static final String[] llIIIlll;
    private static String[] llIIlIII;

    public static File getConfigFile(String s, boolean flag) {
        File file;

        if (!flag) {
            file = new File(ConfigManager.dir, String.format(ConfigManager.llIIIlll[0], new Object[] { s}));
        } else {
            file = new File(ConfigManager.dir, String.format(ConfigManager.llIIIlll[1], new Object[] { s}));
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ioexception) {
                ;
            }
        }

        return file;
    }

    public static void init() {
        if (!ConfigManager.dir.exists()) {
            ConfigManager.dir.mkdir();
        }

        LoadConfig(ConfigManager.llIIIlll[2]);
        LoadFriends();
        loadGUISettings();
    }

    public static void SaveConfigFile(String s) {
        try {
            if (Explicit.instance.sm == null || Explicit.instance.sm.getSettings() == null) {
                return;
            }

            PrintWriter printwriter = new PrintWriter(getConfigFile(s, true));

            for (Iterator iterator = Explicit.instance.sm.getSettings().iterator(); iterator.hasNext(); printwriter.println()) {
                Setting setting = (Setting) iterator.next();

                if (setting.isCheck()) {
                    printwriter.write(String.valueOf((new StringBuilder()).append(ConfigManager.llIIIlll[3]).append(setting.getParentMod().getName()).append(ConfigManager.llIIIlll[4]).append(setting.getName()).append(ConfigManager.llIIIlll[5]).append(setting.getValBoolean())));
                } else if (setting.isCombo()) {
                    printwriter.write(String.valueOf((new StringBuilder()).append(ConfigManager.llIIIlll[6]).append(setting.getParentMod().getName()).append(ConfigManager.llIIIlll[7]).append(setting.getName()).append(ConfigManager.llIIIlll[8]).append(setting.getValString())));
                } else if (setting.isSlider()) {
                    printwriter.write(String.valueOf((new StringBuilder()).append(ConfigManager.llIIIlll[9]).append(setting.getParentMod().getName()).append(ConfigManager.llIIIlll[10]).append(setting.getName()).append(ConfigManager.llIIIlll[11]).append(setting.getValDouble())));
                } else {
                    printwriter.write(String.valueOf((new StringBuilder()).append(ConfigManager.llIIIlll[12]).append(setting.getParentMod().getName()).append(ConfigManager.llIIIlll[13]).append(setting.getName())));
                }
            }

            for (int i = 0; i < Explicit.instance.mm.getModules().size(); ++i) {
                Module module = (Module) Explicit.instance.mm.getModules().get(i);

                printwriter.write(String.valueOf((new StringBuilder()).append(ConfigManager.llIIIlll[14]).append(module.getName()).append(ConfigManager.llIIIlll[15]).append(module.isToggled()).append(ConfigManager.llIIIlll[16]).append(module.getKey())));
                if (Explicit.instance.mm.getModules().size() != i) {
                    printwriter.println();
                }
            }

            printwriter.close();
        } catch (FileNotFoundException filenotfoundexception) {
            filenotfoundexception.printStackTrace();
        }

    }

    public static void SaveFriendsFile() {
        try {
            PrintWriter printwriter = new PrintWriter(ConfigManager.FRIENDS);

            for (int i = 0; i < Explicit.instance.friendManager.getFriends().size(); ++i) {
                printwriter.println((String) Explicit.instance.friendManager.getFriends().get(i));
            }

            printwriter.close();
        } catch (FileNotFoundException filenotfoundexception) {
            filenotfoundexception.printStackTrace();
        }

    }

    public static void LoadFriends() {
        try {
            BufferedReader bufferedreader = new BufferedReader(new FileReader(ConfigManager.FRIENDS));
            String s;

            if (ConfigManager.FRIENDS.exists()) {
                while ((s = bufferedreader.readLine()) != null) {
                    Explicit.instance.friendManager.addFriendNoSave(s);
                }
            }

            bufferedreader.close();
        } catch (Exception exception) {
            ;
        }

    }

    public static List GetConfigs() {
        ArrayList arraylist = new ArrayList();
        File file = ConfigManager.dir;

        if (file.isDirectory()) {
            File[] afile = file.listFiles();
            File[] afile1 = afile;
            int i = afile.length;

            for (int j = 0; j < i; ++j) {
                File file1 = afile1[j];

                if (file1.getName().contains(ConfigManager.llIIIlll[17]) && !file1.getName().equalsIgnoreCase(ConfigManager.llIIIlll[18])) {
                    String s = file1.getName();

                    arraylist.add(s);
                }
            }
        }

        return arraylist;
    }

    public static void saveGUISettings() {
        try {
            PrintWriter printwriter = new PrintWriter(ConfigManager.CLICKGUI);
            Iterator iterator = ClickGUI.panels.iterator();

            while (iterator.hasNext()) {
                Panel panel = (Panel) iterator.next();

                printwriter.println(String.valueOf((new StringBuilder()).append(panel.title).append(ConfigManager.llIIIlll[19]).append(panel.x).append(ConfigManager.llIIIlll[20]).append(panel.y).append(ConfigManager.llIIIlll[21]).append(panel.extended)));
            }

            printwriter.close();
        } catch (FileNotFoundException filenotfoundexception) {
            filenotfoundexception.printStackTrace();
        }

    }

    public static void loadGUISettings() {
        ArrayList arraylist = new ArrayList();

        String s;

        try {
            BufferedReader bufferedreader = new BufferedReader(new FileReader(ConfigManager.CLICKGUI));

            while ((s = bufferedreader.readLine()) != null) {
                arraylist.add(s);
            }

            bufferedreader.close();
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }

        if (!arraylist.isEmpty()) {
            Iterator iterator = arraylist.iterator();

            while (iterator.hasNext()) {
                s = (String) iterator.next();
                String[] astring = s.split(ConfigManager.llIIIlll[22]);

                if (astring.length > 3) {
                    Iterator iterator1 = ClickGUI.panels.iterator();

                    while (iterator1.hasNext()) {
                        Panel panel = (Panel) iterator1.next();

                        if (panel.title.equalsIgnoreCase(astring[0])) {
                            panel.x = Double.parseDouble(astring[1]);
                            panel.y = Double.parseDouble(astring[2]);
                            panel.extended = Boolean.parseBoolean(astring[3]);
                        }
                    }
                }
            }
        }

    }

    public static void LoadConfig(String s) {
        try {
            BufferedReader bufferedreader = new BufferedReader(new FileReader(getConfigFile(s, true)));
            String s1;

            if (getConfigFile(s, true).exists()) {
                while ((s1 = bufferedreader.readLine()) != null) {
                    String[] astring = s1.split(ConfigManager.llIIIlll[23]);
                    Module module = Explicit.instance.mm.getModuleByName(astring[1]);

                    if (module != null) {
                        if (astring[0].equalsIgnoreCase(ConfigManager.llIIIlll[24])) {
                            Setting setting = Explicit.instance.sm.getSettingByName(module, astring[2]);

                            if (setting != null) {
                                if (setting.isCheck()) {
                                    setting.setValBooleanNoSave(Boolean.parseBoolean(astring[3]));
                                } else if (setting.isCombo()) {
                                    setting.setValStringNoSave(astring[3]);
                                } else if (setting.isSlider()) {
                                    setting.setValDoubleNoSave(Double.parseDouble(astring[3]));
                                }
                            }
                        } else if (astring[0].equalsIgnoreCase(ConfigManager.llIIIlll[25])) {
                            if (Boolean.parseBoolean(astring[2])) {
                                module.setToggledNoSave(true);
                            }

                            int i = Integer.parseInt(astring[3]);

                            if (i != 0) {
                                module.setKey(i);
                            }
                        }
                    }
                }
            }

            bufferedreader.close();
        } catch (Exception exception) {
            ;
        }

    }

    static {
        lIlllIlIIl();
        lIlllIlIII();
        File file = Minecraft.getMinecraft().mcDataDir;

        ConfigManager.dir = new File(file, ConfigManager.llIIIlll[26]);
        if (!ConfigManager.dir.exists()) {
            ConfigManager.dir.mkdirs();
        }

        DEFAULT = getConfigFile(ConfigManager.llIIIlll[27], true);
        FRIENDS = getConfigFile(ConfigManager.llIIIlll[28], false);
        CLICKGUI = getConfigFile(ConfigManager.llIIIlll[29], false);
        File file1 = new File(ConfigManager.dir, ConfigManager.llIIIlll[30]);

        if (!file1.exists()) {
            try {
                file1.createNewFile();
                PrintWriter printwriter = new PrintWriter(file1);

                printwriter.println(ConfigManager.llIIIlll[31]);
                printwriter.println(ConfigManager.llIIIlll[32]);
                printwriter.println(ConfigManager.llIIIlll[33]);
                printwriter.close();
            } catch (IOException ioexception) {
                ;
            }
        }

        KILLSULTS = file1;
    }

    private static void lIlllIlIII() {
        llIIIlll = new String[34];
        ConfigManager.llIIIlll[0] = lIlllIIlIl(ConfigManager.llIIlIII[0], ConfigManager.llIIlIII[1]);
        ConfigManager.llIIIlll[1] = lIlllIIlIl(ConfigManager.llIIlIII[2], ConfigManager.llIIlIII[3]);
        ConfigManager.llIIIlll[2] = lIlllIIlIl(ConfigManager.llIIlIII[4], ConfigManager.llIIlIII[5]);
        ConfigManager.llIIIlll[3] = lIlllIIllI(ConfigManager.llIIlIII[6], ConfigManager.llIIlIII[7]);
        ConfigManager.llIIIlll[4] = lIlllIIlIl(ConfigManager.llIIlIII[8], ConfigManager.llIIlIII[9]);
        ConfigManager.llIIIlll[5] = lIlllIIlIl(ConfigManager.llIIlIII[10], ConfigManager.llIIlIII[11]);
        ConfigManager.llIIIlll[6] = lIlllIIllI(ConfigManager.llIIlIII[12], ConfigManager.llIIlIII[13]);
        ConfigManager.llIIIlll[7] = lIlllIIllI(ConfigManager.llIIlIII[14], ConfigManager.llIIlIII[15]);
        ConfigManager.llIIIlll[8] = lIlllIIlIl(ConfigManager.llIIlIII[16], ConfigManager.llIIlIII[17]);
        ConfigManager.llIIIlll[9] = lIlllIIlll(ConfigManager.llIIlIII[18], ConfigManager.llIIlIII[19]);
        ConfigManager.llIIIlll[10] = lIlllIIlll(ConfigManager.llIIlIII[20], ConfigManager.llIIlIII[21]);
        ConfigManager.llIIIlll[11] = lIlllIIlIl(ConfigManager.llIIlIII[22], ConfigManager.llIIlIII[23]);
        ConfigManager.llIIIlll[12] = lIlllIIlIl(ConfigManager.llIIlIII[24], ConfigManager.llIIlIII[25]);
        ConfigManager.llIIIlll[13] = lIlllIIllI(ConfigManager.llIIlIII[26], ConfigManager.llIIlIII[27]);
        ConfigManager.llIIIlll[14] = lIlllIIllI(ConfigManager.llIIlIII[28], ConfigManager.llIIlIII[29]);
        ConfigManager.llIIIlll[15] = lIlllIIlIl(ConfigManager.llIIlIII[30], ConfigManager.llIIlIII[31]);
        ConfigManager.llIIIlll[16] = lIlllIIlIl(ConfigManager.llIIlIII[32], ConfigManager.llIIlIII[33]);
        ConfigManager.llIIIlll[17] = lIlllIIlIl(ConfigManager.llIIlIII[34], ConfigManager.llIIlIII[35]);
        ConfigManager.llIIIlll[18] = lIlllIIllI(ConfigManager.llIIlIII[36], ConfigManager.llIIlIII[37]);
        ConfigManager.llIIIlll[19] = lIlllIIllI(ConfigManager.llIIlIII[38], ConfigManager.llIIlIII[39]);
        ConfigManager.llIIIlll[20] = lIlllIIlll(ConfigManager.llIIlIII[40], ConfigManager.llIIlIII[41]);
        ConfigManager.llIIIlll[21] = lIlllIIlll(ConfigManager.llIIlIII[42], ConfigManager.llIIlIII[43]);
        ConfigManager.llIIIlll[22] = lIlllIIlIl(ConfigManager.llIIlIII[44], ConfigManager.llIIlIII[45]);
        ConfigManager.llIIIlll[23] = lIlllIIlll(ConfigManager.llIIlIII[46], ConfigManager.llIIlIII[47]);
        ConfigManager.llIIIlll[24] = lIlllIIlIl(ConfigManager.llIIlIII[48], ConfigManager.llIIlIII[49]);
        ConfigManager.llIIIlll[25] = lIlllIIlIl(ConfigManager.llIIlIII[50], ConfigManager.llIIlIII[51]);
        ConfigManager.llIIIlll[26] = lIlllIIlll(ConfigManager.llIIlIII[52], ConfigManager.llIIlIII[53]);
        ConfigManager.llIIIlll[27] = lIlllIIlIl("vRQT+g5Hwd0=", "pMhKw");
        ConfigManager.llIIIlll[28] = lIlllIIlIl("+p0f4h+WRDc=", "beqrU");
        ConfigManager.llIIIlll[29] = lIlllIIllI("sc1t+nn6iLHd6vNb3DkFuQ==", "QSdTq");
        ConfigManager.llIIIlll[30] = lIlllIIlIl("qsQv058qXWS3yWTy7tlbXg==", "NvlHD");
        ConfigManager.llIIIlll[31] = lIlllIIllI("GtLRtWuImW+UmnnSZZTX/gZhOYi8bc9lJzJBB3tHDnJJjZFAsjraO7nYLzyRNgBgZwokjYl6O+ZrPK1vd1iC9g==", "EATkz");
        ConfigManager.llIIIlll[32] = lIlllIIlll("DBY4STYKAzwMJksGPkkgChdsTCwKHilM", "ksLiB");
        ConfigManager.llIIIlll[33] = lIlllIIlll("dho0AgB2VDwCBDQdOwpFPRshTxAgHTsIRRYMJQMMMB0h", "StUoe");
        ConfigManager.llIIlIII = null;
    }

    private static void lIlllIlIIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ConfigManager.llIIlIII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIlllIIlll(String s, String s1) {
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

    private static String lIlllIIllI(String s, String s1) {
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

    private static String lIlllIIlIl(String s, String s1) {
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
