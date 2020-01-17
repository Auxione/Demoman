package Curio.SessionManagers;

import Curio.Functions;
import Curio.ItemSystem.ItemMap;
import Curio.Physics.WorldTime;
import Curio.PlantSystem.PlantList;
import Curio.PlantSystem.PlantMap;
import Default.Player;

public class PlantManager {
	private WorldManager worldManager;
	private WorldTime worldTime;
	public PlantMap plantMap;
	private ItemMap itemMap;

	public PlantManager(WorldManager worldManager, PlantMap plantMap, ItemMap itemMap) {
		this.plantMap = plantMap;
		this.worldManager = worldManager;
		this.itemMap = itemMap;
		this.worldTime = worldManager.worldTime;
	}

	public void harvest(Player player) {
		int plantID = plantMap.getTile(player.cellCoordinate, 0);
		boolean readyToHarvest = (plantMap.getTile(player.cellCoordinate, 4) == plantMap.getTile(player.cellCoordinate,
				5));

		if (plantID != 0 && readyToHarvest) {
			if (PlantList.list.get(plantID).harvestCondition(player, worldManager, player.cellCoordinate.getCellX(),
					player.cellCoordinate.getCellY())) {
				itemMap.put(player.cellCoordinate, PlantList.list.get(plantID).getHarvestItem());
				plantMap.clearCell(player.cellCoordinate);
			}
		}
	}

	public void plant(Player player, int plantID) {
		if (canPlant(player, plantID) == true) {
			plantMap.put(player.cellCoordinate, plantID);
		}
	}

	public boolean canPlant(Player player, int plantID) {
		if (PlantList.list.get(plantID).plantCondition(player, worldManager, player.cellCoordinate.getCellX(),
				player.cellCoordinate.getCellY()) && plantMap.getTile(player.cellCoordinate, 0) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void update() {
		updateGrowth();
		checkHealth();
	}

	private void updateGrowth() {
		if (worldTime.getMinutesTick() == true) {
			for (int x = 0; x < plantMap.getXAxisMaxCell(); x++) {
				for (int y = 0; y < plantMap.getYAxisMaxCell(); y++) {
					if (plantMap.isEmpty(x, y) == false) {
						if (PlantList.list.get(plantMap.getTile(x, y, 0)).growthCondition(worldManager) == true) {
							if (plantMap.getTile(x, y, 2) < plantMap.getTile(x, y, 3)) {
								// calculate growth
								int growth = plantMap.getTile(x, y, 2);
								plantMap.setTile(x, y, 2, growth + 1);
								// calculate state
								plantMap.setTile(x, y, 4, calculateState(x, y));
							} 
							
							else if (plantMap.getTile(x, y, 2) >= plantMap.getTile(x, y, 3)) {
								plantMap.setTile(x, y, 2, plantMap.getTile(x, y, 3));
								plantMap.setTile(x, y, 4, plantMap.getTile(x, y, 5));
							}
						}
					}
					// if health below zero then clear the cell
					else if (plantMap.getTile(x, y, 1) <= 0) {
						plantMap.clearCell(x, y);
					}
				}
			}
		}
	}

	public String getName(int id) {
		return PlantList.list.get(id).getName();
	}

	public String getDescription(int id) {
		return PlantList.list.get(id).getDesc();
	}

	public int getHealth(int x, int y) {
		return plantMap.getTile(x, y, 1);
	}

	private int calculateState(int x, int y) {
		int out = (int) Functions.map(plantMap.getTile(x, y, 2), 0, plantMap.getTile(x, y, 3), 0,
				plantMap.getTile(x, y, 5));
		return out;
	}

	public void checkHealth() {
		for (int x = 0; x < plantMap.getXAxisMaxCell(); x++) {
			for (int y = 0; y < plantMap.getYAxisMaxCell(); y++) {
				if (plantMap.getTile(x, y, 1) < 0) {
					plantMap.clearCell(x, y);
				}

				else if (plantMap.getTile(x, y, 1) > getHealth(x, y)) {
					plantMap.setTile(x, y, 1, getHealth(x, y));
				}
			}
		}
	}
}
