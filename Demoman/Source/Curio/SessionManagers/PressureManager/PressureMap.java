package Curio.SessionManagers.PressureManager;

import java.util.ArrayList;

import Curio.CellularMap.CellularMap;
import Curio.SessionManagers.WorldManager.TileMap;

public class PressureMap extends CellularMap {
	private ArrayList<PressureArea> PressureAreas;
	
	public int maxCellVolume = 32;
	public int minCellVolume = 0;

	public PressureMap(TileMap tileMap) {
		super(tileMap.getXAxisMaxCell(), tileMap.getYAxisMaxCell(), 2);
		this.PressureAreas = new ArrayList<PressureArea>();
		clearCells();
	}
	public void createNewAreas() {
		
		
	}
	
}
