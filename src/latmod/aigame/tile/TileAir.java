package latmod.aigame.tile;

import latmod.aigame.world.*;

public class TileAir extends Tile
{
	public TileAir(String s)
	{
		super(s);
	}
	
	public AABB getAABB(TileInstance t)
	{ return null; }
	
	public int getRenderID()
	{ return -1; }
}