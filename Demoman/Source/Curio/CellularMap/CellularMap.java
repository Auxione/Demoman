package Curio.CellularMap;

import java.io.Serializable;

import Curio.Console;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;
import Default.Constants;

public class CellularMap implements Serializable {
	protected Console console = null;
	protected int[][][] cellularMap = null;
	private int xAxisSize;
	private int yAxisSize;
	private int zAxisSize;

	public CellularMap(int xAxisSize, int yAxisSize, int zAxisSize) {
		this.xAxisSize = xAxisSize;
		this.yAxisSize = yAxisSize;
		this.zAxisSize = zAxisSize;
		this.cellularMap = new int[xAxisSize][yAxisSize][zAxisSize];
	}

	public CellularMap setConsole(Console console) {
		this.console = console;
		return this;
	}

	public void setCellularMap(int[][][] cellularMap) {
		this.cellularMap = cellularMap;
	}

	public int[][][] getCellularMap() {
		return this.cellularMap;
	}

	public void setTile(int xPosition, int yPosition, int zPosition, int value) {
		// change the tile and render again
		if ((xPosition >= 0) && (xPosition < cellularMap.length)) {
			if ((yPosition >= 0) && (yPosition < cellularMap[0].length)) {
				if ((zPosition >= 0) && (zPosition < cellularMap[0][0].length)) {
					cellularMap[xPosition][yPosition][zPosition] = value;
					if (console != null) {
						console.Add(0, "Cell Changed at x: " + xPosition + " y: " + yPosition + " z: " + zPosition
								+ " to value: " + value);
					}
				}
			}
		}
	}

	public void setTile(CellCoordinate cellCoordinate, int zPosition, int value) {
		// change the tile and render again
		if ((cellCoordinate.getCellX() >= 0) && (cellCoordinate.getCellX() < cellularMap.length)) {
			if ((cellCoordinate.getCellY() >= 0) && (cellCoordinate.getCellY() < cellularMap[0].length)) {
				if ((zPosition >= 0) && (zPosition < cellularMap[0][0].length)) {
					cellularMap[cellCoordinate.getCellX()][cellCoordinate.getCellY()][zPosition] = value;
					if (console != null) {
						console.Add(0, "Cell Changed at x: " + cellCoordinate.getCellX() + " y: "
								+ cellCoordinate.getCellY() + " z: " + zPosition + " to value: " + value);
					}
				}
			}
		}
	}

	public void setTile(int xPosition, int yPosition, int zPosition, int[][] value) {
		for (int valueX = 0; valueX < value.length; valueX++) {
			for (int valueY = 0; valueY < value[0].length; valueY++) {
				if ((xPosition + valueX >= 0) && (xPosition + valueX < cellularMap.length)) {
					if ((yPosition + valueY >= 0) && (yPosition + valueY < cellularMap[0].length)) {
						if ((zPosition >= 0) && (zPosition < cellularMap[0][0].length)) {
							this.cellularMap[valueX + xPosition][valueY + yPosition][zPosition] = value[valueX][valueY];
						}
					}
				}
			}
		}
	}

	public int getTile(int xPosition, int yPosition, int zPosition) {
		if ((xPosition >= 0) && (xPosition < cellularMap.length)) {
			if ((yPosition >= 0) && (yPosition < cellularMap[0].length)) {
				if ((zPosition >= 0) && (zPosition < cellularMap[0][0].length)) {
					if (console != null) {
						console.Add(0,
								"Cell in x: " + xPosition + " y: " + yPosition + " z: " + zPosition + " requested.");
					}
					return cellularMap[xPosition][yPosition][zPosition];
				}
			}
		}
		return 0;
	}

	public int getTile(CellCoordinate cellCoordinate, int zPosition) {
		return getTile(cellCoordinate.getCellX(), cellCoordinate.getCellY(), zPosition);
	}

	public void clearCells() {
		for (int x = 0; x < getXAxisMaxCell(); x++) {
			for (int y = 0; y < getYAxisMaxCell(); y++) {
				for (int z = 0; z < getZAxisMaxCell(); z++) {
					setTile(x, y, z, 0);
				}
			}
		}
	}

	public void clearCell(CellCoordinate cellCoordinate) {
		clearCell(cellCoordinate.getCellX(), cellCoordinate.getCellY());
	}

	public void clearCell(int x, int y) {
		for (int z = 0; z < getZAxisMaxCell(); z++) {
			setTile(x, y, z, 0);
		}
	}

	public boolean isEmpty(int x, int y) {
		for (int z = 0; z < getZAxisMaxCell(); z++) {
			if (getTile(x, y, z) != 0) {
				return false;
			}
		}
		return true;
	}

	public int getXAxisMaxCell() {
		return xAxisSize;
	}

	public int getYAxisMaxCell() {
		return yAxisSize;
	}

	public int getZAxisMaxCell() {
		return zAxisSize;
	}

}
