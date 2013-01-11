package net.zoocraftia.functional;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.zoocraftia.core.TileEntityZoocraftia;

public class TileEntitySafe extends TileEntityZoocraftia implements IInventory{

	public ItemStack[] stacks = new ItemStack[6];
	public float prevAngle;
	public float currentAngle;
	public boolean locked = true;
	public boolean setPassword = true;
	public int money;
	public String password = "";
	private int ticksSinceSync;
	
	public void blockActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		if(!locked)
		{
			lock();
		}else{
			unlock();
		}
	}
	
	public void lock()
	{
		locked = true;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, ZoocraftiaBlocks.safe.blockID, 1, (locked == true ? 1 : 0));
	}
	
	public void unlock()
	{
		locked = false;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, ZoocraftiaBlocks.safe.blockID, 1, (locked == true ? 1 : 0));
	}
	
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		locked = nbt.getBoolean("Lock");
		setPassword = nbt.getBoolean("Password set");
		password = nbt.getString("Password");
		money = nbt.getInteger("Money");
		
		NBTTagList nbtList = nbt.getTagList("Items");
		stacks = new ItemStack[getSizeInventory()];
		
		for(int x = 0; x < nbtList.tagCount(); x++)
		{
			NBTTagCompound nbt2 = (NBTTagCompound) nbtList.tagAt(x);
			byte b = (byte) (nbt2.getByte("Slot") & 255);
			
			if(b >= 0 && b < this.stacks.length)
			{
				stacks[b] = ItemStack.loadItemStackFromNBT(nbt2);
			}
		}
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		nbt.setBoolean("Lock", locked);
		nbt.setBoolean("Password set", setPassword);
		nbt.setString("Password", password);
		nbt.setInteger("Money", money);
		
		NBTTagList nbtList = new NBTTagList();
		
		for(int x = 0; x < stacks.length; x++)
		{
			if(stacks[x] != null)
			{
				NBTTagCompound nbt2 = new NBTTagCompound();
				nbt2.setByte("Slot", (byte) x);
				stacks[x].writeToNBT(nbt2);
				nbtList.appendTag(nbt2);
			}
		}
		nbt.setTag("Items", nbtList);
	}
	
	public void updateEntity()
	{
		super.updateEntity();
		if((++ticksSinceSync % 20) * 4 == 0)
		{
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, ZoocraftiaBlocks.safe.blockID, 3, (((locked == true ? 1 : 0) << 3) & 0xF8) | (getDirection() & 0x7));
		}
		prevAngle = currentAngle;
		float f = 0.1F;
		
		if(!locked && currentAngle == 0.0F)
		{
			worldObj.playSoundEffect(xCoord, yCoord + 0.5D, zCoord, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}
		
		if(locked && currentAngle > 0.0F || !locked && currentAngle < 1.0F)
		{
			float f1 = currentAngle;
			
			if(!locked)
			{
				currentAngle += f;
			}else{
				currentAngle -= f;
			}
			
			if(currentAngle > 1.0F)
			{
				currentAngle = 1.0F;
			}
			
			if(currentAngle < 0.0F)
			{
				currentAngle = 0.0F;
			}
			
			float f2 = 0.5F;
			
			if(currentAngle < f2 && f1 >= f2)
			{
				worldObj.playSoundEffect(xCoord, yCoord+ 0.5D, zCoord, "random.chestclosed", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
			}
		}
	}
	
	@Override
	public int getSizeInventory() {
		return stacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return stacks[var1];
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		if(stacks[var1] != null)
		{
			ItemStack is;
			
			if(stacks[var1].stackSize <= var2)
			{
				is = stacks[var1];
				stacks[var1] = null;
				return is;
			}else{
				is = stacks[var1].splitStack(var2);
				
				if(stacks[var1].stackSize == 0)
				{
					stacks[var1] = null;
				}
				
				return is;
			}
		}else{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		if(stacks[var1] != null)
		{
			ItemStack is = stacks[var1];
			stacks[var1] = null;
			return is;
		}else{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		stacks[var1] = var2;
		
		if(var2 != null && var2.stackSize > getInventoryStackLimit())
		{
			var2.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName() {
		return "Safe";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	public boolean isUseableByPlayer(EntityPlayer player) {
		if(this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this)
		{
			return false;
		}else{
			return player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
		}
	}

	public void receiveClientEvent(int par1, int par2) {
		if(par1 == 1)
		{
			locked = (par2 == 1 ? true : false);
		}else if(par1 == 2)
		{
			setDirection(par2);
		}else if(par1 == 3)
		{
			setDirection(par2 & 0x7);
            locked = ((par2 & 0xF8) >> 3) == 1 ? true : false;
		}
	}
	
	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}
}
