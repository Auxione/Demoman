package Curio.Utilities;

import java.util.ArrayList;

import Curio.TileMap;
import Curio.TileList;;

public class FloodFill {

	private TileMap tilemap;
	private boolean[][] array;
	private ArrayList<CellCoordinate> list = new ArrayList<CellCoordinate>();
	//private HashMap<CellCoordinate,Boolean> list = new ArrayList<CellCoordinate>();
	
	public FloodFill(TileMap tilemap, int x, int y) {
		this.tilemap = tilemap;
		this.array = new boolean[tilemap.getXAxisMaxCell()][tilemap.getYAxisMaxCell()];
		Floodfill(x, y);
	}

	public ArrayList<CellCoordinate> getCellList() {
		return list;
	}

	public boolean[][] getArray() {
		return array;
	}

	public void Floodfill(int x, int y) {
		if ((x <= 0) && (x >= tilemap.getXAxisMaxCell()) && (y <= 0) && (y >= tilemap.getYAxisMaxCell())) {
			return;
		} else if (TileList.getTile(tilemap.getTile(x, y, 0)).isMoveable() == false) {
			return;
		} else if (array[x][y] == true) {
			return;
		} else if (TileList.getTile(tilemap.getTile(x, y, 0)).isMoveable() == true) {
			array[x][y] = true;
			
			list.add(new CellCoordinate(x, y));
			Floodfill(x + 1, y);
			Floodfill(x, y + 1);
			Floodfill(x - 1, y);
			Floodfill(x, y - 1);
		}
		return;
	}
}
