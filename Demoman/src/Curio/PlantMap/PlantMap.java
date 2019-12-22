package Curio.PlantMap;

import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.ItemMap.Item;
import Curio.ItemMap.ItemMap;
import Curio.ItemMap.Items.Medpack;
import Curio.LogicMap.Controller.DynamicWall;
import Curio.Tilemap.TileMap;
import Default.Constants;

public class PlantMap {
	public HashMap<Integer, Plant> plantList;

	private int[][][] plantMap;
	private TileMap tilemap;
	private ItemMap itemMap;

	int millis_goal = 0;
	int rate = 200;

	public PlantMap(TileMap tilemap, ItemMap itemMap) {
		// coordinates x,y
		// int[][][0] = plant id
		// int[][][1] = plant health
		// int[][][2] = plantgrowth
		plantMap = new int[tilemap.get_MaxCellX()][tilemap.get_MaxCellY()][3];
		this.tilemap = tilemap;
		this.itemMap = itemMap;

		// set value of the all channels to 0
		for (int x = 0; x < plantMap.length; x++) {
			for (int y = 0; y < plantMap[0].length; y++) {
				plantMap[x][y][0] = 0;
				plantMap[x][y][1] = 0;
				plantMap[x][y][2] = 0;
			}
		}

		plantList.put(1, new BerryBush());
	}

	public void put(int x, int y, int id) {
		if (plantMap[x][y][0] == 0) {
			plantMap[x][y][0] = id;
			plantMap[x][y][1] = plantList.get(id).getMaxHealth();
		}
	}

	public void update(int millis) {
		updateGrowth(millis);

	}

	public void render(Graphics g) {
		for (int x = 0; x < plantMap.length; x++) {
			for (int y = 0; y < plantMap[0].length; y++) {
				if (plantMap[x][y][0] > 0 && plantMap[x][y][1] > 0) {
					g.drawImage(plantList.get(plantMap[x][y][0]).getImage(), x * Constants.CellSize,
							y * Constants.CellSize);
				}
			}
		}
	}

	private void updateGrowth(int millis) {
		if (millis > millis_goal) {
			for (int x = 0; x < plantMap.length; x++) {
				for (int y = 0; y < plantMap[0].length; y++) {
					if (plantMap[x][y][2] < plantList.get(plantMap[x][y][0]).getMaxGrowth()) {
						plantMap[x][y][2] += 1;
					}
					plantList.get(plantMap[x][y][0]).growthRate(getPercentage(x, y));
				}
			}
			millis_goal = millis + rate;
		}
	}

	private float getPercentage(int x, int y) {
		float out = plantMap[x][y][1] / plantMap[x][y][2];
		return out;
	}
}
