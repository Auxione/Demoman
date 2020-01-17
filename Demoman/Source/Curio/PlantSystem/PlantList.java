package Curio.PlantSystem;

import java.util.HashMap;

import Curio.PlantSystem.Plants.PlantBerryBush;
import Curio.PlantSystem.Plants.PlantSausagePlant;

public class PlantList {
	public static HashMap<Integer, Plant> list = new HashMap<Integer, Plant>();

	public PlantList() {
		list.put(1, new PlantBerryBush());
		list.put(2, new PlantSausagePlant());
	}
}
