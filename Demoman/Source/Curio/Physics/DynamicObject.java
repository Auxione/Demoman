package Curio.Physics;

import Curio.Functions;
import Curio.GameObject;
import Curio.Physics.Interfaces.FixedUpdate;
import Curio.Utilities.Math.Vector;
import Curio.Utilities.Shapes.Shape;

public class DynamicObject extends GameObject implements FixedUpdate {
	public Vector velocity;
	public Vector acceleration;
	private Vector forceAccum;

	private float damping = 1.0f;
	private float mass = 1.0f;
	public Shape shape;

	public DynamicObject(Shape shape) {
		this.shape = shape;
		this.acceleration = new Vector(0, 0, 0);
		this.velocity = new Vector(0, 0, 0);
		this.forceAccum = new Vector(0, 0, 0);
	}

	public void fixedUpdate(int delta) {
		float deltaTime = delta / 1000.0f;

		this.acceleration.addScaledVector(forceAccum, 1.0f / mass);

		this.velocity.multiply(damping);
		this.velocity.addScaledVector(acceleration, deltaTime);

		super.transform.position.addScaledVector(velocity, deltaTime);

		updateCellPosition();
		clearForceAccumulator();
	}

	private void clearForceAccumulator() {
		forceAccum.set(0, 0, 0);
	}

	void updateCellPosition() {
		super.cellCoordinate = Functions.worldPostoCellPosition(super.transform.position);
	}

	public void addForce(Vector force) {
		this.forceAccum.add(force);
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public void setDamping(float damping) {
		this.damping = damping;
	}

	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}

	public void setAcceleration(Vector acceleration) {
		this.acceleration = acceleration;
	}

	public void setPosition(float x, float y, float z) {
		super.transform.position.set(x, y, z);
	}
}