package Default;

import Curio.Functions;
import Curio.Physics.DynamicObject;
import Curio.Utilities.Math.Transform;

public class Player extends DynamicObject {
	public int maxHealth = 100;
	public int currentHealth = maxHealth;
	public float healthRatio = 0.0f;

	public int currentFood = 0;
	public int maxFood = 100;
	public float foodRatio = 0.0f;

	public int size = 10;

	public Player() {
		super.setSize(size);
	}

	public Player setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
		return this;
	}

	public Player setMaxFood(int maxFood) {
		this.maxFood = maxFood;
		return this;
	}
	
	public Player setHealthValue(int currentHealth) {
		this.currentHealth = currentHealth;
		return this;
	}

	public Player setFoodValue(int currentFood) {
		this.currentFood = currentFood;
		return this;
	}

	public void update() {
		this.currentHealth = (int) Functions.clamp(currentHealth, 0, maxHealth);
		this.currentFood = (int) Functions.clamp(currentFood, 0, maxFood);
		this.healthRatio = (float) currentHealth / maxHealth;
		this.foodRatio = (float) currentFood / maxFood;
	}

	public void spawn(int x, int y) {
		super.setTransform(new Transform((x - 0.5f) * Constants.CellSize, (y - 0.5f) * Constants.CellSize, 0));
	}

	public void addFood(int value) {
		this.currentFood += value;
	}

	public void addHealth(int value) {
		this.currentHealth += value;
	}

	public void applyDamage(int value) {
		this.currentHealth -= value;
	}
}
