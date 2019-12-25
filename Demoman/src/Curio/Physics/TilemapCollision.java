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

	public TilemapCollision(TileMap _level, DynamicObject _dynamicObject) {
		this.console = null;
		level = _level;
		dynamicObject = _dynamicObject;
	}

	public void checkCollisions() {
		if (dynamicObject != null) {
			float radius = dynamicObject.size;
			checkEast(radius);
			checkWest(radius);
			checkNorth(radius);
			checkSouth(radius);
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