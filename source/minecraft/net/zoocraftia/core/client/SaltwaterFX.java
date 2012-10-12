package net.zoocraftia.core.client;

import net.minecraft.src.RenderEngine;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLTextureFX;

public class SaltwaterFX extends FMLTextureFX
{

    protected float red[];
    protected float green[];
    protected float blue[];
    protected float alpha[];
    private int tickCounter;
    private boolean isAnimated;
    public int opacity;

    public SaltwaterFX(int i, boolean flag)
    {
        super(i);
        iconIndex = i;
        if(flag)
        {
            tileSize = 2;
        } else
        {
            tileSize = 1;
        }
        isAnimated = flag;
        tileImage = 2;
        opacity = 120;
    }
    
    public void setup()
    {
    	super.setup();
        red = new float[tileSizeSquare];
        green = new float[tileSizeSquare];
        blue = new float[tileSizeSquare];
        alpha = new float[tileSizeSquare];
        tickCounter = 0;
    }

    public void onTick()
    {
        super.onTick();
        if(isAnimated)
        {
            tickCounter++;
            for(int k = 0; k < tileSizeBase; k++)
            {
                for(int k1 = 0; k1 < tileSizeBase; k1++)
                {
                    float f = 0.0F;
                    for(int i3 = k1 - 2; i3 <= k1; i3++)
                    {
                        int k3 = k & tileSizeMask;
                        int k4 = i3 & tileSizeMask;
                        f += red[k3 + k4 * tileSizeBase];
                    }

                    green[k + k1 * tileSizeBase] = f / 3.2F + blue[k + k1 * tileSizeBase] * 0.8F;
                }

            }

            for(int l = 0; l < tileSizeBase; l++)
            {
                for(int l1 = 0; l1 < tileSizeBase; l1++)
                {
                    blue[l + l1 * tileSizeBase] += alpha[l + l1 * tileSizeBase] * 0.05F;
                    if(blue[l + l1 * tileSizeBase] < 0.0F)
                    {
                        blue[l + l1 * tileSizeBase] = 0.0F;
                    }
                    alpha[l + l1 * tileSizeBase] -= 0.3F;
                    if(Math.random() < 0.20000000000000001D)
                    {
                        alpha[l + l1 * tileSizeBase] = 0.5F;
                    }
                }

            }

            float af[] = green;
            green = red;
            red = af;
            for(int i2 = 0; i2 < tileSizeSquare; i2++)
            {
                float f1 = red[i2 - tickCounter * tileSizeBase & tileSizeSquareMask];
                if(f1 > 1.0F)
                {
                    f1 = 1.0F;
                }
                if(f1 < 0.0F)
                {
                    f1 = 0.0F;
                }
                float f4 = f1 * f1;
                int l3 = (int)(32F + f4 * 32F);
                int l4 = (int)(50F + f4 * 64F);
                int k5 = 255;
                int i6 = (int)(146F + f4 * 50F);
                if(anaglyphEnabled)
                {
                    int k6 = (l3 * 30 + l4 * 59 + k5 * 11) / 100;
                    int i7 = (l3 * 30 + l4 * 70) / 100;
                    int k7 = (l3 * 30 + k5 * 70) / 100;
                    l3 = k6;
                    l4 = i7;
                    k5 = k7;
                }
                int i = (l3 + l4 + k5) / 3;
                l3 = i;
                l4 = i;
                k5 = i;
                Integer integer = 0x00F7FF;
                
                if(opacity < 0)
                {
                    opacity = i6;
                }
                imageData[i2 * 4 + 0] = (byte)(int)((double)l3 * ((double)(integer.intValue() >> 16 & 0xff) / 256D));
                imageData[i2 * 4 + 1] = (byte)(int)((double)l4 * ((double)(integer.intValue() >> 8 & 0xff) / 256D));
                imageData[i2 * 4 + 2] = (byte)(int)((double)k5 * ((double)(integer.intValue() & 0xff) / 256D));
                imageData[i2 * 4 + 3] = (byte)opacity;
            }

        } else
        {
            tickCounter++;
            for(int i1 = 0; i1 < tileSizeBase; i1++)
            {
                for(int j2 = 0; j2 < tileSizeBase; j2++)
                {
                    float f2 = 0.0F;
                    for(int j3 = i1 - 1; j3 <= i1 + 1; j3++)
                    {
                        int i4 = j3 & tileSizeMask;
                        int i5 = j2 & tileSizeMask;
                        f2 += red[i4 + i5 * tileSizeBase];
                    }

                    green[i1 + j2 * tileSizeBase] = f2 / 3.3F + blue[i1 + j2 * tileSizeBase] * 0.8F;
                }

            }

            for(int j1 = 0; j1 < tileSizeBase; j1++)
            {
                for(int k2 = 0; k2 < tileSizeBase; k2++)
                {
                    blue[j1 + k2 * tileSizeBase] += alpha[j1 + k2 * tileSizeBase] * 0.05F;
                    if(blue[j1 + k2 * tileSizeBase] < 0.0F)
                    {
                        blue[j1 + k2 * tileSizeBase] = 0.0F;
                    }
                    alpha[j1 + k2 * tileSizeBase] -= 0.1F;
                    if(Math.random() < 0.050000000000000003D)
                    {
                        alpha[j1 + k2 * tileSizeBase] = 0.5F;
                    }
                }

            }

            float af1[] = green;
            green = red;
            red = af1;
            for(int l2 = 0; l2 < tileSizeSquare; l2++)
            {
                float f3 = red[l2];
                if(f3 > 1.0F)
                {
                    f3 = 1.0F;
                }
                if(f3 < 0.0F)
                {
                    f3 = 0.0F;
                }
                float f5 = f3 * f3;
                int j4 = (int)(32F + f5 * 32F);
                int j5 = (int)(50F + f5 * 64F);
                int l5 = 255;
                int j6 = (int)(146F + f5 * 50F);
                if(anaglyphEnabled)
                {
                    int l6 = (j4 * 30 + j5 * 59 + l5 * 11) / 100;
                    int j7 = (j4 * 30 + j5 * 70) / 100;
                    int l7 = (j4 * 30 + l5 * 70) / 100;
                    j4 = l6;
                    j5 = j7;
                    l5 = l7;
                }
                int j = (j4 + j5 + l5) / 3;
                j4 = j;
                j5 = j;
                l5 = j;
                Integer integer1 = 0x00F7FF;
                
                if(opacity < 0)
                {
                    opacity = j6;
                }
                imageData[l2 * 4 + 0] = (byte)(int)((double)j4 * ((double)(integer1.intValue() >> 16 & 0xff) / 256D));
                imageData[l2 * 4 + 1] = (byte)(int)((double)j5 * ((double)(integer1.intValue() >> 8 & 0xff) / 256D));
                imageData[l2 * 4 + 2] = (byte)(int)((double)l5 * ((double)(integer1.intValue() & 0xff) / 256D));
                imageData[l2 * 4 + 3] = (byte)opacity;
            }

        }
    }

    public void bindImage(RenderEngine renderengine)
    {
        if(tileImage == 2)
        {
            GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, renderengine.getTexture("/zoocraftia/core/textures/water.png"));
        } else
        {
            super.bindImage(renderengine);
        }
    }
}
