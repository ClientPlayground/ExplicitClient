package me.explicit.command.commands;

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
import me.explicit.utils.ChatUtils;
import me.explicit.utils.Game;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class SettingsCommand implements ICommand {

    private static final String[] lllIlllI;
    private static String[] lllllIll;

    public int compareTo(ICommand icommand) {
        return 0;
    }

    public String getCommandName() {
        return SettingsCommand.lllIlllI[0];
    }

    public String getCommandUsage(ICommandSender icommandsender) {
        return SettingsCommand.lllIlllI[1];
    }

    public List getCommandAliases() {
        ArrayList arraylist = new ArrayList();

        arraylist.add(SettingsCommand.lllIlllI[2]);
        arraylist.add(SettingsCommand.lllIlllI[3]);
        return arraylist;
    }

    public void processCommand(ICommandSender icommandsender, String[] astring) throws CommandException {
        this.executeCommand(astring);
    }

    public List executeCommand(String[] astring) {
        boolean flag = Explicit.consolegui;
        ArrayList arraylist;

        (arraylist = new ArrayList()).clear();
        if (Explicit.destructed) {
            Game.Player().addChatMessage(new ChatComponentText(String.valueOf((new StringBuilder()).append(EnumChatFormatting.RED).append(SettingsCommand.lllIlllI[4]))));
            return arraylist;
        } else {
            if (astring.length < 3) {
                if (flag) {
                    arraylist.add(SettingsCommand.lllIlllI[5]);
                } else {
                    ChatUtils.sendMessage(SettingsCommand.lllIlllI[6]);
                }
            } else if (Explicit.instance.mm.getModuleByName(astring[0]) != null) {
                Module module = Explicit.instance.mm.getModuleByName(astring[0]);
                Setting setting = Explicit.instance.sm.getSettingByName(module, astring[1]);

                if (setting == null) {
                    if (flag) {
                        arraylist.add(String.valueOf((new StringBuilder()).append(SettingsCommand.lllIlllI[7]).append(astring[1]).append(SettingsCommand.lllIlllI[8])));
                    } else {
                        ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(SettingsCommand.lllIlllI[9]).append(astring[1]).append(SettingsCommand.lllIlllI[10])));
                    }
                } else {
                    String s = astring[2];

                    if (s != null) {
                        try {
                            if (setting.isCheck()) {
                                if (!s.equalsIgnoreCase(SettingsCommand.lllIlllI[11]) && !s.equalsIgnoreCase(SettingsCommand.lllIlllI[12])) {
                                    if (flag) {
                                        arraylist.add(String.valueOf((new StringBuilder()).append(SettingsCommand.lllIlllI[15]).append(astring[1]).append(SettingsCommand.lllIlllI[16]).append(s).append(SettingsCommand.lllIlllI[17])));
                                        arraylist.add(SettingsCommand.lllIlllI[18]);
                                    } else {
                                        ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(SettingsCommand.lllIlllI[19]).append(astring[1]).append(SettingsCommand.lllIlllI[20]).append(s).append(SettingsCommand.lllIlllI[21])));
                                        ChatUtils.sendMessage(SettingsCommand.lllIlllI[22]);
                                    }
                                } else {
                                    setting.setValBoolean(Boolean.parseBoolean(s));
                                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(SettingsCommand.lllIlllI[13]).append(setting.getName()).append(SettingsCommand.lllIlllI[14]).append(s)));
                                }
                            } else if (setting.isCombo()) {
                                boolean flag1 = false;
                                Iterator iterator = setting.getOptions().iterator();

                                while (iterator.hasNext()) {
                                    String s1 = (String) iterator.next();

                                    if (s1.equalsIgnoreCase(s)) {
                                        setting.setValString(s);
                                        flag1 = true;
                                    }
                                }

                                if (!flag1) {
                                    if (flag) {
                                        arraylist.add(String.valueOf((new StringBuilder()).append(SettingsCommand.lllIlllI[23]).append(astring[1]).append(SettingsCommand.lllIlllI[24]).append(s).append(SettingsCommand.lllIlllI[25])));
                                        arraylist.add(SettingsCommand.lllIlllI[26]);
                                    } else {
                                        ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(SettingsCommand.lllIlllI[27]).append(astring[1]).append(SettingsCommand.lllIlllI[28]).append(s).append(SettingsCommand.lllIlllI[29])));
                                        String s2 = SettingsCommand.lllIlllI[30];
                                        Iterator iterator1 = setting.getOptions().iterator();

                                        while (iterator1.hasNext()) {
                                            String s3 = (String) iterator1.next();

                                            if (s3 == setting.getOptions().get(setting.getOptions().size() - 2)) {
                                                s2 = String.valueOf((new StringBuilder()).append(s2).append(s3).append(SettingsCommand.lllIlllI[31]));
                                            } else if (s3 == setting.getOptions().get(setting.getOptions().size() - 1)) {
                                                s2 = String.valueOf((new StringBuilder()).append(s2).append(s3));
                                            } else {
                                                s2 = String.valueOf((new StringBuilder()).append(s2).append(s3).append(SettingsCommand.lllIlllI[32]));
                                            }
                                        }

                                        ChatUtils.sendMessage(s2);
                                    }
                                } else {
                                    setting.setValString(s);
                                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(SettingsCommand.lllIlllI[33]).append(setting.getName()).append(SettingsCommand.lllIlllI[34]).append(s)));
                                }
                            } else if (setting.isSlider()) {
                                if (Double.parseDouble(s) >= setting.getMin() && Double.parseDouble(s) <= setting.getMax()) {
                                    setting.setValDouble(Double.parseDouble(s));
                                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(SettingsCommand.lllIlllI[44]).append(setting.getName()).append(SettingsCommand.lllIlllI[45]).append(s)));
                                } else if (flag) {
                                    arraylist.add(String.valueOf((new StringBuilder()).append(SettingsCommand.lllIlllI[35]).append(astring[1]).append(SettingsCommand.lllIlllI[36]).append(s).append(SettingsCommand.lllIlllI[37])));
                                    arraylist.add(SettingsCommand.lllIlllI[38]);
                                } else {
                                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(SettingsCommand.lllIlllI[39]).append(astring[1]).append(SettingsCommand.lllIlllI[40]).append(s).append(SettingsCommand.lllIlllI[41])));
                                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(SettingsCommand.lllIlllI[42]).append(setting.getMin()).append(SettingsCommand.lllIlllI[43]).append(setting.getMax())));
                                }
                            }
                        } catch (NumberFormatException numberformatexception) {
                            if (flag) {
                                arraylist.add(String.valueOf((new StringBuilder()).append(SettingsCommand.lllIlllI[46]).append(astring[1]).append(SettingsCommand.lllIlllI[47]).append(s).append(SettingsCommand.lllIlllI[48])));
                                arraylist.add(SettingsCommand.lllIlllI[49]);
                            } else {
                                ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(SettingsCommand.lllIlllI[50]).append(astring[1]).append(SettingsCommand.lllIlllI[51]).append(s).append(SettingsCommand.lllIlllI[52])));
                                ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(SettingsCommand.lllIlllI[53]).append(setting.getMin()).append(SettingsCommand.lllIlllI[54]).append(setting.getMax())));
                            }
                        }
                    }
                }
            } else if (flag) {
                arraylist.add(String.valueOf((new StringBuilder()).append(SettingsCommand.lllIlllI[55]).append(astring[0]).append(SettingsCommand.lllIlllI[56])));
            } else {
                ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(SettingsCommand.lllIlllI[57]).append(astring[0]).append(SettingsCommand.lllIlllI[58])));
            }

            return arraylist;
        }
    }

    public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
        return true;
    }

    public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring, BlockPos blockpos) {
        return null;
    }

    public boolean isUsernameIndex(String[] astring, int i) {
        return false;
    }

    public int compareTo(Object object) {
        return this.compareTo((ICommand) object);
    }

    static {
        llIllllllI();
        llIlllllII();
    }

    private static void llIlllllII() {
        lllIlllI = new String[59];
        SettingsCommand.lllIlllI[0] = llIllIIlIl(SettingsCommand.lllllIll[0], SettingsCommand.lllllIll[1]);
        SettingsCommand.lllIlllI[1] = llIllIIlll(SettingsCommand.lllllIll[2], SettingsCommand.lllllIll[3]);
        SettingsCommand.lllIlllI[2] = llIllIIlIl(SettingsCommand.lllllIll[4], SettingsCommand.lllllIll[5]);
        SettingsCommand.lllIlllI[3] = llIllIIlll(SettingsCommand.lllllIll[6], SettingsCommand.lllllIll[7]);
        SettingsCommand.lllIlllI[4] = llIllIlIII(SettingsCommand.lllllIll[8], SettingsCommand.lllllIll[9]);
        SettingsCommand.lllIlllI[5] = llIllIlIII(SettingsCommand.lllllIll[10], SettingsCommand.lllllIll[11]);
        SettingsCommand.lllIlllI[6] = llIllIlIII(SettingsCommand.lllllIll[12], SettingsCommand.lllllIll[13]);
        SettingsCommand.lllIlllI[7] = llIllIlIII(SettingsCommand.lllllIll[14], SettingsCommand.lllllIll[15]);
        SettingsCommand.lllIlllI[8] = llIllIlIII(SettingsCommand.lllllIll[16], SettingsCommand.lllllIll[17]);
        SettingsCommand.lllIlllI[9] = llIllIIlll(SettingsCommand.lllllIll[18], SettingsCommand.lllllIll[19]);
        SettingsCommand.lllIlllI[10] = llIllIIlll(SettingsCommand.lllllIll[20], SettingsCommand.lllllIll[21]);
        SettingsCommand.lllIlllI[11] = llIllIIlIl(SettingsCommand.lllllIll[22], SettingsCommand.lllllIll[23]);
        SettingsCommand.lllIlllI[12] = llIllIlIII(SettingsCommand.lllllIll[24], SettingsCommand.lllllIll[25]);
        SettingsCommand.lllIlllI[13] = llIllIlIII(SettingsCommand.lllllIll[26], SettingsCommand.lllllIll[27]);
        SettingsCommand.lllIlllI[14] = llIllIlIII("QiY7Tg==", "bRTnx");
        SettingsCommand.lllIlllI[15] = llIllIlIII("ECo1OgtvZQ==", "CEGHr");
        SettingsCommand.lllIlllI[16] = llIllIlIII("UBJzHzgbFDZJOhYPPQYtVwM2SX4=", "waSiY");
        SettingsCommand.lllIlllI[17] = llIllIIlll("w1HHdppc3CQ=", "vaKBV");
        SettingsCommand.lllIlllI[18] = llIllIIlll("BBTVPZNlta/ZGXWMF2kuqaXSp6PDeaVGu+UBkKBMdi8=", "IVPru");
        SettingsCommand.lllIlllI[19] = llIllIIlIl("SdeTjuqOUB8=", "SBjsX");
        SettingsCommand.lllIlllI[20] = llIllIlIII("didWIzE9IRN1MzA6GDokcTYTdXc=", "QTvUP");
        SettingsCommand.lllIlllI[21] = llIllIIlll("6lfLXIRzLb4=", "KdGwN");
        SettingsCommand.lllIlllI[22] = llIllIIlll("ERo9/Ewjey3AkVTgk09SAFQgiwSRq13xown134bqHZU=", "YfhgD");
        SettingsCommand.lllIlllI[23] = llIllIIlll("vxbhbreplMw=", "NLhHK");
        SettingsCommand.lllIlllI[24] = llIllIIlIl("uqbKv0jkSkqu9lxIoRbVauZkfbEQVfWn", "IUEYo");
        SettingsCommand.lllIlllI[25] = llIllIIlll("sBEk592zorU=", "yUWRY");
        SettingsCommand.lllIlllI[26] = llIllIIlll("FzYZyQ7H9huokXHxnIcV2fVe9U8/UhOKCHdxYasTZYw=", "NEDaW");
        SettingsCommand.lllIlllI[27] = llIllIIlIl("KjNCDBfZtPM=", "klNwH");
        SettingsCommand.lllIlllI[28] = llIllIIlll("zo9y7o1cOGc7gNLOsiIHyA1PLKBoP2Yc", "oVEaQ");
        SettingsCommand.lllIlllI[29] = llIllIlIII("bg==", "Iqtfa");
        SettingsCommand.lllIlllI[30] = llIllIlIII("MwB1DRUUVDoAGANUNwtU", "ztUnt");
        SettingsCommand.lllIlllI[31] = llIllIIlll("3dHtoMhR3IQ=", "lzHNr");
        SettingsCommand.lllIlllI[32] = llIllIIlIl("koICllPqhB8=", "CrqhC");
        SettingsCommand.lllIlllI[33] = llIllIlIII("AT4gKxAhOCU9GT4yYzsQJms=", "RKCHu");
        SettingsCommand.lllIlllI[34] = llIllIIlIl("57ijTaAnfyg=", "XUxKe");
        SettingsCommand.lllIlllI[35] = llIllIIlll("fLv3T7a3eTY=", "jCANs");
        SettingsCommand.lllIlllI[36] = llIllIlIII("fRViJS02EydzLzsILDw4egQnc2s=", "ZfBSL");
        SettingsCommand.lllIlllI[37] = llIllIlIII("VA==", "sBjgH");
        SettingsCommand.lllIlllI[38] = llIllIlIII("BjpLKhshbgQnFjZuCSxaOzweLFogPEsvGyM9Dg==", "ONkIz");
        SettingsCommand.lllIlllI[39] = llIllIIlll("RCWMcSmRFmM=", "XVPpI");
        SettingsCommand.lllIlllI[40] = llIllIIlll("4IwYAmUsRHq8pYCQlWerad1CN9+OGZPN", "XybuT");
        SettingsCommand.lllIlllI[41] = llIllIIlIl("abaisLt8K6Q=", "EFAhw");
        SettingsCommand.lllIlllI[42] = llIllIIlIl("IidsRqhYIapKhb7VD/nd/0oSxpoRGJOOAK4fzDnManT/tD9LizFv2g==", "WHUfg");
        SettingsCommand.lllIlllI[43] = llIllIlIII("Rg48Fmc=", "foRrG");
        SettingsCommand.lllIlllI[44] = llIllIIlll("PzY2r6639iPVfiVxOvkQdSFy9l9ytLEq", "drTOK");
        SettingsCommand.lllIlllI[45] = llIllIIlll("0HmXnCoypx0=", "ndQRj");
        SettingsCommand.lllIlllI[46] = llIllIIlll("Ed7DLTnsAuI=", "gWWPl");
        SettingsCommand.lllIlllI[47] = llIllIlIII("fiNuPxc1JStpFTg+ICYCeTIraVE=", "YPNIv");
        SettingsCommand.lllIlllI[48] = llIllIIlIl("SNFgJbrXIV4=", "LqezW");
        SettingsCommand.lllIlllI[49] = llIllIlIII("ATlDNi0mbQw7IDFtATBsPD8WMGwnP0MzLSQ+Bg==", "HMcUL");
        SettingsCommand.lllIlllI[50] = llIllIlIII("NQ49ERJKQQ==", "faOck");
        SettingsCommand.lllIlllI[51] = llIllIIlll("OWvzsYaVMOSj7+b5158v98RA81eKX/8u", "KDYAX");
        SettingsCommand.lllIlllI[52] = llIllIlIII("Qw==", "dKtlB");
        SettingsCommand.lllIlllI[53] = llIllIIlIl("1BVrKDaQkX5PJCAoRoGbzbBn3yUY+gnpT8X3iqrtru0FVswFoAfITA==", "BaPEl");
        SettingsCommand.lllIlllI[54] = llIllIlIII("YwUDAFg=", "Cdmdx");
        SettingsCommand.lllIlllI[55] = llIllIlIII("Ogg9FBtFRzsOB0kKIAIXBQJvQQ==", "igOfb");
        SettingsCommand.lllIlllI[56] = llIllIlIII("U0YLGBIHCEgDVxEeBgQD", "tfoww");
        SettingsCommand.lllIlllI[57] = llIllIIlIl("HAy9LyBDRAUnHQbKQYjqjEQgZ9/7yYzI", "CnwJL");
        SettingsCommand.lllIlllI[58] = llIllIIlll("iD/7qNBwzyhZtA/fsUppDw==", "ntgoU");
        SettingsCommand.lllllIll = null;
    }

    private static void llIllllllI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        SettingsCommand.lllllIll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIllIIlll(String s, String s1) {
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

    private static String llIllIIlIl(String s, String s1) {
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

    private static String llIllIlIII(String s, String s1) {
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
