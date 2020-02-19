package Curio.Controllers.Input.AI.Tasks;

import Curio.GameObject;
import Curio.Controllers.ControlPackage;
import Curio.Controllers.Input.AI.Interfaces.Task;
import Curio.Controllers.Input.AI.Orders.AimToObjectOrder;
import Curio.Physics.DynamicObject;

public class AimToObjectTask implements Task {
	private AimToObjectOrder aimToObjectOrder;

	private boolean deactivate = false;

	public AimToObjectTask(ControlPackage controlPackage, DynamicObject dynamicObject, GameObject gameObject) {
		this.aimToObjectOrder = new AimToObjectOrder(controlPackage, dynamicObject, gameObject);
	}

	@Override
	public void frameUpdate() {
		if (deactivate == false) {
			this.aimToObjectOrder.frameUpdate();
		}
	}

	@Override
	public boolean finished() {
		// TODO Auto-generated method stub
		return this.deactivate;
	}

	@Override
	public void endTask() {
		this.deactivate = true;
	}
}
