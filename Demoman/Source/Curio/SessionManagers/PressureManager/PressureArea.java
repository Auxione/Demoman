package Curio.SessionManagers.PressureManager;

import java.util.ArrayList;

import Curio.Utilities.CellCoordinate;

public class PressureArea {
	private ArrayList<CellCoordinate> areaCellCoordinate;
	private PressureMap pressureMap;

	public PressureArea(PressureMap pressureMap) {
		this.areaCellCoordinate = new ArrayList<CellCoordinate>();
		this.pressureMap = pressureMap;
	}

	public void setPressure(int newPressure) {
		for (CellCoordinate CC : areaCellCoordinate) {
			pressureMap.setCell(CC, 0, newPressure);
		}
	}

	public void extend(CellCoordinate newCell) {
		for (CellCoordinate CC : areaCellCoordinate) {
			if (CC.equals(newCell) == false) {
				areaCellCoordinate.add(newCell);
				break;
			}
		}
	}

	public boolean remove(CellCoordinate newCell) {
		if (areaCellCoordinate.contains(newCell) == true) {
			areaCellCoordinate.remove(newCell);
			return true;
		}
		return false;
	}
}
