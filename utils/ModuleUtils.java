package me.explicit.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;

public class ModuleUtils {

    private static final String[] lIIlIlIII;
    private static String[] lIIlIlIlI;

    public static void setAllModulesToggled(boolean flag) {
        Iterator iterator = Explicit.instance.mm.getModules().iterator();

        while (iterator.hasNext()) {
            Module module = (Module) iterator.next();

            if (!module.getName().equalsIgnoreCase(ModuleUtils.lIIlIlIII[0]) && !module.getCategory().equals(Category.VALUES)) {
                module.setToggled(flag);
            }
        }

    }

    static {
        lIIIIIIllII();
        lIIIIIIlIll();
    }

    private static void lIIIIIIlIll() {
        lIIlIlIII = new String[1];
        ModuleUtils.lIIlIlIII[0] = lIIIIIIlIlI(ModuleUtils.lIIlIlIlI[0], ModuleUtils.lIIlIlIlI[1]);
        ModuleUtils.lIIlIlIlI = null;
    }

    private static void lIIIIIIllII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ModuleUtils.lIIlIlIlI = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIIIIlIlI(String s, String s1) {
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
