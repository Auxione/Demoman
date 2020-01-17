package Curio.ItemSystem.Items;

import org.newdawn.slick.Image;

import Curio.ItemSystem.Item;
import Curio.SessionManagers.PlantManager;
import Curio.SessionManagers.WorldManager;
import Curio.SessionManagers.BombManager.BombManager;
import Curio.SessionManagers.GameObjectManager.WorldObjectManager;
import Default.Constants;
import Default.Player;

public class ItemBerries implements Item {
	private Image img = Constants.berries;

	private int foodValue = 5;
	private int healthValue = 5;

	private String name = "Berry";
	private String description = "Fresh berries.";
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
		return img;
	}

	@Override
	public void apply(WorldManager worldManager,WorldObjectManager gameObjectManager, BombManager bombManager, PlantManager plantManager, Player player) {
		player.addFood(foodValue);
		player.addHealth(healthValue);
	}

	@Override
	public boolean condition(WorldManager worldManager,WorldObjectManager gameObjectManager, BombManager bombManager, PlantManager plantManager, Player player) {
		if (player.getCurrentFood() < player.getMaxFood()) {
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
