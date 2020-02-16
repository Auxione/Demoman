package Curio.Utilities.Math;

import java.io.Serializable;

public class Transform implements Serializable {
	public Rotation rotation;
	public Vector position;

	// needs quaternion class to rotation
	public Transform() {
		this.position = new Vector();
		this.rotation = new Rotation();
	}
	
	public Transform(float x, float y) {
		this.position = new Vector(x, y);
		this.rotation = new Rotation();
	}

	public Transform(float x, float y, float angle) {
		this.position = new Vector(x, y);
		this.rotation = new Rotation(angle);
	}

	public Transform(Transform tr) {
		this.position = tr.position;
		this.rotation = tr.rotation;
	}

	public boolean equals(Transform transform) {
		if (transform.position.x == this.position.x && transform.position.y == this.position.y) {
			return true;
		} else {
			return false;
		}
	}
}
