package net.zoocraftia.core;

import java.io.File;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.zoocraftia.api.Zoocraftia;
import net.zoocraftia.client.core.gui.GuiEntity;
import net.zoocraftia.core.MinecraftPlayerState.PlayerState;
import net.zoocraftia.core.MoneyHandler.Debt;
import net.zoocraftia.core.TaggedEntityManager.PlayerTagEntry;
import net.zoocraftia.core.network.ZoocraftiaPacket;
import net.zoocraftia.core.network.ZoocraftiaPacket.Type;

public class CommonProxy
{

	public MoneyManager MONEY_MANAGER;
	public TaggedEntityManager TAGGED_MANAGER;

	public void registerKeyBindingHandler()
	{
	}

	public void setKeyBinding(String name, int value)
	{
	}

	public void initialize()
	{
		MONEY_MANAGER = new MoneyManager();
		TAGGED_MANAGER = new TaggedEntityManager();
	}

	public void postInitialize()
	{
	}

	public void handleTagSet(EntityPlayer player, ArrayList<String> list)
	{
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = ZoocraftiaMain.CHANNEL_NAME;
		packet.data = ZoocraftiaPacket.makePacket(Type.TAG_PACKET, list);
		packet.length = packet.data.length;

		if (MinecraftPlayerState.getPlayerState(player) == PlayerState.SMP)
		{
			((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(packet);
		}
	}

	public void handleTagAdd(EntityPlayer player, EntityLiving tagged)
	{
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = ZoocraftiaMain.CHANNEL_NAME;
		packet.data = ZoocraftiaPacket.makePacket(Type.TAG_PACKET, ((PlayerTagEntry) TAGGED_MANAGER.getEntry(player)).taggedEntities);
		packet.length = packet.data.length;

		if (MinecraftPlayerState.getPlayerState(player) == PlayerState.SMP)
		{
			((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(packet);
		}
	}

	public void handleDebtSet(EntityPlayer player, ArrayList<Debt> debts)
	{
		for (Debt debt : debts)
		{
			Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.channel = ZoocraftiaMain.CHANNEL_NAME;
			packet.data = ZoocraftiaPacket.makePacket(Type.DEBT_PACKET, player, debt.debtOwner, debt.debt, "add");
			packet.length = packet.data.length;

			if (MinecraftPlayerState.getPlayerState(player) == PlayerState.SMP)
			{
				((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(packet);
			}
		}
	}

	public void handleMoneySet(EntityPlayer player, int i)
	{
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = ZoocraftiaMain.CHANNEL_NAME;
		packet.data = ZoocraftiaPacket.makePacket(Type.MONEY_PACKET, ((MoneyHandler) MONEY_MANAGER.getEntry(player)).money);
		packet.length = packet.data.length;

		if (MinecraftPlayerState.getPlayerState(player) == PlayerState.SMP)
		{
			((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(packet);
		}
	}

	public void handleMoneyAdd(EntityPlayer player, int i)
	{
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = ZoocraftiaMain.CHANNEL_NAME;
		packet.data = ZoocraftiaPacket.makePacket(Type.MONEY_PACKET, ((MoneyHandler) MONEY_MANAGER.getEntry(player)).money);
		packet.length = packet.data.length;

		if (MinecraftPlayerState.getPlayerState(player) == PlayerState.SMP)
		{
			((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(packet);
		}
	}

	public void handleDebtAdd(EntityPlayer player, EntityLiving debtOwner, int debt)
	{
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = ZoocraftiaMain.CHANNEL_NAME;
		packet.data = ZoocraftiaPacket.makePacket(Type.DEBT_PACKET, player, debtOwner, debt, "add");
		packet.length = packet.data.length;
		if (MinecraftPlayerState.getPlayerState(player) == PlayerState.SMP)
		{
			((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(packet);
		}
	}

	public void handleDebtRemove(EntityPlayer player, EntityLiving debtOwner, int debt, double interestRate)
	{
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = ZoocraftiaMain.CHANNEL_NAME;
		packet.data = ZoocraftiaPacket.makePacket(Type.DEBT_PACKET, player, debtOwner, debt, "remove", interestRate);
		packet.length = packet.data.length;
		if (MinecraftPlayerState.getPlayerState(player) == PlayerState.SMP)
		{
			((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(packet);
		}
	}

	public World getClientWorld()
	{
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

	public void triggerKeyEvent(KeyBinding key)
	{
	}

	public boolean clientTick(float time, Minecraft minecraftInstance)
	{
		return false;
	}

}
