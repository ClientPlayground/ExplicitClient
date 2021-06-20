package me.explicit.net;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.Timer;
import me.explicit.Explicit;
import me.explicit.module.combat.Reach;
import me.explicit.module.misc.PingSpoof;
import me.explicit.utils.CombatUtils;
import me.explicit.utils.Game;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.network.play.server.S06PacketUpdateHealth;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S09PacketHeldItemChange;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import net.minecraft.network.play.server.S10PacketSpawnPainting;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S2EPacketCloseWindow;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.network.play.server.S31PacketWindowProperty;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.network.play.server.S34PacketMaps;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.network.play.server.S36PacketSignEditorOpen;
import net.minecraft.network.play.server.S37PacketStatistics;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.network.play.server.S44PacketWorldBorder;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;

public class NetHandler extends NetHandlerPlayClient {

    private NetHandlerPlayClient parent;
    private static final String[] lIlllIll;
    private static String[] lIllllIl;

    public NetHandler(NetHandlerPlayClient nethandlerplayclient) {
        super(Minecraft.getMinecraft(), getGuiScreen(nethandlerplayclient), nethandlerplayclient.getNetworkManager(), Game.Player().getGameProfile());
        this.parent = nethandlerplayclient;
    }

    private static GuiScreen getGuiScreen(NetHandlerPlayClient nethandlerplayclient) {
        Field[] afield = nethandlerplayclient.getClass().getDeclaredFields();
        int i = afield.length;

        for (int j = 0; j < i; ++j) {
            Field field = afield[j];

            if (field.getType().equals(GuiScreen.class)) {
                field.setAccessible(true);

                try {
                    return (GuiScreen) field.get(nethandlerplayclient);
                } catch (Exception exception) {
                    return null;
                }
            }
        }

        return null;
    }

    private double a(float f, float f1) {
        float f2 = Math.abs(f - f1) % 360.0F;

        if (f2 > 180.0F) {
            f2 = 360.0F - f2;
        }

        return (double) f2;
    }

    private float a(double d0, double d1) {
        double d2 = d0 - Minecraft.getMinecraft().thePlayer.posX;
        double d3 = d1 - Minecraft.getMinecraft().thePlayer.posZ;
        float f = (float) Math.toDegrees(-Math.atan(d2 / d3));

        if (d3 < 0.0D && d2 < 0.0D) {
            f = (float) (90.0D + Math.toDegrees(Math.atan(d3 / d2)));
        } else if (d3 < 0.0D && d2 > 0.0D) {
            f = (float) (-90.0D + Math.toDegrees(Math.atan(d3 / d2)));
        }

        return f;
    }

    public void handleJoinGame(S01PacketJoinGame s01packetjoingame) {
        this.parent.handleJoinGame(s01packetjoingame);
    }

    public void handleSpawnObject(S0EPacketSpawnObject s0epacketspawnobject) {
        this.parent.handleSpawnObject(s0epacketspawnobject);
    }

    public void handleSpawnExperienceOrb(S11PacketSpawnExperienceOrb s11packetspawnexperienceorb) {
        this.parent.handleSpawnExperienceOrb(s11packetspawnexperienceorb);
    }

    public void handleSpawnGlobalEntity(S2CPacketSpawnGlobalEntity s2cpacketspawnglobalentity) {
        this.parent.handleSpawnGlobalEntity(s2cpacketspawnglobalentity);
    }

    public void handleSpawnPainting(S10PacketSpawnPainting s10packetspawnpainting) {
        this.parent.handleSpawnPainting(s10packetspawnpainting);
    }

    public void handleEntityVelocity(S12PacketEntityVelocity s12packetentityvelocity) {
        this.parent.handleEntityVelocity(s12packetentityvelocity);
    }

    public void handleEntityMetadata(S1CPacketEntityMetadata s1cpacketentitymetadata) {
        this.parent.handleEntityMetadata(s1cpacketentitymetadata);
    }

    public void handleSpawnPlayer(S0CPacketSpawnPlayer s0cpacketspawnplayer) {
        this.parent.handleSpawnPlayer(s0cpacketspawnplayer);
    }

