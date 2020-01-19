package Curio.SessionManagers.LogicManager.LogicObjects.Processor;

import Curio.Physics.Time;
import Curio.SessionManagers.LogicManager.LogicMap;
import Curio.SessionManagers.LogicManager.Interfaces.LogicProcessor;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;

public class Delay extends LogicObject implements LogicProcessor {
	final String debugcode = "<TimedTrigger>:";
	final boolean debugActive = false;

	private Time goal;
	public boolean activated = false;
	private int delayInSeconds;
	private CellCoordinate outputCC;
	private boolean triggerOnce = false;

	public Delay(Transform transform, CellCoordinate outputCC, int delayInSeconds) {
		super(null, transform);
		this.delayInSeconds = delayInSeconds;
		this.outputCC = outputCC;
		this.goal = new Time();
	}

	@Override
	public void update(LogicMap logicMap, Time currentTime) {
		boolean readState = logicMap.getState(super.cellCoordinate);
		if (readState == true && triggerOnce == false) {
			this.activated = true;
			this.triggerOnce = true;
		}

		else if (readState == false) {
			this.triggerOnce  = false;
		}

		if (activated == true) {
			this.goal.set(currentTime).addSecond(delayInSeconds);
			this.activated = false;
		}

		if (goal.getSeconds() < currentTime.getSeconds() && activated == false) {
			logicMap.sendTick(outputCC, true);
		}
	}
}