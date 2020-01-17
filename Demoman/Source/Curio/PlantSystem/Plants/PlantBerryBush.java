package Curio.PlantSystem.Plants;

import org.newdawn.slick.Image;

import Curio.ItemSystem.Item;
import Curio.ItemSystem.ItemList;
import Curio.PlantSystem.Plant;
import Curio.SessionManagers.WorldManager;
import Default.Constants;
import Default.Player;

public class PlantBerryBush implements Plant {
	Image[] imgArray = { Constants.berryBushState1, Constants.berryBushState2, Constants.berryBushState3 };

	private int maxStates = imgArray.length - 1;
	private int maxGrowthInMinutes = 100;
	private int maxHealth = 100;
	private int itemID = 8;
	private String name = "Berrybush";
	private String desc = "null";

	@Override
	public boolean plantCondition(Player player, WorldManager worldManager, int x, int y) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean growthCondition(WorldManager worldManager) {
		if (worldManager.worldTime.isDaytime() == true) {
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
	public int getMaxHealth() {
		return maxHealth;
	}

	@Override
	public int getMaxGrowthInMinutes() {
		// TODO Auto-generated method stub
		return maxGrowthInMinutes;
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
