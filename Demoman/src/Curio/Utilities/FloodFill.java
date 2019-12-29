package Curio.Utilities;

import java.util.ArrayList;

import Curio.Tileset;
import Curio.Tilemap.TileMap;

public class FloodFill {

	private TileMap tilemap;
	private boolean[][] array;
	private ArrayList<CellCoordinate> list = new ArrayList<CellCoordinate>();
	//private HashMap<CellCoordinate,Boolean> list = new ArrayList<CellCoordinate>();
	
	public FloodFill(TileMap tilemap, int x, int y) {
		this.tilemap = tilemap;
		this.array = new boolean[tilemap.get_MaxCellX()][tilemap.get_MaxCellY()];
		Floodfill(x, y);
	}

	public ArrayList<CellCoordinate> getCellList() {
		return list;
	}

	public boolean[][] getArray() {
		return array;
	}

	public void Floodfill(int x, int y) {
		if ((x <= 0) && (x >= tilemap.get_MaxCellX()) && (y <= 0) && (y >= tilemap.get_MaxCellY())) {
			return;
		} else if (Tileset.canMove(tilemap.get_Tile(x, y)) == false) {
			return;
		} else if (array[x][y] == true) {
			return;
		} else if (Tileset.canMove(tilemap.get_Tile(x, y)) == true) {
			array[x][y] = true;
			for(CellCoordinate l :list) {
				
			}
			list.add(new CellCoordinate(x, y));
			Floodfill(x + 1, y);
			Floodfill(x, y + 1);
			Floodfill(x - 1, y);
			Floodfill(x, y - 1);
		}
		return;
	}
}
