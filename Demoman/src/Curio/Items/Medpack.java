package Curio.Items;

import org.newdawn.slick.Image;

import Curio.Tilemap.Tilemap;
import Default.Constants;
import Default.DynamicPlayer;

public class Medpack extends Item {
	public int value = 35;
	private Image image = Constants.medpack;

	@Override
	public void apply(DynamicPlayer dp) {
		dp.addHealth(value);
	}

	public Image getImage() {
		return image;
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
	public void apply(Tilemap level) {
		// TODO Auto-generated method stub
		
	}
}
