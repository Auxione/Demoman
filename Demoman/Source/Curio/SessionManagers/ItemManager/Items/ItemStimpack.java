package Curio.SessionManagers.ItemManager.Items;

import org.newdawn.slick.Image;

import Curio.SessionManagers.BombManager.BombManager;
import Curio.SessionManagers.ItemManager.Item;
import Curio.SessionManagers.PlantManager.PlantManager;
import Curio.SessionManagers.WorldManager.WorldManager;
import Curio.SessionManagers.WorldObjectManager.WorldObjectManager;
import Default.Constants;
import Default.Player;

public class ItemStimpack implements Item {
	public int value = 10;
	private Image image = Constants.stimpack;

	private String name = "Stimpack";
	private String description = "Heals for " + value + " HP.";
	private int category = 2;
	private int health = 100;
	
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
		return image;
	}

	@Override
	public void apply(WorldManager worldManager,WorldObjectManager gameObjectManager, BombManager bombManager, PlantManager plantManager, Player player) {
		player.addHealth(value);

	}

	@Override
	public boolean condition(WorldManager worldManager,WorldObjectManager gameObjectManager, BombManager bombManager, PlantManager plantManager, Player player) {
		if (player.currentHealth < player.maxHealth) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

}
