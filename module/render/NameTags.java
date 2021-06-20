package me.explicit.module.render;

import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import me.explicit.Explicit;
import me.explicit.module.Category;
import me.explicit.module.Module;
import me.explicit.settings.Setting;
import me.explicit.utils.CombatUtils;
import me.explicit.utils.Game;
import me.explicit.utils.PrivateUtils;
import me.explicit.utils.RenderUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.RenderLivingEvent.Specials.Pre;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class NameTags extends Module {

    private float scale;
    private String mode;
    boolean armor;
    boolean dura;
    boolean players = true;
    boolean invis;
    boolean mobs = false;
    boolean animals = false;
    private ArrayList entities;
    float _x;
    float _y;
    float _z;
    private static final String[] lIIIll;
    private static String[] llIIII;

    public NameTags() {
        super(NameTags.lIIIll[0], 0, Category.RENDER, NameTags.lIIIll[1]);
    }

    public void setup() {
        ArrayList arraylist;

        (arraylist = new ArrayList()).add(NameTags.lIIIll[2]);
        arraylist.add(NameTags.lIIIll[3]);
        Explicit.instance.sm.rSetting(new Setting(NameTags.lIIIll[4], this, NameTags.lIIIll[5], arraylist));
        Explicit.instance.sm.rSetting(new Setting(NameTags.lIIIll[6], this, 5.0D, 0.1D, 10.0D, false));
        Explicit.instance.sm.rSetting(new Setting(NameTags.lIIIll[7], this, 0.0D, 0.0D, 512.0D, true));
        Explicit.instance.sm.rSetting(new Setting(NameTags.lIIIll[8], this, true));
        Explicit.instance.sm.rSetting(new Setting(NameTags.lIIIll[9], this, false));
        Explicit.instance.sm.rSetting(new Setting(NameTags.lIIIll[10], this, true));
    }

    public void onUpdate() {
        this.scale = (float) Explicit.instance.sm.getSettingByName(this, NameTags.lIIIll[11]).getValDouble();
        this.mode = Explicit.instance.sm.getSettingByName(this, NameTags.lIIIll[12]).getValString();
        this.armor = Explicit.instance.sm.getSettingByName(this, NameTags.lIIIll[13]).getValBoolean();
        this.dura = Explicit.instance.sm.getSettingByName(this, NameTags.lIIIll[14]).getValBoolean();
    }

    @SubscribeEvent
    public void nameTag(Pre pre) {
        if (pre.entity.getDisplayName().getFormattedText() != null && pre.entity.getDisplayName().getFormattedText() != NameTags.lIIIll[15] && pre.entity instanceof EntityPlayer && CombatUtils.canTarget(pre.entity, false) && ((double) Game.Player().getDistanceToEntity(pre.entity) <= Explicit.instance.sm.getSettingByName(this, NameTags.lIIIll[16]).getValDouble() || Explicit.instance.sm.getSettingByName(this, NameTags.lIIIll[17]).getValDouble() == 0.0D)) {
            pre.setCanceled(true);
        }

    }

    @SubscribeEvent
    public void render3d(RenderWorldLastEvent renderworldlastevent) {
        ArrayList arraylist = new ArrayList();

        if (arraylist.size() > 100) {
            arraylist.clear();
        }

        Iterator iterator = Module.mc.theWorld.playerEntities.iterator();

        while (iterator.hasNext()) {
            EntityLivingBase entitylivingbase = (EntityLivingBase) iterator.next();

            if ((double) Game.Player().getDistanceToEntity(entitylivingbase) > Explicit.instance.sm.getSettingByName(this, NameTags.lIIIll[18]).getValDouble() && Explicit.instance.sm.getSettingByName(this, NameTags.lIIIll[19]).getValDouble() != 0.0D) {
                if (arraylist.contains(entitylivingbase)) {
                    arraylist.remove(entitylivingbase);
                }
            } else if (entitylivingbase.getName().contains(NameTags.lIIIll[20])) {
                if (arraylist.contains(entitylivingbase)) {
                    arraylist.remove(entitylivingbase);
                }
            } else if (entitylivingbase.isEntityAlive()) {
                if (entitylivingbase.isInvisible()) {
                    if (arraylist.contains(entitylivingbase)) {
                        arraylist.remove(entitylivingbase);
                    }
                } else if (entitylivingbase == Module.mc.thePlayer) {
                    if (arraylist.contains(entitylivingbase)) {
                        arraylist.remove(entitylivingbase);
                    }
                } else {
                    if (arraylist.size() > 100) {
                        break;
                    }

                    if (!arraylist.contains(entitylivingbase)) {
                        arraylist.add((EntityPlayer) entitylivingbase);
                    }
                }
            } else if (arraylist.contains(entitylivingbase)) {
                arraylist.remove(entitylivingbase);
            }
        }

        this._x = 0.0F;
        this._y = 0.0F;
        this._z = 0.0F;
        iterator = arraylist.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            if (CombatUtils.canTarget(entityplayer, false)) {
                entityplayer.setAlwaysRenderNameTag(false);
                this._x = (float) (entityplayer.lastTickPosX + (entityplayer.posX - entityplayer.lastTickPosX) * (double) PrivateUtils.timer().renderPartialTicks - Game.Minecraft().getRenderManager().viewerPosX);
                this._y = (float) (entityplayer.lastTickPosY + (entityplayer.posY - entityplayer.lastTickPosY) * (double) PrivateUtils.timer().renderPartialTicks - Game.Minecraft().getRenderManager().viewerPosY);
                this._z = (float) (entityplayer.lastTickPosZ + (entityplayer.posZ - entityplayer.lastTickPosZ) * (double) PrivateUtils.timer().renderPartialTicks - Game.Minecraft().getRenderManager().viewerPosZ);
                this.renderNametag(entityplayer, this._x, this._y, this._z);
            }
        }

    }

    private String getHealth(EntityPlayer entityplayer) {
        DecimalFormat decimalformat = new DecimalFormat(NameTags.lIIIll[21]);

        return this.mode.equalsIgnoreCase(NameTags.lIIIll[22]) ? decimalformat.format((double) (entityplayer.getHealth() * 5.0F + entityplayer.getAbsorptionAmount() * 5.0F)) : decimalformat.format((double) (entityplayer.getHealth() / 2.0F + entityplayer.getAbsorptionAmount() / 2.0F));
    }

    private void drawNames(EntityPlayer entityplayer) {
        float f = 2.2F;
        float f1 = (float) this.getWidth(this.getPlayerName(entityplayer)) / 2.0F + 2.2F;
        float f2;

        f1 = f2 = (float) ((double) f1 + (double) (this.getWidth(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[23]).append(this.getHealth(entityplayer)))) / 2) + 2.5D);
        float f3 = -f1 - 2.2F;
        float f4 = (float) (this.getWidth(this.getPlayerName(entityplayer)) + 4);

        if (this.mode.equalsIgnoreCase(NameTags.lIIIll[24])) {
            RenderUtils.drawBorderedRect(f3, -3.0F, f1, 10.0F, 1.0F, (new Color(20, 20, 20, 180)).getRGB(), (new Color(10, 10, 10, 200)).getRGB());
        } else {
            RenderUtils.drawBorderedRect(f3 + 5.0F, -3.0F, f1, 10.0F, 1.0F, (new Color(20, 20, 20, 180)).getRGB(), (new Color(10, 10, 10, 200)).getRGB());
        }

        GlStateManager.disableDepth();
        if (this.mode.equalsIgnoreCase(NameTags.lIIIll[25])) {
            f4 += (float) (this.getWidth(this.getHealth(entityplayer)) + this.getWidth(NameTags.lIIIll[26]) - 1);
        } else {
            f4 += (float) (this.getWidth(this.getHealth(entityplayer)) + this.getWidth(NameTags.lIIIll[27]) - 1);
        }

        this.drawString(this.getPlayerName(entityplayer), f2 - f4, 0.0F, 16777215);
        if (entityplayer.getHealth() == 10.0F) {
            ;
        }

        int i;

        if (entityplayer.getHealth() > 10.0F) {
            i = RenderUtils.blend(new Color(-16711936), new Color(-256), (double) (1.0F / entityplayer.getHealth() / 2.0F * (entityplayer.getHealth() - 10.0F))).getRGB();
        } else {
            i = RenderUtils.blend(new Color(-256), new Color(-65536), (double) (0.1F * entityplayer.getHealth())).getRGB();
        }

        if (this.mode.equalsIgnoreCase(NameTags.lIIIll[28])) {
            this.drawString(String.valueOf((new StringBuilder()).append(String.valueOf(this.getHealth(entityplayer))).append(NameTags.lIIIll[29])), f2 - (float) this.getWidth(String.valueOf((new StringBuilder()).append(String.valueOf(this.getHealth(entityplayer))).append(NameTags.lIIIll[30]))), 0.0F, i);
        } else {
            this.drawString(this.getHealth(entityplayer), f2 - (float) this.getWidth(String.valueOf((new StringBuilder()).append(String.valueOf(this.getHealth(entityplayer))).append(NameTags.lIIIll[31]))), 0.0F, i);
        }

        GlStateManager.enableDepth();
    }

    private void drawString(String s, float f, float f1, int i) {
        Module.mc.fontRendererObj.drawStringWithShadow(s, f, f1, i);
    }

    private int getWidth(String s) {
        return Module.mc.fontRendererObj.getStringWidth(s);
    }

    private void startDrawing(float f, float f1, float f2, EntityPlayer entityplayer) {
        float f3 = Module.mc.gameSettings.thirdPersonView == 2 ? -1.0F : 1.0F;
        double d0 = (double) (this.getSize(entityplayer) / 10.0F * this.scale) * 1.5D;

        GL11.glPushMatrix();
        RenderUtils.startDrawing();
        GL11.glTranslatef(f, f1, f2);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-Module.mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(Module.mc.getRenderManager().playerViewX, f3, 0.0F, 0.0F);
        GL11.glScaled(-0.01666666753590107D * d0, -0.01666666753590107D * d0, 0.01666666753590107D * d0);
    }

    private void stopDrawing() {
        RenderUtils.stopDrawing();
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
    }

    private void renderNametag(EntityPlayer entityplayer, float f, float f1, float f2) {
        f1 += (float) (1.55D + (entityplayer.isSneaking() ? 0.5D : 0.7D));
        this.startDrawing(f, f1, f2, entityplayer);
        this.drawNames(entityplayer);
        GL11.glColor4d(1.0D, 1.0D, 1.0D, 1.0D);
        if (this.armor) {
            this.renderArmor(entityplayer);
        }

        this.stopDrawing();
    }

    private void renderArmor(EntityPlayer entityplayer) {
        ItemStack[] aitemstack = entityplayer.inventory.armorInventory;
        int i = aitemstack.length;
        int j = 0;
        ItemStack[] aitemstack1 = aitemstack;
        int k = aitemstack.length;

        for (int l = 0; l < k; ++l) {
            ItemStack itemstack = aitemstack1[l];

            if (itemstack != null) {
                j -= 8;
            }
        }

        if (entityplayer.getHeldItem() != null) {
            j -= 8;
            ItemStack itemstack1 = entityplayer.getHeldItem().copy();

            if (itemstack1.hasEffect() && (itemstack1.getItem() instanceof ItemTool || itemstack1.getItem() instanceof ItemArmor)) {
                itemstack1.stackSize = 1;
            }

            this.renderItemStack(itemstack1, j, -20);
            j += 16;
        }

        aitemstack = entityplayer.inventory.armorInventory;

        for (k = 3; k >= 0; --k) {
            ItemStack itemstack2 = aitemstack[k];

            if (itemstack2 != null) {
                this.renderItemStack(itemstack2, j, -20);
                j += 16;
            }
        }

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private String getPlayerName(EntityPlayer entityplayer) {
        boolean flag = Explicit.instance.sm.getSettingByName(this, NameTags.lIIIll[32]).getValBoolean();

        return String.valueOf((new StringBuilder()).append(flag ? String.valueOf((new StringBuilder()).append((new DecimalFormat(NameTags.lIIIll[33])).format((double) Game.Player().getDistanceToEntity(entityplayer))).append(NameTags.lIIIll[34])) : NameTags.lIIIll[35]).append(entityplayer.getDisplayName().getFormattedText()));
    }

    private float getSize(EntityPlayer entityplayer) {
        return Module.mc.thePlayer.getDistanceToEntity(entityplayer) / 4.0F <= 2.0F ? 2.0F : Module.mc.thePlayer.getDistanceToEntity(entityplayer) / 4.0F;
    }

    private void renderItemStack(ItemStack itemstack, int i, int j) {
        GlStateManager.pushMatrix();
        GlStateManager.depthMask(true);
        GlStateManager.clear(256);
        RenderHelper.enableStandardItemLighting();
        Module.mc.getRenderItem().zLevel = -150.0F;
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        Module.mc.getRenderItem().renderItemAndEffectIntoGUI(itemstack, i, j);
        Module.mc.getRenderItem().renderItemOverlays(Module.mc.fontRendererObj, itemstack, i, j);
        Module.mc.getRenderItem().zLevel = 0.0F;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableLighting();
        double d0 = 0.5D;

        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        GlStateManager.disableDepth();
        this.renderEnchantText(itemstack, i, j);
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0F, 2.0F, 2.0F);
        GlStateManager.popMatrix();
    }

    private void renderEnchantText(ItemStack itemstack, int i, int j) {
        int k = j - 24;

        if (itemstack.getEnchantmentTagList() != null && itemstack.getEnchantmentTagList().tagCount() >= 6) {
            Module.mc.fontRendererObj.drawStringWithShadow(NameTags.lIIIll[36], (float) (i * 2), (float) k, 16711680);
        } else {
            int l;
            int i1;
            int j1;
            int k1;

            if (itemstack.getItem() instanceof ItemArmor) {
                l = EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, itemstack);
                i1 = EnchantmentHelper.getEnchantmentLevel(Enchantment.projectileProtection.effectId, itemstack);
                j1 = EnchantmentHelper.getEnchantmentLevel(Enchantment.blastProtection.effectId, itemstack);
                k1 = EnchantmentHelper.getEnchantmentLevel(Enchantment.fireProtection.effectId, itemstack);
                int l1 = EnchantmentHelper.getEnchantmentLevel(Enchantment.thorns.effectId, itemstack);
                int i2 = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, itemstack);
                int j2 = itemstack.getMaxDamage() - itemstack.getItemDamage();

                if (this.dura) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(j2)), (float) (i * 2), (float) j, 16777215);
                }

                if (l > 0) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[37]).append(l)), (float) (i * 2), (float) k, -1);
                    k += 8;
                }

                if (i1 > 0) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[38]).append(i1)), (float) (i * 2), (float) k, -1);
                    k += 8;
                }

                if (j1 > 0) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[39]).append(j1)), (float) (i * 2), (float) k, -1);
                    k += 8;
                }

                if (k1 > 0) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[40]).append(k1)), (float) (i * 2), (float) k, -1);
                    k += 8;
                }

                if (l1 > 0) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[41]).append(l1)), (float) (i * 2), (float) k, -1);
                    k += 8;
                }

                if (i2 > 0) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[42]).append(i2)), (float) (i * 2), (float) k, -1);
                    k += 8;
                }
            }

            if (itemstack.getItem() instanceof ItemBow) {
                l = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemstack);
                i1 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itemstack);
                j1 = EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, itemstack);
                k1 = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, itemstack);
                if (l > 0) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[43]).append(l)), (float) (i * 2), (float) k, -1);
                    k += 8;
                }

                if (i1 > 0) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[44]).append(i1)), (float) (i * 2), (float) k, -1);
                    k += 8;
                }

                if (j1 > 0) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[45]).append(j1)), (float) (i * 2), (float) k, -1);
                    k += 8;
                }

                if (k1 > 0) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[46]).append(k1)), (float) (i * 2), (float) k, -1);
                    k += 8;
                }
            }

            if (itemstack.getItem() instanceof ItemSword) {
                l = EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, itemstack);
                i1 = EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, itemstack);
                j1 = EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, itemstack);
                k1 = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, itemstack);
                if (l > 0) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[47]).append(l)), (float) (i * 2), (float) k, -1);
                    k += 8;
                }

                if (i1 > 0) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[48]).append(i1)), (float) (i * 2), (float) k, -1);
                    k += 8;
                }

                if (j1 > 0) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[49]).append(j1)), (float) (i * 2), (float) k, -1);
                    k += 8;
                }

                if (k1 > 0) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[50]).append(k1)), (float) (i * 2), (float) k, -1);
                }
            }

            if (itemstack.getItem() instanceof ItemTool) {
                l = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, itemstack);
                i1 = EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, itemstack);
                j1 = EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, itemstack);
                k1 = EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, itemstack);
                if (i1 > 0) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[51]).append(i1)), (float) (i * 2), (float) k, -1);
                    k += 8;
                }

                if (j1 > 0) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[52]).append(j1)), (float) (i * 2), (float) k, -1);
                    k += 8;
                }

                if (k1 > 0) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[53]).append(k1)), (float) (i * 2), (float) k, -1);
                    k += 8;
                }

                if (l > 0) {
                    Module.mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder()).append(NameTags.lIIIll[54]).append(l)), (float) (i * 2), (float) k, -1);
                }
            }

            if (itemstack.getItem() == Items.golden_apple && itemstack.hasEffect()) {
                Module.mc.fontRendererObj.drawStringWithShadow(NameTags.lIIIll[55], (float) (i * 2), (float) k, -1);
            }

        }
    }

    static {
        lIIlllll();
        lIIllllI();
    }

    private static void lIIllllI() {
        lIIIll = new String[56];
        NameTags.lIIIll[0] = lllIlll(NameTags.llIIII[0], NameTags.llIIII[1]);
        NameTags.lIIIll[1] = llllIII(NameTags.llIIII[2], NameTags.llIIII[3]);
        NameTags.lIIIll[2] = llllIIl(NameTags.llIIII[4], NameTags.llIIII[5]);
        NameTags.lIIIll[3] = lllIlll(NameTags.llIIII[6], NameTags.llIIII[7]);
        NameTags.lIIIll[4] = lllIlll(NameTags.llIIII[8], NameTags.llIIII[9]);
        NameTags.lIIIll[5] = llllIIl(NameTags.llIIII[10], NameTags.llIIII[11]);
        NameTags.lIIIll[6] = lllIlll(NameTags.llIIII[12], NameTags.llIIII[13]);
        NameTags.lIIIll[7] = llllIIl(NameTags.llIIII[14], NameTags.llIIII[15]);
        NameTags.lIIIll[8] = llllIIl(NameTags.llIIII[16], NameTags.llIIII[17]);
        NameTags.lIIIll[9] = llllIII(NameTags.llIIII[18], NameTags.llIIII[19]);
        NameTags.lIIIll[10] = llllIII(NameTags.llIIII[20], NameTags.llIIII[21]);
        NameTags.lIIIll[11] = lllIlll(NameTags.llIIII[22], NameTags.llIIII[23]);
        NameTags.lIIIll[12] = llllIII(NameTags.llIIII[24], NameTags.llIIII[25]);
        NameTags.lIIIll[13] = lllIlll(NameTags.llIIII[26], NameTags.llIIII[27]);
        NameTags.lIIIll[14] = llllIIl(NameTags.llIIII[28], NameTags.llIIII[29]);
        NameTags.lIIIll[15] = llllIIl(NameTags.llIIII[30], NameTags.llIIII[31]);
        NameTags.lIIIll[16] = llllIII(NameTags.llIIII[32], NameTags.llIIII[33]);
        NameTags.lIIIll[17] = llllIII(NameTags.llIIII[34], NameTags.llIIII[35]);
        NameTags.lIIIll[18] = llllIII(NameTags.llIIII[36], NameTags.llIIII[37]);
        NameTags.lIIIll[19] = llllIIl(NameTags.llIIII[38], NameTags.llIIII[39]);
        NameTags.lIIIll[20] = llllIIl(NameTags.llIIII[40], NameTags.llIIII[41]);
        NameTags.lIIIll[21] = lllIlll(NameTags.llIIII[42], NameTags.llIIII[43]);
        NameTags.lIIIll[22] = lllIlll("0m/j2qzj2UHWr+0QgLBmMQ==", "QTjrb");
        NameTags.lIIIll[23] = lllIlll("pwMWrm7iV3w=", "bBReU");
        NameTags.lIIIll[24] = llllIIl("Ki40Nx8UPyczHw==", "zKFTz");
        NameTags.lIIIll[25] = lllIlll("gbNdu7I+vp9nWTBPQQ3ukw==", "vRiby");
        NameTags.lIIIll[26] = llllIIl("RUg=", "emcBX");
        NameTags.lIIIll[27] = llllIII("f1oHmprZRTI=", "RJetQ");
        NameTags.lIIIll[28] = lllIlll("mxXn7CvpCXM9xELb+zJjcA==", "HtcQG");
        NameTags.lIIIll[29] = llllIIl("dw==", "RZMYa");
        NameTags.lIIIll[30] = llllIII("QvNkO/3GLMg=", "IKKET");
        NameTags.lIIIll[31] = llllIIl("Zg==", "FCBRY");
        NameTags.lIIIll[32] = llllIIl("JSwCEQgPJhQ=", "aEqei");
        NameTags.lIIIll[33] = llllIIl("REtzcg==", "gePQo");
        NameTags.lIIIll[34] = llllIII("oJwXYkhSoss=", "xrUvL");
        NameTags.lIIIll[35] = llllIII("CNgfzcWX3jo=", "twlut");
        NameTags.lIIIll[36] = llllIII("X/5eJyd8XoM=", "RdhND");
        NameTags.lIIIll[37] = llllIIl("KBgLIA==", "XjdTb");
        NameTags.lIIIll[38] = llllIII("6lstHFRfH1M=", "YzgkT");
        NameTags.lIIIll[39] = llllIIl("LQI=", "OrSIk");
        NameTags.lIIIll[40] = lllIlll("G3weh1kleo8=", "YBJnG");
        NameTags.lIIIll[41] = llllIII("souQZGHK43k=", "KEcbJ");
        NameTags.lIIIll[42] = llllIII("EmFZq2eq1R8=", "EqeRx");
        NameTags.lIIIll[43] = lllIlll("ioplyssfSFc=", "juZNa");
        NameTags.lIIIll[44] = llllIII("qjL6HOtQTpk=", "YQCgU");
        NameTags.lIIIll[45] = lllIlll("3eOnQWuCIes=", "QCBhB");
        NameTags.lIIIll[46] = llllIII("OO6GPuM/h9k=", "uTDoH");
        NameTags.lIIIll[47] = llllIII("EDeomb/PFlQ=", "ObnnQ");
        NameTags.lIIIll[48] = llllIIl("Bxg=", "lzouX");
        NameTags.lIIIll[49] = lllIlll("ybhgneIw9eg=", "kUhyW");
        NameTags.lIIIll[50] = llllIIl("AwgL", "vfiJy");
        NameTags.lIIIll[51] = llllIIl("DjEJ", "kWowy");
        NameTags.lIIIll[52] = lllIlll("nkrlJ3pM3ik=", "Ovagk");
        NameTags.lIIIll[53] = llllIIl("OTo1Ag==", "JSYiX");
        NameTags.lIIIll[54] = llllIIl("Myw=", "FNOTf");
        NameTags.lIIIll[55] = lllIlll("lBQQu/JKCBg=", "RpfhY");
        NameTags.llIIII = null;
    }

    private static void lIIlllll() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        NameTags.llIIII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String llllIII(String s, String s1) {
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

    private static String lllIlll(String s, String s1) {
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

    private static String llllIIl(String s, String s1) {
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
