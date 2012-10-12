package net.zoocraftia.core.network;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NetHandler;
import net.minecraft.src.NetLoginHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.Packet250CustomPayload;
import net.zoocraftia.api.Zoocraftia;
import net.zoocraftia.core.PerPlayerBase;
import net.zoocraftia.core.ZoocraftiaMain;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandlerZoocraftia implements IPacketHandler, IConnectionHandler{

	public void playerLoggedIn(Player player, NetHandler netHandler, NetworkManager manager) {
		ZoocraftiaMain.proxy.MONEY_MANAGER.loadPlayerMain((EntityPlayer) player);
	}

	public String connectionReceived(NetLoginHandler netHandler, NetworkManager manager) {
		return null;
	}

	public void connectionOpened(NetHandler netClientHandler, String server, int port, NetworkManager manager) {
		
	}

	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, NetworkManager manager) {
		
	}

	public void connectionClosed(NetworkManager manager) {
		
	}

	public void clientLoggedIn(NetHandler clientHandler, NetworkManager manager, Packet1Login login) {
		
	}

	public void onPacketData(NetworkManager manager, Packet250CustomPayload packet, Player player) {
		String channel = packet.channel;
		if(channel.equals(ZoocraftiaMain.CHANNEL_NAME))
		{
			ZoocraftiaPacket zcPacket = ZoocraftiaPacket.readPacket(packet.data);
			zcPacket.execute(manager, player);
		}
	}

}
