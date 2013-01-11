package net.zoocraftia.client.core.listeners;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;
import net.zoocraftia.client.core.helpers.KeyBindingHelper;
import net.zoocraftia.core.ZoocraftiaMain;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class KeyListener extends KeyBindingRegistry.KeyHandler{
	
	public KeyListener() {
		super(KeyBindingHelper.gatherKeyBindings(), KeyBindingHelper.gatherIsRepeating());
	}

	@Override
    public void keyDown(EnumSet<TickType> type, KeyBinding kb, boolean end, boolean repeats)
    {
        if(end)
        {
        	ZoocraftiaMain.proxy.triggerKeyEvent(kb);
        }
    }

    @Override
    public void keyUp(EnumSet<TickType> type, KeyBinding kb, boolean end){}

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel()
    {
        return "Zoocraftia";
    }
    
    private static String getLocalizedKey(String key) {
    	return LanguageRegistry.instance().getStringLocalization(key);
    }
}
