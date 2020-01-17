package Curio.PlantSystem.Plants;

import org.newdawn.slick.Image;

import Curio.ItemSystem.Item;
import Curio.ItemSystem.ItemList;
import Curio.PlantSystem.Plant;
import Curio.SessionManagers.WorldManager;
import Default.Constants;
import Default.Player;

public class PlantSausagePlant implements Plant {
	Image[] imgArray = { Constants.sausagePlant1, Constants.sausagePlant2, Constants.sausagePlant3,
			Constants.sausagePlant4, Constants.sausagePlant5 };

	private int maxStates = imgArray.length - 1;
	private int maxGrowthInMinutes = 200;
	private int maxHealth = 100;
	private int itemID = 7;
	private String name = "Sausage Plant";
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
	public boolean plantCondition(Player player, WorldManager worldManager, int x, int y) {
		if (worldManager.tileMap.getTile(x, y, 0) == 6) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean growthCondition(WorldManager worldManager) {
		if (worldManager.worldTime.isNight() == true) {
			return true;
		}
		return false;
	}

	@Override
	public boolean harvestCondition(Player player, WorldManager worldManager, int x, int y) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getMaxGrowthInMinutes() {
		// TODO Auto-generated method stub
		return maxGrowthInMinutes;
	}

	@Override
	public int getMaxHealth() {
		return maxHealth;
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
	public Item getHarvestItem() {
		// TODO Auto-generated method stub
		return ItemList.list.get(itemID);
	}
}
