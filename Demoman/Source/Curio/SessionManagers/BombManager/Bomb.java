package Curio.SessionManagers.BombManager;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Curio.FluidMap;
import Curio.Functions;
import Curio.TileMap;
import Curio.ItemSystem.ItemMap;
import Curio.PlantSystem.PlantMap;
import Curio.SessionManagers.PlantManager;
import Curio.SessionManagers.WorldManager;
import Curio.SessionManagers.FireManager.FireManager;
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
			g.drawImage(bombImage, cellPosition.getWorldX(), cellPosition.getWorldY());
			break;
		case 2:
			ExplosionA.render(g, cellPosition);
			break;
		}
	}

	public abstract void effectRender(Graphics g);

	protected abstract void Effect(WorldManager worldManager, FireManager fireManager, PlantManager plantManager,
			ItemMap itemMap, Player player);
}
