package net.zoocraftia.core.network;

import java.util.Arrays;

import net.minecraft.src.NetHandler;
import net.minecraft.src.NetworkManager;

import com.google.common.primitives.Bytes;
import com.google.common.primitives.UnsignedBytes;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.common.network.FMLPacket;
import cpw.mods.fml.common.network.Player;

public abstract class ZoocraftiaPacket {

	public enum Type
    {
        
        MONEY_PACKET(MoneyPacket.class),
        DEBT_PACKET(DebtPacket.class),
        TAG_PACKET(TagPacket.class);
        

        private Class<? extends ZoocraftiaPacket> packetType;

        private Type(Class<? extends ZoocraftiaPacket> clazz)
        {
            this.packetType = clazz;
        }

        ZoocraftiaPacket make()
        {
            try
            {
                return this.packetType.newInstance();
            }
            catch (Exception e)
            {
            	throw new RuntimeException(e);
            }
        }
    }
	
	private Type type;
	
	public ZoocraftiaPacket(Type type)
	{
		this.type = type;
	}
	
	public static byte[] makePacket(Type type, Object... data)
    {
        byte[] packetData = type.make().generatePacket(data);
        return Bytes.concat(new byte[] { UnsignedBytes.checkedCast(type.ordinal()) }, packetData );
    }

    public static ZoocraftiaPacket readPacket(byte[] payload)
    {
        int type = UnsignedBytes.toInt(payload[0]);
        return Type.values()[type].make().consumePacket(Arrays.copyOfRange(payload, 1, payload.length));
    }

    public abstract byte[] generatePacket(Object... data);

    public abstract ZoocraftiaPacket consumePacket(byte[] data);

    public abstract void execute(NetworkManager network, Player player);
}
