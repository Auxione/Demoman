package Curio.Physics;

import java.util.ArrayList;

import Curio.Tilemap.TileMap;
import Curio.Utilities.CellCoordinate;

public class CellularObject {
	public static ArrayList<CellularObject> cellularObjectList = new ArrayList<CellularObject>();
	TileMap level;

	public CellCoordinate CellPosition;

	protected CellularObject(TileMap level, int positionX, int positionY) {
		this.level = level;

		CellPosition = new CellCoordinate(positionX, positionY);
		cellularObjectList.add(this);
	}

	public void move(int x, int y) {
		switch (y) {
		case -1:
			if (TilemapCollision.canMoveNorth == true) {
				CellPosition.setCellY(CellPosition.getCellY() - 1);
			}
			break;
		case 0:
			break;
		case +1:
			if (TilemapCollision.canMoveSouth == true) {
				CellPosition.setCellY(CellPosition.getCellY() + 1);
			}
			break;
		}

		switch (x) {
		case -1:
			if (TilemapCollision.canMoveWest == true) {
				CellPosition.setCellX(CellPosition.getCellX() - 1);
			}
			break;
		case 0:
			break;
		case +1:
			if (TilemapCollision.canMoveEast == true) {
				CellPosition.setCellX(CellPosition.getCellX() + 1);
			}
			break;
		}
	}

	public void set(int x, int y) {
		CellPosition.setCellX(x);
		CellPosition.setCellY(y);
	}

	public void set( CellCoordinate tr) {
		CellPosition.setCellX(tr.getCellX());
		CellPosition.setCellY(tr.getCellY());
	}
}
