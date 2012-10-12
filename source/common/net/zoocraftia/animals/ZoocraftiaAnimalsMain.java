package net.zoocraftia.animals;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EnumCreatureType;
import net.zoocraftia.api.IZoocraftiaPlugin;
import net.zoocraftia.api.Zoocraftia;
import net.zoocraftia.api.ZoocraftiaEntityInfo;
import net.zoocraftia.core.network.PacketHandlerZoocraftia;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;

@Mod(modid = "ZoocraftiaAnimals", name = "Zoocraftia Animals", version = "Beta 1.0.0")
@NetworkMod(connectionHandler = PacketHandlerZoocraftia.class,
			packetHandler = PacketHandlerZoocraftia.class,
			channels = {"Zoocraftia"}, clientSideRequired = true,
			serverSideRequired = false)
public class ZoocraftiaAnimalsMain implements IZoocraftiaPlugin {

	@Instance(value = "ZoocraftiaAnimals")
	public static ZoocraftiaAnimalsMain INSTANCE;
	@SidedProxy(clientSide = "net.zoocraftia.animals.client.ClientProxy", serverSide = "net.zoocraftia.animals.CommonProxy")
	public static CommonProxy proxy;
	
	public HashMap<Class<? extends EntityLiving>, ZoocraftiaEntityInfo> entities = new HashMap<Class<? extends EntityLiving>, ZoocraftiaEntityInfo>();
	
	public void loadPlugin() {
		entities.put(ZoocraftiaEntityFlamingo.class, new ZoocraftiaEntityInfo("Flamingo", EntityRegistry.findGlobalUniqueEntityId(), 0xffa7a7, 0xffc5b3, 10, 3, 7, EnumCreatureType.creature, true));
		
		
		loadEntities();
		proxy.initialize();
	}
	
	private void loadEntities()
	{
		int modID = 0;
		for(Entry<Class<? extends EntityLiving>, ZoocraftiaEntityInfo> e : entities.entrySet())
		{
			if(e.getValue().backgroundEggColor == -1 || e.getValue().foregroundEggColor == -1)
			{
				EntityRegistry.registerGlobalEntityID(e.getKey(), e.getValue().name, e.getValue().ID);
			}else{
				EntityRegistry.registerGlobalEntityID(e.getKey(), e.getValue().name, e.getValue().ID, e.getValue().backgroundEggColor, e.getValue().foregroundEggColor);
			}
			EntityRegistry.registerModEntity(e.getKey(), e.getValue().name, modID, this, 80, 3, true);
			if(e.getValue().shouldSpawn)
			{
				EntityRegistry.addSpawn(e.getKey(), e.getValue().probability, e.getValue().min, e.getValue().max, e.getValue().type, e.getValue().biomes);
			}
			Zoocraftia.addLocalization("entity." + e.getValue().name + ".name", e.getValue().name, "entities");
			modID++;
		}
	}
	
	public String getPluginName() {
		return "Zoocraftia: Animals";
	}
}
