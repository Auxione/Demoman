package Curio.SessionManagers.ItemManager.Items;

import org.newdawn.slick.Image;

import Curio.SessionManagers.BombManager.BombManager;
import Curio.SessionManagers.ItemManager.Item;
import Curio.SessionManagers.PlantManager.PlantManager;
import Curio.SessionManagers.WorldManager.WorldManager;
import Curio.SessionManagers.WorldObjectManager.WorldObjectManager;
import Default.Constants;
import Default.Player;

public class ItemNapalmBomb implements Item {
	public int value = 35;
	private Image image = Constants.blueBombNapalm;

	private String name = "NapalmBomb";
	private String description = "Classic bomb with Napalm warhead.";
	private int category = 3;
	private int timerValue = 2;
	private int damageValue = 15;

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

	public void apply(WorldManager worldManager, WorldObjectManager gameObjectManager, BombManager bombManager,
			PlantManager plantManager, Player player) {
		bombManager.create(player.transform, 2, timerValue, damageValue);

	}

	@Override
	public boolean condition(WorldManager worldManager, WorldObjectManager gameObjectManager, BombManager bombManager,
			PlantManager plantManager, Player player) {
		// TODO Auto-generated method stub
		return bombManager.canPlace(player.transform);
	}

	private int health = 100;

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

}
