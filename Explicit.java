package me.explicit;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.color.ColorManager;
import me.explicit.command.commands.BindCommand;
import me.explicit.command.commands.ConfigCommand;
import me.explicit.command.commands.FriendCommand;
import me.explicit.command.commands.HelpCommand;
import me.explicit.command.commands.SettingsCommand;
import me.explicit.command.commands.ToggleCommand;
import me.explicit.config.ConfigManager;
import me.explicit.consolegui.ConsoleGUI;
import me.explicit.friends.FriendManager;
import me.explicit.module.Module;
import me.explicit.module.ModuleManager;
import me.explicit.net.NetHandler;
import me.explicit.settings.SettingsManager;
import me.explicit.ui.clickgui.ClickGUI;
import me.explicit.ui.hud.HUDRenderer;
import me.explicit.utils.Game;
import me.explicit.utils.VersionCheck;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.INetHandler;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import org.lwjgl.input.Keyboard;

public class Explicit {

    public static final String MODID;
    public static final String NAME;
    public static final String VERSION;
    public static Explicit instance;
    public ModuleManager mm;
    public SettingsManager sm;
    public ClickGUI clickGui;
    public ColorManager cm;
    public ConsoleGUI cg;
    public HUDRenderer uiRenderer;
    public ConfigManager configManager;
    public FriendManager friendManager;
    public static boolean destructed;
    public static boolean consolegui;
    private static final String[] lIIIIIIl;
    private static String[] lIIIIIll;

    public void onInit() {
        VersionCheck versioncheck = new VersionCheck();

        versioncheck.setName(Explicit.lIIIIIIl[0]);
        versioncheck.start();
        MinecraftForge.EVENT_BUS.register(this);
        this.configManager = new ConfigManager();
        this.sm = new SettingsManager();
        this.mm = new ModuleManager();
        this.clickGui = new ClickGUI();
        this.cm = new ColorManager();
        this.uiRenderer = new HUDRenderer();
        ConfigManager.init();
        this.friendManager = new FriendManager();
        if (!Explicit.destructed) {
            this.registerCommands();
        }

        this.cg = new ConsoleGUI();
        this.cg.setName(Explicit.lIIIIIIl[1]);
        this.cg.start();
    }

    public void registerCommands() {
        ClientCommandHandler.instance.registerCommand(new FriendCommand());
        ClientCommandHandler.instance.registerCommand(new ToggleCommand());
        ClientCommandHandler.instance.registerCommand(new BindCommand());
        ClientCommandHandler.instance.registerCommand(new ConfigCommand());
        ClientCommandHandler.instance.registerCommand(new SettingsCommand());
        ClientCommandHandler.instance.registerCommand(new HelpCommand());
    }

    public void onSelfDestruct() {
        Minecraft.getMinecraft().currentScreen = null;
        Iterator iterator = this.mm.modules.iterator();

        while (iterator.hasNext()) {
            Module module = (Module) iterator.next();

            MinecraftForge.EVENT_BUS.unregister(module);
            module.setToggledNoSave(false);
            module.setName(Explicit.lIIIIIIl[2]);
            module = null;
            this.mm.modules.remove(module);
        }

        MinecraftForge.EVENT_BUS.unregister(this);
        Explicit.consolegui = false;
        Explicit.destructed = true;
        this.configManager = null;
        this.uiRenderer = null;
        this.clickGui = null;
        this.mm = null;
        this.sm = null;
        File file = Minecraft.getMinecraft().mcDataDir;
        File file1 = new File(file, Explicit.lIIIIIIl[3]);
        File file2 = new File(file, Explicit.lIIIIIIl[4]);
        String[] astring = file1.list();

        for (int i = 0; i < astring.length; ++i) {
            String s = astring[i];
            File file3 = new File(file1.getPath(), s);

            file3.delete();
        }

        file1.delete();
        if (file2.exists()) {
            String[] astring1 = file2.list();

            for (int j = 0; j < astring1.length; ++j) {
                String s1 = astring1[j];
                File file4 = new File(file2.getPath(), s1);

                file4.delete();
            }

            file2.delete();
        }

    }

