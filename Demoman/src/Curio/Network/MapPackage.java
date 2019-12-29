package Curio.Network;

import java.io.Serializable;

import Curio.ItemMap.ItemMap;
import Curio.LogicMap.LogicMap;
import Curio.PlantMap.PlantMap;
import Curio.Tilemap.TileMap;
import org.json.simple.*;

public class MapPackage implements Serializable {
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

	void toJSON() {
		JSONObject json = new JSONObject();
		// tile array
		for (int x = 0; x < tileArray.length; x++) {
			for (int y = 0; y < tileArray[x].length; y++) {
				for (int i = 0; i < tileArray[x][y].length; i++) {
					if (i == 0) {
						json.put("id", tileArray[x][y][i]);
					} else if (i == 1) {
						json.put("hp", tileArray[x][y][i]);
					}
				}
			}
		}
		
		// item array
		// itemMap[][][0] = item id;
		// itemMap[][][1] = item count;
		for (int x = 0; x < itemArray.length; x++) {
			for (int y = 0; y < itemArray[x].length; y++) {
				for (int i = 0; i < itemArray[x][y].length; i++) {
					if (i == 0) {
						json.put("id", itemArray[x][y][i]);
					} else if (i == 1) {
						json.put("count", itemArray[x][y][i]);
					}
				}
			}
		}
	}
}
