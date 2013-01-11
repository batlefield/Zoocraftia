package net.zoocraftia.client.core.helpers;

import java.util.ArrayList;

import net.minecraft.client.settings.KeyBinding;

public class KeyBindingHelper {

	public static ArrayList<KeyBinding> keyBindingList;
	public static ArrayList<Boolean> isRepeatingList;
	
	public static void addKeyBinding(String name, int value)
	{
		if(keyBindingList == null)
		{
			keyBindingList = new ArrayList<KeyBinding>();
		}
		keyBindingList.add(new KeyBinding(name, value));
	}
	
	public static void addIsRepeating(boolean val)
	{
		if(isRepeatingList == null)
		{
			isRepeatingList = new ArrayList<Boolean>();
		}
		isRepeatingList.add(val);
	}
	
	public static KeyBinding[] gatherKeyBindings()
	{
		return keyBindingList.toArray(new KeyBinding[keyBindingList.size()]);
	}
	
	public static boolean[] gatherIsRepeating()
	{
		boolean[] isRepeating = new boolean[isRepeatingList.size()];
		
		for(int i = 0; i < isRepeating.length; i++)
		{
			isRepeating[i] = isRepeatingList.get(i).booleanValue();
		}
		
		return isRepeating;
	}
	
}
