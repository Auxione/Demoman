package Curio.Controllers.Input.AI.Orders;

import Curio.Controllers.ControlPackage;
import Curio.Controllers.Input.AI.Interfaces.ActionOrder;

public class BlindDropItemOrder implements ActionOrder{
	private ControlPackage controlPackage;

	private boolean itemDropped = false;

	public BlindDropItemOrder(ControlPackage controlPackage) {
		this.controlPackage = controlPackage;
	}

	@Override
	public void frameUpdate() {
		if (this.itemDropped == false) {
			this.controlPackage.ActionDrop = true;
			this.itemDropped = true;
		}
	}

	@Override
	public boolean finished() {
		// TODO Auto-generated method stub
		return itemDropped;
	}
}
