package Curio.ItemSystem.Items;

import org.newdawn.slick.Image;

import Curio.ItemSystem.Item;
import Curio.SessionManagers.PlantManager;
import Curio.SessionManagers.WorldManager;
import Curio.SessionManagers.BombManager.BombManager;
import Curio.SessionManagers.GameObjectManager.WorldObjectManager;
import Curio.Utilities.Math.Transform;
import Default.Constants;
import Default.Player;

public class ItemDefaultBomb implements Item {
	public int value = 35;
	private Image image = Constants.blueBombNormal;
	Transform tr;

	private String name = "Bomb";
	private String description = "Deals " + value + " damage.";
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
	public void apply(WorldManager worldManager,WorldObjectManager gameObjectManager, BombManager bombManager, PlantManager plantManager, Player player) {
		bombManager.create(player.cellCoordinate, 1, 100, value);

	}

	@Override
	public boolean condition(WorldManager worldManager,WorldObjectManager gameObjectManager, BombManager bombManager, PlantManager plantManager, Player player) {
		// TODO Auto-generated method stub
		return bombManager.canPlace(player.cellCoordinate);
	}

	private int health = 100;

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

}
