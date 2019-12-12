package Engine.Tilemap.Logic.Trigger;

import org.newdawn.slick.Graphics;

import Default.Constants;
import Engine.Tilemap.Logic.Logic;
import Engine.Utilities.Transform;

public class Switchbutton extends Logic {
	final String debugcode = "<Pushbutton>:";
	final boolean debugActive = false;

	public Transform transform;
	private Transform outputTile;

	private boolean state = false;
	private boolean Activated = false;

	public Switchbutton(int x, int y, Transform _outputTile) {
		transform = new Transform(x, y);
		outputTile = _outputTile;
	}

	public void render(Graphics g) {
		if (state == true) {
			g.drawImage(Constants.switchon, transform.get_x() * Constants.CellSize,
					transform.get_y() * Constants.CellSize);
		} else if (state == false) {
			g.drawImage(Constants.switchoff, transform.get_x() * Constants.CellSize,
					transform.get_y() * Constants.CellSize);
		}
	}

	public void keyEvent() {
		state = !state;
		Activated = true;
	}

	public void update() {
		if (Activated == true) {
			setState(outputTile, state);
			Activated = false;
		}
	}
}