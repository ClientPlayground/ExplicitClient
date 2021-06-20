package me.explicit.consolegui;

import io.netty.util.internal.ThreadLocalRandom;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.command.CommandUtils;
import me.explicit.module.Module;
import me.explicit.settings.Setting;
import me.explicit.utils.TimerUtils;
import net.minecraft.client.Minecraft;

public class ConsoleGUI extends Thread {

    private String fileName;
    private String fileLocation;
    private TimerUtils fileChecker = new TimerUtils();
    private TimerUtils processChecker = new TimerUtils();
    private boolean hasOpened = false;
    private static final String[] llIIIIl;
    private static String[] llIIIlI;

    public void run() {
        Explicit.consolegui = true;
        if (!System.getProperty(ConsoleGUI.llIIIIl[0]).toLowerCase().contains(ConsoleGUI.llIIIIl[1])) {
            Explicit.consolegui = false;
            System.out.println(ConsoleGUI.llIIIIl[2]);
        } else {
            File file = Minecraft.getMinecraft().mcDataDir;
            File file1 = new File(file, ConsoleGUI.llIIIIl[3]);

            if (!file1.exists()) {
                System.out.println(ConsoleGUI.llIIIIl[4]);
                Explicit.consolegui = false;
            } else {
                File[] afile = file1.listFiles();

                for (int i = 0; i < afile.length; ++i) {
                    if (afile[i].isFile() && afile[i].getName().endsWith(ConsoleGUI.llIIIIl[5])) {
                        this.fileName = afile[i].getName();
                        this.fileLocation = afile[i].getAbsolutePath();
                    }
                }

                if (this.fileName != null && this.fileLocation != null) {
                    try {
                        File file2 = new File(file1, ConsoleGUI.llIIIIl[7]);
                        File file3;

                        if (file2.exists()) {
                            String[] astring = file2.list();

                            for (int j = 0; j < astring.length; ++j) {
                                String s = astring[j];

                                file3 = new File(file2.getPath(), s);
                                file3.delete();
                            }

                            file2.delete();
                        }

                        File file4 = new File(file1, ConsoleGUI.llIIIIl[8]);

                        if (file4.exists()) {
                            String[] astring1 = file4.list();

                            for (int k = 0; k < astring1.length; ++k) {
                                String s1 = astring1[k];
                                File file5 = new File(file4.getPath(), s1);

                                file5.delete();
                            }

                            file4.delete();
                        }

                        File file6 = new File(file1, ConsoleGUI.llIIIIl[9]);

                        if (!file6.exists()) {
                            file6.createNewFile();
                        }

                        File file7 = new File(file1, ConsoleGUI.llIIIIl[10]);

                        if (!file7.exists()) {
                            file7.createNewFile();
                        }

                        file3 = new File(file1, ConsoleGUI.llIIIIl[11]);
                        if (!file3.exists()) {
                            file3.createNewFile();
                        }

                        PrintWriter printwriter = new PrintWriter(file6);
                        Iterator iterator = Explicit.instance.mm.getModules().iterator();

                        while (iterator.hasNext()) {
                            Module module = (Module) iterator.next();

                            printwriter.println(module.getName().toLowerCase());
                        }

                        printwriter.close();
                        PrintWriter printwriter1 = new PrintWriter(file7);

                        for (Iterator iterator1 = Explicit.instance.sm.getSettings().iterator(); iterator1.hasNext(); printwriter1.println()) {
                            Setting setting = (Setting) iterator1.next();

                            printwriter1.print(String.valueOf((new StringBuilder()).append(setting.getParentMod().getName().toLowerCase()).append(ConsoleGUI.llIIIIl[12]).append(setting.getName().toLowerCase()).append(ConsoleGUI.llIIIIl[13])));
                            if (setting.isCheck()) {
                                printwriter1.print(ConsoleGUI.llIIIIl[14]);
                            } else if (setting.isCombo()) {
                                printwriter1.print(ConsoleGUI.llIIIIl[15]);
                                Iterator iterator2 = setting.getOptions().iterator();

                                while (iterator2.hasNext()) {
                                    String s2 = (String) iterator2.next();

                                    printwriter1.print(s2);
                                    if (s2 != setting.getOptions().get(setting.getOptions().size() - 1)) {
                                        printwriter1.print(ConsoleGUI.llIIIIl[16]);
                                    }
                                }
                            } else if (setting.isSlider()) {
                                printwriter1.print(String.valueOf((new StringBuilder()).append(setting.getMin()).append(ConsoleGUI.llIIIIl[17]).append(setting.getMax())));
                            }
                        }

                        printwriter1.close();
                        PrintWriter printwriter2 = new PrintWriter(file3);

                        printwriter2.print(this.fileName);
                        printwriter2.close();
                        Runtime.getRuntime().exec(String.valueOf((new StringBuilder()).append(ConsoleGUI.llIIIIl[18]).append(file3.getAbsolutePath())));
                    } catch (IOException ioexception) {
                        ioexception.printStackTrace();
                        System.out.println(ConsoleGUI.llIIIIl[19]);
                        Explicit.consolegui = false;
                    }

                    if (Explicit.consolegui) {
                        this.fileChecker.reset();
                        this.processChecker.reset();
                        this.runChecks();
                    } else {
                        if (Explicit.instance.mm.getModuleByName(ConsoleGUI.llIIIIl[20]).getKey() == 0) {
                            Explicit.instance.mm.getModuleByName(ConsoleGUI.llIIIIl[21]).setKey(54);
                        }

                        System.out.println(ConsoleGUI.llIIIIl[22]);
                    }

                } else {
                    System.out.println(ConsoleGUI.llIIIIl[6]);
                    Explicit.consolegui = false;
                }
            }
        }
    }

