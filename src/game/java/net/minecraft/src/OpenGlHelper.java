// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import org.lwjgl.opengl.*;

import net.lax1dude.eaglercraft.opengl.EaglercraftGPU;
import net.lax1dude.eaglercraft.opengl.GlStateManager;

public class OpenGlHelper
{

    public static int lightmapDisabled;
    public static int lightmapEnabled;
    private static boolean useMultitextureARB = false;

    public OpenGlHelper()
    {
    }

    public static void initializeTextures()
    {
        if(useMultitextureARB)
        {
            lightmapDisabled = 33984 /*GL_TEXTURE0_ARB*/;
            lightmapEnabled = 33985 /*GL_TEXTURE1_ARB*/;
        } else
        {
            lightmapDisabled = 33984 /*GL_TEXTURE0_ARB*/;
            lightmapEnabled = 33985 /*GL_TEXTURE1_ARB*/;
        }
    }

    public static void setActiveTexture(int i)
    {
        GlStateManager.setActiveTexture(i);
    }

    public static void setClientActiveTexture(int i)
    {
        GlStateManager.setActiveTexture(i);
    }

    public static void setLightmapTextureCoords(int i, float f, float f1)
    {
        
    }

}
