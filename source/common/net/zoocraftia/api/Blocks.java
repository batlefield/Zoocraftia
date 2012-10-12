package net.zoocraftia.api;

import net.minecraft.src.Block;

public final class Blocks {

	public static Block getBlock(String name)
	{
		try{
			Object ret = Class.forName(getPackage() + ".core.ZoocraftiaBlocks").getField(name).get(null);
			
			if(ret instanceof Block)
			{
				return (Block)ret;
			}
			return null;
			
		}catch(Exception e)
		{
			System.err.println("Zoocraftia: could not access block " + name);
		}
		return null;
	}
	
	private static String getPackage()
	{
		Package pkg = Blocks.class.getPackage();
		if(pkg != null)
		{
			return pkg.getName().substring(0, pkg.getName().lastIndexOf('.'));
		}
		return "net.zoocraftia";
	}
	
}
