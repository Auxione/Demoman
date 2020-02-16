package Curio.Utilities.Math;

import static java.lang.Math.*;

import java.io.Serializable;

import Curio.Functions;

public class Vector implements Serializable {
	public float x = 0.0f, y = 0.0f, z = 0.0f;

	public Vector() {
	}

	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector(Vector vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
	}

	public Vector set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public Vector set(Vector vector) {
		return set(vector.x, vector.y, vector.z);
	}

	public Vector add(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Vector add(Vector vector) {
		return add(vector.x, vector.y, vector.z);
	}

	public Vector sub(float x, float y, float z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	public Vector sub(Vector vector) {
		return sub(vector.x, vector.y, vector.z);
	}

	public Vector multiply(float value) {
		this.x *= value;
		this.y *= value;
		this.z *= value;
		return this;
	}

	public Vector divide(float value) {
		this.x /= value;
		this.y /= value;
		this.z /= value;
		return this;
	}

	public Vector inverse() {
		return multiply(-1.0f);
	}

	public float getAngle() {
		return (float) atan2(x, y);
	}

	public float getAngleInDegrees() {
		return (float) Math.toDegrees(atan2(x, y));
	}

	public float getAngleInDegreesMAPPED() {
		float f = (float) Math.toDegrees(atan2(x, y));
		return Functions.map(f, -180, 180, 360, 0);
	}

	public float magnitude() {
		return (float) sqrt((pow(x, 2.0f) + pow(y, 2.0f) + pow(z, 2.0f)));
	}

	public Vector normalize() {
		float m = magnitude();
		if (m != 0 && m != 1) {
			divide(m);
		}
		return this;
	}

	public Vector setMagnitude(float value) {
		this.normalize();
		this.multiply(value);
		return this;
	}

	public Vector distance(Vector vector) {
		return new Vector(this.x - vector.x, this.y - vector.y, this.z - vector.z);
	}

	public float distanceSQRT(Vector vector) {
		float dx = x - vector.x;
		float dy = y - vector.y;
		float dz = z - vector.z;
		return (float) sqrt(dx * dx + dy * dy + dz * dz);
	}

	public float dotProduct(Vector vector) {
		return this.x * vector.x + this.y * vector.y + this.z * vector.z;
	}

	public Vector vectorProduct(Vector vector) {
		return new Vector(this.y * vector.z - this.z * vector.y, this.z * vector.x - this.x * vector.z,
				this.x * vector.y - this.y * vector.x);
	}

	public Vector addScaledVector(Vector vector, float scale) {
		return add(vector.multiply(scale));
	}

	public boolean inBetween(Vector vector1, Vector vector2) {
		float c1 = vector1.dotProduct(this);
		float c2 = this.dotProduct(vector2);
		if (c1 >= 0 && c2 >= 0) {
			return true;
		}
		return false;
	}
}
