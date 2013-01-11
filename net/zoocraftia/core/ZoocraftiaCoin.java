package net.zoocraftia.core;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.zoocraftia.api.MoneyHelper;

public class ZoocraftiaCoin extends Item {

	protected ZoocraftiaCoin(int par1) {
		super(par1);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	public int getIconFromDamage(int i)
    {
        switch(i)
        {
        case 0:
        	return 8;
        case 1:
        	return 9;
        case 2:
        	return 10;
    	default:
    		return 8;
        }
    }
	
	public String getItemNameIS(ItemStack par1ItemStack)
	{
	    return "item.coin." + names[par1ItemStack.getItemDamage()];
	}
	 
	private String[] names = new String[]{
			"gold", "silver", "bronze"
	};
	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
		int meta = itemstack.getItemDamage();
		
		if(!world.isRemote)
		{
			switch(meta)
			{
			case 0:
				MoneyHelper.addMoney(entityplayer, 1000);
				break;
			case 1:
				MoneyHelper.addMoney(entityplayer, 100);
				break;
			case 2:
				MoneyHelper.addMoney(entityplayer, 10);
				break;
			}
		}
		
		itemstack.stackSize--;
		
        return itemstack;
    }
	
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
    }
	
	public String getTextureFile()
	{
		return "/zoocraftia/core/items.png";
	}

}
