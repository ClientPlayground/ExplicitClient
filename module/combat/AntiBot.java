package me.explicit.module.combat;

import com.google.common.collect.Ordering;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.Setting;
import me.explicit.utils.TimerUtils;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;

public class AntiBot extends Module {

    private TimerUtils notificationTimer = new TimerUtils();
    private TimerUtils resetTimer = new TimerUtils();
    private int botsFound;
    private static List bots;
    String mode;
    private static final String[] llIIIlII;
    private static String[] llIIIlIl;

    public AntiBot() {
        super(AntiBot.llIIIlII[0], 0, Category.COMBAT, AntiBot.llIIIlII[1]);
    }

    public void setup() {
        ArrayList arraylist;

        (arraylist = new ArrayList()).add(AntiBot.llIIIlII[2]);
        arraylist.add(AntiBot.llIIIlII[3]);
        Explicit.instance.sm.rSetting(new Setting(AntiBot.llIIIlII[4], this, AntiBot.llIIIlII[5], arraylist));
    }

    public void onUpdate() {
        if (this.resetTimer.hasReached(20000.0D)) {
            AntiBot.bots.clear();
            this.resetTimer.reset();
        }

        this.mode = Explicit.instance.sm.getSettingByName(this, AntiBot.llIIIlII[6]).getValString();
        if (this.botsFound > 0 && this.notificationTimer.hasReached(1000.0D)) {
            this.notificationTimer.reset();
            this.botsFound = 0;
        }

        if (AntiBot.mc.thePlayer.ticksExisted <= 0) {
            this.notificationTimer.reset();
            this.botsFound = 0;
            AntiBot.bots.clear();
        }

        Iterator iterator;
        Object object;
        EntityPlayer entityplayer;

        if (this.mode.equalsIgnoreCase(AntiBot.llIIIlII[7])) {
            iterator = Module.mc.theWorld.playerEntities.iterator();

            while (iterator.hasNext()) {
                object = iterator.next();
                if (object instanceof EntityPlayer) {
                    entityplayer = (EntityPlayer) object;
                    if (entityplayer.ticksExisted < 2 && entityplayer.getHealth() < 20.0F && entityplayer.getHealth() > 0.0F && entityplayer != Module.mc.thePlayer) {
                        this.add(entityplayer, false);
                        ++this.botsFound;
                    }
                }
            }
        } else if (this.mode.equalsIgnoreCase(AntiBot.llIIIlII[8])) {
            iterator = AntiBot.mc.theWorld.getLoadedEntityList().iterator();

            while (iterator.hasNext()) {
                object = iterator.next();
                if (object instanceof EntityPlayer && object != AntiBot.mc.thePlayer && !AntiBot.bots.contains(object)) {
                    entityplayer = (EntityPlayer) object;
                    String s = entityplayer.getDisplayName().getFormattedText();
                    String s1 = entityplayer.getCustomNameTag();
                    String s2 = entityplayer.getName();
                    boolean flag = false;

                    if (this.getTabPlayerList() != null && !this.getTabPlayerList().contains(entityplayer)) {
                        this.add(entityplayer, false);
                        flag = true;
                    }

                    if (s.contains(AntiBot.llIIIlII[9]) && entityplayer != AntiBot.mc.thePlayer) {
                        this.add(entityplayer, false);
                        flag = true;
                    }

                    if (s1.equalsIgnoreCase(s2) && entityplayer.getMaxHealth() == 20.0F && (this.getTabPlayerList() == null || !this.getTabPlayerList().contains(entityplayer)) && s.contains(AntiBot.llIIIlII[10]) && s.contains(AntiBot.llIIIlII[11])) {
                        this.add(entityplayer, true);
                        flag = true;
                    }
                }
            }
        }

    }

    public void add(EntityPlayer entityplayer, boolean flag) {
        if (!AntiBot.bots.contains(entityplayer)) {
            ++this.botsFound;
            AntiBot.bots.add(entityplayer);
        }

    }

