package Curio.Physics.ForceGenerators;

import Curio.Physics.DynamicObject;
import Curio.Utilities.Math.Vector;

public class Drag implements ForceGenerators {

	float dragCoeff;
	/** Holds the velocity drag coefficient. */
	float k1;
	/** Holds the velocity squared drag coefficient. */
	float k2;

	public Drag(float k1, float k2) {
		this.k2 = k2;
		this.k1 = k1;
	}

	@Override
	public Vector getForce(DynamicObject dynamicObject) {
		Vector force = new Vector();
		// Calculate the total drag coefficient.
		dragCoeff = force.magnitude();
		dragCoeff = k1 * dragCoeff + k2 * dragCoeff * dragCoeff;
		// Calculate the final force and apply it.
		force.normalize();
		force.multiply(-dragCoeff);
		return force;
	}
}
