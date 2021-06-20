package me.explicit.config;

import java.awt.Color;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.command.commands.ConfigCommand;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class ConfigGUI extends GuiScreen {

    private final GuiScreen previousScreen;
    private int selected;
    private int offset;
    private static final String[] lIIlIllII;
    private static String[] lIIlIlllI;

    public ConfigGUI(GuiScreen guiscreen) {
        this.previousScreen = guiscreen;
    }

    public void actionPerformed(GuiButton guibutton) throws IOException {
        if (this.mc.currentScreen instanceof ConfigGUI) {
            ConfigCommand configcommand;

            switch (guibutton.id) {
            case 0:
                this.mc.displayGuiScreen((GuiScreen) null);
                break;

            case 1:
                configcommand = new ConfigCommand();
                if (this.selected != -1) {
                    configcommand.executeCommand(new String[] { ConfigGUI.lIIlIllII[0], ((String) ConfigManager.GetConfigs().get(this.selected)).replace(ConfigGUI.lIIlIllII[1], ConfigGUI.lIIlIllII[2])}, true);
                }
                break;

            case 2:
                configcommand = new ConfigCommand();
                if (this.selected != -1) {
                    configcommand.executeCommand(new String[] { ConfigGUI.lIIlIllII[3], ((String) ConfigManager.GetConfigs().get(this.selected)).replace(ConfigGUI.lIIlIllII[4], ConfigGUI.lIIlIllII[5])}, true);
                }

                this.selected = -1;
                break;

            case 3:
                configcommand = new ConfigCommand();
                if (this.selected == -1) {
                    configcommand.executeCommand(new String[] { ConfigGUI.lIIlIllII[6], String.valueOf((new StringBuilder()).append(ConfigGUI.lIIlIllII[7]).append(ConfigManager.GetConfigs().size() + 1))}, true);
                } else {
                    configcommand.executeCommand(new String[] { ConfigGUI.lIIlIllII[8], ((String) ConfigManager.GetConfigs().get(this.selected)).replace(ConfigGUI.lIIlIllII[9], ConfigGUI.lIIlIllII[10])}, true);
                }
            }

        }
    }

    public void drawScreen(int i, int j, float f) {
        if (this.mc.currentScreen instanceof ConfigGUI) {
            this.drawDefaultBackground();
            if (Mouse.hasWheel()) {
                int k = Mouse.getDWheel();

                if (k < 0) {
                    this.offset += 26;
                    if (this.offset < 0) {
                        this.offset = 0;
                    }
                } else if (k > 0) {
                    this.offset -= 26;
                    if (this.offset < 0) {
                        this.offset = 0;
                    }
                }
            }

            this.drawDefaultBackground();
            FontRenderer fontrenderer = this.mc.fontRendererObj;

            fontrenderer.drawString(ConfigGUI.lIIlIllII[11], this.width / 2 - fontrenderer.getStringWidth(ConfigGUI.lIIlIllII[12]) / 2, 10, -1);
            GL11.glPushMatrix();
            this.prepareScissorBox(0.0F, 33.0F, (float) this.width, (float) (this.height - 50));
            GL11.glEnable(3089);
            int l = 38;
            List list = ConfigManager.GetConfigs();

            if (list.isEmpty()) {
                this.selected = -1;
            }

            for (int i1 = 0; i1 < list.size(); ++i1) {
                if (this.isAltInArea(l) && !((String) list.get(i1)).equalsIgnoreCase(ConfigGUI.lIIlIllII[13])) {
                    if (i1 == this.selected) {
                        if (this.isMouseOverAlt(i, j, l - this.offset) && Mouse.isButtonDown(0)) {
                            Gui.drawRect(52, l - this.offset - 4, this.width - 52, l - this.offset + 20, (new Color(50, 50, 50)).getRGB());
                        } else if (this.isMouseOverAlt(i, j, l - this.offset)) {
                            Gui.drawRect(52, l - this.offset - 4, this.width - 52, l - this.offset + 20, (new Color(150, 150, 150)).getRGB());
                        } else {
                            Gui.drawRect(52, l - this.offset - 4, this.width - 52, l - this.offset + 20, (new Color(50, 50, 50)).getRGB());
                        }
                    } else if (this.isMouseOverAlt(i, j, l - this.offset) && Mouse.isButtonDown(0)) {
                        Gui.drawRect(52, l - this.offset - 4, this.width - 52, l - this.offset + 20, (new Color(150, 150, 150)).getRGB());
                    } else if (this.isMouseOverAlt(i, j, l - this.offset)) {
                        Gui.drawRect(52, l - this.offset - 4, this.width - 52, l - this.offset + 20, (new Color(150, 150, 150)).getRGB());
                    }

                    this.mc.fontRendererObj.drawString((String) list.get(i1), 54, l + 3, -1);
                    l += 26;
                }
            }

            GL11.glDisable(3089);
            GL11.glPopMatrix();
            super.drawScreen(i, j, f);
            if (Keyboard.isKeyDown(200)) {
                this.offset -= 26;
                if (this.offset < 0) {
                    this.offset = 0;
                }
            } else if (Keyboard.isKeyDown(208)) {
                this.offset += 26;
                if (this.offset < 0) {
                    this.offset = 0;
                }
            }

        }
    }

    public void initGui() {
        this.buttonList.add(new GuiButton(0, this.width / 2 + 4, this.height - 24, 75, 20, ConfigGUI.lIIlIllII[14]));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 70, this.height - 48, 70, 20, ConfigGUI.lIIlIllII[15]));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 70, this.height - 24, 70, 20, ConfigGUI.lIIlIllII[16]));
        this.buttonList.add(new GuiButton(3, this.width / 2 + 4, this.height - 48, 75, 20, ConfigGUI.lIIlIllII[17]));
    }

    private boolean isAltInArea(int i) {
        return i - this.offset <= this.height - 50;
    }

    private boolean isMouseOverAlt(int i, int j, int k) {
        return i >= 52 && j >= k - 4 && i <= this.width - 52 && j <= k + 20 && i >= 0 && j >= 33 && i <= this.width && j <= this.height - 50;
    }

    protected void mouseClicked(int i, int j, int k) throws IOException {
        if (this.mc.currentScreen instanceof ConfigGUI) {
            if (this.offset < 0) {
                this.offset = 0;
            }

            int l = 38 - this.offset;
            List list = ConfigManager.GetConfigs();
            boolean flag = false;

            for (int i1 = 0; i1 < list.size(); ++i1) {
                if (this.isMouseOverAlt(i, j, l)) {
                    if (i1 == this.selected) {
                        this.actionPerformed((GuiButton) this.buttonList.get(1));
                        return;
                    }

                    this.selected = i1;
                    flag = true;
                }

                l += 26;
            }

            try {
                super.mouseClicked(i, j, k);
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            }

            if (!flag) {
                this.selected = -1;
            }

        }
    }

    public void prepareScissorBox(float f, float f1, float f2, float f3) {
        int i = (new ScaledResolution(this.mc)).getScaleFactor();

        GL11.glScissor((int) (f * (float) i), (int) (((float) (new ScaledResolution(this.mc)).getScaledHeight() - f3) * (float) i), (int) ((f2 - f) * (float) i), (int) ((f3 - f1) * (float) i));
    }

    public void renderBackground(int i, int j) {
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(3008);
        this.drawDefaultBackground();
        Tessellator tessellator = Tessellator.getInstance();

        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    static {
        lIIIIlIIlIl();
        lIIIIlIIlII();
    }

    private static void lIIIIlIIlII() {
        lIIlIllII = new String[18];
        ConfigGUI.lIIlIllII[0] = lIIIIIlllll(ConfigGUI.lIIlIlllI[0], ConfigGUI.lIIlIlllI[1]);
        ConfigGUI.lIIlIllII[1] = lIIIIlIIIII(ConfigGUI.lIIlIlllI[2], ConfigGUI.lIIlIlllI[3]);
        ConfigGUI.lIIlIllII[2] = lIIIIIlllll(ConfigGUI.lIIlIlllI[4], ConfigGUI.lIIlIlllI[5]);
        ConfigGUI.lIIlIllII[3] = lIIIIIlllll(ConfigGUI.lIIlIlllI[6], ConfigGUI.lIIlIlllI[7]);
        ConfigGUI.lIIlIllII[4] = lIIIIlIIIII(ConfigGUI.lIIlIlllI[8], ConfigGUI.lIIlIlllI[9]);
        ConfigGUI.lIIlIllII[5] = lIIIIlIIIIl(ConfigGUI.lIIlIlllI[10], ConfigGUI.lIIlIlllI[11]);
        ConfigGUI.lIIlIllII[6] = lIIIIlIIIIl(ConfigGUI.lIIlIlllI[12], ConfigGUI.lIIlIlllI[13]);
        ConfigGUI.lIIlIllII[7] = lIIIIIlllll(ConfigGUI.lIIlIlllI[14], ConfigGUI.lIIlIlllI[15]);
        ConfigGUI.lIIlIllII[8] = lIIIIIlllll(ConfigGUI.lIIlIlllI[16], ConfigGUI.lIIlIlllI[17]);
        ConfigGUI.lIIlIllII[9] = lIIIIIlllll(ConfigGUI.lIIlIlllI[18], ConfigGUI.lIIlIlllI[19]);
        ConfigGUI.lIIlIllII[10] = lIIIIIlllll(ConfigGUI.lIIlIlllI[20], ConfigGUI.lIIlIlllI[21]);
        ConfigGUI.lIIlIllII[11] = lIIIIIlllll(ConfigGUI.lIIlIlllI[22], ConfigGUI.lIIlIlllI[23]);
        ConfigGUI.lIIlIllII[12] = lIIIIlIIIII(ConfigGUI.lIIlIlllI[24], ConfigGUI.lIIlIlllI[25]);
        ConfigGUI.lIIlIllII[13] = lIIIIlIIIII(ConfigGUI.lIIlIlllI[26], ConfigGUI.lIIlIlllI[27]);
        ConfigGUI.lIIlIllII[14] = lIIIIIlllll(ConfigGUI.lIIlIlllI[28], ConfigGUI.lIIlIlllI[29]);
        ConfigGUI.lIIlIllII[15] = lIIIIlIIIIl(ConfigGUI.lIIlIlllI[30], ConfigGUI.lIIlIlllI[31]);
        ConfigGUI.lIIlIllII[16] = lIIIIlIIIII(ConfigGUI.lIIlIlllI[32], ConfigGUI.lIIlIlllI[33]);
        ConfigGUI.lIIlIllII[17] = lIIIIIlllll(ConfigGUI.lIIlIlllI[34], ConfigGUI.lIIlIlllI[35]);
        ConfigGUI.lIIlIlllI = null;
    }

    private static void lIIIIlIIlIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ConfigGUI.lIIlIlllI = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIIlIIIII(String s, String s1) {
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

    private static String lIIIIlIIIIl(String s, String s1) {
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

    private static String lIIIIIlllll(String s, String s1) {
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
