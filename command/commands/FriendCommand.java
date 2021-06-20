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
import me.explicit.config.ConfigManager;
import me.explicit.utils.ChatUtils;
import me.explicit.utils.Game;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class FriendCommand implements ICommand {

    private static final String[] lIIIIllII;
    private static String[] lIIIlIIll;

    public int compareTo(ICommand icommand) {
        return 0;
    }

    public String getCommandName() {
        return FriendCommand.lIIIIllII[0];
    }

    public String getCommandUsage(ICommandSender icommandsender) {
        return FriendCommand.lIIIIllII[1];
    }

    public List getCommandAliases() {
        ArrayList arraylist = new ArrayList();

        arraylist.add(FriendCommand.lIIIIllII[2]);
        arraylist.add(FriendCommand.lIIIIllII[3]);
        return arraylist;
    }

    public void processCommand(ICommandSender icommandsender, String[] astring) throws CommandException {}

    public List executeCommand(String[] astring) {
        ArrayList arraylist;

        (arraylist = new ArrayList()).clear();
        if (Explicit.destructed) {
            Game.Player().addChatMessage(new ChatComponentText(String.valueOf((new StringBuilder()).append(EnumChatFormatting.RED).append(FriendCommand.lIIIIllII[4]))));
            return arraylist;
        } else if (astring.length == 0) {
            if (Explicit.consolegui) {
                arraylist.add(FriendCommand.lIIIIllII[5]);
                arraylist.add(FriendCommand.lIIIIllII[6]);
                arraylist.add(FriendCommand.lIIIIllII[7]);
                arraylist.add(FriendCommand.lIIIIllII[8]);
                arraylist.add(FriendCommand.lIIIIllII[9]);
            } else {
                ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(FriendCommand.lIIIIllII[10])));
                ChatUtils.sendMessage(FriendCommand.lIIIIllII[11]);
                ChatUtils.sendMessage(FriendCommand.lIIIIllII[12]);
                ChatUtils.sendMessage(FriendCommand.lIIIIllII[13]);
                ChatUtils.sendMessage(FriendCommand.lIIIIllII[14]);
            }

            return arraylist;
        } else {
            if (astring[0].equalsIgnoreCase(FriendCommand.lIIIIllII[15])) {
                Explicit.instance.friendManager.getFriends().clear();
                ConfigManager.SaveFriendsFile();
                if (Explicit.consolegui) {
                    arraylist.add(FriendCommand.lIIIIllII[16]);
                } else {
                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(FriendCommand.lIIIIllII[17])));
                }
            } else if (astring.length > 0 && !astring[0].equalsIgnoreCase(FriendCommand.lIIIIllII[18]) && !astring[0].equalsIgnoreCase(FriendCommand.lIIIIllII[19]) && !astring[0].equalsIgnoreCase(FriendCommand.lIIIIllII[20]) && !astring[0].equalsIgnoreCase(FriendCommand.lIIIIllII[21])) {
                if (Explicit.consolegui) {
                    arraylist.add(FriendCommand.lIIIIllII[22]);
                    arraylist.add(FriendCommand.lIIIIllII[23]);
                    arraylist.add(FriendCommand.lIIIIllII[24]);
                    arraylist.add(FriendCommand.lIIIIllII[25]);
                    arraylist.add(FriendCommand.lIIIIllII[26]);
                } else {
                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(FriendCommand.lIIIIllII[27])));
                    ChatUtils.sendMessage(FriendCommand.lIIIIllII[28]);
                    ChatUtils.sendMessage(FriendCommand.lIIIIllII[29]);
                    ChatUtils.sendMessage(FriendCommand.lIIIIllII[30]);
                    ChatUtils.sendMessage(FriendCommand.lIIIIllII[31]);
                }
            } else if (astring[0].equalsIgnoreCase(FriendCommand.lIIIIllII[32]) && astring.length > 1) {
                Explicit.instance.friendManager.addFriend(astring[1]);
                if (Explicit.consolegui) {
                    arraylist.add(String.valueOf((new StringBuilder()).append(FriendCommand.lIIIIllII[33]).append(astring[1]).append(FriendCommand.lIIIIllII[34])));
                } else {
                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(FriendCommand.lIIIIllII[35]).append(astring[1]).append(FriendCommand.lIIIIllII[36])));
                }
            } else if (astring[0].equalsIgnoreCase(FriendCommand.lIIIIllII[37])) {
                if (Explicit.consolegui) {
                    arraylist.add(FriendCommand.lIIIIllII[38]);
                } else {
                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(FriendCommand.lIIIIllII[39])));
                }
            } else if (astring[0].equalsIgnoreCase(FriendCommand.lIIIIllII[40]) && astring.length > 1) {
                if (Explicit.instance.friendManager.getFriends().contains(astring[1])) {
                    Explicit.instance.friendManager.getFriends().remove(astring[1]);
                    ConfigManager.SaveFriendsFile();
                    if (Explicit.consolegui) {
                        arraylist.add(String.valueOf((new StringBuilder()).append(FriendCommand.lIIIIllII[41]).append(astring[1]).append(FriendCommand.lIIIIllII[42])));
                    } else {
                        ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(FriendCommand.lIIIIllII[43]).append(astring[1]).append(EnumChatFormatting.GOLD).append(FriendCommand.lIIIIllII[44])));
                    }
                } else if (Explicit.consolegui) {
                    arraylist.add(String.valueOf((new StringBuilder()).append(FriendCommand.lIIIIllII[45]).append(astring[1]).append(FriendCommand.lIIIIllII[46])));
                } else {
                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(FriendCommand.lIIIIllII[47]).append(astring[1]).append(EnumChatFormatting.GOLD).append(FriendCommand.lIIIIllII[48])));
                }
            } else if (astring[0].equalsIgnoreCase(FriendCommand.lIIIIllII[49])) {
                if (Explicit.instance.friendManager.getFriends().size() == 0) {
                    if (Explicit.consolegui) {
                        arraylist.add(FriendCommand.lIIIIllII[50]);
                    } else {
                        ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(FriendCommand.lIIIIllII[51])));
                    }
                } else {
                    if (Explicit.consolegui) {
                        arraylist.add(FriendCommand.lIIIIllII[52]);
                    }

                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(FriendCommand.lIIIIllII[53])));
                }

                Iterator iterator = Explicit.instance.friendManager.getFriends().iterator();

                while (iterator.hasNext()) {
                    String s = (String) iterator.next();

                    if (Explicit.consolegui) {
                        arraylist.add(s);
                    } else {
                        ChatUtils.sendMessage(s);
                    }
                }
            } else if (astring[0].equalsIgnoreCase(FriendCommand.lIIIIllII[54])) {
                if (Explicit.consolegui) {
                    arraylist.add(FriendCommand.lIIIIllII[55]);
                } else {
                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(FriendCommand.lIIIIllII[56])));
                }
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
        llllIIllIl();
        llllIIllII();
    }

    private static void llllIIllII() {
        lIIIIllII = new String[57];
        FriendCommand.lIIIIllII[0] = lllIllIllI(FriendCommand.lIIIlIIll[0], FriendCommand.lIIIlIIll[1]);
        FriendCommand.lIIIIllII[1] = lllIllIlll(FriendCommand.lIIIlIIll[2], FriendCommand.lIIIlIIll[3]);
        FriendCommand.lIIIIllII[2] = lllIllIllI(FriendCommand.lIIIlIIll[4], FriendCommand.lIIIlIIll[5]);
        FriendCommand.lIIIIllII[3] = lllIllIlll(FriendCommand.lIIIlIIll[6], FriendCommand.lIIIlIIll[7]);
        FriendCommand.lIIIIllII[4] = lllIlllIII(FriendCommand.lIIIlIIll[8], FriendCommand.lIIIlIIll[9]);
        FriendCommand.lIIIIllII[5] = lllIlllIII(FriendCommand.lIIIlIIll[10], FriendCommand.lIIIlIIll[11]);
        FriendCommand.lIIIIllII[6] = lllIllIllI(FriendCommand.lIIIlIIll[12], FriendCommand.lIIIlIIll[13]);
        FriendCommand.lIIIIllII[7] = lllIllIlll(FriendCommand.lIIIlIIll[14], FriendCommand.lIIIlIIll[15]);
        FriendCommand.lIIIIllII[8] = lllIllIlll(FriendCommand.lIIIlIIll[16], FriendCommand.lIIIlIIll[17]);
        FriendCommand.lIIIIllII[9] = lllIlllIII(FriendCommand.lIIIlIIll[18], FriendCommand.lIIIlIIll[19]);
        FriendCommand.lIIIIllII[10] = lllIllIlll(FriendCommand.lIIIlIIll[20], FriendCommand.lIIIlIIll[21]);
        FriendCommand.lIIIIllII[11] = lllIllIlll(FriendCommand.lIIIlIIll[22], FriendCommand.lIIIlIIll[23]);
        FriendCommand.lIIIIllII[12] = lllIllIllI(FriendCommand.lIIIlIIll[24], FriendCommand.lIIIlIIll[25]);
        FriendCommand.lIIIIllII[13] = lllIllIlll(FriendCommand.lIIIlIIll[26], FriendCommand.lIIIlIIll[27]);
        FriendCommand.lIIIIllII[14] = lllIlllIII(FriendCommand.lIIIlIIll[28], FriendCommand.lIIIlIIll[29]);
        FriendCommand.lIIIIllII[15] = lllIllIllI(FriendCommand.lIIIlIIll[30], FriendCommand.lIIIlIIll[31]);
        FriendCommand.lIIIIllII[16] = lllIlllIII(FriendCommand.lIIIlIIll[32], FriendCommand.lIIIlIIll[33]);
        FriendCommand.lIIIIllII[17] = lllIlllIII("tpmzinw1mIdBKy8+DhKUrXlDgOtJGqyh2RJV7NV1yimu2sau7Q7Okw==", "gAMlY");
        FriendCommand.lIIIIllII[18] = lllIllIlll("uCydWNPLK30=", "SWZLK");
        FriendCommand.lIIIIllII[19] = lllIlllIII("JPJeuxIbHK0=", "vStka");
        FriendCommand.lIIIIllII[20] = lllIlllIII("1rKFbJgshLM=", "pBAry");
        FriendCommand.lIIIIllII[21] = lllIlllIII("OqJ9fpYjs5Q=", "FNBoz");
        FriendCommand.lIIIIllII[22] = lllIlllIII("qVxqneI9XtmEyf8tunEKNQ==", "otyRo");
        FriendCommand.lIIIIllII[23] = lllIllIllI("ADIn", "AVCay");
        FriendCommand.lIIIIllII[24] = lllIllIllI("KyIpGyMc", "yGDtU");
        FriendCommand.lIIIIllII[25] = lllIllIlll("d/7AAy7Tl4c=", "NriPf");
        FriendCommand.lIIIIllII[26] = lllIllIllI("LgsmDA==", "bbUxY");
        FriendCommand.lIIIIllII[27] = lllIllIllI("JAYAEQgAGhMXX0U=", "etgde");
        FriendCommand.lIIIIllII[28] = lllIlllIII("cU6K5EzsohA=", "hmicp");
        FriendCommand.lIIIIllII[29] = lllIllIllI("NAQ3OAYD", "faZWp");
        FriendCommand.lIIIIllII[30] = lllIlllIII("qNeZ5XgPHik=", "IPUUA");
        FriendCommand.lIIIIllII[31] = lllIlllIII("uddOiQy9RUI=", "ZwhwR");
        FriendCommand.lIIIIllII[32] = lllIllIlll("DFhDRzkDWxg=", "nOpZm");
        FriendCommand.lIIIIllII[33] = lllIllIlll("ALLzc0XkxY0LhRCvjTnkp0vZ9wbqoYRO", "VDUHu");
        FriendCommand.lIIIIllII[34] = lllIllIlll("CtZWEGg+GYz77dnz08EP+v+bS0nri/IP", "JouKL");
        FriendCommand.lIIIIllII[35] = lllIllIllI("KgEEGwsKBwENAhUNRxkKHREDWEk=", "ytgxn");
        FriendCommand.lIIIIllII[36] = lllIllIllI("XVkVAUoDFhQcShwLCAsEHgpBAgMJDQ==", "zyanj");
        FriendCommand.lIIIIllII[37] = lllIllIllI("Iw8y", "BkVQo");
        FriendCommand.lIIIIllII[38] = lllIlllIII("5vdd78mtZaHkeaT8YlsXmOvVowaOta05", "ltTPy");
        FriendCommand.lIIIIllII[39] = lllIllIllI("WxEQKCgaExFhLBATQn0DFRoHfw==", "twbAM");
        FriendCommand.lIIIIllII[40] = lllIllIlll("++67LY9g/KQ=", "LxhQL");
        FriendCommand.lIIIIllII[41] = lllIllIlll("vZYWzmebtkgEtffmd+aaTs5q5uJWp/+P", "FIDyF");
        FriendCommand.lIIIIllII[42] = lllIllIlll("mihsyNaGPKVxG3ZhonvqXgAnm7bdvPSLcpafobwwvQQ=", "EauGB");
        FriendCommand.lIIIIllII[43] = lllIllIlll("gO4Wtc8Rj2fKH9ENuIZIKQ25T+cuDV6j", "FAWDc");
        FriendCommand.lIIIIllII[44] = lllIllIllI("ZFoJOx0uWhYmBzFaCTsbJhQLOlIvExw9", "CzoIr");
        FriendCommand.lIIIIllII[45] = lllIlllIII("hyOC56yDTfI=", "LzFKb");
        FriendCommand.lIIIIllII[46] = lllIllIlll("a+QKo+kycnJ4Lk7PLTJ3VZr3yjlB72fG35eqaIEh4p9EeXk0YrjZ0g==", "dSvTx");
        FriendCommand.lIIIIllII[47] = lllIllIllI("Vw==", "pRLca");
        FriendCommand.lIIIIllII[48] = lllIllIllI("fWQRDiV6KgkbdjwrEwEyeisITy81MRRPMCgtAwEyKWQKBiUu", "ZDfoV");
        FriendCommand.lIIIIllII[49] = lllIlllIII("FGyD1J6lNQw=", "bOUvZ");
        FriendCommand.lIIIIllII[50] = lllIlllIII("w1bPe3+nw3LwkppR7HqjI6sMGF6vCgNy", "hfVFK");
        FriendCommand.lIIIIllII[51] = lllIllIllI("GiEhWQwiODFZCixuMgsNJiAwCkR5Zg==", "CNTyd");
        FriendCommand.lIIIIllII[52] = lllIllIlll("0vqPDSwLPDEHCbvd9fm3jQ==", "lMXRc");
        FriendCommand.lIIIIllII[53] = lllIlllIII("JHr9fQgQztS/uq9oDQLeuw==", "HcvNv");
        FriendCommand.lIIIIllII[54] = lllIlllIII("ivuA3Xop6Uw=", "jnSTX");
        FriendCommand.lIIIIllII[55] = lllIllIlll("MIVYkvNuyOUpBkjRQ3h0n2Z8o7beUJvw", "ySNRQ");
        FriendCommand.lIIIIllII[56] = lllIllIllI("QVo0ICYLGTYhbxwSPz05C1duHC4DEmw=", "nwRRO");
        FriendCommand.lIIIlIIll = null;
    }

    private static void llllIIllIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        FriendCommand.lIIIlIIll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lllIllIlll(String s, String s1) {
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

    private static String lllIllIllI(String s, String s1) {
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

    private static String lllIlllIII(String s, String s1) {
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
