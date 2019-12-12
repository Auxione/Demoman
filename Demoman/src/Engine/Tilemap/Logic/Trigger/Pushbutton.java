package Engine.Tilemap.Logic.Trigger;

import org.newdawn.slick.Graphics;

import Default.Constants;
import Engine.Tilemap.Logic.Logic;
import Engine.Utilities.Transform;

public class Pushbutton extends Logic {

	public Transform transform;
	private Transform outputTile;

	private boolean activated = false;

	public Pushbutton(int x, int y, Transform _outputTile) {
		transform = new Transform(x, y);
		outputTile = _outputTile;
	}

	@Override
	public void keyEvent() {
		activated = true;
	}

	@Override
	public void update() {
		if (activated == true) {
			sendTick(outputTile, activated);
			activated = false;
		}	
	}

	public void render(Graphics g) {
		g.drawImage(Constants.pushbutton, transform.get_x() * Constants.CellSize,
				transform.get_y() * Constants.CellSize);
	}
}