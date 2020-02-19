package Curio.Controllers.Input.AI.Orders;

import Curio.GameObject;
import Curio.Controllers.ControlPackage;
import Curio.Controllers.Input.AI.Interfaces.LookDirectionOrder;
import Curio.Physics.DynamicObject;
import Curio.Utilities.Math.Vector;

public class AimToObjectOrder implements LookDirectionOrder {
	private Vector currentPosition;
	private Vector targetPosition;

	private ControlPackage controlPackage;

	public AimToObjectOrder(ControlPackage controlPackage, DynamicObject dynamicObject, GameObject gameObject) {
		this.currentPosition = dynamicObject.transform.position;
		this.targetPosition = gameObject.transform.position;
		this.controlPackage = controlPackage;
	}

	@Override
	public void frameUpdate() {
		this.controlPackage.rotation.setAngle(currentPosition.sub(targetPosition).getAngleInDegreesMAPPED());
	}

	@Override
	public boolean finished() {
		// TODO Auto-generated method stub
		return false;
	}

}
