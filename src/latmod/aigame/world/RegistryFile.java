package latmod.aigame.world;

import latmod.aigame.Registry;
import latmod.aigame.item.Items;
import latmod.core.nbt.NBTMap;

public class RegistryFile
{
	public static int writeToNBT(NBTMap map)
	{
		int i = 0;
		i += writeToNBT(Items.registry, map, "Items");
		return i;
	}
	
	private static int writeToNBT(Registry<?> r, NBTMap map, String s)
	{ NBTMap m = new NBTMap(); int i = r.writeToNBT(m); map.setMap(s, m); return i; }
	
	public static int readFromNBT(NBTMap map)
	{
		int i = 0;
		i += readFromNBT(Items.registry, map, "Item");
		return i;
	}
	
	private static int readFromNBT(Registry<?> r, NBTMap map, String s)
	{ return r.readFromNBT(map.getMap(s)); }
}