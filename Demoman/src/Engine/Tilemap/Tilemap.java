package Engine.Tilemap;

import java.util.Random;

import org.newdawn.slick.Graphics;

import Engine.Utilities.Tileset;

public class Tilemap {
	// create tile array that holds informations
	// coords x, coord y, and tile value
	private int[][] tilemap;

	private int Cellsize;
	private Tileset Tset;

	public Tilemap(int sx, int sy, int cellsize, Tileset t) {
		tilemap = new int[sx][sy];
		Cellsize = cellsize;
		Tset = t;
	}

	// create new map or load from the server.
	// x dimensions, y dim.
	public void create_BlankLevel() {
		// create level with tile ids of 1
		createBlankTileMap();
		// createCollisionMap();
	}

	public void create_RandomLevel() {
		// create level with tile ids of 1
		createRandomTileMap();
		// createCollisionMap();
	}

	private void createBlankTileMap() {
		for (int x = 0; x < tilemap.length; x++) {
			for (int y = 0; y < tilemap[0].length; y++) {
				if (x == tilemap.length - 1 || x == 0 || y == tilemap[0].length - 1 || y == 0) {
					tilemap[x][y] = 5;
				} else {
					tilemap[x][y] = 1;
				}
			}
		}
	}

	private void createRandomTileMap() {
		int[] idArray = { 1, 2, 2, 2, 2, 2, 4, 5 };
		for (int x = 0; x < tilemap.length; x++) {
			for (int y = 0; y < tilemap[0].length; y++) {
				if (x == tilemap.length - 1 || x == 0 || y == tilemap[0].length - 1 || y == 0) {
					tilemap[x][y] = 5;
				} else {
					tilemap[x][y] = getRandom(idArray);
				}
			}
		}
	}

	private int getRandom(int[] array) {
		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}

	public void render(Graphics g) {
		// make background white
		// put dots in every corner of the cells
		for (int x = 0; x < tilemap.length; x++) {
			for (int y = 0; y < tilemap[0].length; y++) {
				// this function gets the texture image from hashmap using tile id.
				g.drawImage(Tset.getTexture(tilemap[x][y]), x * Cellsize, y * Cellsize);
			}
		}
	}

	// -----------------------------------functions-----------------------------------
	// when you receive new level data from the server, load new data and render map
	// again
	public void update_Level(int[][] lvl) {
		tilemap = lvl;
	}

	// use this to update tiles in level when server level data taking too much to
	// load.
	public void set_Tile(int x, int y, int val) {
		// change the tile and render again
		if ((x > 0) && (x < tilemap.length) && (y > 0) && (y < tilemap[0].length)) {
			tilemap[x][y] = val;
		}
	}

	public int get_Tile(int x, int y) {
		// if any request from this function exceeds tilemap borders
		// return only the border value
		if (x >= 0 && x < tilemap.length && y >= 0 && y < tilemap[0].length) {
			// if its inside the tilemap array
			return tilemap[x][y];
		} else
			return 0;
	}

	public int get_MaxCellX() {
		return tilemap.length;

	}

	public int get_MaxCellY() {
		return tilemap[0].length;

	}

	public void put_TileObj(int[][] obj, int px, int py) {
		for (int x = 0; x < obj.length; x++) {
			for (int y = 0; y < obj[0].length; y++) {
				set_Tile(px + x, py + y, obj[x][y]);
			}
		}
	}
}
