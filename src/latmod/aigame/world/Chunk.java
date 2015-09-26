package latmod.aigame.world;

import latmod.core.util.*;

public class Chunk
{
	public final World world;
	public final int chunkX, chunkY, chunkIndex;
	public final FastMap<Short, TileInstance> tiles;
	public boolean isDirty;
	
	public Chunk(World w, int x, int y)
	{
		world = w;
		chunkX = x;
		chunkY = y;
		chunkIndex = getChunkIndex(chunkX, chunkY);
		tiles = new FastMap<Short, TileInstance>();
		markDirty();
	}
	
	public static short getBlockIndex(int x, int y)
	{ return Bits.bytesToShort(MathHelperLM.wrap(x, 16), MathHelperLM.wrap(y, 16)); }
	
	public static int getChunkIndex(int cx, int cy)
	{ return Bits.shortsToInt(cx, cy); }
	
	public TileInstance getTileInstance(int x, int y)
	{ return tiles.get(getBlockIndex(x, y)); }
	
	public void setTileInstance(int x, int y, TileInstance t)
	{
		if(t == null) tiles.remove(getBlockIndex(x, y));
		else tiles.put(getBlockIndex(x, y), t);
	}
	
	public void markDirty()
	{ isDirty = true; }
}