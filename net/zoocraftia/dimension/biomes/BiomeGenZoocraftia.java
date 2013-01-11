package net.zoocraftia.dimension.biomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public abstract class BiomeGenZoocraftia extends BiomeGenBase{

	public BiomeDecoratorZoocraftia zoocraftiaDecorator;
	
	protected BiomeGenZoocraftia(int par1) {
		super(par1);
		topBlock = (byte)Block.grass.blockID;
		fillerBlock = (byte)Block.dirt.blockID;
		waterColorMultiplier = 0xFFFFFF;
		temperature = 0.5F;
		rainfall = 0.5F;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		spawnableWaterCreatureList.clear();
		zoocraftiaDecorator = createZoocraftiaBiomeDecorator();
	}
	
	protected BiomeDecoratorZoocraftia createZoocraftiaBiomeDecorator()
    {
        return new BiomeDecoratorZoocraftia(this);
    }
	
	
	public static final BiomeGenBase mesa = new BiomeGenMesa(250).setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, -0.1F);
	public static final BiomeGenBase ocean = new BiomeGenOcean(251).setMinMaxHeight(-1F, 0.4F);
	public static final BiomeGenBase savannah = new BiomeGenSavannah(252).setDisableRain().setTemperatureRainfall(2.0F, 0.0F);

}
