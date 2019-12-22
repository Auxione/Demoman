package Curio.LogicMap.Trigger;

import org.newdawn.slick.Graphics;

import Curio.LogicMap.Logic;
import Curio.LogicMap.LogicMap;
import Curio.Utilities.Math.Transform;
import Default.Constants;

public class Switchbutton implements Logic {
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

	public void update(LogicMap logicMap) {
		if (Activated == true) {
			logicMap.setState(outputTile, state);
			Activated = false;
		}
	}
}