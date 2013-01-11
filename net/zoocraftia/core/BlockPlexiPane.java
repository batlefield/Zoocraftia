package net.zoocraftia.core;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockPlexiPane extends BlockPane
{
    private int sideTextureIndex;

    protected BlockPlexiPane(int par1, int par2, int par3, Material par4Material)
    {
        super(par1, par2, par3, par4Material, false);
        this.sideTextureIndex = par3;
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    public String getTextureFile()
    {
    	return "/zoocraftia/core/blocks.png";
    }
}
