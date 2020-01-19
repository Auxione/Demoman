package Curio.SessionManagers.LogicManager.LogicObjects.Processor;

import Curio.Physics.Time;
import Curio.SessionManagers.LogicManager.LogicMap;
import Curio.SessionManagers.LogicManager.Interfaces.LogicProcessor;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;

public class PushToSwitch extends LogicObject implements LogicProcessor {
	final String debugcode = "<PushToSwitch>:";
	final boolean debugActive = false;

	public boolean state = false;
	public boolean activated = false;
	private boolean triggerOnce = false;
	public CellCoordinate outputCC;

	public PushToSwitch(Transform transform, CellCoordinate outputCC) {
		super(null, transform);
		this.outputCC = outputCC;
	}

	@Override
	public void update(LogicMap logicMap, Time currentTime) {
		boolean readState = logicMap.getState(super.cellCoordinate);
		if (readState == true && triggerOnce == false) {
			this.activated = true;
			this.triggerOnce = true;
		}

		else if (readState == false) {
			this.triggerOnce = false;
		}

		if (activated == true) {
			state = !state;
			logicMap.setState(outputCC, state);
			activated = false;
		}
	}
}