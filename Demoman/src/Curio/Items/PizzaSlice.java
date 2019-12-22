package Curio.Items;

import org.newdawn.slick.Image;

import Curio.Tilemap.Tilemap;
import Curio.Tilemap.Bomb.BombManager;
import Default.Constants;
import Default.DynamicPlayer;

public class PizzaSlice extends Item {
	public int value = 10;
	private Image image = Constants.pizzaSlice;

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return image;
	}

	@Override
	public void apply(DynamicPlayer dp, Tilemap level, BombManager bm) {
		dp.addFood(value);

	}

	@Override
	public boolean condition(DynamicPlayer dp, Tilemap level, BombManager bm) {
		if (dp.getCurrentFood() < dp.getMaxFood()) {
			return true;
		} else {
			return false;
		}
	}
}
