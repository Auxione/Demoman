package Curio.Controllers.Input.AI.Interfaces;

import Curio.Physics.Interfaces.FrameUpdate;

public interface MovementOrder extends FrameUpdate {
	public boolean finished();

	// extend Frame
	// Update boolean finished -> return true when task is finished

}


