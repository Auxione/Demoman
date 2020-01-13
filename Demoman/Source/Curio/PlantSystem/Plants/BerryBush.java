package Curio.PlantSystem.Plants;

import org.newdawn.slick.Image;

import Curio.TileMap;
import Curio.PlantSystem.Plant;
import Default.Constants;
import Default.Player;

public class BerryBush implements Plant {
	Image[] imgArray = { Constants.berryBushState1, Constants.berryBushState2, Constants.berryBushState3 };

	private int maxStates = imgArray.length - 1;
	private int maxGrowth = 100;
	private int maxHealth = 100;
	private int itemID = 8;
	private String name = "Berrybush";
	private String desc = "null";
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return desc;
	}
	@Override
	public boolean plantCondition(Player player, TileMap level,int x, int y) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean harvestCondition(Player player, TileMap level,int x, int y) {
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
