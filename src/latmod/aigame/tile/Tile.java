package latmod.aigame.tile;

import latmod.aigame.item.*;
import latmod.aigame.tileentity.TileEntity;
import latmod.aigame.world.*;

public class Tile implements IItem
{
	private final String tileID;
	public short tileIntID;
	
	public int maxDamage;
	public int lightValue;
	public AABB aabb;
	public int tileColor;
	
	public Tile(String s)
	{
		tileID = s;
		maxDamage = 5;
		lightValue = 0;
		aabb = new AABB(0D, 0D, 1D, 1D);
		tileColor = 0xFF808080;
	}
	
	public final String getID()
	{ return tileID; }
	
	public final Tile register()
	{ Items.add(tileID, this); return this; }
	
	public int getMaxDamage(TileInstance t)
	{ return maxDamage; }
	
	public float getLightValue(TileInstance t)
	{ return lightValue; }
	
	public AABB getAABB(TileInstance t)
	{ return aabb; }
	
	public boolean hasTileEntity(byte m)
	{ return false; }
	
	public TileEntity createTileEntity(TileInstance t)
	{ return null; }
	
	public int getColor(TileInstance t)
	{ return tileColor; }
	
	public int getRenderID()
	{ return 0; }
}