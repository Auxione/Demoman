package Curio.Utilities;

import java.io.Serializable;

import Default.Constants;

public class CellCoordinate implements Serializable {
	private int x, y, z;
	
	public CellCoordinate(CellCoordinate cellCoordinate) {
		this.x = cellCoordinate.getCellX();
		this.y = cellCoordinate.getCellY();
		this.z = cellCoordinate.getCellZ();
	}
	
	public CellCoordinate(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.setCellZ(z);
	}

	public CellCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 0;
	}

	public CellCoordinate() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public void setCellX(int x) {
		this.x = x;
	}

	public void setCellY(int y) {
		this.y = y;
	}

	public void setCellZ(int z) {
		this.z = z;
	}

	public void setCell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getCellX() {
		return x;
	}

	public int getCellY() {
		return y;
	}

	public int getCellZ() {
		return z;
	}

	public int getWorldX() {
		return x * Constants.CellSize;
	}

	public int getWorldY() {
		return y * Constants.CellSize;
	}

	public int getWorldZ() {
		return z * Constants.CellSize;
	}
	
	public boolean equals(CellCoordinate cell) {
		if (this.x == cell.x && this.y == cell.y && this.z == cell.z) {
			return true;
		}
		return false;
	}
	
	public boolean equals(int x, int y, int z) {
		if (this.x == x && this.y == y && this.z == z) {
			return true;
		}
		return false;
	}
}