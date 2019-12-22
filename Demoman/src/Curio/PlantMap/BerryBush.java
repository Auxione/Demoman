package Curio.PlantMap;

import org.newdawn.slick.Image;

import Curio.Tilemap.TileMap;
import Curio.Tilemap.Bomb.BombManager;
import Default.Constants;
import Default.Player;

public class BerryBush implements Plant {
	Image state1 = Constants.berryBushState1;
	Image state2 = Constants.berryBushState2;
	Image state3 = Constants.berryBushState3;

	private int state = 0;
	private int maxGrowth = 100;
	private int maxHealth = 100;

	@Override
	public boolean plantCondition(Player p, TileMap level, BombManager bm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean harvestCondition(Player p, TileMap level, BombManager bm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void growthRate(float percentage) {
		if (percentage > 0.0f && percentage < 45.0f) {
			state = 0;
		} else if (percentage > 45.0f && percentage < 90.0f) {
			state = 1;
		} else if (percentage > 90.0f && percentage < 100.0f) {
			state = 2;
		}
	}

	@Override
	public Image getImage() {
		switch (state) {
		default:
			return state1;
		case 1:
			return state2;
		case 2:
			return state2;
		}
	}

	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return maxHealth;
	}

	@Override
	public int getMaxGrowth() {
		// TODO Auto-generated method stub
		return maxGrowth;
	}

}
