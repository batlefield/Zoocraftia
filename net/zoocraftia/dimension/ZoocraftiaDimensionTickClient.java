package net.zoocraftia.dimension;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ZoocraftiaDimensionTickClient implements ITickHandler{

	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}

	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		ZoocraftiaDimensionMain.proxy.onTick(type, tickData);
	}

	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER);
	}

	public String getLabel() {
		return null;
	}

}
