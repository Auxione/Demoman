package Engine.Tilemap.Bomb;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Graphics;

import Engine.Tilemap.FireManager;
import Engine.Tilemap.Tilemap;
import Engine.Utilities.Transform;

public class BombManager {
	private ArrayList<Bomb> bombList = new ArrayList<Bomb>();

	private Tilemap level;
	private FireManager fireManager;

	public BombManager(FireManager _fireManager, Tilemap _level) {
		level = _level;
		fireManager = _fireManager;
	}

	public void update() {
		Iterator<Bomb> b = bombList.iterator();

		while (b.hasNext()) {
			Bomb bomb = b.next(); // must be called before you can call f.remove()
			bomb.update();
			if (bomb.Exploded == true) {
				bomb.effect();
				b.remove();
			}
		}
	}

	public void render(Graphics g) {
		for (Bomb bomb : bombList) {
			bomb.render(g);
		}
	}

	public void create(Transform _transform, int _team, int _type, int _time) {
		Transform bombpos = new Transform(_transform.get_x(), _transform.get_y());
		if (_type == 1) {
			bombList.add(new Default(level, bombpos, _time));
		} else if (_type == 2) {
			bombList.add(new Napalm(fireManager, level, bombpos, _time));
		}
	}
	
	
}
