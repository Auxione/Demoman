package Curio.Controllers.Input.AI.Tasks;

import Curio.Controllers.Input.AI.AI;
import Curio.Controllers.Input.AI.Interfaces.Task;
import Curio.Controllers.Input.AI.Orders.FollowPathOrder;
import Curio.Utilities.Navigation.Path;

public class FollowPathTask implements Task {
	FollowPathOrder FollowPathOrder;
	private boolean endOrder = false;

	public FollowPathTask(AI ai, Path path) {
		this.FollowPathOrder = new FollowPathOrder(ai.getPackage(), ai.dynamicObject, path);
	};

	@Override
	public void frameUpdate() {
		if (endOrder == false) {
			this.FollowPathOrder.frameUpdate();
		}
	}

	@Override
	public boolean finished() {
		return FollowPathOrder.finished() || endOrder;
		// calisirken false false false
		// durdurulmak false true true
		// bitti true false true

	}

	@Override
	public void endTask() {
		this.endOrder = true;
	}
}
