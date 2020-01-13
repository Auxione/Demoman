package Curio.ItemSystem.Items;

import org.newdawn.slick.Image;

import Curio.TileMap;
import Curio.BombManager.BombManager;
import Curio.GameObject.GameObjectManager;
import Curio.ItemSystem.Item;
import Curio.PlantSystem.PlantMap;
import Default.Constants;
import Default.Player;

public class SausageSeed implements Item {
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
	public void apply(Player p, TileMap level, BombManager bm, PlantMap plantMap, GameObjectManager gOManager) {
		plantMap.put(p.cellCoordinate.getCellX(), p.cellCoordinate.getCellY(), 2);
	}

	@Override
	public boolean condition(Player p, TileMap level, BombManager bm, PlantMap plantMap, GameObjectManager gOManager) {
		return plantMap.canPlant(p, 2);
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

}
