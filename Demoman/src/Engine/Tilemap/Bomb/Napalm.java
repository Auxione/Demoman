package Engine.Tilemap.Bomb;

import org.newdawn.slick.Graphics;

import Default.Constants;
import Engine.Tilemap.FireManager;
import Engine.Tilemap.Tilemap;
import Engine.Utilities.Transform;

public class Napalm extends Bomb {
	public Transform transform;
	private Tilemap level;
	private FireManager fireManager;

	private int size = 1;
	private int breakID = 0;
	public boolean Exploded = false;

	public Napalm(FireManager _fireManager, Tilemap _level, Transform _transform, int _time) {
		super(_time, Constants.blueBombNapalm, _transform);

		transform = _transform;
		level = _level;
		fireManager = _fireManager;
	}

	public void effect() {
		for (int x = 0; x <= size; x++) {
			// get tile id
			int xval = transform.get_x() + x;
			int yval = transform.get_y();
			int tileid = level.get_Tile(xval, yval);

			if (Constants.Tset.getBreakable(tileid) == true) {
				level.set_Tile(xval, yval, breakID);
			} else if (Constants.Tset.getBreakable(tileid) == true && Constants.Tset.getMoveable(tileid) == false) {
				break;
			} else {
				fireManager.create(xval, yval);
			}
		}
		for (int x = 0; x <= size; x++) {
			// get tile id
			int xval = transform.get_x() - x;
			int yval = transform.get_y();
			int tileid = level.get_Tile(xval, yval);
			if (Constants.Tset.getBreakable(tileid) == true) {
				level.set_Tile(xval, yval, breakID);
			} else if (Constants.Tset.getBreakable(tileid) == true && Constants.Tset.getMoveable(tileid) == false) {
				break;
			} else {
				fireManager.create(xval, yval);
			}
		}

		for (int y = 0; y <= size; y++) {
			// get tile id
			int xval = transform.get_x();
			int yval = transform.get_y() + y;
			int tileid = level.get_Tile(xval, yval);
			if (Constants.Tset.getBreakable(tileid) == true) {
				level.set_Tile(xval, yval, breakID);
			} else if (Constants.Tset.getBreakable(tileid) == true && Constants.Tset.getMoveable(tileid) == false) {
				break;
			} else {
				fireManager.create(xval, yval);
			}
		}
		for (int y = 0; y <= size; y++) {
			// get tile id
			int xval = transform.get_x();
			int yval = transform.get_y() - y;
			int tileid = level.get_Tile(xval, yval);
			if (Constants.Tset.getBreakable(tileid) == true) {
				level.set_Tile(xval, yval, breakID);
			} else if (Constants.Tset.getBreakable(tileid) == true && Constants.Tset.getMoveable(tileid) == false) {
				break;
			} else {
				fireManager.create(xval, yval);
			}
		}
	}

	@Override
	public void effectRender(Graphics g) {
	
	}
}
