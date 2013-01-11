package net.zoocraftia.client.functional;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.zoocraftia.core.ZoocraftiaDirection;
import net.zoocraftia.functional.TileEntitySafe;

import org.lwjgl.opengl.GL11;

public class SafeRenderer extends TileEntitySpecialRenderer
{

	private ModelSafe safeModel = new ModelSafe();
	
	public SafeRenderer()
	{
		
	}
	
	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
	{
		renderModelAt((TileEntitySafe) var1, var2, var4, var6, var8);
	}

	public void renderModelAt(TileEntitySafe safe, double d, double d1, double d2, float f)
	{
		int x;
		if(safe.worldObj == null)
		{
			x = 0;
		}else{
			x = ZoocraftiaDirection.getOpposite(safe.getDirection());
		}
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
		if(x == 1) GL11.glRotatef(-90, 0, 1F, 0);
		if(x == 3) GL11.glRotatef(90, 0, 1F, 0);
		if(x == 2) GL11.glRotatef(180, 0, 1F, 0);
		if(x == 0) GL11.glRotatef(0, 0, 1F, 0);
		GL11.glRotatef(180, 1F, 0, 0);
		bindTextureByName("/zoocraftia/functional/textures/safe.png");
		GL11.glPushMatrix();
		float rotation = safe.prevAngle + (safe.currentAngle - safe.prevAngle) * f;
		rotation = 1.0F - rotation;
		rotation = 1.0F - rotation;
		safeModel.SafeDoor.rotateAngleY = -(rotation * (float) Math.PI * 1.5F) / 2;
		safeModel.renderModel(0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

}
