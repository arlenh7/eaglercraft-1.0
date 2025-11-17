// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import net.lax1dude.eaglercraft.internal.buffer.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;

// Referenced classes of package net.minecraft.src:
//            GLAllocation

public class MouseHelper
{

    private Component windowComponent;
    public int deltaX;
    public int deltaY;
    private int field_1115_e;

    public MouseHelper(Component component)
    {
        field_1115_e = 10;
        windowComponent = component;
        IntBuffer intbuffer = GLAllocation.createDirectIntBuffer(1);
        intbuffer.put(0);
        intbuffer.flip();
        IntBuffer intbuffer1 = GLAllocation.createDirectIntBuffer(1024);
    }

    public void grabMouseCursor()
    {
        Mouse.setGrabbed(true);
        deltaX = 0;
        deltaY = 0;
    }

    public void ungrabMouseCursor()
    {
        Mouse.setCursorPosition(windowComponent.getWidth() / 2, windowComponent.getHeight() / 2);
        Mouse.setGrabbed(false);
    }

    public void mouseXYChange()
    {
        deltaX = Mouse.getDX();
        deltaY = Mouse.getDY();
    }
}
