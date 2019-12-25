package Curio;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Image;
import Default.Constants;

public class Tileset {
	private static ArrayList<Integer> IDarray = new ArrayList<Integer>();

	private static HashMap<Integer, String> Name = new HashMap<Integer, String>();
	private static HashMap<Integer, Boolean> canBreak = new HashMap<Integer, Boolean>();
	private static HashMap<Integer, Boolean> canMove = new HashMap<Integer, Boolean>();
	private static HashMap<Integer, Integer> maxHP = new HashMap<Integer, Integer>();
	private static HashMap<Integer, Boolean> canBurn = new HashMap<Integer, Boolean>();
	private static HashMap<Integer, Boolean> canFlow = new HashMap<Integer, Boolean>();
	private static HashMap<Integer, Image> Texture = new HashMap<Integer, Image>();
	private static HashMap<Integer, Boolean> canPlant = new HashMap<Integer, Boolean>();

	public static void InitTileset() {
		createTile(0, "Empty", true, false, 0, false, Constants.empty, false, true);

		createTile(1, "grass", true, true, 5000, true, Constants.grass, true, true);

		createTile(2, "wood", true, true, 2000, true, Constants.wood, false, false);

		createTile(3, "rock", true, true, 3000, false, Constants.rock, false, false);
		createTile(4, "Brickwall", false, false, 4000, false, Constants.brick, false, false);
		createTile(5, "metalframe", false, true, 5000, false, Constants.metalframe, false, false);

		createTile(6, "farmLand", true, true, 5000, true, Constants.Farmland, true, true);
	}

	private static void createTile(int id, // id of the tile
			String name, // name of the tile
			boolean moveable, // player and other items collide with this tile
			boolean unbreakable, // take damage
			int tileMaxHP, // maximum damage can block take before gets destroyed
			boolean flammable, // maximum damage can block take before gets destroyed
			Image texture, // block texture
			boolean canplant, boolean canflow) {

		IDarray.add(id);
		Name.put(id, name);

		canBreak.put(id, unbreakable);
		maxHP.put(id, tileMaxHP);

		canMove.put(id, moveable);

		canBurn.put(id, flammable);
		Texture.put(id, texture);

		canPlant.put(id, canplant);
		canFlow.put(id, canflow);
	}

	public static String getname(int id) {
		return Name.get(id);
	}

	public static int getHP(int id) {
		return maxHP.get(id);
	}

	public static boolean canBreak(int id) {
		return canBreak.get(id);
	}

	public static boolean canMove(int id) {
		return canMove.get(id);
	}

	public static boolean canBurn(int id) {
		return canBurn.get(id);
	}

	public static Image getTexture(int id) {
		return Texture.get(id);
	}

	public static boolean canPlant(int id) {
		return canPlant.get(id);
	}
	
	public static boolean canFlow(int id) {
		return canFlow.get(id);
	}
}