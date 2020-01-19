package Curio.SessionManagers.PlantManager;

import Curio.CellularMap.CellularMap;
import Curio.SessionManagers.WorldManager.TileList;
import Curio.SessionManagers.WorldManager.TileMap;
import Curio.Utilities.CellCoordinate;

public class PlantMap extends CellularMap {
	private TileMap tilemap;

	public PlantMap(TileMap tilemap) {
		// coordinates x,y
		// int[][][0] = plant id
		// int[][][1] = plant health
		// int[][][2] = currentplantgrowth
		// int[][][3] = plantmaxgrowth
		// int[][][4] = currentStates
		// int[][][5] = maxStates
		super(tilemap.getXAxisMaxCell(), tilemap.getYAxisMaxCell(), 6);
		this.tilemap = tilemap;
		super.clearCells();
	}

	public void put(CellCoordinate cellCoordinate, int plantID) {
		put(cellCoordinate.getCellX(), cellCoordinate.getCellY(), plantID);
	}

	public void put(int x, int y, int id) {
		if (super.getCell(x, y, 0) == 0 && TileList.getTile(tilemap.getCell(x, y, 0)).getCanplant()) {
			super.setCell(x, y, 0, id);
			super.setCell(x, y, 1, PlantList.list.get(id).getMaxHealth());
			super.setCell(x, y, 2, 0);
			super.setCell(x, y, 3, PlantList.list.get(id).getMaxGrowthInMinutes());
			super.setCell(x, y, 4, 0);
			super.setCell(x, y, 5, PlantList.list.get(id).getMaxStates());
		}
	}

	public void applyDamage(int xPosition, int yPosition, int damage) {
		int tileHP = super.getCell(xPosition, yPosition, 1);
		if (tileHP > 0) {
			super.setCell(xPosition, yPosition, 1, tileHP - damage);
		}

		else if (tileHP <= 0) {
			super.clearCell(xPosition, yPosition);
		}
	}
}
