package Curio.Tilemap.Bomb;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Curio.Functions;
import Curio.ItemMap.ItemMap;
import Curio.PlantMap.PlantMap;
import Curio.Tilemap.FluidMap;
import Curio.Tilemap.TileMap;
import Curio.Utilities.Animation;
import Curio.Utilities.CellCoordinate;
import Default.Constants;
import Default.Player;

public abstract class Bomb {
	public CellCoordinate cellPosition;
	public Animation ExplosionA;

	private Image bombImage = null;

	private int timer;
	private int ExplodeTime;

	public boolean Exploded = false;
	private int state;

	public Bomb(int time, Image bombImage, CellCoordinate cellPosition) {
		this.bombImage = bombImage;
		this.timer = time;
		this.cellPosition = cellPosition;

		ExplosionA = new Animation(Constants.ExplosionSprite, 32, 32, 6, 200);
		// calculate the time when bomb explodes
		ExplodeTime = timer + Functions.millis();
		state = 1;
	}

	public void update() {
		switch (state) {
		case 1:
			int counter = ExplodeTime - Functions.millis();
			if (counter < 0) {
				ExplosionA.Play();
				state = 2;
			}
			break;
		case 2:
			if (ExplosionA.Finished == true) {
				Exploded = true;
			}
			break;
		}

	}

	public void render(Graphics g) {
		switch (state) {
		case 1:
			g.drawImage(bombImage,cellPosition.getWorldX(), cellPosition.getWorldY());
			break;
		case 2:
			ExplosionA.render(g, cellPosition);
			break;
		}
	}

	public abstract void Effect(Player player, TileMap tileMap, ItemMap itemMap, PlantMap plantMap, FluidMap fluidMap);

	public abstract void effectRender(Graphics g);
}
