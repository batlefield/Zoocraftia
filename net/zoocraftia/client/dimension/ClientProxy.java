package net.zoocraftia.client.dimension;

import java.io.File;
import java.util.EnumSet;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.zoocraftia.dimension.CommonProxy;
import net.zoocraftia.dimension.ZoocraftiaBlocks;
import net.zoocraftia.dimension.ZoocraftiaDimensionMain;
import net.zoocraftia.dimension.ZoocraftiaDimensionTickClient;
import net.zoocraftia.dimension.ZoocraftiaDimensionTickPlayer;
import net.zoocraftia.dimension.ZoocraftiaTeleporter;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy{

	public File getLocation()
	{
		return Minecraft.getMinecraftDir();
	}
	
	public void preInit() {
		TickRegistry.registerTickHandler(new ZoocraftiaDimensionTickClient(), Side.CLIENT);
		TickRegistry.registerTickHandler(new ZoocraftiaDimensionTickPlayer(), Side.CLIENT);
		MinecraftForgeClient.preloadTexture("/zoocraftia/dimension/blocks.png");
		MinecraftForgeClient.preloadTexture("/zoocraftia/dimension/items.png");
	}
	
	public void postInit() {
		Minecraft.getMinecraft().renderEngine.registerTextureFX(new PortalFX());
	}
	
	public void onTick(EnumSet<TickType> ticks, Object... data)
	{
		Minecraft mc = FMLClientHandler.instance().getClient();
		if(ticks.contains(TickType.PLAYER) && mc.theWorld != null)
		{
			EntityPlayer player = (EntityPlayer) data[0];
			if(!playersInPortal.contains(player))
			{
				return;
			}
			
			if(!isInsideOfBlock(player, ZoocraftiaBlocks.portal))
			{
				playersInPortal.remove(player);
				player.timeUntilPortal = 0;
				return;
			}
			
			player = playerToUsername.get(player.username);
			player.timeUntilPortal--;
			if(player.timeUntilPortal == 1)
			{
				IntegratedServer server = mc.getIntegratedServer();
				String[] names = server.getConfigurationManager().getAllUsernames();
				for(String s : names)
				{
					if(s.equalsIgnoreCase(player.username))
					{
						player = server.getConfigurationManager().getPlayerForUsername(s);
					}
				}
				
				if(player instanceof EntityPlayerMP)
				{
					MinecraftServer mcServer = ((EntityPlayerMP)player).mcServer;
					if(player.dimension == 0)
					{
						mcServer.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) player, ZoocraftiaDimensionMain.dimensionID, new ZoocraftiaTeleporter(mcServer.worldServerForDimension(ZoocraftiaDimensionMain.dimensionID)));
					}else if(player.dimension == ZoocraftiaDimensionMain.dimensionID)
					{
						mcServer.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) player, 0, new ZoocraftiaTeleporter(mcServer.worldServerForDimension(ZoocraftiaDimensionMain.dimensionID)));
					}else{
						return;
					}
				}
				player.timeUntilPortal = 0;
			}
			
		}else if(ticks.contains(TickType.RENDER))
		{
			float ptt = (Float) data[0];
			EntityPlayer player = mc.thePlayer;
			if(!playersInPortal.contains(player))
			{
				return;
			}
			
			if(!isInsideOfBlock(player, ZoocraftiaBlocks.portal))
			{
				playersInPortal.remove(player);
				return;
			}
			
			//renderPortalOverlay(mc, ptt);
		}
	}
	
	private void renderPortalOverlay(Minecraft mc, float ptt)
    {
		float par1 = mc.thePlayer.prevTimeInPortal + (mc.thePlayer.timeInPortal - mc.thePlayer.prevTimeInPortal) * ptt;
		ScaledResolution sr = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		
        if (par1 < 1.0F)
        {
            par1 *= par1;
            par1 *= par1;
            par1 = par1 * 0.8F + 0.2F;
        }

        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, par1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/terrain.png"));
        float var4 = (float)(Block.portal.blockIndexInTexture % 16) / 16.0F;
        float var5 = (float)(Block.portal.blockIndexInTexture / 16) / 16.0F;
        float var6 = (float)(Block.portal.blockIndexInTexture % 16 + 1) / 16.0F;
        float var7 = (float)(Block.portal.blockIndexInTexture / 16 + 1) / 16.0F;
        Tessellator var8 = Tessellator.instance;
        var8.startDrawingQuads();
        var8.addVertexWithUV(0.0D, (double)sr.getScaledHeight(), -90.0D, (double)var4, (double)var7);
        var8.addVertexWithUV((double)sr.getScaledWidth(), (double)sr.getScaledHeight(), -90.0D, (double)var6, (double)var7);
        var8.addVertexWithUV((double)sr.getScaledWidth(), 0.0D, -90.0D, (double)var6, (double)var5);
        var8.addVertexWithUV(0.0D, 0.0D, -90.0D, (double)var4, (double)var5);
        var8.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
	
	public void addEffect(String s, World world, double d1, double d2, double d3, double d4, double d5, double d6) {
		EntityFX efx = null;
		
		if(s == "portal")
		{
			efx = new EntityPortalFX(world, d1, d2, d3, d4, d5, d6);
		}
		
		if(efx != null)
		{
			Minecraft.getMinecraft().effectRenderer.addEffect(efx, null);
		}
	}
	
}
