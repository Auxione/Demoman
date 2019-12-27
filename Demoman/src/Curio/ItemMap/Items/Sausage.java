package Curio.ItemMap.Items;

import org.newdawn.slick.Image;

import Curio.ItemMap.Item;
import Curio.PlantMap.PlantMap;
import Curio.Tilemap.TileMap;
import Curio.Tilemap.Bomb.BombManager;
import Default.Constants;
import Default.Player;

public class Sausage implements Item {
	private Image img = Constants.sausage;
	private int foodValue = 15;
	private int healthValue = 5;
	private String name = "Sausage";
	private String description = "Space sausage. Yummy!";

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
	public void apply(Player p, TileMap level, BombManager bm, PlantMap plantMap) {
		p.addFood(foodValue);
		p.addHealth(healthValue);
	}

	@Override
	public boolean condition(Player p, TileMap level, BombManager bm, PlantMap plantMap) {
		if (p.getCurrentFood() < p.getMaxFood()) {
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
