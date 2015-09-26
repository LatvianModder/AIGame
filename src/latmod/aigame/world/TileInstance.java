package latmod.aigame.world;

import latmod.aigame.tile.Tile;
import latmod.aigame.tileentity.TileEntity;

public class TileInstance
{
	public final Chunk chunk;
	public final int posX, posY;
	public final Tile tile;
	public byte metadata, damage;
	public TileEntity tileEntity;
	
	public TileInstance(Chunk c, int x, int y, Tile t)
	{
		chunk = c;
		posX = x;
		posY = y;
		tile = t;
	}
}