package Curio;

import Curio.CellularMap.CellularMap;
import Curio.Renderer.ConsoleDisplay;
import Curio.SessionManagers.WorldManager.TileList;
import Curio.SessionManagers.WorldManager.TileMap;

public class FluidMap extends CellularMap {
	private TileMap tileMap;
	ConsoleDisplay console;

	private float timeGoal = 0;
	private int goalDelay = 200;
	private int transferRate = 1;
	public int maxCellVolume = 32;
	public int minCellVolume = 0;

	public FluidMap(TileMap tileMap) {
		super(tileMap.getXAxisMaxCell(), tileMap.getYAxisMaxCell(), 1);
		this.tileMap = tileMap;
		clearCells();
	}

	public void update() {
		if (Functions.millis() > timeGoal) {
			for (int x = 0; x < super.getXAxisMaxCell(); x++) {
				for (int y = 0; y < super.getYAxisMaxCell(); y++) {
					if (x >= 1 && x < super.getXAxisMaxCell() - 1 && y >= 1 && y < super.getYAxisMaxCell() - 1) {
						if (super.getCell(x, y, 0) > minCellVolume) {
							if (checkWest(x, y) == true) {
								transferWest(x, y);
							}
							if (checkEast(x, y) == true) {
								transferEast(x, y);
							}
							if (checkNorth(x, y) == true) {
								transferNorth(x, y);
							}
							if (checkSouth(x, y) == true) {
								transferSouth(x, y);
							}
						}
						constrain(x, y);
					}
				}
			}
			timeGoal = Functions.millis() + goalDelay;
		}
	}
	
	public void put(int x, int y, int amount) {
		super.setCell(x, y, 0, amount);
		constrain(x, y);
	}

	private void constrain(int x, int y) {
		if (super.getCell(x, y, 0) >= maxCellVolume) {
			super.setCell(x, y, 0, maxCellVolume);
		}

		else if (super.getCell(x, y, 0) <= minCellVolume) {
			super.setCell(x, y, 0, minCellVolume);
		}
	}

	private void transferWest(int x, int y) {
		super.setCell(x, y, 0, super.getCell(x, y, 0) - transferRate);
		super.setCell(x - 1, y, 0, super.getCell(x - 1, y, 0) + transferRate);
	}

	private void transferEast(int x, int y) {
		super.setCell(x, y, 0, super.getCell(x, y, 0) - transferRate);
		super.setCell(x + 1, y, 0, super.getCell(x + 1, y, 0) + transferRate);
	}

	private void transferNorth(int x, int y) {
		super.setCell(x, y, 0, super.getCell(x, y, 0) - transferRate);
		super.setCell(x, y - 1, 0, super.getCell(x, y - 1, 0) + transferRate);
	}

	private void transferSouth(int x, int y) {
		super.setCell(x, y, 0, super.getCell(x, y, 0) - transferRate);
		super.setCell(x, y + 1, 0, super.getCell(x, y + 1, 0) + transferRate);
	}

	private boolean checkWest(int x, int y) {
		if (TileList.getTile(tileMap.getCell(x - 1, y, 0)).getCanflow() == true) {
			if (tileMap.getCell(x - 1, y, 0) <= maxCellVolume && tileMap.getCell(x - 1, y, 0) >= minCellVolume) {
				if (tileMap.getCell(x - 1, y, 0) < tileMap.getCell(x, y, 0)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkEast(int x, int y) {
		if (TileList.getTile(tileMap.getCell(x + 1, y, 0)).getCanflow() == true) {
			if (tileMap.getCell(x + 1, y, 0) <= maxCellVolume && tileMap.getCell(x + 1, y, 0) >= minCellVolume) {
				if (tileMap.getCell(x + 1, y, 0) < tileMap.getCell(x, y, 0)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkNorth(int x, int y) {
		if (TileList.getTile(tileMap.getCell(x, y - 1, 0)).getCanflow() == true) {
			if (tileMap.getCell(x, y - 1, 0) <= maxCellVolume && tileMap.getCell(x, y - 1, 0) >= minCellVolume) {
				if (tileMap.getCell(x, y - 1, 0) < tileMap.getCell(x, y, 0)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkSouth(int x, int y) {
		if (TileList.getTile(tileMap.getCell(x, y + 1, 0)).getCanflow() == true) {
			if (tileMap.getCell(x, y + 1, 0) <= maxCellVolume && tileMap.getCell(x, y + 1, 0) >= minCellVolume) {
				if (tileMap.getCell(x, y + 1, 0) < tileMap.getCell(x, y, 0)) {
					return true;
				}
			}
		}
		return false;
	}
}
