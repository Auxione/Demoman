package Curio.Controllers;

import Curio.Physics.DynamicObject;
import Curio.Utilities.Math.Vector;

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
			Vector v = new Vector(controlPackage.movementDirection).multiply(300.0f);
			dynamicObject.addForce(v);
			dynamicObject.transform.rotation.setAngle(controlPackage.rotation);
		}
	}
}