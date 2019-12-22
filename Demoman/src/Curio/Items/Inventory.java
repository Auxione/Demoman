package Curio.Items;

import Curio.Tilemap.Tilemap;
import Curio.Tilemap.Bomb.BombManager;
import Curio.Utilities.Vector;
import Default.DynamicPlayer;

public class Inventory {
	private int[][] inventoryMap;
	public Vector Position;

	private DynamicPlayer player;
	public int itemIndex = 0;

	public final int maxItem = 3;
	public int inventorySize;

	public Inventory(DynamicPlayer dp, int _sizeX) {
		player = dp;
		inventorySize = _sizeX;
		inventoryMap = new int[inventorySize][2];
		Position = new Vector(0, 0);
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

	public void useSelf(BombManager bm, Tilemap level) {
		applyItem(bm, level, player, itemIndex);
	}

	public void useOther(BombManager bm, Tilemap level, DynamicPlayer dp) {
		applyItem(bm, level, player, itemIndex);
	}

	public void drop() {
		int cpx = player.CellPosition.get_x();
		int cpy = player.CellPosition.get_y();
		if (Item.put(cpx, cpy, inventoryMap[itemIndex][0]) == true) {
			remove(inventoryMap[itemIndex][0]);
		}
	}

	public void take() {
		int cpx = player.CellPosition.get_x();
		int cpy = player.CellPosition.get_y();
		if (Item.get(cpx, cpy) != 0) {
			for (int x = 0; x < inventoryMap.length; x++) {
				// check if the id exists in inv
				if (inventoryMap[x][0] == Item.get(cpx, cpy) && inventoryMap[x][1] < maxItem) {
					inventoryMap[x][1] += 1;
					Item.remove(cpx, cpy);
					break;
				} else if (inventoryMap[x][0] == 0 && inventoryMap[x][1] < maxItem) {
					// put it into inv
					inventoryMap[x][0] = Item.get(cpx, cpy);
					inventoryMap[x][1] += 1;
					Item.remove(cpx, cpy);
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

	private void applyItem(BombManager bm, Tilemap level, DynamicPlayer dp, int index) {
		if (inventoryMap[index][0] > 0 && inventoryMap[index][1] > 0) {
			if (Item.itemList.get(inventoryMap[index][0]).condition(dp, level, bm) == true) {
				Item.itemList.get(inventoryMap[index][0]).apply(dp, level, bm);
				inventoryMap[index][1] = inventoryMap[index][1] - 1;
			}
		}
		if (inventoryMap[index][1] <= 0) {
			inventoryMap[index][0] = 0;
			inventoryMap[index][1] = 0;
		}
	}

	public int[][] getInventoryMap() {
		return inventoryMap;
	}

}
