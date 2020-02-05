package Curio.SessionManagers.LogicManager.LogicObjects.Trigger;

import org.newdawn.slick.Image;

import Curio.SessionManagers.LogicManager.LogicMap;
import Curio.SessionManagers.LogicManager.Interfaces.LogicTrigger;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;
import Default.Constants;

public class MoveTrigger extends LogicObject implements LogicTrigger {

	public Image img = Constants.movetrigger;
	public boolean activated = false;
	public boolean triggerOnce = true;

	public MoveTrigger(Transform transform) {
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
		if (onTop == true && triggerOnce == false) {
			this.activated = true;
			triggerOnce = true;
		} 
		
		else if (onTop == false) {
			triggerOnce = false;
		}

	}

	@Override
	public void keyEvent(boolean ActionUse) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "MoveTrigger";
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
