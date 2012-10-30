package net.zoocraftia.core;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

public class BlockFence  extends net.minecraft.src.BlockFence{

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

}
