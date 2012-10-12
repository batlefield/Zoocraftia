package net.zoocraftia.core;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.Material;
import net.minecraft.src.World;


public class BlockQuicksand extends Block
{

    public BlockQuicksand(int i, int j)
    {
        super(i, j, Material.web);
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
        entity.setInWeb();
    }
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }

    public String getTextureFile()
    {
    	return "/zoocraftia/core/blocks.png";
    }
	
}
