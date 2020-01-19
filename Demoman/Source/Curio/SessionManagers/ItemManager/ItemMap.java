package Curio.SessionManagers.ItemManager;

import Curio.CellularMap.CellularMap;
import Curio.SessionManagers.WorldManager.TileMap;
import Curio.Utilities.CellCoordinate;

public class ItemMap extends CellularMap {
	public ItemMap(TileMap tilemap) {
		super(tilemap.getXAxisMaxCell(), tilemap.getYAxisMaxCell(), 2);
		// coordinate system x,y
		// itemMap[][][0] = item id;
		// itemMap[][][1] = item count;
		super.clearCells();
	}

	public void applyDamage(int x, int y, int damage) {
		// just for testing
		clearCell(x, y);
	}
}
