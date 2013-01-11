package net.zoocraftia.core;

import java.util.ArrayList;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class MoneyHandler
{

	public EntityPlayer player;
	public int money;
	public ArrayList<Debt> debts = new ArrayList<Debt>();
	
	public MoneyHandler(EntityPlayer player)
	{
		this.player = player;
		money = 0;
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("Money", money);
		NBTTagList nbt1 = new NBTTagList();
		for(Debt debt : debts)
		{
			NBTTagCompound nbt2 = new NBTTagCompound();
			nbt2.setString("EntityName", EntityList.getEntityString(debt.debtOwner));
			nbt2.setInteger("Debt", debt.debt);
			nbt1.appendTag(nbt2);
		}
		nbt.setTag("Debts", nbt1);
	}
	
	public void readFromNBT(NBTTagCompound nbt)
	{
		money = nbt.getInteger("Money");
		debts.clear();
		NBTTagList nbt1 = nbt.getTagList("Debts");
		for(int i = 0; i < nbt1.tagCount(); i++)
		{
			NBTTagCompound nbt2 = (NBTTagCompound) nbt1.tagAt(i);
			EntityLiving entity = (EntityLiving) EntityList.createEntityByName(nbt2.getString("EntityName"), player.worldObj);
			if(entity != null)
			{
				addDebt(entity, nbt2.getInteger("Debt"));
			}
			
		}
	}
	
	public void addDebt(EntityLiving debtOwner, int debt)
	{
		debts.add(new Debt(debtOwner, debt));
	}
	
	public static class Debt{
		public EntityLiving debtOwner;
		public int debt;
		
		public Debt(EntityLiving entity, int debt)
		{
			debtOwner = entity;
			this.debt = debt;
		}
		
		public boolean equals(Object obj)
		{
			if(!(obj instanceof Debt))
			{
				return false;
			}else if(this.debt != ((Debt)obj).debt)
			{
				return false;
			}else if(this.debtOwner != ((Debt)obj).debtOwner)
			{
				return false;
			}
			return true;
		}
		
	}
	
}
