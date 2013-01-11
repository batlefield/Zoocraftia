package net.zoocraftia.core.network;

import java.util.LinkedList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.zoocraftia.api.EntityEnums.Sex;
import net.zoocraftia.api.EntityMessages;
import net.zoocraftia.client.core.gui.GuiEntity;
import net.zoocraftia.core.ZoocraftiaMain;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.Player;

public class EntityGuiPacket extends ZoocraftiaPacket
{
	private int age;
	private int hunger;
	private Sex sex;
	private int health;
	private LinkedList<EntityMessages> messages = new LinkedList<EntityMessages>();
	private int maxHunger;
	private int maxHealth;
	private String name;

	public EntityGuiPacket()
	{
		super(Type.ENTITY_GUI_PACKET);
	}

	public byte[] writePacketData(Object... data)
	{
		ByteArrayDataOutput dat = ByteStreams.newDataOutput();
		dat.writeInt((Integer) data[0]);
		dat.writeByte(data[1] == Sex.MALE ? 0 : 1);
		dat.writeInt((Integer) data[2]);
		dat.writeInt((Integer) data[3]);
		dat.writeInt((Integer) data[5]);
		dat.writeInt((Integer) data[6]);
		dat.writeUTF((String) data[7]);

		byte[] b = new byte[127];
		int x = 0;
		for(EntityMessages message : (LinkedList<EntityMessages>)data[4])
		{
			b[x] = (byte) ((message.id << 3) | (message.getPriorirty() & 0x7));
		}
		
		dat.write(b);
		return dat.toByteArray();
	}

	public ZoocraftiaPacket readPacketData(byte[] data)
	{
		ByteArrayDataInput dat = ByteStreams.newDataInput(data);
		age = dat.readInt();
		sex = dat.readByte() == 0 ? Sex.MALE : Sex.FEMALE;
		hunger = dat.readInt();
		health = dat.readInt();
		maxHealth = dat.readInt();
		maxHunger = dat.readInt();
		name = dat.readUTF();
		
		byte[] b = new byte[127];
		
		dat.readFully(b);
		
		for(byte b1 : b)
		{
			EntityMessages message = EntityMessages.messages[b1 >> 3];
			message.setPriority(b1 & 0x7);
			messages.add(message);
		}
		
		return this;
	}

	public void executePacket(INetworkManager network, Player player)
	{
		EntityPlayer entityPlayer = (EntityPlayer) player;
		openGUI(entityPlayer, age, sex, hunger, health, messages, maxHealth, maxHunger, name);
	}
	
	public static void openGUI(EntityPlayer player, int age, Sex sex, int hunger, int health, LinkedList messages, int maxHealth, int maxHunger, String name)
	{
		if(player instanceof EntityPlayerMP)
		{
			openRemoteGui((EntityPlayerMP) player, age, sex, hunger, health, messages, maxHealth, maxHunger, name);
		}else{
			openLocalGui(player, age, sex, hunger, health, messages, maxHealth, maxHunger, name);
		}
	}
	
	static void openRemoteGui(EntityPlayerMP player, int age, Sex sex, int hunger, int health, LinkedList messages, int maxHealth, int maxHunger, String name)
    {
        Packet250CustomPayload pkt = new Packet250CustomPayload();
        pkt.channel = ZoocraftiaMain.CHANNEL_NAME;
        pkt.data = ZoocraftiaPacket.makePacket(Type.ENTITY_GUI_PACKET, age, sex, hunger, health, messages, maxHealth, maxHunger, name);
        pkt.length = pkt.data.length;
        player.playerNetServerHandler.sendPacketToPlayer(pkt);

    }
	
    static void openLocalGui(EntityPlayer player, int age, Sex sex, int hunger, int health, LinkedList messages, int maxHealth, int maxHunger, String name)
    {
        FMLCommonHandler.instance().showGuiScreen(new GuiEntity(player, age, sex, hunger, health, messages, maxHealth, maxHunger, name));
    }
	
}