    public void handleEntityTeleport(S18PacketEntityTeleport s18packetentityteleport) {
        Entity entity = Minecraft.getMinecraft().theWorld.getEntityByID(s18packetentityteleport.getEntityId());

        if (Explicit.destructed) {
            this.parent.handleEntityTeleport(s18packetentityteleport);
        } else {
            Reach reach = (Reach) Explicit.instance.mm.getModuleByName(NetHandler.lIlllIll[0]);

            if (entity instanceof EntityPlayer && CombatUtils.canTarget(entity, true) && reach.isToggled() && Explicit.instance.sm.getSettingByName(reach, NetHandler.lIlllIll[1]).getValBoolean()) {
                double d0 = (double) s18packetentityteleport.getX() / 32.0D;
                double d1 = (double) s18packetentityteleport.getZ() / 32.0D;
                double d2 = Explicit.instance.sm.getSettingByName(reach, NetHandler.lIlllIll[2]).getValDouble() - 3.0D;
                double d3 = Explicit.instance.sm.getSettingByName(reach, NetHandler.lIlllIll[3]).getValDouble() - 3.0D;
                double d4 = ThreadLocalRandom.current().nextDouble(Math.min(d2, d3), Math.max(d2, d3));

                if (d4 == 0.0D) {
                    this.parent.handleEntityTeleport(s18packetentityteleport);
                    return;
                }

                double d5 = Math.hypot(Minecraft.getMinecraft().thePlayer.posX - d0, Minecraft.getMinecraft().thePlayer.posZ - d1);

                if (d4 > d5) {
                    d4 -= d5;
                }

                float f = this.a(d0, d1);

                if (this.a(Minecraft.getMinecraft().thePlayer.rotationYaw, f) > 180.0D) {
                    this.parent.handleEntityTeleport(s18packetentityteleport);
                    return;
                }

                double d6 = Math.cos(Math.toRadians((double) (f + 90.0F)));
                double d7 = Math.sin(Math.toRadians((double) (f + 90.0F)));

                d0 -= d6 * d4;
                d1 -= d7 * d4;
                Class oclass = s18packetentityteleport.getClass();

                Field field;

                try {
                    field = oclass.getDeclaredField(NetHandler.lIlllIll[4]);
                    field.setAccessible(true);
                    field.set(s18packetentityteleport, Integer.valueOf(MathHelper.floor_double(d0 * 32.0D)));
                    field = oclass.getDeclaredField(NetHandler.lIlllIll[5]);
                    field.setAccessible(true);
                    field.set(s18packetentityteleport, Integer.valueOf(MathHelper.floor_double(d1 * 32.0D)));
                } catch (Exception exception) {
                    ;
                }

                try {
                    field = oclass.getDeclaredField(NetHandler.lIlllIll[6]);
                    field.setAccessible(true);
                    field.set(s18packetentityteleport, Integer.valueOf(MathHelper.floor_double(d0 * 32.0D)));
                    field = oclass.getDeclaredField(NetHandler.lIlllIll[7]);
                    field.setAccessible(true);
                    field.set(s18packetentityteleport, Integer.valueOf(MathHelper.floor_double(d1 * 32.0D)));
                } catch (Exception exception1) {
                    ;
                }
            }

            this.parent.handleEntityTeleport(s18packetentityteleport);
        }
    }

    public void handleHeldItemChange(S09PacketHeldItemChange s09packethelditemchange) {
        this.parent.handleHeldItemChange(s09packethelditemchange);
    }

    public void handleEntityMovement(S14PacketEntity s14packetentity) {
        this.parent.handleEntityMovement(s14packetentity);
    }

    public void handleEntityHeadLook(S19PacketEntityHeadLook s19packetentityheadlook) {
        this.parent.handleEntityHeadLook(s19packetentityheadlook);
    }

    public void handleDestroyEntities(S13PacketDestroyEntities s13packetdestroyentities) {
        this.parent.handleDestroyEntities(s13packetdestroyentities);
    }

    public void handlePlayerPosLook(S08PacketPlayerPosLook s08packetplayerposlook) {
        this.parent.handlePlayerPosLook(s08packetplayerposlook);
    }

    public void handleMultiBlockChange(S22PacketMultiBlockChange s22packetmultiblockchange) {
        this.parent.handleMultiBlockChange(s22packetmultiblockchange);
    }

    public void handleChunkData(S21PacketChunkData s21packetchunkdata) {
        this.parent.handleChunkData(s21packetchunkdata);
    }

    public void handleBlockChange(S23PacketBlockChange s23packetblockchange) {
        this.parent.handleBlockChange(s23packetblockchange);
    }

    public void handleDisconnect(S40PacketDisconnect s40packetdisconnect) {
        this.parent.handleDisconnect(s40packetdisconnect);
    }

