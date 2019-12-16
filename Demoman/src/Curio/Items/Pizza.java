package Curio.Items;

import org.newdawn.slick.Image;

import Curio.Tilemap.Tilemap;
import Default.Constants;
import Default.DynamicPlayer;

public class Pizza extends Item {
	public int value = 60;
	private Image image = Constants.pizza;

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return image;
	}

	@Override
	public void apply(DynamicPlayer dp) {
		dp.addFood(value);

	}

	@Override
	public void apply(Tilemap level) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean condition(DynamicPlayer dp) {
		if (dp.getCurrentFood() < dp.getMaxFood()) {
			return true;
		} else {
			return false;
		}
	}
}