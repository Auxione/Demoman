package Curio.SessionManagers;


import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Functions;
import Curio.TileMap;
import Curio.Viewport;
import Curio.ItemSystem.ItemMap;
import Curio.Physics.WorldTime;
import Curio.PlantSystem.PlantMap;

public class WorldManager {
	public TileMap tileMap;
	public WorldTime worldTime;
	private Color nightColor = new Color(0.0f, 0.0f, 0.0f, 0.0f);
	private PlantMap plantMap;
	private ItemMap itemMap;

	public WorldManager(PlantMap plantMap, TileMap tilemap, ItemMap itemMap, WorldTime worldTime) {
		this.plantMap = plantMap;
		this.tileMap = tilemap;
		this.itemMap = itemMap;
		this.worldTime = worldTime;
	}

	public void updateCycle() {
		if (worldTime.isSunset() == true) {
			nightColor.a = Functions.map(worldTime.getMinutes(), 0, 59, 0.0f, 0.7f);
		}

		else if (worldTime.isSunrise() == true) {
			nightColor.a = Functions.map(worldTime.getMinutes(), 0, 59, 0.7f, 0.0f);
		}
	}

	public void render(Viewport viewPort, Graphics g) {
		g.pushTransform();
		g.translate(0, 0);

		if (worldTime.isNight() == true) {
			g.setColor(nightColor);
			g.fillRect(0, 0, viewPort.screenSizeX, viewPort.screenSizeY);
		}

		g.popTransform();
	}

	public void RenderAlphaMap(Graphics g) {
		g.clearAlphaMap();
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE); // Blends the alpha map.
		
		
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_DST_ALPHA); // Resets the blending factor.
		g.setDrawMode(Graphics.MODE_NORMAL); // Resets the Graphics draw mode.
		g.clearWorldClip();
	}
}
