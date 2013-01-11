package net.zoocraftia.client.core;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.zoocraftia.core.ZoocraftiaBlocks;
import net.zoocraftia.core.ZoocraftiaCore;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class PlexiglassRenderHandler implements ISimpleBlockRenderingHandler{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		if(modelID == getRenderId())
		{
			RenderEngine rE = Minecraft.getMinecraft().renderEngine;
		    int texture = rE.getTexture("/zoocraftia/core/connectedGlass.png");
		    int terrain = rE.getTexture("/terrain.png");
		    rE.bindTexture(texture);
		    block.setBlockBoundsForItemRender();
		    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		    Tessellator.instance.startDrawingQuads();
		    Tessellator.instance.setNormal(0.0F, -1F, 0.0F);
		    renderer.renderBottomFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(0, metadata));
		    Tessellator.instance.draw();
		    Tessellator.instance.startDrawingQuads();
		    Tessellator.instance.setNormal(0.0F, 1.0F, 0.0F);
		    renderer.renderTopFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(1, metadata));
		    Tessellator.instance.draw();
		    Tessellator.instance.startDrawingQuads();
		    Tessellator.instance.setNormal(0.0F, 0.0F, -1F);
		    renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(2, metadata));
		    Tessellator.instance.draw();
		    Tessellator.instance.startDrawingQuads();
		    Tessellator.instance.setNormal(0.0F, 0.0F, 1.0F);
		    renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(3, metadata));
		    Tessellator.instance.draw();
		    Tessellator.instance.startDrawingQuads();
		    Tessellator.instance.setNormal(-1F, 0.0F, 0.0F);
		    renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(4, metadata));
		    Tessellator.instance.draw();
		    Tessellator.instance.startDrawingQuads();
		    Tessellator.instance.setNormal(1.0F, 0.0F, 0.0F);
		    renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(5, metadata));
		    Tessellator.instance.draw();
		    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		    rE.bindTexture(terrain);
		}
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
			GL11.glBindTexture(3553 /* GL_TEXTURE_2D */, renderengine.getTexture("/zoocraftia/core/connectedGlass.png"));
			renderer.renderStandardBlock(block, x, y, z);
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
		return true;
	}

	@Override
	public int getRenderId() {
		return ZoocraftiaCore.plexiglassRenderID;
	}

}
