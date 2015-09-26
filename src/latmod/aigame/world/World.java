package latmod.aigame.world;

import java.util.Random;

import latmod.aigame.tile.*;
import latmod.aigame.tileentity.TileEntity;
import latmod.core.util.*;

public class World
{
	public final long seed;
	public final Random random;
	public final FastMap<Integer, Chunk> chunks;
	public final int sizeChunks;
	public final int sizeTiles;
	
	public World(long s, int ch)
	{
		seed = s;
		random = new Random(s);
		chunks = new FastMap<Integer, Chunk>();
		sizeChunks = ch;
		sizeTiles = sizeChunks * 16;
	}
	
	public boolean chunkExists(int cx, int cy)
	{ return cx >= 0 && cy >= 0 && cx < sizeChunks && cy < sizeChunks && chunks.keys.contains(Chunk.getChunkIndex(cx, cy)); }
	
	public Chunk getChunk(int cx, int cy)
	{ return chunks.get(Chunk.getChunkIndex(cx, cy)); }
	
	public Chunk getChunkI(int x, int y)
	{ return getChunk(MathHelperLM.chunk(x), MathHelperLM.chunk(y)); }
	
	public Chunk loadChunk(int cx, int cy)
	{
		Chunk c = getChunk(cx, cy);
		if(c == null) { c = new Chunk(this, cx, cy); chunks.put(c.chunkIndex, c); }
		return c;
	}
	
	public Chunk loadChunkI(int x, int y)
	{ return loadChunk(MathHelperLM.chunk(x), MathHelperLM.chunk(y)); }
	
	public void setTile(int x, int y, Tile t, int m)
	{
		Chunk chunk = loadChunkI(x, y);
		if(t != Tiles.air)
		{
			TileInstance ti = new TileInstance(chunk, x, y, t);
			ti.metadata = (byte)m;
			if(t.hasTileEntity(ti.metadata))
			{
				ti.tileEntity = t.createTileEntity(ti);
				ti.tileEntity.world = this;
				ti.tileEntity.posX = x;
				ti.tileEntity.posY = y;
				ti.tileEntity.init();
			}
			
			chunk.setTileInstance(x, y, ti);
		}
		else chunk.setTileInstance(x, y, null);
		chunk.markDirty();
	}
	
	public void setMeta(int x, int y, int m)
	{
		Chunk chunk = getChunkI(x, y);
		if(chunk == null) return;
		TileInstance t = chunk.getTileInstance(x, y);
		if(t != null)
		{
			int m0 = t.metadata;
			t.metadata = (byte)m;
			if(m0 != m) chunk.markDirty();
		}
	}
	
	public TileInstance getTileInstance(int x, int y)
	{
		Chunk chunk = getChunkI(x, y);
		return (chunk != null) ? chunk.getTileInstance(x, y) : null;
	}
	
	public Tile getTile(int x, int y)
	{
		TileInstance t = getTileInstance(x, y);
		return (t != null) ? t.tile : Tiles.air;
	}
	
	public int getMeta(int x, int y)
	{
		TileInstance t = getTileInstance(x, y);
		return (t != null) ? t.metadata : 0;
	}
	
	public TileEntity getTileEntity(int x, int y)
	{
		TileInstance t = getTileInstance(x, y);
		return (t != null) ? t.tileEntity : null;
	}
}