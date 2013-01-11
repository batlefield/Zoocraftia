package net.zoocraftia.core;

import net.zoocraftia.api.EntityEnums;
import net.zoocraftia.api.EntityEnums.EntityAction;
import net.zoocraftia.api.EntityEnums.EntityType;
import net.zoocraftia.api.EntityEnums.FoodType;

public class TypeHelper {

	public static EntityEnums.EntityAction getHungerAction(EntityEnums.EntityType type1, EntityEnums.EntityType type2, double hungerFactor, EntityEnums.FoodType foodType)
	{
		if(foodType == FoodType.HERBIVORE)
		{
			if(hungerFactor <= 0.1)
			{
				if((type1 == EntityType.NORMAL_PREY && type2 == EntityType.SMALL_PREY) || (type1 == EntityType.NORMAL_PREY && type2 == EntityType.SMALL_PREDATOR))
				{
					return EntityAction.ATTACK;
				}else if((type1 == EntityType.BIG_PREY && type2 == EntityType.SMALL_PREY) || (type1 == EntityType.BIG_PREY && type2 == EntityType.NORMAL_PREY) || (type1 == EntityType.BIG_PREY && type2 == EntityType.NORMAL_PREDATOR)|| (type1 == EntityType.BIG_PREY && type2 == EntityType.SMALL_PREDATOR))
				{
					return EntityAction.ATTACK;
				}else if((type1 == EntityType.SMALL_PREDATOR && type2 == EntityType.SMALL_PREDATOR) || (type1 == EntityType.NORMAL_PREDATOR && type2 == EntityType.NORMAL_PREDATOR) || (type1 == EntityType.BIG_PREDATOR && type2 == EntityType.BIG_PREDATOR))
				{
					return EntityAction.ATTACK;
				}else if((type1 == EntityType.SMALL_PREDATOR && type2 == EntityType.SMALL_PREY) || (type1 == EntityType.SMALL_PREDATOR && type2 == EntityType.NORMAL_PREY) || (type1 == EntityType.SMALL_PREDATOR && type2 == EntityType.SMALL_PREDATOR))
				{
					return EntityAction.ATTACK;
				}else if((type1 == EntityType.NORMAL_PREDATOR && type2 == EntityType.SMALL_PREY) || (type1 == EntityType.NORMAL_PREDATOR && type2 == EntityType.NORMAL_PREY) || (type1 == EntityType.NORMAL_PREDATOR && type2 == EntityType.BIG_PREY) || (type1 == EntityType.NORMAL_PREDATOR && type2 == EntityType.SMALL_PREDATOR) || (type1 == EntityType.NORMAL_PREDATOR && type2 == EntityType.NORMAL_PREDATOR))
				{
					return EntityAction.ATTACK;
				}else if(type1 == EntityType.BIG_PREDATOR)
				{
					return EntityAction.ATTACK;
				}else{
					return EntityAction.NOTHING;
				}
			}else{
				return EntityAction.EATPLANTS;
			}
		}else if(foodType == FoodType.CARNIVORE)
		{
			if(hungerFactor <= 0.1)
			{
				return EntityAction.ATTACK;
			}else if(hungerFactor <= 0.5)
			{
				if((type1 == EntityType.SMALL_PREY && type2 == EntityType.SMALL_PREY) || (type1 == EntityType.NORMAL_PREY && type2 == EntityType.NORMAL_PREY) || (type1 == EntityType.BIG_PREY && type2 == EntityType.BIG_PREY))
				{
					return EntityAction.ATTACK;
				}else if((type1 == EntityType.NORMAL_PREY && type2 == EntityType.SMALL_PREY) || (type1 == EntityType.NORMAL_PREY && type2 == EntityType.SMALL_PREDATOR))
				{
					return EntityAction.ATTACK;
				}else if((type1 == EntityType.BIG_PREY && type2 == EntityType.SMALL_PREY) || (type1 == EntityType.BIG_PREY && type2 == EntityType.NORMAL_PREY) || (type1 == EntityType.BIG_PREY && type2 == EntityType.NORMAL_PREDATOR)|| (type1 == EntityType.BIG_PREY && type2 == EntityType.SMALL_PREDATOR))
				{
					return EntityAction.ATTACK;
				}else if((type1 == EntityType.SMALL_PREDATOR && type2 == EntityType.SMALL_PREDATOR) || (type1 == EntityType.NORMAL_PREDATOR && type2 == EntityType.NORMAL_PREDATOR) || (type1 == EntityType.BIG_PREDATOR && type2 == EntityType.BIG_PREDATOR))
				{
					return EntityAction.ATTACK;
				}else if((type1 == EntityType.SMALL_PREDATOR && type2 == EntityType.SMALL_PREY) || (type1 == EntityType.SMALL_PREDATOR && type2 == EntityType.NORMAL_PREY) || (type1 == EntityType.SMALL_PREDATOR && type2 == EntityType.SMALL_PREDATOR))
				{
					return EntityAction.ATTACK;
				}else if((type1 == EntityType.NORMAL_PREDATOR && type2 == EntityType.SMALL_PREY) || (type1 == EntityType.NORMAL_PREDATOR && type2 == EntityType.NORMAL_PREY) || (type1 == EntityType.NORMAL_PREDATOR && type2 == EntityType.BIG_PREY) || (type1 == EntityType.NORMAL_PREDATOR && type2 == EntityType.SMALL_PREDATOR) || (type1 == EntityType.NORMAL_PREDATOR && type2 == EntityType.NORMAL_PREDATOR))
				{
					return EntityAction.ATTACK;
				}else if(type1 == EntityType.BIG_PREDATOR)
				{
					return EntityAction.ATTACK;
				}else{
					return EntityAction.NOTHING;
				}
			}else{
				return EntityAction.NOTHING;
			}
		}else if(foodType == FoodType.OMNIVORE)
		{
			if(hungerFactor <= 0.1)
			{
				if((type1 == EntityType.SMALL_PREY && type2 == EntityType.SMALL_PREY) || (type1 == EntityType.NORMAL_PREY && type2 == EntityType.NORMAL_PREY) || (type1 == EntityType.BIG_PREY && type2 == EntityType.BIG_PREY))
				{
					return EntityAction.ATTACK;
				}else if((type1 == EntityType.NORMAL_PREY && type2 == EntityType.SMALL_PREY) || (type1 == EntityType.NORMAL_PREY && type2 == EntityType.SMALL_PREDATOR))
				{
					return EntityAction.ATTACK;
				}else if((type1 == EntityType.BIG_PREY && type2 == EntityType.SMALL_PREY) || (type1 == EntityType.BIG_PREY && type2 == EntityType.NORMAL_PREY) || (type1 == EntityType.BIG_PREY && type2 == EntityType.NORMAL_PREDATOR)|| (type1 == EntityType.BIG_PREY && type2 == EntityType.SMALL_PREDATOR))
				{
					return EntityAction.ATTACK;
				}else if((type1 == EntityType.SMALL_PREDATOR && type2 == EntityType.SMALL_PREDATOR) || (type1 == EntityType.NORMAL_PREDATOR && type2 == EntityType.NORMAL_PREDATOR) || (type1 == EntityType.BIG_PREDATOR && type2 == EntityType.BIG_PREDATOR))
				{
					return EntityAction.ATTACK;
				}else if((type1 == EntityType.SMALL_PREDATOR && type2 == EntityType.SMALL_PREY) || (type1 == EntityType.SMALL_PREDATOR && type2 == EntityType.NORMAL_PREY) || (type1 == EntityType.SMALL_PREDATOR && type2 == EntityType.SMALL_PREDATOR))
				{
					return EntityAction.ATTACK;
				}else if((type1 == EntityType.NORMAL_PREDATOR && type2 == EntityType.SMALL_PREY) || (type1 == EntityType.NORMAL_PREDATOR && type2 == EntityType.NORMAL_PREY) || (type1 == EntityType.NORMAL_PREDATOR && type2 == EntityType.BIG_PREY) || (type1 == EntityType.NORMAL_PREDATOR && type2 == EntityType.SMALL_PREDATOR) || (type1 == EntityType.NORMAL_PREDATOR && type2 == EntityType.NORMAL_PREDATOR))
				{
					return EntityAction.ATTACK;
				}else if(type1 == EntityType.BIG_PREDATOR)
				{
					return EntityAction.ATTACK;
				}else{
					return EntityAction.NOTHING;
				}
			}else{
				return EntityAction.EATPLANTS;
			}
		}else{
			return EntityAction.NOTHING;
		}
	}
	
}
