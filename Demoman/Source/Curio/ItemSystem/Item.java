package Curio.ItemSystem;

import org.newdawn.slick.Image;

import Curio.SessionManagers.PlantManager;
import Curio.SessionManagers.WorldManager;
import Curio.SessionManagers.BombManager.BombManager;
import Curio.SessionManagers.GameObjectManager.WorldObjectManager;
import Default.Player;

public interface Item {

	public Image getImage();

	public void apply(WorldManager worldManager,WorldObjectManager gameObjectManager, BombManager bombManager, PlantManager plantManager, Player player);

	public boolean condition(WorldManager worldManager,WorldObjectManager gameObjectManager, BombManager bombManager, PlantManager plantManager, Player player);

	public String getName();
	
	public int getItemCategory();
	//cat 1 = food;
	//cat 2 = health
	//cat 3 = explosive
	//cat 4 = seed
	//cat 5 = gameobj
	
	public String getDescription();

	public int getHealth();
}
