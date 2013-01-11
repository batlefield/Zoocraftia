package net.zoocraftia.dimension.biomes;

import net.zoocraftia.core.ZoocraftiaBlocks;

public class BiomeGenMesa extends BiomeGenZoocraftia{

	protected BiomeGenMesa(int par1) {
		super(par1);
		setBiomeName("Mesa");
		setColor(0xFFEF00);
		zoocraftiaDecorator.setTreesPerChunk(-999);
		zoocraftiaDecorator.setFlowersPerChunk(-999);
		zoocraftiaDecorator.setGrassPerChunk(-999);
        topBlock = (byte)ZoocraftiaBlocks.mesa.blockID;
        fillerBlock = (byte)ZoocraftiaBlocks.mesa.blockID;
        theBiomeDecorator.generateLakes = false;
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
	}

}
