package Engine.Tilemap.Logic.Controller;

import org.newdawn.slick.Graphics;

import Default.Constants;
import Default.GameManager;
import Engine.Tilemap.Logic.Logic;
import Engine.Utilities.Transform;

//test edilmedi
public class ItemSpawner extends Logic {
	final String debugcode = "<ItemSpawner>: ";
	final boolean debugActive = true;
	
	public Transform transform;

	private boolean spawn = false;
	private int itemid;
	
	public ItemSpawner(GameManager gameManager, int x, int y, int id) {
		transform = new Transform(x,y);
		
		itemid = id;
	}

	public void render(Graphics g) {
		g.drawImage(Constants.itemspawner, transform.get_x() * Constants.CellSize, transform.get_y() * Constants.CellSize);
	}

	@Override
	public void keyEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		spawn = getState(transform);
		
		if (spawn == true) {
			if (debugActive) {
				System.out.println(debugcode + "Item with id: " + itemid + " spawned at x: " + (transform.get_x()) + " y: "
						+ (transform.get_y()));
			}
		}
	}
}