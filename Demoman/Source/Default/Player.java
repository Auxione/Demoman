package Default;


import Curio.GameObject.GameObject;
import Curio.Utilities.Math.Transform;

public class Player extends GameObject{
	private int maxHealth = 100;
	private int currentHealth = maxHealth;

	private int currentFood = 0;
	private int maxFood = 100;

	public int psize = 10;
	public boolean alive;
	public int bombType = 1;
	public int bombTimer = 1500;
	public int Team = 1;// spectator, dead = 0 //blue = 1 // green = 2

	public Player() {
	}

	public void update() {
		if (alive == true) {
			if (currentHealth < 0) {
				this.currentHealth = 0;
				alive = false;
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

	public void spawn(int x, int y) {
		super.setTransform(new Transform((x - 0.5f) * Constants.CellSize, (y - 0.5f) * Constants.CellSize, 0));
		
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
		if (super.cellCoordinate.getCellX() == x) {
			if (super.cellCoordinate.getCellY() == y) {
				if (currentHealth >= 0 && currentHealth <= maxHealth) {
					this.currentHealth -= val;
				}
			}
		}
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
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
