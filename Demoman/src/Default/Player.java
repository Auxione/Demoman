package Default;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.HUD.ConsoleDisplay;
import Curio.Physics.DynamicObject;
import Curio.Tilemap.TileMap;

public class Player extends DynamicObject {

	public int bombType = 1;
	public int bombTimer = 1500;

	private int maxHealth = 100;
	private int currentHealth = maxHealth;
	public int Team = 1;// spectator, dead = 0 //blue = 1 // green = 2

	private int currentFood = 0;
	private int maxFood = 100;

	public int psize = 10;

	public boolean Dead;
	private ConsoleDisplay console;

	public Player(TileMap level, int positionX, int positionY, ConsoleDisplay console) {
		super(level, positionX, positionY);
		super.setSize(psize);
		this.console = console;
		// TODO Auto-generated constructor stub
		String cmd = "Player: initialized and spawned to: " + positionX + "-" + positionY + ".";
		console.Add(0, cmd);
	}

	public Player(TileMap level, int positionX, int positionY) {
		super(level, positionX, positionY);
		super.setSize(psize);
		this.console = null;
		// TODO Auto-generated constructor stub
	}

	public void loop() {
		statsUpdate();
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(super.Position.x - psize, super.Position.y - psize, psize * 2, psize * 2);
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getCurrentFood() {
		return currentFood;
	}

	public int getMaxFood() {
		return maxFood;
	}

	private void statsUpdate() {
		if (currentHealth < 0 && Dead == false) {
			this.currentHealth = 0;
			Dead = true;
			if (console != null) {
				String cmd = "Player at: " + super.CellPosition.get_x() + "-" + super.CellPosition.get_y()
						+ " is dead.";
				console.Add(0, cmd);
			}
		} else if (currentHealth > maxHealth && Dead == false) {
			this.currentHealth = maxHealth;
		}

		if (currentFood < 0 && Dead == false) {
			this.currentFood = 0;
		} else if (currentFood > maxFood && Dead == false) {
			this.currentFood = maxFood;
		}

	}

	public void addFood(int val) {
		if (currentFood >= 0 && currentFood <= maxFood) {
			this.currentFood += val;
		}
	}

	public void addHealth(int val) {
		if (currentHealth >= 0 && currentHealth <= maxHealth) {
			this.currentHealth += val;
		}
	}

	public void applyDamage(int val) {
		if (currentHealth >= 0 && currentHealth <= maxHealth) {
			this.currentHealth -= val;
		}
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
}
