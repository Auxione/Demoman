package Curio.PlantSystem;

import Curio.Functions;
import Curio.TileMap;
import Curio.TileList;
import Curio.CellularMap.CellularMap;
import Curio.ItemSystem.ItemMap;
import Default.Player;

public class PlantMap extends CellularMap {
	
	
	private TileMap tilemap;
	private ItemMap itemMap;

	int millis_goal = 0;
	int rate = 20;

	public PlantMap(TileMap tilemap, ItemMap itemMap) {
		// coordinates x,y
		// int[][][0] = plant id
		// int[][][1] = plant health
		// int[][][2] = currentplantgrowth
		// int[][][3] = plantmaxgrowth
		// int[][][4] = currentStates
		// int[][][5] = maxStates
		super(tilemap.getXAxisMaxCell(), tilemap.getYAxisMaxCell(), 6);
		
		this.tilemap = tilemap;
		this.itemMap = itemMap;

		super.clearCells();
	}

	public void put(int x, int y, int id) {
		if (super.getTile(x, y, 0) == 0 && TileList.getTile(tilemap.getTile(x, y, 0)).getCanplant()) {
			super.setTile(x, y, 0, id);
			super.setTile(x, y, 1, PlantList.list.get(id).getMaxHealth());
			super.setTile(x, y, 2, 0);
			super.setTile(x, y, 3, PlantList.list.get(id).getMaxGrowth());
			super.setTile(x, y, 4, 0);
			super.setTile(x, y, 5, PlantList.list.get(id).getMaxStates());
		}
	}

	public void harvest(Player player) {
		int x = player.cellCoordinate.getCellX();
		int y = player.cellCoordinate.getCellY();
		int plantID = super.getTile(x, y, 0);

		boolean readyToHarvest = (super.getTile(x, y, 4) == super.getTile(x, y, 5));
		if (plantID != 0 && readyToHarvest) {
			if (PlantList.list.get(plantID).harvestCondition(player, tilemap, x, y)) {
				itemMap.put(x, y, PlantList.list.get(plantID).getHarvestItem());
				clearCell(x, y);
			}
		}
	}

	public boolean canPlant(Player player, int plantID) {
		int x = player.cellCoordinate.getCellX();
		int y = player.cellCoordinate.getCellY();
		if (PlantList.list.get(plantID).plantCondition(player, tilemap, x, y) && super.getTile(x, y, 0) == 0) {
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
		if (Functions.millis() > millis_goal) {
			for (int x = 0; x < super.getXAxisMaxCell(); x++) {
				for (int y = 0; y < super.getYAxisMaxCell(); y++) {
					if (super.getTile(x, y, 0) != 0 && super.getTile(x, y, 1) > 0) {
						if (super.getTile(x, y, 2) < super.getTile(x, y, 3)) {
							// calculate growth
							int growth = super.getTile(x, y, 2);
							super.setTile(x, y, 2, growth + 1);
							// calculate state
							super.setTile(x, y, 4, calculateState(x, y));
						}

						else if (super.getTile(x, y, 2) >= super.getTile(x, y, 3)) {
							super.setTile(x, y, 2, super.getTile(x, y, 3));
							super.setTile(x, y, 4, super.getTile(x, y, 5));
						}
					}
					// if health below zero then clear the cell
					else if (super.getTile(x, y, 1) <= 0) {
						clearCell(x, y);
					}
				}
			}
			millis_goal = Functions.millis() + rate;
		}
	}

	// int[][][0] = plant id
	// int[][][1] = plant health
	// int[][][2] = currentplantgrowth
	// int[][][3] = plantmaxgrowth
	// int[][][4] = currentStates
	// int[][][5] = maxStates
	public String getName(int id) {
		return PlantList.list.get(id).getName();
	}

	public String getDescription(int id) {
		return PlantList.list.get(id).getDesc();
	}

	public int getHealth(int x, int y) {
		return super.getTile(x, y, 1);
	}

	private int calculateState(int x, int y) {
		int out = (int) Functions.map(super.getTile(x, y, 2), 0, super.getTile(x, y, 3), 0, super.getTile(x, y, 5));
		return out;
	}

	public void applyDamage(int xPosition, int yPosition, int damage) {
		int tileHP = super.getTile(xPosition, yPosition, 1);
		super.setTile(xPosition, yPosition, 1, tileHP - damage);
	}

	public void checkHealth() {
		for (int x = 0; x < super.getXAxisMaxCell(); x++) {
			for (int y = 0; y < super.getYAxisMaxCell(); y++) {
				if (super.getTile(x, y, 1) < 0) {
					clearCell(x, y);
				}

				else if (super.getTile(x, y, 1) > getHealth(x, y)) {
					super.setTile(x, y, 1, getHealth(x, y));
				}
			}
		}
	}
}
