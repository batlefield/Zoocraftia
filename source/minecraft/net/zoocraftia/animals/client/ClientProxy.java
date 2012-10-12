package net.zoocraftia.animals.client;

import java.util.Map.Entry;

import net.minecraft.src.EntityLiving;
import net.zoocraftia.animals.CommonProxy;
import net.zoocraftia.animals.ZoocraftiaAnimalsMain;
import net.zoocraftia.api.ZoocraftiaEntityInfo;
import net.zoocraftia.api.client.ZoocraftiaEntityRender;
import net.zoocraftia.core.ZoocraftiaMain;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{

	public void initialize()
	{
		for(Entry<Class<? extends EntityLiving>, ZoocraftiaEntityInfo> e : ZoocraftiaAnimalsMain.INSTANCE.entities.entrySet())
		{
			RenderingRegistry.registerEntityRenderingHandler(e.getKey(), new ZoocraftiaEntityRender());
		}
	}
	
}
