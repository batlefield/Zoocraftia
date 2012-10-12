package net.zoocraftia.core;

import net.minecraft.src.Item;

public class DefaultItem extends Item{

	protected DefaultItem(int par1, boolean flag) {
		super(par1);
		if(flag)
		{
			setHasSubtypes(true);
			setMaxDamage(0);
		}
	}
	
	public String getTextureFile()
	{
		return "/zoocraftia/core/items.png";
	}
	
}
