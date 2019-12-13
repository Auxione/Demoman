package Default;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Physics.*;
import Curio.Tilemap.Tilemap;

public class DynamicPlayer extends DynamicObject {
	public int bombType = 1;
	public int bombTimer = 1500;

	private int maxHealth = 100;
	private int currentHealth = maxHealth;
	public int Team = 1;// spectator, dead = 0 //blue = 1 // green = 2
	private int Speed = 100000;

	public int psize = 10;
	Tilemap level;

	public boolean Dead;
	
	private int Left = 0, Right = 0, Up = 0, Down = 0;
	
	protected DynamicPlayer(Tilemap _level, float positionX, float positionY) {
		super(_level, positionX, positionY);
		level = _level;
		Dead = false;
	}

	
	public void loop() {
		move((Left + Right) * Speed, (Up + Down) * Speed);
		healthUpdate();
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(super.Position.x - psize , super.Position.y - psize , psize*2, psize*2);
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

	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	private void healthUpdate() {
		if (currentHealth < 0 && Dead == false) {
			this.currentHealth = 0;
			Dead = true;
		} else if (currentHealth > maxHealth && Dead == false) {
			this.currentHealth = maxHealth;
		}
	}

	public void addHealth(int val) {
		if (currentHealth >= 0 && currentHealth <= maxHealth) {
			this.currentHealth += val;
		}
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
}
