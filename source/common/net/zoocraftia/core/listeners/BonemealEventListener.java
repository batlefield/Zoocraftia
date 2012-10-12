package net.zoocraftia.core.listeners;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.zoocraftia.core.BlockSapling;
import net.zoocraftia.core.ZoocraftiaBlocks;

public class BonemealEventListener {

	@ForgeSubscribe
	public void bonemealTrigger(BonemealEvent event)
	{
		if(!event.world.isRemote)
		{
			((BlockSapling)ZoocraftiaBlocks.sapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			event.setHandeled();
		}
	}
	
}
