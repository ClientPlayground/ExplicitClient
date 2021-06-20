package me.explicit.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class RotationUtils {

    public static float getDistanceBetweenAngles(float f, float f1) {
        float f2;

        for (f2 = f - f1; f2 < -180.0F; f2 += 360.0F) {
            ;
        }

        while (f2 > 180.0F) {
            f2 -= 360.0F;
        }

        return f2;
    }

    public static float[] getRotations(Entity entity) {
        if (entity == null) {
            return null;
        } else {
            double d0 = entity.posX - Game.Player().posX;
            double d1 = entity.posZ - Game.Player().posZ;
            EntityLivingBase entitylivingbase = (EntityLivingBase) entity;
            double d2 = entitylivingbase.posY + ((double) entitylivingbase.getEyeHeight() - 0.4D) - (Game.Player().posY + (double) Game.Player().getEyeHeight());
            double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d1 * d1);
            float f = (float) (Math.atan2(d1, d0) * 180.0D / 3.141592653589793D) - 90.0F;
            float f1 = (float) (-(Math.atan2(d2, d3) * 180.0D / 3.141592653589793D));

            return new float[] { f, f1};
        }
    }

    public static float getFovDistanceToEntity(Entity entity) {
        float f;

        for (f = Game.Player().rotationYaw; f > 360.0F; f -= 360.0F) {
            ;
        }

        while (f < -360.0F) {
            f += 360.0F;
        }

        float f1 = getDistanceBetweenAngles(f, getRotations(entity)[0]);

        return f1;
    }

    public static void limitAngleChange(float[] afloat, float[] afloat1, float f) {
        float f1 = getAngleDifference(afloat1[0], afloat[0]);
        float f2 = getAngleDifference(afloat1[1], afloat[1]);

        Game.Player().rotationYaw = afloat[0] + (f1 > f ? f : Math.max(f1, -f));
        Game.Player().rotationPitch = afloat[1] + (f2 > f ? f : Math.max(f2, -f));
    }

    public static float[] toRotation(Vec3 vec3, boolean flag) {
        Vec3 vec31 = new Vec3(Game.Player().posX, Game.Player().getEntityBoundingBox().minY + (double) Game.Player().getEyeHeight(), Game.Player().posZ);

        if (flag) {
            vec31.addVector(Game.Player().motionX, Game.Player().motionY, Game.Player().motionZ);
        }

        double d0 = vec3.xCoord - vec31.xCoord;
        double d1 = vec3.yCoord - vec31.yCoord;
        double d2 = vec3.zCoord - vec31.zCoord;
        float f = MathHelper.wrapAngleTo180_float((float) Math.toDegrees(Math.atan2(d2, d0)) - 90.0F);
        float f1 = MathHelper.wrapAngleTo180_float((float) (-Math.toDegrees(Math.atan2(d1, Math.sqrt(d0 * d0 + d2 * d2)))));

        return new float[] { f, f1};
    }

    private static float getAngleDifference(float f, float f1) {
        return ((f - f1) % 360.0F + 540.0F) % 360.0F - 180.0F;
    }

    public static boolean canEntityBeSeen(Entity entity) {
        Vec3 vec3 = new Vec3(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY + (double) Minecraft.getMinecraft().thePlayer.getEyeHeight(), Minecraft.getMinecraft().thePlayer.posZ);
        AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox();
        Vec3 vec31 = new Vec3(entity.posX, entity.posY + (double) (entity.getEyeHeight() / 1.32F), entity.posZ);
        double d0 = entity.posX - 0.25D;
        double d1 = entity.posX + 0.25D;
        double d2 = entity.posY;
        double d3 = entity.posY + Math.abs(entity.posY - axisalignedbb.maxY);
        double d4 = entity.posZ - 0.25D;
        double d5 = entity.posZ + 0.25D;
        boolean flag = Minecraft.getMinecraft().theWorld.rayTraceBlocks(vec3, vec31) == null;

        if (flag) {
            return true;
        } else {
            vec31 = new Vec3(d1, d2, d4);
            flag = Minecraft.getMinecraft().theWorld.rayTraceBlocks(vec3, vec31) == null;
            if (flag) {
                return true;
            } else {
                vec31 = new Vec3(d0, d2, d4);
                flag = Minecraft.getMinecraft().theWorld.rayTraceBlocks(vec3, vec31) == null;
                if (flag) {
                    return true;
                } else {
                    vec31 = new Vec3(d0, d2, d5);
                    flag = Minecraft.getMinecraft().theWorld.rayTraceBlocks(vec3, vec31) == null;
                    if (flag) {
                        return true;
                    } else {
                        vec31 = new Vec3(d1, d2, d5);
                        flag = Minecraft.getMinecraft().theWorld.rayTraceBlocks(vec3, vec31) == null;
                        if (flag) {
                            return true;
                        } else {
                            vec31 = new Vec3(d1, d3, d4);
                            flag = Minecraft.getMinecraft().theWorld.rayTraceBlocks(vec3, vec31) == null;
                            if (flag) {
                                return true;
                            } else {
                                vec31 = new Vec3(d0, d3, d4);
                                flag = Minecraft.getMinecraft().theWorld.rayTraceBlocks(vec3, vec31) == null;
                                if (flag) {
                                    return true;
                                } else {
                                    vec31 = new Vec3(d0, d3, d5 - 0.1D);
                                    flag = Minecraft.getMinecraft().theWorld.rayTraceBlocks(vec3, vec31) == null;
                                    if (flag) {
                                        return true;
                                    } else {
                                        vec31 = new Vec3(d1, d3, d5);
                                        flag = Minecraft.getMinecraft().theWorld.rayTraceBlocks(vec3, vec31) == null;
                                        return flag;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static float getYawChange(float f, double d0, double d1) {
        double d2 = d0 - Minecraft.getMinecraft().thePlayer.posX;
        double d3 = d1 - Minecraft.getMinecraft().thePlayer.posZ;
        double d4 = 0.0D;

        if (d3 < 0.0D && d2 < 0.0D) {
            if (d2 != 0.0D) {
                d4 = 90.0D + Math.toDegrees(Math.atan(d3 / d2));
            }
        } else if (d3 < 0.0D && d2 > 0.0D) {
            if (d2 != 0.0D) {
                d4 = -90.0D + Math.toDegrees(Math.atan(d3 / d2));
            }
        } else if (d3 != 0.0D) {
            d4 = Math.toDegrees(-Math.atan(d2 / d3));
        }

        return MathHelper.wrapAngleTo180_float(-(f - (float) d4));
    }

    public static float roundTo360(float f) {
        float f1;

        for (f1 = f; f1 > 360.0F; f1 -= 360.0F) {
            ;
        }

        return f1;
    }

    public static float[] getRotationsBlock(BlockPos blockpos, EnumFacing enumfacing) {
        double d0 = (double) blockpos.getX() + 0.5D - Game.Minecraft().thePlayer.posX + (double) enumfacing.getFrontOffsetX() / 2.0D;
        double d1 = (double) blockpos.getZ() + 0.5D - Game.Minecraft().thePlayer.posZ + (double) enumfacing.getFrontOffsetZ() / 2.0D;
        double d2 = (double) blockpos.getY() + 0.5D;
        double d3 = Game.Minecraft().thePlayer.posY + (double) Game.Minecraft().thePlayer.getEyeHeight() - d2;
        double d4 = (double) MathHelper.sqrt_double(d0 * d0 + d1 * d1);
        float f = (float) (Math.atan2(d1, d0) * 180.0D / 3.141592653589793D) - 90.0F;
        float f1 = (float) (Math.atan2(d3, d4) * 180.0D / 3.141592653589793D);

        if (f < 0.0F) {
            f += 360.0F;
        }

        return new float[] { f, f1};
    }
}
