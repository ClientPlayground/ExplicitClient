package me.explicit.command;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.command.commands.BindCommand;
import me.explicit.command.commands.ConfigCommand;
import me.explicit.command.commands.FriendCommand;
import me.explicit.command.commands.HelpCommand;
import me.explicit.command.commands.SettingsCommand;
import me.explicit.command.commands.ToggleCommand;
import org.apache.commons.lang3.ArrayUtils;

public class CommandUtils {

    private static final String[] llIIlllI;
    private static String[] llIlIIll;

    public static List sendCommand(String s) {
        Object object;

        ((List) (object = new ArrayList())).clear();
        ((List) object).add(CommandUtils.llIIlllI[0]);
        s = s.replaceFirst(CommandUtils.llIIlllI[1], CommandUtils.llIIlllI[2]);
        s = s.replaceFirst(CommandUtils.llIIlllI[3], CommandUtils.llIIlllI[4]);
        if (s.startsWith(CommandUtils.llIIlllI[5])) {
            object = (new BindCommand()).executeCommand((String[]) ArrayUtils.remove(s.split(CommandUtils.llIIlllI[6]), 0));
        } else if (!s.startsWith(CommandUtils.llIIlllI[7]) && !s.startsWith(CommandUtils.llIIlllI[8])) {
            if (!s.startsWith(CommandUtils.llIIlllI[10]) && !s.startsWith(CommandUtils.llIIlllI[11]) && !s.startsWith(CommandUtils.llIIlllI[12])) {
                if (s.startsWith(CommandUtils.llIIlllI[14])) {
                    object = (new ConfigCommand()).executeCommand((String[]) ArrayUtils.remove(s.split(CommandUtils.llIIlllI[15]), 0), false);
                } else if (!s.startsWith(CommandUtils.llIIlllI[16]) && !s.startsWith(CommandUtils.llIIlllI[17]) && !s.startsWith(CommandUtils.llIIlllI[18])) {
                    if (s.startsWith(CommandUtils.llIIlllI[20])) {
                        object = (new HelpCommand()).executeCommand((String[]) ArrayUtils.remove(s.split(CommandUtils.llIIlllI[21]), 0));
                    } else {
                        ((List) object).clear();
                        ((List) object).add(String.valueOf((new StringBuilder()).append(CommandUtils.llIIlllI[22]).append(s).append(CommandUtils.llIIlllI[23])));
                    }
                } else {
                    object = (new SettingsCommand()).executeCommand((String[]) ArrayUtils.remove(s.split(CommandUtils.llIIlllI[19]), 0));
                }
            } else {
                object = (new FriendCommand()).executeCommand((String[]) ArrayUtils.remove(s.split(CommandUtils.llIIlllI[13]), 0));
            }
        } else {
            object = (new ToggleCommand()).executeCommand((String[]) ArrayUtils.remove(s.split(CommandUtils.llIIlllI[9]), 0));
        }

        return (List) object;
    }

    static {
        llIIIlIlII();
        llIIIlIIlI();
    }

    private static void llIIIlIIlI() {
        llIIlllI = new String[24];
        CommandUtils.llIIlllI[0] = llIIIIIlII(CommandUtils.llIlIIll[0], CommandUtils.llIlIIll[1]);
        CommandUtils.llIIlllI[1] = llIIIIlIlI(CommandUtils.llIlIIll[2], CommandUtils.llIlIIll[3]);
        CommandUtils.llIIlllI[2] = llIIIIIlII(CommandUtils.llIlIIll[4], CommandUtils.llIlIIll[5]);
        CommandUtils.llIIlllI[3] = llIIIIlIlI(CommandUtils.llIlIIll[6], CommandUtils.llIlIIll[7]);
        CommandUtils.llIIlllI[4] = llIIIIlIll(CommandUtils.llIlIIll[8], CommandUtils.llIlIIll[9]);
        CommandUtils.llIIlllI[5] = llIIIIlIll(CommandUtils.llIlIIll[10], CommandUtils.llIlIIll[11]);
        CommandUtils.llIIlllI[6] = llIIIIlIlI(CommandUtils.llIlIIll[12], CommandUtils.llIlIIll[13]);
        CommandUtils.llIIlllI[7] = llIIIIlIll(CommandUtils.llIlIIll[14], CommandUtils.llIlIIll[15]);
        CommandUtils.llIIlllI[8] = llIIIIlIll(CommandUtils.llIlIIll[16], CommandUtils.llIlIIll[17]);
        CommandUtils.llIIlllI[9] = llIIIIlIlI(CommandUtils.llIlIIll[18], CommandUtils.llIlIIll[19]);
        CommandUtils.llIIlllI[10] = llIIIIIlII(CommandUtils.llIlIIll[20], CommandUtils.llIlIIll[21]);
        CommandUtils.llIIlllI[11] = llIIIIlIll(CommandUtils.llIlIIll[22], CommandUtils.llIlIIll[23]);
        CommandUtils.llIIlllI[12] = llIIIIlIlI(CommandUtils.llIlIIll[24], CommandUtils.llIlIIll[25]);
        CommandUtils.llIIlllI[13] = llIIIIlIll(CommandUtils.llIlIIll[26], CommandUtils.llIlIIll[27]);
        CommandUtils.llIIlllI[14] = llIIIIlIlI(CommandUtils.llIlIIll[28], CommandUtils.llIlIIll[29]);
        CommandUtils.llIIlllI[15] = llIIIIIlII(CommandUtils.llIlIIll[30], CommandUtils.llIlIIll[31]);
        CommandUtils.llIIlllI[16] = llIIIIlIlI(CommandUtils.llIlIIll[32], CommandUtils.llIlIIll[33]);
        CommandUtils.llIIlllI[17] = llIIIIIlII(CommandUtils.llIlIIll[34], CommandUtils.llIlIIll[35]);
        CommandUtils.llIIlllI[18] = llIIIIIlII(CommandUtils.llIlIIll[36], CommandUtils.llIlIIll[37]);
        CommandUtils.llIIlllI[19] = llIIIIlIll(CommandUtils.llIlIIll[38], CommandUtils.llIlIIll[39]);
        CommandUtils.llIIlllI[20] = llIIIIIlII(CommandUtils.llIlIIll[40], CommandUtils.llIlIIll[41]);
        CommandUtils.llIIlllI[21] = llIIIIlIlI(CommandUtils.llIlIIll[42], CommandUtils.llIlIIll[43]);
        CommandUtils.llIIlllI[22] = llIIIIlIll(CommandUtils.llIlIIll[44], CommandUtils.llIlIIll[45]);
        CommandUtils.llIIlllI[23] = llIIIIlIlI(CommandUtils.llIlIIll[46], CommandUtils.llIlIIll[47]);
        CommandUtils.llIlIIll = null;
    }

    private static void llIIIlIlII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        CommandUtils.llIlIIll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIIIIIlII(String s, String s1) {
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

    private static String llIIIIlIlI(String s, String s1) {
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

    private static String llIIIIlIll(String s, String s1) {
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
