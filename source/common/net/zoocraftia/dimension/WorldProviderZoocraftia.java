package net.zoocraftia.dimension;

import net.minecraft.src.ChunkProviderGenerate;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.WorldChunkManager;
import net.minecraft.src.WorldProvider;
import net.minecraft.src.WorldProviderEnd;
import net.minecraft.src.WorldProviderHell;

public class WorldProviderZoocraftia extends WorldProvider
{
    public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new ZoocraftiaChunkManager(this.worldObj);
    }
    
    public String getWelcomeMessage()
    {
        return "Entering Zoocraftia";
    }

    /**
     * A Message to display to the user when they transfer out of this dismension.
     *
     * @return The message to be displayed
     */
    public String getDepartMessage()
    {
        return "Leaving zoocraftia";
    }
    
    public IChunkProvider getChunkProvider()
    {
        return new ChunkProviderZoocraftia(this.worldObj, this.worldObj.getSeed(), true);
    }

    public String func_80007_l()
    {
        return "Zoocraftia";
    }
}
