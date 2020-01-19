package Curio.SessionManagers.LogicManager.LogicObjects.Controller;

import Curio.SessionManagers.LogicManager.LogicMap;
import Curio.SessionManagers.LogicManager.Interfaces.LogicController;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.SessionManagers.WorldManager.TileMap;
import Curio.Utilities.Math.Transform;

public class DynamicWall extends LogicObject implements LogicController {
	private Boolean state = false;
	private int changedTileID, originalTileID;

	private TileMap tileMap;
	/*
	 * Create dynamic wall which is tile id in that position can be changed with
	 * user interaction in game parameters: level,x position, y position,changed
	 * tile id
	 */

	public DynamicWall(TileMap tileMap, Transform transform, int changeID) {
		super(null, transform);
		this.tileMap = tileMap;

		this.originalTileID = tileMap.getCell(super.cellCoordinate, 0);
		this.changedTileID = changeID;
	}
	@Override
	public void update(LogicMap logicMap) {
		// get current tile id first to process it on checktile()
		// if tile is activated and not broken
		// check the tile if its not changed outside our func.
		// change the state
		state = logicMap.getState(super.cellCoordinate);
		

		if (state == true) {
			// change the tile id on the current level to tileid1
			tileMap.setCell(super.cellCoordinate, 0, changedTileID);
		}

		else if (state == false) {
			// change the tile id on the current level to tileid2
			tileMap.setCell(super.cellCoordinate, 0, originalTileID);
		}
	}

}