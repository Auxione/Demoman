package Curio.Utilities.Math.Geometry;

public class Circle implements Shape {
	public float radius = 0.0f;

	public Circle() {

	}

	public Circle setRadius(float radius) {
		this.radius = radius;
		return this;
	}
}
