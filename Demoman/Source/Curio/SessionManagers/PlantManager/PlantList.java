package Curio.SessionManagers.PlantManager;

import java.util.HashMap;

import Curio.SessionManagers.PlantManager.Plants.PlantBerryBush;
import Curio.SessionManagers.PlantManager.Plants.PlantSausagePlant;

public class PlantList {
	public static HashMap<Integer, Plant> list = new HashMap<Integer, Plant>();

	public PlantList() {
		list.put(1, new PlantBerryBush());
		list.put(2, new PlantSausagePlant());
	}
}
