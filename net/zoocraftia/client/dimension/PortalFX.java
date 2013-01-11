package net.zoocraftia.client.dimension;

import java.util.Random;

import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.util.MathHelper;
import net.zoocraftia.dimension.ZoocraftiaBlocks;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLTextureFX;

public class PortalFX extends FMLTextureFX
{
    private int portalTickCounter;
    private byte portalTextureData[][];

    public PortalFX()
    {
        super(ZoocraftiaBlocks.portal.blockIndexInTexture);
        setup();
    }
    
    public void setup()
    {
    	super.setup();
        portalTextureData = new byte[32][tileSizeSquare << 4];
        Random random = new Random(100L);
        for (int i = 0; i < 32; i++)
        {
            for (int j = 0; j < tileSizeBase; j++)
            {
                for (int k = 0; k < tileSizeBase; k++)
                {
                    float f = 0.0F;
                    for (int l = 0; l < 2; l++)
                    {
                    	float f1 = (float)(l * tileSizeBase) * 0.5F;
                        float f2 = (float)(l * tileSizeBase) * 0.5F;
                        float f3 = ((float)j - f1) / (float)tileSizeBase * 2.0F;
                        float f4 = ((float)k - f2) / (float)tileSizeBase * 2.0F;
                        if (f3 < -1F)
                        {
                            f3 += 2.0F;
                        }
                        if (f3 >= 1.0F)
                        {
                            f3 -= 2.0F;
                        }
                        if (f4 < -1F)
                        {
                            f4 += 2.0F;
                        }
                        if (f4 >= 1.0F)
                        {
                            f4 -= 2.0F;
                        }
                        float f5 = f3 * f3 + f4 * f4;
                        float f6 = (float)Math.atan2(f4, f3) + ((((float)i / 32F) * 3.141593F * 2.0F - f5 * 10F) + (float)(l * 2)) * (float)(l * 2 - 1);
                        f6 = (MathHelper.sin(f6) + 1.0F) / 2.0F;
                        f6 /= f5 + 1.0F;
                        f += f6 * 0.5F;
                    }

                    f += random.nextFloat() * 0.1F;
                    int i1 = (int)(f * 100F + 155F);//R
                    int j1 = (int)(f * f * 200F + 200F);//G
                    int k1 = (int)(f * f * f * f * 255F);//B
                    int l1 = (int)(f * 100F + 155F);//Alpha
                    int i2 = k * tileSizeBase + j;
                    portalTextureData[i][i2 * 4 + 0] = (byte)32;
                    portalTextureData[i][i2 * 4 + 1] = (byte)181;
                    portalTextureData[i][i2 * 4 + 2] = (byte)28;
                    portalTextureData[i][i2 * 4 + 3] = (byte)l1;
                }
            }
        }
        portalTickCounter = 0;
    }

    public void onTick()
    {
        portalTickCounter++;
        byte abyte0[] = portalTextureData[portalTickCounter & 0x1f];
        for (int i = 0; i < tileSizeSquare; i++)
        {
            int j = abyte0[i * 4 + 0] & 0xff;
            int k = abyte0[i * 4 + 1] & 0xff;
            int l = abyte0[i * 4 + 2] & 0xff;
            int i1 = abyte0[i * 4 + 3] & 0xff;
            if (anaglyphEnabled)
            {
                int j1 = (j * 30 + k * 59 + l * 11) / 100;
                int k1 = (j * 30 + k * 70) / 100;
                int l1 = (j * 30 + l * 70) / 100;
                j = j1;
                k = k1;
                l = l1;
            }
            imageData[i * 4 + 0] = (byte)j;
            imageData[i * 4 + 1] = (byte)k;
            imageData[i * 4 + 2] = (byte)l;
            imageData[i * 4 + 3] = (byte)i1;
        }
    }
    
    public void bindImage(RenderEngine renderengine)
    {
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, renderengine.getTexture("/zoocraftia/dimension/blocks.png"));
    }
}
