package Curio.ObjectiveSystem;

import java.util.ArrayList;

import Curio.TileMap;
import Curio.ItemSystem.ItemMap;
import Curio.PlantSystem.PlantMap;
import Default.Player;

public class ObjectiveSystem {
	private Player player;
	private TileMap tileMap;
	private ItemMap itemMap;
	private PlantMap plantMap;

	private ArrayList<Objective> objectiveList;

	public ObjectiveSystem(Player player, TileMap tileMap, ItemMap itemMap, PlantMap plantMap) {
		this.player = player;
		this.tileMap = tileMap;
		this.itemMap = itemMap;
		this.plantMap = plantMap;

		objectiveList = new ArrayList<Objective>();
	}

	public void add(Objective objc) {
		objectiveList.add(objc);
	}

	public void remove(Objective objc) {
		objectiveList.remove(objc);
	}

	public void update(Player player, TileMap tileMap, ItemMap itemMap, PlantMap plantMap) {
		for (Objective objc : objectiveList) {
			objc.objectiveUpdate(player, tileMap, itemMap, plantMap);
			if (objc.completed() == true && objc.failed() == false) {
				objc.successfulTrigger(player, tileMap, itemMap, plantMap);
			}

			else if (objc.completed() == true && objc.failed() == true) {
				objc.failedTrigger(player, tileMap, itemMap, plantMap);
			}
		}
	}

	public void updateEnd() {
		for (Objective objc : objectiveList) {
			objc.updateEnd();
		}
	}

}
