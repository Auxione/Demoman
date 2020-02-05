package Curio.SessionManagers.WorldManager;

import Curio.SessionManagers.ItemManager.ItemMap;
import Curio.SessionManagers.PlantManager.PlantMap;

public class WorldManager {
	public TileMap tileMap;
	public WorldTime worldTime;
	private PlantMap plantMap;
	private ItemMap itemMap;

	public WorldManager(PlantMap plantMap, TileMap tilemap, ItemMap itemMap, WorldTime worldTime) {

		this.plantMap = plantMap;
		this.tileMap = tilemap;
		this.itemMap = itemMap;
		this.worldTime = worldTime;
	}
}