    public List getTabPlayerList() {
        NetHandlerPlayClient nethandlerplayclient = AntiBot.mc.thePlayer.sendQueue;
        ArrayList arraylist;

        (arraylist = new ArrayList()).clear();
        Ordering ordering = this.field_175252_a();

        if (ordering == null) {
            return null;
        } else {
            List list = ordering.sortedCopy(nethandlerplayclient.getPlayerInfoMap());
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                Object object = iterator.next();
                NetworkPlayerInfo networkplayerinfo = (NetworkPlayerInfo) object;

                if (networkplayerinfo != null) {
                    arraylist.add(AntiBot.mc.theWorld.getPlayerEntityByName(networkplayerinfo.getGameProfile().getName()));
                }
            }

            return arraylist;
        }
    }

    public Ordering field_175252_a() {
        try {
            Class oclass = GuiPlayerTabOverlay.class;
            Field field = oclass.getDeclaredField(AntiBot.llIIIlII[12]);

            field.setAccessible(true);
            return (Ordering) field.get(GuiPlayerTabOverlay.class);
        } catch (Exception exception) {
            return null;
        }
    }

    public void onEnable() {
        super.onEnable();
        AntiBot.bots.clear();
    }

    public void onDisable() {
        super.onDisable();
        AntiBot.bots.clear();
    }

    public static List getBots() {
        return AntiBot.bots;
    }

    static {
        lIllIlIIlI();
        lIllIlIIIl();
        AntiBot.bots = new ArrayList();
    }

    private static void lIllIlIIIl() {
        llIIIlII = new String[13];
        AntiBot.llIIIlII[0] = lIllIIlllI(AntiBot.llIIIlIl[0], AntiBot.llIIIlIl[1]);
        AntiBot.llIIIlII[1] = lIllIIllll(AntiBot.llIIIlIl[2], AntiBot.llIIIlIl[3]);
        AntiBot.llIIIlII[2] = lIllIlIIII(AntiBot.llIIIlIl[4], AntiBot.llIIIlIl[5]);
        AntiBot.llIIIlII[3] = lIllIlIIII(AntiBot.llIIIlIl[6], AntiBot.llIIIlIl[7]);
        AntiBot.llIIIlII[4] = lIllIIllll(AntiBot.llIIIlIl[8], AntiBot.llIIIlIl[9]);
        AntiBot.llIIIlII[5] = lIllIIlllI(AntiBot.llIIIlIl[10], AntiBot.llIIIlIl[11]);
        AntiBot.llIIIlII[6] = lIllIlIIII(AntiBot.llIIIlIl[12], AntiBot.llIIIlIl[13]);
        AntiBot.llIIIlII[7] = lIllIIllll(AntiBot.llIIIlIl[14], AntiBot.llIIIlIl[15]);
        AntiBot.llIIIlII[8] = lIllIIlllI(AntiBot.llIIIlIl[16], AntiBot.llIIIlIl[17]);
        AntiBot.llIIIlII[9] = lIllIlIIII(AntiBot.llIIIlIl[18], AntiBot.llIIIlIl[19]);
        AntiBot.llIIIlII[10] = lIllIIlllI(AntiBot.llIIIlIl[20], AntiBot.llIIIlIl[21]);
        AntiBot.llIIIlII[11] = lIllIIlllI(AntiBot.llIIIlIl[22], AntiBot.llIIIlIl[23]);
        AntiBot.llIIIlII[12] = lIllIlIIII(AntiBot.llIIIlIl[24], AntiBot.llIIIlIl[25]);
        AntiBot.llIIIlIl = null;
    }

    private static void lIllIlIIlI() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        AntiBot.llIIIlIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIllIIlllI(String s, String s1) {
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

    private static String lIllIIllll(String s, String s1) {
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

    private static String lIllIlIIII(String s, String s1) {
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
