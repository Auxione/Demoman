package Curio.Controllers.Input.AI.Tasks;

import Curio.Controllers.Input.AI.AI;
import Curio.Controllers.Input.AI.Interfaces.Task;
import Curio.Controllers.Input.AI.Orders.BlindUseItemOrder;

public class BlindUseItemTask implements Task {
	private BlindUseItemOrder useItemOrder;
	private boolean endOrder = false;

	public BlindUseItemTask(AI ai) {
		this.useItemOrder = new BlindUseItemOrder(ai.getPackage());
	};

	@Override
	public void frameUpdate() {
		if (endOrder == false) {
			this.useItemOrder.frameUpdate();
		}
	}

	@Override
	public boolean finished() {
		return useItemOrder.finished() || endOrder;
		// calisirken false false false
		// durdurulmak false true true
		// bitti true false true

	}

	@Override
	public void endTask() {
		this.endOrder = true;
	}
}
