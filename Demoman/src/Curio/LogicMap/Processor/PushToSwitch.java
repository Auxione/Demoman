package Curio.LogicMap.Processor;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.LogicMap.Logic;
import Curio.LogicMap.LogicMap;
import Curio.Utilities.Math.Transform;
import Default.Constants;

public class PushToSwitch implements Logic {
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

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(transform.get_x() * Constants.CellSize, transform.get_y() * Constants.CellSize, Constants.CellSize,
				Constants.CellSize);
	}

	@Override
	public void keyEvent() {

	}

	@Override
	public void update(LogicMap logicMap) {
		if (logicMap.getState(transform) == true) {
			state = !state;
			activated = true;
		}

		if (activated == true) {
			logicMap.setState(outputTile, state);
			activated = false;
		}
	}
}