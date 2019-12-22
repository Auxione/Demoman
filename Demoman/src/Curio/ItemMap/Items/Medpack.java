package Curio.ItemMap.Items;

import org.newdawn.slick.Image;

import Curio.ItemMap.Item;
import Curio.Tilemap.TileMap;
import Curio.Tilemap.Bomb.BombManager;
import Default.Constants;
import Default.Player;

public class Medpack implements Item {
	public int value = 35;
	private Image image = Constants.medpack;

	public Image getImage() {
		return image;
	}

	@Override
	public void apply(Player dp, TileMap level, BombManager bm) {
		dp.addHealth(value);
	}

	@Override
	public boolean condition(Player dp, TileMap level, BombManager bm) {
		if (dp.getCurrentHealth() < dp.getMaxHealth()) {
			return true;
		} else {
			return false;
		}
	}

}
