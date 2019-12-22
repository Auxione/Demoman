package Curio.Physics;

import Curio.Functions;
import Curio.Tileset;
import Curio.HUD.ConsoleDisplay;
import Curio.Tilemap.TileMap;
import Curio.Utilities.Math.Vector;
import Default.Constants;
import Default.Main;

public class TilemapCollision {
	private DynamicObject dynamicObject = null;
	private CellularObject cellularObject = null;

	static boolean canMoveEast, canMoveWest, canMoveNorth, canMoveSouth;
	// CollisionMap: [x][y][edge points of the cell]:
	// x1y1-------x2y2
	// ,,|``````````|
	// ,,|,,,,,,,,,,|
	// x4y4-------x3y3

	// [x][y][0] = x1 [x][y][1] = y1
	// [x][y][2] = x2 [x][y][3] = y2
	// [x][y][4] = x3 [x][y][5] = y3
	// [x][y][6] = x4 [x][y][7] = y4

	TileMap level;
	private ConsoleDisplay console;

	public TilemapCollision(TileMap _level, DynamicObject _dynamicObject, ConsoleDisplay console) {
		this.console = console;
		level = _level;
		dynamicObject = _dynamicObject;

		String cmd = "TilemapCollision: Initialized and assigned.";
		console.Add(0, cmd);
	}

	public TilemapCollision(TileMap level, CellularObject cellularObject, ConsoleDisplay console) {
		this.level = level;
		this.cellularObject = cellularObject;
		this.console = console;
		
		String cmd = "TilemapCollision: Initialized and assigned.";
		Main.console.Add(0, cmd);
	}

	public TilemapCollision(TileMap _level, DynamicObject _dynamicObject) {
		this.console = null;
		level = _level;
		dynamicObject = _dynamicObject;
	}

	public TilemapCollision(TileMap level, CellularObject cellularObject) {
		this.level = level;
		this.cellularObject = cellularObject;
		this.console = null;
	}

	public void checkCollisions() {
		if (dynamicObject != null) {
			float radius = dynamicObject.size;
			checkEast(radius);
			checkWest(radius);
			checkNorth(radius);
			checkSouth(radius);
		}
		if (cellularObject != null) {
			checkWorld();
		}
	}

	private void checkWorld() {
		int cellx = cellularObject.CellPosition.get_x();
		int celly = cellularObject.CellPosition.get_y();

		if (cellx < 0) {
			cellularObject.CellPosition.set_x(0);
		} else if (cellx >= level.get_MaxCellX()) {
			cellularObject.CellPosition.set_x(level.get_MaxCellX());
		}

		if (celly < 0) {
			cellularObject.CellPosition.set_y(0);
		} else if (celly >= level.get_MaxCellY()) {
			cellularObject.CellPosition.set_y(level.get_MaxCellX());
		}

		if (Tileset.canMove(level.get_Tile(cellx + 1, celly))) {
			canMoveEast = true;
		} else if (!Tileset.canMove(level.get_Tile(cellx + 1, celly))) {
			canMoveEast = false;
		}

		if (Tileset.canMove(level.get_Tile(cellx - 1, celly))) {
			canMoveWest = true;
		} else if (!Tileset.canMove(level.get_Tile(cellx - 1, celly))) {
			canMoveWest = false;
		}

		if (Tileset.canMove(level.get_Tile(cellx, celly - 1))) {
			canMoveNorth = true;
		} else if (!Tileset.canMove(level.get_Tile(cellx, celly - 1))) {
			canMoveNorth = false;
		}

		if (Tileset.canMove(level.get_Tile(cellx, celly + 1))) {
			canMoveSouth = true;
		} else if (!Tileset.canMove(level.get_Tile(cellx, celly + 1))) {
			canMoveSouth = false;
		}
	}