    public void onDisconnect(IChatComponent ichatcomponent) {
        this.parent.onDisconnect(ichatcomponent);
    }

    public void handleCollectItem(S0DPacketCollectItem s0dpacketcollectitem) {
        this.parent.handleCollectItem(s0dpacketcollectitem);
    }

    public void handleChat(S02PacketChat s02packetchat) {
        IChatComponent ichatcomponent = s02packetchat.getChatComponent();

        if (!Explicit.destructed && Explicit.instance.mm.getModuleByName(NetHandler.lIlllIll[8]).isToggled()) {
            String s = s02packetchat.getChatComponent().getFormattedText();
            String s1 = s.replaceAll(Minecraft.getMinecraft().thePlayer.getName(), NetHandler.lIlllIll[9]);
            String s2 = s1.replaceAll(Minecraft.getMinecraft().thePlayer.getDisplayName().getFormattedText(), NetHandler.lIlllIll[10]);

            if (s02packetchat.getChatComponent() instanceof ChatComponentText && !s02packetchat.isChat()) {
                this.parent.handleChat(new S02PacketChat(new ChatComponentText(s2)));
                return;
            }
        }

        this.parent.handleChat(s02packetchat);
    }

    public void handleAnimation(S0BPacketAnimation s0bpacketanimation) {
        this.parent.handleAnimation(s0bpacketanimation);
    }

    public void handleUseBed(S0APacketUseBed s0apacketusebed) {
        this.parent.handleUseBed(s0apacketusebed);
    }

    public void handleSpawnMob(S0FPacketSpawnMob s0fpacketspawnmob) {
        this.parent.handleSpawnMob(s0fpacketspawnmob);
    }

    public void handleTimeUpdate(S03PacketTimeUpdate s03packettimeupdate) {
        String s = Explicit.destructed ? NetHandler.lIlllIll[11] : (Explicit.instance.mm.getModuleByName(NetHandler.lIlllIll[12]).isToggled() ? Explicit.instance.sm.getSettingByName(Explicit.instance.mm.getModuleByName(NetHandler.lIlllIll[13]), NetHandler.lIlllIll[14]).getValString().toUpperCase() : NetHandler.lIlllIll[15]);

        if (s.equalsIgnoreCase(NetHandler.lIlllIll[16])) {
            this.parent.handleTimeUpdate(new S03PacketTimeUpdate(s03packettimeupdate.getTotalWorldTime(), -6000L, true));
        } else if (s.equalsIgnoreCase(NetHandler.lIlllIll[17])) {
            this.parent.handleTimeUpdate(new S03PacketTimeUpdate(s03packettimeupdate.getTotalWorldTime(), -22880L, true));
        } else if (s.equalsIgnoreCase(NetHandler.lIlllIll[18])) {
            this.parent.handleTimeUpdate(new S03PacketTimeUpdate(s03packettimeupdate.getTotalWorldTime(), -18000L, true));
        } else {
            this.parent.handleTimeUpdate(s03packettimeupdate);
        }

    }

    public void handleSpawnPosition(S05PacketSpawnPosition s05packetspawnposition) {
        this.parent.handleSpawnPosition(s05packetspawnposition);
    }

    public void handleEntityAttach(S1BPacketEntityAttach s1bpacketentityattach) {
        this.parent.handleEntityAttach(s1bpacketentityattach);
    }

    public void handleEntityStatus(S19PacketEntityStatus s19packetentitystatus) {
        this.parent.handleEntityStatus(s19packetentitystatus);
    }

    public void handleUpdateHealth(S06PacketUpdateHealth s06packetupdatehealth) {
        this.parent.handleUpdateHealth(s06packetupdatehealth);
    }

    public void handleSetExperience(S1FPacketSetExperience s1fpacketsetexperience) {
        this.parent.handleSetExperience(s1fpacketsetexperience);
    }

    public void handleRespawn(S07PacketRespawn s07packetrespawn) {
        this.parent.handleRespawn(s07packetrespawn);
    }

    public void handleExplosion(S27PacketExplosion s27packetexplosion) {
        this.parent.handleExplosion(s27packetexplosion);
    }

    public void handleOpenWindow(S2DPacketOpenWindow s2dpacketopenwindow) {
        this.parent.handleOpenWindow(s2dpacketopenwindow);
    }

    public void handleSetSlot(S2FPacketSetSlot s2fpacketsetslot) {
        this.parent.handleSetSlot(s2fpacketsetslot);
    }

