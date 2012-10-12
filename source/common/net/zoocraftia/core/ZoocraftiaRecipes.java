package net.zoocraftia.core;

import net.minecraft.src.Block;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class ZoocraftiaRecipes {

	public ZoocraftiaRecipes()
	{
		CraftingManager.getInstance().addRecipe(new ItemStack(ZoocraftiaBlocks.plexiglass, 8), new Object[] {
		     "XXX", "XYX","XXX", Character.valueOf('X'), Block.glass, Character.valueOf('Y'), Item.diamond
		});
		CraftingManager.getInstance().addRecipe(new ItemStack(ZoocraftiaBlocks.plexiglassPane, 1), new Object[] {
			"XXX", "XXX", Character.valueOf('X'), ZoocraftiaBlocks.plexiglass
		});
		CraftingManager.getInstance().addRecipe(new ItemStack(ZoocraftiaBlocks.quicksand, 1), new Object[] {
			"X", "Y", Character.valueOf('X'), Item.bucketWater, Character.valueOf('Y'), Block.sand
		});
	}
	
}
