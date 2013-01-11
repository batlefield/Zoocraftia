package net.zoocraftia.core;

import java.util.Random;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

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

	public int getRenderType()
    {
        return ZoocraftiaCore.plexiglassRenderID;
    }
	
	public String getTextureFile()
	{
		return "/zoocraftia/core/connectedGlass.png";
	}

	public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
	{
		return true;
	}

	public int getBlockTexture(IBlockAccess iblockaccess, int x, int y, int z, int i)
	{
		int texture = 0;
		boolean b[] = new boolean[6];
		
		if (i <= 1)
		{
			b[0] = isShouldBeConnected(iblockaccess, x - 1, y, z, i);
			b[1] = isShouldBeConnected(iblockaccess, x + 1, y, z, i);
			b[2] = isShouldBeConnected(iblockaccess, x, y, z + 1, i);
			b[3] = isShouldBeConnected(iblockaccess, x, y, z - 1, i);
		}
		else if (i == 2)
		{
			b[0] = isShouldBeConnected(iblockaccess, x + 1, y, z, i);
			b[1] = isShouldBeConnected(iblockaccess, x - 1, y, z, i);
			b[2] = isShouldBeConnected(iblockaccess, x, y - 1, z, i);
			b[3] = isShouldBeConnected(iblockaccess, x, y + 1, z, i);
		}
		else if (i == 3)
		{
			b[0] = isShouldBeConnected(iblockaccess, x - 1, y, z, i);
			b[1] = isShouldBeConnected(iblockaccess, x + 1, y, z, i);
			b[2] = isShouldBeConnected(iblockaccess, x, y - 1, z, i);
			b[3] = isShouldBeConnected(iblockaccess, x, y + 1, z, i);
		}
		else if (i == 4)
		{
			b[0] = isShouldBeConnected(iblockaccess, x, y, z - 1, i);
			b[1] = isShouldBeConnected(iblockaccess, x, y, z + 1, i);
			b[2] = isShouldBeConnected(iblockaccess, x, y - 1, z, i);
			b[3] = isShouldBeConnected(iblockaccess, x, y + 1, z, i);
		}
		else if (i == 5)
		{
			b[0] = isShouldBeConnected(iblockaccess, x, y, z + 1, i);
			b[1] = isShouldBeConnected(iblockaccess, x, y, z - 1, i);
			b[2] = isShouldBeConnected(iblockaccess, x, y - 1, z, i);
			b[3] = isShouldBeConnected(iblockaccess, x, y + 1, z, i);
		}
		if (b[0] & !b[1] & !b[2] & !b[3])
			texture = 3;
		else if (!b[0] & b[1] & !b[2] & !b[3])
			texture = 1;
		else if (!b[0] & !b[1] & b[2] & !b[3])
			texture = 16;
		else if (!b[0] & !b[1] & !b[2] & b[3])
			texture = 48;
		else if (b[0] & b[1] & !b[2] & !b[3])
			texture = 2;
		else if (!b[0] & !b[1] & b[2] & b[3])
			texture = 32;
		else if (b[0] & !b[1] & b[2] & !b[3])
			texture = 19;
		else if (b[0] & !b[1] & !b[2] & b[3])
			texture = 51;
		else if (!b[0] & b[1] & b[2] & !b[3])
			texture = 17;
		else if (!b[0] & b[1] & !b[2] & b[3])
			texture = 49;
		else if (!b[0] & b[1] & b[2] & b[3])
			texture = 33;
		else if (b[0] & !b[1] & b[2] & b[3])
			texture = 35;
		else if (b[0] & b[1] & !b[2] & b[3])
			texture = 50;
		else if (b[0] & b[1] & b[2] & !b[3])
			texture = 18;
		else if (b[0] & b[1] & b[2] & b[3])
			texture = 34;
		return texture;
	}

	private boolean isShouldBeConnected(IBlockAccess iblockaccess, int x, int y, int z, int i)
	{
		int x2 = x;
		int y2 = y;
		int z2 = z;
		if (i == 0)
			y2--;
		else if (i == 1)
			y2++;
		else if (i == 2)
			z2--;
		else if (i == 3)
			z2++;
		else if (i == 4)
			x2--;
		else if (i == 5)
			x2++;
		return ((iblockaccess.getBlockId(x, y, z) == blockID) && !iblockaccess.isBlockOpaqueCube(x2, y2, z2) && (iblockaccess.getBlockId(x2, y2, z2) != blockID));
	}
}
