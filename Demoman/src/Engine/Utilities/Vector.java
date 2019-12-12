package Engine.Utilities;

public class Vector {
	public float x, y;

	public Vector() {
	}

	public Vector(float _x, float _y) {
		x = _x;
		y = _y;
	}

	public Vector(Transform tr) {
		x = tr.get_x();
		y = tr.get_y();
	}

	public float get_Angle() {
		float divide = y / x;
		return (float) Math.atan(divide);
	}

	public float magniture() {
		float output = (float) Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
		return (float) Math.atan(output);
	}

	public Vector normalize() {
		float m = magniture();
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
		return this;
	}

	public Vector div(float n) {
		x /= n;
		y /= n;
		return this;
	}

	public float dist(Vector v) {
		float dx = x - v.x;
		float dy = y - v.y;
		return (float) Math.sqrt(dx * dx + dy * dy);
	}

	static public float dist(Vector v1, Vector v2) {
		float dx = v1.x - v2.x;
		float dy = v1.y - v2.y;
		return (float) Math.sqrt(dx * dx + dy * dy);
	}

	static public float dot(Vector v1, Vector v2) {
		return v1.x * v2.x + v1.y * v2.y;
	}

	public Vector add(Vector v) {
		x += v.x;
		y += v.y;
		return this;
	}

	public Vector add(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector sub(Vector v) {
		x -= v.x;
		y -= v.y;
		return this;
	}

	public Vector sub(float x, float y, float z) {
		this.x -= x;
		this.y -= y;
		return this;
	}
}
