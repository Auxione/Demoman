package Curio.Tilemap.Bomb;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Graphics;

import Curio.Tilemap.FireManager;
import Curio.Tilemap.TileMap;
import Curio.Utilities.Math.Transform;
import Default.Player;

public class BombManager {
	private ArrayList<Bomb> bombList = new ArrayList<Bomb>();

	private TileMap level;
	private FireManager fireManager;

	public BombManager(FireManager _fireManager, TileMap _level) {
		level = _level;
		fireManager = _fireManager;
	}

	public void update(Player dp) {
		Iterator<Bomb> b = bombList.iterator();

		while (b.hasNext()) {
			Bomb bomb = b.next(); // must be called before you can call f.remove()
			bomb.update();
			if (bomb.Exploded == true) {
				bomb.Effect(level,dp);;
				b.remove();
			}
		}
	}

	public void render(Graphics g) {
		for (Bomb bomb : bombList) {
			bomb.render(g);
		}
	}

	public boolean canPlace(Transform _transform) {
		if (bombList.isEmpty() == false) {
			for (Bomb b : bombList) {
				if (!b.transform.equals(_transform)) {
					return true;
				}
			}
			return false;
		} else {
			return true;
		}
	}

	public void create(Transform _transform, int _type, int _time) {
		Transform bombpos = new Transform(_transform.get_x(), _transform.get_y());
		if (_type == 1) {
			bombList.add(new Default(level, bombpos, _time));
		} else if (_type == 2) {
			bombList.add(new Napalm(fireManager, level, bombpos, _time));
		}

	}
}
