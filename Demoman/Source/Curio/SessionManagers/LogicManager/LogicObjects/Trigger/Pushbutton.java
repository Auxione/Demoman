package Curio.SessionManagers.LogicManager.LogicObjects.Trigger;

import org.newdawn.slick.Image;

import Curio.SessionManagers.LogicManager.LogicMap;
import Curio.SessionManagers.LogicManager.Interfaces.LogicTrigger;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.Utilities.Math.Transform;
import Default.Constants;

public class Pushbutton extends LogicObject implements LogicTrigger {
	public boolean activated = false;
	public Image img = Constants.pushbutton;

	public Pushbutton(Transform transform) {
		super(null, transform);
	}

	@Override
	public void update(LogicMap logicMap) {
		if (activated == true) {
			logicMap.sendTick(super.cellCoordinate, activated);
			activated = false;
		}
	}

	@Override
	public void onTopEvent(boolean onTop) {
	}

	@Override
	public void keyEvent(boolean ActionUse) {
		activated = ActionUse;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Pushbutton";
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