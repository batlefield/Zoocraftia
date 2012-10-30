package net.zoocraftia.core;

import java.util.Random;

import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;
import cpw.mods.fml.common.IDispenserHandler;

public class DispenseHandler implements IDispenserHandler{

	@Override
	public int dispense(int x, int y, int z, int xVelocity, int zVelocity, World world, ItemStack item, Random random, double entX, double entY, double entZ) {
		if (item.itemID == ZoocraftiaItems.dart.shiftedIndex)
        {
            DartEntity var28 = new DartEntity(world, x, y, z);
            var28.setThrowableHeading((double)xVelocity, 0.10000000149011612D, (double)zVelocity, 1.1F, (float)6);
            var28.canBePickedUp = 1;
            world.spawnEntityInWorld(var28);
            world.playAuxSFX(1002, MathHelper.floor_double(x), MathHelper.floor_double(y), MathHelper.floor_double(z), 0);
            return 1;
        }else{
        	return -1;
        }
	}

}
