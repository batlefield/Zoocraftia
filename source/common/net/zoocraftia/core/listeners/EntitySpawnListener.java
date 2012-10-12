package net.zoocraftia.core.listeners;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.World;
import net.minecraftforge.event.EventPriority;
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
			event.setHandeled();
		}
	}
	
}
