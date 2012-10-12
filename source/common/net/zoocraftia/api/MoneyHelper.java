package net.zoocraftia.api;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.zoocraftia.core.ZoocraftiaMain;

public class MoneyHelper {

	public static void addMoney(EntityPlayer player, int moneyToAdd)
	{
		ZoocraftiaMain.proxy.MONEY_MANAGER.addMoney(player, moneyToAdd);
	}
	
	public static void removeMoney(EntityPlayer player, int moneyToRemove)
	{
		ZoocraftiaMain.proxy.MONEY_MANAGER.addMoney(player, -moneyToRemove);
	}
	
	public static void addDebt(EntityPlayer player, EntityLiving debtOwner, int debt)
	{
		ZoocraftiaMain.proxy.MONEY_MANAGER.addDebt(player, debtOwner, debt);
	}
	
	public static void removeDebt(EntityPlayer player, EntityLiving debtOwner, int debt)
	{
		ZoocraftiaMain.proxy.MONEY_MANAGER.removeDebt(player, debtOwner, debt);
	}
	
	public static void removeDebt(EntityPlayer player, EntityLiving debtOwner, int debt, double interestRate)
	{
		ZoocraftiaMain.proxy.MONEY_MANAGER.removeDebt(player, debtOwner, debt, interestRate);
	}
	
	public static int getMoney(EntityPlayer player)
	{
		return ZoocraftiaMain.proxy.MONEY_MANAGER.getMoney(player);
	}
	
}