	private void checkEast(float radius) {
		int cellx = dynamicObject.CellPosition.get_x();
		int celly = dynamicObject.CellPosition.get_y();

		float Posx = dynamicObject.Position.x;
		float Posy = dynamicObject.Position.y;

		int tileid = level.get_Tile(cellx + 1, celly);

		if (Tileset.canMove(tileid) == false) {
			Vector point1 = new Vector((cellx + 1) * Constants.CellSize, celly * Constants.CellSize);
			Vector point2 = new Vector((cellx + 1) * Constants.CellSize, (celly + 1) * Constants.CellSize);
			float[] intersectPoint = Functions.lineToLineIntersectionCord(point1.x, point1.y, point2.x, point2.y, Posx,
					Posy, Posx + radius, Posy);

			if (Functions.lineToLineIntersectionBool(point1.x, point1.y, point2.x, point2.y, Posx, Posy, Posx + radius,
					Posy) == true) {
				dynamicObject.Velocity.x = 0;
				dynamicObject.Position.x = intersectPoint[0] - radius;
			}
		}
	}

	private void checkWest(float radius) {
		int cellx = dynamicObject.CellPosition.get_x();
		int celly = dynamicObject.CellPosition.get_y();

		float Posx = dynamicObject.Position.x;
		float Posy = dynamicObject.Position.y;

		int tileid = level.get_Tile(cellx - 1, celly);

		if (Tileset.canMove(tileid) == false) {
			Vector point1 = new Vector((cellx) * Constants.CellSize, celly * Constants.CellSize);
			Vector point2 = new Vector((cellx) * Constants.CellSize, (celly + 1) * Constants.CellSize);
			float[] intersectPoint = Functions.lineToLineIntersectionCord(point1.x, point1.y, point2.x, point2.y, Posx,
					Posy, Posx - radius, Posy);

			if (Functions.lineToLineIntersectionBool(point1.x, point1.y, point2.x, point2.y, Posx, Posy, Posx - radius,
					Posy) == true) {
				dynamicObject.Velocity.x = 0;
				dynamicObject.Position.x = intersectPoint[0] + radius;
			}
		}
	}

	private void checkNorth(float radius) {
		int cellx = dynamicObject.CellPosition.get_x();
		int celly = dynamicObject.CellPosition.get_y();

		float Posx = dynamicObject.Position.x;
		float Posy = dynamicObject.Position.y;

		int tileid = level.get_Tile(cellx, celly - 1);

		if (Tileset.canMove(tileid) == false) {
			Vector point1 = new Vector((cellx) * Constants.CellSize, celly * Constants.CellSize);
			Vector point2 = new Vector((cellx + 1) * Constants.CellSize, (celly) * Constants.CellSize);
			float[] intersectPoint = Functions.lineToLineIntersectionCord(point1.x, point1.y, point2.x, point2.y, Posx,
					Posy, Posx, Posy - radius);

			if (Functions.lineToLineIntersectionBool(point1.x, point1.y, point2.x, point2.y, Posx, Posy, Posx,
					Posy - radius) == true) {
				dynamicObject.Velocity.y = 0;
				dynamicObject.Position.y = intersectPoint[1] + radius;
			}
		}
	}

	private void checkSouth(float radius) {
		int cellx = dynamicObject.CellPosition.get_x();
		int celly = dynamicObject.CellPosition.get_y();

		float Posx = dynamicObject.Position.x;
		float Posy = dynamicObject.Position.y;

		int tileid = level.get_Tile(cellx, celly + 1);

		if (Tileset.canMove(tileid) == false) {
			Vector point1 = new Vector((cellx) * Constants.CellSize, (celly + 1) * Constants.CellSize);
			Vector point2 = new Vector((cellx + 1) * Constants.CellSize, (celly + 1) * Constants.CellSize);
			float[] intersectPoint = Functions.lineToLineIntersectionCord(point1.x, point1.y, point2.x, point2.y, Posx,
					Posy, Posx, Posy + radius);

			if (Functions.lineToLineIntersectionBool(point1.x, point1.y, point2.x, point2.y, Posx, Posy, Posx,
					Posy + radius) == true) {
				dynamicObject.Velocity.y = 0;
				dynamicObject.Position.y = intersectPoint[1] - radius;
			}
		}
	}

}