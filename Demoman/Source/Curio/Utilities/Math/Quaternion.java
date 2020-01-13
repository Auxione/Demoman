package Curio.Utilities.Math;

public class Quaternion {
//w + xj + yj + zk
	private Vector imaginary;
	private double w;

	Quaternion(double w, double x, double y, double z) {
		this.imaginary = new Vector(x, y, z);
		this.w = w;
	}

	Quaternion(float w, Vector imaginary) {
		this.imaginary = imaginary;
		this.w = w;
	}

	public Quaternion multiply(Quaternion q2) {
		// Components of the first quaternion.
		double q1a = w;
		double q1b = imaginary.x;
		double q1c = imaginary.y;
		double q1d = imaginary.z;

		// Components of the second quaternion.
		double q2a = q2.w;
		double q2b = q2.imaginary.x;
		double q2c = q2.imaginary.y;
		double q2d = q2.imaginary.z;

		// Components of the product.
		double w = q1a * q2a - q1b * q2b - q1c * q2c - q1d * q2d;
		double x = q1a * q2b + q1b * q2a + q1c * q2d - q1d * q2c;
		double y = q1a * q2c - q1b * q2d + q1c * q2a + q1d * q2b;
		double z = q1a * q2d + q1b * q2c - q1c * q2b + q1d * q2a;

		return new Quaternion(w, x, y, z);
	}

	public Quaternion add(Quaternion q2) {
		return new Quaternion(this.w + q2.w, this.imaginary.x + q2.imaginary.x, this.imaginary.y + q2.imaginary.y,
				this.imaginary.z + q2.imaginary.z);
	}
}
