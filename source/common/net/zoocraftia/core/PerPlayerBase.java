package net.zoocraftia.core;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.src.EntityPlayer;

public abstract class PerPlayerBase {

	private ArrayList<EntityPlayer> loadedPlayers = new ArrayList<EntityPlayer>();
	private HashMap playerToObject = new HashMap<EntityPlayer, Object>();
	
	public void loadPlayerMain(EntityPlayer player)
	{
		loadedPlayers.add(player);
		loadPlayer(player);
	}
	
	public void unloadPlayerMain(EntityPlayer player)
	{
		loadedPlayers.remove(player);
		unloadPlayer(player);
	}
	
	public void addEntry(EntityPlayer player, Object o)
	{
		playerToObject.put(player, o);
	}
	
	public Object getEntry(EntityPlayer player)
	{
		return playerToObject.get(player);
	}
	
	public Object removeEntry(EntityPlayer player)
	{
		return playerToObject.remove(player);
	}
	
	public void loadPlayer(EntityPlayer player){}
	public void unloadPlayer(EntityPlayer player){}
	
	public void trigger(EntityPlayer player)
	{
		if(!loadedPlayers.contains(player))
		{
			loadPlayerMain(player);
		}
	}
	
}
