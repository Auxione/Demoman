package Curio.Physics.Collisions;

import Curio.Functions;
import Curio.Physics.DynamicObject;
import Curio.Physics.Interfaces.FrameUpdate;
import Curio.SessionManagers.WorldManager.TileList;
import Curio.SessionManagers.WorldManager.TileMap;
import Curio.Utilities.Math.Vector;
import Curio.Utilities.Math.Geometry.Circle;
import Curio.Utilities.Math.Geometry.Rectangle;
import Curio.Utilities.Math.Geometry.Shape;
import Default.Constants;

public class TilemapCollision implements Collision {
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
	private Shape shape;
	private TileMap tilemap;
	private Boolean active = true;

	public TilemapCollision(TileMap tilemap) {
		this.tilemap = tilemap;
	}

	public TilemapCollision setShape(Shape shape) {
		this.shape = shape;
		return this;
	}

	public TilemapCollision setState(Boolean active) {
		this.active = active;
		return this;
	}
	
	public TilemapCollision setDynamicObject(DynamicObject dynamicObject) {
		this.dynamicObject = dynamicObject;
		return this;
	}

	@Override
	public void frameUpdate() {
		if (active == true) {
			if (shape instanceof Circle) {
				Circle c = (Circle) shape;
				checkEast(c.radius);
				checkWest(c.radius);
				checkNorth(c.radius);
				checkSouth(c.radius);
			}

			else if (shape instanceof Rectangle) {
				Rectangle r = (Rectangle) shape;
			}
		}
	}

	private void checkEast(float radius) {
		int cellx = dynamicObject.cellCoordinate.getCellX();
		int celly = dynamicObject.cellCoordinate.getCellY();

		float Posx = dynamicObject.transform.position.x;
		float Posy = dynamicObject.transform.position.y;

		int tileid = tilemap.getCell(cellx + 1, celly, 0);

		if (TileList.getTile(tileid).isSolid() == true) {
			Vector point1 = new Vector((cellx + 1) * Constants.CellSize, celly * Constants.CellSize);
			Vector point2 = new Vector((cellx + 1) * Constants.CellSize, (celly + 1) * Constants.CellSize);
			float[] intersectPoint = Functions.lineToLineIntersectionCord(point1.x, point1.y, point2.x, point2.y, Posx,
					Posy, Posx + radius, Posy);

			if (Functions.lineToLineIntersectionBool(point1.x, point1.y, point2.x, point2.y, Posx, Posy, Posx + radius,
					Posy) == true) {
				dynamicObject.velocity.x = 0;
				dynamicObject.transform.position.x = intersectPoint[0] - radius;
			}
		}
	}

	private void checkWest(float radius) {
		int cellx = dynamicObject.cellCoordinate.getCellX();
		int celly = dynamicObject.cellCoordinate.getCellY();

		float Posx = dynamicObject.transform.position.x;
		float Posy = dynamicObject.transform.position.y;

		int tileid = tilemap.getCell(cellx - 1, celly, 0);

		if (TileList.getTile(tileid).isSolid() == true) {
			Vector point1 = new Vector((cellx) * Constants.CellSize, celly * Constants.CellSize);
			Vector point2 = new Vector((cellx) * Constants.CellSize, (celly + 1) * Constants.CellSize);
			float[] intersectPoint = Functions.lineToLineIntersectionCord(point1.x, point1.y, point2.x, point2.y, Posx,
					Posy, Posx - radius, Posy);

			if (Functions.lineToLineIntersectionBool(point1.x, point1.y, point2.x, point2.y, Posx, Posy, Posx - radius,
					Posy) == true) {

				dynamicObject.velocity.x = 0;
				dynamicObject.transform.position.x = intersectPoint[0] + radius;
			}
		}
	}

	private void checkNorth(float radius) {
		int cellx = dynamicObject.cellCoordinate.getCellX();
		int celly = dynamicObject.cellCoordinate.getCellY();

		float Posx = dynamicObject.transform.position.x;
		float Posy = dynamicObject.transform.position.y;

		int tileid = tilemap.getCell(cellx, celly - 1, 0);

		if (TileList.getTile(tileid).isSolid() == true) {
			Vector point1 = new Vector((cellx) * Constants.CellSize, celly * Constants.CellSize);
			Vector point2 = new Vector((cellx + 1) * Constants.CellSize, (celly) * Constants.CellSize);
			float[] intersectPoint = Functions.lineToLineIntersectionCord(point1.x, point1.y, point2.x, point2.y, Posx,
					Posy, Posx, Posy - radius);

			if (Functions.lineToLineIntersectionBool(point1.x, point1.y, point2.x, point2.y, Posx, Posy, Posx,
					Posy - radius) == true) {
				dynamicObject.velocity.y = 0;
				dynamicObject.transform.position.y = intersectPoint[1] + radius;
			}
		}
	}

	private void checkSouth(float radius) {
		int cellx = dynamicObject.cellCoordinate.getCellX();
		int celly = dynamicObject.cellCoordinate.getCellY();

		float Posx = dynamicObject.transform.position.x;
		float Posy = dynamicObject.transform.position.y;

		int tileid = tilemap.getCell(cellx, celly + 1, 0);

		if (TileList.getTile(tileid).isSolid() == true) {
			Vector point1 = new Vector((cellx) * Constants.CellSize, (celly + 1) * Constants.CellSize);
			Vector point2 = new Vector((cellx + 1) * Constants.CellSize, (celly + 1) * Constants.CellSize);
			float[] intersectPoint = Functions.lineToLineIntersectionCord(point1.x, point1.y, point2.x, point2.y, Posx,
					Posy, Posx, Posy + radius);

			if (Functions.lineToLineIntersectionBool(point1.x, point1.y, point2.x, point2.y, Posx, Posy, Posx,
					Posy + radius) == true) {
				dynamicObject.velocity.y = 0;
				dynamicObject.transform.position.y = intersectPoint[1] - radius;
			}
		}
	}

}