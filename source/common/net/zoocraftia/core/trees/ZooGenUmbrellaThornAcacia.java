// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.zoocraftia.core.trees;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import net.zoocraftia.core.ZoocraftiaBlocks;

// Referenced classes of package net.minecraft.src:
//            WorldGenerator, World, Block, BlockLeaves, 
//            BlockGrass

public class ZooGenUmbrellaThornAcacia extends WorldGenerator
{
	
	private StructureGenerator generator = new StructureGenerator();

    public ZooGenUmbrellaThornAcacia(boolean flag)
    {
    	super(flag);
    }

    public boolean generate(World world, Random random, int i, int j, int k)
    {
        int l = random.nextInt(3) + 4;
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
                        if(j3 != 0 && (j3 != ZoocraftiaBlocks.leaves.blockID && world.getBlockMetadata(i2, i1, l2) != 4))
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
        if(j1 != ZoocraftiaBlocks.savannahGround.blockID && j1 != Block.dirt.blockID || j >= 256 - l - 1)
        {
            return false;
        }
        world.setBlock(i, j - 1, k, Block.dirt.blockID);
        

        for(int l1 = 0; l1 < l; l1++)
        {
        	int leavesID = ZoocraftiaBlocks.leaves.blockID;;
        	int leavesMD = 4;
        	int h = j + l;
            int k2 = world.getBlockId(i, j + l1, k);
            if(k2 == 0 || (k2 == ZoocraftiaBlocks.leaves.blockID && world.getBlockMetadata(i, j + l1, k) == 4))
            {
                setBlockAndMetadata(world, i, j + l1, k, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i, h - 1, k + 1, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i, h - 1, k - 1, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i + 1, h - 1, k, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i - 1, h - 1, k, Block.wood.blockID, 0);

                setBlockAndMetadata(world, i, h, k + 2, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i, h, k - 2, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i + 2, h, k, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i - 2, h, k, Block.wood.blockID, 0);
                
                setBlockAndMetadata(world, i, h + 1, k + 3, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i, h + 1, k - 3, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i + 3, h + 1, k, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i - 3, h + 1, k, Block.wood.blockID, 0);
                
                setBlockAndMetadata(world, i, h + 2, k + 4, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i, h + 2, k - 4, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i + 4, h + 2, k, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i - 4, h + 2, k, Block.wood.blockID, 0);
                
                //generating diagonalno vejo
                setBlockAndMetadata(world, i - 1, h, k - 1, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i - 2, h + 1, k - 2, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i - 3, h + 2, k - 3, Block.wood.blockID, 0);
                
                setBlockAndMetadata(world, i + 1, h, k + 1, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i + 2, h + 1, k + 2, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i + 3, h + 2, k + 3, Block.wood.blockID, 0);
                
                setBlockAndMetadata(world, i - 1, h, k + 1, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i - 2, h + 1, k + 2, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i - 3, h + 2, k + 3, Block.wood.blockID, 0);
                
                setBlockAndMetadata(world, i + 1, h, k - 1, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i + 2, h + 1, k - 2, Block.wood.blockID, 0);
                setBlockAndMetadata(world, i + 3, h + 2, k - 3, Block.wood.blockID, 0);
                
                setBlockAndMetadata(world, i + 2, h + 3, k - 2, leavesID, leavesMD);
                setBlockAndMetadata(world, i + 2, h + 3, k - 3, leavesID, leavesMD);
                setBlockAndMetadata(world, i + 3, h + 3, k - 2, leavesID, leavesMD);
                setBlockAndMetadata(world, i + 3, h + 3, k - 3, leavesID, leavesMD);

                setBlockAndMetadata(world, i, h + 4, k + 2, leavesID, leavesMD);
                setBlockAndMetadata(world, i, h + 4, k - 2, leavesID, leavesMD);
                setBlockAndMetadata(world, i - 2, h + 4, k, leavesID, leavesMD);
                setBlockAndMetadata(world, i + 2, h + 4, k, leavesID, leavesMD);
                
                //generating base
                generator.generateFloor(world, h + 3, i - 5, k - 1, i + 5, k + 1, leavesID, leavesMD);
                generator.generateFloor(world, h + 3, i - 1, k - 5, i + 1, k + 5, leavesID, leavesMD);
                generator.generateFloor(world, h + 4, i - 2, k - 2, i + 2, k + 2, leavesID, leavesMD);
                
                //generating corners
                generator.generateFloor(world, h + 3, i - 2, k - 2, i - 3, k - 3, leavesID, leavesMD);
                generator.generateFloor(world, h + 3, i + 2, k + 2, i + 3, k + 3, leavesID, leavesMD);
                generator.generateFloor(world, h + 3, i + 2, k - 2, i + 3, k - 3, leavesID, leavesMD);
                generator.generateFloor(world, h + 3, i - 2, k + 2, i - 3, k + 3, leavesID, leavesMD);
                
                //generating
                setBlockAndMetadata(world, i + 4, h + 3, k + 2, leavesID, leavesMD);
                setBlockAndMetadata(world, i + 2, h + 3, k + 4, leavesID, leavesMD);
                setBlockAndMetadata(world, i - 4, h + 3, k - 2, leavesID, leavesMD);
                setBlockAndMetadata(world, i - 2, h + 3, k - 4, leavesID, leavesMD);
                setBlockAndMetadata(world, i + 4, h + 3, k - 2, leavesID, leavesMD);
                setBlockAndMetadata(world, i + 2, h + 3, k - 4, leavesID, leavesMD);
                setBlockAndMetadata(world, i - 4, h + 3, k + 2, leavesID, leavesMD);
                setBlockAndMetadata(world, i - 2, h + 3, k + 4, leavesID, leavesMD);
                
                //generating asymetrical side
                setBlockAndMetadata(world, i - 2, h + 3, k - 5, leavesID, leavesMD);
                setBlockAndMetadata(world, i - 3, h + 3, k - 4, leavesID, leavesMD);
                setBlockAndMetadata(world, i - 4, h + 3, k - 3, leavesID, leavesMD);
                setBlockAndMetadata(world, i - 5, h + 3, k - 2, leavesID, leavesMD);
                
                //generating ends
                setBlockAndMetadata(world, i + 3, h + 4, k + 1, leavesID, leavesMD);
                setBlockAndMetadata(world, i + 3, h + 4, k - 1, leavesID, leavesMD);
                setBlockAndMetadata(world, i + 3, h + 4, k, leavesID, leavesMD);
                setBlockAndMetadata(world, i + 4, h + 4, k, leavesID, leavesMD);
                
                setBlockAndMetadata(world, i + 1, h + 4, k + 3, leavesID, leavesMD);
                setBlockAndMetadata(world, i - 1, h + 4, k + 3, leavesID, leavesMD);
                setBlockAndMetadata(world, i, h + 4, k + 3, leavesID, leavesMD);
                setBlockAndMetadata(world, i, h + 4, k + 4, leavesID, leavesMD);
                
                setBlockAndMetadata(world, i - 3, h + 4, k, leavesID, leavesMD);
                setBlockAndMetadata(world, i - 3, h + 4, k + 1, leavesID, leavesMD);
                setBlockAndMetadata(world, i - 3, h + 4, k - 2, leavesID, leavesMD);
                setBlockAndMetadata(world, i - 3, h + 4, k - 1, leavesID, leavesMD);
                setBlockAndMetadata(world, i - 4, h + 4, k - 1, leavesID, leavesMD);
                setBlockAndMetadata(world, i - 4, h + 4, k, leavesID, leavesMD);
                
                setBlockAndMetadata(world, i, h + 4, k - 3, leavesID, leavesMD);
                setBlockAndMetadata(world, i + 1, h + 4, k - 3, leavesID, leavesMD);
                setBlockAndMetadata(world, i - 2, h + 4, k - 3, leavesID, leavesMD);
                setBlockAndMetadata(world, i - 1, h + 4, k - 3, leavesID, leavesMD);
                setBlockAndMetadata(world, i - 1, h + 4, k - 4, leavesID, leavesMD);
                setBlockAndMetadata(world, i, h + 4, k - 4, leavesID, leavesMD);
                
                generator.generateFloor(world, h + 3, i - 3, k - 1, i + 3, k + 1, 0);
                generator.generateFloor(world, h + 3, i - 1, k - 3, i + 1, k + 3, 0);
                
                setBlockAndMetadata(world, i - 2, h + 3, k - 2, 0, 0);
                setBlockAndMetadata(world, i + 2, h + 3, k + 2, 0, 0);
                setBlockAndMetadata(world, i - 2, h + 3, k + 2, 0, 0);
                setBlockAndMetadata(world, i + 2, h + 3, k - 2, 0, 0);
            }
        }

        return true;
    }
}
