package net.zoocraftia.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public class TileEntityZoocraftia extends TileEntity{
	
	
	private int direction;
	private String owner;
	
	public int getX()
	{
		return MathHelper.floor_double(xCoord);
	}
	
	public int getY()
	{
		return MathHelper.floor_double(yCoord);
	}
	
	public int getZ()
	{
		return MathHelper.floor_double(zCoord);
	}
	
	public int getDirection()
	{
		return direction;
	}
	
	public void setDirection(int i)
	{
		this.direction = (byte) i;
	}
	
	public String getOwner()
	{
		return owner;
	}
	
	public void setOwner(String owner)
	{
		this.owner = owner;
	}
	
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return owner.equals(player.username);
	}
	
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		direction = nbt.getInteger("Direction");
		owner = nbt.getString("Owner");
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		nbt.setInteger("Direction", direction);
		if(!stringNullOrLengthZero(owner))
		{
			nbt.setString("Owner", owner);
		}
	}
	
	public static boolean stringNullOrLengthZero(String par0Str)
    {
        return par0Str == null || par0Str.length() == 0;
    }

}
