package Curio.Network;

import java.io.Serializable;

import Curio.ItemMap.ItemMap;
import Curio.LogicMap.LogicMap;
import Curio.PlantMap.PlantMap;
import Curio.Tilemap.TileMap;

public class MapPackage implements Serializable {
	public int[][][] tilemap;
	public int[][][] tileArray;
	public int[][][] itemArray;
	public int[][][] logicArray;
	public int[][][] plantArray;

	public MapPackage(PlantMap plantMap, ItemMap itemMap, TileMap tileMap, LogicMap logicMap) {
		this.tileArray = tileMap.getMap();
		this.itemArray = itemMap.getMap();
		this.logicArray = logicMap.getMap();
		this.plantArray = plantMap.getMap();
	}
}
