package Curio.Controllers.Input.AI.Orders;

import Curio.Controllers.ControlPackage;
import Curio.Controllers.Input.AI.Interfaces.MovementOrder;
import Curio.Physics.DynamicObject;
import Curio.Utilities.Math.Vector;
import Curio.Utilities.Navigation.Path;

public class FollowPathOrder implements MovementOrder {
	private Vector currentPosition;
	private Vector targetPosition;
	private Path path;
	private Vector distanceVector;
	public float distance;

	private ControlPackage controlPackage;
	private int targetOffset = 10;
	private boolean targetReached = false;
	private boolean pathCompleted = false;

	public FollowPathOrder(ControlPackage controlPackage, DynamicObject dynamicObject, Path path) {
		this.currentPosition = dynamicObject.transform.position;
		this.path = path;
		this.targetPosition = path.getFirstNode();
		this.controlPackage = controlPackage;
		calculateDistance();
	}

	public FollowPathOrder setTargetOffset(int targetOffset) {
		this.targetOffset = targetOffset;
		return this;
	}

	@Override
	public void frameUpdate() {
		calculateDistance();
		if (distance <= targetOffset) {
			this.targetReached = true;
			if (this.targetReached == true) {
				if (path.nextNode(targetPosition) != null) {
					targetPosition = path.nextNode(targetPosition);
					this.targetReached = false;
				}

				else if (path.nextNode(targetPosition) == null) {
					this.controlPackage.movementDirection.set(0, 0, 0);
					this.pathCompleted = true;
				}
			}
		}

		else if (distance > targetOffset) {
			Vector movementControlVector = new Vector(distanceVector.normalize());
			this.controlPackage.movementDirection.set(movementControlVector);
		}
	}

	@Override
	public boolean finished() {
		// TODO Auto-generated method stub
		return pathCompleted;
	}

	private void calculateDistance() {
		this.distanceVector = targetPosition.distance(currentPosition);
		this.distance = targetPosition.distanceSQRT(currentPosition);
	}
}
