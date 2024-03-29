package net.zoocraftia.core;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.zoocraftia.client.core.ClientProxy;

public class MinecraftPlayerState {

	public static boolean isClient()
	{
		try{
			boolean ok = false;
			try{
				Minecraft.getMinecraft();
				ok = true;
			}catch(Exception e){return false;}
			
			return ZoocraftiaMain.proxy instanceof ClientProxy && ok && Minecraft.getMinecraft().isSingleplayer() && !Minecraft.getMinecraft().getIntegratedServer().getPublic();
		}catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean isLan()
	{
		try{
			boolean ok = false;
			try{
				Minecraft.getMinecraft();
				ok = true;
			}catch(Exception e){return false;}
			
			try{
				return ZoocraftiaMain.proxy instanceof ClientProxy && ok && !Minecraft.getMinecraft().isSingleplayer() && Minecraft.getMinecraft().getIntegratedServer().getPublic();
			}catch(Exception e)
			{
				return false;
			}
		}catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean isServer()
	{
		boolean ok;
		try{
			Minecraft.getMinecraft();
			ok = false;
		}catch(Exception e){ok = true;}
		
		return ok && !(ZoocraftiaMain.proxy instanceof ClientProxy);
	}
	
	public static PlayerState getPlayerState(EntityPlayer player)
	{
		if(player instanceof EntityPlayerMP)
		{
			return PlayerState.SMP;
		}else if(player instanceof EntityOtherPlayerMP)
		{
			return PlayerState.OTHER;
		}else if(player instanceof EntityClientPlayerMP)
		{
			return PlayerState.SSP;
		}else{
			return null;
		}
	}
	
	public static enum PlayerState{
		SMP,
		SSP,
		OTHER;
	}
	
}
