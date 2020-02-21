package Curio.Controllers.Input.AI.Tasks;

import Curio.Controllers.Input.AI.AI;
import Curio.Controllers.Input.AI.Interfaces.Task;
import Curio.Controllers.Input.AI.Orders.BlindDropItemOrder;
import Curio.Controllers.Input.AI.Orders.BlindUseItemOrder;

public class BlindDropItemTask implements Task{
	private BlindDropItemOrder blindDropItemOrder;
	private boolean endOrder = false;

	public BlindDropItemTask(AI ai) {
		this.blindDropItemOrder = new BlindDropItemOrder(ai.getPackage());
	};

	@Override
	public void frameUpdate() {
		if (endOrder == false) {
			this.blindDropItemOrder.frameUpdate();
		}
	}

	@Override
	public boolean finished() {
		return blindDropItemOrder.finished() || endOrder;
		// calisirken false false false
		// durdurulmak false true true
		// bitti true false true

	}

	@Override
	public void endTask() {
		this.endOrder = true;
	}
}
