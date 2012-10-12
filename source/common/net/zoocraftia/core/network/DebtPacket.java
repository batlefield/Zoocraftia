package net.zoocraftia.core.network;

import java.util.List;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NetworkManager;
import net.zoocraftia.api.Zoocraftia;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.Player;

public class DebtPacket extends ZoocraftiaPacket{

	private int playerID;
	private int entityID;
	private int debt;
	private double interestRate;
	private boolean add = false;
	
	public DebtPacket() {
		super(Type.DEBT_PACKET);
	}

	@Override
	public byte[] generatePacket(Object... data) {
		ByteArrayDataOutput dat = ByteStreams.newDataOutput();
		dat.writeInt(((EntityPlayer)data[0]).entityId);
		dat.writeInt(((EntityLiving)data[1]).entityId);
		dat.writeInt((Integer) data[2]);
		dat.writeUTF((String) data[3]);
		if(data[3].equals("remove"))
		{
			dat.writeDouble((Double) data[4]);
		}
		return dat.toByteArray();
	}

	@Override
	public ZoocraftiaPacket consumePacket(byte[] data) {
		ByteArrayDataInput dat = ByteStreams.newDataInput(data);
		playerID = dat.readInt();
		entityID = dat.readInt();
		debt = dat.readInt();
		String s = dat.readUTF();
		if(s.equals("add"))
		{
			add = true;
		}
		
		if(!add)
		{
			interestRate = dat.readDouble();
		}
		
		return this;
		
	}

	@Override
	public void execute(NetworkManager network, Player player) {
		if(((EntityPlayer)player).entityId != playerID)
		{
			return;
		}
		
		if(add)
		{
			List<Entity> list = ((EntityPlayer)player).worldObj.loadedEntityList;
			for(Entity ent : list)
			{
				if(ent.entityId == entityID)
				{
					Zoocraftia.MONEY_MANAGER.addDebt((EntityPlayer)player, (EntityLiving)ent, debt);
					break;
				}
			}
		}else{
			List<Entity> list = ((EntityPlayer)player).worldObj.loadedEntityList;
			for(Entity ent : list)
			{
				if(ent.entityId == entityID)
				{
					Zoocraftia.MONEY_MANAGER.removeDebt((EntityPlayer)player, (EntityLiving)ent, debt, interestRate);
					break;
				}
			}
		}
	}

}
