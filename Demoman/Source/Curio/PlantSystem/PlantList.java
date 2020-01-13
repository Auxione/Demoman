package Curio.PlantSystem;

import java.util.HashMap;

import Curio.PlantSystem.Plants.BerryBush;
import Curio.PlantSystem.Plants.SausagePlant;

public class PlantList {
	public static HashMap<Integer, Plant> list = new HashMap<Integer, Plant>();

	public PlantList() {
		list.put(1, new BerryBush());
		list.put(2, new SausagePlant());
	}
}
