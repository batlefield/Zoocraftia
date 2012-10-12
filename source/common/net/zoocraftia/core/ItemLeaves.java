package net.zoocraftia.core;

import net.minecraft.src.Block;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class ItemLeaves extends ItemBlock
{
    public ItemLeaves(int par1)
    {
        super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    public int getMetadata(int par1)
    {
        return par1;
    }

    @SideOnly(Side.CLIENT)
    public int getIconFromDamage(int par1)
    {
        return ZoocraftiaBlocks.leaves.getBlockTextureFromSideAndMetadata(0, par1);
    }

    public String getItemNameIS(ItemStack par1ItemStack)
    {
        int var2 = par1ItemStack.getItemDamage();

        if (var2 < 0 || var2 >= BlockLeaves.treeTypes.length)
        {
            var2 = 0;
        }

        return super.getItemName() + "." + BlockLeaves.treeTypes[var2];
    }
    
    public String getTextureFile()
    {
    	return "/zoocraftia/core/blocks.png";
    }
}
