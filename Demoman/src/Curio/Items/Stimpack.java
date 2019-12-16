package Curio.Items;

import org.newdawn.slick.Image;

import Curio.Tilemap.Tilemap;
import Default.Constants;
import Default.DynamicPlayer;

public class Stimpack extends Item {
	public int value = 10;
	private Image image = Constants.stimpack;

	@Override
	public void apply(DynamicPlayer dp) {
		dp.addHealth(value);
	}

	@Override
	public boolean condition(DynamicPlayer dp) {
		if (dp.getCurrentHealth() < dp.getMaxHealth()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public void apply(Tilemap level) {
		// TODO Auto-generated method stub
		
	}
}
