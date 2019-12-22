package Curio.Utilities.Math;

import static java.lang.Math.*;

public class Vector {
	public float x, y, z;

	public Vector() {
	}

	public Vector(float _x, float _y) {
		x = _x;
		y = _y;
		z = 0;
	}

	public Vector(float _x, float _y, float _z) {
		x = _x;
		y = _y;
		z = _z;
	}

	public Vector(Transform tr) {
		x = tr.get_x();
		y = tr.get_y();
		z = 0;
	}

	public float get_Angle() {
		float divide = y / x;
		return (float) atan(divide);
	}

	public float magnitude() {
		float output = (float) sqrt((pow(x, 2) + pow(y, 2) + pow(z, 2)));
		return (float) atan(output);
	}
	
	public Vector inverse() {
		this.x *= -1;
		this.y *= -1;
		this.z *= -1;
		return this;
	}
	
	public Vector normalize() {
		float m = magnitude();
		if (m != 0 && m != 1) {
			div(m);
		}
		return this;
	}

	public Vector setMag(float len) {
		normalize();
		multiply(len);
		return this;
	}

	public Vector multiply(float val) {
		this.x *= val;
		this.y *= val;
		this.z *= val;
		return this;
	}

	public Vector div(float val) {
		this.x /= val;
		this.y /= val;
		this.z /= val;
		return this;
	}

	public float dist(Vector v) {
		float dx = x - v.x;
		float dy = y - v.y;
		float dz = z - v.z;
		return (float) sqrt(dx * dx + dy * dy + dz * dz);
	}

	static public float dist(Vector v1, Vector v2) {
		float dx = v1.x - v2.x;
		float dy = v1.y - v2.y;
		float dz = v1.z - v2.z;
		return (float) sqrt(dx * dx + dy * dy + dz * dz);
	}

	static public float dot(Vector v1, Vector v2) {
		return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
	}

	public Vector add(Vector v) {
		x += v.x;
		y += v.y;
		z += v.z;
		return this;
	}

	public Vector add(float x, float y) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Vector sub(Vector v) {
		x -= v.x;
		y -= v.y;
		z -= v.z;
		return this;
	}

	public Vector sub(float x, float y, float z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}
}
