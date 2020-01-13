package Curio.ObjectiveSystem;

import Curio.TileMap;
import Curio.ItemSystem.ItemMap;
import Curio.PlantSystem.PlantMap;
import Default.Player;

public interface Objective {
	public void startTrigger(Player player, TileMap tileMap, ItemMap itemMap, PlantMap plantMap);

	public void objectiveUpdate(Player player, TileMap tileMap, ItemMap itemMap, PlantMap plantMap);

	public void successfulTrigger(Player player, TileMap tileMap, ItemMap itemMap, PlantMap plantMap);

	public void failedTrigger(Player player, TileMap tileMap, ItemMap itemMap, PlantMap plantMap);

	public boolean completed();

	public boolean failed();

	public String goalText();
	
	public void updateEnd();
}
