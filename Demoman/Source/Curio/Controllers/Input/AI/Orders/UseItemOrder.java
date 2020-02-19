package Curio.Controllers.Input.AI.Orders;

import Curio.Controllers.ControlPackage;
import Curio.Controllers.Input.AI.Interfaces.ActionOrder;
import Curio.SessionManagers.ItemManager.Inventory;
import Curio.SessionManagers.ItemManager.Item;

public class UseItemOrder implements ActionOrder {
	private ControlPackage controlPackage;

	private boolean itemUsed = false;

	public UseItemOrder(ControlPackage controlPackage, Inventory inventory, Item item) {
		this.controlPackage = controlPackage;
		inventory.searchAndSetIndex(item);
	}

	@Override
	public void frameUpdate() {
		if (this.itemUsed == false) {
			this.controlPackage.ActionUseItem = true;
			this.itemUsed = true;
		}
	}

	@Override
	public boolean finished() {
		// TODO Auto-generated method stub
		return itemUsed;
	}
}
