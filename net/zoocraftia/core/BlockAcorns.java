package net.zoocraftia.core;

import net.minecraft.block.BlockFlower;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockAcorns extends BlockFlower {

	public BlockAcorns(int i, int j) {
		super(i, j);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
	}
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return 23;
    }
    
    public boolean canThisPlantGrowOnThisBlockID(int i)
    { 
		return i == ZoocraftiaBlocks.deciduousGround.blockID;
    }

    public String getTextureFile()
    {
    	return "/zoocraftia/core/blocks.png";
    }

}
