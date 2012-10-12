package net.zoocraftia.api;

public class EntityEnums {

	public enum Sex{
		MALE,
		FEMALE;
	}
	
	public enum FoodType{
		CARNIVORE,
		HERBIVORE,
		OMNIVORE;
	}
	
	public enum EntityType{
		SMALL_PREY,
		NORMAL_PREY,
		BIG_PREY,
		SMALL_PREDATOR,
		NORMAL_PREDATOR,
		BIG_PREDATOR;
	}
	
	public enum EntityAction{
		ATTACK,
		FLEE,
		NOTHING,
		EATPLANTS,
		DEFEND;
	}
	
}
