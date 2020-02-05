package Curio.SessionManagers.LogicManager.LogicObjects.Processor;

import Curio.Physics.Time;
import Curio.SessionManagers.LogicManager.LogicMap;
import Curio.SessionManagers.LogicManager.Interfaces.LogicProcessor;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;

public class PushToSwitch extends LogicObject implements LogicProcessor {
	public boolean state = false;
	public boolean activated = false;
	private boolean triggerOnce = false;
	public CellCoordinate inputCC;

	public PushToSwitch(Transform transform, CellCoordinate inputCC) {
		super(null, transform);
		this.inputCC = inputCC;
	}

	@Override
	public void update(LogicMap logicMap, Time currentTime) {
		boolean readState = logicMap.getState(inputCC);
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
			activated = false;
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "PushToSwitch";
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