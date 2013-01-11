package net.zoocraftia.client.functional;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSafe extends ModelBase
{
	// fields
	ModelRenderer SafeDoorInner;
	ModelRenderer SafeDoor;
	ModelRenderer SafeBase;
	ModelRenderer DoorOL;
	ModelRenderer DoorOR;
	ModelRenderer DoorOU;
	ModelRenderer DoorOD;
	ModelRenderer DoorIU;
	ModelRenderer DoorID;
	ModelRenderer DoorIL;
	ModelRenderer DoorIR;

	public ModelSafe()
	{
		textureWidth = 128;
		textureHeight = 128;

		SafeDoorInner = new ModelRenderer(this, 56, 14);
		SafeDoorInner.addBox(0F, 0F, 0F, 10, 11, 1);
		SafeDoorInner.setRotationPoint(-11F, 1F, 1F);
		SafeDoorInner.setTextureSize(128, 128);
		SafeDoorInner.mirror = true;
		setRotation(SafeDoorInner, 0F, 0F, 0F);
		SafeDoor = new ModelRenderer(this, 52, 0);
		SafeDoor.addBox(-12F, 0F, 0F, 12, 13, 1);
		SafeDoor.setRotationPoint(6F, 10F, -6F);
		SafeDoor.setTextureSize(128, 128);
		SafeDoor.mirror = true;
		setRotation(SafeDoor, 0F, -90F, 0F);
		SafeDoor.addChild(SafeDoorInner);
		SafeBase = new ModelRenderer(this, 78, 0);
		SafeBase.addBox(-14F, 0F, 0F, 14, 15, 11);
		SafeBase.setRotationPoint(7F, 9F, -4F);
		SafeBase.setTextureSize(128, 128);
		SafeBase.mirror = true;
		setRotation(SafeBase, 0F, 0F, 0F);
		DoorOL = new ModelRenderer(this, 0, 32);
		DoorOL.addBox(0F, 0F, -2F, 1, 15, 2);
		DoorOL.setRotationPoint(-7F, 9F, -4F);
		DoorOL.setTextureSize(128, 128);
		DoorOL.mirror = true;
		setRotation(DoorOL, 0F, 0F, 0F);
		DoorOR = new ModelRenderer(this, 0, 31);
		DoorOR.addBox(0F, 0F, -2F, 1, 15, 2);
		DoorOR.setRotationPoint(6F, 9F, -4F);
		DoorOR.setTextureSize(128, 128);
		DoorOR.mirror = true;
		setRotation(DoorOR, 0F, 0F, 0F);
		DoorOU = new ModelRenderer(this, 20, 0);
		DoorOU.addBox(0F, 0F, 0F, 12, 1, 2);
		DoorOU.setRotationPoint(-6F, 9F, -6F);
		DoorOU.setTextureSize(128, 128);
		DoorOU.mirror = true;
		setRotation(DoorOU, 0F, 0F, 0F);
		DoorOD = new ModelRenderer(this, 19, 0);
		DoorOD.addBox(0F, 0F, 0F, 12, 1, 2);
		DoorOD.setRotationPoint(-6F, 23F, -6F);
		DoorOD.setTextureSize(128, 128);
		DoorOD.mirror = true;
		setRotation(DoorOD, 0F, 0F, 0F);
		DoorIU = new ModelRenderer(this, 25, 3);
		DoorIU.addBox(0F, 0F, 0F, 12, 1, 1);
		DoorIU.setRotationPoint(-6F, 10F, -5F);
		DoorIU.setTextureSize(128, 128);
		DoorIU.mirror = true;
		setRotation(DoorIU, 0F, 0F, 0F);
		DoorID = new ModelRenderer(this, 25, 3);
		DoorID.addBox(0F, 0F, 0F, 12, 1, 1);
		DoorID.setRotationPoint(-6F, 22F, -5F);
		DoorID.setTextureSize(128, 128);
		DoorID.mirror = true;
		setRotation(DoorID, 0F, 0F, 0F);
		DoorIL = new ModelRenderer(this, 0, 0);
		DoorIL.addBox(0F, 0F, 0F, 1, 13, 1);
		DoorIL.setRotationPoint(-6F, 10F, -5F);
		DoorIL.setTextureSize(128, 128);
		DoorIL.mirror = true;
		setRotation(DoorIL, 0F, 0F, 0F);
		DoorIR = new ModelRenderer(this, 0, 0);
		DoorIR.addBox(0F, 0F, 0F, 1, 13, 1);
		DoorIR.setRotationPoint(5F, 10F, -5F);
		DoorIR.setTextureSize(128, 128);
		DoorIR.mirror = true;
		setRotation(DoorIR, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		SafeDoorInner.render(f5);
		SafeDoor.render(f5);
		SafeBase.render(f5);
		DoorOL.render(f5);
		DoorOR.render(f5);
		DoorOU.render(f5);
		DoorOD.render(f5);
		DoorIU.render(f5);
		DoorID.render(f5);
		DoorIL.render(f5);
		DoorIR.render(f5);
	}

	public void renderModel(float f1)
	{
		SafeDoor.render(f1);
		SafeBase.render(f1);
		DoorOL.render(f1);
		DoorOR.render(f1);
		DoorOU.render(f1);
		DoorOD.render(f1);
		DoorIU.render(f1);
		DoorID.render(f1);
		DoorIL.render(f1);
		DoorIR.render(f1);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
	}

}