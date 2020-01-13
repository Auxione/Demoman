package Default;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Console;
import Curio.Network.PlayerPositionPackage;
import Curio.Physics.DynamicObject;
import Curio.Tilemap.TileMap;
import Curio.Utilities.Math.Transform;

public class Player extends DynamicObject {

	public int bombType = 1;
	public int bombTimer = 1500;

	private int maxHealth = 100;
	private int currentHealth = maxHealth;
	public int Team = 1;// spectator, dead = 0 //blue = 1 // green = 2

	private int currentFood = 0;
	private int maxFood = 100;

	public int psize = 10;

	public boolean alive;
	private Console console;

	public Player(TileMap level, Console console) {
		super(level);
		super.setSize(psize);
		this.console = console;
		// TODO Auto-generated constructor stub
		console.Add(0, "Player: initialized");
	}

	public Player(TileMap level) {
		super(level);
		super.setSize(psize);
		this.console = null;
		// TODO Auto-generated constructor stub
	}

	public void loop() {
		statsUpdate();
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(super.transform.position.x - psize, super.transform.position.y - psize, psize * 2, psize * 2);
	}

	private void statsUpdate() {
		if (alive == true) {
			if (currentHealth < 0) {
				this.currentHealth = 0;
				alive = false;
				if (console != null) {
					console.Add(0, "Player at: " + super.CellPosition.getCellX() + "-" + super.CellPosition.getCellY()
							+ " is dead.");
				}
			} else if (currentHealth >= maxHealth) {
				this.currentHealth = maxHealth;
			}

			if (currentFood < 0) {
				this.currentFood = 0;
			} else if (currentFood >= maxFood) {
				this.currentFood = maxFood;
			}
		}
	}

	public void spawn(float x, float y) {
		super.setPosition(x, y);
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

	public void applyDamage(int x, int y, int val) {
		if (CellPosition.getCellX() == x) {
			if (CellPosition.getCellY() == y) {
				if (currentHealth >= 0 && currentHealth <= maxHealth) {
					this.currentHealth -= val;
				}
			}
		}
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void ServerPackage(PlayerPositionPackage pack) {
		Transform position = pack.position;
		super.setPosition(position);
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

}
