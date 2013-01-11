package net.zoocraftia.core;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class BlockFence extends net.minecraft.block.BlockFence{

	public BlockFence(int par1, int par2, Material par3Material) {
		super(par1, par2, par3Material);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	public String getTextureFile()
    {
		if(this.blockID == ZoocraftiaCore.getOrCreateBlockID("BrownFence", 3017))
		{
    		return "/zoocraftia/core/blocks.png";
		}else{
			return super.getTextureFile();
		}
    }
	
	public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
    {
    	return true;
    }

}
