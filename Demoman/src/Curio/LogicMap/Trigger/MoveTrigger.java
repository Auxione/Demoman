package Curio.LogicMap.Trigger;

import org.newdawn.slick.Graphics;

import Curio.LogicMap.Logic;
import Curio.LogicMap.LogicMap;
import Curio.Utilities.Math.Transform;
import Default.Constants;

public class MoveTrigger implements Logic {
	final String debugcode = "<MoveTrigger>: ";
	final boolean debugActive = false;

	public Transform transform;
	public Transform outputTile;

	public boolean activated = false;

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
	}

	@Override
	public void update(LogicMap logicMap) {
		if (activated == true) {
			logicMap.sendTick(outputTile, activated);
			activated = false;
		}
	}
}
