package net.zoocraftia.animals;

import net.minecraft.src.Entity;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;
import net.minecraft.src.World;
import net.zoocraftia.api.BaseEntity;
import net.zoocraftia.api.EntityEnums.EntityType;
import net.zoocraftia.api.EntityEnums.FoodType;

public class ZoocraftiaEntityFlamingo extends BaseEntity {

	public ZoocraftiaEntityFlamingo(World world) {
		super(world);
	}

	public String getTexture() {
		return "/zoocraftia/animals/animalTextures/flamingo.png";
	}

	public int getMaxHunger() {
		return 200;
	}

	public EntityType getEntityType() {
		return EntityType.SMALL_PREY;
	}

	public FoodType getFoodType() {
		return FoodType.HERBIVORE;
	}

	public void doRender(double x, double y, double z, float yaw, float ptt) {
	}
	
	public class ModelFlamingo extends ModelBase{
		    ModelRenderer body;
		    ModelRenderer neck;
		    ModelRenderer right_wing;
		    ModelRenderer left_wing;
		    ModelRenderer HEADBASE;
		    ModelRenderer LEFT_LEG_BASE;
		    ModelRenderer RIGHT_LEG_BASE;
		  
		  public ModelFlamingo()
		  {
		    textureWidth = 64;
		    textureHeight = 32;
		    setTextureOffset("HEADBASE.delete", 0, 0);
		    setTextureOffset("LEFT_LEG_BASE.delete", 0, 0);
		    setTextureOffset("RIGHT_LEG_BASE.delete", 0, 0);
		    
		    body = new ModelRenderer(this, 6, 6);
		      body.addBox(0F, 0F, 0F, 3, 3, 4);
		      body.setRotationPoint(-3F, 15F, -1F);
		      body.setTextureSize(64, 32);
		      body.mirror = true;
		      setRotation(body, 0F, 0F, 0F);
		      neck = new ModelRenderer(this, 0, 0);
		      neck.addBox(0F, 0F, 0F, 1, 5, 1);
		      neck.setRotationPoint(-2F, 10F, -1F);
		      neck.setTextureSize(64, 32);
		      neck.mirror = true;
		      setRotation(neck, 0F, 0F, 0F);
		      right_wing = new ModelRenderer(this, 0, 21);
		      right_wing.addBox(0F, 0F, 0F, 1, 2, 3);
		      right_wing.setRotationPoint(-4F, 15F, -1F);
		      right_wing.setTextureSize(64, 32);
		      right_wing.mirror = true;
		      setRotation(right_wing, 0F, 0F, 0F);
		      left_wing = new ModelRenderer(this, 0, 21);
		      left_wing.addBox(0F, 0F, 0F, 1, 2, 3);
		      left_wing.setRotationPoint(0F, 15F, -1F);
		      left_wing.setTextureSize(64, 32);
		      left_wing.mirror = true;
		      setRotation(left_wing, 0F, 0F, 0F);
		    HEADBASE = new ModelRenderer(this, "HEADBASE");
		    HEADBASE.setRotationPoint(-1.5F, 10F, -0.5F);
		    setRotation(HEADBASE, 0F, 0F, 0F);
		    HEADBASE.mirror = true;
		    ModelRenderer head = new ModelRenderer(this, 7, 0);
		      head.addBox(0F, 0F, 0F, 2, 2, 4);
		      head.setRotationPoint(-1F, -2F, -3.5F);
		      head.setTextureSize(64, 32);
		      head.mirror = true;
		      setRotation(head, 0F, 0F, 0F);
		      ModelRenderer beak2 = new ModelRenderer(this, 0, 17);
		      beak2.addBox(0F, 0F, 0F, 2, 1, 1);
		      beak2.setRotationPoint(-1F, -0.6F, -4.9F);
		      beak2.setTextureSize(64, 32);
		      beak2.mirror = true;
		      setRotation(beak2, 0F, 0F, 0F);
		      ModelRenderer beak1 = new ModelRenderer(this, 0, 16);
		      beak1.addBox(0F, 0F, 0F, 2, 2, 1);
		      beak1.setRotationPoint(-1F, -2.05F, -3.4F);
		      beak1.setTextureSize(64, 32);
		      beak1.mirror = true;
		      setRotation(beak1, -0.7853982F, 0F, 0F);
		      
		      HEADBASE.addChild(head);
		      HEADBASE.addChild(beak1);
		      HEADBASE.addChild(beak2);
		      
		    LEFT_LEG_BASE = new ModelRenderer(this, "LEFT_LEG_BASE");
		    LEFT_LEG_BASE.setRotationPoint(-0.5F, 18F, 1.5F);
		    setRotation(LEFT_LEG_BASE, 0F, 0F, 0F);
		    LEFT_LEG_BASE.mirror = true;
		    ModelRenderer left_leg = new ModelRenderer(this, 0, 7);
		      left_leg.addBox(0F, 0F, 0F, 1, 5, 1);
		      left_leg.setRotationPoint(-0.5F, 0F, -0.5F);
		      left_leg.setTextureSize(64, 32);
		      left_leg.mirror = true;
		      setRotation(left_leg, 0F, 0F, 0F);
		      ModelRenderer left_foot = new ModelRenderer(this, 0, 27);
		      left_foot.addBox(0F, 0F, 0F, 1, 1, 2);
		      left_foot.setRotationPoint(-0.5F, 5F, -1.5F);
		      left_foot.setTextureSize(64, 32);
		      left_foot.mirror = true;
		      setRotation(left_foot, 0F, 0F, 0F);
		      
		      LEFT_LEG_BASE.addChild(left_foot);
		      LEFT_LEG_BASE.addChild(left_leg);
		      
		    RIGHT_LEG_BASE = new ModelRenderer(this, "RIGHT_LEG_BASE");
		    RIGHT_LEG_BASE.setRotationPoint(-2.5F, 18F, 1.5F);
		    setRotation(RIGHT_LEG_BASE, 0F, 0F, 0F);
		    RIGHT_LEG_BASE.mirror = true;
		    ModelRenderer right_leg = new ModelRenderer(this, 0, 7);
		      right_leg.addBox(0F, 0F, 0F, 1, 5, 1);
		      right_leg.setRotationPoint(-0.5F, 0F, -0.5333334F);
		      right_leg.setTextureSize(64, 32);
		      right_leg.mirror = true;
		      setRotation(right_leg, 0F, 0F, 0F);
		      ModelRenderer right_foot = new ModelRenderer(this, 0, 27);
		      right_foot.addBox(0F, 0F, 0F, 1, 1, 2);
		      right_foot.setRotationPoint(-0.5F, 5F, -1.5F);
		      right_foot.setTextureSize(64, 32);
		      right_foot.mirror = true;
		      setRotation(right_foot, 0F, 0F, 0F);
		      
		      RIGHT_LEG_BASE.addChild(right_foot);
		      RIGHT_LEG_BASE.addChild(right_leg);
		  }
		  
		  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
		  {
		    super.render(entity, f, f1, f2, f3, f4, f5);
		    setRotationAngles(f, f1, f2, f3, f4, f5);
		    body.render(f5);
		    neck.render(f5);
		    right_wing.render(f5);
		    left_wing.render(f5);
		    HEADBASE.render(f5);
		    LEFT_LEG_BASE.render(f5);
		    RIGHT_LEG_BASE.render(f5);
		  }
		  
