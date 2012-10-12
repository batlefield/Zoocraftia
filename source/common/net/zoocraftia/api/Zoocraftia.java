package net.zoocraftia.api;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import net.zoocraftia.core.MoneyManager;
import net.zoocraftia.core.TaggedEntityManager;
import net.zoocraftia.core.ZoocraftiaMain;

public class Zoocraftia {
	
	private static Map<Class<? extends GuiScreen>, Integer> guiIds = new HashMap<Class<? extends GuiScreen>, Integer>();
	private int lastID = 0;
	private static boolean init = false;
	private static Configuration guiConfig = new Configuration(new File(ZoocraftiaMain.INSTANCE.proxy.getLocation(), "Zoocraftia/GuiIds.cfg"));
	private static Configuration lang = new Configuration(new File(ZoocraftiaMain.INSTANCE.proxy.getLocation(), "Zoocraftia/localization.lang"));
	public static MoneyManager MONEY_MANAGER;
	public static TaggedEntityManager TAGGED_MANAGER;
	
	public static void init()
	{
		if(init)
		{
			return;
		}
		guiConfig.categories.clear();
		guiConfig.categories.put("guiids", new TreeMap<String, Property>());
		lang.categories.clear();
		lang.categories.put("items", new TreeMap<String, Property>());
		
		
		init = true;
	}
	
	public static void addGui(Class<? extends GuiScreen> cls, int i)
	{
		init();
		int x = getOrCreatGuiID(cls, i);
		guiIds.put(cls, x);
	}
	
	private static int getOrCreatGuiID(Class<? extends GuiScreen> cls, int i)
	{
		guiConfig.load();
		int ret = new Integer(guiConfig.getOrCreateIntProperty(cls.getSimpleName(), "guiids", i).value).intValue();
		guiConfig.save();
		return ret;
		
	}
	
	public static int getGuiId(Class<? extends GuiScreen> cls)
	{
		init();
		return guiIds.get(cls);
	}
	
	public static void addName(Object obj, String s)
	{
		String objectName;
        if (obj instanceof Item) {
            objectName=((Item)obj).getItemName();
        } else if (obj instanceof Block) {
            objectName=((Block)obj).getBlockName();
        } else if (obj instanceof ItemStack) {
            objectName=Item.itemsList[((ItemStack)obj).itemID].getItemNameIS((ItemStack)obj);
        } else {
            throw new IllegalArgumentException(String.format("Illegal object for naming %s",obj));
        }
        objectName+=".name";
        addLocalization(objectName, s);
	}
	
	public static void addLocalization(String mcName, String name)
	{
		addLocalization(mcName, name, "items");
	}
	
	public static void addLocalization(String mcName, String name, String type)
	{
		lang.load();
		String s = name;
		if(lang != null)
		{
			if(!lang.categories.containsKey(type))
			{
				lang.categories.put(type, new TreeMap<String, Property>());
			}
			s = lang.getOrCreateProperty(mcName, type, name).value;
		}
		lang.save();
		ModLoader.addLocalization(mcName, name);
	}
	
	public static MoneyManager getMoneyManager()
	{
		return MONEY_MANAGER;
	}
	
}
