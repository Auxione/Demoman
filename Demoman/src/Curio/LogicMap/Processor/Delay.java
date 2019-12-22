package Curio.LogicMap.Processor;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Functions;
import Curio.LogicMap.Logic;
import Curio.LogicMap.LogicMap;
import Curio.Utilities.Math.Transform;
import Default.Constants;

public class Delay implements Logic {
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

	@Override
	public void update(LogicMap logicMap) {
		if (logicMap.getState(transform) == true) {
			activated = true;
			goal = Functions.millis() + timer;
		}

		if (startTimer(goal) == true && activated == true) {
			logicMap.sendTick(outputTile, true);
			activated = false;
		}
	}
}