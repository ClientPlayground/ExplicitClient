package me.explicit.utils;

import java.awt.Color;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import org.lwjgl.opengl.GL11;

public class RenderUtils {

    private static final String[] llIll;
    private static String[] lllII;

    public static void renderBlockPos(BlockPos blockpos, int i) {
        if (blockpos != null) {
            double d0 = (double) blockpos.getX() - Game.Minecraft().getRenderManager().viewerPosX;
            double d1 = (double) blockpos.getY() - Game.Minecraft().getRenderManager().viewerPosY;
            double d2 = (double) blockpos.getZ() - Game.Minecraft().getRenderManager().viewerPosZ;

            GL11.glBlendFunc(770, 771);
            GL11.glEnable(3042);
            GL11.glLineWidth(2.0F);
            GL11.glDisable(3553);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            float f = (float) (i >> 24 & 255) / 255.0F;
            float f1 = (float) (i >> 16 & 255) / 255.0F;
            float f2 = (float) (i >> 8 & 255) / 255.0F;
            float f3 = (float) (i & 255) / 255.0F;

            GL11.glColor4d((double) f1, (double) f2, (double) f3, (double) f);
            RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(d0, d1, d2, d0 + 1.0D, d1 + 1.0D, d2 + 1.0D));
            dbb(new AxisAlignedBB(d0, d1, d2, d0 + 1.0D, d1 + 1.0D, d2 + 1.0D), f1, f2, f3);
            GL11.glEnable(3553);
            GL11.glEnable(2929);
            GL11.glDepthMask(true);
            GL11.glDisable(3042);
        }
    }

    public static void renderEntity(Entity entity, int i, int j) {
        if (entity != null) {
            double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) PrivateUtils.timer().renderPartialTicks - Minecraft.getMinecraft().getRenderManager().viewerPosX;
            double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) PrivateUtils.timer().renderPartialTicks - Minecraft.getMinecraft().getRenderManager().viewerPosY;
            double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) PrivateUtils.timer().renderPartialTicks - Minecraft.getMinecraft().getRenderManager().viewerPosZ;

            if (entity instanceof EntityPlayer && ((EntityPlayer) entity).hurtTime != 0) {
                i = Color.RED.getRGB();
            }

            float f;
            float f1;
            float f2;
            float f3;

            if (j == 1) {
                GlStateManager.pushMatrix();
                GL11.glBlendFunc(770, 771);
                GL11.glEnable(3042);
                GL11.glDisable(3553);
                GL11.glDisable(2929);
                GL11.glDepthMask(false);
                GL11.glLineWidth(2.0F);
                f = (float) (i >> 24 & 255) / 255.0F;
                f1 = (float) (i >> 16 & 255) / 255.0F;
                f2 = (float) (i >> 8 & 255) / 255.0F;
                f3 = (float) (i & 255) / 255.0F;
                GL11.glColor4f(f1, f2, f3, f);
                RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05D - entity.posX + (entity.posX - Minecraft.getMinecraft().getRenderManager().viewerPosX), entity.getEntityBoundingBox().minY - entity.posY + (entity.posY - Minecraft.getMinecraft().getRenderManager().viewerPosY), entity.getEntityBoundingBox().minZ - 0.05D - entity.posZ + (entity.posZ - Minecraft.getMinecraft().getRenderManager().viewerPosZ), entity.getEntityBoundingBox().maxX + 0.05D - entity.posX + (entity.posX - Minecraft.getMinecraft().getRenderManager().viewerPosX), entity.getEntityBoundingBox().maxY + 0.1D - entity.posY + (entity.posY - Minecraft.getMinecraft().getRenderManager().viewerPosY), entity.getEntityBoundingBox().maxZ + 0.05D - entity.posZ + (entity.posZ - Minecraft.getMinecraft().getRenderManager().viewerPosZ)));
                dbb(new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05D - entity.posX + (entity.posX - Minecraft.getMinecraft().getRenderManager().viewerPosX), entity.getEntityBoundingBox().minY - entity.posY + (entity.posY - Minecraft.getMinecraft().getRenderManager().viewerPosY), entity.getEntityBoundingBox().minZ - 0.05D - entity.posZ + (entity.posZ - Minecraft.getMinecraft().getRenderManager().viewerPosZ), entity.getEntityBoundingBox().maxX + 0.05D - entity.posX + (entity.posX - Minecraft.getMinecraft().getRenderManager().viewerPosX), entity.getEntityBoundingBox().maxY + 0.1D - entity.posY + (entity.posY - Minecraft.getMinecraft().getRenderManager().viewerPosY), entity.getEntityBoundingBox().maxZ + 0.05D - entity.posZ + (entity.posZ - Minecraft.getMinecraft().getRenderManager().viewerPosZ)), f1, f2, f3);
                GL11.glEnable(3553);
                GL11.glEnable(2929);
                GL11.glDepthMask(true);
                GL11.glDisable(3042);
                GlStateManager.popMatrix();
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            } else if (j == 2) {
                GL11.glBlendFunc(770, 771);
                GL11.glEnable(3042);
                GL11.glLineWidth(2.0F);
                GL11.glDisable(3553);
                GL11.glDisable(2929);
                GL11.glDepthMask(false);
                f = (float) (i >> 24 & 255) / 255.0F;
                f1 = (float) (i >> 16 & 255) / 255.0F;
                f2 = (float) (i >> 8 & 255) / 255.0F;
                f3 = (float) (i & 255) / 255.0F;
                GL11.glColor4d((double) f1, (double) f2, (double) f3, (double) f);
                RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05D - entity.posX + (entity.posX - Minecraft.getMinecraft().getRenderManager().viewerPosX), entity.getEntityBoundingBox().minY - entity.posY + (entity.posY - Minecraft.getMinecraft().getRenderManager().viewerPosY), entity.getEntityBoundingBox().minZ - 0.05D - entity.posZ + (entity.posZ - Minecraft.getMinecraft().getRenderManager().viewerPosZ), entity.getEntityBoundingBox().maxX + 0.05D - entity.posX + (entity.posX - Minecraft.getMinecraft().getRenderManager().viewerPosX), entity.getEntityBoundingBox().maxY + 0.1D - entity.posY + (entity.posY - Minecraft.getMinecraft().getRenderManager().viewerPosY), entity.getEntityBoundingBox().maxZ + 0.05D - entity.posZ + (entity.posZ - Minecraft.getMinecraft().getRenderManager().viewerPosZ)));
                GL11.glEnable(3553);
                GL11.glEnable(2929);
                GL11.glDepthMask(true);
                GL11.glDisable(3042);
            } else if (j == 3) {
                GL11.glPushMatrix();
                GL11.glTranslated(d0, d1 - 0.2D, d2);
                GL11.glScalef(0.03F, 0.03F, 0.03F);
                GL11.glRotated((double) (-Minecraft.getMinecraft().getRenderManager().playerViewY), 0.0D, 1.0D, 0.0D);
                GlStateManager.disableDepth();
                Gui.drawRect(-20, -1, -26, 15, Color.black.getRGB());
                Gui.drawRect(-21, 0, -25, 14, i);
                Gui.drawRect(-20, 61, -26, 75, Color.black.getRGB());
                Gui.drawRect(-21, 62, -25, 74, i);
                Gui.drawRect(20, 61, 26, 75, Color.black.getRGB());
                Gui.drawRect(21, 62, 25, 74, i);
                Gui.drawRect(20, -1, 26, 15, Color.black.getRGB());
                Gui.drawRect(21, 0, 25, 14, i);
                Gui.drawRect(-20, -1, -9, 5, Color.black.getRGB());
                Gui.drawRect(-21, 0, -10, 4, i);
                Gui.drawRect(20, -1, 9, 5, Color.black.getRGB());
                Gui.drawRect(21, 0, 10, 4, i);
                Gui.drawRect(20, 70, 9, 75, Color.black.getRGB());
                Gui.drawRect(21, 71, 10, 74, i);
                Gui.drawRect(-20, 70, -9, 75, Color.black.getRGB());
                Gui.drawRect(-21, 71, -10, 74, i);
                GlStateManager.enableDepth();
                GL11.glPopMatrix();
            }

        }
    }

    public static void dbb(AxisAlignedBB axisalignedbb, float f, float f1, float f2) {
        float f3 = 0.25F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();

        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        tessellator.draw();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        tessellator.draw();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        tessellator.draw();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        tessellator.draw();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        tessellator.draw();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        worldrenderer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(f, f1, f2, 0.25F).endVertex();
        tessellator.draw();
    }

    public static void stopDrawing() {
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
    }

    public static void startDrawing() {
        GL11.glEnable(3042);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glDisable(3553);
        GL11.glDisable(2929);

        try {
            Class oclass = Minecraft.getMinecraft().entityRenderer.getClass();
            Method method = oclass.getDeclaredMethod(RenderUtils.llIll[0], new Class[] { Float.TYPE, Integer.TYPE});

            method.setAccessible(true);
            method.invoke(Minecraft.getMinecraft().entityRenderer, new Object[] { Float.valueOf(PrivateUtils.timer().renderPartialTicks), Integer.valueOf(0)});
        } catch (Exception exception) {
            try {
                Class oclass1 = Minecraft.getMinecraft().entityRenderer.getClass();
                Method method1 = oclass1.getDeclaredMethod(RenderUtils.llIll[1], new Class[] { Float.TYPE, Integer.TYPE});

                method1.setAccessible(true);
                method1.invoke(Minecraft.getMinecraft().entityRenderer, new Object[] { Float.valueOf(PrivateUtils.timer().renderPartialTicks), Integer.valueOf(0)});
            } catch (Exception exception1) {
                ;
            }
        }

    }

    public static void blockESPBox(BlockPos blockpos, float f, float f1, float f2, float f3) {
        double d0 = (double) blockpos.getX() - Game.Minecraft().getRenderManager().viewerPosX;
        double d1 = (double) blockpos.getY() - Game.Minecraft().getRenderManager().viewerPosY;
        double d2 = (double) blockpos.getZ() - Game.Minecraft().getRenderManager().viewerPosZ;

        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(f3);
        GL11.glColor4d(0.0D, 1.0D, 0.0D, 0.15000000596046448D);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d((double) f, (double) f1, (double) f2, 1000.0D);
        RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(d0, d1, d2, d0 + 1.0D, d1 + 1.0D, d2 + 1.0D));
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }

    public static void drawBorderedRect(float f, float f1, float f2, float f3, float f4, int i, int j) {
        drawRect((double) f, (double) f1, (double) f2, (double) f3, j);
        float f5 = (float) (i >> 24 & 255) / 255.0F;
        float f6 = (float) (i >> 16 & 255) / 255.0F;
        float f7 = (float) (i >> 8 & 255) / 255.0F;
        float f8 = (float) (i & 255) / 255.0F;

        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glLineWidth(f4);
        GL11.glBegin(1);
        GL11.glVertex2d((double) f, (double) f1);
        GL11.glVertex2d((double) f, (double) f3);
        GL11.glVertex2d((double) f2, (double) f3);
        GL11.glVertex2d((double) f2, (double) f1);
        GL11.glVertex2d((double) f, (double) f1);
        GL11.glVertex2d((double) f2, (double) f1);
        GL11.glVertex2d((double) f, (double) f3);
        GL11.glVertex2d((double) f2, (double) f3);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }

    public static void drawRect(double d0, double d1, double d2, double d3, int i) {
        double d4;

        if (d0 < d2) {
            d4 = d0;
            d0 = d2;
            d2 = d4;
        }

        if (d1 < d3) {
            d4 = d1;
            d1 = d3;
            d3 = d4;
        }

        float f = (float) (i >> 24 & 255) / 255.0F;
        float f1 = (float) (i >> 16 & 255) / 255.0F;
        float f2 = (float) (i >> 8 & 255) / 255.0F;
        float f3 = (float) (i & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();

        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f1, f2, f3, f);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(d0, d3, 0.0D).endVertex();
        worldrenderer.pos(d2, d3, 0.0D).endVertex();
        worldrenderer.pos(d2, d1, 0.0D).endVertex();
        worldrenderer.pos(d0, d1, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static Color blend(Color color, Color color1, double d0) {
        float f = (float) d0;
        float f1 = 1.0F - f;
        float[] afloat = new float[3];
        float[] afloat1 = new float[3];

        color.getColorComponents(afloat);
        color1.getColorComponents(afloat1);
        Color color2 = new Color(afloat[0] * f + afloat1[0] * f1, afloat[1] * f + afloat1[1] * f1, afloat[2] * f + afloat1[2] * f1);

        return color2;
    }

    public static void drawSolidBox(AxisAlignedBB axisalignedbb) {
        GL11.glBegin(10);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glEnd();
    }

    public static void drawOutlinedBox() {
        drawOutlinedBox(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D));
    }

    public static void drawSolidBox() {
        drawSolidBox(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D));
    }

    public static void drawOutlinedBox(AxisAlignedBB axisalignedbb) {
        GL11.glBegin(1);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glEnd();
    }

    static {
        llIIIII();
        lIlllll();
    }

    private static void lIlllll() {
        llIll = new String[2];
        RenderUtils.llIll[0] = lIlllIl(RenderUtils.lllII[0], RenderUtils.lllII[1]);
        RenderUtils.llIll[1] = lIllllI(RenderUtils.lllII[2], RenderUtils.lllII[3]);
        RenderUtils.lllII = null;
    }

    private static void llIIIII() {
        String s = (new Exception()).getStackTrace()[0].getFileName();

        RenderUtils.lllII = s.substring(s.indexOf("ä") + 1, s.lastIndexOf("ü")).split("ö");
    }

    private static String lIlllIl(String s, String s1) {
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

    private static String lIllllI(String s, String s1) {
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
