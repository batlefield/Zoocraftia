package net.zoocraftia.api;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.WorldType;

public class ZoocraftiaEntityInfo {
	
	public final String name;
	public final int ID;
	public final int foregroundEggColor;
	public final int backgroundEggColor;
	public final int probability;
	public final int min;
	public final int max;
	public final EnumCreatureType type;
	public final BiomeGenBase[] biomes;
	public final boolean shouldSpawn;
	
	public ZoocraftiaEntityInfo(String name, int id, int foregroundEggColor, int backgroundEggColor, int probability, int min, int max, EnumCreatureType type, BiomeGenBase...bases)
	{
		this.name = name;
		this.ID = id;
		this.foregroundEggColor = foregroundEggColor;
		this.backgroundEggColor = backgroundEggColor;
		this.probability = probability;
		this.min = min;
		this.max = max;
		this.type = type;
		this.biomes = bases;
		this.shouldSpawn = true;
	}
	
	
	public ZoocraftiaEntityInfo(String name, int id, int foregroundEggColor, int backgroundEggColor, int probability, int min, int max, EnumCreatureType type)
	{
		this.name = name;
		this.ID = id;
		this.foregroundEggColor = foregroundEggColor;
		this.backgroundEggColor = backgroundEggColor;
		this.probability = probability;
		this.min = min;
		this.max = max;
		this.type = type;
		this.biomes = WorldType.base12Biomes;
		this.shouldSpawn = true;
	}
	
	public ZoocraftiaEntityInfo(String name, int id, int foregroundEggColor, int backgroundEggColor, int probability, int min, int max, EnumCreatureType type, boolean shouldSpawn, BiomeGenBase...bases)
	{
		this.name = name;
		this.ID = id;
		this.foregroundEggColor = foregroundEggColor;
		this.backgroundEggColor = backgroundEggColor;
		this.probability = probability;
		this.min = min;
		this.max = max;
		this.type = type;
		this.biomes = bases;
		this.shouldSpawn = shouldSpawn;
	}
	
	
	public ZoocraftiaEntityInfo(String name, int id, int foregroundEggColor, int backgroundEggColor, int probability, int min, int max, EnumCreatureType type, boolean shouldSpawn)
	{
		this.name = name;
		this.ID = id;
		this.foregroundEggColor = foregroundEggColor;
		this.backgroundEggColor = backgroundEggColor;
		this.probability = probability;
		this.min = min;
		this.max = max;
		this.type = type;
		this.biomes = WorldType.base12Biomes;
		this.shouldSpawn = shouldSpawn;
	}
	
	public ZoocraftiaEntityInfo(String name, int id, int probability, int min, int max, EnumCreatureType type, BiomeGenBase...bases)
	{
		this.name = name;
		this.ID = id;
		this.foregroundEggColor = -1;
		this.backgroundEggColor = -1;
		this.probability = probability;
		this.min = min;
		this.max = max;
		this.type = type;
		this.biomes = bases;
		this.shouldSpawn = true;
	}
	
	
	public ZoocraftiaEntityInfo(String name, int id, int probability, int min, int max, EnumCreatureType type)
	{
		this.name = name;
		this.ID = id;
		this.foregroundEggColor = -1;
		this.backgroundEggColor = -1;
		this.probability = probability;
		this.min = min;
		this.max = max;
		this.type = type;
		this.biomes = WorldType.base12Biomes;
		this.shouldSpawn = true;
	}
	
	public ZoocraftiaEntityInfo(String name, int id, int probability, int min, int max, EnumCreatureType type, boolean shouldSpawn, BiomeGenBase...bases)
	{
		this.name = name;
		this.ID = id;
		this.foregroundEggColor = -1;
		this.backgroundEggColor = -1;
		this.probability = probability;
		this.min = min;
		this.max = max;
		this.type = type;
		this.biomes = bases;
		this.shouldSpawn = shouldSpawn;
	}
	
	
	public ZoocraftiaEntityInfo(String name, int id, int probability, int min, int max, EnumCreatureType type, boolean shouldSpawn)
	{
		this.name = name;
		this.ID = id;
		this.foregroundEggColor = -1;
		this.backgroundEggColor = -1;
		this.probability = probability;
		this.min = min;
		this.max = max;
		this.type = type;
		this.biomes = WorldType.base12Biomes;
		this.shouldSpawn = shouldSpawn;
	}
}
