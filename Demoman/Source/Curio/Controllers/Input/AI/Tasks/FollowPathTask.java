package Curio.Controllers.Input.AI.Tasks;

import Curio.Controllers.ControlPackage;
import Curio.Controllers.Input.AI.Interfaces.Task;
import Curio.Controllers.Input.AI.Orders.FollowPathOrder;
import Curio.Physics.DynamicObject;
import Curio.Utilities.Navigation.Path;

public class FollowPathTask implements Task {
	FollowPathOrder FollowPathOrder;
	private boolean endOrder = false;

	public FollowPathTask(ControlPackage controlPackage, DynamicObject dynamicObject, Path path) {
		this.FollowPathOrder = new FollowPathOrder(controlPackage, dynamicObject, path);
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
