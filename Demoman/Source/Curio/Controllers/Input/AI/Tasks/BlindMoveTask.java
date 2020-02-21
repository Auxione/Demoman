package Curio.Controllers.Input.AI.Tasks;

import Curio.Controllers.Input.AI.AI;
import Curio.Controllers.Input.AI.Interfaces.Task;
import Curio.Controllers.Input.AI.Orders.BlindMoveOrder;
import Curio.Utilities.Math.Vector;

public class BlindMoveTask implements Task {
	BlindMoveOrder blindMoveOrder;
	private boolean endOrder = false;

	public BlindMoveTask(AI ai,Vector targetPosition) {
		this.blindMoveOrder = new BlindMoveOrder(ai.getPackage(), ai.dynamicObject, targetPosition);
	};

	@Override
	public void frameUpdate() {
		if (endOrder == false) {
			this.blindMoveOrder.frameUpdate();
		}
	}

	@Override
	public boolean finished() {
		return blindMoveOrder.finished() || endOrder;
		//calisirken	false false	false
		//durdurulmak 	false true	true
		//bitti			true  false	true
		
	}

	@Override
	public void endTask() {
		this.endOrder = true;
	}
}
