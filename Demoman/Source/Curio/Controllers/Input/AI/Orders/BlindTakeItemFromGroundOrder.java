package Curio.Controllers.Input.AI.Orders;

import Curio.Controllers.ControlPackage;
import Curio.Controllers.Input.AI.Interfaces.ActionOrder;
import Curio.Physics.DynamicObject;
import Curio.SessionManagers.ItemManager.ItemManager;
import Curio.Utilities.CellCoordinate;

public class BlindTakeItemFromGroundOrder implements ActionOrder {
	private ControlPackage controlPackage;

	private boolean itemTaken = false;

	public BlindTakeItemFromGroundOrder(ControlPackage controlPackage) {
		this.controlPackage = controlPackage;
	}

	@Override
	public void frameUpdate() {
		if (this.itemTaken == false) {
			this.controlPackage.ActionTake = true;
			this.itemTaken = true;
		}
	}

	@Override
	public boolean finished() {
		// TODO Auto-generated method stub
		return itemTaken;
	}
}
