package Curio.Utilities.Math;

import static java.lang.Math.*;

import java.io.Serializable;

import Curio.Functions;

public class Vector implements Serializable {
	public float x = 0, y = 0, z = 0;

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

	public float getAngle() {
		return (float) atan2(x,y);
	}

	public float getAngleInDegrees() {
		return (float) Math.toDegrees(atan2(x,y));
	}
	
	public float getAngleInDegreesMAPPED() {
		float f = (float) Math.toDegrees(atan2(x,y));
		
		return Functions.map(f, -180, 180, 360, 0);
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

	public Vector multiply(float d) {
		this.x *= d;
		this.y *= d;
		this.z *= d;
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
		this.x -= v.x;
		this.y -= v.y;
		this.z -= v.z;
		return this;
	}

	public Vector sub(float x, float y, float z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}
}