    public void handleConfirmTransaction(S32PacketConfirmTransaction s32packetconfirmtransaction) {
        this.parent.handleConfirmTransaction(s32packetconfirmtransaction);
    }

    public void handleWindowItems(S30PacketWindowItems s30packetwindowitems) {
        this.parent.handleWindowItems(s30packetwindowitems);
    }

    public void handleSignEditorOpen(S36PacketSignEditorOpen s36packetsigneditoropen) {
        this.parent.handleSignEditorOpen(s36packetsigneditoropen);
    }

    public void handleUpdateSign(S33PacketUpdateSign s33packetupdatesign) {
        this.parent.handleUpdateSign(s33packetupdatesign);
    }

    public void handleUpdateTileEntity(S35PacketUpdateTileEntity s35packetupdatetileentity) {
        this.parent.handleUpdateTileEntity(s35packetupdatetileentity);
    }

    public void handleWindowProperty(S31PacketWindowProperty s31packetwindowproperty) {
        this.parent.handleWindowProperty(s31packetwindowproperty);
    }

    public void handleEntityEquipment(S04PacketEntityEquipment s04packetentityequipment) {
        this.parent.handleEntityEquipment(s04packetentityequipment);
    }

    public void handleCloseWindow(S2EPacketCloseWindow s2epacketclosewindow) {
        this.parent.handleCloseWindow(s2epacketclosewindow);
    }

    public void handleBlockAction(S24PacketBlockAction s24packetblockaction) {
        this.parent.handleBlockAction(s24packetblockaction);
    }

    public void handleBlockBreakAnim(S25PacketBlockBreakAnim s25packetblockbreakanim) {
        this.parent.handleBlockBreakAnim(s25packetblockbreakanim);
    }

    public void handleMapChunkBulk(S26PacketMapChunkBulk s26packetmapchunkbulk) {
        this.parent.handleMapChunkBulk(s26packetmapchunkbulk);
    }

    public void handleChangeGameState(S2BPacketChangeGameState s2bpacketchangegamestate) {
        this.parent.handleChangeGameState(s2bpacketchangegamestate);
    }

    public void handleMaps(S34PacketMaps s34packetmaps) {
        this.parent.handleMaps(s34packetmaps);
    }

    public void handleEffect(S28PacketEffect s28packeteffect) {
        this.parent.handleEffect(s28packeteffect);
    }

    public void handleStatistics(S37PacketStatistics s37packetstatistics) {
        this.parent.handleStatistics(s37packetstatistics);
    }

    public void handleEntityEffect(S1DPacketEntityEffect s1dpacketentityeffect) {
        this.parent.handleEntityEffect(s1dpacketentityeffect);
    }

    public void handleRemoveEntityEffect(S1EPacketRemoveEntityEffect s1epacketremoveentityeffect) {
        this.parent.handleRemoveEntityEffect(s1epacketremoveentityeffect);
    }

    public void handlePlayerListItem(S38PacketPlayerListItem s38packetplayerlistitem) {
        this.parent.handlePlayerListItem(s38packetplayerlistitem);
    }

    public void handleKeepAlive(final S00PacketKeepAlive s00packetkeepalive) {
        if (PingSpoof.toggled) {
            Timer timer = new Timer(PingSpoof.ping, new ActionListener() {
                final S00PacketKeepAlive val$packetIn = s00packetkeepalive;
                final NetHandler this$0 = NetHandler.this;

                public void actionPerformed(ActionEvent actionevent) {
                    this.this$0.parent.handleKeepAlive(this.val$packetIn);
                }
            });

            timer.setRepeats(false);
            timer.start();
        } else {
            this.parent.handleKeepAlive(s00packetkeepalive);
        }

    }

    public void handlePlayerAbilities(S39PacketPlayerAbilities s39packetplayerabilities) {
        this.parent.handlePlayerAbilities(s39packetplayerabilities);
    }

    public void handleTabComplete(S3APacketTabComplete s3apackettabcomplete) {
        this.parent.handleTabComplete(s3apackettabcomplete);
    }

    public void handleSoundEffect(S29PacketSoundEffect s29packetsoundeffect) {
        this.parent.handleSoundEffect(s29packetsoundeffect);
    }

    public void handleCustomPayload(S3FPacketCustomPayload s3fpacketcustompayload) {
        this.parent.handleCustomPayload(s3fpacketcustompayload);
    }

