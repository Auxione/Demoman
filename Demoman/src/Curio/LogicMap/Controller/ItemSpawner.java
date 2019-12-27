package Curio.LogicMap.Controller;

import org.newdawn.slick.Graphics;

import Curio.ItemMap.ItemMap;
import Curio.LogicMap.Logic;
import Curio.LogicMap.LogicMap;
import Curio.Utilities.CellCoordinate;
import Default.Constants;

//test edilmedi
public class ItemSpawner implements Logic {
	final String debugcode = "<ItemSpawner>: ";
	final boolean debugActive = true;

	public CellCoordinate objectCellPosition;

	private boolean spawn = false;
	private int itemID;

	private ItemMap itemMap;

	public ItemSpawner(int x, int y, int id, ItemMap itemMap) {
		this.itemMap = itemMap;
		objectCellPosition = new CellCoordinate(x, y);
		itemID = id;
	}

	public void render(Graphics g) {
		g.pushTransform();
		g.translate(objectCellPosition.getWorldX(), objectCellPosition.getWorldY());
		g.drawImage(Constants.itemspawner, 0, 0);
		g.popTransform();
		}

	@Override
	public void keyEvent() {

	}

	@Override
	public void update(LogicMap logicMap) {
		spawn = logicMap.getState(objectCellPosition);
		if (spawn == true) {
			itemMap.put(objectCellPosition.getCellX(), objectCellPosition.getCellY(), itemID);
		}
	}
}