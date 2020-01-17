package Curio.Utilities;

import java.util.ArrayList;
import Curio.CellularMap.CellularMap;

public class FloodFill {

	private CellularMap cellularMap;
	private boolean[][] array;
	private ArrayList<CellCoordinate> list = new ArrayList<CellCoordinate>();
	private int wantedID;

	public FloodFill(CellularMap cellularMap, int x, int y, int wantedID) {
		this.cellularMap = cellularMap;
		this.array = new boolean[cellularMap.getXAxisMaxCell()][cellularMap.getYAxisMaxCell()];
		this.wantedID = wantedID;
		Floodfill(x, y);
	}

	public ArrayList<CellCoordinate> getCellList() {
		return list;
	}

	public boolean[][] getArray() {
		return array;
	}

	public void Floodfill(int x, int y) {
		if ((x <= 0) && (x >= cellularMap.getXAxisMaxCell()) && (y <= 0) && (y >= cellularMap.getYAxisMaxCell())) {
			return;
		} else if (cellularMap.getTile(x, y, 0) != wantedID) {
			return;
		} else if (array[x][y] == true) {
			return;
		} else if (cellularMap.getTile(x, y, 0) == wantedID) {
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
