package Curio.Tilemap;

import java.util.Random;

import org.newdawn.slick.Graphics;

import Curio.Functions;
import Curio.Utilities.Animation;
import Curio.Utilities.Transform;
import Default.Constants;
import Default.DynamicPlayer;

public class Fire {
	private Tilemap level;
	public Transform transform;
	private Random r = new Random();
	private Animation FireAnimation;

	private int spreadChance = 50;
	private float burnTime = 0;
	final private int goalOffset = 000;
	private int burntID = 0;

	public int burnDamage = -10;

	public boolean burnt;

	public Fire(Tilemap _level, int x, int y) {
		level = _level;
		transform = new Transform(x, y);

		int tileid = level.get_Tile(transform.get_x(), transform.get_y());
		burntID = tileid * 100;

		int tileBurnTime = 1;//Tileset.getBurnTime(tileid);
		burnTime = Functions.millis() + tileBurnTime;

		FireAnimation = new Animation(Constants.FireSprite, 32, 32, 7, tileBurnTime);
		FireAnimation.Play();
		burnt = false;
	}

	int applyDamageTimer = 200;
	int damageGoal = 0;

	// deal damage every "applyDamageTimer" milliseconds
	private void applyDamage(DynamicPlayer dp) {
		if (transform.equals(dp.CellPosition) == true) {
			if (Functions.millis() > damageGoal) {
				dp.addHealth(burnDamage);
				damageGoal = Functions.millis() + applyDamageTimer;
			}
		}
	}

	public void update(DynamicPlayer dp) {
		if (Functions.millis() < burnTime - goalOffset) {
			applyDamage(dp);
		} else if (Functions.millis() > burnTime - goalOffset) {
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
