package Curio.SessionManagers.LogicManager;

import Curio.Console;
import Curio.CellularMap.CellularMap;
import Curio.SessionManagers.WorldManager.TileMap;
import Curio.Utilities.CellCoordinate;

public class LogicMap extends CellularMap {
	private Console console;

	public LogicMap(TileMap tileMap) {
		super(tileMap.getXAxisMaxCell(), tileMap.getYAxisMaxCell(), 2);
		// coordinate system x,y
		// logicMap[][][0] is one tick.0=off , 1=on;
		// logicMap[][][1] is switch.0=off , 1=on;
		clearCells();
	}

	public LogicMap setConsole(Console console) {
		this.console = console;
		this.console.Add(0, "Logic: created");
		return this;
	}

	public void clearTickChannels() {
		for (int x = 0; x < super.getXAxisMaxCell(); x++) {
			for (int y = 0; y < super.getYAxisMaxCell(); y++) {
				super.setCell(x, y, 0, 0);
			}
		}
	}

	public void sendTick(CellCoordinate cellCoordinate, boolean state) {
		if (state == true) {
			super.setCell(cellCoordinate, 0, 1);
		}
	}

	public void setState(CellCoordinate cellCoordinate, boolean state) {
		if (state == true) {
			super.setCell(cellCoordinate, 1, 1);
		}

		else if (state == false) {
			super.setCell(cellCoordinate, 1, 0);
		}
	}

	public boolean getState(CellCoordinate cellCoordinate) {
		if (super.getCell(cellCoordinate, 1) == 1) {
			return true;
		} 
		
		else if (super.getCell(cellCoordinate, 0) == 1) {

			return true;
		}

		else {
			return false;
		}
	}
}
