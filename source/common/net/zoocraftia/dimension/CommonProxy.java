package net.zoocraftia.dimension;

import java.io.File;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

import net.minecraft.src.Block;
import net.minecraft.src.BlockFluid;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;
import net.minecraftforge.common.DimensionManager;
import net.zoocraftia.core.ZoocraftiaSaltwater;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;

public class CommonProxy {

	public ArrayList<EntityPlayer> playersInPortal = new ArrayList<EntityPlayer>();
	public HashMap<String, EntityPlayer> playerToUsername = new HashMap<String, EntityPlayer>();
	
	public void setInPortal(EntityPlayer player)
	{
		if(player.timeUntilPortal == 0)
		{
			player.timeUntilPortal = 40;
		}
		playersInPortal.add(player);
		playerToUsername.put(player.username, player);
	}
	
	public void onTick(EnumSet<TickType> ticks, Object... data)
	{
		if(ticks.contains(TickType.PLAYER))
		{
			EntityPlayer player = (EntityPlayer) data[0];
			if(!playersInPortal.contains(player))
			{
				return;
			}
			
			if(!isInsideOfBlock(player, ZoocraftiaBlocks.portal))
			{
				playersInPortal.remove(player);
				player.timeUntilPortal = 0;
				return;
			}
			
			player.timeUntilPortal--;
			if(player.timeUntilPortal <= 0)
			{
				if(player.dimension == 0)
				{
					((EntityPlayerMP)player).mcServer.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) player, ZoocraftiaDimensionMain.dimensionID, new ZoocraftiaTeleporter());
				}else if(player.dimension == ZoocraftiaDimensionMain.dimensionID)
				{
					((EntityPlayerMP)player).mcServer.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) player, 0, new ZoocraftiaTeleporter());
				}else{
					return;
				}
			}
			
		}
	}
	
	public File getLocation()
	{
		return new File(".");
	}

	public void preInit() {
		TickRegistry.registerTickHandler(new ZoocraftiaDimensionTickPlayer(), Side.SERVER);
	}
	
	public boolean isInsideOfBlock(EntityPlayer pl, Block bl)
    {
        double var2 = pl.posY + (double)pl.getEyeHeight();
        int var4 = MathHelper.floor_double(pl.posX);
        int var5 = MathHelper.floor_float((float)MathHelper.floor_double(var2));
        int var6 = MathHelper.floor_double(pl.posZ);
        int var7 = pl.worldObj.getBlockId(var4, var5, var6);

        if (var7 != 0 && var7 == bl.blockID)
        {
        	if(Block.blocksList[var7] instanceof ZoocraftiaSaltwater)
        	{
	            float var8 = BlockFluid.getFluidHeightPercent(pl.worldObj.getBlockMetadata(var4, var5, var6)) - 0.11111111F;
	            float var9 = (float)(var5 + 1) - var8;
	            return var2 < (double)var9;
        	}else{
        		return true;
        	}
        }
        else
        {
            return false;
        }
    }

	public void postInit() {}
	public void addEffect(String s, World world, double d1, double d2, double d3, double d4, double d5, double d6) {}
	
}
