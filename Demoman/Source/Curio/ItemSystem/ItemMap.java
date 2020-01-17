package Curio.ItemSystem;

import java.io.Serializable;

import Curio.TileMap;
import Curio.CellularMap.CellularMap;
import Curio.Utilities.CellCoordinate;

public class ItemMap extends CellularMap implements Serializable {
	private int tileMaxItems = 5;

	public ItemMap(TileMap tilemap, int tileMaxItems) {
		super(tilemap.getXAxisMaxCell(), tilemap.getYAxisMaxCell(), 2);
		this.tileMaxItems = tileMaxItems;
		// coordinate system x,y
		// itemMap[][][0] = item id;
		// itemMap[][][1] = item count;
		super.clearCells();
	}

	public boolean put(CellCoordinate cellCoordinate, Item item) {
		return put(cellCoordinate.getCellX(), cellCoordinate.getCellY(), item);
	}

	// this function returns true if item added or item count modified
	public boolean put(int x, int y, Item item) {
		int itemID = ItemList.IDlist.get(item);
		// check if theres a item on position
		if (super.getTile(x, y, 1) <= tileMaxItems) {
			if (super.getTile(x, y, 0) == 0) {
				super.setTile(x, y, 0, itemID);
				int itemCount = super.getTile(x, y, 1);
				super.setTile(x, y, 1, itemCount + 1);
				return true;
			}
			// if theres a item on that position just edit counter
			else if (super.getTile(x, y, 0) == itemID) {
				int itemCount = super.getTile(x, y, 1);
				super.setTile(x, y, 1, itemCount + 1);
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
		if (super.getTile(x, y, 0) > 0 && super.getTile(x, y, 1) > 0) {
			return ItemList.list.get(super.getTile(x, y, 0));
		}
		return null;
	}

	public void removeItem(CellCoordinate cellCoordinate, int count) {
		removeItem(cellCoordinate.getCellX(), cellCoordinate.getCellY(), count);
	}

	public void removeItem(int x, int y, int count) {
		super.setTile(x, y, 1, super.getTile(x, y, 1) - count);
		// check the itemcount if its 0
		if (super.getTile(x, y, 1) <= 0) {
			super.clearCell(x, y);
		}
	}

	public void applyDamage(int x, int y, int damage) {
		// just for testing
		clearCell(x, y);
	}
}
