package latmod.aigame.tile;

public class Tiles
{
	public static void init()
	{
		//TODO: Send register tiles event
	}
	
	public static final Tile air = new TileAir("air");
	public static final Tile stone = new TileStone("stone").register();
	public static final Tile bush = new TileBush("bush").register();
	public static final Tile tree = new TileTree("tree").register();
	public static final Tile water = new TileWater("water").register();
	public static final Tile ore = new TileOre("ore").register();
}