package Curio.SessionManagers.WorldManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Functions;
import Curio.Viewport;
import Curio.Physics.WorldTime;
import Curio.Renderer.Interface.Renderer;
import Curio.SessionManagers.ItemManager.ItemMap;
import Curio.SessionManagers.PlantManager.PlantMap;

public class WorldManager implements Renderer {
	public TileMap tileMap;
	public WorldTime worldTime;
	private Color nightColor = new Color(0.0f, 0.0f, 0.0f, 0.0f);
	private PlantMap plantMap;
	private ItemMap itemMap;
	private int screenSizeX = 0, screenSizeY = 0;

	public WorldManager(Viewport viewPort, PlantMap plantMap, TileMap tilemap, ItemMap itemMap, WorldTime worldTime) {
		this.screenSizeX = viewPort.screenSizeX;
		this.screenSizeY = viewPort.screenSizeY;

		this.plantMap = plantMap;
		this.tileMap = tilemap;
		this.itemMap = itemMap;
		this.worldTime = worldTime;
	}

	public void updateCycle() {
		if (worldTime.isSunset() == true) {
			nightColor.a = Functions.map(worldTime.getTime().minute, 0, 59, 0.0f, 0.9f);
		}

		else if (worldTime.isSunrise() == true) {
			nightColor.a = Functions.map(worldTime.getTime().minute, 0, 59, 0.9f, 0.0f);
		}
	}

	@Override
	public void render(Graphics g) {
		g.pushTransform();
		g.translate(0, 0);

		if (worldTime.isNight() == true) {
			g.setColor(nightColor);
			g.fillRect(0, 0, screenSizeX, screenSizeY);
		}
		g.popTransform();
	}
}
