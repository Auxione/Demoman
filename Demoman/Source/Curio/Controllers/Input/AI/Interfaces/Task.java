package Curio.Controllers.Input.AI.Interfaces;

import Curio.Physics.Interfaces.FrameUpdate;

public interface Task extends FrameUpdate {
	public boolean finished();

	public void endTask();
	
	// extend FrameUpdate
	// boolean finished -> return true when task is finished
	// void endTask -> execute to end the current task (may not be needed)
}