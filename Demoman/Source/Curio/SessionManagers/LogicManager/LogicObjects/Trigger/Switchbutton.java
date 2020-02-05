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
	public boolean activated = false;
	private boolean triggerOnce = false;
	private boolean readState = false;

	public Switchbutton(Transform transform) {
		super(null, transform);
	}

	public void keyEvent() {

	}

	public void update(LogicMap logicMap) {
		if (readState == true && triggerOnce == false) {
			this.activated = true;
			this.triggerOnce = true;
		}

		else if (readState == false) {
			this.triggerOnce = false;
		}

		if (activated == true) {
			state = !state;
			logicMap.setState(super.cellCoordinate, state);
			this.activated = false;
		}
		this.readState = false;
	}

	@Override
	public void onTopEvent(boolean onTop) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyEvent(boolean ActionUse) {
		this.readState = ActionUse;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Switchbutton";
	}

	@Override
	public String getCustomInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		return activated;
	}
}