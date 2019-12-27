package Curio.Tilemap;

import java.util.Random;

import org.newdawn.slick.Graphics;

import Curio.Tileset;
import Curio.Viewport;
import Curio.HUD.ConsoleDisplay;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;
import Default.Constants;

public class TileMap {
	// create tile array that holds informations
	// coords x, coord y, and tile value
	private int[][][] tileMap;

	private int Cellsize;
	ConsoleDisplay console;

	public TileMap(int sx, int sy, int cellsize, ConsoleDisplay console) {
		tileMap = new int[sx][sy][2];

		Cellsize = cellsize;
		this.console = console;
		console.Add(0, "Map Initialized with parameters: x= " + sx + " y= " + sy + " CellSize= " + cellsize);
	}

	public TileMap(int sx, int sy, int cellsize) {
		tileMap = new int[sx][sy][2];

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
		for (int x = 0; x < tileMap.length; x++) {
			for (int y = 0; y < tileMap[0].length; y++) {
				tileMap[x][y][1] = Tileset.getHP(tileMap[x][y][0]);
			}
		}
	}

	public boolean applyDamage(int x, int y, float damage) {
		if (x >= 0 && x < tileMap.length && y >= 0 && y < tileMap[0].length) {
			tileMap[x][y][1] -= damage;
			if (tileMap[x][y][1] <= 0) {
				tileMap[x][y][1] = 0;
				tileMap[x][y][0] = 0;
				return true;
			}
			return false;
		}
		return false;
	}

	private void createBlankTileMap() {
		for (int x = 0; x < tileMap.length; x++) {
			for (int y = 0; y < tileMap[0].length; y++) {
				if (x == tileMap.length - 1 || x == 0 || y == tileMap[0].length - 1 || y == 0) {
					tileMap[x][y][0] = 5;
				} else {
					tileMap[x][y][0] = 1;
				}
			}
		}
	}

	private void createRandomTileMap() {
		int[] idArray = { 1, 2, 2, 2, 2, 2, 4, 5 };
		for (int x = 0; x < tileMap.length; x++) {
			for (int y = 0; y < tileMap[0].length; y++) {
				if (x == tileMap.length - 1 || x == 0 || y == tileMap[0].length - 1 || y == 0) {
					tileMap[x][y][0] = 5;
				} else {
					tileMap[x][y][0] = getRandom(idArray);
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
		for (int x = 0; x < tileMap.length; x++) {
			for (int y = 0; y < tileMap[0].length; y++) {
				g.drawImage(Tileset.getTexture(tileMap[x][y][0]), x * Cellsize, y * Cellsize);
			}
		}
	}

	// -----------------------------------functions-----------------------------------
	// when you receive new level data from the server, load new data and render map
	// again
	public void update_Level(int[][][] lvl) {
		tileMap = lvl;
	}

	// use this to update tiles in level when server level data taking too much to
	// load.
	public boolean set_Tile(int x, int y, int val) {
		// change the tile and render again
		if ((x > 0) && (x < tileMap.length) && (y > 0) && (y < tileMap[0].length)) {
			tileMap[x][y][0] = val;
			return true;
		} else {
			return false;
		}
	}

	public int[][][] getMap() {
		return tileMap;
	}

	public CellCoordinate worldPostoMapPos(Transform vpos) {
		CellCoordinate out = new CellCoordinate();
		if (vpos.position.x >= 0 && vpos.position.x <= tileMap.length * Constants.CellSize && vpos.position.y >= 0
				&& vpos.position.y <= tileMap[0].length * Constants.CellSize) {
			out.setCellX((int) (Math.floor(vpos.position.x / Constants.CellSize)));
			out.setCellY((int) (Math.floor(vpos.position.y / Constants.CellSize)));
		}
		return out;
	}

	public int get_MaxCellX() {
		return tileMap.length;
	}

	public int get_MaxCellY() {
		return tileMap[0].length;
	}

	public void put_TileObj(int[][] obj, int px, int py) {
		for (int x = 0; x < obj.length; x++) {
			for (int y = 0; y < obj[0].length; y++) {
				set_Tile(px + x, py + y, obj[x][y]);
			}
		}
	}

	public int get_Tile(int x, int y) {
		// if any request from this function exceeds tilemap borders
		// return only the border value
		if (x >= 0 && x < tileMap.length && y >= 0 && y < tileMap[0].length) {
			// if its inside the tilemap array
			return tileMap[x][y][0];
		} else
			return 0;
	}

	public int get_Tile(CellCoordinate cell) {
		// if any request from this function exceeds tilemap borders
		// return only the border value
		if (cell.getCellX() >= 0 && cell.getCellX() < tileMap.length && cell.getCellY() >= 0 && cell.getCellY() < tileMap[0].length) {
			// if its inside the tilemap array
			return tileMap[cell.getCellX()][cell.getCellY()][0];
		} else
			return 0;
	}
}
