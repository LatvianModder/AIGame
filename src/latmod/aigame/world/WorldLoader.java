package latmod.aigame.world;

import latmod.core.nbt.*;
import latmod.core.util.*;

public class WorldLoader
{
	public static void writeToNBT(World w, NBTMap map)
	{
		NBTMap registryMap = new NBTMap();
		RegistryFile.writeToNBT(registryMap);
		map.setMap("Reg", registryMap);
		
		NBTList chunkList = new NBTList();
		
		for(Chunk c : w.chunks.values)
		{
			NBTMap map1 = new NBTMap();
			
			map1.setInt("P", Bits.shortsToInt(c.chunkX, c.chunkY));
			
			IntList tileList = new IntList();
			
			for(TileInstance t : c.tiles.values)
			{
				short pos = Chunk.getBlockIndex(t.posX, t.posY);
				short meta = Bits.bytesToShort(t.metadata, t.damage);
				tileList.add(Bits.shortsToInt(pos, meta));
			}
			
			map1.setIntArray("T", tileList.toArray());
			
			NBTMap entityMap = new NBTMap();
			
			for(TileInstance t : c.tiles.values)
			{
				if(t.tile != null && t.tileEntity != null && t.tile.hasTileEntity(t.metadata))
				{
					NBTMap map2 = new NBTMap();
					t.tileEntity.writeToNBT(map2);
					entityMap.setMap("" + t.chunk.chunkIndex, map2);
				}
			}
			
			map1.setMap("E", entityMap);
			
			chunkList.add(map1);
		}
		
		map.setList("Chunks", chunkList);
	}
	
	public static void readFromNBT(World w, NBTMap map)
	{
	}
}