package Curio.ItemSystem;

import java.io.Serializable;

import Curio.TileMap;
import Curio.CellularMap.CellularMap;

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

	// this function returns true if item added or item count modified
	public boolean put(int x, int y, int id) {
		// check if theres a item on position
		if (super.getTile(x, y, 1) <= tileMaxItems) {
			if (super.getTile(x, y, 0) == 0) {
				super.setTile(x, y, 0, id);
				int itemCount = super.getTile(x, y, 1);
				super.setTile(x, y, 1, itemCount + 1);
				return true;
			}
			// if theres a item on that position just edit counter
			else if (super.getTile(x, y, 0) == id) {
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

	public void applyDamage(int x, int y, int damage) {
		clearCell(x, y);
	}

	// this function returns item id if conditions are met
	public int getItemID(int x, int y) {
		if (super.getTile(x, y, 0) > 0 && super.getTile(x, y, 1) > 0) {
			return super.getTile(x, y, 0);
		}
		return 0;
	}

	public int getItemCategory(int x, int y) {
		if (super.getTile(x, y, 0) > 0 && super.getTile(x, y, 1) > 0) {
			return ItemList.list.get(super.getTile(x, y, 0)).getItemCategory();
		}
		return 0;
	}

	public void remove(int x, int y) {
		int itemCount = super.getTile(x, y, 1);
		super.setTile(x, y, 1, itemCount - 1);
		if (super.getTile(x, y, 0) == 0 || super.getTile(x, y, 1) <= 0) {
			clearCell(x, y);
		}
	}
}
