package Curio.Physics;

import java.util.ArrayList;

import Curio.Functions;
import Curio.GameObject;
import Curio.Physics.ForceGenerators.Drag;
import Curio.Physics.ForceGenerators.ForceGenerators;
import Curio.Physics.Interfaces.FixedUpdate;
import Curio.Utilities.Math.Vector;
import Curio.Utilities.Math.Geometry.Circle;
import Curio.Utilities.Math.Geometry.Rectangle;
import Curio.Utilities.Math.Geometry.Shape;

public class DynamicObject extends GameObject implements FixedUpdate {
	public ArrayList<ForceGenerators> forceGenerators = new ArrayList<ForceGenerators>();

	private Vector centerOfMass;
	private float momentOfInertia;

	private float mass = 1.0f;
	private float inverseMass = 1.0f / mass;

	private Vector position;
	public Vector velocity;
	public Vector acceleration;
	private Vector accumulatedForce;

	public float angle;
	public float angularVelocity;
	public float angularAcceleration;
	private float accumulatedTorque;

	private float damping = 1.0f;
	private float Angulardamping = 1.0f;

	public Shape shape;

	private float velocityLimit = 0.0f;

	private boolean torqueActive = true;

	/*
	 * #1: accumulate forces and torques for object.
	 * 
	 * #2: convert them into linear and angular acceleration for object.
	 * 
	 * #3: compute new linear and angular velocity and position for object.
	 */
	public DynamicObject(Shape shape) {
		this.shape = shape;

		this.position = super.transform.position;
		this.velocity = new Vector();
		this.acceleration = new Vector();
		this.accumulatedForce = new Vector();

		this.angle = super.transform.rotation.degrees();
		this.angularVelocity = 0.0f;
		this.angularAcceleration = 0.0f;
		this.accumulatedTorque = 0.0f;

		this.centerOfMass = new Vector(this.position);
		this.momentOfInertia = CalculateMoI(shape);

		this.forceGenerators.add(new Drag(0.1f, 0.01f));
	}

	public void fixedUpdate(int delta) {
		float deltaTime = delta / 1000.0f;

		if (forceGenerators.isEmpty() == false) {
			for (ForceGenerators fg : forceGenerators) {
				applyForceToCoM(fg.getForce(this));
			}
		}
		// roundForceToZero(1.0f);

		this.acceleration.x = this.accumulatedForce.x * inverseMass;
		this.acceleration.y = this.accumulatedForce.y * inverseMass;
		this.acceleration.z = this.accumulatedForce.z * inverseMass;
		this.velocity.x = this.velocity.x * damping + this.acceleration.x * deltaTime;
		this.velocity.y = this.velocity.y * damping + this.acceleration.y * deltaTime;
		this.velocity.z = this.velocity.z * damping + this.acceleration.z * deltaTime;

		limitVelocity();

		this.position.x += this.velocity.x * deltaTime;
		this.position.y += this.velocity.y * deltaTime;
		this.position.z += this.velocity.z * deltaTime;

		roundVectorToZero(velocity, 1.0f);
		// roundVectorToZero(acceleration, 1.0f);

		if (torqueActive == true) {
			this.angularAcceleration = this.accumulatedTorque / momentOfInertia;
			this.angularVelocity = this.angularVelocity * Angulardamping + this.angularAcceleration * deltaTime;
			this.angle = this.angle + this.angularVelocity * deltaTime;

			super.transform.rotation.setAngle(angle);

			// roundValueToZero(angularVelocity, 1.0f);
			// roundValueToZero(angularAcceleration, 1.0f);
		}

		updateCellPosition();
		clearTorqueAndForce();
	}

	private void roundValueToZero(float value, float min) {
		if (value <= min && value >= -min) {
			value = 0.0f;
		}
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

	private float CalculateMoI(Shape shape) {
		if (shape instanceof Circle) {
			Circle c = (Circle) shape;
			return (float) (Math.PI * Math.pow(c.radius, 4) / 4);
		}

		else if (shape instanceof Rectangle) {
			Rectangle r = (Rectangle) shape;
			if (r.width > r.height) {
				return (float) (Math.pow(r.height, 3) * r.width / 12);
			} else if (r.width <= r.height) {
				return (float) (Math.pow(r.width, 3) * r.height / 12);
			}
		}
		return 0.0f;
	}

	private float calculateTorque(Vector point, Vector force) {
		Vector distancetoCom = point.distance(centerOfMass).add(1250, 0, 0);
		Vector torqueVector = distancetoCom.crossProduct(force);
		return torqueVector.z;

	}

	private void limitVelocity() {
		if (velocityLimit != 0.0f) {
			if (this.velocity.magnitude() >= velocityLimit) {
				this.velocity.setMagnitude(velocityLimit);
			}
		}
	}

	public DynamicObject setVelocityLimit(float velocity) {
		if (this.velocityLimit != velocity) {
			this.velocityLimit = velocity;
		}
		return this;
	}

	public void applyForceToPoint(Vector point, Vector force) {
		this.accumulatedTorque = this.accumulatedTorque + calculateTorque(point, force);
		this.accumulatedForce.add(force);
	}

	public void applyForceToCoM(Vector force) {
		applyForceToPoint(centerOfMass, force);
	}

	private void clearTorqueAndForce() {
		this.accumulatedForce.set(0, 0, 0);
		this.accumulatedTorque = 0.0f;
	}

	private void updateCellPosition() {
		super.cellCoordinate = Functions.worldPostoCellPosition(super.transform.position);
	}

	public void addForceGenerator(ForceGenerators forceGenerators) {
		this.forceGenerators.add(forceGenerators);
	}

	public void removeForceGenerator(ForceGenerators forceGenerators) {
		this.forceGenerators.remove(forceGenerators);
	}

	public DynamicObject setCenterOfMassOffset(Vector point) {
		this.centerOfMass.add(point);
		return this;
	}

	public DynamicObject setMass(float mass) {
		this.mass = mass;
		this.inverseMass = 1.0f / mass;
		return this;
	}

	public DynamicObject setTorqueCalculation(boolean torqueActive) {
		this.torqueActive = torqueActive;
		return this;
	}

	public DynamicObject setMomentOfInertia(float momentOfInertia) {
		this.momentOfInertia = momentOfInertia;
		return this;
	}

	public DynamicObject setDamping(float damping) {
		this.damping = damping;
		return this;
	}

	public DynamicObject setAngularDamping(float angulardamping) {
		this.Angulardamping = angulardamping;
		return this;
	}
}