package net.zoocraftia.core.listeners;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.zoocraftia.core.ZoocraftiaBlocks;
import net.zoocraftia.core.ZoocraftiaItems;

public class FillBucketListener {

	@ForgeSubscribe
    public void onBucketFill(FillBucketEvent event){
    	
    	ItemStack result = fillCustomBucket(event.world, event.target, event.entityPlayer);
    	
    	if(result == null)
    	{
    		return;
    	}

    	event.result=result;
    	event.setResult(Result.ALLOW);
    	
    }
    
	public ItemStack fillCustomBucket(World world, MovingObjectPosition pos, EntityPlayer player) {

		int blockID = world.getBlockId(pos.blockX,pos.blockY,pos.blockZ);

		if((blockID == ZoocraftiaBlocks.saltwaterStill.blockID || blockID == ZoocraftiaBlocks.saltwaterMoving.blockID)
				&& world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) == 0) {

			world.setBlockWithNotify(pos.blockX, pos.blockY, pos.blockZ, 0);

			if(!player.capabilities.isCreativeMode)
			{
				return new ItemStack(ZoocraftiaItems.saltwaterBucket);
			}else{
				return null;
			}
		} else
			return null;

	}
	
}
