package net.zoocraftia.api.client;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.OpenGlHelper;
import net.minecraft.src.Render;
import net.minecraft.src.RenderEngine;
import net.minecraft.src.Tessellator;
import net.minecraft.src.World;
import net.zoocraftia.api.BaseEntity;
import net.zoocraftia.api.ZoocraftiaException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class ZoocraftiaEntityRender extends Render
{
    protected ModelBase mainModel;
    protected ModelBase renderPassModel;

    /**
     * Sets the model to be used in the current render pass (the first render pass is done after the primary model is
     * rendered) Args: model
     */
    public void setRenderPassModel(ModelBase par1ModelBase)
    {
        this.renderPassModel = par1ModelBase;
    }

    private float func_77034_a(float par1, float par2, float par3)
    {
        float var4;

        for (var4 = par2 - par1; var4 < -180.0F; var4 += 360.0F)
        {
            ;
        }

        while (var4 >= 180.0F)
        {
            var4 -= 360.0F;
        }

        return par1 + par3 * var4;
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        
        if(mainModel == null && par1EntityLiving instanceof BaseEntity)
        {
        	mainModel = ((BaseEntity)par1EntityLiving).getModel();
        }
        
        this.mainModel.onGround = this.renderSwingProgress(par1EntityLiving, par9);

        if (this.renderPassModel != null)
        {
            this.renderPassModel.onGround = this.mainModel.onGround;
        }

        this.mainModel.isRiding = par1EntityLiving.isRiding();

        if (this.renderPassModel != null)
        {
            this.renderPassModel.isRiding = this.mainModel.isRiding;
        }

        this.mainModel.isChild = par1EntityLiving.isChild();

        if (this.renderPassModel != null)
        {
            this.renderPassModel.isChild = this.mainModel.isChild;
        }

        try
        {
            float var10 = this.func_77034_a(par1EntityLiving.prevRenderYawOffset, par1EntityLiving.renderYawOffset, par9);
            float var11 = this.func_77034_a(par1EntityLiving.prevRotationYawHead, par1EntityLiving.rotationYawHead, par9);
            float var12 = par1EntityLiving.prevRotationPitch + (par1EntityLiving.rotationPitch - par1EntityLiving.prevRotationPitch) * par9;
            this.renderLivingAt(par1EntityLiving, par2, par4, par6);
            float var13 = this.handleRotationFloat(par1EntityLiving, par9);
            this.rotateCorpse(par1EntityLiving, var13, var10, par9);
            float var14 = 0.0625F;
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glScalef(-1.0F, -1.0F, 1.0F);
            this.preRenderCallback(par1EntityLiving, par9);
            GL11.glTranslatef(0.0F, -24.0F * var14 - 0.0078125F, 0.0F);
            float var15 = par1EntityLiving.prevLegYaw + (par1EntityLiving.legYaw - par1EntityLiving.prevLegYaw) * par9;
            float var16 = par1EntityLiving.legSwing - par1EntityLiving.legYaw * (1.0F - par9);

            if (par1EntityLiving.isChild())
            {
                var16 *= 3.0F;
            }

            if (var15 > 1.0F)
            {
                var15 = 1.0F;
            }

            GL11.glEnable(GL11.GL_ALPHA_TEST);
            this.mainModel.setLivingAnimations(par1EntityLiving, var16, var15, par9);
            this.renderModel(par1EntityLiving, var16, var15, var13, var11 - var10, var12, var14);
            float var19;
            int var18;
            float var20;
            float var22;

            for (int var17 = 0; var17 < 4; ++var17)
            {
                var18 = this.shouldRenderPass(par1EntityLiving, var17, par9);

                if (var18 > 0)
                {
                    this.renderPassModel.setLivingAnimations(par1EntityLiving, var16, var15, par9);
                    this.renderPassModel.render(par1EntityLiving, var16, var15, var13, var11 - var10, var12, var14);

                    if (var18 == 15)
                    {
                        var19 = (float)par1EntityLiving.ticksExisted + par9;
                        this.loadTexture("%blur%/misc/glint.png");
                        GL11.glEnable(GL11.GL_BLEND);
                        var20 = 0.5F;
                        GL11.glColor4f(var20, var20, var20, 1.0F);
                        GL11.glDepthFunc(GL11.GL_EQUAL);
                        GL11.glDepthMask(false);

                        for (int var21 = 0; var21 < 2; ++var21)
                        {
                            GL11.glDisable(GL11.GL_LIGHTING);
                            var22 = 0.76F;
                            GL11.glColor4f(0.5F * var22, 0.25F * var22, 0.8F * var22, 1.0F);
                            GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
                            GL11.glMatrixMode(GL11.GL_TEXTURE);
                            GL11.glLoadIdentity();
                            float var23 = var19 * (0.001F + (float)var21 * 0.003F) * 20.0F;
                            float var24 = 0.33333334F;
                            GL11.glScalef(var24, var24, var24);
                            GL11.glRotatef(30.0F - (float)var21 * 60.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glTranslatef(0.0F, var23, 0.0F);
                            GL11.glMatrixMode(GL11.GL_MODELVIEW);
                            this.renderPassModel.render(par1EntityLiving, var16, var15, var13, var11 - var10, var12, var14);
                        }

                        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                        GL11.glMatrixMode(GL11.GL_TEXTURE);
                        GL11.glDepthMask(true);
                        GL11.glLoadIdentity();
                        GL11.glMatrixMode(GL11.GL_MODELVIEW);
                        GL11.glEnable(GL11.GL_LIGHTING);
                        GL11.glDisable(GL11.GL_BLEND);
                        GL11.glDepthFunc(GL11.GL_LEQUAL);
                    }

                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                }
            }

            this.renderEquippedItems(par1EntityLiving, par9);
            float var26 = par1EntityLiving.getBrightness(par9);
            var18 = this.getColorMultiplier(par1EntityLiving, var26, par9);
            OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);

            if ((var18 >> 24 & 255) > 0 || par1EntityLiving.hurtTime > 0 || par1EntityLiving.deathTime > 0)
            {
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glDepthFunc(GL11.GL_EQUAL);

                if (par1EntityLiving.hurtTime > 0 || par1EntityLiving.deathTime > 0)
                {
                    GL11.glColor4f(var26, 0.0F, 0.0F, 0.4F);
                    this.mainModel.render(par1EntityLiving, var16, var15, var13, var11 - var10, var12, var14);

                    for (int var27 = 0; var27 < 4; ++var27)
                    {
                        if (this.inheritRenderPass(par1EntityLiving, var27, par9) >= 0)
                        {
                            GL11.glColor4f(var26, 0.0F, 0.0F, 0.4F);
                            this.renderPassModel.render(par1EntityLiving, var16, var15, var13, var11 - var10, var12, var14);
                        }
                    }
                }

                if ((var18 >> 24 & 255) > 0)
                {
                    var19 = (float)(var18 >> 16 & 255) / 255.0F;
                    var20 = (float)(var18 >> 8 & 255) / 255.0F;
                    float var29 = (float)(var18 & 255) / 255.0F;
                    var22 = (float)(var18 >> 24 & 255) / 255.0F;
                    GL11.glColor4f(var19, var20, var29, var22);
                    this.mainModel.render(par1EntityLiving, var16, var15, var13, var11 - var10, var12, var14);

                    for (int var28 = 0; var28 < 4; ++var28)
                    {
                        if (this.inheritRenderPass(par1EntityLiving, var28, par9) >= 0)
                        {
                            GL11.glColor4f(var19, var20, var29, var22);
                            this.renderPassModel.render(par1EntityLiving, var16, var15, var13, var11 - var10, var12, var14);
                        }
                    }
                }

                GL11.glDepthFunc(GL11.GL_LEQUAL);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
            }

            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        }
        catch (Exception var25)
        {
            var25.printStackTrace();
        }

        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
        this.passSpecialRender(par1EntityLiving, par2, par4, par6);
    }

    /**
     * Renders the model in RenderLiving
     */
    protected void renderModel(EntityLiving par1EntityLiving, float par2, float par3, float par4, float par5, float par6, float par7)
    {
    	if(mainModel == null && par1EntityLiving instanceof BaseEntity)
        {
        	mainModel = ((BaseEntity)par1EntityLiving).getModel();
        }
        this.loadDownloadableImageTexture(par1EntityLiving.skinUrl, ((BaseEntity)par1EntityLiving).getTexture());
        this.mainModel.render(par1EntityLiving, par2, par3, par4, par5, par6, par7);
    }

    /**
     * Sets a simple glTranslate on a LivingEntity.
     */
    protected void renderLivingAt(EntityLiving par1EntityLiving, double par2, double par4, double par6)
    {
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
    }

    protected void rotateCorpse(EntityLiving par1EntityLiving, float par2, float par3, float par4)
    {
        GL11.glRotatef(180.0F - par3, 0.0F, 1.0F, 0.0F);

        if (par1EntityLiving.deathTime > 0)
        {
            float var5 = ((float)par1EntityLiving.deathTime + par4 - 1.0F) / 20.0F * 1.6F;
            var5 = MathHelper.sqrt_float(var5);

            if (var5 > 1.0F)
            {
                var5 = 1.0F;
            }

            GL11.glRotatef(var5 * this.getDeathMaxRotation(par1EntityLiving), 0.0F, 0.0F, 1.0F);
        }
    }

    protected float renderSwingProgress(EntityLiving par1EntityLiving, float par2)
    {
        return par1EntityLiving.getSwingProgress(par2);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLiving par1EntityLiving, float par2)
    {
        return (float)par1EntityLiving.ticksExisted + par2;
    }

    protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2) {}

    protected int inheritRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.shouldRenderPass(par1EntityLiving, par2, par3);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return -1;
    }

    protected float getDeathMaxRotation(EntityLiving par1EntityLiving)
    {
        return 90.0F;
    }

    /**
     * Returns an ARGB int color back. Args: entityLiving, lightBrightness, partialTickTime
     */
    protected int getColorMultiplier(EntityLiving par1EntityLiving, float par2, float par3)
    {
        return 0;
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLiving par1EntityLiving, float par2) {}

    /**
     * Passes the specialRender and renders it
     */
    protected void passSpecialRender(EntityLiving par1EntityLiving, double par2, double par4, double par6)
    {
        if (Minecraft.isDebugInfoEnabled())
        {
            ;
        }
    }

    /**
     * Draws the debug or playername text above a living
     */
    protected void renderLivingLabel(EntityLiving par1EntityLiving, String par2Str, double par3, double par5, double par7, int par9)
    {
        double var10 = par1EntityLiving.getDistanceSqToEntity(this.renderManager.livingPlayer);

        if (var10 <= (double)(par9 * par9))
        {
            FontRenderer var12 = this.getFontRendererFromRenderManager();
            float var13 = 1.6F;
            float var14 = 0.016666668F * var13;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)par3 + 0.0F, (float)par5 + 2.3F, (float)par7);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(-var14, -var14, var14);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDepthMask(false);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            Tessellator var15 = Tessellator.instance;
            byte var16 = 0;

            if (par2Str.equals("deadmau5"))
            {
                var16 = -10;
            }

            GL11.glDisable(GL11.GL_TEXTURE_2D);
            var15.startDrawingQuads();
            int var17 = var12.getStringWidth(par2Str) / 2;
            var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
            var15.addVertex((double)(-var17 - 1), (double)(-1 + var16), 0.0D);
            var15.addVertex((double)(-var17 - 1), (double)(8 + var16), 0.0D);
            var15.addVertex((double)(var17 + 1), (double)(8 + var16), 0.0D);
            var15.addVertex((double)(var17 + 1), (double)(-1 + var16), 0.0D);
            var15.draw();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            var12.drawString(par2Str, -var12.getStringWidth(par2Str) / 2, var16, 553648127);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthMask(true);
            var12.drawString(par2Str, -var12.getStringWidth(par2Str) / 2, var16, -1);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
        }
    }
    
    public void doRenderShadowAndFire(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
    	if(par1Entity instanceof BaseEntity && shadowSize == 0.0F)
        {
        	shadowSize = ((BaseEntity)par1Entity).getShadowSize();
        }
    	
        if (this.renderManager.options.fancyGraphics && this.shadowSize > 0.0F)
        {
            double var10 = this.renderManager.getDistanceToCamera(par1Entity.posX, par1Entity.posY, par1Entity.posZ);
            float var12 = (float)((1.0D - var10 / 256.0D) * (double)this.shadowOpaque);

            if (var12 > 0.0F)
            {
                this.renderShadow(par1Entity, par2, par4, par6, var12, par9);
            }
        }

        if (par1Entity.isBurning())
        {
            this.renderEntityOnFire(par1Entity, par2, par4, par6, par9);
        }
    }
    
    private void renderShadow(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderEngine var10 = this.renderManager.renderEngine;
        var10.bindTexture(var10.getTexture("%clamp%/misc/shadow.png"));
        World var11 = renderManager.worldObj;
        GL11.glDepthMask(false);
        
        if(par1Entity instanceof BaseEntity && shadowSize == 0.0F)
        {
        	shadowSize = ((BaseEntity)par1Entity).getShadowSize();
        }
        
        float var12 = this.shadowSize;

        if (par1Entity instanceof EntityLiving)
        {
            EntityLiving var13 = (EntityLiving)par1Entity;
            var12 *= var13.getRenderSizeModifier();

            if (var13 instanceof EntityAnimal)
            {
                EntityAnimal var14 = (EntityAnimal)var13;

                if (var14.isChild())
                {
                    var12 *= 0.5F;
                }
            }
        }
        
        double var36 = par1Entity.lastTickPosX + (par1Entity.posX - par1Entity.lastTickPosX) * (double)par9;
        double var15 = par1Entity.lastTickPosY + (par1Entity.posY - par1Entity.lastTickPosY) * (double)par9 + (double)par1Entity.getShadowSize();
        double var17 = par1Entity.lastTickPosZ + (par1Entity.posZ - par1Entity.lastTickPosZ) * (double)par9;
        int var19 = MathHelper.floor_double(var36 - (double)var12);
        int var20 = MathHelper.floor_double(var36 + (double)var12);
        int var21 = MathHelper.floor_double(var15 - (double)var12);
        int var22 = MathHelper.floor_double(var15);
        int var23 = MathHelper.floor_double(var17 - (double)var12);
        int var24 = MathHelper.floor_double(var17 + (double)var12);
        double var25 = par2 - var36;
        double var27 = par4 - var15;
        double var29 = par6 - var17;
        Tessellator var31 = Tessellator.instance;
        var31.startDrawingQuads();

        for (int var32 = var19; var32 <= var20; ++var32)
        {
            for (int var33 = var21; var33 <= var22; ++var33)
            {
                for (int var34 = var23; var34 <= var24; ++var34)
                {
                    int var35 = var11.getBlockId(var32, var33 - 1, var34);

                    if (var35 > 0 && var11.getBlockLightValue(var32, var33, var34) > 3)
                    {
                        this.renderShadowOnBlock(Block.blocksList[var35], par2, par4 + (double)par1Entity.getShadowSize(), par6, var32, var33, var34, par8, var12, var25, var27 + (double)par1Entity.getShadowSize(), var29);
                    }
                }
            }
        }

        var31.draw();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRenderLiving((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
        if(par1Entity instanceof BaseEntity)
    	{
    		((BaseEntity)par1Entity).doRender(par2, par4, par6, par8, par9);
    	}else{
    		throw new ZoocraftiaException("Impossible Exception in " + this.getClass().getSimpleName());
    	}
    }
    
    private void renderEntityOnFire(Entity par1Entity, double par2, double par4, double par6, float par8)
    {
        GL11.glDisable(GL11.GL_LIGHTING);
        int var9 = Block.fire.blockIndexInTexture;
        int var10 = (var9 & 15) << 4;
        int var11 = var9 & 240;
        float var12 = (float)var10 / 256.0F;
        float var13 = ((float)var10 + 15.99F) / 256.0F;
        float var14 = (float)var11 / 256.0F;
        float var15 = ((float)var11 + 15.99F) / 256.0F;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        float var16 = par1Entity.width * 1.4F;
        GL11.glScalef(var16, var16, var16);
        this.loadTexture("/terrain.png");
        Tessellator var17 = Tessellator.instance;
        float var18 = 0.5F;
        float var19 = 0.0F;
        float var20 = par1Entity.height / var16;
        float var21 = (float)(par1Entity.posY - par1Entity.boundingBox.minY);
        GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, 0.0F, -0.3F + (float)((int)var20) * 0.02F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float var22 = 0.0F;
        int var23 = 0;
        var17.startDrawingQuads();

        while (var20 > 0.0F)
        {
            if (var23 % 2 == 0)
            {
                var12 = (float)var10 / 256.0F;
                var13 = ((float)var10 + 15.99F) / 256.0F;
                var14 = (float)var11 / 256.0F;
                var15 = ((float)var11 + 15.99F) / 256.0F;
            }
            else
            {
                var12 = (float)var10 / 256.0F;
                var13 = ((float)var10 + 15.99F) / 256.0F;
                var14 = (float)(var11 + 16) / 256.0F;
                var15 = ((float)(var11 + 16) + 15.99F) / 256.0F;
            }

            if (var23 / 2 % 2 == 0)
            {
                float var24 = var13;
                var13 = var12;
                var12 = var24;
            }

            var17.addVertexWithUV((double)(var18 - var19), (double)(0.0F - var21), (double)var22, (double)var13, (double)var15);
            var17.addVertexWithUV((double)(-var18 - var19), (double)(0.0F - var21), (double)var22, (double)var12, (double)var15);
            var17.addVertexWithUV((double)(-var18 - var19), (double)(1.4F - var21), (double)var22, (double)var12, (double)var14);
            var17.addVertexWithUV((double)(var18 - var19), (double)(1.4F - var21), (double)var22, (double)var13, (double)var14);
            var20 -= 0.45F;
            var21 -= 0.45F;
            var18 *= 0.9F;
            var22 += 0.03F;
            ++var23;
        }

        var17.draw();
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
    }
    
    private void renderShadowOnBlock(Block par1Block, double par2, double par4, double par6, int par8, int par9, int par10, float par11, float par12, double par13, double par15, double par17)
    {
        Tessellator var19 = Tessellator.instance;

        if (par1Block.renderAsNormalBlock())
        {
            double var20 = ((double)par11 - (par4 - ((double)par9 + par15)) / 2.0D) * 0.5D * (double)this.renderManager.worldObj.getLightBrightness(par8, par9, par10);

            if (var20 >= 0.0D)
            {
                if (var20 > 1.0D)
                {
                    var20 = 1.0D;
                }

                var19.setColorRGBA_F(1.0F, 1.0F, 1.0F, (float)var20);
                double var22 = (double)par8 + par1Block.func_83009_v() + par13;
                double var24 = (double)par8 + par1Block.func_83007_w() + par13;
                double var26 = (double)par9 + par1Block.func_83008_x() + par15 + 0.015625D;
                double var28 = (double)par10 + par1Block.func_83005_z() + par17;
                double var30 = (double)par10 + par1Block.func_83006_A() + par17;
                float var32 = (float)((par2 - var22) / 2.0D / (double)par12 + 0.5D);
                float var33 = (float)((par2 - var24) / 2.0D / (double)par12 + 0.5D);
                float var34 = (float)((par6 - var28) / 2.0D / (double)par12 + 0.5D);
                float var35 = (float)((par6 - var30) / 2.0D / (double)par12 + 0.5D);
                var19.addVertexWithUV(var22, var26, var28, (double)var32, (double)var34);
                var19.addVertexWithUV(var22, var26, var30, (double)var32, (double)var35);
                var19.addVertexWithUV(var24, var26, var30, (double)var33, (double)var35);
                var19.addVertexWithUV(var24, var26, var28, (double)var33, (double)var34);
            }
        }
    }

}
