package net.zoocraftia.core.network;

import java.util.ArrayList;
import java.util.Map.Entry;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.INetworkManager;
import net.zoocraftia.api.Zoocraftia;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.Player;

public class TagPacket extends ZoocraftiaPacket{

	public TagPacket() {
		super(Type.TAG_PACKET);
	}

	public ArrayList<String> tagged = new ArrayList<String>();
	
	@Override
	public byte[] generatePacket(Object... data) {
		ByteArrayDataOutput dat = ByteStreams.newDataOutput();
		dat.writeInt(((ArrayList<String>)data[0]).size());
		for(String s : (ArrayList<String>)data[0])
		{
			for(Object o : EntityList.IDtoClassMapping.entrySet())
			{
				Entry<Integer, Class<? extends Entity>> e = (Entry<Integer, Class<? extends Entity>>)o;
				if(EntityList.stringToClassMapping.get(s).equals(e.getValue()))
				{
					dat.writeInt(e.getKey());
					break;
				}
			}
		}
		return dat.toByteArray();
	}

	@Override
	public ZoocraftiaPacket consumePacket(byte[] data) {
		ByteArrayDataInput dat = ByteStreams.newDataInput(data);
		int size = dat.readInt();
		for(int i = 0; i < size; i++)
		{
			int entityID = dat.readInt();
			tagged.add(EntityList.getStringFromID(entityID));
		}
		return this;
	}

	@Override
	public void execute(INetworkManager network, Player player) {
		Zoocraftia.TAGGED_MANAGER.setTagged((EntityPlayer) player, tagged);
	}

}
