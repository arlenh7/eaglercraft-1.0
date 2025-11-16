// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            NBTBase

public class NBTTagEnd extends NBTBase
{

    public void write(DataOutput var1) throws IOException {

    }

	public void read(DataInput var1, int var2, NBTSizeTracker var3) throws IOException {
        
    }

    public NBTTagEnd()
    {
        super(null);
    }

    void readTagContents(DataInput datainput)
        throws IOException
    {
    }

    void writeTagContents(DataOutput dataoutput)
        throws IOException
    {
    }

    public byte getType()
    {
        return 0;
    }

    public String toString()
    {
        return "END";
    }

    public NBTBase func_40195_b()
    {
        return new NBTTagEnd();
    }

    public boolean equals(Object obj)
    {
        return super.equals(obj);
    }
}
