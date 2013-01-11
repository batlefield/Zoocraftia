package net.zoocraftia.core;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;


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
