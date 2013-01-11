package net.zoocraftia.core;

import java.util.Map;
import java.util.TreeMap;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
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
