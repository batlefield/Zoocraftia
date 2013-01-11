package net.zoocraftia.core.trees;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class StructureGenerator
{

	public static int TopOfDoor = 8;
	public static int DoorFaceWest = 0;
	public static int DoorFaceSouth = 1;
	public static int DoorFaceEast = 2;
	public static int DoorFaceNorth = 3;
	public static int SlabStone = 0;
	public static int SlabSand = 1;
	public static int SlabWood = 2;
	public static int SlabCobble = 3;
	public static int SlabBrick = 4;
	public static int SlabSmoothStoneBrick = 5;
	public static int HeadOfBed = 8;
	public static int PlaceBedSouthward = 0;
	public static int PlaceBedWestward = 1;
	public static int PlaceBedNorthward = 2;
	public static int PlaceBedEastward = 3;
	public static int StairsPointWestward = 0;
	public static int StairsPointEastward = 1;
	public static int StairsPointNorthward = 2;
	public static int StairsPointSouthward = 3;
	public static int WoodRegular = 0;
	public static int WoodDark = 1;
	public static int WoodBirch = 2;
	public static int WoolWhite = 0;
	public static int WoolOrange = 1;
	public static int WoolMagenta = 2;
	public static int WoolLightBlue = 3;
	public static int WoolYellow = 4;
	public static int WoolLime = 5;
	public static int WoolPink = 6;
	public static int WoolGray = 7;
	public static int WoolLightGray = 8;
	public static int WoolCyan = 9;
	public static int WoolPurple = 10;
	public static int WoolBlue = 11;
	public static int WoolBrown = 12;
	public static int WoolGreen = 13;
	public static int WoolRed = 14;
	public static int WoolBlack = 15;

	/**
	 * Place block at defined coordinates. Has support for automatic placing of
	 * the second block of Doors and Beds.
	 * 
	 * @param world
	 *            World to modify block in
	 * @param x
	 *            X Value of block
	 * @param y
	 *            Y Value of block
	 * @param z
	 *            Z Value of block
	 * @param block
	 *            ID of block to be placed
	 * @param mData
	 *            Metadata of block to be placed. Useful for rotations and
	 *            different textures of blocks.
	 */
	public static void placeBlock(World world, int x, int y, int z, int block, int mData)
	{
		if (block == Block.doorWood.blockID)
		{
			if (mData >= TopOfDoor)
			{
				if (world.getBlockId(x, y - 1, z) != block)
				{
					world.setBlockAndMetadataWithNotify(x, y - 1, z, block, mData - TopOfDoor);
				}
			}
			else if (mData < TopOfDoor)
			{
				if (world.getBlockId(x, y + 1, z) != block)
				{
					world.setBlockAndMetadataWithNotify(x, y + 1, z, block, mData + TopOfDoor);
				}
			}
		}
		else if (block == Block.doorSteel.blockID)
		{
			if (mData >= TopOfDoor)
			{
				if (world.getBlockId(x, y - 1, z) != block)
				{
					world.setBlockAndMetadataWithNotify(x, y - 1, z, block, mData - TopOfDoor);
				}
			}
			else if (mData < TopOfDoor)
			{
				if (world.getBlockId(x, y + 1, z) != block)
				{
					world.setBlockAndMetadataWithNotify(x, y + 1, z, block, mData + TopOfDoor);
				}
			}
		}
		else if (block == Block.bed.blockID)
		{
			if (mData >= HeadOfBed)
			{
				boolean bedTowardNorthSouth = getBedPlacementDirectionNorthOrSouth(mData - 8);
				if (bedTowardNorthSouth)
				{
					if ((mData - 8) == PlaceBedNorthward)
					{
						world.setBlockAndMetadataWithNotify(x, y, z - 1, Block.bed.blockID, PlaceBedNorthward);
					}
					else if ((mData - 8) == PlaceBedSouthward)
					{
						world.setBlockAndMetadataWithNotify(x, y, z + 1, Block.bed.blockID, PlaceBedSouthward);
					}
				}
				else
				{
					if ((mData - 8) == PlaceBedEastward)
					{
						world.setBlockAndMetadataWithNotify(x + 1, y, z, Block.bed.blockID, PlaceBedEastward);
					}
					else if ((mData - 8) == PlaceBedWestward)
					{
						world.setBlockAndMetadataWithNotify(x - 1, y, z, Block.bed.blockID, PlaceBedWestward);
					}
				}
			}
			else if (mData < HeadOfBed)
			{
				boolean bedTowardNorthSouth = getBedPlacementDirectionNorthOrSouth(mData);
				if (bedTowardNorthSouth)
				{
					if (mData == PlaceBedNorthward)
					{
						world.setBlockAndMetadataWithNotify(x, y, z - 1, Block.bed.blockID, PlaceBedNorthward + HeadOfBed);
					}
					else if (mData == PlaceBedSouthward)
					{
						world.setBlockAndMetadataWithNotify(x, y, z + 1, Block.bed.blockID, PlaceBedSouthward + HeadOfBed);
					}
				}
				else
				{
					if (mData == PlaceBedEastward)
					{
						world.setBlockAndMetadataWithNotify(x + 1, y, z, Block.bed.blockID, PlaceBedEastward + HeadOfBed);
					}
					else if (mData == PlaceBedWestward)
					{
						world.setBlockAndMetadataWithNotify(x - 1, y, z, Block.bed.blockID, PlaceBedWestward + HeadOfBed);
					}
				}
			}
		}
		world.setBlockAndMetadataWithNotify(x, y, z, block, mData);
	}

	/**
	 * Place block at defined coordinates. Has support for automatic placing of
	 * the second block of Doors and Beds.
	 * 
	 * @param world
	 *            World to modify block in
	 * @param x
	 *            X Value of block
	 * @param y
	 *            Y Value of block
	 * @param z
	 *            Z Value of block
	 * @param block
	 *            ID of block to be placed
	 * @see #placeBlock(World, int, int, int, int, int)
	 */
	public static void placeBlock(World world, int x, int y, int z, int block)
	{
		placeBlock(world, x, y, z, block, 0);
	}

	private static boolean getBedPlacementDirectionNorthOrSouth(int mData)
	{
		switch (mData)
		{
		case 0:
		case 2:
			return true;
		case 1:
		case 3:
			return false;
		default:
			return false;
		}
	}

	/**
	 * Generates a flat floor of blocks and isn't hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param y
	 *            The Y value for the floor
	 * @param x1
	 *            The first X coordinate
	 * @param z1
	 *            The first Z coordinate
	 * @param x2
	 *            The second X coordinate
	 * @param z2
	 *            The second Z coordinate
	 * @param block
	 *            ID of block to be placed
	 * @param mData
	 *            Metadata of block to be placed. Useful for rotations and
	 *            different textures of blocks.
	 */
	public static void generateFloor(World world, int y, int x1, int z1, int x2, int z2, int block, int mData)
	{
		if (x1 <= x2 && z1 <= z2)
		{
			for (int startX = x1; startX <= x2; startX++)
			{
				for (int startZ = z1; startZ <= z2; startZ++)
				{
					placeBlock(world, startX, y, startZ, block, mData);
				}
			}
		}
		else if (x1 <= x2 && z1 >= z2)
		{
			for (int startX = x1; startX < x2; startX++)
			{
				for (int startZ = z1; startZ >= z2; startZ--)
				{
					placeBlock(world, startX, y, startZ, block, mData);
				}
			}
		}
		else if (x1 >= x2 && z1 <= z2)
		{
			for (int startX = x1; startX >= x2; startX--)
			{
				for (int startZ = z1; startZ <= z2; startZ++)
				{
					placeBlock(world, startX, y, startZ, block, mData);
				}
			}
		}
		else if (x1 >= x2 && z1 >= z2)
		{
			for (int startX = x1; startX >= x2; startX--)
			{
				for (int startZ = z1; startZ >= z2; startZ--)
				{
					placeBlock(world, startX, y, startZ, block, mData);
				}
			}
		}
	}

	/**
	 * Generates a flat floor of blocks and isn't hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param y
	 *            The Y value for the floor
	 * @param x1
	 *            The first X coordinate
	 * @param z1
	 *            The first Z coordinate
	 * @param x2
	 *            The second X coordinate
	 * @param z2
	 *            The second Z coordinate
	 * @param block
	 *            ID of block to be placed
	 * @see #generateFloor(World, int, int, int, int, int, int, int)
	 */
	public static void generateFloor(World world, int y, int x1, int z1, int x2, int z2, int block)
	{
		generateFloor(world, y, x1, z1, x2, z2, block, 0);
	}

	/**
	 * Generates a flat floor of blocks and is hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param y
	 *            The Y value for the floor
	 * @param x1
	 *            The first X coordinate
	 * @param z1
	 *            The first Z coordinate
	 * @param x2
	 *            The second X coordinate
	 * @param z2
	 *            The second Z coordinate
	 * @param block
	 *            ID of block to be placed
	 * @param mData
	 *            Metadata of block to be placed. Useful for rotations and
	 *            different textures of blocks.
	 */
	public static void generateHollowFloor(World world, int y, int x1, int z1, int x2, int z2, int block, int mData)
	{
		if (x1 <= x2)
		{
			for (int startX = x1; startX <= x2; startX++)
			{
				placeBlock(world, startX, y, z1, block, mData);
				placeBlock(world, startX, y, z2, block, mData);
			}
		}
		else if (x1 >= x2)
		{
			for (int startX = x1; startX >= x2; startX--)
			{
				placeBlock(world, startX, y, z1, block, mData);
				placeBlock(world, startX, y, z2, block, mData);
			}
		}

		if (z1 <= z2)
		{
			for (int startZ = z1; startZ <= z2; startZ++)
			{
				placeBlock(world, x1, y, startZ, block, mData);
				placeBlock(world, x2, y, startZ, block, mData);
			}
		}
		else if (z1 >= z2)
		{
			for (int startZ = z1; startZ >= z2; startZ--)
			{
				placeBlock(world, x1, y, startZ, block, mData);
				placeBlock(world, x2, y, startZ, block, mData);
			}
		}
	}

	/**
	 * Generates a flat floor of blocks and is hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param y
	 *            The Y value for the floor
	 * @param x1
	 *            The first X coordinate
	 * @param z1
	 *            The first Z coordinate
	 * @param x2
	 *            The second X coordinate
	 * @param z2
	 *            The second Z coordinate
	 * @param block
	 *            ID of block to be placed
	 * @see #generateHollowFloor(World, int, int, int, int, int, int, int)
	 */
	public static void generateHollowFloor(World world, int y, int x1, int z1, int x2, int z2, int block)
	{
		generateHollowFloor(world, y, x1, z1, x2, z2, block, 0);
	}

	/**
	 * Generates a vertical wall in the East or West direction and isn't hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param x
	 *            The X value of the wall
	 * @param y1
	 *            The first Y coordinate
	 * @param z1
	 *            The first Z coordinate
	 * @param y2
	 *            The first Y coordinate
	 * @param z2
	 *            The first Z coordinate
	 * @param block
	 *            ID of block to be placed
	 * @param mData
	 *            Metadata of block to be placed. Useful for rotations and
	 *            different textures of blocks.
	 * @see #generateXWall(World, int, int, int, int, int, int)
	 */
	public static void generateEastWestWall(World world, int x, int y1, int z1, int y2, int z2, int block, int mData)
	{
		generateXWall(world, x, y1, z1, y2, z2, block, mData);
	}

	/**
	 * Generates a vertical wall in the East or West direction and isn't hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param x
	 *            The X value of the wall
	 * @param y1
	 *            The first Y coordinate
	 * @param z1
	 *            The first Z coordinate
	 * @param y2
	 *            The first Y coordinate
	 * @param z2
	 *            The first Z coordinate
	 * @param block
	 *            ID of block to be placed
	 * @see #generateEastWestWall(World, int, int, int, int, int, int, int)
	 */
	public static void generateEastWestWall(World world, int x, int y1, int z1, int y2, int z2, int block)
	{
		generateXWall(world, x, y1, z1, y2, z2, block, 0);
	}

	/**
	 * Generates a vertical wall in the East or West direction and is hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param x
	 *            The X value of the wall
	 * @param y1
	 *            The first Y coordinate
	 * @param z1
	 *            The first Z coordinate
	 * @param y2
	 *            The first Y coordinate
	 * @param z2
	 *            The first Z coordinate
	 * @param block
	 *            ID of block to be placed
	 * @param mData
	 *            Metadata of block to be placed. Useful for rotations and
	 *            different textures of blocks.
	 * @see #generateHollowXWall(World, int, int, int, int, int, int)
	 */
	public static void generateHollowEastWestWall(World world, int x, int y1, int z1, int y2, int z2, int block, int mData)
	{
		generateHollowXWall(world, x, y1, z1, y2, z2, block, mData);
	}

	/**
	 * Generates a vertical wall in the East or West direction and is hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param x
	 *            The X value of the wall
	 * @param y1
	 *            The first Y coordinate
	 * @param z1
	 *            The first Z coordinate
	 * @param y2
	 *            The first Y coordinate
	 * @param z2
	 *            The first Z coordinate
	 * @param block
	 *            ID of block to be placed
	 * @see #generateHollowEastWestWall(World, int, int, int, int, int, int,
	 *      int)
	 */
	public static void generateHollowEastWestWall(World world, int x, int y1, int z1, int y2, int z2, int block)
	{
		generateHollowXWall(world, x, y1, z1, y2, z2, block, 0);
	}

	/**
	 * Generates a vertical wall in the North or South direction and isn't
	 * hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param z
	 *            The Z value of the wall
	 * @param x1
	 *            The first X coordinate
	 * @param y1
	 *            The first Y coordinate
	 * @param x2
	 *            The first X coordinate
	 * @param y2
	 *            The first Y coordinate
	 * @param block
	 *            ID of block to be placed
	 * @param mData
	 *            Metadata of block to be placed. Useful for rotations and
	 *            different textures of blocks.
	 * @see #generateZWall(World, int, int, int, int, int, int)
	 */
	public static void generateNorthSouthWall(World world, int z, int x1, int y1, int x2, int y2, int block, int mData)
	{
		generateZWall(world, z, x1, y1, x2, y2, block, mData);
	}

	/**
	 * Generates a vertical wall in the North or South direction and isn't
	 * hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param z
	 *            The Z value of the wall
	 * @param x1
	 *            The first X coordinate
	 * @param y1
	 *            The first Y coordinate
	 * @param x2
	 *            The first X coordinate
	 * @param y2
	 *            The first Y coordinate
	 * @param block
	 *            ID of block to be placed
	 * @see #generateNorthSouthWall(World, int, int, int, int, int, int, int)
	 */
	public static void generateNorthSouthWall(World world, int z, int x1, int y1, int x2, int y2, int block)
	{
		generateZWall(world, z, x1, y1, x2, y2, block, 0);
	}

	/**
	 * Generates a vertical wall in the North or South direction and is hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param z
	 *            The Z value of the wall
	 * @param x1
	 *            The first X coordinate
	 * @param y1
	 *            The first Y coordinate
	 * @param x2
	 *            The first X coordinate
	 * @param y2
	 *            The first Y coordinate
	 * @param block
	 *            ID of block to be placed
	 * @param mData
	 *            Metadata of block to be placed. Useful for rotations and
	 *            different textures of blocks.
	 * @see #generateHollowZWall(World, int, int, int, int, int, int)
	 */
	public static void generateHollowNorthSouthWall(World world, int z, int x1, int y1, int x2, int y2, int block, int mData)
	{
		generateHollowZWall(world, z, x1, y1, x2, y2, block, mData);
	}

	/**
	 * Generates a vertical wall in the North or South direction and is hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param z
	 *            The Z value of the wall
	 * @param x1
	 *            The first X coordinate
	 * @param y1
	 *            The first Y coordinate
	 * @param x2
	 *            The first X coordinate
	 * @param y2
	 *            The first Y coordinate
	 * @param block
	 *            ID of block to be placed
	 * @see #generateHollowNorthSouthWall(World, int, int, int, int, int, int,
	 *      int)
	 */
	public static void generateHollowNorthSouthWall(World world, int z, int x1, int y1, int x2, int y2, int block)
	{
		generateHollowZWall(world, z, x1, y1, x2, y2, block, 0);
	}

	/**
	 * Generates a vertical wall in the East or West direction and isn't hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param x
	 *            The X value of the wall
	 * @param y1
	 *            The first Y coordinate
	 * @param z1
	 *            The first Z coordinate
	 * @param y2
	 *            The first Y coordinate
	 * @param z2
	 *            The first Z coordinate
	 * @param block
	 *            ID of block to be placed
	 * @param mData
	 *            Metadata of block to be placed. Useful for rotations and
	 *            different textures of blocks.
	 */
	public static void generateXWall(World world, int x, int y1, int z1, int y2, int z2, int block, int mData)
	{
		if (y1 >= y2)
		{
			for (int startY = y1; startY >= y2; startY--)
			{
				if (z1 >= z2)
				{
					for (int startZ = z1; startZ >= z2; startZ--)
					{
						placeBlock(world, x, startY, startZ, block, mData);
					}
				}
				else if (z1 <= z2)
				{
					for (int startZ = z1; startZ <= z2; startZ++)
					{
						placeBlock(world, x, startY, startZ, block, mData);
					}
				}
			}
		}
		else if (y1 <= y2)
		{
			for (int startY = y1; startY <= y2; startY++)
			{
				if (z1 >= z2)
				{
					for (int startZ = z1; startZ >= z2; startZ--)
					{
						placeBlock(world, x, startY, startZ, block, mData);
					}
				}
				else if (z1 <= z2)
				{
					for (int startZ = z1; startZ <= z2; startZ++)
					{
						placeBlock(world, x, startY, startZ, block, mData);
					}
				}
			}
		}
	}

	/**
	 * Generates a vertical wall in the East or West direction and isn't hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param x
	 *            The X value of the wall
	 * @param y1
	 *            The first Y coordinate
	 * @param z1
	 *            The first Z coordinate
	 * @param y2
	 *            The first Y coordinate
	 * @param z2
	 *            The first Z coordinate
	 * @param block
	 *            ID of block to be placed
	 * @see #generateXWall(World, int, int, int, int, int, int, int)
	 */
	public static void generateXWall(World world, int x, int y1, int z1, int y2, int z2, int block)
	{
		generateXWall(world, x, y1, z1, y2, z2, block, 0);
	}

	/**
	 * Generates a vertical wall in the North or South direction and isn't
	 * hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param z
	 *            The Z value of the wall
	 * @param x1
	 *            The first X coordinate
	 * @param y1
	 *            The first Y coordinate
	 * @param x2
	 *            The first X coordinate
	 * @param y2
	 *            The first Y coordinate
	 * @param block
	 *            ID of block to be placed
	 * @param mData
	 *            Metadata of block to be placed. Useful for rotations and
	 *            different textures of blocks.
	 */
	public static void generateZWall(World world, int z, int x1, int y1, int x2, int y2, int block, int mData)
	{
		if (y1 >= y2)
		{
			for (int startY = y1; startY >= y2; startY--)
			{
				if (x1 >= x2)
				{
					for (int startX = x1; startX >= x2; startX--)
					{
						placeBlock(world, startX, startY, z, block, mData);
					}
				}
				else if (x1 <= x2)
				{
					for (int startX = x1; startX <= x2; startX++)
					{
						placeBlock(world, startX, startY, z, block, mData);
					}
				}
			}
		}
		else if (y1 <= y2)
		{
			for (int startY = y1; startY <= y2; startY++)
			{
				if (x1 >= x2)
				{
					for (int startX = x1; startX >= x2; startX--)
					{
						placeBlock(world, startX, startY, z, block, mData);
					}
				}
				else if (x1 <= x2)
				{
					for (int startX = x1; startX <= x2; startX++)
					{
						placeBlock(world, startX, startY, z, block, mData);
					}
				}
			}
		}
	}

	/**
	 * Generates a vertical wall in the North or South direction and isn't
	 * hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param z
	 *            The Z value of the wall
	 * @param x1
	 *            The first X coordinate
	 * @param y1
	 *            The first Y coordinate
	 * @param x2
	 *            The first X coordinate
	 * @param y2
	 *            The first Y coordinate
	 * @param block
	 *            ID of block to be placed
	 * @see #generateZWall(World, int, int, int, int, int, int, int)
	 */
	public static void generateZWall(World world, int z, int x1, int y1, int x2, int y2, int block)
	{
		generateZWall(world, z, x1, y1, x2, y2, block, 0);
	}

	/**
	 * Generates a vertical wall in the East or West direction and is hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param x
	 *            The X value of the wall
	 * @param y1
	 *            The first Y coordinate
	 * @param z1
	 *            The first Z coordinate
	 * @param y2
	 *            The first Y coordinate
	 * @param z2
	 *            The first Z coordinate
	 * @param block
	 *            ID of block to be placed
	 * @param mData
	 *            Metadata of block to be placed. Useful for rotations and
	 *            different textures of blocks.
	 */
	public static void generateHollowXWall(World world, int x, int y1, int z1, int y2, int z2, int block, int mData)
	{
		if (y1 >= y2)
		{
			for (int startY = y1; startY >= y2; startY--)
			{
				placeBlock(world, x, startY, z1, block, mData);
				placeBlock(world, x, startY, z2, block, mData);
			}
		}
		else if (y1 <= y2)
		{
			for (int startY = y1; startY <= y2; startY++)
			{
				placeBlock(world, x, startY, z1, block, mData);
				placeBlock(world, x, startY, z2, block, mData);
			}
		}

		if (z1 >= z2)
		{
			for (int startZ = z1; startZ >= z2; startZ--)
			{
				placeBlock(world, x, y1, startZ, block, mData);
				placeBlock(world, x, y2, startZ, block, mData);
			}
		}
		else if (z1 <= z2)
		{
			for (int startZ = z1; startZ <= z2; startZ++)
			{
				placeBlock(world, x, y1, startZ, block, mData);
				placeBlock(world, x, y2, startZ, block, mData);
			}
		}
	}

	/**
	 * Generates a vertical wall in the East or West direction and is hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param x
	 *            The X value of the wall
	 * @param y1
	 *            The first Y coordinate
	 * @param z1
	 *            The first Z coordinate
	 * @param y2
	 *            The first Y coordinate
	 * @param z2
	 *            The first Z coordinate
	 * @param block
	 *            ID of block to be placed
	 * @see #generateHollowXWall(World, int, int, int, int, int, int, int)
	 */
	public static void generateHollowXWall(World world, int x, int y1, int z1, int y2, int z2, int block)
	{
		generateHollowXWall(world, x, y1, z1, y2, z2, block, 0);
	}

	/**
	 * Generates a vertical wall in the North or South direction and is hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param z
	 *            The Z value of the wall
	 * @param x1
	 *            The first X coordinate
	 * @param y1
	 *            The first Y coordinate
	 * @param x2
	 *            The first X coordinate
	 * @param y2
	 *            The first Y coordinate
	 * @param block
	 *            ID of block to be placed
	 * @param mData
	 *            Metadata of block to be placed. Useful for rotations and
	 *            different textures of blocks.
	 */
	public static void generateHollowZWall(World world, int z, int x1, int y1, int x2, int y2, int block, int mData)
	{
		if (y1 >= y2)
		{
			for (int startY = y1; startY >= y2; startY--)
			{
				placeBlock(world, x1, startY, z, block, mData);
				placeBlock(world, x2, startY, z, block, mData);
			}
		}
		else if (y1 <= y2)
		{
			for (int startY = y1; startY <= y2; startY++)
			{
				placeBlock(world, x1, startY, z, block, mData);
				placeBlock(world, x2, startY, z, block, mData);
			}
		}

		if (x1 >= x2)
		{
			for (int startX = x1; startX >= x2; startX--)
			{
				placeBlock(world, startX, y1, z, block, mData);
				placeBlock(world, startX, y2, z, block, mData);
			}
		}
		else if (x1 <= x2)
		{
			for (int startX = x1; startX <= x2; startX++)
			{
				placeBlock(world, startX, y1, z, block, mData);
				placeBlock(world, startX, y2, z, block, mData);
			}
		}
	}

	/**
	 * Generates a vertical wall in the North or South direction and is hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param z
	 *            The Z value of the wall
	 * @param x1
	 *            The first X coordinate
	 * @param y1
	 *            The first Y coordinate
	 * @param x2
	 *            The first X coordinate
	 * @param y2
	 *            The first Y coordinate
	 * @param block
	 *            ID of block to be placed
	 * @see #generateHollowZWall(World, int, int, int, int, int, int, int)
	 */
	public static void generateHollowZWall(World world, int z, int x1, int y1, int x2, int y2, int block)
	{
		generateHollowZWall(world, z, x1, y1, x2, y2, block, 0);
	}

	/**
	 * Automatically determines the direction of a wall. Then generates that
	 * wall if able to. Automatically makes the wall not hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param x1
	 *            The first X coordinate
	 * @param y1
	 *            The first Y coordinate
	 * @param z1
	 *            The first Z coordinate
	 * @param x2
	 *            The second X coordinate
	 * @param y2
	 *            The second Y coordinate
	 * @param z2
	 *            The second Z coordinate
	 * @param block
	 *            ID of block to be placed
	 * @param mData
	 *            Metadata of block to be placed. Useful for rotations and
	 *            different textures of blocks.
	 * @return Returns true if was able to generate wall. Returns false if it
	 *         was unable to generate wall.
	 * @see #generateWall(World, int, int, int, int, int, int, int, int,
	 *      boolean)
	 * @see #generateWall(World, int, int, int, int, int, int, int)
	 */
	public static boolean generateWall(World world, int x1, int y1, int z1, int x2, int y2, int z2, int block, int mData)
	{
		return generateWall(world, x1, y1, z1, x2, y2, z2, block, mData, false);
	}

	/**
	 * Automatically determines the direction of a wall. Then generates that
	 * wall if able to. Automatically makes the wall not hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param x1
	 *            The first X coordinate
	 * @param y1
	 *            The first Y coordinate
	 * @param z1
	 *            The first Z coordinate
	 * @param x2
	 *            The second X coordinate
	 * @param y2
	 *            The second Y coordinate
	 * @param z2
	 *            The second Z coordinate
	 * @param block
	 *            ID of block to be placed
	 * @return Returns true if was able to generate wall. Returns false if it
	 *         was unable to generate wall.
	 * @see #generateWall(World, int, int, int, int, int, int, int, int,
	 *      boolean)
	 * @see #generateWall(World, int, int, int, int, int, int, int, int)
	 */
	public static boolean generateWall(World world, int x1, int y1, int z1, int x2, int y2, int z2, int block)
	{
		return generateWall(world, x1, y1, z1, x2, y2, z2, block, 0);
	}

	/**
	 * Automatically determines the direction of a wall. Then generates that
	 * wall if able to.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param x1
	 *            The first X coordinate
	 * @param y1
	 *            The first Y coordinate
	 * @param z1
	 *            The first Z coordinate
	 * @param x2
	 *            The second X coordinate
	 * @param y2
	 *            The second Y coordinate
	 * @param z2
	 *            The second Z coordinate
	 * @param block
	 *            ID of block to be placed
	 * @param mData
	 *            Metadata of block to be placed. Useful for rotations and
	 *            different textures of blocks.
	 * @param hollow
	 *            Make wall hollow or not
	 * @return Returns true if was able to generate wall. Returns false if it
	 *         was unable to generate wall.
	 */
	public static boolean generateWall(World world, int x1, int y1, int z1, int x2, int y2, int z2, int block, int mData, boolean hollow)
	{
		if (x1 == x2)
		{
			if (hollow)
			{
				generateHollowXWall(world, x1, y1, z1, y2, z2, block, mData);
			}
			else
			{
				generateXWall(world, x1, y1, z1, y2, z2, block, mData);
			}
			return true;
		}
		else if (z1 == z2)
		{
			if (hollow)
			{
				generateHollowZWall(world, z1, x1, y1, x2, y2, block, mData);
			}
			else
			{
				generateZWall(world, z1, x1, y1, x2, y2, block, mData);
			}
			return true;
		}
		return false;
	}

	/**
	 * Automatically determines how to create a cuboid of blocks based off of
	 * coordinates given. Then generates that cuboid. Also automatically causes
	 * cuboid to not be hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param x1
	 *            The first X coordinate
	 * @param y1
	 *            The first Y coordinate
	 * @param z1
	 *            The first Z coordinate
	 * @param x2
	 *            The second X coordinate
	 * @param y2
	 *            The second Y coordinate
	 * @param z2
	 *            The second Z coordinate
	 * @param block
	 *            ID of block to be placed
	 * @param mData
	 *            Metadata of block to be placed. Useful for rotations and
	 *            different textures of blocks.
	 * @see #generateCuboid(World, int, int, int, int, int, int, int, int,
	 *      boolean)
	 * @see #generateCuboid(World, int, int, int, int, int, int, int)
	 */
	public static void generateCuboid(World world, int x1, int y1, int z1, int x2, int y2, int z2, int block, int mData)
	{
		generateCuboid(world, x1, y1, z1, x2, y2, z2, block, mData, false);
	}

	/**
	 * Automatically determines how to create a cuboid of blocks based off of
	 * coordinates given. Then generates that cuboid. Also automatically causes
	 * cuboid to not be hollow.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param x1
	 *            The first X coordinate
	 * @param y1
	 *            The first Y coordinate
	 * @param z1
	 *            The first Z coordinate
	 * @param x2
	 *            The second X coordinate
	 * @param y2
	 *            The second Y coordinate
	 * @param z2
	 *            The second Z coordinate
	 * @param block
	 *            ID of block to be placed
	 * @see #generateCuboid(World, int, int, int, int, int, int, int, int)
	 * @see #generateCuboid(World, int, int, int, int, int, int, int, int,
	 *      boolean)
	 */
	public static void generateCuboid(World world, int x1, int y1, int z1, int x2, int y2, int z2, int block)
	{
		generateCuboid(world, x1, y1, z1, x2, y2, z2, block, 0);
	}

	/**
	 * Automatically determines how to create a cuboid of blocks based off of
	 * coordinates given. Then generates that cuboid.
	 * 
	 * @param world
	 *            World to modify blocks in
	 * @param x1
	 *            The first X coordinate
	 * @param y1
	 *            The first Y coordinate
	 * @param z1
	 *            The first Z coordinate
	 * @param x2
	 *            The second X coordinate
	 * @param y2
	 *            The second Y coordinate
	 * @param z2
	 *            The second Z coordinate
	 * @param block
	 *            ID of block to be placed
	 * @param mData
	 *            Metadata of block to be placed. Useful for rotations and
	 *            different textures of blocks.
	 * @param hollow
	 *            Make cuboid hollow or not
	 */
	public static void generateCuboid(World world, int x1, int y1, int z1, int x2, int y2, int z2, int block, int mData, boolean hollow)
	{
		if (y1 == y2)
		{
			if (hollow)
			{
				generateHollowFloor(world, y1, x1, z1, x2, z2, block, mData);
			}
			else
			{
				generateFloor(world, y1, x1, z1, x2, z2, block, mData);
			}
		}
		else if (!generateWall(world, x1, y1, z1, x2, y2, z2, block, mData, hollow))
		{
			if (hollow)
			{
				generateFloor(world, y1, x1, z1, x2, z2, block, mData);
				generateFloor(world, y2, x1, z1, x2, z2, block, mData);
				if (y1 >= y2)
				{
					for (int startY = y1 - 1; startY >= (y2 + 1); startY--)
					{
						generateHollowFloor(world, startY, x1, z1, x2, z2, block, mData);
					}
				}
				else
				{
					for (int startY = y1 + 1; startY <= (y2 - 1); startY++)
					{
						generateHollowFloor(world, startY, x1, z1, x2, z2, block, mData);
					}
				}
			}
			else
			{
				if (y1 >= y2)
				{
					for (int startY = y1; startY >= y2; startY--)
					{
						generateFloor(world, startY, x1, z1, x2, z2, block, mData);
					}
				}
				else
				{
					for (int startY = y1; startY <= y2; startY++)
					{
						generateFloor(world, startY, x1, z1, x2, z2, block, mData);
					}
				}
			}
		}
	}

	public static void generatePillar(World world, int x, int y, int z, int l, int height)
	{
		generatePillar(world, x, y, z, l, 0, height);
	}

	public static void generatePillar(World world, int x, int y, int z, int l, int md, int height)
	{
		for (int n = 0; n < height; n++)
		{
			world.setBlockAndMetadataWithNotify(x, y + n, z, l, md);
		}
	}

}
