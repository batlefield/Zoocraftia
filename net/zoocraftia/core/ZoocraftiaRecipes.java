package net.zoocraftia.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class ZoocraftiaRecipes {

	public ZoocraftiaRecipes()
	{
		CraftingManager.getInstance().func_92051_a(new ItemStack(ZoocraftiaBlocks.plexiglass, 8), new Object[] {
		     "XXX", "XYX","XXX", Character.valueOf('X'), Block.glass, Character.valueOf('Y'), Item.diamond
		});
		CraftingManager.getInstance().func_92051_a(new ItemStack(ZoocraftiaBlocks.plexiglassPane, 1), new Object[] {
			"XXX", "XXX", Character.valueOf('X'), ZoocraftiaBlocks.plexiglass
		});
		CraftingManager.getInstance().func_92051_a(new ItemStack(ZoocraftiaBlocks.quicksand, 1), new Object[] {
			"X", "Y", Character.valueOf('X'), Item.bucketWater, Character.valueOf('Y'), Block.sand
		});
	}
	
}
