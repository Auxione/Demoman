package Curio.ItemMap.Items;

import org.newdawn.slick.Image;

import Curio.ItemMap.Item;
import Curio.PlantMap.PlantMap;
import Curio.Tilemap.TileMap;
import Curio.Tilemap.Bomb.BombManager;
import Default.Constants;
import Default.Player;

public class Stimpack implements Item {
	public int value = 10;
	private Image image = Constants.stimpack;
	
	private String name = "Stimpack";
	private String description = "Heals for "+value+" HP.";

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
	public void apply(Player dp, TileMap level, BombManager bm,PlantMap plantMap) {
		dp.addHealth(value);
		
	}

	@Override
	public boolean condition(Player dp, TileMap level, BombManager bm,PlantMap plantMap) {
		if (dp.getCurrentHealth() < dp.getMaxHealth()) {
			return true;
		} else {
			return false;
		}
	}
}
