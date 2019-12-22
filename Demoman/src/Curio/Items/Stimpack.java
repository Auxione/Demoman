package Curio.Items;

import org.newdawn.slick.Image;

import Curio.Tilemap.Tilemap;
import Curio.Tilemap.Bomb.BombManager;
import Default.Constants;
import Default.DynamicPlayer;

public class Stimpack extends Item {
	public int value = 10;
	private Image image = Constants.stimpack;
	
	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public void apply(DynamicPlayer dp, Tilemap level, BombManager bm) {
		dp.addHealth(value);
		
	}

	@Override
	public boolean condition(DynamicPlayer dp, Tilemap level, BombManager bm) {
		if (dp.getCurrentHealth() < dp.getMaxHealth()) {
			return true;
		} else {
			return false;
		}
	}
}
