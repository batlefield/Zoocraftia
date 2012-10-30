package net.zoocraftia.core;

import java.util.List;
import java.util.Random;

import net.minecraft.src.BlockFlower;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import net.zoocraftia.core.trees.ZooGenBluePine2;
import net.zoocraftia.core.trees.ZooGenDeciTree;
import net.zoocraftia.core.trees.ZooGenUmbrellaThornAcacia;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class BlockSapling extends BlockFlower
{
	public static final String[] leafTypes = new String[] {"Acacia", "BluePine", "Deciduous"};

    protected BlockSapling(int par1)
    {
        super(par1, 15);
        float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
        this.setCreativeTab(CreativeTabs.tabDecorations);
       this.setRequiresSelfNotify();
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            super.updateTick(par1World, par2, par3, par4, par5Random);

            if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0)
            {
                int var6 = par1World.getBlockMetadata(par2, par3, par4);

                if ((var6 & 8) == 0)
                {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 | 8);
                }
                else
                {
                    this.growTree(par1World, par2, par3, par4, par5Random);
                }
            }
        }
    }

    public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        par2 &= 3;
        if(par2 == 0)
        {
        	return 16;
        }else if(par2 == 1)
        {
        	return 17;
        }else if(par2 == 2)
        {
        	return 18;
        }else{
        	return 16;
        }
    }

    /**
     * Attempts to grow a sapling into a tree
     */
    public void growTree(World world, int i, int j, int k, Random random)
    {
    	if(!world.isRemote)
    	{
	    	if(world.getBlockId(i, j, k) == ZoocraftiaBlocks.sapling.blockID)
	    	{
		    	int l = world.getBlockMetadata(i, j, k) & 3;  
		        Object obj = null;
		        if (l == 1)
		        {
		        	world.setBlock(i, j, k, 0);
		            obj = new ZooGenBluePine2(true);
		        }
		        else if (l == 2)
		        {
		        	world.setBlock(i, j, k, 0);
		            obj = new ZooGenDeciTree(true);
		        }
		        else if (l == 0)
		        {
		        	world.setBlock(i, j, k, 0);
		            obj = new ZooGenUmbrellaThornAcacia(true);
		        }else{
		        	return;
		        }
		        if (!((WorldGenerator) (obj)).generate(world, random, i, j, k))
		        {
		            world.setBlockAndMetadata(i, j, k, blockID, l);
		        }
	    	}else{
	    		return;
	    	}
    	}
    }

    public int damageDropped(int par1)
    {
        return par1 & 3;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
    }
    
    public String getTextureFile()
    {
    	return "/zoocraftia/core/blocks.png";
    }
    
    public boolean canThisPlantGrowOnThisBlockID(int i)
    {
		return i == ZoocraftiaBlocks.savannahGround.blockID || i == ZoocraftiaBlocks.caniferousGround.blockID || i == ZoocraftiaBlocks.deciduousGround.blockID;
    }
}