    private void runChecks() {
        int i = 0;
        ArrayList arraylist;

        (arraylist = new ArrayList()).clear();

        while (Explicit.consolegui) {
            if (this.fileChecker.hasReached(2000.0D)) {
                File file = Minecraft.getMinecraft().mcDataDir;
                File file1 = new File(file, ConsoleGUI.llIIIIl[23]);
                File file2 = new File(file1, ConsoleGUI.llIIIIl[24]);

                if (file2.isDirectory()) {
                    File[] afile = file2.listFiles();

                    if (afile.length > 0) {
                        File[] afile1 = afile;
                        int j = afile.length;

                        for (int k = 0; k < j; ++k) {
                            File file3 = afile1[k];

                            if (!arraylist.contains(file3.getName())) {
                                try {
                                    BufferedReader bufferedreader = new BufferedReader(new FileReader(file3));

                                    String s;

                                    while ((s = bufferedreader.readLine()) != null) {
                                        if (s == ConsoleGUI.llIIIIl[25] && !this.isProcessOpen()) {
                                            Explicit.instance.cg = null;
                                        } else {
                                            this.sendMessage(CommandUtils.sendCommand(s));
                                        }
                                    }

                                    arraylist.add(file3.getName());
                                } catch (FileNotFoundException filenotfoundexception) {
                                    filenotfoundexception.printStackTrace();
                                } catch (IOException ioexception) {
                                    ioexception.printStackTrace();
                                }
                            }

                            file3.delete();
                        }
                    }
                }

                this.fileChecker.reset();
            }

            if (this.processChecker.hasReached(8000.0D)) {
                if (!this.hasOpened && i < 4 && this.isProcessOpen()) {
                    this.hasOpened = true;
                    ++i;
                }

                if (!this.isProcessOpen() && i >= 4 && !this.hasOpened) {
                    System.out.println(ConsoleGUI.llIIIIl[26]);
                    Explicit.consolegui = false;
                }

                if (this.hasOpened && !this.isProcessOpen()) {
                    ++i;
                    if (i > 1) {
                        if (Explicit.instance.mm.getModuleByName(ConsoleGUI.llIIIIl[27]).getKey() == 0) {
                            Explicit.instance.mm.getModuleByName(ConsoleGUI.llIIIIl[28]).setKey(54);
                        }

                        System.out.println(ConsoleGUI.llIIIIl[29]);
                        Explicit.consolegui = false;
                    }
                } else if (this.hasOpened) {
                    i = 0;
                }

                this.processChecker.reset();
            }

            if (!Explicit.consolegui) {
                Explicit.instance.cg = null;
                if (!Explicit.destructed && Explicit.instance.mm.getModuleByName(ConsoleGUI.llIIIIl[30]).getKey() == 0) {
                    Explicit.instance.mm.getModuleByName(ConsoleGUI.llIIIIl[31]).setKey(54);
                }
            }
        }

    }

