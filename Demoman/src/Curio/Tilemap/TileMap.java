package Curio.Tilemap;

import java.util.Random;

import org.newdawn.slick.Graphics;

import Curio.Tileset;
import Curio.Viewport;
import Curio.HUD.ConsoleDisplay;
import Curio.Utilities.Math.Transform;
import Curio.Utilities.Math.Vector;
import Default.Constants;

public class TileMap {
	// create tile array that holds informations
	// coords x, coord y, and tile value
	private int[][] tilemap;
	private float[][] tileHPMap;

	private int Cellsize;
	ConsoleDisplay console;

	public TileMap(int sx, int sy, int cellsize, ConsoleDisplay console) {
		tilemap = new int[sx][sy];
		tileHPMap = new float[sx][sy];

		Cellsize = cellsize;
		this.console = console;
		console.Add(0, "Map Initialized with parameters: x= " + sx + " y= " + sy + " CellSize= " + cellsize);
	}

	public TileMap(int sx, int sy, int cellsize) {
		tilemap = new int[sx][sy];
		tileHPMap = new float[sx][sy];

		Cellsize = cellsize;
		this.console = null;
	}

	// create new map or load from the server.
	// x dimensions, y dim.
	public void create_BlankLevel() {
		createBlankTileMap();
		generateTileHPs();
		if (console != null) {
			console.Add(0, "Map Created");
		}
	}

	public void create_RandomLevel() {
		createRandomTileMap();
		generateTileHPs();
		if (console != null) {
			console.Add(0, "Map Created");
		}
	}

	private void generateTileHPs() {
		for (int x = 0; x < tilemap.length; x++) {
			for (int y = 0; y < tilemap[0].length; y++) {
				tileHPMap[x][y] = Tileset.getHP(tilemap[x][y]);
			}
		}
	}

	public boolean applyDamage(int x, int y, float damage) {
		if (x >= 0 && x < tilemap.length && y >= 0 && y < tilemap[0].length) {
			tileHPMap[x][y] -= damage;
			if (tileHPMap[x][y] <= 0) {
				tilemap[x][y] = 0;
				return true;
			}
			return false;
		}
		return false;
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

	public void render(Graphics g, Viewport vp) {
		// make background white
		// put dots in every corner of the cells
		for (int x = 0; x < tilemap.length; x++) {
			for (int y = 0; y < tilemap[0].length; y++) {
				g.drawImage(Tileset.getTexture(tilemap[x][y]), x * Cellsize, y * Cellsize);
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
	public boolean set_Tile(int x, int y, int val) {
		// change the tile and render again
		if ((x > 0) && (x < tilemap.length) && (y > 0) && (y < tilemap[0].length)) {
			tilemap[x][y] = val;
			return true;
		} else {
			return false;
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

	public int[][] get_Tilemap() {
		return tilemap;
	}

	public Transform worldPostoMapPos(Vector vpos) {
		Transform out = new Transform();
		if (vpos.x >= 0 && vpos.x <= tilemap.length * Constants.CellSize && vpos.y >= 0
				&& vpos.y <= tilemap[0].length * Constants.CellSize) {
			out.set_x((int) (vpos.x / Constants.CellSize));
			out.set_y((int) (vpos.y / Constants.CellSize));
		}
		return out;
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
