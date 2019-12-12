package Default;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Engine.Physics.*;
import Engine.Tilemap.Tilemap;

public class DynamicPlayer extends DynamicObject {
	public int bombSize = 2;
	public int bombTimer = 1500;

	private int maxHealth = 100;
	private int currentHealth = maxHealth;
	public int Team = 1;// spectator, dead = 0 //blue = 1 // green = 2
	private int Speed = 100000;

	private int psize = 10;
	Tilemap level;

	private int Left = 0, Right = 0, Up = 0, Down = 0;

	protected DynamicPlayer(Tilemap _level, float positionX, float positionY) {
		super(_level, positionX, positionY);
		level = _level;
	}

	public void movementUpdate() {
		move((Left + Right) * Speed, (Up + Down) * Speed);
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(super.Position.x-psize/2, super.Position.y-psize/2, psize, psize);
	}

	void MovementDir(char k, int act) {
		switch (k) {
		case 'w':
			Up = -act;
			break;
		case 's':
			Down = act;
			break;
		case 'd':
			Right = act;
			break;
		case 'a':
			Left = -act;
			break;
		}

	}
}
