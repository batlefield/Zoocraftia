package net.zoocraftia.dimension;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ZoocraftiaDimensionTickPlayer implements ITickHandler{

	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}

	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		ZoocraftiaDimensionMain.proxy.onTick(type, tickData);
	}

	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.PLAYER);
	}

	public String getLabel() {
		return null;
	}

}
