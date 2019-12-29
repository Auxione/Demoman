package Curio.Network;

import java.io.Serializable;
import java.util.Arrays;

import Curio.ItemMap.ItemMap;
import Curio.LogicMap.LogicMap;
import Curio.PlantMap.PlantMap;
import Curio.Tilemap.TileMap;
import org.json.simple.*;

public class MapPackage implements Serializable {
	public int[][][] tileArray = null;
	public int[][][] itemArray = null;
	public int[][][] logicArray = null;
	public int[][][] plantArray = null;

	public MapPackage(TileMap tileMap, ItemMap itemMap) {
		tileArray = new int[tileMap.get_MaxCellX()][tileMap.get_MaxCellY()][2];
		itemArray = new int[tileMap.get_MaxCellX()][tileMap.get_MaxCellY()][2];

		for (int x = 0; x < tileMap.getMap().length; x++) {
			for (int y = 0; y < tileMap.getMap()[0].length; y++) {
				for (int i = 0; i < tileMap.getMap()[0][0].length; i++) {
					tileArray[x][y][i] = tileMap.getMap()[x][y][i];
					itemArray[x][y][i] = itemMap.getMap()[x][y][i];
				}
			}
		}
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
