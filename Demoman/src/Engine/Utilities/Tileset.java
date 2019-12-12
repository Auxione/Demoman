package Engine.Utilities;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Image;

public class Tileset {
	ArrayList<Integer> IDarray;

	private HashMap<Integer, String> Name;
	private HashMap<Integer, Boolean> Breakable;
	private HashMap<Integer, Boolean> Moveable;

	private HashMap<Integer, Boolean> Flammable;
	private HashMap<Integer, Integer> BurnTime;

	private HashMap<Integer, Image> Texture;

	public Tileset() {
		IDarray = new ArrayList<Integer>();
		Name = new HashMap<Integer, String>();
		Breakable = new HashMap<Integer, Boolean>();
		Flammable = new HashMap<Integer, Boolean>();
		BurnTime = new HashMap<Integer, Integer>();
		Moveable = new HashMap<Integer, Boolean>();
		Texture = new HashMap<Integer, Image>();
	}

	public void createTile(int id, String name, boolean breakable, boolean flammable, int burntime, boolean moveable,
			Image texture) {
		IDarray.add(id);
		Name.put(id, name);
		Breakable.put(id, breakable);
		Flammable.put(id, flammable);
		BurnTime.put(id, burntime);
		Moveable.put(id, moveable);
		Texture.put(id, texture);
	}

	public String getname(int id) {
			return Name.get(id);
	}

	public boolean getBreakable(int id) {
		return Breakable.get(id);
	}

	public boolean getMoveable(int id) {
		return Moveable.get(id);
	}

	public int getBurnTime(int id) {
		return BurnTime.get(id);
	}

	public Boolean getFlammable(int id) {
		return Flammable.get(id);
	}

	public Image getTexture(int id) {
		return Texture.get(id);
	}

	public boolean exists(int id) {
		boolean out = false;
		for (Integer i : IDarray) {
			if (i == id) {
				out = true;
			}
		}
		return out;
	}
}