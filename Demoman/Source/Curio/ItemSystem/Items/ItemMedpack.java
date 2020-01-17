package Curio.ItemSystem.Items;

import org.newdawn.slick.Image;

import Curio.ItemSystem.Item;
import Curio.SessionManagers.PlantManager;
import Curio.SessionManagers.WorldManager;
import Curio.SessionManagers.BombManager.BombManager;
import Curio.SessionManagers.GameObjectManager.WorldObjectManager;
import Default.Constants;
import Default.Player;

public class ItemMedpack implements Item {
	private int value = 35;
	private Image image = Constants.medpack;
	private String name = "Medpack";
	private String description = "Heals players up to " + value + ".";
	private int category = 2;

	@Override
	public int getItemCategory() {
		// TODO Auto-generated method stub
		return category;
	}

	public Image getImage() {
		return image;
	}

	@Override
	public void apply(WorldManager worldManager,WorldObjectManager gameObjectManager, BombManager bombManager, PlantManager plantManager, Player player) {
		player.addHealth(value);
	}

	@Override
	public boolean condition(WorldManager worldManager,WorldObjectManager gameObjectManager, BombManager bombManager, PlantManager plantManager, Player player){
		if (player.getCurrentHealth() < player.getMaxHealth()) {
			return true;
		} else {
			return false;
		}
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

	private int health = 100;

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}
}
