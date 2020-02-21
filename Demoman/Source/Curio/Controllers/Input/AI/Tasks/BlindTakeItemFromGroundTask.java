package Curio.Controllers.Input.AI.Tasks;

import Curio.Controllers.Input.AI.AI;
import Curio.Controllers.Input.AI.Interfaces.Task;
import Curio.Controllers.Input.AI.Orders.BlindTakeItemFromGroundOrder;

public class BlindTakeItemFromGroundTask implements Task {
	private BlindTakeItemFromGroundOrder takeItemFromGroundOrder;
	private boolean endOrder = false;

	public  BlindTakeItemFromGroundTask(AI ai) {
		this.takeItemFromGroundOrder = new BlindTakeItemFromGroundOrder(ai.getPackage());
	};

	@Override
	public void frameUpdate() {
		if (endOrder == false) {
			this.takeItemFromGroundOrder.frameUpdate();
		}
	}

	@Override
	public boolean finished() {
		return takeItemFromGroundOrder.finished() || endOrder;
		// calisirken false false false
		// durdurulmak false true true
		// bitti true false true

	}

	@Override
	public void endTask() {
		this.endOrder = true;
	}
}