		  private void setRotation(ModelRenderer model, float x, float y, float z)
		  {
		    model.rotateAngleX = x;
		    model.rotateAngleY = y;
		    model.rotateAngleZ = z;
		  }
		  
		  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
		  {
			  HEADBASE.rotateAngleX = f4 / (180F / (float)Math.PI);
		      HEADBASE.rotateAngleY = f3 / (180F / (float)Math.PI);
		      LEFT_LEG_BASE.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		      RIGHT_LEG_BASE.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
		  }

		}

	
	public class ModelOkapi extends ModelBase{
		  //fields
		    ModelRenderer tail;
		    ModelRenderer neck;
		    ModelRenderer body;
		    ModelRenderer leg1;
		    ModelRenderer leg2;
		    ModelRenderer leg3;
		    ModelRenderer leg4;
		    ModelRenderer HEADBASE;
		  
		  public ModelOkapi()
		  {
		    textureWidth = 128;
		    textureHeight = 128;
		    
		      tail = new ModelRenderer(this, 0, 0);
		      tail.addBox(0F, 0F, 0F, 2, 2, 10);
		      tail.setRotationPoint(-3F, 3F, 10F);
		      tail.setTextureSize(128, 128);
		      tail.mirror = true;
		      setRotation(tail, -1.041001F, 0F, 0F);
		      neck = new ModelRenderer(this, 0, 34);
		      neck.addBox(-4F, -4F, -6F, 4, 5, 8);
		      neck.setRotationPoint(0F, 4F, -8F);
		      neck.setTextureSize(128, 128);
		      neck.mirror = true;
		      setRotation(neck, -0.9294653F, 0F, 0F);
		      body = new ModelRenderer(this, 33, 4);
		      body.addBox(-6F, -10F, -7F, 8, 18, 9);
		      body.setRotationPoint(0F, 5F, 2F);
		      body.setTextureSize(128, 128);
		      body.mirror = true;
		      setRotation(body, 1.570796F, 0F, 0F);
		      leg1 = new ModelRenderer(this, 0, 16);
		      leg1.addBox(-3F, 0F, -2F, 3, 12, 3);
		      leg1.setRotationPoint(-3F, 12F, 9F);
		      leg1.setTextureSize(128, 128);
		      leg1.mirror = true;
		      setRotation(leg1, 0F, 0F, 0F);
		      leg2 = new ModelRenderer(this, 0, 16);
		      leg2.addBox(-1F, 0F, -2F, 3, 12, 3);
		      leg2.setRotationPoint(0F, 12F, 9F);
		      leg2.setTextureSize(128, 128);
		      leg2.mirror = true;
		      setRotation(leg2, 0F, 0F, 0F);
		      leg3 = new ModelRenderer(this, 0, 73);
		      leg3.addBox(-3F, 0F, -3F, 3, 12, 3);
		      leg3.setRotationPoint(-3F, 12F, -5F);
		      leg3.setTextureSize(128, 128);
		      leg3.mirror = true;
		      setRotation(leg3, 0F, 0F, 0F);
		      leg4 = new ModelRenderer(this, 0, 73);
		      leg4.addBox(-1F, 0F, -3F, 3, 12, 3);
		      leg4.setRotationPoint(0F, 12F, -5F);
		      leg4.setTextureSize(128, 128);
		      leg4.mirror = true;
		      setRotation(leg4, 0F, 0F, 0F);
		    HEADBASE = new ModelRenderer(this, "HEADBASE");
		    HEADBASE.setRotationPoint(-2F, -2F, -10F);
		    setRotation(HEADBASE, 0F, 0F, 0F);
		    HEADBASE.mirror = true;
	    	  ModelRenderer head = new ModelRenderer(this, 0, 51);
		      head.addBox(0F, 0F, 0F, 4, 4, 6);
		      head.setRotationPoint(-2F, -2F, -4F);
		      head.setTextureSize(128, 128);
		      head.mirror = true;
		      setRotation(head, 0F, 0F, 0F);
		      ModelRenderer nose = new ModelRenderer(this, 29, 34);
		      nose.addBox(0F, 0F, 0F, 3, 3, 3);
		      nose.setRotationPoint(-1.5F, -1F, -7F);
		      nose.setTextureSize(128, 128);
		      nose.mirror = true;
		      setRotation(nose, 0F, 0F, 0F);
		      ModelRenderer ear1 = new ModelRenderer(this, 0, 65);
		      ear1.addBox(0F, 0F, 0F, 2, 3, 1);
		      ear1.setRotationPoint(-2F, -4.7F, 1F);
		      ear1.setTextureSize(128, 128);
		      ear1.mirror = true;
		      setRotation(ear1, 0F, 0F, -0.2974289F);
		      ModelRenderer ear2 = new ModelRenderer(this, 0, 65);
		      ear2.addBox(0F, 0F, 0F, 2, 3, 1);
		      ear2.setRotationPoint(0F, -3.9F, 1F);
		      ear2.setTextureSize(128, 128);
		      ear2.mirror = true;
		      setRotation(ear2, 0F, 0F, 0.296706F);

		      HEADBASE.addChild(head);
		      HEADBASE.addChild(nose);
		      HEADBASE.addChild(ear1);
		      HEADBASE.addChild(ear2);
		  }
		  
		  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
		  {
		    super.render(entity, f, f1, f2, f3, f4, f5);
		    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		    tail.render(f5);
		    neck.render(f5);
		    body.render(f5);
		    leg1.render(f5);
		    leg2.render(f5);
		    leg3.render(f5);
		    leg4.render(f5);
		    HEADBASE.render(f5);
		  }
		  
		  private void setRotation(ModelRenderer model, float x, float y, float z)
		  {
		    model.rotateAngleX = x;
		    model.rotateAngleY = y;
		    model.rotateAngleZ = z;
		  }
		  
		  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
			  super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
		  }

	}
	
	public ModelBase getModel() {
		return new ModelFlamingo();
	}

	public int getAttackStrenght() {
		return 200;
	}

	public float getShadowSize() {
		return 0;
	}

	public ItemStack[] getFoodThatCanBeFed() {
		return new ItemStack[] { new ItemStack(Item.stick) };
	}

	public int getMaxHealth() {
		return 100;
	}

}