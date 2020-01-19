package Curio.SessionManagers.LogicManager.LogicObjects.Trigger;

import org.newdawn.slick.Image;

import Curio.SessionManagers.LogicManager.LogicMap;
import Curio.SessionManagers.LogicManager.Interfaces.LogicTrigger;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;
import Default.Constants;

public class MoveTrigger extends LogicObject implements LogicTrigger {
	final String debugcode = "<MoveTrigger>: ";
	final boolean debugActive = false;

	public Image img = Constants.movetrigger;
	public boolean activated = false;
	public boolean triggerOnce = true;
	public CellCoordinate outputCC;

	public MoveTrigger(Transform transform, CellCoordinate outputCC) {
		super(null, transform);
		this.outputCC = outputCC;
	}

	@Override
	public void update(LogicMap logicMap) {
		if (activated == true) {
			logicMap.sendTick(outputCC, activated);
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
}
