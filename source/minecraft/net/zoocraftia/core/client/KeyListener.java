package net.zoocraftia.core.client;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import com.google.common.primitives.Booleans;

import net.minecraft.src.KeyBinding;
import net.zoocraftia.core.ZoocraftiaMain;
import cpw.mods.fml.client.registry.*;
import cpw.mods.fml.common.TickType;

public class KeyListener extends KeyBindingRegistry.KeyHandler{

    private List<KeyBinding> helper;
    private boolean[] active = new boolean[0];
    private boolean[] mlRepeats = new boolean[0];
    private boolean[] armed = new boolean[0];
	
	public KeyListener(KeyBinding[] keyBindings, boolean [] repeats) {
		super(keyBindings, repeats);
		this.active = new boolean[this.keyBindings.length];
        this.armed = new boolean[this.keyBindings.length];
        this.mlRepeats = Booleans.concat(this.mlRepeats, new boolean[] { false });
        this.keyDown = new boolean[this.keyBindings.length];
        this.helper = Arrays.asList(this.keyBindings);
	}

	@Override
    public void keyDown(EnumSet<TickType> type, KeyBinding kb, boolean end, boolean repeats)
    {
        if (!end)
        {
            return;
        }
        int idx = helper.indexOf(kb);
        if (type.contains(TickType.CLIENT))
        {
            armed[idx] = true;
        }
        if (armed[idx] && type.contains(TickType.RENDER) && (!active[idx] || mlRepeats[idx]))
        {
            ZoocraftiaMain.proxy.triggerKeyEvent(kb);
            active[idx] = true;
            armed[idx] = false;
        }
    }

    @Override
    public void keyUp(EnumSet<TickType> type, KeyBinding kb, boolean end)
    {
        if (!end)
        {
            return;
        }
        int idx = helper.indexOf(kb);
        active[idx] = false;
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.CLIENT, TickType.RENDER);
    }

    @Override
    public String getLabel()
    {
        return "Zoocraftia";
    }
}
