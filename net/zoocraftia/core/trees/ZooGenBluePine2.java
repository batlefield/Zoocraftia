package net.zoocraftia.core.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.zoocraftia.core.ZoocraftiaBlocks;

public class ZooGenBluePine2 extends WorldGenerator
{
    public ZooGenBluePine2(boolean flag)
    {
    	super(flag);
    }

    public boolean generate(World world, Random random, int i, int j, int k)
    {
        int l = random.nextInt(4) + 6;
        int i1 = 1 + random.nextInt(2);
        int j1 = l - i1;
        int k1 = 2 + random.nextInt(2);
        boolean flag = true;
        if (j < 1 || j + l + 1 > 256)
        {
            return false;
        }
        for (int l1 = j; l1 <= j + 1 + l && flag; l1++)
        {
            int j2 = 1;
            if (l1 - j < i1)
            {
                j2 = 0;
            }
            else
            {
                j2 = k1;
            }
            for (int l2 = i - j2; l2 <= i + j2 && flag; l2++)
            {
                for (int j3 = k - j2; j3 <= k + j2 && flag; j3++)
                {
                    if (l1 >= 0 && l1 < 256)
                    {
                        int k3 = world.getBlockId(l2, l1, j3);
                        if (k3 != 0 && k3 != ZoocraftiaBlocks.leaves.blockID && world.getBlockMetadata(l2, l1, j3) != 0)
                        {
                            flag = false;
                        }
                    }
                    else
                    {
                        flag = false;
                    }
                }
            }
        }

        if (!flag)
        {
            return false;
        }
        int i2 = world.getBlockId(i, j - 1, k);
        if (i2 != ZoocraftiaBlocks.caniferousGround.blockID && i2 != Block.dirt.blockID || j >= 256 - l - 1)
        {
            return false;
        }
        world.setBlock(i, j - 1, k, Block.dirt.blockID);
        int k2 = random.nextInt(2);
        int i3 = 1;
        boolean flag1 = false;
        for (int l3 = 0; l3 <= j1; l3++)
        {
            int j4 = (j + l) - l3;
            for (int l4 = i - k2; l4 <= i + k2; l4++)
            {
                int j5 = l4 - i;
                for (int k5 = k - k2; k5 <= k + k2; k5++)
                {
                    int l5 = k5 - k;
                    if ((Math.abs(j5) != k2 || Math.abs(l5) != k2 || k2 <= 0) && !Block.opaqueCubeLookup[world.getBlockId(l4, j4, k5)])
                    {
                    	setBlockAndMetadata(world, l4, j4, k5, ZoocraftiaBlocks.leaves.blockID, 0);
                    }
                }
            }

            if (k2 >= i3)
            {
                k2 = ((flag1) ? 1 : 0);
                flag1 = true;
                if (++i3 > k1)
                {
                    i3 = k1;
                }
            }
            else
            {
                k2++;
            }
        }

        int i4 = random.nextInt(3);
        for (int k4 = 0; k4 < l - i4; k4++)
        {
            int i5 = world.getBlockId(i, j + k4, k);
            if (i5 == 0 || (i5 == ZoocraftiaBlocks.leaves.blockID && world.getBlockMetadata(i, j + k4, k) == 0))
            {
            	setBlockAndMetadata(world, i, j + k4, k, Block.wood.blockID, 1);
            }
        }

        return true;
    }
}
