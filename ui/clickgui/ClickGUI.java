package me.explicit.ui.clickgui;

import java.awt.Color;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.config.ConfigManager;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.SettingsManager;
import me.explicit.ui.clickgui.elements.Element;
import me.explicit.ui.clickgui.elements.ModuleButton;
import me.explicit.ui.clickgui.elements.menu.ElementSlider;
import me.explicit.ui.clickgui.util.ColorUtil;
import me.explicit.ui.clickgui.util.FontUtil;
import me.explicit.utils.TimerUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class ClickGUI extends GuiScreen {

    public static ArrayList panels;
    public static ArrayList rpanels;
    private ModuleButton mb = null;
    public SettingsManager setmgr;
    boolean wasFastRenderOn;
    public static boolean enabled;
    private TimerUtils timer = new TimerUtils();
    private static final String[] lIllllI;
    private static String[] lIlllll;

    public ClickGUI() {
        this.setmgr = Explicit.instance.sm;
        FontUtil.setupFontUtils();
        (ClickGUI.panels = new ArrayList()).clear();
        final double d0 = 80.0D;
        final double d1 = 20.0D;
        final double d2 = 10.0D;
        final double d3 = 10.0D;
        double d4 = d1;
        Category[] acategory = Category.values();
        int i = acategory.length;

        for (int j = 0; j < i; ++j) {
            final Category category = acategory[j];
            final String s = String.valueOf((new StringBuilder()).append(Character.toUpperCase(category.name().toLowerCase().charAt(0))).append(category.name().toLowerCase().substring(1)));

            ClickGUI.panels.add(new Panel(s, d2, d0, (double) false, (double) category, flag, clickgui) {
                final Category val$c = category;
                final ClickGUI this$0 = ClickGUI.this;

                public void setup() {
                    Iterator iterator = Explicit.instance.mm.modules.iterator();

                    while (iterator.hasNext()) {
                        Module module = (Module) iterator.next();

                        if (module.getCategory().equals(this.val$c)) {
                            this.Elements.add(new ModuleButton(module, this));
                        }
                    }

                }
            });
            d3 += d4 + 2.0D;
        }

        ClickGUI.rpanels = new ArrayList();
        Iterator iterator = ClickGUI.panels.iterator();

        while (iterator.hasNext()) {
            Panel panel = (Panel) iterator.next();

            ClickGUI.rpanels.add(panel);
        }

        Collections.reverse(ClickGUI.rpanels);
    }

    public void drawScreen(int i, int j, float f) {
        Iterator iterator = ClickGUI.panels.iterator();

        while (iterator.hasNext()) {
            Panel panel = (Panel) iterator.next();

            panel.drawScreen(i, j, f);
        }

        ScaledResolution scaledresolution = new ScaledResolution(this.mc);

        GL11.glPushMatrix();
        GL11.glTranslated((double) scaledresolution.getScaledWidth(), (double) scaledresolution.getScaledHeight(), 0.0D);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        GL11.glPopMatrix();
        this.mb = null;
        Iterator iterator1 = ClickGUI.panels.iterator();

        Panel panel1;
        Iterator iterator2;
        ModuleButton modulebutton;

        label109:
        while (iterator1.hasNext()) {
            panel1 = (Panel) iterator1.next();
            if (panel1 != null && panel1.visible && panel1.extended && panel1.Elements != null && panel1.Elements.size() > 0) {
                iterator2 = panel1.Elements.iterator();

                while (iterator2.hasNext()) {
                    modulebutton = (ModuleButton) iterator2.next();
                    if (modulebutton.listening) {
                        this.mb = modulebutton;
                        break label109;
                    }
                }
            }
        }

        iterator1 = ClickGUI.panels.iterator();

        while (iterator1.hasNext()) {
            panel1 = (Panel) iterator1.next();
            if (panel1.extended && panel1.visible && panel1.Elements != null) {
                iterator2 = panel1.Elements.iterator();

                while (iterator2.hasNext()) {
                    modulebutton = (ModuleButton) iterator2.next();
                    if (modulebutton.extended && modulebutton.menuelements != null && !modulebutton.menuelements.isEmpty()) {
                        double d0 = -1.0D;
                        Color color = ColorUtil.getClickGUIColor().darker();
                        int k = (new Color(color.getRed(), color.getGreen(), color.getBlue(), 255)).getRGB();
                        Iterator iterator3 = modulebutton.menuelements.iterator();

                        while (iterator3.hasNext()) {
                            Element element = (Element) iterator3.next();

                            if (!element.set.isVisible()) {
                                element.offset = d0;
                                element.update();
                                element.drawScreen(i, j, f);
                                d0 += element.height;
                            }
                        }
                    }
                }
            }
        }

        if (this.mb != null) {
            drawRect(0, 0, this.width, this.height, -2012213232);
            GL11.glPushMatrix();
            GL11.glTranslatef((float) (scaledresolution.getScaledWidth() / 2), (float) (scaledresolution.getScaledHeight() / 2), 0.0F);
            FontUtil.drawBigTotalCenteredStringWithShadow(ClickGUI.lIllllI[0], 0.0F, -20.0F, -1);
            FontUtil.drawBigTotalCenteredStringWithShadow(String.valueOf((new StringBuilder()).append(ClickGUI.lIllllI[1]).append(this.mb.mod.getName()).append(this.mb.mod.getKey() > -1 ? String.valueOf((new StringBuilder()).append(ClickGUI.lIllllI[2]).append(Keyboard.getKeyName(this.mb.mod.getKey())).append(ClickGUI.lIllllI[3])) : ClickGUI.lIllllI[4])), 0.0F, 0.0F, -1);
            GL11.glPopMatrix();
        }

        String s = String.valueOf((new StringBuilder()).append(ClickGUI.lIllllI[5]).append(ClickGUI.lIllllI[6].toLowerCase()));
        char[] achar = s.toCharArray();
        double d1 = 5.0D;
        ScaledResolution scaledresolution1 = new ScaledResolution(this.mc);

        for (int l = 0; l < achar.length; ++l) {
            this.mc.fontRendererObj.drawString(String.valueOf((new StringBuilder()).append(achar[l]).append(ClickGUI.lIllllI[7])), scaledresolution1.getScaledWidth() - 10 - this.mc.fontRendererObj.getStringWidth(s) + (int) d1, 5, Explicit.instance.uiRenderer.getColor(l).getRGB());
            d1 += (double) this.mc.fontRendererObj.getStringWidth(String.valueOf((new StringBuilder()).append(achar[l]).append(ClickGUI.lIllllI[8])));
        }

        super.drawScreen(i, j, f);
    }

    public void mouseClicked(int i, int j, int k) {
        ConfigManager.saveGUISettings();
        if (this.mb == null) {
            Iterator iterator = ClickGUI.rpanels.iterator();

            Panel panel;

            while (iterator.hasNext()) {
                panel = (Panel) iterator.next();
                if (panel.extended && panel.visible && panel.Elements != null) {
                    Iterator iterator1 = panel.Elements.iterator();

                    while (iterator1.hasNext()) {
                        ModuleButton modulebutton = (ModuleButton) iterator1.next();

                        if (modulebutton.extended) {
                            Iterator iterator2 = modulebutton.menuelements.iterator();

                            while (iterator2.hasNext()) {
                                Element element = (Element) iterator2.next();

                                if (!element.set.isVisible() && element.mouseClicked(i, j, k)) {
                                    return;
                                }
                            }
                        }
                    }
                }
            }

            iterator = ClickGUI.rpanels.iterator();

            do {
                if (!iterator.hasNext()) {
                    try {
                        super.mouseClicked(i, j, k);
                    } catch (IOException ioexception) {
                        ioexception.printStackTrace();
                    }

                    return;
                }

                panel = (Panel) iterator.next();
            } while (!panel.mouseClicked(i, j, k));

        }
    }

    public void mouseReleased(int i, int j, int k) {
        if (this.mb == null) {
            Iterator iterator = ClickGUI.rpanels.iterator();

            Panel panel;

            while (iterator.hasNext()) {
                panel = (Panel) iterator.next();
                if (panel.extended && panel.visible && panel.Elements != null) {
                    Iterator iterator1 = panel.Elements.iterator();

                    while (iterator1.hasNext()) {
                        ModuleButton modulebutton = (ModuleButton) iterator1.next();

                        if (modulebutton.extended) {
                            Iterator iterator2 = modulebutton.menuelements.iterator();

                            while (iterator2.hasNext()) {
                                Element element = (Element) iterator2.next();

                                element.mouseReleased(i, j, k);
                            }
                        }
                    }
                }
            }

            iterator = ClickGUI.rpanels.iterator();

            while (iterator.hasNext()) {
                panel = (Panel) iterator.next();
                panel.mouseReleased(i, j, k);
            }

            super.mouseReleased(i, j, k);
        }
    }

    protected void keyTyped(char c0, int i) {
        Iterator iterator = ClickGUI.rpanels.iterator();

        while (iterator.hasNext()) {
            Panel panel = (Panel) iterator.next();

            if (panel != null && panel.visible && panel.extended && panel.Elements != null && panel.Elements.size() > 0) {
                Iterator iterator1 = panel.Elements.iterator();

                while (iterator1.hasNext()) {
                    ModuleButton modulebutton = (ModuleButton) iterator1.next();

                    try {
                        if (modulebutton.keyTyped(c0, i)) {
                            return;
                        }
                    } catch (IOException ioexception) {
                        ioexception.printStackTrace();
                    }
                }
            }
        }

        try {
            super.keyTyped(c0, i);
        } catch (IOException ioexception1) {
            ioexception1.printStackTrace();
        }

    }

    public void initGui() {
        ScaledResolution scaledresolution = new ScaledResolution(this.mc);

        this.buttonList.add(new GuiButton(1, 5, scaledresolution.getScaledHeight() - 25, 100, 20, ClickGUI.lIllllI[9]));
    }

    protected void actionPerformed(GuiButton guibutton) throws IOException {
        if (guibutton.id == 1) {
            this.closeAllSettings();
            double d0 = 22.0D;
            double d1 = 10.0D;
            double d2 = 10.0D;
            double d3 = d0;

            for (Iterator iterator = ClickGUI.panels.iterator(); iterator.hasNext(); d2 += d3) {
                Panel panel = (Panel) iterator.next();

                panel.dragging = false;
                panel.extended = false;
                panel.x = d1;
                panel.y = d2;
            }
        }

    }

    public void onGuiClosed() {
        Iterator iterator = ClickGUI.rpanels.iterator();

        while (iterator.hasNext()) {
            Panel panel = (Panel) iterator.next();

            if (panel.extended && panel.visible && panel.Elements != null) {
                Iterator iterator1 = panel.Elements.iterator();

                while (iterator1.hasNext()) {
                    ModuleButton modulebutton = (ModuleButton) iterator1.next();

                    if (modulebutton.extended) {
                        Iterator iterator2 = modulebutton.menuelements.iterator();

                        while (iterator2.hasNext()) {
                            Element element = (Element) iterator2.next();

                            if (element instanceof ElementSlider) {
                                ((ElementSlider) element).dragging = false;
                            }
                        }
                    }
                }
            }
        }

    }

    public void closeAllSettings() {
        Iterator iterator = ClickGUI.rpanels.iterator();

        while (iterator.hasNext()) {
            Panel panel = (Panel) iterator.next();
            ModuleButton modulebutton;

            if (panel != null && panel.visible && panel.extended && panel.Elements != null && panel.Elements.size() > 0) {
                for (Iterator iterator1 = panel.Elements.iterator(); iterator1.hasNext(); modulebutton.extended = false) {
                    modulebutton = (ModuleButton) iterator1.next();
                }
            }
        }

    }

    static {
        lIlIIIlll();
        lIlIIIllI();
        ClickGUI.enabled = false;
    }

    private static void lIlIIIllI() {
        lIllllI = new String[10];
        ClickGUI.lIllllI[0] = lIlIIIIll(ClickGUI.lIlllll[0], ClickGUI.lIlllll[1]);
        ClickGUI.lIllllI[1] = lIlIIIIll(ClickGUI.lIlllll[2], ClickGUI.lIlllll[3]);
        ClickGUI.lIllllI[2] = lIlIIIIll(ClickGUI.lIlllll[4], ClickGUI.lIlllll[5]);
        ClickGUI.lIllllI[3] = lIlIIIlII(ClickGUI.lIlllll[6], ClickGUI.lIlllll[7]);
        ClickGUI.lIllllI[4] = lIlIIIlIl(ClickGUI.lIlllll[8], ClickGUI.lIlllll[9]);
        ClickGUI.lIllllI[5] = lIlIIIlII(ClickGUI.lIlllll[10], ClickGUI.lIlllll[11]);
        ClickGUI.lIllllI[6] = lIlIIIlIl(ClickGUI.lIlllll[12], ClickGUI.lIlllll[13]);
        ClickGUI.lIllllI[7] = lIlIIIlIl(ClickGUI.lIlllll[14], ClickGUI.lIlllll[15]);
        ClickGUI.lIllllI[8] = lIlIIIIll(ClickGUI.lIlllll[16], ClickGUI.lIlllll[17]);
        ClickGUI.lIllllI[9] = lIlIIIlII(ClickGUI.lIlllll[18], ClickGUI.lIlllll[19]);
        ClickGUI.lIlllll = null;
    }

    private static void lIlIIIlll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        ClickGUI.lIlllll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIlIIIlIl(String s, String s1) {
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

    private static String lIlIIIIll(String s, String s1) {
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

    private static String lIlIIIlII(String s, String s1) {
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
