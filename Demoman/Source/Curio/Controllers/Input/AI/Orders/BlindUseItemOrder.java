package Curio.Controllers.Input.AI.Orders;

import Curio.Controllers.ControlPackage;
import Curio.Controllers.Input.AI.Interfaces.ActionOrder;

public class BlindUseItemOrder implements ActionOrder {
	private ControlPackage controlPackage;

	private boolean itemUsed = false;

	public BlindUseItemOrder(ControlPackage controlPackage) {
		this.controlPackage = controlPackage;
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
