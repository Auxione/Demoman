package Curio.Editor;

import Curio.SessionManagers.ItemManager.ItemMap;
import Curio.SessionManagers.LogicManager.LogicMap;
import Curio.SessionManagers.PlantManager.PlantMap;
import Curio.SessionManagers.PressureManager.PressureMap;
import Curio.SessionManagers.WorldManager.TileMap;

public class ShipObject {
	private TileMap tileMap;
	private ItemMap itemMap;
	private PlantMap plantMap;
	private LogicMap logicMap;
	public ShipObject(int maxXCell,int maxYCell) {
		tileMap = new TileMap(maxXCell,maxYCell);
		itemMap = new ItemMap(tileMap);
		plantMap = new PlantMap(tileMap);
		logicMap = new LogicMap(tileMap);
	}

}
