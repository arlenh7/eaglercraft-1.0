// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import net.lax1dude.eaglercraft.EaglerZLIB;

// Referenced classes of package net.minecraft.src:
//            NBTBase, NBTTagCompound

public class CompressedStreamTools
{

    public CompressedStreamTools()
    {
    }

    public static NBTTagCompound readCompressed(InputStream is) throws IOException {
		DataInputStream datainputstream = new DataInputStream(
				new BufferedInputStream(EaglerZLIB.newGZIPInputStream(is)));

		NBTTagCompound nbttagcompound;
		try {
			nbttagcompound = read(datainputstream, NBTSizeTracker.INFINITE);
		} finally {
			datainputstream.close();
		}

		return nbttagcompound;
	}

    public static NBTTagCompound read(DataInputStream inputStream) throws IOException {
		/**+
		 * Reads the given DataInput, constructs, and returns an
		 * NBTTagCompound with the data from the DataInput
		 */
		return read(inputStream, NBTSizeTracker.INFINITE);
	}

	/**+
	 * Reads the given DataInput, constructs, and returns an
	 * NBTTagCompound with the data from the DataInput
	 */
	public static NBTTagCompound read(DataInput parDataInput, NBTSizeTracker parNBTSizeTracker) throws IOException {
		NBTBase nbtbase = func_152455_a(parDataInput, 0, parNBTSizeTracker);
		if (nbtbase instanceof NBTTagCompound) {
			return (NBTTagCompound) nbtbase;
		} else {
			throw new IOException("Root tag must be a named compound tag");
		}
	}

    public static NBTTagCompound loadGzippedCompoundFromOutputStream(InputStream inputstream)
        throws IOException
    {
        DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(inputstream)));
        try
        {
            NBTTagCompound nbttagcompound = func_1141_a(datainputstream);
            return nbttagcompound;
        }
        finally
        {
            datainputstream.close();
        }
    }

    public static void writeGzippedCompoundToOutputStream(NBTTagCompound nbttagcompound, OutputStream outputstream)
        throws IOException
    {
        DataOutputStream dataoutputstream = new DataOutputStream(new GZIPOutputStream(outputstream));
        try
        {
            writeTo(nbttagcompound, dataoutputstream);
        }
        finally
        {
            dataoutputstream.close();
        }
    }

    public static NBTTagCompound func_40592_a(byte abyte0[])
        throws IOException
    {
        DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(abyte0))));
        try
        {
            NBTTagCompound nbttagcompound = func_1141_a(datainputstream);
            return nbttagcompound;
        }
        finally
        {
            datainputstream.close();
        }
    }

    public static byte[] func_40591_a(NBTTagCompound nbttagcompound)
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        DataOutputStream dataoutputstream = new DataOutputStream(new GZIPOutputStream(bytearrayoutputstream));
        try
        {
            writeTo(nbttagcompound, dataoutputstream);
        }
        finally
        {
            dataoutputstream.close();
        }
        return bytearrayoutputstream.toByteArray();
    }

    public static void func_35621_a(NBTTagCompound nbttagcompound, File file)
        throws IOException
    {
        File file1 = new File((new StringBuilder()).append(file.getAbsolutePath()).append("_tmp").toString());
        if(file1.exists())
        {
            file1.delete();
        }
        func_35620_b(nbttagcompound, file1);
        if(file.exists())
        {
            file.delete();
        }
        if(file.exists())
        {
            throw new IOException((new StringBuilder()).append("Failed to delete ").append(file).toString());
        } else
        {
            file1.renameTo(file);
            return;
        }
    }

    public static void func_35620_b(NBTTagCompound nbttagcompound, File file)
        throws IOException
    {
        DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(file));
        try
        {
            writeTo(nbttagcompound, dataoutputstream);
        }
        finally
        {
            dataoutputstream.close();
        }
    }

    public static NBTTagCompound func_35622_a(File file)
        throws IOException
    {
        if(!file.exists())
        {
            return null;
        }
        DataInputStream datainputstream = new DataInputStream(new FileInputStream(file));
        try
        {
            NBTTagCompound nbttagcompound = func_1141_a(datainputstream);
            return nbttagcompound;
        }
        finally
        {
            datainputstream.close();
        }
    }

    public static NBTTagCompound func_1141_a(DataInput datainput)
        throws IOException
    {
        NBTBase nbtbase = NBTBase.readTag(datainput);
        if(nbtbase instanceof NBTTagCompound)
        {
            return (NBTTagCompound)nbtbase;
        } else
        {
            throw new IOException("Root tag must be a named compound tag");
        }
    }

    public static void writeTo(NBTTagCompound nbttagcompound, DataOutput dataoutput)
        throws IOException
    {
        NBTBase.writeTag(nbttagcompound, dataoutput);
    }

    private static NBTBase func_152455_a(DataInput parDataInput, int parInt1, NBTSizeTracker parNBTSizeTracker)
			throws IOException {
		byte b0 = parDataInput.readByte();
		if (b0 == 0) {
			return new NBTTagEnd();
		} else {
			parDataInput.readUTF();
			NBTBase nbtbase = NBTBase.createNewByType(b0);

			try {
				nbtbase.read(parDataInput, parInt1, parNBTSizeTracker);
				return nbtbase;
			} catch (IOException ioexception) {
                /* 
				CrashReport crashreport = CrashReport.makeCrashReport(ioexception, "Loading NBT data");
				CrashReportCategory crashreportcategory = crashreport.makeCategory("NBT Tag");
				crashreportcategory.addCrashSection("Tag name", "[UNNAMED TAG]");
				crashreportcategory.addCrashSection("Tag type", Byte.valueOf(b0));
				throw new ReportedException(crashreport);
                */
                return nbtbase;
            }
		}
	}
}
