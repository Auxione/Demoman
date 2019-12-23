package Curio.PlantMap;

import org.newdawn.slick.Image;

import Curio.Tilemap.TileMap;
import Default.Player;

public interface Plant {

	public boolean plantCondition(Player player, TileMap level,int x, int y);

	public boolean harvestCondition(Player player, TileMap level,int x, int y);

	public Image[] getImageArray();

	public int getMaxHealth();

	public int getMaxGrowth();

	public int getMaxStates();
	
	public int getHarvestItem();
}
