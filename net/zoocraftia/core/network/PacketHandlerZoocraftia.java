package net.zoocraftia.core.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.zoocraftia.core.ZoocraftiaMain;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandlerZoocraftia implements IPacketHandler, IConnectionHandler{

	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
		ZoocraftiaMain.proxy.MONEY_MANAGER.loadPlayerMain((EntityPlayer) player);
	}

	public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) {
		return null;
	}

	public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {
		
	}

	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {
		
	}

	public void connectionClosed(INetworkManager manager) {
		
	}

	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {
		
	}

	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		String channel = packet.channel;
		if(channel.equals(ZoocraftiaMain.CHANNEL_NAME))
		{
			ZoocraftiaPacket zcPacket = ZoocraftiaPacket.readPacket(packet.data);
			zcPacket.executePacket(manager, player);
		}
	}

}
