package me.explicit.module.blatant;

import io.netty.util.internal.ThreadLocalRandom;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.config.ConfigManager;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.Setting;
import me.explicit.utils.ChatUtils;
import me.explicit.utils.CombatUtils;
import me.explicit.utils.Game;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class KillSults extends Module {

    private List killsults = new ArrayList();
    private String mode;
    private EntityLivingBase lastHit;
    private static final String[] lIIllllI;
    private static String[] lIIlllll;

    public KillSults() {
        super(KillSults.lIIllllI[0], 0, Category.BLATANT, KillSults.lIIllllI[1]);
        this.mode = KillSults.lIIllllI[2];
        this.lastHit = null;
    }

    public void setup() {
        ArrayList arraylist = new ArrayList();

        arraylist.add(KillSults.lIIllllI[3]);
        arraylist.add(KillSults.lIIllllI[4]);
        Explicit.instance.sm.rSetting(new Setting(KillSults.lIIllllI[5], this, KillSults.lIIllllI[6], arraylist));
        Explicit.instance.sm.rSetting(new Setting(KillSults.lIIllllI[7], this, false));
    }

    public void onEnable() {
        super.onEnable();
        this.setKillsults();
    }

    public void onUpdateNoToggle() {
        this.mode = Explicit.instance.sm.getSettingByName(this, KillSults.lIIllllI[8]).getValString();
        if (Explicit.instance.sm.getSettingByName(this, KillSults.lIIllllI[9]).getValBoolean()) {
            this.setKillsults();
            Explicit.instance.sm.getSettingByName(this, KillSults.lIIllllI[10]).setValBoolean(false);
        }

    }

    @SubscribeEvent
    public void chat(ClientChatReceivedEvent clientchatreceivedevent) {
        if (this.mode.equalsIgnoreCase(KillSults.lIIllllI[11]) && this.lastHit != null) {
            String s = clientchatreceivedevent.message.getUnformattedText();

            if (s.startsWith(this.lastHit.getName()) && (s.endsWith(Game.Player().getName()) || s.endsWith(String.valueOf((new StringBuilder()).append(Game.Player().getName()).append(KillSults.lIIllllI[12]))))) {
                this.insult();
            }
        }

    }

    @SubscribeEvent
    public void kill(LivingDeathEvent livingdeathevent) {
        if (livingdeathevent.source.getEntity() != null && livingdeathevent.source.getEntity().getName() == Game.Player().getName() && CombatUtils.canTarget(livingdeathevent.entityLiving, true) && this.mode.equalsIgnoreCase(KillSults.lIIllllI[13])) {
            this.insult();
        }

    }

    @SubscribeEvent(
        priority = EventPriority.LOWEST
    )
    public void onAttack(AttackEntityEvent attackentityevent) {
        if (attackentityevent.entityLiving != null && attackentityevent.entityLiving.getName() == Game.Player().getName() && attackentityevent.target != null && CombatUtils.canTarget(attackentityevent.target, true)) {
            this.lastHit = (EntityLivingBase) attackentityevent.target;
        }

    }

    public void insult() {
        if (!this.killsults.isEmpty()) {
            String s = (String) this.killsults.get(ThreadLocalRandom.current().nextInt(this.killsults.size()));

            Game.Player().sendChatMessage(s.replaceAll(KillSults.lIIllllI[14], this.lastHit.getName()));
        }
    }

    public void setKillsults() {
        ConfigManager.getConfigFile(KillSults.lIIllllI[15], false);
        File file = ConfigManager.KILLSULTS;

        try {
            BufferedReader bufferedreader = new BufferedReader(new FileReader(file));

            this.killsults.clear();

            String s;

            while ((s = bufferedreader.readLine()) != null) {
                this.killsults.add(s);
            }

            ChatUtils.sendMessage(KillSults.lIIllllI[16]);
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }

    }

    static {
        lIIIlIllll();
        lIIIlIlllI();
    }

    private static void lIIIlIlllI() {
        lIIllllI = new String[17];
        KillSults.lIIllllI[0] = lIIIlIlIll(KillSults.lIIlllll[0], KillSults.lIIlllll[1]);
        KillSults.lIIllllI[1] = lIIIlIlIll(KillSults.lIIlllll[2], KillSults.lIIlllll[3]);
        KillSults.lIIllllI[2] = lIIIlIlIll(KillSults.lIIlllll[4], KillSults.lIIlllll[5]);
        KillSults.lIIllllI[3] = lIIIlIllII(KillSults.lIIlllll[6], KillSults.lIIlllll[7]);
        KillSults.lIIllllI[4] = lIIIlIlIll(KillSults.lIIlllll[8], KillSults.lIIlllll[9]);
        KillSults.lIIllllI[5] = lIIIlIllII(KillSults.lIIlllll[10], KillSults.lIIlllll[11]);
        KillSults.lIIllllI[6] = lIIIlIllIl(KillSults.lIIlllll[12], KillSults.lIIlllll[13]);
        KillSults.lIIllllI[7] = lIIIlIlIll(KillSults.lIIlllll[14], KillSults.lIIlllll[15]);
        KillSults.lIIllllI[8] = lIIIlIllII(KillSults.lIIlllll[16], KillSults.lIIlllll[17]);
        KillSults.lIIllllI[9] = lIIIlIllIl(KillSults.lIIlllll[18], KillSults.lIIlllll[19]);
        KillSults.lIIllllI[10] = lIIIlIllIl(KillSults.lIIlllll[20], KillSults.lIIlllll[21]);
        KillSults.lIIllllI[11] = lIIIlIllIl(KillSults.lIIlllll[22], KillSults.lIIlllll[23]);
        KillSults.lIIllllI[12] = lIIIlIllIl(KillSults.lIIlllll[24], KillSults.lIIlllll[25]);
        KillSults.lIIllllI[13] = lIIIlIllIl(KillSults.lIIlllll[26], KillSults.lIIlllll[27]);
        KillSults.lIIllllI[14] = lIIIlIlIll(KillSults.lIIlllll[28], KillSults.lIIlllll[29]);
        KillSults.lIIllllI[15] = lIIIlIlIll(KillSults.lIIlllll[30], KillSults.lIIlllll[31]);
        KillSults.lIIllllI[16] = lIIIlIllII(KillSults.lIIlllll[32], KillSults.lIIlllll[33]);
        KillSults.lIIlllll = null;
    }

    private static void lIIIlIllll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        KillSults.lIIlllll = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIIlIlIll(String s, String s1) {
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

    private static String lIIIlIllIl(String s, String s1) {
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

    private static String lIIIlIllII(String s, String s1) {
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
