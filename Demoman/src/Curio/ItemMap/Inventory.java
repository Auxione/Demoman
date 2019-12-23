package Curio.ItemMap;

import Curio.HUD.ConsoleDisplay;
import Curio.PlantMap.PlantMap;
import Curio.Tilemap.TileMap;
import Curio.Tilemap.Bomb.BombManager;
import Curio.Utilities.Math.Vector;
import Default.Player;

public class Inventory {
	private int[][] inventoryMap;
	public Vector Position;

	private Player player;

	private int playerCellX, playerCellY;

	public int itemIndex = 0;

	public final int tileMaxItem;
	public int inventorySize;
	private ItemMap itemMap;
	private ConsoleDisplay console;

	public Inventory(ItemMap itemMap, Player player, int inventorySize, int tileMaxItem, ConsoleDisplay console) {
		this.itemMap = itemMap;
		this.player = player;
		this.inventorySize = inventorySize;
		this.console = console;
		this.tileMaxItem = tileMaxItem;

		inventoryMap = new int[inventorySize][2];
		// Position = new Vector(0, 0);
		// init inv
		for (int x = 0; x < getInventoryMap().length; x++) {
			inventoryMap[x][0] = 0;
			inventoryMap[x][1] = 0;
		}

		String cmd = "Inventory: Initialized and assigned.";
		console.Add(0, cmd);
	}

	public Inventory(ItemMap itemMap, Player player, int inventorySize, int tileMaxItem) {
		this.itemMap = itemMap;
		this.player = player;
		this.inventorySize = inventorySize;
		this.console = null;
		this.tileMaxItem = tileMaxItem;

		inventoryMap = new int[inventorySize][2];
		// Position = new Vector(0, 0);
		// init inv
		for (int x = 0; x < getInventoryMap().length; x++) {
			inventoryMap[x][0] = 0;
			inventoryMap[x][1] = 0;
		}
	}

	public void switchCurrentItem() {
		if (itemIndex >= inventoryMap.length - 1) {
			itemIndex = 0;
		} else if (itemIndex < inventoryMap.length) {
			itemIndex = itemIndex + 1;
		}
	}

	public void useSelf(BombManager bm, TileMap level,PlantMap plantMap) {
		applyItem(bm, level, plantMap,player, itemIndex);

	}

	public void useOther(BombManager bm, TileMap level,PlantMap plantMap, Player dp) {
		applyItem(bm, level, plantMap,dp, itemIndex);
	}

	public void drop() {
		playerCellX = player.CellPosition.get_x();
		playerCellY = player.CellPosition.get_y();

		if (itemMap.put(playerCellX, playerCellY, inventoryMap[itemIndex][0]) == true) {
			remove(inventoryMap[itemIndex][0]);
			if (console != null) {
				String cmd = "Inventory: Item dropped to: " + playerCellX + "-" + playerCellY + ".";
				console.Add(0, cmd);
			}
		}
	}

	public void take() {
		playerCellX = player.CellPosition.get_x();
		playerCellY = player.CellPosition.get_y();

		if (itemMap.get(playerCellX, playerCellY) != 0) {
			for (int x = 0; x < inventoryMap.length; x++) {
				// check if the id exists in inv
				if (inventoryMap[x][0] == itemMap.get(playerCellX, playerCellY) && inventoryMap[x][1] < tileMaxItem) {
					inventoryMap[x][1] += 1;
					itemMap.remove(playerCellX, playerCellY);

					if (console != null) {
						String cmd = "Inventory: Item taken from: " + playerCellX + "-" + playerCellY + ".";
						console.Add(0, cmd);
					}
					break;
				} else if (inventoryMap[x][0] == 0 && inventoryMap[x][1] < tileMaxItem) {
					// put it into inv
					inventoryMap[x][0] = itemMap.get(playerCellX, playerCellY);
					inventoryMap[x][1] += 1;
					itemMap.remove(playerCellX, playerCellY);

					if (console != null) {
						String cmd = "Inventory: Item taken from: " + playerCellX + "-" + playerCellY + ".";
						console.Add(0, cmd);
					}
					break;
				}
			}
		}
	}

	public void remove(int id) {
		for (int x = 0; x < inventoryMap.length; x++) {
			if (inventoryMap[x][0] == id) {
				inventoryMap[itemIndex][1] -= 1;
				if (inventoryMap[itemIndex][1] <= 0) {
					inventoryMap[itemIndex][0] = 0;
					inventoryMap[itemIndex][1] = 0;
				}
				break;
			}
		}
	}

	private void applyItem(BombManager bm, TileMap level,PlantMap plantMap, Player dp, int index) {
		if (inventoryMap[index][0] > 0 && inventoryMap[index][1] > 0) {
			if (itemMap.itemList.get(inventoryMap[index][0]).condition(dp, level, bm,plantMap) == true) {
				itemMap.itemList.get(inventoryMap[index][0]).apply(dp, level, bm,plantMap);
				inventoryMap[index][1] = inventoryMap[index][1] - 1;
			}
		}
		if (inventoryMap[index][1] <= 0) {
			inventoryMap[index][0] = 0;
			inventoryMap[index][1] = 0;
		}
		if (console != null) {
			String cmd = "Inventory: Item activated.";
			console.Add(0, cmd);
		}
	}

	public int[][] getInventoryMap() {
		return inventoryMap;
	}

}
