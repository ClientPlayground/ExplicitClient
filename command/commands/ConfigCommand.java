package me.explicit.command.commands;

import java.io.File;
import java.io.IOException;
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
import me.explicit.config.ConfigManager;
import me.explicit.utils.ChatUtils;
import me.explicit.utils.Game;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ConfigCommand implements ICommand {

    private static final String[] lIIlIIIl;
    private static String[] lIIlIllI;

    public int compareTo(ICommand icommand) {
        return 0;
    }

    public String getCommandName() {
        return ConfigCommand.lIIlIIIl[0];
    }

    public String getCommandUsage(ICommandSender icommandsender) {
        return ConfigCommand.lIIlIIIl[1];
    }

    public List getCommandAliases() {
        ArrayList arraylist = new ArrayList();

        arraylist.add(ConfigCommand.lIIlIIIl[2]);
        arraylist.add(ConfigCommand.lIIlIIIl[3]);
        return arraylist;
    }

    public void processCommand(ICommandSender icommandsender, String[] astring) throws CommandException {
        this.executeCommand(astring, true);
    }

    public List executeCommand(String[] astring, boolean flag) {
        ArrayList arraylist;

        (arraylist = new ArrayList()).clear();
        if (Explicit.destructed) {
            Game.Player().addChatMessage(new ChatComponentText(String.valueOf((new StringBuilder()).append(EnumChatFormatting.RED).append(ConfigCommand.lIIlIIIl[4]))));
            return arraylist;
        } else {
            boolean flag1 = Explicit.consolegui || !flag;

            if (astring.length == 0) {
                if (flag1) {
                    arraylist.add(ConfigCommand.lIIlIIIl[5]);
                    arraylist.add(ConfigCommand.lIIlIIIl[6]);
                    arraylist.add(ConfigCommand.lIIlIIIl[7]);
                    arraylist.add(ConfigCommand.lIIlIIIl[8]);
                    arraylist.add(ConfigCommand.lIIlIIIl[9]);
                } else {
                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(ConfigCommand.lIIlIIIl[10])));
                    ChatUtils.sendMessage(ConfigCommand.lIIlIIIl[11]);
                    ChatUtils.sendMessage(ConfigCommand.lIIlIIIl[12]);
                    ChatUtils.sendMessage(ConfigCommand.lIIlIIIl[13]);
                    ChatUtils.sendMessage(ConfigCommand.lIIlIIIl[14]);
                }

                return arraylist;
            } else if (astring.length >= 2 && astring[1].equalsIgnoreCase(ConfigCommand.lIIlIIIl[15])) {
                if (flag1) {
                    arraylist.add(String.valueOf((new StringBuilder()).append(ConfigCommand.lIIlIIIl[16]).append(astring[1]).append(ConfigCommand.lIIlIIIl[17])));
                } else {
                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(ConfigCommand.lIIlIIIl[18]).append(astring[1]).append(ConfigCommand.lIIlIIIl[19])));
                }

                return arraylist;
            } else {
                if (astring.length >= 1) {
                    if (astring[0].equalsIgnoreCase(ConfigCommand.lIIlIIIl[20])) {
                        if (ConfigManager.GetConfigs().size() == 0) {
                            if (flag1) {
                                arraylist.add(ConfigCommand.lIIlIIIl[21]);
                            } else {
                                ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(ConfigCommand.lIIlIIIl[22])));
                            }
                        } else {
                            if (flag1) {
                                arraylist.add(ConfigCommand.lIIlIIIl[23]);
                            } else {
                                ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(ConfigCommand.lIIlIIIl[24])));
                            }

                            Iterator iterator = ConfigManager.GetConfigs().iterator();

                            while (iterator.hasNext()) {
                                String s = (String) iterator.next();

                                if (flag1) {
                                    arraylist.add(s);
                                } else {
                                    ChatUtils.sendMessage(s);
                                }
                            }
                        }
                    } else if (astring[0].equalsIgnoreCase(ConfigCommand.lIIlIIIl[25])) {
                        if (astring.length >= 2) {
                            ConfigManager.getConfigFile(astring[1], true);
                            ConfigManager.SaveConfigFile(astring[1]);
                            if (flag1) {
                                arraylist.add(String.valueOf((new StringBuilder()).append(ConfigCommand.lIIlIIIl[26]).append(astring[1]).append(ConfigCommand.lIIlIIIl[27])));
                            } else {
                                ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(ConfigCommand.lIIlIIIl[28]).append(astring[1]).append(ConfigCommand.lIIlIIIl[29])));
                            }
                        } else if (flag1) {
                            arraylist.add(ConfigCommand.lIIlIIIl[30]);
                        } else {
                            ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(ConfigCommand.lIIlIIIl[31])));
                        }
                    } else {
                        boolean flag2;
                        File file;

                        if (astring[0].equalsIgnoreCase(ConfigCommand.lIIlIIIl[32])) {
                            astring[1].replaceAll(ConfigCommand.lIIlIIIl[33], ConfigCommand.lIIlIIIl[34]);
                            if (astring.length >= 2) {
                                flag2 = true;
                                file = new File(ConfigManager.dir, String.format(ConfigCommand.lIIlIIIl[35], new Object[] { astring[1]}));
                                if (!file.exists()) {
                                    flag2 = false;

                                    try {
                                        file.createNewFile();
                                    } catch (IOException ioexception) {
                                        ;
                                    }
                                }

                                if (flag2) {
                                    ConfigManager.LoadConfig(astring[1]);
                                    if (flag1) {
                                        arraylist.add(String.valueOf((new StringBuilder()).append(ConfigCommand.lIIlIIIl[36]).append(astring[1]).append(ConfigCommand.lIIlIIIl[37])));
                                    } else {
                                        ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(ConfigCommand.lIIlIIIl[38]).append(astring[1]).append(ConfigCommand.lIIlIIIl[39])));
                                    }
                                } else if (flag1) {
                                    arraylist.add(String.valueOf((new StringBuilder()).append(ConfigCommand.lIIlIIIl[40]).append(astring[1]).append(ConfigCommand.lIIlIIIl[41])));
                                } else {
                                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(ConfigCommand.lIIlIIIl[42]).append(astring[1]).append(EnumChatFormatting.GOLD).append(ConfigCommand.lIIlIIIl[43])));
                                }
                            } else if (flag1) {
                                arraylist.add(ConfigCommand.lIIlIIIl[44]);
                            } else {
                                ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(ConfigCommand.lIIlIIIl[45])));
                            }
                        } else if (astring[0].equalsIgnoreCase(ConfigCommand.lIIlIIIl[46])) {
                            if (astring.length >= 2) {
                                flag2 = true;
                                file = new File(ConfigManager.dir, String.format(ConfigCommand.lIIlIIIl[47], new Object[] { astring[1]}));
                                if (!file.exists()) {
                                    flag2 = false;
                                }

                                if (flag2) {
                                    if (file.delete()) {
                                        if (flag1) {
                                            arraylist.add(String.valueOf((new StringBuilder()).append(ConfigCommand.lIIlIIIl[48]).append(astring[1]).append(ConfigCommand.lIIlIIIl[49])));
                                        } else {
                                            ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(ConfigCommand.lIIlIIIl[50]).append(astring[1]).append(ConfigCommand.lIIlIIIl[51])));
                                        }
                                    } else if (flag1) {
                                        arraylist.add(String.valueOf((new StringBuilder()).append(ConfigCommand.lIIlIIIl[52]).append(astring[1]).append(ConfigCommand.lIIlIIIl[53])));
                                    } else {
                                        ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(ConfigCommand.lIIlIIIl[54]).append(astring[1]).append(ConfigCommand.lIIlIIIl[55])));
                                    }
                                } else if (flag1) {
                                    arraylist.add(String.valueOf((new StringBuilder()).append(ConfigCommand.lIIlIIIl[56]).append(astring[1]).append(ConfigCommand.lIIlIIIl[57])));
                                } else {
                                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(ConfigCommand.lIIlIIIl[58]).append(astring[1]).append(EnumChatFormatting.GOLD).append(ConfigCommand.lIIlIIIl[59])));
                                }
                            } else if (flag1) {
                                arraylist.add(ConfigCommand.lIIlIIIl[60]);
                            } else {
                                ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(ConfigCommand.lIIlIIIl[61])));
                            }
                        } else if (flag1) {
                            arraylist.add(ConfigCommand.lIIlIIIl[62]);
                            arraylist.add(ConfigCommand.lIIlIIIl[63]);
                            arraylist.add(ConfigCommand.lIIlIIIl[64]);
                            arraylist.add(ConfigCommand.lIIlIIIl[65]);
                            arraylist.add(ConfigCommand.lIIlIIIl[66]);
                        } else {
                            ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(ConfigCommand.lIIlIIIl[67])));
                            ChatUtils.sendMessage(ConfigCommand.lIIlIIIl[68]);
                            ChatUtils.sendMessage(ConfigCommand.lIIlIIIl[69]);
                            ChatUtils.sendMessage(ConfigCommand.lIIlIIIl[70]);
                            ChatUtils.sendMessage(ConfigCommand.lIIlIIIl[71]);
                        }
                    }
                }

                return arraylist;
            }
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
        lIIIIlIIlI();
        lIIIIlIIIl();
    }

    private static void lIIIIlIIIl() {
        lIIlIIIl = new String[72];
        ConfigCommand.lIIlIIIl[0] = lllllllII(ConfigCommand.lIIlIllI[0], ConfigCommand.lIIlIllI[1]);
        ConfigCommand.lIIlIIIl[1] = lllllllIl(ConfigCommand.lIIlIllI[2], ConfigCommand.lIIlIllI[3]);
        ConfigCommand.lIIlIIIl[2] = lllllllII(ConfigCommand.lIIlIllI[4], ConfigCommand.lIIlIllI[5]);
        ConfigCommand.lIIlIIIl[3] = lllllllIl(ConfigCommand.lIIlIllI[6], ConfigCommand.lIIlIllI[7]);
        ConfigCommand.lIIlIIIl[4] = lllllllIl(ConfigCommand.lIIlIllI[8], ConfigCommand.lIIlIllI[9]);
        ConfigCommand.lIIlIIIl[5] = lllllllII(ConfigCommand.lIIlIllI[10], ConfigCommand.lIIlIllI[11]);
        ConfigCommand.lIIlIIIl[6] = lllllllll(ConfigCommand.lIIlIllI[12], ConfigCommand.lIIlIllI[13]);
        ConfigCommand.lIIlIIIl[7] = lllllllII(ConfigCommand.lIIlIllI[14], ConfigCommand.lIIlIllI[15]);
        ConfigCommand.lIIlIIIl[8] = lllllllIl(ConfigCommand.lIIlIllI[16], ConfigCommand.lIIlIllI[17]);
        ConfigCommand.lIIlIIIl[9] = lllllllll(ConfigCommand.lIIlIllI[18], ConfigCommand.lIIlIllI[19]);
        ConfigCommand.lIIlIIIl[10] = lllllllIl(ConfigCommand.lIIlIllI[20], ConfigCommand.lIIlIllI[21]);
        ConfigCommand.lIIlIIIl[11] = lllllllII(ConfigCommand.lIIlIllI[22], ConfigCommand.lIIlIllI[23]);
        ConfigCommand.lIIlIIIl[12] = lllllllIl(ConfigCommand.lIIlIllI[24], ConfigCommand.lIIlIllI[25]);
        ConfigCommand.lIIlIIIl[13] = lllllllII(ConfigCommand.lIIlIllI[26], ConfigCommand.lIIlIllI[27]);
        ConfigCommand.lIIlIIIl[14] = lllllllIl(ConfigCommand.lIIlIllI[28], ConfigCommand.lIIlIllI[29]);
        ConfigCommand.lIIlIIIl[15] = lllllllIl(ConfigCommand.lIIlIllI[30], ConfigCommand.lIIlIllI[31]);
        ConfigCommand.lIIlIIIl[16] = lllllllll(ConfigCommand.lIIlIllI[32], ConfigCommand.lIIlIllI[33]);
        ConfigCommand.lIIlIIIl[17] = lllllllIl(ConfigCommand.lIIlIllI[34], ConfigCommand.lIIlIllI[35]);
        ConfigCommand.lIIlIIIl[18] = lllllllII("Nh0aIw9FBgA0VgYdBjcfAlIfOAINUhw5E0UcCTwTRVU=", "erhQv");
        ConfigCommand.lIIlIIIl[19] = lllllllll("2KyZ3G1WqhDQLyTSrGCpI0r5u7t1wDte", "liBbi");
        ConfigCommand.lIIlIIIl[20] = lllllllIl("T1q17aVoDt8=", "MXTgB");
        ConfigCommand.lIIlIIIl[21] = lllllllII("MD8eWg8IJg5aCQZwCBUJDzkMCQ==", "iPkzg");
        ConfigCommand.lIIlIIIl[22] = lllllllll("ZDVFFtAdWfumrpBOvgkc4Dxh+yTcXGC1", "nBCbk");
        ConfigCommand.lIIlIIIl[23] = lllllllIl("Jlh8ol/VEZyF3jJcWif5lg==", "IZnoq");
        ConfigCommand.lIIlIIIl[24] = lllllllII("IAsmIDkEF3Jm", "cdHFP");
        ConfigCommand.lIIlIIIl[25] = lllllllII("Axg3Bw==", "PyAbg");
        ConfigCommand.lIIlIIIl[26] = lllllllll("lskS6j2vAd5EL9E5a8fLeTB0jtQ0o3Ick8RkhfbA4STOOLVenR53apXI5CUyQwxh", "Fqxyh");
        ConfigCommand.lIIlIIIl[27] = lllllllll("cqIi6va/Iqg=", "GjILs");
        ConfigCommand.lIIlIIIl[28] = lllllllll("848f3orYgLXOoTWYWk2EMvvNFTBGpIItJwyXGONETik7rmdMhOl8j2IQu/bqUaxH", "IisRa");
        ConfigCommand.lIIlIIIl[29] = lllllllII("VQ==", "rrihw");
        ConfigCommand.lIIlIIIl[30] = lllllllII("PhImKBFRQWgsGwUHLihUGAAxKlRXIighEgIGZwEVBgR5", "kaGOt");
        ConfigCommand.lIIlIIIl[31] = lllllllIl("1O6IUd+2oo6BtKCVlBNw24Kn8J8cGdFqhB4d4Rbxw+qrIT1Ix4sVqQ==", "bjPce");
        ConfigCommand.lIIlIIIl[32] = lllllllII("JTUOKQ==", "iZoMl");
        ConfigCommand.lIIlIIIl[33] = lllllllll("1FWzYUEm800=", "oNHdH");
        ConfigCommand.lIIlIIIl[34] = lllllllII("", "LVgzf");
        ConfigCommand.lIIlIIIl[35] = lllllllll("PyAVH6WOF8s=", "HZIkR");
        ConfigCommand.lIIlIIIl[36] = lllllllIl("SM7Qzv3r2YAX0+9YxIhYgz2zS/jW/yKU/TR6TdBjci/oOh0UDVgMASmsdzRJnDad", "qBBuY");
        ConfigCommand.lIIlIIIl[37] = lllllllll("kvUKCJb79Is=", "nwkcl");
        ConfigCommand.lIIlIIIl[38] = lllllllIl("ySQhINb2DcGMN0p+ftkmRIqSY26/2NRa6U9MQRoHNJkZ4Gm5rd1I9s5BWe5qfvX7", "IOnzM");
        ConfigCommand.lIIlIIIl[39] = lllllllll("9J+axgLHW84=", "mgxPC");
        ConfigCommand.lIIlIIIl[40] = lllllllII("IDkBVQ0bPwIcCVQmDQEGVCUMEE4aMAkQTlM=", "tQdun");
        ConfigCommand.lIIlIIIl[41] = lllllllIl("lcRVq/V+KUeSJ7QZGFFESmzvMpvZ3CIU", "NgtcV");
        ConfigCommand.lIIlIIIl[42] = lllllllII("Ag0gVzk5CyMePXYSLAMydhEtEno4BCgSenE=", "VeEwZ");
        ConfigCommand.lIIlIIIl[43] = lllllllll("tIcL1EcOdHbztqknGV2DwAeMAJIjuPDS", "GIpDn");
        ConfigCommand.lIIlIIIl[44] = lllllllll("dry1pGJG8YlG3J4s1MhyaXghGG0KKrAhC34+B7ZPoJC4Fy1nMKva+A==", "ergbh");
        ConfigCommand.lIIlIIIl[45] = lllllllll("NL8tgyz5r+/BPqYFQQ3iXu3coBxWei6czim+9pxYngUTzPq0BT++zg==", "fSHyD");
        ConfigCommand.lIIlIIIl[46] = lllllllll("6aFp2LulEeQ=", "LIlzm");
        ConfigCommand.lIIlIIIl[47] = lllllllIl("NUqUvH5fRFI=", "NeLgS");
        ConfigCommand.lIIlIIIl[48] = lllllllll("q+/hRddLGg3YU57qoyS289E1c8qgAi676IQ6L4YRn/A2dAx3YFlBJgVyrpQ6zUvM", "nZcJG");
        ConfigCommand.lIIlIIIl[49] = lllllllll("bUD6Z3kpbp8=", "NOOxX");
        ConfigCommand.lIIlIIIl[50] = lllllllIl("agIHm5SZusZBIimN6yimn5b6yI8+xlctH6a8Sod8HMsc1W/E5sd1OwiZyB/2nyPl", "KpGTF");
        ConfigCommand.lIIlIIIl[51] = lllllllIl("x5pVmut6hjU=", "NekFE");
        ConfigCommand.lIIlIIIl[52] = lllllllll("UlD6uGcPRWRpCPrkI931jk4e1+Bkal1Nr4FtieD7dQGV81u/uox4cg==", "KMJXE");
        ConfigCommand.lIIlIIIl[53] = lllllllII("bW03ID0mKDU=", "JMQAT");
        ConfigCommand.lIIlIIIl[54] = lllllllIl("YzEWs3T7sPgy7izyHyRPhpmm/zyvEcUOU1kJYE1WW7jht98b64sbdA==", "fHWIr");
        ConfigCommand.lIIlIIIl[55] = lllllllll("CThCnVqeZChR/D4BNxpdYg==", "NVgKy");
        ConfigCommand.lIIlIIIl[56] = lllllllIl("XpVadfTzVcnsWWRUGYDLSuqV3tfjQMAoJgQjDx/vTzY=", "PVxSn");
        ConfigCommand.lIIlIIIl[57] = lllllllII("QUUyCzEVRTgLIEYALg0nEg==", "feVdT");
        ConfigCommand.lIIlIIIl[58] = lllllllll("2q50+h9hUN1SSwdcs1J5egwUT+m3tmu4+AuZqzkSdsQ=", "FdAKg");
        ConfigCommand.lIIlIIIl[59] = lllllllll("3olkJTBcKZ8VZJfQSogvQAZOA3vhAgF1", "ZaJNP");
        ConfigCommand.lIIlIIIl[60] = lllllllII("Fh0kChR5TmoOHi0ILApRJwspCAUmTnkuHi0ILApRDQ8oCE8=", "CnEmq");
        ConfigCommand.lIIlIIIl[61] = lllllllII("NxsPCBZYSEEMHAwOBwhTBg0CCgcHSFIsHAwOBwhTLAkDCk0=", "bhnos");
        ConfigCommand.lIIlIIIl[62] = lllllllII("AhMeNAEmDw0yVmM=", "CayAl");
        ConfigCommand.lIIlIIIl[63] = lllllllll("qJKr7DKtzR0=", "ejFlx");
        ConfigCommand.lIIlIIIl[64] = lllllllIl("zogaveo+J/U=", "xvWhZ");
        ConfigCommand.lIIlIIIl[65] = lllllllII("ADY6HwMh", "DSVzw");
        ConfigCommand.lIIlIIIl[66] = lllllllII("AhorEw==", "NsXgN");
        ConfigCommand.lIIlIIIl[67] = lllllllIl("NLXHrIs6M/1NPjpDYDvPbg==", "dVCDG");
        ConfigCommand.lIIlIIIl[68] = lllllllII("PBsdNw==", "ozkRh");
        ConfigCommand.lIIlIIIl[69] = lllllllII("GhsgMg==", "VtAVP");
        ConfigCommand.lIIlIIIl[70] = lllllllII("AB0/NyEh", "DxSRU");
        ConfigCommand.lIIlIIIl[71] = lllllllIl("ix52CjG+qvM=", "GwCUP");
        ConfigCommand.lIIlIllI = null;
    }

    private static void lIIIIlIIlI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ConfigCommand.lIIlIllI = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lllllllII(String s, String s1) {
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

    private static String lllllllIl(String s, String s1) {
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

    private static String lllllllll(String s, String s1) {
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
