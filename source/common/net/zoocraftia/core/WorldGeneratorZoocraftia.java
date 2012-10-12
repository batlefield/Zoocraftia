package net.zoocraftia.core;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ChunkProviderGenerate;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorZoocraftia implements IWorldGenerator{

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (chunkGenerator instanceof ChunkProviderGenerate)
        {
			for (int i = 0; i < 50; i++)
			{
				int randPosX = (chunkX << 4) + rand.nextInt(16);
				int randPosY = rand.nextInt(256);
				int randPosZ = (chunkZ << 4) + rand.nextInt(16);
				(new WorldGenMinable(ZoocraftiaBlocks.brownstone.blockID, 50)).generate(world, rand, randPosX, randPosY, randPosZ);
			}
        }
	}

}
