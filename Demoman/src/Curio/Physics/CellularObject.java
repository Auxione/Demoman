package Curio.Physics;

import java.util.ArrayList;

import Curio.Tilemap.TileMap;
import Curio.Utilities.Math.Transform;

public class CellularObject {
	public static ArrayList<CellularObject> cellularObjectList = new ArrayList<CellularObject>();
	TileMap level;

	public Transform CellPosition;

	protected CellularObject(TileMap level, int positionX, int positionY) {
		this.level = level;

		CellPosition = new Transform(positionX, positionY);
		cellularObjectList.add(this);
	}

	public void move(int x, int y) {
		switch (y) {
		case -1:
			if (TilemapCollision.canMoveNorth == true) {
				CellPosition.set_y(CellPosition.get_y() - 1);
			}
			break;
		case 0:
			break;
		case +1:
			if (TilemapCollision.canMoveSouth == true) {
				CellPosition.set_y(CellPosition.get_y() + 1);
			}
			break;
		}

		switch (x) {
		case -1:
			if (TilemapCollision.canMoveWest == true) {
				CellPosition.set_x(CellPosition.get_x() - 1);
			}
			break;
		case 0:
			break;
		case +1:
			if (TilemapCollision.canMoveEast == true) {
				CellPosition.set_x(CellPosition.get_x() + 1);
			}
			break;
		}
	}

	public void set(int x, int y) {
		CellPosition.set_x(x);
		CellPosition.set_y(y);
	}

	public void set(Transform tr) {
		CellPosition.set_x(tr.get_x());
		CellPosition.set_y(tr.get_y());
	}
}
