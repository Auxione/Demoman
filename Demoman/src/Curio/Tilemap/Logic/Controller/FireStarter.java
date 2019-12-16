package Curio.Tilemap.Logic.Controller;

import org.newdawn.slick.Graphics;

import Curio.Tilemap.FireManager;
import Curio.Tilemap.Logic.Logic;
import Curio.Utilities.Transform;
import Default.Constants;

public class FireStarter extends Logic {
	public Transform transform;
	public FireManager fm;
	private boolean Activate = false;

	public FireStarter(FireManager _fm,int x, int y) {
		fm = _fm;
		transform = new Transform(x, y);
		
	}

	public void render(Graphics g) {
		g.drawImage(Constants.firestarter, transform.get_x() * Constants.CellSize,
				transform.get_y() * Constants.CellSize);
	}

	@Override
	public void keyEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		Activate = getState(transform);
		if (Activate == true) {
			fm.create(transform.get_x(), transform.get_y());
			Activate = false;
		}
	}

}
