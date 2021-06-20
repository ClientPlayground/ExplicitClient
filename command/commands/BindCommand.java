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
import me.explicit.utils.ChatUtils;
import me.explicit.utils.Game;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;

public class BindCommand implements ICommand {

    private static final String[] lIIllIllI;
    private static String[] lIlIIIlII;

    public int compareTo(ICommand icommand) {
        return 0;
    }

    public String getCommandName() {
        return BindCommand.lIIllIllI[0];
    }

    public String getCommandUsage(ICommandSender icommandsender) {
        return BindCommand.lIIllIllI[1];
    }

    public List getCommandAliases() {
        ArrayList arraylist = new ArrayList();

        arraylist.add(BindCommand.lIIllIllI[2]);
        return arraylist;
    }

    public void processCommand(ICommandSender icommandsender, String[] astring) throws CommandException {
        this.executeCommand(astring);
    }

    public List executeCommand(String[] astring) {
        ArrayList arraylist;

        (arraylist = new ArrayList()).clear();
        if (Explicit.destructed) {
            Game.Player().addChatMessage(new ChatComponentText(String.valueOf((new StringBuilder()).append(EnumChatFormatting.RED).append(BindCommand.lIIllIllI[3]))));
            return arraylist;
        } else {
            if (astring.length == 0) {
                if (Explicit.consolegui) {
                    arraylist.add(BindCommand.lIIllIllI[4]);
                    arraylist.add(BindCommand.lIIllIllI[5]);
                    arraylist.add(BindCommand.lIIllIllI[6]);
                    arraylist.add(BindCommand.lIIllIllI[7]);
                } else {
                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(BindCommand.lIIllIllI[8])));
                    ChatUtils.sendMessage(BindCommand.lIIllIllI[9]);
                    ChatUtils.sendMessage(BindCommand.lIIllIllI[10]);
                    ChatUtils.sendMessage(BindCommand.lIIllIllI[11]);
                }
            } else if (astring[0].equalsIgnoreCase(BindCommand.lIIllIllI[12])) {
                if (astring.length >= 2 && Explicit.instance.mm.getModuleByName(astring[1]) != null) {
                    if (astring.length == 3 && Keyboard.getKeyIndex(astring[2].toUpperCase()) != 0) {
                        Explicit.instance.mm.getModuleByName(astring[1]).setKey(Keyboard.getKeyIndex(astring[2].toUpperCase()));
                        if (Explicit.consolegui) {
                            arraylist.add(String.valueOf((new StringBuilder()).append(BindCommand.lIIllIllI[13]).append(astring[1]).append(BindCommand.lIIllIllI[14]).append(astring[2].toUpperCase()).append(BindCommand.lIIllIllI[15])));
                        } else {
                            ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(BindCommand.lIIllIllI[16]).append(astring[1]).append(BindCommand.lIIllIllI[17]).append(astring[2].toUpperCase()).append(BindCommand.lIIllIllI[18])));
                        }
                    } else if (Keyboard.getKeyIndex(astring[2].toUpperCase()) == 0) {
                        if (Explicit.consolegui) {
                            arraylist.add(String.valueOf((new StringBuilder()).append(BindCommand.lIIllIllI[19]).append(astring[2].toUpperCase()).append(BindCommand.lIIllIllI[20])));
                        } else {
                            ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(BindCommand.lIIllIllI[21]).append(astring[2].toUpperCase()).append(BindCommand.lIIllIllI[22])));
                        }
                    } else if (Explicit.consolegui) {
                        arraylist.add(BindCommand.lIIllIllI[23]);
                    } else {
                        ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(BindCommand.lIIllIllI[24])));
                    }
                } else if (astring.length >= 2 && Explicit.instance.mm.getModuleByName(astring[1]) == null) {
                    if (Explicit.consolegui) {
                        arraylist.add(String.valueOf((new StringBuilder()).append(BindCommand.lIIllIllI[25]).append(astring[1].toUpperCase()).append(BindCommand.lIIllIllI[26])));
                    } else {
                        ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(BindCommand.lIIllIllI[27]).append(astring[1].toUpperCase()).append(BindCommand.lIIllIllI[28])));
                    }
                } else if (astring.length == 1) {
                    if (Explicit.consolegui) {
                        arraylist.add(BindCommand.lIIllIllI[29]);
                    } else {
                        ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(BindCommand.lIIllIllI[30])));
                    }
                }
            } else if (astring[0].equalsIgnoreCase(BindCommand.lIIllIllI[31])) {
                Iterator iterator = Explicit.instance.mm.getModules().iterator();

                while (iterator.hasNext()) {
                    Module module = (Module) iterator.next();

                    module.setKey(0);
                }

                if (Explicit.consolegui) {
                    arraylist.add(BindCommand.lIIllIllI[32]);
                } else {
                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(BindCommand.lIIllIllI[33])));
                }
            } else if (astring[0].equalsIgnoreCase(BindCommand.lIIllIllI[34])) {
                if (astring.length >= 2 && Explicit.instance.mm.getModuleByName(astring[1]) != null) {
                    Explicit.instance.mm.getModuleByName(astring[1]).setKey(0);
                    if (Explicit.consolegui) {
                        arraylist.add(String.valueOf((new StringBuilder()).append(BindCommand.lIIllIllI[35]).append(astring[1]).append(BindCommand.lIIllIllI[36])));
                    } else {
                        ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(BindCommand.lIIllIllI[37]).append(astring[1]).append(BindCommand.lIIllIllI[38])));
                    }
                } else if (astring.length >= 2) {
                    if (Explicit.consolegui) {
                        arraylist.add(String.valueOf((new StringBuilder()).append(BindCommand.lIIllIllI[39]).append(astring[1]).append(BindCommand.lIIllIllI[40])));
                    } else {
                        ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(BindCommand.lIIllIllI[41]).append(astring[1]).append(BindCommand.lIIllIllI[42])));
                    }
                } else if (Explicit.consolegui) {
                    arraylist.add(BindCommand.lIIllIllI[43]);
                } else {
                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(BindCommand.lIIllIllI[44])));
                }
            } else if (Explicit.consolegui) {
                arraylist.add(BindCommand.lIIllIllI[45]);
                arraylist.add(BindCommand.lIIllIllI[46]);
                arraylist.add(BindCommand.lIIllIllI[47]);
                arraylist.add(BindCommand.lIIllIllI[48]);
            } else {
                ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(BindCommand.lIIllIllI[49])));
                ChatUtils.sendMessage(BindCommand.lIIllIllI[50]);
                ChatUtils.sendMessage(BindCommand.lIIllIllI[51]);
                ChatUtils.sendMessage(BindCommand.lIIllIllI[52]);
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
        lIIIlllIlll();
        lIIIlllIllI();
    }

    private static void lIIIlllIllI() {
        lIIllIllI = new String[53];
        BindCommand.lIIllIllI[0] = lIIIIllllII(BindCommand.lIlIIIlII[0], BindCommand.lIlIIIlII[1]);
        BindCommand.lIIllIllI[1] = lIIIIllllII(BindCommand.lIlIIIlII[2], BindCommand.lIlIIIlII[3]);
        BindCommand.lIIllIllI[2] = lIIIIllllII(BindCommand.lIlIIIlII[4], BindCommand.lIlIIIlII[5]);
        BindCommand.lIIllIllI[3] = lIIIIllllII(BindCommand.lIlIIIlII[6], BindCommand.lIlIIIlII[7]);
        BindCommand.lIIllIllI[4] = lIIIIllllII(BindCommand.lIlIIIlII[8], BindCommand.lIlIIIlII[9]);
        BindCommand.lIIllIllI[5] = lIIIIllllIl(BindCommand.lIlIIIlII[10], BindCommand.lIlIIIlII[11]);
        BindCommand.lIIllIllI[6] = lIIIIllllll(BindCommand.lIlIIIlII[12], BindCommand.lIlIIIlII[13]);
        BindCommand.lIIllIllI[7] = lIIIIllllII(BindCommand.lIlIIIlII[14], BindCommand.lIlIIIlII[15]);
        BindCommand.lIIllIllI[8] = lIIIIllllII(BindCommand.lIlIIIlII[16], BindCommand.lIlIIIlII[17]);
        BindCommand.lIIllIllI[9] = lIIIIllllII(BindCommand.lIlIIIlII[18], BindCommand.lIlIIIlII[19]);
        BindCommand.lIIllIllI[10] = lIIIIllllII(BindCommand.lIlIIIlII[20], BindCommand.lIlIIIlII[21]);
        BindCommand.lIIllIllI[11] = lIIIIllllll(BindCommand.lIlIIIlII[22], BindCommand.lIlIIIlII[23]);
        BindCommand.lIIllIllI[12] = lIIIIllllIl(BindCommand.lIlIIIlII[24], BindCommand.lIlIIIlII[25]);
        BindCommand.lIIllIllI[13] = lIIIIllllll(BindCommand.lIlIIIlII[26], BindCommand.lIlIIIlII[27]);
        BindCommand.lIIllIllI[14] = lIIIIllllII(BindCommand.lIlIIIlII[28], BindCommand.lIlIIIlII[29]);
        BindCommand.lIIllIllI[15] = lIIIIllllll(BindCommand.lIlIIIlII[30], BindCommand.lIlIIIlII[31]);
        BindCommand.lIIllIllI[16] = lIIIIllllIl(BindCommand.lIlIIIlII[32], BindCommand.lIlIIIlII[33]);
        BindCommand.lIIllIllI[17] = lIIIIllllII(BindCommand.lIlIIIlII[34], BindCommand.lIlIIIlII[35]);
        BindCommand.lIIllIllI[18] = lIIIIllllIl(BindCommand.lIlIIIlII[36], BindCommand.lIlIIIlII[37]);
        BindCommand.lIIllIllI[19] = lIIIIllllIl(BindCommand.lIlIIIlII[38], BindCommand.lIlIIIlII[39]);
        BindCommand.lIIllIllI[20] = lIIIIllllIl("laeKFUSipqpA2qjU5uuz3+oBiX6PXcqo", "PcVBG");
        BindCommand.lIIllIllI[21] = lIIIIllllII("NigKKy5JZwwxMkUsHSB3Qg==", "eGxYW");
        BindCommand.lIIllIllI[22] = lIIIIllllll("0N5JHvJzy2wnVnwOZwaEE6NNfWXmV1ea", "Hhquu");
        BindCommand.lIIllIllI[23] = lIIIIllllII("DQAgCxViU24OGTYXYR8VLFN9IR88Bi0JTnhPCgkJZg==", "XsAlp");
        BindCommand.lIIllIllI[24] = lIIIIllllII("BBwtFA9rT2MRAz8LbAAPJU9wPgU1GiAWVHFTBxYTbw==", "QoLsj");
        BindCommand.lIIllIllI[25] = lIIIIllllII("MAIHOgBPTQEgHEMAGiwMDwhVbw==", "cmuHy");
        BindCommand.lIIllIllI[26] = lIIIIllllII("cFYxPAIkVjs8E3cTLToUIw==", "WvUSg");
        BindCommand.lIIllIllI[27] = lIIIIllllIl("dnMuvjvGZm62GLI7AI53Mt4tInriJ4Gg", "jhWIj");
        BindCommand.lIIllIllI[28] = lIIIIllllll("xTQhZhJsyzDCXayS8CcreU+GZPNvICNf", "Yupfi");
        BindCommand.lIIllIllI[29] = lIIIIllllIl("v27ztdw7eCBud9rm1T+OjD1sxuuG1UbYU/mTtDsKZKs=", "ImpgB");
        BindCommand.lIIllIllI[30] = lIIIIllllII("AjEMBgRtYkIDCDkmTRIEI2JRLA4zNwEEX3d+JgQYaQ==", "WBmaa");
        BindCommand.lIIllIllI[31] = lIIIIllllII("NxAXGRUANBYa", "euzvc");
        BindCommand.lIIllIllI[32] = lIIIIllllll("G8dT3qog5pPlBKObMnLeW0Ck8onEGcBoYzUF+Nle5uXzLs9T1SoOpg==", "SOvaM");
        BindCommand.lIIllIllI[33] = lIIIIllllll("O58K0EbpPWLrQKP+T/lTZmOtvf4oIjQsHZZkUdf1QzwBXE6nWH8fQg==", "HgeXr");
        BindCommand.lIIllIllI[34] = lIIIIllllIl("60DSLA/3WB4=", "kBGLh");
        BindCommand.lIIllIllI[35] = lIIIIllllII("IS8XMSwBKRInJR4jVCcnEDUBPC1SfQ==", "rZtRI");
        BindCommand.lIIllIllI[36] = lIIIIllllIl("E4qO5fibs48=", "ozqZU");
        BindCommand.lIIllIllI[37] = lIIIIllllII("CxsPLRcrHQo7HjQXTDscOgEZIBZ4SQ==", "XnlNr");
        BindCommand.lIIllIllI[38] = lIIIIllllIl("4Lmi+ophXx0=", "XOWZI");
        BindCommand.lIIllIllI[39] = lIIIIllllII("FTkEBjVqdgIcKWY7GRA5KjNWUw==", "FVvtL");
        BindCommand.lIIllIllI[40] = lIIIIllllll("AhGYxmvcaLQUa76lzQswQObXv2l1MdiT", "WqdKD");
        BindCommand.lIIllIllI[41] = lIIIIllllll("ojNpEFvdFfcBPiuSfNmHV56OdJCUVdPE", "MWVuM");
        BindCommand.lIIllIllI[42] = lIIIIllllIl("LsbxW7idyGbMCjzQHrCI2V/lvd7DCprK", "JgDWB");
        BindCommand.lIIllIllI[43] = lIIIIllllII("BTAGEBVqY0gVGT4nRwUVPSwRElBsDggTBTwmWQ==", "PCgwp");
        BindCommand.lIIllIllI[44] = lIIIIllllll("r8TgN+SLxK6Q3NM0EPno9gs6xkVpCaIRGrnR1Ydcc6U=", "hFfli");
        BindCommand.lIIllIllI[45] = lIIIIllllII("OTAwISIdLCMndVg=", "xBWTO");
        BindCommand.lIIllIllI[46] = lIIIIllllll("mTcbmHDKyIk=", "aBkqL");
        BindCommand.lIIllIllI[47] = lIIIIllllII("CgAVDgY9", "Xexap");
        BindCommand.lIIllIllI[48] = lIIIIllllII("HhAOBDApNA8H", "LuckF");
        BindCommand.lIIllIllI[49] = lIIIIllllIl("2mt3Lx8C/IbCtjVTJpcSng==", "hGRoB");
        BindCommand.lIIllIllI[50] = lIIIIllllll("48DW8MxRx+A=", "iHbGm");
        BindCommand.lIIllIllI[51] = lIIIIllllIl("VoSCxdZ3Fl0=", "RuyUJ");
        BindCommand.lIIllIllI[52] = lIIIIllllll("izmXiENsuhl4W3Bi2MOy2w==", "mFnEL");
        BindCommand.lIlIIIlII = null;
    }

    private static void lIIIlllIlll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        BindCommand.lIlIIIlII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIIllllIl(String s, String s1) {
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

    private static String lIIIIllllll(String s, String s1) {
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

    private static String lIIIIllllII(String s, String s1) {
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
