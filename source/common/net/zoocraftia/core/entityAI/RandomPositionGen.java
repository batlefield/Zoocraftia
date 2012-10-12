package net.zoocraftia.core.entityAI;

import java.util.Random;

import net.minecraft.src.EntityCreature;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Vec3;
import net.zoocraftia.api.BaseEntity;

public class RandomPositionGen
{
    private static Vec3 staticVector = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);

    public static Vec3 generateRandomPosition(BaseEntity entity, int par1, int par2)
    {
        return findRandomTargetBlock(entity, par1, par2, (Vec3)null);
    }
    
    public static Vec3 findRandomTargetBlockTowards(BaseEntity baseEntity, int par1, int par2, Vec3 par3Vec3)
    {
        staticVector.xCoord = par3Vec3.xCoord - baseEntity.posX;
        staticVector.yCoord = par3Vec3.yCoord - baseEntity.posY;
        staticVector.zCoord = par3Vec3.zCoord - baseEntity.posZ;
        return findRandomTargetBlock(baseEntity, par1, par2, staticVector);
    }

    public static Vec3 findRandomTargetBlockAwayFrom(BaseEntity baseEntity, int par1, int par2, Vec3 par3Vec3)
    {
        staticVector.xCoord = baseEntity.posX - par3Vec3.xCoord;
        staticVector.yCoord = baseEntity.posY - par3Vec3.yCoord;
        staticVector.zCoord = baseEntity.posZ - par3Vec3.zCoord;
        return findRandomTargetBlock(baseEntity, par1, par2, staticVector);
    }

    private static Vec3 findRandomTargetBlock(BaseEntity baseEntity, int par1, int par2, Vec3 par3Vec3D)
    {
        Random var4 = baseEntity.getRNG();
        boolean var5 = false;
        int var6 = 0;
        int var7 = 0;
        int var8 = 0;
        float var9 = -99999.0F;
        boolean var10;

        if (baseEntity.hasHome())
        {
            double var11 = baseEntity.getHomePosition().getDistanceSquared(MathHelper.floor_double(baseEntity.posX), MathHelper.floor_double(baseEntity.posY), MathHelper.floor_double(baseEntity.posZ)) + 4.0D;
            var10 = var11 < (double)(baseEntity.getMaximumHomeDistance() + (float)par1);
        }
        else
        {
            var10 = false;
        }

        for (int var16 = 0; var16 < 10; ++var16)
        {
            int var12 = var4.nextInt(2 * par1) - par1;
            int var13 = var4.nextInt(2 * par2) - par2;
            int var14 = var4.nextInt(2 * par1) - par1;

            if (par3Vec3D == null || (double)var12 * par3Vec3D.xCoord + (double)var14 * par3Vec3D.zCoord >= 0.0D)
            {
                var12 += MathHelper.floor_double(baseEntity.posX);
                var13 += MathHelper.floor_double(baseEntity.posY);
                var14 += MathHelper.floor_double(baseEntity.posZ);

                if (!var10 || baseEntity.isWithinHomeDistance(var12, var13, var14))
                {
                    float var15 = baseEntity.getBlockPathWeight(var12, var13, var14);

                    if (var15 > var9)
                    {
                        var9 = var15;
                        var6 = var12;
                        var7 = var13;
                        var8 = var14;
                        var5 = true;
                    }
                }
            }
        }

        if (var5)
        {
            return Vec3.getVec3Pool().getVecFromPool((double)var6, (double)var7, (double)var8);
        }
        else
        {
            return null;
        }
    }
}
