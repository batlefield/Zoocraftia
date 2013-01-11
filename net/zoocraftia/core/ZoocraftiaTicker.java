package net.zoocraftia.core;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.modloader.BaseModProxy;

public class ZoocraftiaTicker implements ITickHandler{

	private ZoocraftiaMain mod;
    private EnumSet<TickType> ticks;
    private boolean clockTickTrigger;
    private boolean sendGuiTicks;


    ZoocraftiaTicker(ZoocraftiaMain zc, boolean guiTicker)
    {
        this.mod = zc;
        this.ticks = EnumSet.of(TickType.WORLDLOAD, TickType.RENDER);
        this.sendGuiTicks = guiTicker;
    }
    
    public final boolean doTickInGame(TickType tick, boolean tickEnd, Object... data)
    {
        Minecraft mc = FMLClientHandler.instance().getClient();
        boolean hasWorld = mc.theWorld != null;
        // World and render ticks
        if (tickEnd && ( tick==TickType.RENDER || tick==TickType.CLIENT ) && hasWorld) {
            return mod.proxy.clientTick((Float) data[0], mc);
        }
        return true;
    }

    public final boolean doTickInGUI(TickType tick, boolean tickEnd, Object... data)
    {
        Minecraft mc = FMLClientHandler.instance().getClient();

        boolean hasWorld = mc.theWorld != null;

        if (tickEnd && ( tick==TickType.RENDER || ( tick==TickType.CLIENT && hasWorld))) {
            //return mod.onTickInGUI((Float) data[0], mc, mc.currentScreen);
        }
        return true;
    }
	
	@Override
    public void tickStart(EnumSet<TickType> types, Object... tickData)
    {
        tickBaseMod(types, false, tickData);
    }

    @Override
    public void tickEnd(EnumSet<TickType> types, Object... tickData)
    {
        tickBaseMod(types, true, tickData);
    }

    private void tickBaseMod(EnumSet<TickType> types, boolean end, Object... tickData)
    {
        if (FMLCommonHandler.instance().getSide().isClient() && ( ticks.contains(TickType.CLIENT) || ticks.contains(TickType.WORLDLOAD)))
        {
            EnumSet cTypes=EnumSet.copyOf(types);
            if ( ( end && types.contains(TickType.CLIENT)) || types.contains(TickType.WORLDLOAD))
            {
                clockTickTrigger =  true;
                cTypes.remove(TickType.CLIENT);
                cTypes.remove(TickType.WORLDLOAD);
            }

            if (end && clockTickTrigger && types.contains(TickType.RENDER))
            {
                clockTickTrigger = false;
                cTypes.remove(TickType.RENDER);
                cTypes.add(TickType.CLIENT);
            }

            sendTick(cTypes, end, tickData);
        }
        else
        {
            sendTick(types, end, tickData);
        }
    }

    private void sendTick(EnumSet<TickType> types, boolean end, Object... tickData)
    {
        for (TickType type : types)
        {
            if (!ticks.contains(type))
            {
                continue;
            }

            boolean keepTicking=true;
            if (sendGuiTicks)
            {
                keepTicking = doTickInGUI(type, end, tickData);
            }
            else
            {
                keepTicking = doTickInGame(type, end, tickData);
            }
            if (!keepTicking) {
                ticks.remove(type);
                ticks.removeAll(type.partnerTicks());
            }
        }
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return (clockTickTrigger ? EnumSet.of(TickType.RENDER) : ticks);
    }

    @Override
    public String getLabel()
    {
        return mod.getClass().getSimpleName();
    }

}