    private void sendMessage(List list) {
        File file = Minecraft.getMinecraft().mcDataDir;
        File file1 = new File(file, ConsoleGUI.llIIIIl[32]);
        File file2 = new File(file1, ConsoleGUI.llIIIIl[33]);

        if (!file2.isDirectory()) {
            file2.mkdir();
        }

        File file3 = new File(file2, String.valueOf((new StringBuilder()).append(ConsoleGUI.llIIIIl[34]).append(this.randomString(5)).append(ConsoleGUI.llIIIIl[35])));

        try {
            if (file3.createNewFile()) {
                PrintWriter printwriter = new PrintWriter(file3);
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    String s = (String) iterator.next();

                    printwriter.println(s);
                }

                printwriter.close();
            }
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }

    }

    private boolean isProcessOpen() {
        ProcessBuilder processbuilder = new ProcessBuilder(new String[] { ConsoleGUI.llIIIIl[36]});
        Process process = null;

        try {
            process = processbuilder.start();
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
            return false;
        }

        String s;

        try {
            s = this.toString(process.getInputStream());
        } catch (IOException ioexception1) {
            ioexception1.printStackTrace();
            return false;
        }

        return s.contains(this.fileName);
    }

    private String toString(InputStream inputstream) throws IOException {
        Scanner scanner = (new Scanner(inputstream, ConsoleGUI.llIIIIl[37])).useDelimiter(ConsoleGUI.llIIIIl[38]);
        String s = scanner.hasNext() ? scanner.next() : ConsoleGUI.llIIIIl[39];

        scanner.close();
        return s;
    }

    public String randomString(int i) {
        String s = ConsoleGUI.llIIIIl[40];
        String s1 = ConsoleGUI.llIIIIl[41];

        for (int j = 0; j < i; ++j) {
            s1 = String.valueOf((new StringBuilder()).append(s1).append(s.charAt(ThreadLocalRandom.current().nextInt(s.toCharArray().length))));
        }

        return s1;
    }

    static {
        lIlIlIlII();
        lIlIlIIll();
    }

    private static void lIlIlIIll() {
        llIIIIl = new String[42];
        ConsoleGUI.llIIIIl[0] = lIlIIllll(ConsoleGUI.llIIIlI[0], ConsoleGUI.llIIIlI[1]);
        ConsoleGUI.llIIIIl[1] = lIlIlIIIl(ConsoleGUI.llIIIlI[2], ConsoleGUI.llIIIlI[3]);
        ConsoleGUI.llIIIIl[2] = lIlIlIIIl(ConsoleGUI.llIIIlI[4], ConsoleGUI.llIIIlI[5]);
        ConsoleGUI.llIIIIl[3] = lIlIlIIlI(ConsoleGUI.llIIIlI[6], ConsoleGUI.llIIIlI[7]);
        ConsoleGUI.llIIIIl[4] = lIlIIllll(ConsoleGUI.llIIIlI[8], ConsoleGUI.llIIIlI[9]);
        ConsoleGUI.llIIIIl[5] = lIlIlIIIl(ConsoleGUI.llIIIlI[10], ConsoleGUI.llIIIlI[11]);
        ConsoleGUI.llIIIIl[6] = lIlIlIIIl(ConsoleGUI.llIIIlI[12], ConsoleGUI.llIIIlI[13]);
        ConsoleGUI.llIIIIl[7] = lIlIlIIlI(ConsoleGUI.llIIIlI[14], ConsoleGUI.llIIIlI[15]);
        ConsoleGUI.llIIIIl[8] = lIlIlIIIl(ConsoleGUI.llIIIlI[16], ConsoleGUI.llIIIlI[17]);
        ConsoleGUI.llIIIIl[9] = lIlIlIIIl(ConsoleGUI.llIIIlI[18], ConsoleGUI.llIIIlI[19]);
        ConsoleGUI.llIIIIl[10] = lIlIlIIIl(ConsoleGUI.llIIIlI[20], ConsoleGUI.llIIIlI[21]);
        ConsoleGUI.llIIIIl[11] = lIlIlIIlI(ConsoleGUI.llIIIlI[22], ConsoleGUI.llIIIlI[23]);
        ConsoleGUI.llIIIIl[12] = lIlIIllll(ConsoleGUI.llIIIlI[24], ConsoleGUI.llIIIlI[25]);
        ConsoleGUI.llIIIIl[13] = lIlIIllll(ConsoleGUI.llIIIlI[26], ConsoleGUI.llIIIlI[27]);
        ConsoleGUI.llIIIIl[14] = lIlIIllll(ConsoleGUI.llIIIlI[28], ConsoleGUI.llIIIlI[29]);
        ConsoleGUI.llIIIIl[15] = lIlIlIIlI(ConsoleGUI.llIIIlI[30], ConsoleGUI.llIIIlI[31]);
        ConsoleGUI.llIIIIl[16] = lIlIIllll(ConsoleGUI.llIIIlI[32], ConsoleGUI.llIIIlI[33]);
        ConsoleGUI.llIIIIl[17] = lIlIlIIlI(ConsoleGUI.llIIIlI[34], ConsoleGUI.llIIIlI[35]);
        ConsoleGUI.llIIIIl[18] = lIlIlIIIl(ConsoleGUI.llIIIlI[36], ConsoleGUI.llIIIlI[37]);
        ConsoleGUI.llIIIIl[19] = lIlIIllll(ConsoleGUI.llIIIlI[38], ConsoleGUI.llIIIlI[39]);
        ConsoleGUI.llIIIIl[20] = lIlIlIIIl("GFQG8iKxfZbaBGnj4RIc+A==", "npJHd");
        ConsoleGUI.llIIIIl[21] = lIlIlIIlI("GzURLTofDDE=", "XYxNQ");
        ConsoleGUI.llIIIIl[22] = lIlIlIIIl("1KgeELJLp8NHhZ9rdITcDg==", "ghUii");
        ConsoleGUI.llIIIIl[23] = lIlIlIIlI("ACM+", "gVWKP");
        ConsoleGUI.llIIIIl[24] = lIlIlIIlI("NzEWAQ==", "dTxee");
        ConsoleGUI.llIIIIl[25] = lIlIlIIIl("NpSuZjK/+Sb1Uio1iY4ZMQ==", "YPjWk");
        ConsoleGUI.llIIIIl[26] = lIlIlIIIl("H/RICsD4q9/90nQNStOTagC48H4HqenBpaxSlvuzXnE=", "zlHKO");
        ConsoleGUI.llIIIIl[27] = lIlIIllll("jLc/UIMAr9SMtD46FNYmJw==", "JNTNL");
        ConsoleGUI.llIIIIl[28] = lIlIlIIlI("Jw8aJBojNjo=", "dcsGq");
        ConsoleGUI.llIIIIl[29] = lIlIlIIIl("88rtIC6jsgEw2f8a1TyV8I7irmwjAc1P", "KMvJs");
        ConsoleGUI.llIIIIl[30] = lIlIIllll("P+B6yXzt00R5mgOG8XCXZw==", "sUlSZ");
        ConsoleGUI.llIIIIl[31] = lIlIlIIIl("zIuKm8Mn2iukQNp4RLx52w==", "chQzH");
        ConsoleGUI.llIIIIl[32] = lIlIIllll("BcDEwx/HcNY=", "OXckK");
        ConsoleGUI.llIIIIl[33] = lIlIIllll("8xrB+iwGcus=", "WDzjS");
        ConsoleGUI.llIIIIl[34] = lIlIIllll("LF17abkYAVw=", "VlsKO");
        ConsoleGUI.llIIIIl[35] = lIlIlIIlI("WDYQFg==", "vBhbD");
        ConsoleGUI.llIIIIl[36] = lIlIlIIIl("ge6lXW/QkdiEvyzQuVNSVg==", "jGNnp");
        ConsoleGUI.llIIIIl[37] = lIlIIllll("WORqAJ5WL/A=", "CmQFW");
        ConsoleGUI.llIIIIl[38] = lIlIIllll("2UvxRhcQyz4=", "DOuce");
        ConsoleGUI.llIIIIl[39] = lIlIlIIIl("wAmavX9lwyI=", "SiAhU");
        ConsoleGUI.llIIIIl[40] = lIlIIllll("w3g3QDWt75EqMUT9RXeR4D1sXyKJda+A1EAYwWYwDine9ZaEAHu5nQ==", "lwMsP");
        ConsoleGUI.llIIIIl[41] = lIlIlIIIl("MBqKrBXtZwc=", "ENRvt");
        ConsoleGUI.llIIIlI = null;
    }

    private static void lIlIlIlII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ConsoleGUI.llIIIlI = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIlIlIIIl(String s, String s1) {
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

    private static String lIlIlIIlI(String s, String s1) {
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

    private static String lIlIIllll(String s, String s1) {
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
