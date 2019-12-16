package Curio.Tilemap.Bomb;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Graphics;

import Curio.Tilemap.FireManager;
import Curio.Tilemap.Tilemap;
import Curio.Utilities.Transform;
import Default.DynamicPlayer;

public class BombManager {
	private ArrayList<Bomb> bombList = new ArrayList<Bomb>();

	private Tilemap level;
	private FireManager fireManager;

	public BombManager(FireManager _fireManager, Tilemap _level) {
		level = _level;
		fireManager = _fireManager;
	}

	public void update(DynamicPlayer dp) {
		Iterator<Bomb> b = bombList.iterator();

		while (b.hasNext()) {
			Bomb bomb = b.next(); // must be called before you can call f.remove()
			bomb.update();
			if (bomb.Exploded == true) {
				bomb.tileEffect(level);
				bomb.playerEffect(dp);
				b.remove();
			}
		}
	}

	public void render(Graphics g) {
		for (Bomb bomb : bombList) {
			bomb.render(g);
		}
	}

	public void create(Transform _transform, int _type, int _time) {
		boolean canPlace = false;
		if (bombList.isEmpty() == false) {
			for (Bomb b : bombList) {
				if (!b.transform.equals(_transform)) {
					canPlace = true;
					break;
				}
			}
		} else {
			canPlace = true;
		}
		if (canPlace == true) {
			Transform bombpos = new Transform(_transform.get_x(), _transform.get_y());
			if (_type == 1) {
				bombList.add(new Default(level, bombpos, _time));
			} else if (_type == 2) {
				bombList.add(new Napalm(fireManager, level, bombpos, _time));
			}
		}
	}
}
