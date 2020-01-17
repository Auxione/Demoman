package Curio.ItemSystem.Items;

import org.newdawn.slick.Image;

import Curio.ItemSystem.Item;
import Curio.SessionManagers.PlantManager;
import Curio.SessionManagers.WorldManager;
import Curio.SessionManagers.BombManager.BombManager;
import Curio.SessionManagers.GameObjectManager.WorldObjectManager;
import Default.Constants;
import Default.Player;

public class ItemSausageSeed implements Item {
	private int health = 100;
	private Image image = Constants.SausageSeed;

	private String name = "SausageSeed";
	private String description = "Who would think sausage can grew up on farmland?";
	private int category = 4;

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
		plantManager.plant(player, 2);
	}

	@Override
	public boolean condition(WorldManager worldManager,WorldObjectManager gameObjectManager, BombManager bombManager, PlantManager plantManager, Player player) {
		return plantManager.canPlant(player, 2);
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

}
