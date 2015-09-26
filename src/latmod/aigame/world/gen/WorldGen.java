package latmod.aigame.world.gen;

import latmod.aigame.world.World;
import latmod.core.util.FastList;

public abstract class WorldGen
{
	private static final FastList<WorldGen> generators = new FastList<WorldGen>();
	
	public static void init()
	{
		generators.clear();
		generators.add(new WorldGenTrees());
		generators.add(new WorldGenBushes());
		generators.add(new WorldGenLakes());
		generators.add(new WorldGenRocks());
		generators.add(new WorldGenOres());
	}
	
	public static void generateWorld(World w)
	{
		for(WorldGen wg : generators)
			wg.generate(w);
	}
	
	public abstract void generate(World w);
}