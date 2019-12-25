package Curio.ItemMap.Items;

import org.newdawn.slick.Image;

import Curio.ItemMap.Item;
import Curio.PlantMap.PlantMap;
import Curio.Tilemap.TileMap;
import Curio.Tilemap.Bomb.BombManager;
import Default.Constants;
import Default.Player;

public class Pizza implements Item {
	public int value = 60;
	private Image image = Constants.pizza;
	private String name = "Pizza";
	private String description = "Pizza. Pineapple free.";

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
	public void apply(Player dp, TileMap level, BombManager bm, PlantMap plantMap) {
		dp.addFood(value);

	}

	@Override
	public boolean condition(Player dp, TileMap level, BombManager bm, PlantMap plantMap) {
		if (dp.getCurrentFood() < dp.getMaxFood()) {
			return true;
		} else {
			return false;
		}
	}
}