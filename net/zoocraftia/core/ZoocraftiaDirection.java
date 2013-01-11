package net.zoocraftia.core;

public class ZoocraftiaDirection
{
	
	int EAST = 3;
	int WEST = 1;
	int SOUTH = 0;
	int NORTH = 2;
	
	public static int getOpposite(int direction)
	{
		int ret = direction + 2;
		if(ret >= 4)
		{
			ret = direction - 2;
		}
		
		return ret;
	}
}
