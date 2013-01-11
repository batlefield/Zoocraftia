package net.zoocraftia.core;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class GroundBlock extends DefaultBlock{

	public GroundBlock(int par1, Material par3Material) {
		super(par1, par3Material);
		setCreativeTab(CreativeTabs.tabBlock);
		setTickRandomly(true);
	}
	
	public int getBlockTextureFromSideAndMetadata(int side, int md)
	{
		if(this.blockID == ZoocraftiaBlocks.caniferousGround.blockID)
		{
			//bottom
			if(side == 0)
			{
				return 5;
			}else if(side == 1)
			{
				return 3;
			}else{
				return 4;
			}
		}else if(this.blockID == ZoocraftiaBlocks.deciduousGround.blockID)
		{
			//bottom
			if(side == 0)
			{
				return 24;
			}else if(side == 1)
			{
				return 26;
			}else{
				return 27;
			}
		}else if(this.blockID == ZoocraftiaBlocks.savannahGround.blockID)
		{
			//bottom
			if(side == 0)
			{
				return 2;
			}else if(side == 1)
			{
				return 0;
			}else{
				return 1;
			}
		}else if(this.blockID == ZoocraftiaBlocks.tropicGround.blockID)
		{
			//bottom
			if(side == 0)
			{
				return 24;
			}else if(side == 1)
			{
				return 22;
			}else{
				return 23;
			}
		}else if(this.blockID == ZoocraftiaBlocks.mesa.blockID)
		{
			return 25;
		}else{
			return 0;
		}
	}
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
        	if(this.blockID != ZoocraftiaBlocks.mesa.blockID)
        	{
	            if (par1World.getBlockLightValue(par2, par3 + 1, par4) < 4 && Block.lightOpacity[par1World.getBlockId(par2, par3 + 1, par4)] > 2)
	            {
	                par1World.setBlockWithNotify(par2, par3, par4, Block.dirt.blockID);
	            }
	            else if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
	            {
	                for (int var6 = 0; var6 < 4; ++var6)
	                {
	                    int var7 = par2 + par5Random.nextInt(3) - 1;
	                    int var8 = par3 + par5Random.nextInt(5) - 3;
	                    int var9 = par4 + par5Random.nextInt(3) - 1;
	                    int var10 = par1World.getBlockId(var7, var8 + 1, var9);
	
	                    if (par1World.getBlockId(var7, var8, var9) == Block.dirt.blockID && par1World.getBlockLightValue(var7, var8 + 1, var9) >= 4 && Block.lightOpacity[var10] <= 2)
	                    {
	                        par1World.setBlockWithNotify(var7, var8, var9, this.blockID);
	                    }
	                }
	            }
        	}
        }
    }

}
