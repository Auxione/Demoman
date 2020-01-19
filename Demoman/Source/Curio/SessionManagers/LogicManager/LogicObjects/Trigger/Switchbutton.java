package Curio.SessionManagers.LogicManager.LogicObjects.Trigger;

import org.newdawn.slick.Image;

import Curio.SessionManagers.LogicManager.LogicMap;
import Curio.SessionManagers.LogicManager.Interfaces.LogicTrigger;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;
import Default.Constants;

public class Switchbutton extends LogicObject implements LogicTrigger {
	final String debugcode = "<Pushbutton>:";
	final boolean debugActive = false;

	public Image switchoff = Constants.switchoff;
	public Image switchon = Constants.switchon;

	public boolean state = false;
	public boolean Activated = false;
	public CellCoordinate outputCC;

	public Switchbutton(Transform transform, CellCoordinate outputCC) {
		super(null, transform);
		this.outputCC = outputCC;
	}

	public void keyEvent() {

	}

	public void update(LogicMap logicMap) {
		if (Activated == true) {
			logicMap.setState(outputCC, state);
			Activated = false;
		}
	}

	@Override
	public void onTopEvent(boolean onTop) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyEvent(boolean ActionUse) {
		if (ActionUse == true) {
			state = !state;
			Activated = true;
		}
	}
}