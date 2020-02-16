package Curio.Controllers;

import Curio.Physics.DynamicObject;

public class ObjectController {
	public ControlPackage controlPackage;
	private DynamicObject dynamicObject = null;

	public ObjectController(DynamicObject dynamicObject) {
		this.dynamicObject = dynamicObject;
		this.controlPackage = new ControlPackage();
	}

	public ObjectController setControlPackage(ControlPackage controlPackage) {
		this.controlPackage = controlPackage;
		return this;
	}

	public void update() {
		if (dynamicObject != null) {
			dynamicObject.setAcceleration(controlPackage.movementDirection.multiply(10000.0f));
			dynamicObject.transform.rotation.setAngle(controlPackage.rotation);
		}
	}
}