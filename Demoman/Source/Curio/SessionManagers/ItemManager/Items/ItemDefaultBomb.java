package Curio.SessionManagers.ItemManager.Items;

import org.newdawn.slick.Image;

import Curio.SessionManagers.BombManager.BombManager;
import Curio.SessionManagers.ItemManager.Item;
import Curio.SessionManagers.PlantManager.PlantManager;
import Curio.SessionManagers.WorldManager.WorldManager;
import Curio.SessionManagers.WorldObjectManager.WorldObjectManager;
import Default.Constants;
import Default.Player;

public class ItemDefaultBomb implements Item {
	public int damageValue = 35;
	public int timerValue = 5;
	
	private Image image = Constants.blueBombNormal;

	private int health = 100;
	private String name = "Bomb";
	private String description = "Deals " + damageValue + " damage.";
	private int category = 3;

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

	public Image getImage() {
		// TODO Auto-generated method stub
		return image;
	}

	@Override
	public void apply(WorldManager worldManager, WorldObjectManager gameObjectManager, BombManager bombManager,
			PlantManager plantManager, Player player) {
		bombManager.create(player.transform, 1, timerValue, damageValue);

	}

	@Override
	public boolean condition(WorldManager worldManager, WorldObjectManager gameObjectManager, BombManager bombManager,
			PlantManager plantManager, Player player) {
		// TODO Auto-generated method stub
		return bombManager.canPlace(player.transform);
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

}
