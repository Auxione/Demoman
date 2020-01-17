package Curio.Physics;

import Curio.Functions;
import Curio.TileMap;
import Curio.TileList;
import Curio.Utilities.Math.Vector;
import Default.Constants;

public class TilemapCollision {
	private DynamicObject dynamicObject;

	public boolean canMoveEast, canMoveWest, canMoveNorth, canMoveSouth;
	// CollisionMap: [x][y][edge points of the cell]:
	// x1y1-------x2y2
	// ,,|``````````|
	// ,,|,,,,,,,,,,|
	// x4y4-------x3y3

	// [x][y][0] = x1 [x][y][1] = y1
	// [x][y][2] = x2 [x][y][3] = y2
	// [x][y][4] = x3 [x][y][5] = y3
	// [x][y][6] = x4 [x][y][7] = y4

	private TileMap tilemap;

	public TilemapCollision(TileMap tilemap, DynamicObject dynamicObject) {
		this.tilemap = tilemap;
		this.dynamicObject = dynamicObject;
	}

	public void checkCollisions() {
		float radius = dynamicObject.size;
		checkEast(radius);
		checkWest(radius);
		checkNorth(radius);
		checkSouth(radius);
	}
	
	private void checkNorthEastCorner(float radius) {
		int cellx = dynamicObject.gameObject.cellCoordinate.getCellX();
		int celly = dynamicObject.gameObject.cellCoordinate.getCellY();

		float Posx = dynamicObject.gameObject.transform.position.x;
		float Posy = dynamicObject.gameObject.transform.position.y;

		int tileid = tilemap.getTile(cellx + 1, celly-1, 0);

		if (TileList.getTile(tileid).isSolid() == true) {
			Vector point = new Vector((cellx + 1) * Constants.CellSize, (celly) * Constants.CellSize, 0);

			if (point.dist(dynamicObject.gameObject.transform.position) <= radius) {
			}
		}
	}
	
	private void checkEast(float radius) {
		int cellx = dynamicObject.gameObject.cellCoordinate.getCellX();
		int celly = dynamicObject.gameObject.cellCoordinate.getCellY();

		float Posx = dynamicObject.gameObject.transform.position.x;
		float Posy = dynamicObject.gameObject.transform.position.y;

		int tileid = tilemap.getTile(cellx + 1, celly, 0);
		
		if (TileList.getTile(tileid).isSolid() == true) {
			Vector point1 = new Vector((cellx + 1) * Constants.CellSize, celly * Constants.CellSize, 0);
			Vector point2 = new Vector((cellx + 1) * Constants.CellSize, (celly + 1) * Constants.CellSize, 0);
			float[] intersectPoint = Functions.lineToLineIntersectionCord(point1.x, point1.y, point2.x, point2.y, Posx,
					Posy, Posx + radius, Posy);

			if (Functions.lineToLineIntersectionBool(point1.x, point1.y, point2.x, point2.y, Posx, Posy, Posx + radius,
					Posy) == true) {
				dynamicObject.Velocity.x = 0;
				dynamicObject.gameObject.transform.position.x = intersectPoint[0] - radius;
			}
		}
	}

	private void checkWest(float radius) {
		int cellx = dynamicObject.gameObject.cellCoordinate.getCellX();
		int celly = dynamicObject.gameObject.cellCoordinate.getCellY();

		float Posx = dynamicObject.gameObject.transform.position.x;
		float Posy = dynamicObject.gameObject.transform.position.y;

		int tileid = tilemap.getTile(cellx - 1, celly, 0);
		
		if (TileList.getTile(tileid).isSolid() == true) {
			Vector point1 = new Vector((cellx) * Constants.CellSize, celly * Constants.CellSize, 0);
			Vector point2 = new Vector((cellx) * Constants.CellSize, (celly + 1) * Constants.CellSize, 0);
			float[] intersectPoint = Functions.lineToLineIntersectionCord(point1.x, point1.y, point2.x, point2.y, Posx,
					Posy, Posx - radius, Posy);
			
			if (Functions.lineToLineIntersectionBool(point1.x, point1.y, point2.x, point2.y, Posx, Posy, Posx - radius,
					Posy) == true) {

				dynamicObject.Velocity.x = 0;
				dynamicObject.gameObject.transform.position.x = intersectPoint[0] + radius;
			}
		}
	}

	private void checkNorth(float radius) {
		int cellx = dynamicObject.gameObject.cellCoordinate.getCellX();
		int celly = dynamicObject.gameObject.cellCoordinate.getCellY();

		float Posx = dynamicObject.gameObject.transform.position.x;
		float Posy = dynamicObject.gameObject.transform.position.y;

		int tileid = tilemap.getTile(cellx, celly - 1, 0);
		
		if (TileList.getTile(tileid).isSolid() == true) {
			Vector point1 = new Vector((cellx) * Constants.CellSize, celly * Constants.CellSize);
			Vector point2 = new Vector((cellx + 1) * Constants.CellSize, (celly) * Constants.CellSize);
			float[] intersectPoint = Functions.lineToLineIntersectionCord(point1.x, point1.y, point2.x, point2.y, Posx,
					Posy, Posx, Posy - radius);

			if (Functions.lineToLineIntersectionBool(point1.x, point1.y, point2.x, point2.y, Posx, Posy, Posx,
					Posy - radius) == true) {
				dynamicObject.Velocity.y = 0;
				dynamicObject.gameObject.transform.position.y = intersectPoint[1] + radius;
			}
		}
	}

	private void checkSouth(float radius) {
		int cellx = dynamicObject.gameObject.cellCoordinate.getCellX();
		int celly = dynamicObject.gameObject.cellCoordinate.getCellY();

		float Posx = dynamicObject.gameObject.transform.position.x;
		float Posy = dynamicObject.gameObject.transform.position.y;

		int tileid = tilemap.getTile(cellx, celly + 1, 0);
		
		if (TileList.getTile(tileid).isSolid() == true) {
			Vector point1 = new Vector((cellx) * Constants.CellSize, (celly + 1) * Constants.CellSize);
			Vector point2 = new Vector((cellx + 1) * Constants.CellSize, (celly + 1) * Constants.CellSize);
			float[] intersectPoint = Functions.lineToLineIntersectionCord(point1.x, point1.y, point2.x, point2.y, Posx,
					Posy, Posx, Posy + radius);

			if (Functions.lineToLineIntersectionBool(point1.x, point1.y, point2.x, point2.y, Posx, Posy, Posx,
					Posy + radius) == true) {
				dynamicObject.Velocity.y = 0;
				dynamicObject.gameObject.transform.position.y = intersectPoint[1] - radius;
			}
		}
	}

}