package net.zoocraftia.api;

import java.util.ArrayList;
import java.util.Map;

import net.minecraft.src.BaseMod;

public interface IZoocraftiaPlugin{
	
	/**
	 * Main method from which the plugin must be loaded
	 */
	public void loadPlugin();
	
	/**
	 * Returns the name of the plugin
	 * @return plugin name
	 */
	public String getPluginName();
	
}
