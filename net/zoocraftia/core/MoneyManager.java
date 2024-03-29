package net.zoocraftia.core;

import java.util.ArrayList;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.zoocraftia.core.MoneyHandler.Debt;

public class MoneyManager extends PerPlayerBase
{
	public void loadPlayer(EntityPlayer player)
	{
		MoneyHandler mh = new MoneyHandler(player);
		addEntry(player, mh);
		mh.readFromNBT(player.getEntityData());
		ZoocraftiaMain.proxy.handleMoneySet(player, mh.money);
		ZoocraftiaMain.proxy.handleDebtSet(player, getDebts(player));
	}
	
	public void unloadPlayer(EntityPlayer player)
	{
		((EntityPlayer) removeEntry(player)).writeToNBT(player.getEntityData());
	}
	
	public void setMoney(EntityPlayer player, int i)
	{
		trigger(player);
		((MoneyHandler)getEntry(player)).money = i;
		((MoneyHandler)getEntry(player)).writeToNBT(player.getEntityData());
	}
	
	public void addMoney(EntityPlayer player, int money)
	{
		trigger(player);
		
		MoneyHandler mh = ((MoneyHandler)getEntry(player));
		mh.money += money;
		ZoocraftiaMain.proxy.handleMoneyAdd(player, money);
		mh.writeToNBT(player.getEntityData());
	}
	
	public int getMoney(EntityPlayer player)
	{
		trigger(player);
		
		MoneyHandler mh = ((MoneyHandler)getEntry(player));
		return mh.money;
	}
	
	public boolean removeDebt(EntityPlayer player, EntityLiving debtOwner, int debt)
	{
		return removeDebt(player, debtOwner, debt, 0.2);
	}
	
	public boolean removeDebt(EntityPlayer player, EntityLiving debtOwner, int debt, double interestRate)
	{
		trigger(player);
		
		ArrayList<MoneyHandler.Debt> debts = getDebts(player);
		Debt debtObj = new Debt(debtOwner, debt);
		for(Debt debt1 : debts)
		{
			if(debt1.equals(debtObj))
			{
				ZoocraftiaMain.proxy.handleDebtRemove(player, debtOwner, debt, interestRate);
				
				debts.remove(debt1);
				addMoney(player, (int) -(debtObj.debt * (1 + interestRate)));
				((MoneyHandler)getEntry(player)).writeToNBT(player.getEntityData());
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<MoneyHandler.Debt> getDebts(EntityPlayer player)
	{
		trigger(player);
		
		MoneyHandler mh = ((MoneyHandler)getEntry(player));
		return mh.debts;
	}

	public void addDebt(EntityPlayer player, EntityLiving debtOwner, int debt)
	{
		trigger(player);
		
		ZoocraftiaMain.proxy.handleDebtAdd(player, debtOwner, debt);
		
		((MoneyHandler)getEntry(player)).addDebt(debtOwner, debt);
	}
}
