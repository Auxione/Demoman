package Curio.ItemSystem;

import Curio.Console;
import Curio.TileMap;
import Curio.BombManager.BombManager;
import Curio.GameObject.GameObjectManager;
import Curio.PlantSystem.PlantMap;
import Default.Player;

public class Inventory {
	private int[][] inventoryMap;
	private Player player;
	private int playerCellX, playerCellY;
	public int itemIndex = 0;

	public final int inventoryCellSize;
	public int inventoryCellCount;
	private ItemMap itemMap;
	private Console console = null;

	public Inventory(ItemMap itemMap, Player player, int inventoryCellCount, int inventoryCellSize, Console console) {
		this.itemMap = itemMap;
		this.player = player;
		this.inventoryCellCount = inventoryCellCount;
		this.console = console;
		this.inventoryCellSize = inventoryCellSize;
		this.inventoryMap = new int[inventoryCellCount][2];

		for (int x = 0; x < getMap().length; x++) {
			inventoryMap[x][0] = 0;
			inventoryMap[x][1] = 0;
		}

		String cmd = "Inventory: Initialized and assigned.";
		console.Add(0, cmd);
	}

	public Inventory(ItemMap itemMap, Player player, int inventoryCellCount, int inventoryCellSize) {
		this.itemMap = itemMap;
		this.player = player;
		this.inventoryCellCount = inventoryCellCount;
		this.inventoryCellSize = inventoryCellSize;
		inventoryMap = new int[inventoryCellCount][2];
		// Position = new Vector(0, 0);
		// init inv
		for (int x = 0; x < getMap().length; x++) {
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

	public void useSelf(BombManager bm,GameObjectManager GOManager, TileMap level, PlantMap plantMap) {
		applyItem(bm, GOManager, level, plantMap, player, itemIndex);

	}

	public void useOther(BombManager bm,GameObjectManager GOManager, TileMap level, PlantMap plantMap, Player dp) {
		applyItem(bm, GOManager, level, plantMap, dp, itemIndex);
	}

	public void drop() {
		playerCellX = player.cellCoordinate.getCellX();
		playerCellY = player.cellCoordinate.getCellY();

		if (itemMap.put(playerCellX, playerCellY, inventoryMap[itemIndex][0]) == true) {
			remove(inventoryMap[itemIndex][0]);
			if (console != null) {
				String cmd = "Inventory: Item dropped to: " + playerCellX + "-" + playerCellY + ".";
				console.Add(0, cmd);
			}
		}
	}

	public void take() {
		playerCellX = player.cellCoordinate.getCellX();
		playerCellY = player.cellCoordinate.getCellY();

		if (itemMap.getItemID(playerCellX, playerCellY) != 0) {
			for (int x = 0; x < inventoryMap.length; x++) {
				// check if the id exists in inv
				if (inventoryMap[x][0] == itemMap.getItemID(playerCellX, playerCellY)
						&& inventoryMap[x][1] < inventoryCellSize) {
					inventoryMap[x][1] += 1;
					itemMap.remove(playerCellX, playerCellY);

					if (console != null) {
						String cmd = "Inventory: Item taken from: " + playerCellX + "-" + playerCellY + ".";
						console.Add(0, cmd);
					}
					break;
				} else if (inventoryMap[x][0] == 0 && inventoryMap[x][1] < inventoryCellSize) {
					// put it into inv
					inventoryMap[x][0] = itemMap.getItemID(playerCellX, playerCellY);
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

	private void applyItem(BombManager bm,GameObjectManager GOManager, TileMap level, PlantMap plantMap, Player dp, int index) {
		if (inventoryMap[index][0] > 0 && inventoryMap[index][1] > 0) {
			if (ItemList.list.get(inventoryMap[index][0]).condition(dp, level, bm, plantMap,GOManager) == true) {
				ItemList.list.get(inventoryMap[index][0]).apply(dp, level, bm, plantMap,GOManager);
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

	public int[][] getMap() {
		return inventoryMap;
	}

	public void setMap(int[][] map) {
		inventoryMap = map;
	}
}
