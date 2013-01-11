package net.zoocraftia.core.listeners;

import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingSpecialSpawnEvent;
import net.zoocraftia.api.BaseEntity;

public class EntitySpawnListener {

	@ForgeSubscribe
	public void entitySpanwnEvent(LivingSpecialSpawnEvent event)
	{
		if(event.entityLiving instanceof BaseEntity)
		{
			((BaseEntity)event.entityLiving).setSexRandomly();
			event.setResult(Result.ALLOW);
		}
	}
	
}
