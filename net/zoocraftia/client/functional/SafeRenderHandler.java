package net.zoocraftia.client.functional;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.world.IBlockAccess;
import net.zoocraftia.functional.TileEntitySafe;
import net.zoocraftia.functional.ZoocraftiaFunctionalMain;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class SafeRenderHandler implements ISimpleBlockRenderingHandler
{

	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		if(modelID == ZoocraftiaFunctionalMain.renderID)
		{
			TileEntityRenderer.instance.renderTileEntityAt(new TileEntitySafe(), 0.0D, 0.0D, 0.0D, 0.0F);
		}
	}

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return false;
	}

	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	public int getRenderId()
	{
		return ZoocraftiaFunctionalMain.renderID;
	}

}
