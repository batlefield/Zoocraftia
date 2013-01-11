package net.zoocraftia.functional;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.zoocraftia.core.ZoocraftiaItems;

public class BlockSafe extends BlockContainer
{

	protected BlockSafe(int par1, Material par2Material)
	{
		super(par1, par2Material);
		blockIndexInTexture = Block.blockSteel.blockIndexInTexture;
		setBlockBounds(0.05F, 0.0F, 0.05F, 0.95F, 0.95F, 0.95F);
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	public void breakBlock(World world, int par2, int par3, int par4, int par5, int par6)
	{
		TileEntitySafe var5 = (TileEntitySafe) world.getBlockTileEntity(par2, par3, par4);

		if (var5 != null)
		{
			for (int var6 = 0; var6 < var5.getSizeInventory(); ++var6)
			{
				ItemStack var7 = var5.getStackInSlot(var6);

				if (var7 != null)
				{
					float var8 = world.rand.nextFloat() * 0.8F + 0.1F;
					float var9 = world.rand.nextFloat() * 0.8F + 0.1F;
					EntityItem var12;

					for (float var10 = world.rand.nextFloat() * 0.8F + 0.1F; var7.stackSize > 0; world.spawnEntityInWorld(var12))
					{
						int var11 = world.rand.nextInt(21) + 10;

						if (var11 > var7.stackSize)
						{
							var11 = var7.stackSize;
						}

						var7.stackSize -= var11;
						var12 = new EntityItem(world, (double) ((float) par2 + var8), (double) ((float) par3 + var9), (double) ((float) par4 + var10), new ItemStack(var7.itemID, var11, var7.getItemDamage()));
						float var13 = 0.05F;
						var12.motionX = (double) ((float) world.rand.nextGaussian() * var13);
						var12.motionY = (double) ((float) world.rand.nextGaussian() * var13 + 0.2F);
						var12.motionZ = (double) ((float) world.rand.nextGaussian() * var13);

						if (var7.hasTagCompound())
						{
							var12.func_92014_d().setTagCompound((NBTTagCompound) var7.getTagCompound().copy());
						}
					}
				}
			}
			if(var5.money >= 10)
			{
				int thousand = (int)(var5.money / 1000);
				int hundred = (int)((var5.money % 1000) / 100);
				int ten = (int)(((var5.money % 1000) % 1000) / 10);
				int tStacks = (int)(thousand / 64);
				int tLeft = (int)(thousand % 64);
				int hStacks = (int)(hundred / 64);
				int hLeft = (int)(hundred % 64);
				int t1Stacks = (int)(ten / 64);
				int t1Left = (int)(ten % 64);
				
				EntityItem ei;
				
				for(int i = 0; i < tStacks; world.spawnEntityInWorld(ei))
				{
					ei = new EntityItem(world, par2, par3, par4, new ItemStack(ZoocraftiaItems.coin, 64, 0));
				}
				
				for(int i = 0; i < hStacks; world.spawnEntityInWorld(ei))
				{
					ei = new EntityItem(world, par2, par3, par4, new ItemStack(ZoocraftiaItems.coin, 64, 1));
				}
				
				for(int i = 0; i < t1Stacks; world.spawnEntityInWorld(ei))
				{
					ei = new EntityItem(world, par2, par3, par4, new ItemStack(ZoocraftiaItems.coin, 64, 2));
				}
				
				ei = new EntityItem(world, par2, par3, par4, new ItemStack(ZoocraftiaItems.coin, tLeft, 0));
				world.spawnEntityInWorld(ei);
				ei = new EntityItem(world, par2, par3, par4, new ItemStack(ZoocraftiaItems.coin, hLeft, 1));
				world.spawnEntityInWorld(ei);
				ei = new EntityItem(world, par2, par3, par4, new ItemStack(ZoocraftiaItems.coin, t1Left, 2));
				world.spawnEntityInWorld(ei);
			}
		}

		super.breakBlock(world, par2, par3, par4, par5, par6);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int direction, float xOffset, float yOffset, float zOffset)
	{
		if(world.isRemote)
		{
			return true;
		}else{
			TileEntitySafe safe = (TileEntitySafe) world.getBlockTileEntity(x, y, z);
			
			if(safe != null)
			{
				safe.blockActivated(world, x, y, z, player);
			}
			
			return true;
		}
	}
	
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
	{
		super.onBlockPlacedBy(world, i, j, k, entityliving);


		int direction = (MathHelper.floor_double((double) (entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3);
		System.out.println(direction);
		TileEntitySafe safe = (TileEntitySafe) world.getBlockTileEntity(i, j, k);
		if (safe != null)
		{
			if(entityliving instanceof EntityPlayer)
			{
				safe.setOwner(((EntityPlayer)entityliving).username);
				safe.setDirection(direction);
			}
		}

	}

	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntitySafe();
	}
	
	public int quantityDropped(Random random)
    {
        return 1;
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
        return ZoocraftiaFunctionalMain.renderID;
    }

}
