package Curio.Tilemap.Logic.Processor;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Functions;
import Curio.Tilemap.Logic.Logic;
import Curio.Utilities.Transform;
import Default.Constants;

public class Delay extends Logic {
	final String debugcode = "<TimedTrigger>:";
	final boolean debugActive = false;

	public Transform transform;
	public Transform outputTile;

	private float goal, timer;
	private boolean activated = false;

	public Delay(int x, int y, Transform _outputTile, int t) {
		transform = new Transform(x, y);
		outputTile = _outputTile;

		timer = (float) (t);
	}

	public void update() {
		if (getState(transform) == true) {
			activated = true;
			goal = Functions.millis() + timer;
		}

		if (startTimer(goal) == true && activated == true) {
			sendTick(outputTile, true);
			activated = false;
		}

	}

	private boolean startTimer(float goal) {
		if (Functions.millis() > goal) {
			return true;
		} else {
			return false;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(transform.get_x() * Constants.CellSize, transform.get_y() * Constants.CellSize, Constants.CellSize,
				Constants.CellSize);
	}

	@Override
	public void keyEvent() {
		// TODO Auto-generated method stub

	}
}