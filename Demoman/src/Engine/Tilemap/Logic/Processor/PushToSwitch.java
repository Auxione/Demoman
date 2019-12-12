package Engine.Tilemap.Logic.Processor;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Default.Constants;
import Engine.Tilemap.Logic.Logic;
import Engine.Utilities.Transform;

public class PushToSwitch extends Logic {
	final String debugcode = "<PushToSwitch>:";
	final boolean debugActive = false;

	public Transform transform;
	private Transform outputTile;

	private boolean state = false;
	private boolean activated = false;

	public PushToSwitch(int x, int y, Transform _outputTile) {
		transform = new Transform(x, y);
		outputTile = _outputTile;
	}

	public void update() {
		if (getState(transform) == true) {
			state = !state;
			activated = true;
		}

		if (activated == true) {
			setState(outputTile, state);
			activated = false;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(transform.get_x() * Constants.CellSize, transform.get_y() * Constants.CellSize, Constants.CellSize,
				Constants.CellSize);
	}

	@Override
	public void keyEvent() {

	}
}