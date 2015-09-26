package latmod.aigame.client.render;

import latmod.aigame.client.render.tile.RenderTile;
import latmod.aigame.world.*;
import latmod.core.rendering.Renderer;
import latmod.core.util.IntMap;

public class WorldRenderer
{
	public final World worldObj;
	public final IntMap chunkListIDs;
	
	public WorldRenderer(World w)
	{
		worldObj = w;
		chunkListIDs = new IntMap();
		chunkListIDs.setDefVal(-1);
	}
	
	public void render()
	{
		for(int i = 0; i < worldObj.chunks.size(); i++)
		{
			Chunk c = worldObj.chunks.values.get(i);
			
			if(canRender(c))
			{
				int listID = chunkListIDs.get(c.chunkIndex);
				
				if(listID == -1 || c.isDirty)
				{
					if(listID == -1) listID = Renderer.createListID();
					Renderer.updateList(listID);
					
					for(int j = 0; j < c.tiles.size(); j++)
					{
						TileInstance ti = c.tiles.values.get(j);
						RenderTile.renderTile(ti.posX, ti.posY, ti);
					}
					
					Renderer.endList();
					
					c.isDirty = false;
				}
				
				Renderer.renderList(listID);
				
				/*
				for(int j = 0; j < c.tiles.size(); j++)
				{
					TileInstance ti = c.tiles.values.get(j);
					if(ti.tileEntity != null) TileEnityRenderer.renderTileEntity(ti);
				}*/
			}
		}
	}
	
	public void destroy()
	{
		for(int i : chunkListIDs.values)
			Renderer.deleteList(i);
		chunkListIDs.clear();
	}
	
	public boolean canRender(Chunk c)
	{
		return true;
	}
}