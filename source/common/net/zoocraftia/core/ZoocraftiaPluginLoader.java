package net.zoocraftia.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.client.Minecraft;
import net.zoocraftia.api.IZoocraftiaPlugin;
import cpw.mods.fml.common.Loader;

public class ZoocraftiaPluginLoader {

	private List<IZoocraftiaPlugin> plugins = new ArrayList<IZoocraftiaPlugin>();
	
	public void load()
	{
		for(Entry e : Loader.instance().getModObjectList().entrySet())
		{
			System.out.println("Found " + e.getValue().getClass().getSimpleName());
			if(e.getValue() instanceof IZoocraftiaPlugin)
			{
				Object o = e.getValue();
				plugins.add((IZoocraftiaPlugin) o);
				((IZoocraftiaPlugin)o).loadPlugin();
				System.out.println("Zoocraftia: loaded " + ((IZoocraftiaPlugin) o).getPluginName());
			}
		}
	}
	
	public List<IZoocraftiaPlugin> getPlugins()
	{
		return plugins;
	}
}
