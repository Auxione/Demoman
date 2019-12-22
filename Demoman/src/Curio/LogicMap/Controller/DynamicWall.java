package Curio.LogicMap.Controller;

import org.newdawn.slick.Graphics;

import Curio.LogicMap.Logic;
import Curio.LogicMap.LogicMap;
import Curio.Tilemap.TileMap;
import Curio.Utilities.Math.Transform;

public class DynamicWall implements Logic {
	private Boolean state = false;

	private Transform transform;
	private int changedTileID, originalTileID;

	private TileMap lvl;
	/*
	 * Create dynamic wall which is tile id in that position can be changed with
	 * user interaction in game parameters: level,x position, y position,changed
	 * tile id
	 */

	public DynamicWall(TileMap l, int x, int y, int changeID) {
		lvl = l;
		transform = new Transform(x, y);

		originalTileID = lvl.get_Tile(transform.get_x(), transform.get_y());
		changedTileID = changeID;
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
		state = logicMap.getState(transform);

		if (state == true) {
			// change the tile id on the current level to tileid1
			lvl.set_Tile(transform.get_x(), transform.get_y(), changedTileID);

		} else if (state == false) {
			// change the tile id on the current level to tileid2
			lvl.set_Tile(transform.get_x(), transform.get_y(), originalTileID);

		}
	}

}