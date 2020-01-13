package Curio.ItemSystem.Items;

import org.newdawn.slick.Image;

import Curio.TileMap;
import Curio.BombManager.BombManager;
import Curio.GameObject.GameObjectManager;
import Curio.ItemSystem.Item;
import Curio.PlantSystem.PlantMap;
import Default.Constants;
import Default.Player;

public class PizzaSlice implements Item {
	public int value = 10;
	private Image image = Constants.pizzaSlice;
	private String name = "PizzaSlice";
	private String description = "---";
	private int category = 1;

	@Override
	public int getItemCategory() {
		// TODO Auto-generated method stub
		return category;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return image;
	}

	@Override
	public void apply(Player dp, TileMap level, BombManager bm, PlantMap plantMap, GameObjectManager gOManager) {
		dp.addFood(value);

	}

	@Override
	public boolean condition(Player dp, TileMap level, BombManager bm, PlantMap plantMap, GameObjectManager gOManager) {
		if (dp.getCurrentFood() < dp.getMaxFood()) {
			return true;
		} else {
			return false;
		}
	}

	private int health = 100;

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

}
