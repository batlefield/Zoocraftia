package net.zoocraftia.core;

import net.minecraft.src.Block;
import net.minecraft.src.Material;

public class DefaultBlock extends Block{

	public DefaultBlock(int par1, int par2, Material par3Material) {
		super(par1, par2, par3Material);
	}
	
	public DefaultBlock(int par1, Material par3Material) {
		super(par1, par3Material);
	}
	
	public String getTextureFile()
    {
        return "/zoocraftia/core/blocks.png";
    }

}
