package Curio.Controllers.Input.AI.Orders;

import Curio.Controllers.ControlPackage;
import Curio.Controllers.Input.AI.Interfaces.MovementOrder;
import Curio.Physics.DynamicObject;
import Curio.Utilities.Math.Vector;

public class BlindMoveOrder implements MovementOrder {
	private Vector currentPosition;
	private Vector targetPosition;
	private Vector distanceVector;
	public float distance;
	public float decelerationDistance;

	private ControlPackage controlPackage;
	private int targetOffset = 10;
	private boolean targetReached = false;

	public BlindMoveOrder(ControlPackage controlPackage, DynamicObject dynamicObject, Vector targetPosition) {
		this.currentPosition = dynamicObject.transform.position;
		this.targetPosition = targetPosition;
		this.controlPackage = controlPackage;
		calculateDistance();
	}

	public BlindMoveOrder setTargetOffset(int targetOffset) {
		this.targetOffset = targetOffset;
		return this;
	}

	@Override
	public void frameUpdate() {
		calculateDistance();
		if (distance <= targetOffset) {
			targetReached = true;
			this.controlPackage.movementDirection.set(0, 0, 0);
		}

		else if (distance > targetOffset) {
			Vector movementControlVector = new Vector(distanceVector.normalize());
			this.controlPackage.movementDirection.set(movementControlVector);
		}
	}

	@Override
	public boolean finished() {
		// TODO Auto-generated method stub
		return targetReached;
	}

	private void calculateDistance() {
		this.distanceVector = targetPosition.distance(currentPosition);
		this.distance = targetPosition.distanceSQRT(currentPosition);
		
		
	}
}
