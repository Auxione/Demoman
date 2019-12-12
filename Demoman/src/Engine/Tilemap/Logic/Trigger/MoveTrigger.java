package Engine.Tilemap.Logic.Trigger;

import org.newdawn.slick.Graphics;

import Default.Constants;
import Engine.Tilemap.Logic.Logic;
import Engine.Utilities.Transform;

public class MoveTrigger extends Logic {
	final String debugcode = "<MoveTrigger>: ";
	final boolean debugActive = false;

	public Transform transform;
	public Transform outputTile;

	private boolean activated = false;
	public boolean moved;

	public MoveTrigger(int x, int y, Transform _outputTile) {
		transform = new Transform(x, y);
		outputTile = _outputTile;
	}

	public void render(Graphics g) {
		g.drawImage(Constants.movetrigger, transform.get_x() * Constants.CellSize,
				transform.get_y() * Constants.CellSize);

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
}
