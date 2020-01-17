package Curio.Physics;

import java.util.ArrayList;

import Curio.Functions;
import Curio.GameObject.GameObject;
import Curio.Utilities.Math.Transform;
import Curio.Utilities.Math.Vector;

public class DynamicObject {
	public static ArrayList<DynamicObject> dynamicObjectList = new ArrayList<DynamicObject>();

	public GameObject gameObject;

	public Vector Velocity;
	public Vector Acceleration;
	private float AccelOffset = 1000;

	float size;

	public DynamicObject(GameObject gameObject) {
		this.gameObject = gameObject;

		this.Velocity = new Vector(0, 0);
		this.Acceleration = new Vector(0, 0);

		dynamicObjectList.add(this);
	}

	public DynamicObject setSize(float size) {
		this.size = size;
		return this;
	}

	public void updatePhysics(float deltaTime) {
		deltaTime /= 1000.0f;
		Velocity.x += Acceleration.x * deltaTime;
		Velocity.y += Acceleration.y * deltaTime;

		gameObject.transform.position.x += Velocity.x * deltaTime;
		gameObject.transform.position.y += Velocity.y * deltaTime;

		updateCellPosition();
		updateWithfriction();
		// System.out.println(Velocity.x + " : " + Velocity.y);
	}

	private void updateWithfriction() {
		if (Velocity.magnitude() < 1f) {
			Velocity.multiply(0);
		} else if (Velocity.magnitude() > 1f) {
			Velocity.multiply(0.96f);
		}
	}

	void updateCellPosition() {
		gameObject.cellCoordinate = Functions.worldPostoCellPosition(gameObject.transform);
	}

	public void addAcceleration(float x, float y) {
		Acceleration.x = x * AccelOffset;
		Acceleration.y = y * AccelOffset;
	}

	public void setPosition(float x, float y) {
		gameObject.transform.position.x = x;
		gameObject.transform.position.y = y;
	}

	public void setPosition(Transform tr) {
		gameObject.transform.position.x = tr.position.x;
		gameObject.transform.position.y = tr.position.y;
	}
}