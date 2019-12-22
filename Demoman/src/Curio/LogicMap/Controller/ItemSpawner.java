package Curio.LogicMap.Controller;

import org.newdawn.slick.Graphics;

import Curio.ItemMap.ItemMap;
import Curio.LogicMap.Logic;
import Curio.LogicMap.LogicMap;
import Curio.Utilities.Math.Transform;
import Default.Constants;

//test edilmedi
public class ItemSpawner implements Logic {
	final String debugcode = "<ItemSpawner>: ";
	final boolean debugActive = true;

	public Transform transform;

	private boolean spawn = false;
	private int itemID;

	private ItemMap itemMap;

	public ItemSpawner(int x, int y, int id, ItemMap itemMap) {
		this.itemMap = itemMap;
		transform = new Transform(x, y);
		itemID = id;
	}

	public void render(Graphics g) {
		g.drawImage(Constants.itemspawner, transform.get_x() * Constants.CellSize,
				transform.get_y() * Constants.CellSize);
	}

	@Override
	public void keyEvent() {

	}

	@Override
	public void update(LogicMap logicMap) {
		spawn = logicMap.getState(transform);
		if (spawn == true) {
			itemMap.put(transform.get_x(), transform.get_y(), itemID);
		}
	}
}