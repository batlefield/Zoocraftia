package net.zoocraftia.api;

import net.minecraft.src.Item;

public final class Items {

	public static Item getItem(String name)
	{
		try{
			Object ret = Class.forName(getPackage() + ".core.ZoocraftiaItems").getField(name).get(null);
			
			if(ret instanceof Item)
			{
				return (Item)ret;
			}
			return null;
			
		}catch(Exception e)
		{
			System.err.println("Zoocraftia: could not access item " + name);
		}
		return null;
	}
	
	private static String getPackage()
	{
		Package pkg = Items.class.getPackage();
		if(pkg != null)
		{
			return pkg.getName().substring(0, pkg.getName().lastIndexOf('.'));
		}
		return "net.zoocraftia";
	}
	
}
