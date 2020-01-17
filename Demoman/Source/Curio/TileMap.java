package Curio;

import Curio.CellularMap.CellularMap;
import static Curio.Functions.*;

public class TileMap extends CellularMap {
	// 0 north
	// 1 east
	// 2 south
	// 3 west

	// create tile array that holds informations
	// coords x, coord y, and tile value
	private Console console;

	public TileMap(int xAxisSize, int yAxisSize) {
		super(xAxisSize, yAxisSize, 2);
	}

	public void create_BlankLevel() {
		createBlankTileMap();
		generateTileHPs();
		if (super.console != null) {
			console.Add(0, "Blank Map Created");
		}
	}

	public void create_RandomLevel() {
		createRandomTileMap();
		generateTileHPs();
		if (super.console != null) {
			console.Add(0, "Random Map Created");
		}
	}

	private void generateTileHPs() {
		for (int x = 0; x < super.getXAxisMaxCell(); x++) {
			for (int y = 0; y < super.getYAxisMaxCell(); y++) {
				super.cellularMap[x][y][1] = TileList.getTile(super.cellularMap[x][y][0]).getTileMaxHP();
			}
		}
	}

	public boolean applyDamage(int xPosition, int yPosition, int damage) {
		int tileHP = super.getTile(xPosition, yPosition, 1);
		if (tileHP > 0) {
			if (tileHP < TileList.getTile(super.cellularMap[xPosition][yPosition][0]).getTileMaxHP()) {
				super.setTile(xPosition, yPosition, 1, tileHP - damage);
				return true;
			}
		}
		return false;
	}

	private void createBlankTileMap() {
		for (int x = 0; x < super.getXAxisMaxCell(); x++) {
			for (int y = 0; y < super.getYAxisMaxCell(); y++) {
				if (x == super.getXAxisMaxCell() - 1 || x == 0 || y == super.getYAxisMaxCell() - 1 || y == 0) {
					super.setTile(x, y, 0, 5);
				} else {
					super.setTile(x, y, 0, 1);
				}
			}
		}
	}

	private void createRandomTileMap() {
		int[] idArray = { 1, 2, 2, 2, 2, 2, 4, 5 };
		for (int x = 0; x < super.getXAxisMaxCell(); x++) {
			for (int y = 0; y < super.getYAxisMaxCell(); y++) {
				if (x == super.getXAxisMaxCell() - 1 || x == 0 || y == super.getYAxisMaxCell() - 1 || y == 0) {
					super.setTile(x, y, 0, 5);
				} else {
					super.setTile(x, y, 0, random(idArray));
				}
			}
		}
	}

}
