package net.zoocraftia.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;

import net.minecraft.block.BlockDispenser;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import net.zoocraftia.api.Zoocraftia;
import net.zoocraftia.client.core.ClientProxy;
import net.zoocraftia.core.network.PacketHandlerZoocraftia;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "Zoocraftia", name = "Zoocraftia Core", version = "Beta 1.0.0")
@NetworkMod(connectionHandler = PacketHandlerZoocraftia.class,
			packetHandler = PacketHandlerZoocraftia.class,
			channels = {"Zoocraftia"}, clientSideRequired = true,
			serverSideRequired = false)
public class ZoocraftiaMain {
	
	//Current version
	public static final String VERSION = "Beta 1.0.0";
	
	//plugin loader instance
	private ZoocraftiaPluginLoader LOADER;
	
	//Zoocraftia instance
	@Instance(value = "Zoocraftia")
	public static ZoocraftiaMain INSTANCE;
	
	//Core class instance
	private static ZoocraftiaCore CORE_INSTANCE;
	
	//packet handler instance
	public static PacketHandlerZoocraftia PACKET_HANDLER_INSTANCE = new PacketHandlerZoocraftia();
	
	//Zoocraftia channel name
	public static String CHANNEL_NAME = "Zoocraftia";
	
	//Sided proxy of the mod
	@SidedProxy(clientSide = "net.zoocraftia.client.core.ClientProxy", serverSide = "net.zoocraftia.core.CommonProxy")
	public static CommonProxy proxy;
	
	//logical string and boolean for version check
	public static String newVersion = null;
	public boolean versionDisplayed = false;
	
	public static Configuration config;
	
	public static boolean getOrCreateBooleanProperty(String key,boolean defaultVal, String category, String comment)
	{
		config.load();
		Property prop = config.get(category, key, defaultVal);
		if(!MathHelper.stringNullOrLengthZero(comment))
		{
			prop.comment = comment;
		}
		config.save();
		return Boolean.valueOf(prop.value);
	}
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		LOADER = new ZoocraftiaPluginLoader();
	}
	
	
	public String getVersion() {
		return VERSION;
	}

	@Init
	public void load(FMLInitializationEvent event) {
		proxy.initialize();
		loadCore();
		proxy.postInitialize();
	}

	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event)
	{
		LOADER.load();
		System.out.println("Zoocraftia: loaded " + LOADER.getPlugins().size() + " plugin/s.");
	}
	
	@ServerStarting
	public void serverStarted(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandPay());
		event.registerServerCommand(new CommandGrant());
	}
	
	public void checkVersion() {
		try
	    {
	        URL url = new URL("http://www2.arnes.si/~pmati/ZTUC.html");
	        InputStreamReader isr = new InputStreamReader(url.openStream());
	        BufferedReader reader = new BufferedReader(isr);
	        String line;
	
	        while ((line = reader.readLine()) != null)
	        {
	            if(!line.equalsIgnoreCase(VERSION))
	            {
	            	newVersion = line;
					proxy.versionNotify();
	            }
	        }
	        
	        isr.close();
	        reader.close();
	    }
	    catch (Exception exception)
	    {
	        exception.printStackTrace();
	    }
	}

	public void loadCore() {
		//Basic zoocraftia crap
		Zoocraftia.addLocalization("zoocraftia.money_balance.name", "Money Balance", "misc");
		config = new Configuration(new File(proxy.getLocation(), "Zoocraftia/Core/Configuration.cfg"));
		
		//different registrations inside FML
		TickRegistry.registerTickHandler(new ZoocraftiaTicker(INSTANCE, false), Side.CLIENT);
		NetworkRegistry.instance().registerConnectionHandler(PACKET_HANDLER_INSTANCE);
		NetworkRegistry.instance().registerChannel(PACKET_HANDLER_INSTANCE, CHANNEL_NAME);
		GameRegistry.registerWorldGenerator(new WorldGeneratorZoocraftia());
		
		//Money manager initialization
		if(proxy.MONEY_MANAGER == null)
		{
			proxy.MONEY_MANAGER = new MoneyManager();
		}
		Zoocraftia.MONEY_MANAGER = proxy.MONEY_MANAGER;
		if(proxy.TAGGED_MANAGER == null)
		{
			proxy.TAGGED_MANAGER = new TaggedEntityManager();
		}
		Zoocraftia.TAGGED_MANAGER = proxy.TAGGED_MANAGER;
		
		if(!(proxy instanceof ClientProxy))
		{
			checkVersion();
		}
		
		//Core initialization
		CORE_INSTANCE = new ZoocraftiaCore();
		CORE_INSTANCE.initialize();
	}
	
	public static ZoocraftiaMain getInstance()
	{
		return INSTANCE;
	}
	
	public static ZoocraftiaCore getCoreInstance()
	{
		return CORE_INSTANCE;
	}
}
