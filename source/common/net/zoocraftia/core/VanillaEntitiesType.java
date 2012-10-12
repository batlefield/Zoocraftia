package net.zoocraftia.core;

import java.util.Map;
import java.util.TreeMap;

import net.minecraft.src.EntityBlaze;
import net.minecraft.src.EntityCaveSpider;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.EntityCow;
import net.minecraft.src.EntityCreeper;
import net.minecraft.src.EntityDragon;
import net.minecraft.src.EntityEnderman;
import net.minecraft.src.EntityGhast;
import net.minecraft.src.EntityIronGolem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMagmaCube;
import net.minecraft.src.EntityMooshroom;
import net.minecraft.src.EntityOcelot;
import net.minecraft.src.EntityPig;
import net.minecraft.src.EntityPigZombie;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntitySheep;
import net.minecraft.src.EntitySilverfish;
import net.minecraft.src.EntitySkeleton;
import net.minecraft.src.EntitySlime;
import net.minecraft.src.EntitySnowman;
import net.minecraft.src.EntitySpider;
import net.minecraft.src.EntitySquid;
import net.minecraft.src.EntityVillager;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.EntityZombie;
import net.zoocraftia.api.EntityEnums;
import net.zoocraftia.api.EntityEnums.EntityType;

public class VanillaEntitiesType {

	private static Map<Class<? extends EntityLiving>, EntityEnums.EntityType> entityToType = new TreeMap<Class<? extends EntityLiving>, EntityEnums.EntityType>();
	
	private static void addMapping(Class<? extends EntityLiving> clz, EntityEnums.EntityType type)
	{
		entityToType.put(clz, type);
	}
	
	public static EntityEnums.EntityType getEntityType(EntityLiving ent)
	{
		return getEntityType(ent.getClass());
	}
	
	public static EntityEnums.EntityType getEntityType(Class<? extends EntityLiving> clz)
	{
		return entityToType.get(clz);
	}
	
	static{
		addMapping(EntityPlayer.class, EntityType.NORMAL_PREDATOR);
        addMapping(EntityCreeper.class, EntityType.NORMAL_PREDATOR);
        addMapping(EntitySkeleton.class, EntityType.NORMAL_PREDATOR);
        addMapping(EntitySpider.class, EntityType.NORMAL_PREDATOR);
        addMapping(EntityZombie.class, EntityType.NORMAL_PREDATOR);
        addMapping(EntitySlime.class, EntityType.NORMAL_PREDATOR);
        addMapping(EntityGhast.class, EntityType.BIG_PREDATOR);
        addMapping(EntityPigZombie.class, EntityType.NORMAL_PREDATOR);
        addMapping(EntityEnderman.class, EntityType.NORMAL_PREDATOR);
        addMapping(EntityCaveSpider.class, EntityType.NORMAL_PREDATOR);
        addMapping(EntitySilverfish.class, EntityType.SMALL_PREDATOR);
        addMapping(EntityBlaze.class, EntityType.NORMAL_PREDATOR);
        addMapping(EntityMagmaCube.class, EntityType.NORMAL_PREDATOR);
        addMapping(EntityDragon.class, EntityType.BIG_PREDATOR);
        addMapping(EntityPig.class, EntityType.NORMAL_PREY);
        addMapping(EntitySheep.class, EntityType.NORMAL_PREY);
        addMapping(EntityCow.class, EntityType.NORMAL_PREY);
        addMapping(EntityChicken.class, EntityType.SMALL_PREY);
        addMapping(EntitySquid.class, EntityType.NORMAL_PREY);
        addMapping(EntityWolf.class, EntityType.NORMAL_PREY);
        addMapping(EntityMooshroom.class, EntityType.NORMAL_PREY);
        addMapping(EntitySnowman.class, EntityType.NORMAL_PREY);
        addMapping(EntityOcelot.class, EntityType.SMALL_PREY);
        addMapping(EntityIronGolem.class, EntityType.NORMAL_PREDATOR);
        addMapping(EntityVillager.class, EntityType.NORMAL_PREY);
	}
	
}
