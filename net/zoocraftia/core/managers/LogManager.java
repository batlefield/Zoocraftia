package net.zoocraftia.core.managers;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class LogManager {

	private static Logger logger = Logger.getLogger("Zoocraftia");
	
	public static void init()
	{
		logger.setParent(FMLLog.getLogger());
	}
	
	public static void log(Level logLevel, String msg)
	{
		logger.log(logLevel, msg);
	}
	
}
