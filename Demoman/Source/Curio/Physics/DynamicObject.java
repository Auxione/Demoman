package Curio.Physics;

import java.util.ArrayList;

import Curio.Functions;
import Curio.GameObject;
import Curio.Physics.ForceGenerators.Drag;
import Curio.Physics.ForceGenerators.ForceGenerators;
import Curio.Physics.Interfaces.FixedUpdate;
import Curio.Utilities.Math.Vector;
import Curio.Utilities.Math.Geometry.Shape;

public class DynamicObject extends GameObject implements FixedUpdate {
	public ArrayList<ForceGenerators> forceGenerators = new ArrayList<ForceGenerators>();

	private Vector position;
	public Vector velocity;
	public Vector acceleration;
	private Vector forceAccum;

	private float damping = 1.0f;
	private float inverseMass = 1.0f;
	public Shape shape;

	public DynamicObject(Shape shape) {
		this.shape = shape;
		this.acceleration = new Vector();
		this.velocity = new Vector();
		this.forceAccum = new Vector();
		this.position = super.transform.position;
		forceGenerators.add(new Drag(0.1f, 0.01f));
	}

	public void fixedUpdate(int delta) {
		float deltaTime = delta / 1000.0f;

		if (forceGenerators.isEmpty() == false) {
			for (ForceGenerators fg : forceGenerators) {
				forceAccum.add(fg.getForce(this));
			}
		}
		// roundForceToZero(1.0f);

		this.acceleration.x = this.forceAccum.x * inverseMass;
		this.acceleration.y = this.forceAccum.y * inverseMass;
		this.acceleration.z = this.forceAccum.z * inverseMass;

		this.velocity.x = this.velocity.x * damping + this.acceleration.x * deltaTime;
		this.velocity.y = this.velocity.y * damping + this.acceleration.y * deltaTime;
		this.velocity.z = this.velocity.z * damping + this.acceleration.z * deltaTime;

		this.position.x += this.velocity.x * deltaTime;
		this.position.y += this.velocity.y * deltaTime;
		this.position.z += this.velocity.z * deltaTime;

		roundVectorToZero(velocity, 1.0f);
		updateCellPosition();
		clearForceAccumulator();
	}

	private void roundVectorToZero(Vector vector, float min) {
		if (vector.x <= min && vector.x >= -min) {
			vector.x = 0.0f;
		}
		if (vector.y <= min && vector.y >= -min) {
			vector.y = 0.0f;
		}
		if (vector.z <= min && vector.z >= -min) {
			vector.z = 0.0f;
		}
	}

	private void clearForceAccumulator() {
		this.forceAccum.set(0, 0, 0);
	}

	private void updateCellPosition() {
		super.cellCoordinate = Functions.worldPostoCellPosition(super.transform.position);
	}

	public void addForceGenerator(ForceGenerators forceGenerators) {
		this.forceGenerators.add(forceGenerators);
	}

	public void addForce(Vector force) {
		this.forceAccum.add(force);
	}

	public DynamicObject setMass(float mass) {
		this.inverseMass = 1.0f / mass;
		return this;
	}

	public DynamicObject setDamping(float damping) {
		this.damping = damping;
		return this;
	}
}