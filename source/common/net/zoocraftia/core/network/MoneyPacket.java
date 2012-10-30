package net.zoocraftia.core.network;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.INetworkManager;
import net.zoocraftia.api.Zoocraftia;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.Player;

public class MoneyPacket extends ZoocraftiaPacket{

	private int money;
	
	public MoneyPacket() {
		super(Type.MONEY_PACKET);
	}

	@Override
	public byte[] generatePacket(Object... data) {
		ByteArrayDataOutput dat = ByteStreams.newDataOutput();
		dat.writeInt((Integer) data[0]);
		return dat.toByteArray();
	}

	@Override
	public ZoocraftiaPacket consumePacket(byte[] data) {
		ByteArrayDataInput dat = ByteStreams.newDataInput(data);
		money = dat.readInt();
		return this;
		
	}

	@Override
	public void execute(INetworkManager network, Player player) {
		EntityPlayer player1 = (EntityPlayer) player;
		Zoocraftia.MONEY_MANAGER.setMoney(player1, money);
	}

}
