package net.zoocraftia.core.client;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ModLoader;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.RenderEngine;
import net.minecraft.src.Tessellator;
import net.zoocraftia.core.ZoocraftiaCore;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockRenderHandler implements ISimpleBlockRenderingHandler{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if (modelId == getRenderId())
		{
			GL11.glPushMatrix();
			RenderEngine renderengine = Minecraft.getMinecraft().renderEngine;
			Tessellator tessellator = Tessellator.instance;
			tessellator.draw();
			tessellator.startDrawingQuads();
			GL11.glBindTexture(3553 /* GL_TEXTURE_2D */, renderengine.getTexture("/zoocraftia/core/textures/water.png"));
			renderer.renderBlockFluids(block, x, y, z);
			tessellator.draw();
			tessellator.startDrawingQuads();
			GL11.glBindTexture(3553 /* GL_TEXTURE_2D */, renderengine.getTexture("/terrain.png"));
			GL11.glPopMatrix();
			return true;
		} else
		{
			return true;
		}
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return ZoocraftiaCore.saltwaterRenderID;
	}

}
