package Curio.PlantMap.Plants;

import org.newdawn.slick.Image;

import Curio.PlantMap.Plant;
import Curio.PlantMap.PlantMap;
import Curio.Tilemap.TileMap;
import Default.Constants;
import Default.Player;

public class SausagePlant implements Plant {
	Image[] imgArray = { Constants.sausagePlant1, Constants.sausagePlant2, Constants.sausagePlant3,
			Constants.sausagePlant4, Constants.sausagePlant5 };

	private int maxStates = imgArray.length - 1;
	private int maxGrowth = 200;
	private int maxHealth = 100;
	private int itemID = 7;

	@Override
	public boolean plantCondition(Player player, TileMap tilemap, int x, int y) {
		if (tilemap.get_Tile(x, y) == 6) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean harvestCondition(Player player, TileMap tilemap, int x, int y) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getMaxHealth() {
		return maxHealth;
	}

	@Override
	public int getMaxGrowth() {
		return maxGrowth;
	}

	@Override
	public int getMaxStates() {
		return maxStates;
	}

	@Override
	public Image[] getImageArray() {
		return imgArray;
	}

	@Override
	public int getHarvestItem() {
		return itemID;
	}
}
