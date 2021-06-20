package me.explicit.module.player;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.utils.Game;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import org.lwjgl.input.Mouse;

public class AutoTool extends Module {

    private int oldSlot = -1;
    private boolean wasBreaking = false;
    private static final String[] lIlIIIll;
    private static String[] lIlIIlII;

    public AutoTool() {
        super(AutoTool.lIlIIIll[0], AutoTool.lIlIIIll[1], Category.PLAYER);
    }

    public void onEnable() {
        super.onEnable();
        this.oldSlot = AutoTool.mc.thePlayer.inventory.currentItem;
        this.wasBreaking = false;
    }

    @SubscribeEvent
    public void tick(RenderTickEvent rendertickevent) {
        if (AutoTool.mc.currentScreen == null && Mouse.isButtonDown(0) && Game.Player() != null && Game.World() != null && AutoTool.mc.objectMouseOver != null && AutoTool.mc.objectMouseOver.getBlockPos() != null && AutoTool.mc.objectMouseOver.entityHit == null) {
            try {
                float f = 1.0F;
                int i = -1;
                Block block = AutoTool.mc.theWorld.getBlockState(AutoTool.mc.objectMouseOver.getBlockPos()).getBlock();

                for (int j = 0; j < 8; ++j) {
                    ItemStack itemstack = Game.Player().inventory.getStackInSlot(j);

                    if (itemstack != null) {
                        float f1 = itemstack.getStrVsBlock(block);

                        if (f1 > f) {
                            f = f1;
                            i = j;
                        }
                    }
                }

                if (i != -1 && AutoTool.mc.thePlayer.inventory.currentItem != i) {
                    AutoTool.mc.thePlayer.inventory.currentItem = i;
                    this.wasBreaking = true;
                } else if (i == -1) {
                    if (this.wasBreaking) {
                        AutoTool.mc.thePlayer.inventory.currentItem = this.oldSlot;
                        this.wasBreaking = false;
                    }

                    this.oldSlot = AutoTool.mc.thePlayer.inventory.currentItem;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if (Game.Player() != null && Game.World() != null) {
            if (this.wasBreaking) {
                AutoTool.mc.thePlayer.inventory.currentItem = this.oldSlot;
                this.wasBreaking = false;
            }

            this.oldSlot = AutoTool.mc.thePlayer.inventory.currentItem;
        }

    }

    static {
        lIIlIIIIll();
        lIIlIIIIlI();
    }

    private static void lIIlIIIIlI() {
        lIlIIIll = new String[2];
        AutoTool.lIlIIIll[0] = lIIlIIIIIl(AutoTool.lIlIIlII[0], AutoTool.lIlIIlII[1]);
        AutoTool.lIlIIIll[1] = lIIlIIIIIl(AutoTool.lIlIIlII[2], AutoTool.lIlIIlII[3]);
        AutoTool.lIlIIlII = null;
    }

    private static void lIIlIIIIll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        AutoTool.lIlIIlII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIIlIIIIIl(String s, String s1) {
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
