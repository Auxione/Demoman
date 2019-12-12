package Engine.Physics;

import Default.Constants;
import Engine.Tilemap.Tilemap;

public class TilemapCollision {
	private int[][][] collisionMap;

	// CollisionMap: [x][y][edge points of the cell]:
	// x1y1-------x2y2
	// ,,|``````````|
	// ,,|,,,,,,,,,,|
	// x4y4-------x3y3

	// [x][y][0] = x1 [x][y][1] = y1
	// [x][y][2] = x2 [x][y][3] = y2
	// [x][y][4] = x3 [x][y][5] = y3
	// [x][y][6] = x4 [x][y][7] = y4

	Tilemap level;

	TilemapCollision(Tilemap _level) {
		level = _level;
		collisionMap = new int[level.get_MaxCellX()][level.get_MaxCellY()][8];
	}

	void createCollisions() {
		for (int x = 0; x < level.get_MaxCellX(); x++) {
			for (int y = 0; y < level.get_MaxCellY(); y++) {

				int tileid = level.get_Tile(x, y);
				boolean Moveable = Constants.Tset.getMoveable(tileid);

				if (Moveable == false) {
					collisionMap[x][y][0] = 0;
				}
			}
		}

	}

	void checkCollisions() {

	}
}