package Curio.Tilemap.Bomb;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Curio.Functions;
import Curio.Tilemap.TileMap;
import Curio.Utilities.Animation;
import Curio.Utilities.Math.Transform;
import Default.Constants;
import Default.Player;

public abstract class Bomb {
	public Transform transform;
	public Animation ExplosionA;
	
	private Image bombImage = null;

	private int timer;
	private int ExplodeTime;

	public boolean Exploded = false;
	private int state;
	
	public Bomb(int _time,Image _bombImage,Transform _transform) {
		bombImage = _bombImage;
		timer = _time;
		transform =_transform ;
		
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
			g.drawImage(bombImage, transform.get_x() * Constants.CellSize, transform.get_y() * Constants.CellSize);
			break;
		case 2:
			ExplosionA.render(g, transform);
			break;
		}
	}
	public abstract void Effect(TileMap _level,Player dp);
	public abstract void effectRender(Graphics g);
}
