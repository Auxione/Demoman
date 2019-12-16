package Curio.Tilemap;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Image;

import Default.Constants;

public class Tileset {
	private static ArrayList<Integer> IDarray = new ArrayList<Integer>();

	private static HashMap<Integer, String> Name = new HashMap<Integer, String>();
	private static HashMap<Integer, Boolean> canBreak = new HashMap<Integer, Boolean>();
	private static HashMap<Integer, Boolean> canMove = new HashMap<Integer, Boolean>();
	private static HashMap<Integer, Float> maxHP = new HashMap<Integer, Float>();
	private static HashMap<Integer, Boolean> canBurn = new HashMap<Integer, Boolean>();
	private static HashMap<Integer, Image> Texture = new HashMap<Integer, Image>();

	public static void InitTileset() {
		createTile(0, "Empty", true, false, 0, true, Constants.empty);

		createTile(1, "grass", true, true, 5000, true, Constants.grass);

		createTile(2, "wood", true, true, 2000, true, Constants.wood);

		createTile(3, "rock", true, true, 3000, false, Constants.rock);
		createTile(4, "Brickwall", false, true, 4000, false, Constants.brick);
		createTile(5, "metalframe", false, false, 5000, false, Constants.metalframe);
	}
	
	private static void createTile(int id, // id of the tile
			String name, // name of the tile
			boolean moveable, // player and other items collide with this tile
			boolean unbreakable, // take damage
			float tileMaxHP, // maximum damage can block take before gets destroyed
			boolean flammable, // maximum damage can block take before gets destroyed
			Image texture // block texture
	) {

		IDarray.add(id);
		Name.put(id, name);
		canBreak.put(id, unbreakable);
		maxHP.put(id, tileMaxHP);
		canMove.put(id, moveable);
		canBurn.put(id, flammable);
		Texture.put(id, texture);
	}


	public static String getname(int id) {
		return Name.get(id);
	}
	public static float getHP(int id) {
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
}