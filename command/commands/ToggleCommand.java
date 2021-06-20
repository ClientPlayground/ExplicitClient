package me.explicit.command.commands;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.utils.ChatUtils;
import me.explicit.utils.Game;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ToggleCommand implements ICommand {

    private static final String[] lIIIIlIll;
    private static String[] lIIIIllll;

    public int compareTo(ICommand icommand) {
        return 0;
    }

    public String getCommandName() {
        return ToggleCommand.lIIIIlIll[0];
    }

    public String getCommandUsage(ICommandSender icommandsender) {
        return ToggleCommand.lIIIIlIll[1];
    }

    public List getCommandAliases() {
        ArrayList arraylist = new ArrayList();

        arraylist.add(ToggleCommand.lIIIIlIll[2]);
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
            Game.Player().addChatMessage(new ChatComponentText(String.valueOf((new StringBuilder()).append(EnumChatFormatting.RED).append(ToggleCommand.lIIIIlIll[3]))));
            return arraylist;
        } else {
            if (astring.length == 0) {
                if (flag) {
                    arraylist.add(ToggleCommand.lIIIIlIll[4]);
                } else {
                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(ToggleCommand.lIIIIlIll[5])));
                }
            } else if (Explicit.instance.mm.getModuleByName(astring[0]) != null) {
                Explicit.instance.mm.getModuleByName(astring[0]).toggle();
                if (flag) {
                    arraylist.add(String.valueOf((new StringBuilder()).append(ToggleCommand.lIIIIlIll[6]).append(astring[0])));
                } else {
                    ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(ToggleCommand.lIIIIlIll[7]).append(astring[0])));
                }
            } else if (flag) {
                arraylist.add(String.valueOf((new StringBuilder()).append(ToggleCommand.lIIIIlIll[8]).append(astring[0]).append(ToggleCommand.lIIIIlIll[9])));
            } else {
                ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(ToggleCommand.lIIIIlIll[10]).append(astring[0]).append(ToggleCommand.lIIIIlIll[11])));
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
        llllIIIIIl();
        llllIIIIII();
    }

    private static void llllIIIIII() {
        lIIIIlIll = new String[12];
        ToggleCommand.lIIIIlIll[0] = lllIllIlIl(ToggleCommand.lIIIIllll[0], ToggleCommand.lIIIIllll[1]);
        ToggleCommand.lIIIIlIll[1] = lllIllIlIl(ToggleCommand.lIIIIllll[2], ToggleCommand.lIIIIllll[3]);
        ToggleCommand.lIIIIlIll[2] = lllIlllIll(ToggleCommand.lIIIIllll[4], ToggleCommand.lIIIIllll[5]);
        ToggleCommand.lIIIIlIll[3] = lllIlllIll(ToggleCommand.lIIIIllll[6], ToggleCommand.lIIIIllll[7]);
        ToggleCommand.lIIIIlIll[4] = lllIllIlIl(ToggleCommand.lIIIIllll[8], ToggleCommand.lIIIIllll[9]);
        ToggleCommand.lIIIIlIll[5] = lllIllIlIl(ToggleCommand.lIIIIllll[10], ToggleCommand.lIIIIllll[11]);
        ToggleCommand.lIIIIlIll[6] = lllIlllIll(ToggleCommand.lIIIIllll[12], ToggleCommand.lIIIIllll[13]);
        ToggleCommand.lIIIIlIll[7] = lllIlllIll(ToggleCommand.lIIIIllll[14], ToggleCommand.lIIIIllll[15]);
        ToggleCommand.lIIIIlIll[8] = lllIlllllI(ToggleCommand.lIIIIllll[16], ToggleCommand.lIIIIllll[17]);
        ToggleCommand.lIIIIlIll[9] = lllIllIlIl(ToggleCommand.lIIIIllll[18], ToggleCommand.lIIIIllll[19]);
        ToggleCommand.lIIIIlIll[10] = lllIlllIll(ToggleCommand.lIIIIllll[20], ToggleCommand.lIIIIllll[21]);
        ToggleCommand.lIIIIlIll[11] = lllIlllIll(ToggleCommand.lIIIIllll[22], ToggleCommand.lIIIIllll[23]);
        ToggleCommand.lIIIIllll = null;
    }

    private static void llllIIIIIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ToggleCommand.lIIIIllll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lllIlllIll(String s, String s1) {
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

    private static String lllIlllllI(String s, String s1) {
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

    private static String lllIllIlIl(String s, String s1) {
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
