package net.zoocraftia.dimension;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderZoocraftia extends WorldProvider
{
	public WorldProviderZoocraftia()
	{
		setDimension(ZoocraftiaDimensionMain.dimensionID);
	}
	
    public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new ZoocraftiaChunkManager(this.worldObj);
    }
    
    public IChunkProvider getChunkProvider()
    {
        return new ChunkProviderZoocraftia(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled());
    }
    
    public String getWelcomeMessage()
    {
        return "Entering Zoocraftia";
    }
    
    public String getDepartMessage()
    {
        return "Leaving zoocraftia";
    }

    public String getSaveFolder()
    {
    	return "DIM-ZOO";
    }
    
    public String getDimensionName()
    {
        return "Zoocraftia";
    }

}
