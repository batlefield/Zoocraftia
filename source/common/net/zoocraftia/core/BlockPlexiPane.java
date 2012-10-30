package net.zoocraftia.core;

import java.util.List;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.BlockPane;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Entity;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

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
