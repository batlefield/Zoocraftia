package net.zoocraftia.client.functional;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.zoocraftia.core.TileEntityZoocraftia;
import net.zoocraftia.functional.CommonProxy;
import net.zoocraftia.functional.TileEntitySafe;
import net.zoocraftia.functional.ZoocraftiaFunctionalMain;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{

	public void init()
	{
		super.init();
		ZoocraftiaFunctionalMain.renderID = RenderingRegistry.getNextAvailableRenderId();
		MinecraftForgeClient.preloadTexture("/zoocraftia/functional/blocks.png");
		MinecraftForgeClient.preloadTexture("/zoocraftia/functional/items.png");
		RenderingRegistry.registerBlockHandler(new SafeRenderHandler());
		registerTileRender(TileEntitySafe.class, new SafeRenderer());
	}
	
	public void registerTileRender(Class<? extends TileEntityZoocraftia> clz, TileEntitySpecialRenderer specialRender)
	{
		TileEntityRenderer.instance.specialRendererMap.put(clz, specialRender);
		specialRender.setTileEntityRenderer(TileEntityRenderer.instance);
	}
	
	public File getLocation()
	{
		return Minecraft.getMinecraftDir();
	}
	
}
