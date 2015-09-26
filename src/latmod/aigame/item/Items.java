package latmod.aigame.item;

import latmod.aigame.*;
import latmod.aigame.tile.Tile;
import latmod.core.util.LMUtils;

public class Items
{
	public static final Registry<IItem> registry = new Registry<IItem>();
	
	public static void add(String s, IItem i)
	{
		if(s == null || s.isEmpty() || i == null) return;
		registry.register(s, i);
		Main.logger.info(((i instanceof Tile) ? "Tile" : "Item") + " '" + s + "' " + LMUtils.classpath(i.getClass()) + " added");
	}
}