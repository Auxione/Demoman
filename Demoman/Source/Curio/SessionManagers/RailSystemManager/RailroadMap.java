package Curio.SessionManagers.RailSystemManager;

import Curio.CellularMap.CellularMap;
import Curio.SessionManagers.WorldManager.TileMap;
import Curio.Utilities.CellCoordinate;

public class RailroadMap extends CellularMap {
	public RailroadMap(TileMap tilemap) {
		super(tilemap.getXAxisMaxCell(), tilemap.getYAxisMaxCell(), 1);
	}

	public void putHorizantalRail(CellCoordinate cellCoordinate) {
		putHorizantalRail(cellCoordinate.getCellX(), cellCoordinate.getCellY());
	}

	public void putHorizantalRail(int x, int y) {
		super.setCell(x, y, 0, 1);
	}
	
	public void putVerticalRail(CellCoordinate cellCoordinate) {
		putVerticalRail(cellCoordinate.getCellX(), cellCoordinate.getCellY());
	}

	public void putVerticalRail(int x, int y) {
		super.setCell(x, y, 0, 2);
	}

	private void checkSurroundings(CellCoordinate cellCoordinate) {
		int originX = cellCoordinate.getCellX();
		int originY = cellCoordinate.getCellY();

		if (super.getCell(originX + 1, originY, 0) == 1) {

		}

		if (super.getCell(originX - 1, originY, 0) == 1) {

		}

		if (super.getCell(originX, originY + 1, 0) == 1) {

		}

		if (super.getCell(originX, originY - 1, 0) == 1) {

		}
	}
}
