package Curio.SessionManagers.LogicManager.Interfaces;

import Curio.SessionManagers.LogicManager.LogicMap;

public interface LogicTrigger {
	public void onTopEvent(boolean onTop);
	
	public void keyEvent(boolean ActionUse);

	public void update(LogicMap logicMap);
}
