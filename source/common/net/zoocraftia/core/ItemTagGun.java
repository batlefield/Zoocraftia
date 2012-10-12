package net.zoocraftia.core;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.zoocraftia.dimension.ZoocraftiaDimensionMain;

public class ItemTagGun extends Item{

	public ItemTagGun(int par1) {
		super(par1);
		maxStackSize = 1;
	}
	
	public String getTextureFile(){
		return "/zoocraftia/core/items.png";
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        boolean var5 = par3EntityPlayer.capabilities.isCreativeMode;
		
		if (var5 || par3EntityPlayer.inventory.hasItem(ZoocraftiaItems.dart.shiftedIndex))
        {
            int var6 = 80000;
            float var7 = (float)var6 / 20.0F;
            var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;

            if ((double)var7 < 0.1D)
            {
                return par1ItemStack;
            }

            if (var7 > 1.0F)
            {
                var7 = 1.0F;
            }

            DartEntity var8 = new DartEntity(par2World, par3EntityPlayer, var7 * 2.0F);


            par1ItemStack.damageItem(1, par3EntityPlayer);
            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

            if (!var5)
            {
                par3EntityPlayer.inventory.consumeInventoryItem(ZoocraftiaItems.dart.shiftedIndex);
            }
            else
            {
                var8.canBePickedUp = 2;
            }

            if (!par2World.isRemote)
            {
                par2World.spawnEntityInWorld(var8);
            }
        }

		return par1ItemStack;
    }
	
}
