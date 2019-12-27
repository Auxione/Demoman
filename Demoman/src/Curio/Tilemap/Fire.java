package Curio.Tilemap;

import java.util.Random;

import org.newdawn.slick.Graphics;

import Curio.Functions;
import Curio.Utilities.Animation;
import Curio.Utilities.CellCoordinate;
import Default.Constants;
import Default.Player;

public class Fire {
	private TileMap level;
	public CellCoordinate cellPosition;
	private Random r = new Random();
	private Animation FireAnimation;

	private int spreadChance = 50;
	private float burnTime = 0;

	public int burnDamage = 10;
	public int burnTileDamage = 1000;

	public boolean burnt;

	public Fire(TileMap _level, int x, int y) {
		level = _level;
		cellPosition = new CellCoordinate(x, y);

		FireAnimation = new Animation(Constants.FireSprite, 32, 32, 7, timer);
		FireAnimation.Play();
		burnt = false;
	}

	private int timer = 200;

	private void newBurn(Player dp) {
		if (burnTime < Functions.millis()) {
			if (level.applyDamage(cellPosition.getCellX(), cellPosition.getCellY(), burnTileDamage) == false) {
				dp.addHealth(burnDamage);
				burnTime = Functions.millis() + timer;
			} else if (level.applyDamage(cellPosition.getCellX(), cellPosition.getCellY(), burnTileDamage) == true) {
				burnt = true;
			}
		}
	}

	public void update(Player dp) {
		newBurn(dp);
	}

	public void render(Graphics g) {
		FireAnimation.render(g, cellPosition);
	}

	public boolean spreadChance() {
		if (r.nextInt(100) > spreadChance) {
			return true;
		} else {
			return false;
		}
	}
}
