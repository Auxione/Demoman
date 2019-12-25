package Curio.ObjectiveSystem.Objectives;

import Curio.ItemMap.ItemMap;
import Curio.ObjectiveSystem.Objective;
import Curio.PlantMap.PlantMap;
import Curio.Tilemap.TileMap;
import Curio.Utilities.Math.Transform;
import Default.Player;

public class MoveToTile implements Objective {
	private String goalText;
	private Transform targetPosition;
	private boolean status = false;

	public MoveToTile(int x, int y) {
		this.targetPosition = new Transform(x, y);
		this.goalText = "Current Goal: \n" + "Move to location: \n" + "x: " + x + " y: " + y + ".";
	}

	@Override
	public void startTrigger(Player player, TileMap tileMap, ItemMap itemMap, PlantMap plantMap) {

	}

	@Override
	public void objectiveUpdate(Player player, TileMap tileMap, ItemMap itemMap, PlantMap plantMap) {
		if (player.CellPosition.equals(targetPosition) == true) {
			status = true;
		}
	}

	@Override
	public void successfulTrigger(Player player, TileMap tileMap, ItemMap itemMap, PlantMap plantMap) {
		// TODO Auto-generated method stub

	}

	@Override
	public void failedTrigger(Player player, TileMap tileMap, ItemMap itemMap, PlantMap plantMap) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean completed() {
		return status;
	}

	@Override
	public boolean failed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String goalText() {
		// TODO Auto-generated method stub
		return goalText;
	}

	@Override
	public void updateEnd() {
		status = false;
	}

}
