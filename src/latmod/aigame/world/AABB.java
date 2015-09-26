package latmod.aigame.world;

public class AABB
{
	public double minX;
	public double minY;
	public double maxX;
	public double maxY;
	
	public AABB()
	{ this(0D, 0D, 1D, 1D); }
	
	public AABB(double x0, double y0, double x1, double y1)
	{ set(x0, y0, x1, y1); }
	
	public void set(double x0, double y0, double x1, double y1)
	{ minX = x0; minY = y0; maxX = x1; maxY = y1; }
}