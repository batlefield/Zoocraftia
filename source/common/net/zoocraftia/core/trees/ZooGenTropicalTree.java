package net.zoocraftia.core.trees;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import net.zoocraftia.core.ZoocraftiaBlocks;

public class ZooGenTropicalTree extends WorldGenerator
{

    public ZooGenTropicalTree(boolean flag)
    {
    	super(flag);
    }

    public boolean generate(World world, Random random, int i, int j, int k)
    {
        int l = random.nextInt(6) + 4;
        boolean flag = true;
        if(j < 1 || j + l + 4 > 256)
        {
            return false;
        }
        for(int i1 = j; i1 <= j + 1 + l; i1++)
        {
            byte byte0 = 1;
            if(i1 == j)
            {
                byte0 = 0;
            }
            if(i1 >= (j + 1 + l) - 2)
            {
                byte0 = 2;
            }
            for(int i2 = i - byte0; i2 <= i + byte0 && flag; i2++)
            {
                for(int l2 = k - byte0; l2 <= k + byte0 && flag; l2++)
                {
                    if(i1 >= 0 && i1 < 256)
                    {
                        int j3 = world.getBlockId(i2, i1, l2);
                        if(j3 != 0 && j3 != Block.leaves.blockID)
                        {
                            flag = false;
                        }
                    } else
                    {
                        flag = false;
                    }
                }

            }

        }

        if(!flag)
        {
            return false;
        }
        int j1 = world.getBlockId(i, j - 1, k);
        if(j1 != ZoocraftiaBlocks.tropicGround.blockID && j1 != Block.dirt.blockID || j >= 256 - l - 1)
        {
            return false;
        }
        

        for(int l1 = 0; l1 < l; l1++)
        {
        	int h = j + l;
            int k2 = world.getBlockId(i, j + l1, k);
            if(k2 == 0 || k2 == Block.leaves.blockID)
            {
                setBlockAndMetadata(world, i, j + l1, k, Block.fence.blockID, 0);
                
                setBlockAndMetadata(world, i, h , k, Block.leaves.blockID, 0);
                
                for(int i10 = 1; i10 < l/2.5; i10++)
                {
                	setBlockAndMetadata(world, i + i10, h-1 , k + i10, Block.leaves.blockID, 0);
                	setBlockAndMetadata(world, i - i10, h-1 , k + i10, Block.leaves.blockID, 0);
                	setBlockAndMetadata(world, i + i10, h-1 , k - i10, Block.leaves.blockID, 0);
                	setBlockAndMetadata(world, i - i10, h-1 , k - i10, Block.leaves.blockID, 0);
                }

            }
        }

        return true;
    }
}
