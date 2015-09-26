package latmod.aigame.client.render.tile;

import org.lwjgl.opengl.GL11;

import latmod.aigame.tile.Tiles;
import latmod.aigame.world.TileInstance;
import latmod.core.util.FastMap;

public abstract class RenderTile
{
	private static int nextRenderID = 0;
	private static final FastMap<Integer, RenderTile> renderers = new FastMap<Integer, RenderTile>();
	
	public static void register(RenderTile r)
	{ if(r != null) renderers.put(r.renderID, r); }
	
	public static RenderTile get(int i)
	{
		if(i < 0) return null;
		else if(i == 0) return RenderTileDefault.instance;
		else return renderers.get(i);
	}
	
	public static void renderTile(double x, double y, TileInstance ti)
	{
		if(ti == null || ti.tile == null || ti.tile == Tiles.air) return;
		RenderTile rt = get(ti.tile.getRenderID());
		
		if(rt != null)
		{
			GL11.glPushMatrix();
			GL11.glTranslated(x, 0, y);
			rt.renderTile(ti);
			GL11.glPopMatrix();
		}
	}
	
	// End of static //
	
	public final int renderID;
	
	public RenderTile()
	{ renderID = ++nextRenderID; }
	
	public int hashCode()
	{ return renderID; }
	
	public boolean equals(Object o)
	{ return o != null && (o == this || o.hashCode() == hashCode()); }
	
	public abstract void renderTile(TileInstance t);
}