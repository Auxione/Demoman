package Curio.SessionManagers.LogicManager.Interfaces;

import Curio.Physics.Time;
import Curio.SessionManagers.LogicManager.LogicMap;

public interface LogicProcessor {
	public void update(LogicMap logicMap,Time currentTime);
}
