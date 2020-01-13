package Curio.PlantMap;

import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Curio.Functions;
import Curio.Tileset;
import Curio.ItemMap.ItemMap;
import Curio.PlantMap.Plants.BerryBush;
import Curio.PlantMap.Plants.SausagePlant;
import Curio.Tilemap.TileMap;
import Default.Constants;
import Default.Player;

public class PlantMap {
	private HashMap<Integer, Plant> plantList;

	private int[][][] plantMap;
	private TileMap tilemap;
	private ItemMap itemMap;

	int millis_goal = 0;
	int rate = 20;

	public PlantMap(TileMap tilemap, ItemMap itemMap) {
		this.plantList = new HashMap<Integer, Plant>();
		// coordinates x,y
		// int[][][0] = plant id
		// int[][][1] = plant health
		// int[][][2] = currentplantgrowth
		// int[][][3] = plantmaxgrowth
		// int[][][4] = currentStates
		// int[][][5] = maxStates
		this.plantMap = new int[tilemap.get_MaxCellX()][tilemap.get_MaxCellY()][6];
		this.tilemap = tilemap;
		this.itemMap = itemMap;

		// set value of the all channels to 0
		for (int x = 0; x < plantMap.length; x++) {
			for (int y = 0; y < plantMap[0].length; y++) {
				clearCell(x, y);
			}
		}
		initPlants();
	}

	private void initPlants() {
		this.plantList.put(1, new BerryBush());
		this.plantList.put(2, new SausagePlant());
	}
	
	public void updateMap(int[][][] map) {
		plantMap = map;
	}
	
	public void put(int x, int y, int id) {
		if (plantMap[x][y][0] == 0 && Tileset.canPlant(tilemap.get_Tile(x, y))) {
			plantMap[x][y][0] = id;
			plantMap[x][y][1] = plantList.get(id).getMaxHealth();
			plantMap[x][y][2] = 0;
			plantMap[x][y][3] = plantList.get(id).getMaxGrowth();
			plantMap[x][y][4] = 0;
			plantMap[x][y][5] = plantList.get(id).getMaxStates();
		}
	}

	public void harvest(Player player) {
		int x = player.CellPosition.getCellX();
		int y = player.CellPosition.getCellY();
		int plantID = plantMap[x][y][0];

		boolean readyToHarvest = (plantMap[x][y][4] == plantMap[x][y][5]);
		if (plantID != 0 && readyToHarvest) {
			if (plantList.get(plantID).harvestCondition(player, tilemap, x, y)) {
				itemMap.put(x, y, plantList.get(plantID).getHarvestItem());
				clearCell(x, y);
			}
		}
	}

	public boolean canPlant(Player player, int plantID) {
		int x = player.CellPosition.getCellX();
		int y = player.CellPosition.getCellY();
		if (plantList.get(plantID).plantCondition(player, tilemap, x, y) && plantMap[x][y][0] == 0) {
			return true;
		} else {
			return false;
		}
	}

	private void clearCell(int x, int y) {
		for (int i = 0; i < plantMap[0][0].length; i++) {
			plantMap[x][y][i] = 0;
		}
	}

	public void update() {
		updateGrowth();
		checkHealth();
	}

	public void render(Graphics g) {
		for (int x = 0; x < plantMap.length; x++) {
			for (int y = 0; y < plantMap[0].length; y++) {
				if (plantMap[x][y][0] > 0 && plantMap[x][y][1] > 0) {
					// get all images
					Image[] i = plantList.get(plantMap[x][y][0]).getImageArray();

					g.drawImage(i[plantMap[x][y][4]], x * Constants.CellSize, y * Constants.CellSize);
				}
			}
		}
	}

	private void updateGrowth() {
		if (Functions.millis() > millis_goal) {
			for (int x = 0; x < plantMap.length; x++) {
				for (int y = 0; y < plantMap[0].length; y++) {
					if (plantMap[x][y][0] != 0 && plantMap[x][y][1] > 0) {
						if (plantMap[x][y][2] < plantMap[x][y][3]) {
							plantMap[x][y][2] += 1;
							plantMap[x][y][4] = calculateState(x, y);
						} else if (plantMap[x][y][2] >= plantMap[x][y][3]) {
							plantMap[x][y][2] = plantMap[x][y][3];
							plantMap[x][y][4] = plantMap[x][y][5];
						}
					}
					// if health < 0 clear the cell
					else if (plantMap[x][y][1] <= 0) {
						clearCell(x, y);
					}
				}
			}
			millis_goal = Functions.millis() + rate;
		}
	}

	public int get_Cell(int x, int y) {
		// if any request from this function exceeds tilemap borders
		// return only the border value

		if (x >= 0 && x < plantMap.length && y >= 0 && y < plantMap[0].length) {
			// if its inside the tilemap array
			return plantMap[x][y][0];
		} else
			return 0;
	}

	// int[][][0] = plant id
	// int[][][1] = plant health
	// int[][][2] = currentplantgrowth
	// int[][][3] = plantmaxgrowth
	// int[][][4] = currentStates
	// int[][][5] = maxStates
	public String getName(int id) {
		return plantList.get(id).getName();
	}

	public String getDescription(int id) {
		return plantList.get(id).getDesc();
	}

	public int getHealth(int x, int y) {
		return plantMap[x][y][1];
	}

	private int calculateState(int x, int y) {
		int out = (int) Functions.map(plantMap[x][y][2], 0, plantMap[x][y][3], 0, plantMap[x][y][5]);
		return out;
	}

	public void applyDamage(int x, int y, int damage) {
		plantMap[x][y][1] -= damage;
	}

	public void checkHealth() {
		for (int x = 0; x < plantMap.length; x++) {
			for (int y = 0; y < plantMap[0].length; y++) {
				if (plantMap[x][y][1] < 0) {
					clearCell(x, y);
				} else if (plantMap[x][y][1] > getHealth(x, y)) {
					plantMap[x][y][1] = getHealth(x, y);
				}
			}
		}
	}

	public int[][][] getMap() {
		return plantMap;
	}
}
