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

public class HelpCommand implements ICommand {

    private static final String[] lIIlll;
    private static String[] lIllII;

    public int compareTo(ICommand icommand) {
        return 0;
    }

    public String getCommandName() {
        return HelpCommand.lIIlll[0];
    }

    public String getCommandUsage(ICommandSender icommandsender) {
        return HelpCommand.lIIlll[1];
    }

    public List getCommandAliases() {
        ArrayList arraylist = new ArrayList();

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
            Game.Player().addChatMessage(new ChatComponentText(String.valueOf((new StringBuilder()).append(EnumChatFormatting.RED).append(HelpCommand.lIIlll[2]))));
            return arraylist;
        } else {
            if (flag) {
                arraylist.add(HelpCommand.lIIlll[3]);
                arraylist.add(HelpCommand.lIIlll[4]);
                arraylist.add(HelpCommand.lIIlll[5]);
                arraylist.add(HelpCommand.lIIlll[6]);
                arraylist.add(HelpCommand.lIIlll[7]);
                arraylist.add(HelpCommand.lIIlll[8]);
            } else {
                ChatUtils.sendMessage(String.valueOf((new StringBuilder()).append(EnumChatFormatting.GOLD).append(HelpCommand.lIIlll[9])));
                ChatUtils.sendMessage(HelpCommand.lIIlll[10]);
                ChatUtils.sendMessage(HelpCommand.lIIlll[11]);
                ChatUtils.sendMessage(HelpCommand.lIIlll[12]);
                ChatUtils.sendMessage(HelpCommand.lIIlll[13]);
                ChatUtils.sendMessage(HelpCommand.lIIlll[14]);
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
        lIIllIII();
        lIIlIlll();
    }

    private static void lIIlIlll() {
        lIIlll = new String[15];
        HelpCommand.lIIlll[0] = lIIIllII(HelpCommand.lIllII[0], HelpCommand.lIllII[1]);
        HelpCommand.lIIlll[1] = lIIIllIl(HelpCommand.lIllII[2], HelpCommand.lIllII[3]);
        HelpCommand.lIIlll[2] = lIIIllIl(HelpCommand.lIllII[4], HelpCommand.lIllII[5]);
        HelpCommand.lIIlll[3] = lIIIlllI(HelpCommand.lIllII[6], HelpCommand.lIllII[7]);
        HelpCommand.lIIlll[4] = lIIIllIl(HelpCommand.lIllII[8], HelpCommand.lIllII[9]);
        HelpCommand.lIIlll[5] = lIIIlllI(HelpCommand.lIllII[10], HelpCommand.lIllII[11]);
        HelpCommand.lIIlll[6] = lIIIllII(HelpCommand.lIllII[12], HelpCommand.lIllII[13]);
        HelpCommand.lIIlll[7] = lIIIllIl(HelpCommand.lIllII[14], HelpCommand.lIllII[15]);
        HelpCommand.lIIlll[8] = lIIIlllI(HelpCommand.lIllII[16], HelpCommand.lIllII[17]);
        HelpCommand.lIIlll[9] = lIIIlllI("nKkDOzYB0/3CVOHGPp1zr8ocCbJcxyjH", "CYkvc");
        HelpCommand.lIIlll[10] = lIIIlllI("gEFUMS/2tR8nf5AaNgBGAzjcSAdrvx/c/NxCu52XAY8=", "rBXyU");
        HelpCommand.lIIlll[11] = lIIIllII("bkEbLCQlTENlGSQYWTEiJEwSIDMjBRchajUDWSRqLAMdMCYk", "AlyEJ");
        HelpCommand.lIIlll[12] = lIIIlllI("C60W4exsk2dylYLvySgwt0FngbySEGRdy0qQqhskUaMXE075ykrcrvvO905Z+JcaZVSSnJO75ixyabJKLqxMSwYzb0xcrjjy", "aLvHv");
        HelpCommand.lIIlll[13] = lIIIllII("TGg6Py8FLD5we0MIOD4gBCB5KS4WN3kzLg0jMDcy", "cEYPA");
        HelpCommand.lIIlll[14] = lIIIllIl("CTgn1oHM9X2dtjh1lojb5IDoaSoQGTChloS3jAPQVtFEqEMGDk4483whvokUo4AY", "TNSVd");
        HelpCommand.lIllII = null;
    }

    private static void lIIllIII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        HelpCommand.lIllII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIllII(String s, String s1) {
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

    private static String lIIIllIl(String s, String s1) {
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

    private static String lIIIlllI(String s, String s1) {
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
