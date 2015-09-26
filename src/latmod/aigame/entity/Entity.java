package latmod.aigame.entity;

import latmod.aigame.world.World;
import latmod.core.nbt.NBTMap;

public class Entity
{
	public World world;
	public double posX, posY;
	public float rotation;
	
	public void init()
	{
	}
	
	public void readFromNBT(NBTMap map)
	{
	}
	
	public void writeToNBT(NBTMap map)
	{
	}
}