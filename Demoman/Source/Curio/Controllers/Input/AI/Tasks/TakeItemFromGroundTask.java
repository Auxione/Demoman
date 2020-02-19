package Curio.Controllers.Input.AI.Tasks;

import Curio.Controllers.ControlPackage;
import Curio.Controllers.Input.AI.Interfaces.Task;
import Curio.Controllers.Input.AI.Orders.TakeItemFromGroundOrder;
import Curio.Physics.DynamicObject;
import Curio.SessionManagers.ItemManager.ItemManager;

public class TakeItemFromGroundTask implements Task {
	private TakeItemFromGroundOrder takeItemFromGroundOrder;
	private boolean endOrder = false;

	public TakeItemFromGroundTask(ControlPackage controlPackage, DynamicObject dynamicObject, ItemManager itemManager) {
		this.takeItemFromGroundOrder = new TakeItemFromGroundOrder(controlPackage, dynamicObject, itemManager);
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
