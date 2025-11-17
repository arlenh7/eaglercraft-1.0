// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.client;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.io.PrintStream;
import java.net.URL;
import net.minecraft.src.*;

// Referenced classes of package net.minecraft.client:
//            Minecraft

public class MinecraftApplet
{

    private Canvas mcCanvas;
    private Minecraft mc;
    private Thread mcThread;

    public MinecraftApplet()
    {
        mcThread = null;
    }

    public void init()
    {
        mcCanvas = new CanvasMinecraftApplet(this);
        boolean flag = false;
    
        mcCanvas.setFocusable(true);
        return;
    }

    public void startMainThread()
    {
        if(mcThread != null)
        {
            return;
        } else
        {
            mcThread = new Thread(mc, "Minecraft main thread");
            mcThread.start();
            return;
        }
    }

    public void start()
    {
        if(mc != null)
        {
            mc.isGamePaused = false;
        }
    }

    public void stop()
    {
        if(mc != null)
        {
            mc.isGamePaused = true;
        }
    }

    public void destroy()
    {
        shutdown();
    }

    public void shutdown()
    {
        if(mcThread == null)
        {
            return;
        }
        mc.shutdown();
        try
        {
            mcThread.join(10000L);
        }
        catch(InterruptedException interruptedexception)
        {
            try
            {
                mc.shutdownMinecraftApplet();
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        }
        mcThread = null;
    }

    public void clearApplet()
    {
        mcCanvas = null;
        mc = null;
        mcThread = null;
        try
        {
        }
        catch(Exception exception) { }
    }
}
