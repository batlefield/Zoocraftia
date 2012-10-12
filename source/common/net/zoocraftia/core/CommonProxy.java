package net.zoocraftia.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.zoocraftia.api.BaseEntity;
import net.zoocraftia.api.Zoocraftia;
import net.zoocraftia.core.MinecraftPlayerState.PlayerState;
import net.zoocraftia.core.MoneyHandler.Debt;
import net.zoocraftia.core.TaggedEntityManager.PlayerTagEntry;
import net.zoocraftia.core.client.GuiEntity;
import net.zoocraftia.core.network.ZoocraftiaPacket;
import net.zoocraftia.core.network.ZoocraftiaPacket.Type;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler{

	public MoneyManager MONEY_MANAGER;
	public TaggedEntityManager TAGGED_MANAGER;
	
	public void initialize()
	{
		MONEY_MANAGER = new MoneyManager();
		TAGGED_MANAGER = new TaggedEntityManager();
	}
	
	public void postInitialize(){}
	
	public void handleTagSet(EntityPlayer player, ArrayList<String> list)
	{
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = ZoocraftiaMain.CHANNEL_NAME;
		packet.data = ZoocraftiaPacket.makePacket(Type.TAG_PACKET, list);
		packet.length = packet.data.length;
		
		if(MinecraftPlayerState.getPlayerState(player) == PlayerState.SMP)
		{
			((EntityPlayerMP)player).serverForThisPlayer.sendPacketToPlayer(packet);
		}
	}
	
	public void handleTagAdd(EntityPlayer player, EntityLiving tagged)
	{
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = ZoocraftiaMain.CHANNEL_NAME;
		packet.data = ZoocraftiaPacket.makePacket(Type.TAG_PACKET, ((PlayerTagEntry)TAGGED_MANAGER.getEntry(player)).taggedEntities);
		packet.length = packet.data.length;
		
		if(MinecraftPlayerState.getPlayerState(player) == PlayerState.SMP)
		{
			((EntityPlayerMP)player).serverForThisPlayer.sendPacketToPlayer(packet);
		}
	}
	
	public void handleDebtSet(EntityPlayer player, ArrayList<Debt> debts)
	{
		for(Debt debt : debts)
		{
			Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.channel = ZoocraftiaMain.CHANNEL_NAME;
			packet.data = ZoocraftiaPacket.makePacket(Type.DEBT_PACKET, player, debt.debtOwner, debt.debt, "add");
			packet.length = packet.data.length;
			
			if(MinecraftPlayerState.getPlayerState(player) == PlayerState.SMP)
			{
				((EntityPlayerMP)player).serverForThisPlayer.sendPacketToPlayer(packet);
			}
		}
	}
	
	public void handleMoneySet(EntityPlayer player, int i)
	{
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = ZoocraftiaMain.CHANNEL_NAME;
		packet.data = ZoocraftiaPacket.makePacket(Type.MONEY_PACKET, ((MoneyHandler)MONEY_MANAGER.getEntry(player)).money);
		packet.length = packet.data.length;

		if(MinecraftPlayerState.getPlayerState(player) == PlayerState.SMP)
		{
			((EntityPlayerMP)player).serverForThisPlayer.sendPacketToPlayer(packet);
		}
	}
	
	public void handleMoneyAdd(EntityPlayer player, int i)
	{
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = ZoocraftiaMain.CHANNEL_NAME;
		packet.data = ZoocraftiaPacket.makePacket(Type.MONEY_PACKET, ((MoneyHandler)MONEY_MANAGER.getEntry(player)).money);
		packet.length = packet.data.length;

		if(MinecraftPlayerState.getPlayerState(player) == PlayerState.SMP)
		{
			((EntityPlayerMP)player).serverForThisPlayer.sendPacketToPlayer(packet);
		}
	}
	
	public void handleDebtAdd(EntityPlayer player, EntityLiving debtOwner, int debt)
	{
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = ZoocraftiaMain.CHANNEL_NAME;
		packet.data = ZoocraftiaPacket.makePacket(Type.DEBT_PACKET, player, debtOwner, debt, "add");
		packet.length = packet.data.length;
		if(MinecraftPlayerState.getPlayerState(player) == PlayerState.SMP)
		{
			((EntityPlayerMP)player).serverForThisPlayer.sendPacketToPlayer(packet);
		}
	}
	
	public void handleDebtRemove(EntityPlayer player, EntityLiving debtOwner, int debt, double interestRate)
	{
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = ZoocraftiaMain.CHANNEL_NAME;
		packet.data = ZoocraftiaPacket.makePacket(Type.DEBT_PACKET, player, debtOwner, debt, "remove", interestRate);
		packet.length = packet.data.length;
		if(MinecraftPlayerState.getPlayerState(player) == PlayerState.SMP)
		{
			((EntityPlayerMP)player).serverForThisPlayer.sendPacketToPlayer(packet);
		}
	}
	
	public World getClientWorld() {
		return null;
	}
	
	//GUI HANDLING
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileentity = null;
		BaseEntity entity = null;
		if(!isEntityGUI(ID) && !isItemGUI(ID))
		{
	        if (!world.blockExists(x, y, z))
	        {
	            return null;
	        }
		}else if(isEntityGUI(ID)){
			List<Entity> list = player.worldObj.loadedEntityList;
			for(Entity ent : list)
			{
				if(ent instanceof BaseEntity && ent.entityId == x)
				{
					entity = (BaseEntity) ent;
					break;
				}
			}
		}

        tileentity = world.getBlockTileEntity(x, y, z);
        
        if(ID == Zoocraftia.getGuiId(GuiEntity.class))
        {
        	return new GuiEntity(player, entity);
        }
		return null;
	}
	
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileentity = null;
		BaseEntity entity = null;
		if(!isEntityGUI(ID) && !isItemGUI(ID))
		{
	        if (!world.blockExists(x, y, z))
	        {
	            return null;
	        }
		}else if(isEntityGUI(ID)){
			List<Entity> list = player.worldObj.loadedEntityList;
			for(Entity ent : list)
			{
				if(ent instanceof BaseEntity && ent.entityId == x)
				{
					entity = (BaseEntity) ent;
					break;
				}
			}
		}

        tileentity = world.getBlockTileEntity(x, y, z);
        
        /*if(ID == Zoocraftia.getGuiId(GuiEntity.class))
        {
        	return new GuiEntity(player, entity);
        }*/
		return null;
	}

	public void versionNotify()
	{
		MinecraftServer.getServer().logger.warning("New zoocraftia version available (" + ZoocraftiaMain.newVersion + ")");
	}
	
	public boolean isItemGUI(int id)
	{
		return false;
	}
	
	public boolean isEntityGUI(int id)
	{
		return id == Zoocraftia.getGuiId(GuiEntity.class);
	}
	
	public File getLocation()
	{
		return new File(".");
	}
	
	public void triggerKeyEvent(KeyBinding key){}
	
	public boolean clientTick(float time, Minecraft minecraftInstance)
	{
		return false;
	}

}
