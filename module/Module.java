package me.explicit.module;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.config.ConfigManager;
import me.explicit.utils.Game;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class Module {

    protected static Minecraft mc;
    private String name;
    private String displayName;
    private String description;
    private int key;
    private Category category;
    private boolean toggled;
    private static final String[] lIIllIIlI;
    private static String[] lIIllIlll;

    public Module(String s, int i, Category category, String s1) {
        this.name = s;
        this.key = i;
        this.category = category;
        this.toggled = false;
        this.description = s1;
        this.setup();
    }

    public Module(String s, String s1, Category category) {
        this.name = s;
        this.key = 0;
        this.category = category;
        this.toggled = false;
        this.description = s1;
        this.setup();
    }

    public void onUpdate() {}

    public void onUpdateNoToggle() {}

    public void onTick() {}

    public void onMove() {}

    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    public void onToggle() {}

    public void setToggled(boolean flag) {
        this.toggled = flag;
        this.onToggle();
        ConfigManager.SaveConfigFile(Module.lIIllIIlI[0]);
        MinecraftForge.EVENT_BUS.register(this);
        if (this.toggled) {
            this.onEnable();
        } else {
            MinecraftForge.EVENT_BUS.unregister(this);
            this.onDisable();
        }

    }

    public void setToggledNoSave(boolean flag) {
        this.toggled = flag;
        this.onToggle();
        MinecraftForge.EVENT_BUS.register(this);
        if (this.toggled) {
            this.onEnable();
        } else {
            MinecraftForge.EVENT_BUS.unregister(this);
            this.onDisable();
        }

    }

    public void toggle() {
        this.toggled = !this.toggled;
        this.onToggle();
        ConfigManager.SaveConfigFile(Module.lIIllIIlI[1]);
        if (this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }

    }

    public void toggleNoSave() {
        this.toggled = !this.toggled;
        this.onToggle();
        if (this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }

    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setName(String s) {
        this.name = s;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int i) {
        ConfigManager.SaveConfigFile(Module.lIIllIIlI[2]);
        this.key = i;
    }

    public void setKeyNoSave(int i) {
        this.key = i;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isToggled() {
        return this.toggled;
    }

    public String getDisplayName() {
        return this.displayName == null ? this.name : this.displayName;
    }

    public void setDisplayName(String s) {
        this.displayName = s;
    }

    public void setup() {}

    public void onRender2D() {}

    public void onRender3D() {}

    static {
        lIIIIlllllI();
        lIIIIlllIll();
        Module.mc = Game.Minecraft();
    }

    private static void lIIIIlllIll() {
        lIIllIIlI = new String[3];
        Module.lIIllIIlI[0] = lIIIIlllIIl(Module.lIIllIlll[0], Module.lIIllIlll[1]);
        Module.lIIllIIlI[1] = lIIIIlllIlI(Module.lIIllIlll[2], Module.lIIllIlll[3]);
        Module.lIIllIIlI[2] = lIIIIlllIlI(Module.lIIllIlll[4], Module.lIIllIlll[5]);
        Module.lIIllIlll = null;
    }

    private static void lIIIIlllllI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Module.lIIllIlll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIIlllIIl(String s, String s1) {
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

    private static String lIIIIlllIlI(String s, String s1) {
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
