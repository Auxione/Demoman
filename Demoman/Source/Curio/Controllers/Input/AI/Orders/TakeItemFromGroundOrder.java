package Curio.Controllers.Input.AI.Orders;

import Curio.Controllers.ControlPackage;
import Curio.Controllers.Input.AI.Interfaces.ActionOrder;
import Curio.Physics.DynamicObject;
import Curio.SessionManagers.ItemManager.ItemManager;
import Curio.Utilities.CellCoordinate;

public class TakeItemFromGroundOrder implements ActionOrder {
	private CellCoordinate currentCellCoordinate;
	private ControlPackage controlPackage;
	private ItemManager itemManager;

	private boolean itemTaken = false;

	public TakeItemFromGroundOrder(ControlPackage controlPackage, DynamicObject dynamicObject,
			ItemManager itemManager) {
		this.currentCellCoordinate = dynamicObject.cellCoordinate;
		this.controlPackage = controlPackage;
		this.itemManager = itemManager;
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
