package Curio.ItemSystem;

import org.newdawn.slick.Image;

import Curio.TileMap;
import Curio.BombManager.BombManager;
import Curio.GameObject.GameObjectManager;
import Curio.PlantSystem.PlantMap;
import Default.Player;

public interface Item {

	public Image getImage();

	public void apply(Player p, TileMap level, BombManager bm, PlantMap plantMap, GameObjectManager gOManager);

	public boolean condition(Player p, TileMap level, BombManager bm, PlantMap plantMap, GameObjectManager gOManager);

	public String getName();
	
	public int getItemCategory();
	//cat 1 = food;
	//cat 2 = health
	//cat 3 = explosive
	//cat 4 = seed
	
	public String getDescription();

	public int getHealth();
}
