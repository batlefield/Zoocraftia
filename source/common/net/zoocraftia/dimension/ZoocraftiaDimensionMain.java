package net.zoocraftia.dimension;

import java.io.File;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.Property;
import net.zoocraftia.api.IZoocraftiaPlugin;
import net.zoocraftia.api.Zoocraftia;
import net.zoocraftia.api.ZoocraftiaException;
import net.zoocraftia.core.ZoocraftiaMain;
import net.zoocraftia.core.network.PacketHandlerZoocraftia;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "ZoocraftiaDimension", name = "Zoocraftia Dimension", version = "Beta 1.0.0")
@NetworkMod(connectionHandler = PacketHandlerZoocraftia.class,
			packetHandler = PacketHandlerZoocraftia.class,
			channels = {"Zoocraftia"}, clientSideRequired = true,
			serverSideRequired = false)
public class ZoocraftiaDimensionMain implements IZoocraftiaPlugin{

	@SidedProxy(serverSide="net.zoocraftia.dimension.CommonProxy", clientSide="net.zoocraftia.dimension.client.ClientProxy")
	public static CommonProxy proxy;
	public static int dimensionID;
	
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
	
	public static int getOrCreateItemID(String item, int id)
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
	
	public static int getOrCreateIntegerProperty(String props, int id, String comment)
	{
		config.load();
		Property prop = config.get(config.CATEGORY_GENERAL, props, id);
		config.save();
		if(!prop.isIntValue())
		{
			throw new ZoocraftiaException("Integer " + props + "is invalid!");
		}
		return prop.getInt();
	}
	
	public void loadPlugin() {
		config = new Configuration(new File(proxy.getLocation(), "Zoocraftia/Dimension/Configuration.cfg"));
		
		ZoocraftiaException e = new ZoocraftiaException("Something weird happend when registering dimension, contact battlefield");
		
		int providerID = getOrCreateIntegerProperty("ProviderID", 5, "Zoocraftia world provider ID(same as dimension ID)");
		dimensionID = getOrCreateIntegerProperty("DimensionID", 5, "The id of zoocraftia diamnsion");
		if(!DimensionManager.registerProviderType(providerID, WorldProviderZoocraftia.class, true))
		{
			throw e;
		}
		DimensionManager.registerDimension(dimensionID, providerID);
		
		proxy.preInit();
		initialize();
		proxy.postInit();
		
	}

	public String getPluginName() {
		return "Zoocraftia Dimension";
	}

	public void initialize()
	{
		ZoocraftiaBlocks.portal = new BlockPortal(getOrCreateBlockID("Portal", 3020), 0).setHardness(-1.0F).setStepSound(Block.soundGlassFootstep).setLightValue(0.75F).setBlockName("portal");
	
	
	
		GameRegistry.registerBlock(ZoocraftiaBlocks.portal);
		Zoocraftia.addName(ZoocraftiaBlocks.portal, "Portal");
	}
	
}
