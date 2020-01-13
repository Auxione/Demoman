package Curio.LogicSystem.Controller;

import org.newdawn.slick.Graphics;

import Curio.TileMap;
import Curio.LogicSystem.Logic;
import Curio.LogicSystem.LogicMap;
import Curio.Utilities.CellCoordinate;

public class DynamicWall implements Logic {
	private Boolean state = false;

	private CellCoordinate objectCellPosition;
	private int changedTileID, originalTileID;

	private TileMap tileMap;
	/*
	 * Create dynamic wall which is tile id in that position can be changed with
	 * user interaction in game parameters: level,x position, y position,changed
	 * tile id
	 */

	public DynamicWall(TileMap tileMap, int x, int y, int changeID) {
		this.tileMap = tileMap;
		this.objectCellPosition = new CellCoordinate(x, y);

		this.originalTileID = tileMap.getTile(objectCellPosition, 0);
		this.changedTileID = changeID;
	}

	@Override
	public void keyEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(LogicMap logicMap) {
		// get current tile id first to process it on checktile()
		// if tile is activated and not broken
		// check the tile if its not changed outside our func.
		// change the state
		state = logicMap.getState(objectCellPosition);

		if (state == true) {
			// change the tile id on the current level to tileid1
			tileMap.setTile(objectCellPosition, 0, changedTileID);
		}

		else if (state == false) {
			// change the tile id on the current level to tileid2
			tileMap.setTile(objectCellPosition, 0, originalTileID);
		}
	}

}