package Curio.Tilemap.Logic.Controller;

import org.newdawn.slick.Graphics;

import Curio.Items.Item;
import Curio.Tilemap.Logic.Logic;
import Curio.Utilities.Transform;
import Default.Constants;

//test edilmedi
public class ItemSpawner extends Logic {
	final String debugcode = "<ItemSpawner>: ";
	final boolean debugActive = true;

	public Transform transform;

	private boolean spawn = false;
	private int itemID;

	public ItemSpawner(int x, int y, int id) {
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
	public void update() {
		spawn = getState(transform);
		if (spawn == true) {
			Item.put(transform.get_x(), transform.get_y(), itemID);
		}
	}
}