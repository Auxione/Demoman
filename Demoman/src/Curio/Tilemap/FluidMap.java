package Curio.Tilemap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Functions;
import Curio.Tileset;
import Curio.HUD.ConsoleDisplay;

public class FluidMap {
	private int[][] fluidMap;
	private TileMap tileMap;
	ConsoleDisplay console;

	private int timeGoal = 0;
	private int goalDelay = 200;
	private int transferRate = 1;
	private int CellSize = 32;
	private int maxCellVolume = 32;
	private int minCellVolume = 0;

	public FluidMap(TileMap tileMap) {
		this.fluidMap = new int[tileMap.get_MaxCellX()][tileMap.get_MaxCellY()];
		this.tileMap = tileMap;
		clearMap();
	}

	private void clearMap() {
		for (int x = 0; x < fluidMap.length; x++) {
			for (int y = 0; y < fluidMap[0].length; y++) {
				fluidMap[x][y] = 0;
			}
		}
	}
	
	public void update() {
		if (Functions.millis() > timeGoal) {
			for (int x = 0; x < fluidMap.length; x++) {
				for (int y = 0; y < fluidMap[0].length; y++) {
					if (x >= 1 && x < fluidMap.length - 1 && y >= 1 && y < fluidMap[0].length - 1) {
						if (fluidMap[x][y] > minCellVolume) {
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

	public void render(Graphics g) {
		for (int x = 0; x < fluidMap.length; x++) {
			for (int y = 0; y < fluidMap[0].length; y++) {
				g.pushTransform();
				g.translate(x * CellSize, y * CellSize);

				float alphaVal = Functions.map(fluidMap[x][y], minCellVolume, maxCellVolume, 0, 180);
				g.setColor(new Color(0, 0, 255, alphaVal));
				g.fillRect(0, 0, CellSize, CellSize);

				g.popTransform();
			}
		}
	}

	public void put(int x, int y, int amount) {
		fluidMap[x][y] = amount;
		constrain(x, y);
	}

	public int get(int x, int y) {
		if (x >= 0 && x < fluidMap.length && y >= 0 && y < fluidMap[0].length) {
			// if its inside the tilemap array
			return fluidMap[x][y];
		} else
			return 0;
	}

	private void constrain(int x, int y) {
		if (fluidMap[x][y] >= maxCellVolume) {
			fluidMap[x][y] = maxCellVolume;
		} else if (fluidMap[x][y] <= minCellVolume) {
			fluidMap[x][y] = minCellVolume;
		}
	}

	private void transferWest(int x, int y) {
		fluidMap[x][y] -= transferRate;
		fluidMap[x - 1][y] += transferRate;
	}

	private void transferEast(int x, int y) {
		fluidMap[x][y] -= transferRate;
		fluidMap[x + 1][y] += transferRate;
	}

	private void transferNorth(int x, int y) {
		fluidMap[x][y] -= transferRate;
		fluidMap[x][y - 1] += transferRate;
	}

	private void transferSouth(int x, int y) {
		fluidMap[x][y] = fluidMap[x][y] - transferRate;
		fluidMap[x][y + 1] = fluidMap[x][y + 1] + transferRate;
	}

	private boolean checkWest(int x, int y) {
		if (Tileset.canFlow(tileMap.get_Tile(x - 1, y))) {
			if (fluidMap[x - 1][y] <= maxCellVolume && fluidMap[x - 1][y] >= minCellVolume) {
				if (fluidMap[x - 1][y] < fluidMap[x][y]) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkEast(int x, int y) {
		if (Tileset.canFlow(tileMap.get_Tile(x + 1, y))) {
			if (fluidMap[x + 1][y] <= maxCellVolume && fluidMap[x + 1][y] >= minCellVolume) {
				if (fluidMap[x + 1][y] < fluidMap[x][y]) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkNorth(int x, int y) {
		if (Tileset.canFlow(tileMap.get_Tile(x, y - 1))) {
			if (fluidMap[x][y - 1] <= maxCellVolume && fluidMap[x][y - 1] >= minCellVolume) {
				if (fluidMap[x][y - 1] < fluidMap[x][y]) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkSouth(int x, int y) {
		if (Tileset.canFlow(tileMap.get_Tile(x, y + 1))) {
			if (fluidMap[x][y + 1] <= maxCellVolume && fluidMap[x][y + 1] >= minCellVolume) {
				if (fluidMap[x][y + 1] < fluidMap[x][y]) {
					return true;
				}
			}
		}
		return false;
	}
}
