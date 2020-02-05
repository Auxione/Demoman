package Curio.SessionManagers.ItemManager.Items;

import org.newdawn.slick.Image;

import Curio.SessionManagers.BombManager.BombManager;
import Curio.SessionManagers.ItemManager.Item;
import Curio.SessionManagers.PlantManager.PlantManager;
import Curio.SessionManagers.WorldManager.WorldManager;
import Curio.SessionManagers.WorldObjectManager.WorldObjectManager;
import Default.Constants;
import Default.Player;

public class ItemPizza implements Item {
	public int value = 60;
	private Image image = Constants.pizza;
	private String name = "Pizza";
	private String description = "Pizza. Pineapple free.";
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
	public void apply(WorldManager worldManager,WorldObjectManager gameObjectManager, BombManager bombManager, PlantManager plantManager, Player player) {
		player.addFood(value);

	}

	@Override
	public boolean condition(WorldManager worldManager,WorldObjectManager gameObjectManager, BombManager bombManager, PlantManager plantManager, Player player) {
		if (player.currentFood < player.maxFood) {
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