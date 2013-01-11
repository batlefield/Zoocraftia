package net.zoocraftia.functional;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import net.zoocraftia.api.IZoocraftiaPlugin;
import net.zoocraftia.api.Zoocraftia;
import net.zoocraftia.api.ZoocraftiaException;
import net.zoocraftia.core.network.PacketHandlerZoocraftia;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "ZoocraftiaFunctional", name = "Zoocraftia Functionals", version = "Beta 1.0.0")
@NetworkMod(connectionHandler = PacketHandlerZoocraftia.class,
			packetHandler = PacketHandlerZoocraftia.class,
			channels = {"Zoocraftia"}, clientSideRequired = true,
			serverSideRequired = false)
public class ZoocraftiaFunctionalMain implements IZoocraftiaPlugin
{

	public static int renderID;
	
	@SidedProxy(clientSide = "net.zoocraftia.client.functional.ClientProxy", serverSide = "net.zoocraftia.functional.CommonProxy")
	public static CommonProxy proxy;
	@Instance(value = "ZoocraftiaFunctional")
	public static ZoocraftiaFunctionalMain INSTANCE;
	
	static Configuration config;
	
	public static int getOrCreateBlockID(String block, int id)
	{
		config.load();
		Property prop = config.getBlock(block, id);
		config.save();
		if(!prop.isIntValue())
		{
			throw new ZoocraftiaException("Id for " + block + "is invalid!");
		}
		return prop.getInt();
	}
	
	public static int getOrCreatItemID(String item, int id)
	{
		config.load();
		Property prop = config.getItem(item, id);
		config.save();
		if(!prop.isIntValue())
		{
			throw new ZoocraftiaException("Id for " + item + "is invalid!");
		}
		return prop.getInt();
	}
	
	public void registerBlock(Block block, String name)
	{
		GameRegistry.registerBlock(block);
		Zoocraftia.addName(block, name);
	}
	
	public void loadPlugin()
	{
		proxy.init();
		
		config = new Configuration(new File(proxy.getLocation(), "/Zoocraftia/Core/IDConfig.cfg"));
		
		GameRegistry.registerTileEntity(TileEntitySafe.class, "Safe");
		
		ZoocraftiaBlocks.safe = new BlockSafe(getOrCreateBlockID("Safe", 3021), Material.iron).setBlockName("safe").setHardness(1F);
		
		registerBlock(ZoocraftiaBlocks.safe, "Safe");
	}

	public String getPluginName()
	{
		return "Zoocraftia: Functional";
	}

}