    public void handleScoreboardObjective(S3BPacketScoreboardObjective s3bpacketscoreboardobjective) {
        this.parent.handleScoreboardObjective(s3bpacketscoreboardobjective);
    }

    public void handleUpdateScore(S3CPacketUpdateScore s3cpacketupdatescore) {
        this.parent.handleUpdateScore(s3cpacketupdatescore);
    }

    public void handleDisplayScoreboard(S3DPacketDisplayScoreboard s3dpacketdisplayscoreboard) {
        this.parent.handleDisplayScoreboard(s3dpacketdisplayscoreboard);
    }

    public void handleWorldBorder(S44PacketWorldBorder s44packetworldborder) {
        this.parent.handleWorldBorder(s44packetworldborder);
    }

    public void handleTeams(S3EPacketTeams s3epacketteams) {
        this.parent.handleTeams(s3epacketteams);
    }

    public void handleParticles(S2APacketParticles s2apacketparticles) {
        this.parent.handleParticles(s2apacketparticles);
    }

    public void handleEntityProperties(S20PacketEntityProperties s20packetentityproperties) {
        this.parent.handleEntityProperties(s20packetentityproperties);
    }

    static NetHandlerPlayClient access$000(NetHandler nethandler) {
        return nethandler.parent;
    }

    static {
        lIlIllIlII();
        lIlIllIIlI();
    }

    private static void lIlIllIIlI() {
        lIlllIll = new String[19];
        NetHandler.lIlllIll[0] = lIlIlIlllI(NetHandler.lIllllIl[0], NetHandler.lIllllIl[1]);
        NetHandler.lIlllIll[1] = lIlIlIlllI(NetHandler.lIllllIl[2], NetHandler.lIllllIl[3]);
        NetHandler.lIlllIll[2] = lIlIlIllll(NetHandler.lIllllIl[4], NetHandler.lIllllIl[5]);
        NetHandler.lIlllIll[3] = lIlIlIllll(NetHandler.lIllllIl[6], NetHandler.lIllllIl[7]);
        NetHandler.lIlllIll[4] = lIlIlIlllI(NetHandler.lIllllIl[8], NetHandler.lIllllIl[9]);
        NetHandler.lIlllIll[5] = lIlIlIlllI(NetHandler.lIllllIl[10], NetHandler.lIllllIl[11]);
        NetHandler.lIlllIll[6] = lIlIlIlllI(NetHandler.lIllllIl[12], NetHandler.lIllllIl[13]);
        NetHandler.lIlllIll[7] = lIlIllIIII(NetHandler.lIllllIl[14], NetHandler.lIllllIl[15]);
        NetHandler.lIlllIll[8] = lIlIllIIII(NetHandler.lIllllIl[16], NetHandler.lIllllIl[17]);
        NetHandler.lIlllIll[9] = lIlIlIlllI(NetHandler.lIllllIl[18], NetHandler.lIllllIl[19]);
        NetHandler.lIlllIll[10] = lIlIlIllll(NetHandler.lIllllIl[20], NetHandler.lIllllIl[21]);
        NetHandler.lIlllIll[11] = lIlIlIlllI(NetHandler.lIllllIl[22], NetHandler.lIllllIl[23]);
        NetHandler.lIlllIll[12] = lIlIlIllll(NetHandler.lIllllIl[24], NetHandler.lIllllIl[25]);
        NetHandler.lIlllIll[13] = lIlIlIllll(NetHandler.lIllllIl[26], NetHandler.lIllllIl[27]);
        NetHandler.lIlllIll[14] = lIlIlIllll(NetHandler.lIllllIl[28], NetHandler.lIllllIl[29]);
        NetHandler.lIlllIll[15] = lIlIlIllll(NetHandler.lIllllIl[30], NetHandler.lIllllIl[31]);
        NetHandler.lIlllIll[16] = lIlIlIllll(NetHandler.lIllllIl[32], NetHandler.lIllllIl[33]);
        NetHandler.lIlllIll[17] = lIlIlIlllI(NetHandler.lIllllIl[34], NetHandler.lIllllIl[35]);
        NetHandler.lIlllIll[18] = lIlIlIllll(NetHandler.lIllllIl[36], NetHandler.lIllllIl[37]);
        NetHandler.lIllllIl = null;
    }

    private static void lIlIllIlII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        NetHandler.lIllllIl = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIlIlIllll(String s, String s1) {
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

    private static String lIlIllIIII(String s, String s1) {
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

    private static String lIlIlIlllI(String s, String s1) {
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
}
