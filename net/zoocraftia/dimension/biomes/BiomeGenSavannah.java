package net.zoocraftia.dimension.biomes;

import java.util.Random;

import net.minecraft.world.gen.feature.WorldGenerator;
import net.zoocraftia.core.ZoocraftiaBlocks;
import net.zoocraftia.core.trees.ZooGenUmbrellaThornAcacia;

public class BiomeGenSavannah extends BiomeGenZoocraftia
{

	public BiomeGenSavannah(int i)
    {
        super(i);
        setBiomeName("Savannah");
        setColor(0xFFEF00);
        zoocraftiaDecorator.setTreesPerChunk(1);
        zoocraftiaDecorator.setFlowersPerChunk(4);
        zoocraftiaDecorator.setGrassPerChunk(-999);
        zoocraftiaDecorator.setCactusPerChunk(1);
        zoocraftiaDecorator.setReedsPerChunk(1);
        topBlock = (byte)ZoocraftiaBlocks.savannahGround.blockID;
        
    }
    
    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        return new ZooGenUmbrellaThornAcacia(false);
    }
}