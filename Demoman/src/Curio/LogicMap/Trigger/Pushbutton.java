package Curio.LogicMap.Trigger;

import org.newdawn.slick.Graphics;

import Curio.LogicMap.Logic;
import Curio.LogicMap.LogicMap;
import Curio.Utilities.Math.Transform;
import Default.Constants;

public class Pushbutton implements Logic {

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
	public void update(LogicMap logicMap) {
		if (activated == true) {
			logicMap.sendTick(outputTile, activated);
			activated = false;
		}
	}

	public void render(Graphics g) {
		g.drawImage(Constants.pushbutton, transform.get_x() * Constants.CellSize,
				transform.get_y() * Constants.CellSize);
	}

}