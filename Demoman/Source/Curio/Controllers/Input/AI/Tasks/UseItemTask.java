package Curio.Controllers.Input.AI.Tasks;

import Curio.Controllers.ControlPackage;
import Curio.Controllers.Input.AI.Interfaces.Task;
import Curio.Controllers.Input.AI.Orders.UseItemOrder;
import Curio.SessionManagers.ItemManager.Inventory;
import Curio.SessionManagers.ItemManager.Item;

public class UseItemTask implements Task {
	private UseItemOrder useItemOrder;
	private boolean endOrder = false;

	public UseItemTask(ControlPackage controlPackage, Inventory inventory, Item item) {
		this.useItemOrder = new UseItemOrder(controlPackage, inventory, item);
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
