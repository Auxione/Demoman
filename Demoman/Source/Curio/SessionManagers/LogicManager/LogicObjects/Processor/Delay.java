package Curio.SessionManagers.LogicManager.LogicObjects.Processor;

import Curio.Physics.Time;
import Curio.SessionManagers.LogicManager.LogicMap;
import Curio.SessionManagers.LogicManager.Interfaces.LogicProcessor;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;

public class Delay extends LogicObject implements LogicProcessor {
	private String objectName = "Delay";
	
	private Time goal;
	public boolean activated = false;
	private int delayInMilliseconds;
	private CellCoordinate inputCC;
	private boolean triggerOnce = false;

	public Delay(Transform transform, CellCoordinate inputCC, int delayInMilliseconds) {
		super(null, transform);
		this.delayInMilliseconds = delayInMilliseconds;
		this.inputCC = inputCC;
		this.goal = new Time();
	}

	@Override
	public void update(LogicMap logicMap, Time currentTime) {
		boolean readState = logicMap.getState(inputCC);
		if (readState == true && triggerOnce == false) {
			this.activated = true;
			this.triggerOnce = true;
		}

		else if (readState == false) {
			this.triggerOnce  = false;
		}

		if (activated == true) {
			this.goal.set(currentTime).addMillisSecond(delayInMilliseconds);
			this.activated = false;
		}

		if (goal.getSeconds() < currentTime.getSeconds() && activated == false) {
			logicMap.sendTick(super.cellCoordinate, true);
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return objectName;
	}

	@Override
	public String getCustomInfo() {
		// TODO Auto-generated method stub
		return "DelayTime: " + delayInMilliseconds;
	}

	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		return activated;
	}
}