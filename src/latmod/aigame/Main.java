package latmod.aigame;

import java.util.logging.Logger;

import org.lwjgl.opengl.GL11;

import latmod.aigame.client.render.WorldRenderer;
import latmod.aigame.tile.Tiles;
import latmod.aigame.world.World;
import latmod.aigame.world.gen.WorldGen;
import latmod.core.*;
import latmod.core.rendering.Renderer;

public class Main extends LMFrame
{
	public static Main inst;
	public static final Logger logger = Logger.getLogger("Game");
	
	public World worldObj = null;
	public WorldRenderer worldRenderer = null;
	
	public Main(String[] s) throws Exception
	{ super(s, 800, 600); }
	
	public void onLoaded() throws Exception
	{
		super.onLoaded();
		setTitle("AI Survival Game");
		logger.setParent(LatCoreGL.logger);
		
		Tiles.init();
		WorldGen.init();
		
		setWorld(new World(10030L, 64));
	}
	
	public void onRender() throws Exception
	{
		Renderer.background(0xFF333333);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Renderer.enableTexture();
		
		if(worldObj != null)
			worldRenderer.render();
	}
	
	public void setWorld(World w)
	{
		worldObj = w;
		worldRenderer = new WorldRenderer(worldObj);
		WorldGen.generateWorld(worldObj);
	}
}