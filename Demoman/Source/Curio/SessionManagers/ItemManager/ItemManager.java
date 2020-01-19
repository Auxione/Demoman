package Curio.SessionManagers.ItemManager;

import Curio.Utilities.CellCoordinate;

public class ItemManager {
	public ItemMap itemMap;
	private int tileMaxItem;

	public ItemManager(ItemMap itemMap, int tileMaxItem) {
		this.itemMap = itemMap;
		this.tileMaxItem = tileMaxItem;
	}

	public boolean put(CellCoordinate cellCoordinate, Item item) {
		return put(cellCoordinate.getCellX(), cellCoordinate.getCellY(), item);
	}

	// this function returns true if item added or item count modified
	public boolean put(int x, int y, Item item) {
		int itemID = ItemList.IDlist.get(item);
		// check if theres a item on position
		if (itemMap.getCell(x, y, 1) < tileMaxItem) {
			if (itemMap.getCell(x, y, 0) == 0) {
				itemMap.setCell(x, y, 0, itemID);
				int itemCount = itemMap.getCell(x, y, 1);
				itemMap.setCell(x, y, 1, itemCount + 1);
				return true;
			}
			// if theres a item on that position just edit counter
			else if (itemMap.getCell(x, y, 0) == itemID) {
				int itemCount = itemMap.getCell(x, y, 1);
				itemMap.setCell(x, y, 1, itemCount + 1);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public Item getItemFromCell(CellCoordinate cellCoordinate) {
		return getItemFromCell(cellCoordinate.getCellX(), cellCoordinate.getCellY());
	}

	public Item getItemFromCell(int x, int y) {
		if (itemMap.getCell(x, y, 0) > 0 && itemMap.getCell(x, y, 1) > 0) {
			return ItemList.list.get(itemMap.getCell(x, y, 0));
		}
		return null;
	}

	public void removeItem(CellCoordinate cellCoordinate, int count) {
		removeItem(cellCoordinate.getCellX(), cellCoordinate.getCellY(), count);
	}

	public void removeItem(int x, int y, int count) {
		itemMap.setCell(x, y, 1, itemMap.getCell(x, y, 1) - count);
		// check the itemcount if its 0
		if (itemMap.getCell(x, y, 1) <= 0) {
			itemMap.clearCell(x, y);
		}
	}
}
