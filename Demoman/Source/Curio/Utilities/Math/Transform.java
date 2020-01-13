package Curio.Utilities.Math;

import static java.lang.Math.sqrt;

import java.io.Serializable;

public class Transform implements Serializable {
	public Quaternion quaternion;
	public Vector position;

	// needs quaternion class to rotation
	public Transform() {
		this.position = new Vector(0, 0, 0);
	}

	public Transform(float x, float y, float z) {
		this.position = new Vector(x, y, z);
		this.quaternion = new Quaternion(0, 0, 0, 0);
	}

	public Transform(float x, float y, float z, Quaternion quaternion) {
		this.position = new Vector(x, y, z);
		this.quaternion = quaternion;
	}

	public Transform(Transform tr) {
		this.position = tr.position;
		this.quaternion = tr.quaternion;
	}

	public boolean equals(Transform transform) {
		if (transform.position.x == this.position.x && transform.position.y == this.position.y
				&& transform.position.z == this.position.z) {
			return true;
		} else {
			return false;
		}
	}

	public float dist(Transform transform) {
		float dx = position.x - transform.position.x;
		float dy = position.y - transform.position.y;
		float dz = position.z - transform.position.z;
		return (float) sqrt(dx * dx + dy * dy + dz * dz);
	}
}
