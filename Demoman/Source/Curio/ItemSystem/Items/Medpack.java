package Curio.ItemSystem.Items;

import org.newdawn.slick.Image;

import Curio.TileMap;
import Curio.BombManager.BombManager;
import Curio.GameObject.GameObjectManager;
import Curio.ItemSystem.Item;
import Curio.PlantSystem.PlantMap;
import Default.Constants;
import Default.Player;

public class Medpack implements Item {
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
	public void apply(Player dp, TileMap level, BombManager bm, PlantMap plantMap, GameObjectManager gOManager) {
		dp.addHealth(value);
	}

	@Override
	public boolean condition(Player dp, TileMap level, BombManager bm, PlantMap plantMap, GameObjectManager gOManager) {
		if (dp.getCurrentHealth() < dp.getMaxHealth()) {
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
