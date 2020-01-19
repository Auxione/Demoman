package Curio.SessionManagers.FireManager;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

import Curio.Functions;
import Curio.SessionManagers.WorldManager.TileMap;
import Curio.Utilities.CellCoordinate;
import Default.Constants;
import Default.Player;

public class Fire {
	private TileMap tilemap;
	public CellCoordinate cellPosition;
	private Random r = new Random();
	private Animation FireAnimation;

	private int spreadChance = 50;
	private float burnTime = 0;

	public int burnDamage = 10;
	public int burnTileDamage = 1000;

	public boolean burnt;

	public Fire(TileMap tilemap, int x, int y) {
		this.tilemap = tilemap;
		this.cellPosition = new CellCoordinate(x, y);

		//this.FireAnimation = new Animation(Constants.FireSprite, 32, 32, 7, timer);
		//this.FireAnimation.Play();
		this.burnt = false;
	}

	private int timer = 200;

	private void newBurn(Player player) {
		if (burnTime < Functions.millis()) {
			if (tilemap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY(), burnTileDamage) == false) {
				player.addHealth(burnDamage);
				burnTime = Functions.millis() + timer;
			} else if (tilemap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY(), burnTileDamage) == true) {
				burnt = true;
			}
		}
	}

	public void update(Player dp) {
		newBurn(dp);
	}

	public void render(Graphics g) {
		//FireAnimation.render(g, cellPosition);
	}

	public boolean spreadChance() {
		if (r.nextInt(100) > spreadChance) {
			return true;
		} else {
			return false;
		}
	}
}
