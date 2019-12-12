package Engine.Tilemap.Bomb;

import org.newdawn.slick.Graphics;

import Default.Constants;
import Engine.Tilemap.Tilemap;
import Engine.Utilities.Transform;

public class Default extends Bomb{
	public Transform transform;
	private Tilemap level;

	private int size;
	private int breakID = 0;
	public boolean Exploded = false;

	public Default(Tilemap _level, Transform _transform, int _time) {
		super( _time,Constants.blueBombNormal,_transform);
		
		transform = _transform;
		level = _level;
	}
	

	public void effect() {
		size = 2;

		for (int x = 0; x <= size; x++) {
			// get tile id
			int xval = Math.round(transform.get_x() + x);
			int yval = Math.round(transform.get_y());
			int tileid = level.get_Tile(xval, yval);
			// check the tile if its breakable
			if (Constants.Tset.getBreakable(tileid) == true) {
				level.set_Tile(xval, yval, breakID);
			} else if (Constants.Tset.getBreakable(tileid) == false && Constants.Tset.getMoveable(tileid) == false) {
				break;
			}
		}

		for (int x = 0; x <= size; x++) {
			// get tile id
			int xval = Math.round(transform.get_x() - x);
			int yval = Math.round(transform.get_y());
			int tileid = level.get_Tile(xval, yval);
			// check the tile if its breakable
			if (Constants.Tset.getBreakable(tileid) == true) {
				level.set_Tile(xval, yval, breakID);
			} else if (Constants.Tset.getBreakable(tileid) == false && Constants.Tset.getMoveable(tileid) == false) {
				break;
			}
		}

		for (int y = 0; y <= size; y++) {
			// get tile id
			int xval = Math.round(transform.get_x());
			int yval = Math.round(transform.get_y() + y);
			int tileid = level.get_Tile(xval, yval);
			// check the tile if its breakable
			if (Constants.Tset.getBreakable(tileid) == true) {
				breakID = tileid * 100;
				level.set_Tile(xval, yval, breakID);
			} else if (Constants.Tset.getBreakable(tileid) == false && Constants.Tset.getMoveable(tileid) == false) {
				break;
			}
		}
		for (int y = 0; y <= size; y++) {
			// get tile id
			int xval = Math.round(transform.get_x());
			int yval = Math.round(transform.get_y() - y);
			int tileid = level.get_Tile(xval, yval);
			// check the tile if its breakable
			if (Constants.Tset.getBreakable(tileid) == true) {
				level.set_Tile(xval, yval, breakID);
			} else if (Constants.Tset.getBreakable(tileid) == false && Constants.Tset.getMoveable(tileid) == false) {
				break;
			}
		}
	}


	@Override
	public void effectRender(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
