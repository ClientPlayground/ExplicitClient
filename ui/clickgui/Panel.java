package me.explicit.ui.clickgui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import me.explicit.ui.clickgui.elements.Element;
import me.explicit.ui.clickgui.elements.ModuleButton;
import me.explicit.ui.clickgui.util.ColorUtil;
import me.explicit.ui.clickgui.util.FontUtil;
import net.minecraft.client.gui.Gui;

public class Panel {

    public String title;
    public double x;
    public double y;
    private double x2;
    private double y2;
    public double width;
    public double height;
    public boolean dragging;
    public boolean extended;
    public boolean visible;
    public ArrayList Elements;
    public ClickGUI clickgui;

    public Panel(String s, double d0, double d1, double d2, double d3, boolean flag, ClickGUI clickgui) {
        this.title = s;
        this.x = d0;
        this.y = d1;
        this.width = d2;
        this.height = d3;
        this.extended = flag;
        this.dragging = false;
        this.visible = true;
        this.clickgui = clickgui;
        (this.Elements = new ArrayList()).clear();
        this.setup();
    }

    public void setup() {}

    public void drawScreen(int i, int j, float f) {
        if (this.visible) {
            if (this.dragging) {
                this.x = this.x2 + (double) i;
                this.y = this.y2 + (double) j;
            }

            Color color = (new Color(ColorUtil.getClickGUIColor().getRed(), ColorUtil.getClickGUIColor().getGreen(), ColorUtil.getClickGUIColor().getBlue(), 0)).darker();
            int k = (new Color(color.getRed(), color.getGreen(), color.getBlue(), 200)).getRGB();

            Gui.drawRect((int) this.x, (int) this.y, (int) this.x + (int) this.width, (int) this.y + (int) this.height, -15592942);
            Gui.drawRect((int) this.x, (int) this.y, (int) this.x + 80, (int) this.y + (int) this.height, k);
            if (this.extended && !this.Elements.isEmpty()) {
                double d0 = this.y + this.height;
                int l = (new Color(ColorUtil.getClickGUIColor().getRed(), ColorUtil.getClickGUIColor().getGreen(), ColorUtil.getClickGUIColor().getBlue(), 100)).darker().darker().getRGB();
                Iterator iterator = this.Elements.iterator();

                ModuleButton modulebutton;

                while (iterator.hasNext()) {
                    modulebutton = (ModuleButton) iterator.next();
                    Element element = null;

                    Gui.drawRect((int) this.x, (int) d0, (int) this.x + (int) this.width, (int) d0 + (int) modulebutton.height + 1, l);
                    modulebutton.x = this.x + 2.0D;
                    modulebutton.y = d0 + 1.0D;
                    modulebutton.width = this.width - 4.0D;
                    modulebutton.drawScreen(i, j, f);
                    if (modulebutton.extended) {
                        int i1 = 1;
                        Iterator iterator1 = modulebutton.menuelements.iterator();

                        while (iterator1.hasNext()) {
                            Element element1 = (Element) iterator1.next();

                            if (!element1.set.isVisible()) {
                                i1 = (int) ((double) i1 + element1.height);
                                element = element1;
                            }
                        }

                        d0 += modulebutton.height + (double) i1;
                    } else {
                        d0 += modulebutton.height + 1.0D;
                    }

                    if (element != null) {
                        element.setEndEl(true);
                        Iterator iterator2 = modulebutton.menuelements.iterator();

                        while (iterator2.hasNext()) {
                            Element element2 = (Element) iterator2.next();

                            if (element2 != element) {
                                element2.setEndEl(false);
                            }
                        }
                    }
                }

                FontUtil.drawStringWithShadow(this.title, (float) (this.x + 5.0D), (float) (this.y + this.height / 2.0D - (double) (FontUtil.getFontHeight() / 2)), -1052689);
                iterator = this.Elements.iterator();

                while (iterator.hasNext()) {
                    modulebutton = (ModuleButton) iterator.next();
                    modulebutton.onUpdate();
                }
            } else {
                FontUtil.drawStringWithShadow(this.title, (float) (this.x + 5.0D), (float) (this.y + this.height / 2.0D - (double) (FontUtil.getFontHeight() / 2)), -1052689);
            }

        }
    }

    public boolean mouseClicked(int i, int j, int k) {
        if (!this.visible) {
            return false;
        } else if (k == 0 && this.isHovered(i, j)) {
            this.x2 = this.x - (double) i;
            this.y2 = this.y - (double) j;
            this.dragging = true;
            return true;
        } else if (k == 1 && this.isHovered(i, j)) {
            this.extended = !this.extended;
            return true;
        } else {
            if (this.extended) {
                Iterator iterator = this.Elements.iterator();

                while (iterator.hasNext()) {
                    ModuleButton modulebutton = (ModuleButton) iterator.next();

                    if (modulebutton.mouseClicked(i, j, k)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public void mouseReleased(int i, int j, int k) {
        if (this.visible) {
            if (k == 0) {
                this.dragging = false;
            }

        }
    }

    public boolean isHovered(int i, int j) {
        return (double) i >= this.x && (double) i <= this.x + this.width && (double) j >= this.y && (double) j <= this.y + this.height;
    }
}
