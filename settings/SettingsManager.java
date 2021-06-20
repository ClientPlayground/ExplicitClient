package me.explicit.settings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.explicit.module.Module;

public class SettingsManager {

    private List settings;
    ArrayList out;
    ArrayList outSetting;

    public void rSetting(Setting setting) {
        if (this.settings == null) {
            (this.settings = new ArrayList()).add(setting);
        } else {
            this.settings.add(setting);
        }

    }

    public List getSettings() {
        return this.settings;
    }

    public List getSettingsByMod(Module module) {
        if (this.outSetting == null) {
            (this.outSetting = new ArrayList()).clear();
        } else {
            this.outSetting.clear();
        }

        Iterator iterator = this.getSettings().iterator();

        while (iterator.hasNext()) {
            Setting setting = (Setting) iterator.next();

            if (setting.getParentMod().equals(module)) {
                this.outSetting.add(setting);
            }
        }

        if (this.outSetting.isEmpty()) {
            return null;
        } else {
            return this.outSetting;
        }
    }

    public List getSettingsNameByMod(Module module) {
        if (this.out == null) {
            (this.out = new ArrayList()).clear();
        } else {
            this.out.clear();
        }

        Iterator iterator = this.getSettings().iterator();

        while (iterator.hasNext()) {
            Setting setting = (Setting) iterator.next();

            if (setting.getParentMod().equals(module)) {
                this.out.add(setting.getName());
            }
        }

        if (this.out.isEmpty()) {
            return null;
        } else {
            return this.out;
        }
    }

    public Setting getSettingByName(Module module, String s) {
        Iterator iterator = this.getSettings().iterator();

        Setting setting;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            setting = (Setting) iterator.next();
        } while (!setting.getName().equalsIgnoreCase(s) || !setting.getParentMod().equals(module));

        return setting;
    }
}
