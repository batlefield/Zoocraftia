package net.zoocraftia.core;

import java.util.List;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.zoocraftia.api.Zoocraftia;

public class ItemMeat extends ItemFood{

	public ItemMeat(int par1) {
		super(par1, 4, false);
		setHasSubtypes(true);
		setMaxDamage(0);
		setTabToDisplayOn(CreativeTabs.tabFood);
		setAlwaysEdible();
	}
	
	public ItemStack onFoodEaten(ItemStack is, World par2World, EntityPlayer par3EntityPlayer)
    {
        --is.stackSize;
        int heal = 0;
        float sat = 0.0F;
        if(is.getItemDamage() == 0)
        {
        	heal = 2;
        	sat = 0.3F;
        }else if(is.getItemDamage() == 1)
        {
        	heal = 6;
        	sat = 0.6F;
        }else if(is.getItemDamage() == 2)
        {
        	heal = 3;
        	sat = 0.3F;
        }else if(is.getItemDamage() == 3)
        {
        	heal = 8;
        	sat = 0.8F;
        }else if(is.getItemDamage() == 4)
        {
        	heal = 2;
        	sat = 0.3F;
        }else if(is.getItemDamage() == 5)
        {
        	heal = 8;
        	sat = 0.8F;
        }
        par3EntityPlayer.getFoodStats().addStats(heal, sat);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        return is;
    }
	
    public String getItemNameIS(ItemStack is)
    {
        if(is.getItemDamage() == 0)
        {
        	return "item.meat.herbivore.raw";
        }else if(is.getItemDamage() == 1)
        {
        	return "item.meat.herbivore.cooked";
        }else if(is.getItemDamage() == 2)
        {
        	return "item.meat.carnivore.raw";
        }else if(is.getItemDamage() == 3)
        {
        	return "item.meat.carnivore.cooked";
        }else if(is.getItemDamage() == 4)
        {
        	return "item.meat.omnivore.raw";
        }else if(is.getItemDamage() == 5)
        {
        	return "item.meat.omnivore.cooked";
        }else{
        	return "item.meat.unknown";
        }
    }
    
    public int getIconFromDamage(int i)
    {
    	switch(i)
    	{
    	case 0:
    		return 14;
		case 1:
    		return 15;
		case 2:
    		return 12;
		case 3:
    		return 13;
		case 4:
			return 16;
		case 5:
			return 17;
		default:
    		return 12;
    	}
    }
    
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
    }
    
    public String getTextureFile()
	{
		return "/zoocraftia/core/items.png";
	}

}
