package Curio.PlantSystem;

import org.newdawn.slick.Image;

import Curio.ItemSystem.Item;
import Curio.SessionManagers.WorldManager;
import Default.Player;

public interface Plant {

	public boolean plantCondition(Player player, WorldManager worldManager,int x,int y);
	
	public boolean growthCondition(WorldManager worldManager);

	public boolean harvestCondition(Player player, WorldManager worldManager,int x,int y);

	public Image[] getImageArray();

	public int getMaxHealth();

	public int getMaxGrowthInMinutes();

	public int getMaxStates();

	public Item getHarvestItem();

	public String getName();

	public String getDesc();
}
