package Engine.Tilemap;

import java.util.Random;

import org.newdawn.slick.Graphics;

import Default.Constants;
import Engine.Functions;
import Engine.Utilities.Animation;
import Engine.Utilities.Transform;

public class Fire {
	private Tilemap level;
	public Transform transform;
	private Random r = new Random();
	private Animation FireAnimation;

	private int spreadChance = 75;
	private float goal = 0;
	final private int goalOffset = 000;
	private int burntID = 0;

	public boolean burnt;
	
	
	public Fire(Tilemap _level, int x, int y) {
		level = _level;
		transform = new Transform(x, y);
		
		
		int tileid = level.get_Tile(transform.get_x(), transform.get_y());
		burntID = tileid * 100;
		
		int burntime = Constants.Tset.getBurnTime(tileid);
		goal = Functions.millis() + burntime;

		FireAnimation = new Animation(Constants.FireSprite, 32, 32, 24, burntime);
		FireAnimation.Play();
		burnt = false;
	}

	

	public void update() {
		if (Functions.millis() > goal - goalOffset) {
			level.set_Tile(transform.get_x(), transform.get_y(), burntID);
			burnt = true;
		}
	}

	public void render(Graphics g) {
		FireAnimation.render(g, transform);
	}

	public boolean spreadChance() {
		if (r.nextInt(100) > spreadChance) {
			return true;
		} else {
			return false;
		}
	}
}
