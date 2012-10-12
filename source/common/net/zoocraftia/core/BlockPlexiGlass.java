package net.zoocraftia.core;

import java.util.Random;

import net.minecraft.src.BlockBreakable;
import net.minecraft.src.Material;

public class BlockPlexiGlass extends BlockBreakable
{
    public BlockPlexiGlass(int par1, int par2)
    {
        super(par1, par2, Material.glass, false);
    }

    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    public int getRenderBlockPass()
    {
        return 0;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    protected boolean canSilkHarvest()
    {
        return true;
    }
    
    public String getTextureFile()
    {
    	return "/zoocraftia/core/blocks.png";
    }
}