    @SubscribeEvent
    public void ClientTick(ClientTickEvent clienttickevent) {
        if (Game.World() != null) {
            INetHandler inethandler = Game.Player().sendQueue.getNetworkManager().getNetHandler();

            if (!(inethandler instanceof NetHandler)) {
                Game.Player().sendQueue.getNetworkManager().setNetHandler(new NetHandler((NetHandlerPlayClient) inethandler));
            }
        }

        if (!Explicit.destructed) {
            Iterator iterator = this.mm.getModules().iterator();

            while (iterator.hasNext()) {
                Module module = (Module) iterator.next();

                if (Game.World() != null && Game.Player() != null) {
                    module.onUpdateNoToggle();
                    if (module.isToggled()) {
                        module.onUpdate();
                    }
                }
            }

        }
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingUpdateEvent livingupdateevent) {
        if (livingupdateevent.entityLiving != null && livingupdateevent.entityLiving == Game.Player()) {
            Iterator iterator = this.mm.getModules().iterator();

            while (iterator.hasNext()) {
                Module module = (Module) iterator.next();

                if (module.isToggled() && Game.World() != null && Game.Player() != null) {
                    module.onMove();
                }
            }
        }

    }

    @SubscribeEvent
    public void PlayerTick(PlayerTickEvent playertickevent) {
        if (!Explicit.destructed) {
            Iterator iterator = this.mm.getModules().iterator();

            while (iterator.hasNext()) {
                Module module = (Module) iterator.next();

                if (module.isToggled() && Game.World() != null && Game.Player() != null) {
                    module.onTick();
                }
            }

        }
    }

    @SubscribeEvent
    public void key(KeyInputEvent keyinputevent) {
        if (!Explicit.destructed && Game.World() != null && Game.Player() != null) {
            try {
                if (Keyboard.isCreated() && Keyboard.getEventKeyState()) {
                    int i = Keyboard.getEventKey();

                    if (i <= 0) {
                        return;
                    }

                    Iterator iterator = this.mm.modules.iterator();

                    while (iterator.hasNext()) {
                        Module module = (Module) iterator.next();

                        if (module.getKey() == i && i > 0) {
                            module.toggle();
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
    }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent rendergameoverlayevent) {
        if (rendergameoverlayevent.type.equals(ElementType.TEXT) && !Explicit.destructed) {
            this.uiRenderer.draw();
            Iterator iterator = this.mm.getModules().iterator();

            while (iterator.hasNext()) {
                Module module = (Module) iterator.next();

                if (module.isToggled() && Game.World() != null && Game.Player() != null) {
                    module.onRender2D();
                }
            }

        }
    }

    @SubscribeEvent
    public void render3d(RenderWorldLastEvent renderworldlastevent) {
        if (!Explicit.destructed) {
            Iterator iterator = this.mm.getModules().iterator();

            while (iterator.hasNext()) {
                Module module = (Module) iterator.next();

                if (module.isToggled() && Game.World() != null && Game.Player() != null) {
                    module.onRender3D();
                }
            }

        }
    }

    static {
        lllIIIIIl();
        lllIIIIII();
        NAME = Explicit.lIIIIIIl[5];
        MODID = Explicit.lIIIIIIl[6];
        VERSION = Explicit.lIIIIIIl[7];
        Explicit.destructed = false;
        Explicit.consolegui = false;
    }

    private static void lllIIIIII() {
        lIIIIIIl = new String[8];
        Explicit.lIIIIIIl[0] = llIlllIlI(Explicit.lIIIIIll[0], Explicit.lIIIIIll[1]);
        Explicit.lIIIIIIl[1] = llIlllIll(Explicit.lIIIIIll[2], Explicit.lIIIIIll[3]);
        Explicit.lIIIIIIl[2] = llIlllIll(Explicit.lIIIIIll[4], Explicit.lIIIIIll[5]);
        Explicit.lIIIIIIl[3] = llIllllII(Explicit.lIIIIIll[6], Explicit.lIIIIIll[7]);
        Explicit.lIIIIIIl[4] = llIlllIll(Explicit.lIIIIIll[8], Explicit.lIIIIIll[9]);
        Explicit.lIIIIIIl[5] = llIllllII(Explicit.lIIIIIll[10], Explicit.lIIIIIll[11]);
        Explicit.lIIIIIIl[6] = llIlllIlI(Explicit.lIIIIIll[12], Explicit.lIIIIIll[13]);
        Explicit.lIIIIIIl[7] = llIlllIll(Explicit.lIIIIIll[14], Explicit.lIIIIIll[15]);
        Explicit.lIIIIIll = null;
    }

    private static void lllIIIIIl() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        Explicit.lIIIIIll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llIlllIlI(String s, String s1) {
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

    private static String llIlllIll(String s, String s1) {
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

    private static String llIllllII(String s, String s1) {
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
