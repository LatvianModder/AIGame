package latmod.aigame;

import latmod.core.nbt.*;
import latmod.core.util.FastMap;

public class Registry<E>
{
	private final FastMap<String, E> map;
	private final FastMap<Integer, String> mapI;
	private final FastMap<Integer, E> mapE;
	
	public Registry()
	{
		map = new FastMap<String, E>();
		mapI = new FastMap<Integer, String>();
		mapE = new FastMap<Integer, E>();
	}
	
	public void register(String s, E e)
	{ map.put(s, e); }
	
	public E get(String s)
	{ return map.get(s); }
	
	public E get(int i)
	{ return mapE.get(Integer.valueOf(i)); }
	
	public String getKey(E e)
	{ return map.getKey(e); }
	
	public String getKey(int i)
	{ return mapI.get(Integer.valueOf(i)); }
	
	public int getID(E e)
	{
		Integer i = mapE.getKey(e);
		return (i == null) ? 0 : i.intValue();
	}
	
	public int getID(String s)
	{
		Integer i = mapI.getKey(s);
		return (i == null) ? 0 : i.intValue();
	}
	
	public int generateIDs()
	{
		mapI.clear();
		mapE.clear();
		
		int id = 0;
		
		for(int i = 0; i < map.size(); i++)
		{
			int id1 = ++id;
			mapI.put(id1, map.keys.get(i));
			mapE.put(id1, map.values.get(i));
		}
		
		return id;
	}
	
	public int readFromNBT(NBTMap m)
	{
		mapI.clear();
		mapE.clear();
		
		if(m != null && !m.isEmpty())
		for(int i = 0; i < m.size(); i++)
		{
			String key = m.map.keys.get(i);
			NBTBase val = m.map.values.get(i);
			
			if(key != null && val instanceof NBTNumber)
			{
				E e = get(key);
				
				if(e != null)
				{
					int id = ((NBTNumber)val).getNumber().shortValue();
					mapI.put(id, key);
					mapE.put(id, e);
				}
			}
		}
		
		return mapI.size();
	}
	
	public int writeToNBT(NBTMap m)
	{
		for(int i = 0; i < mapI.size(); i++)
			m.setShort(mapI.values.get(i), mapI.keys.get(i).intValue());
		return mapI.size();
	}
}